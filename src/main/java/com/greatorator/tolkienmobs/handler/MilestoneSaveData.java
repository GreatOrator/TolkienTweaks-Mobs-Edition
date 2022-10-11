package com.greatorator.tolkienmobs.handler;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.event.entity.ServerEntityEvents;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by brandon3055 on 11/10/2022
 */
public class MilestoneSaveData extends WorldSavedData {
    private static final String SAVE_DATA_NAME = "ttm_milestone_data";
    private static MilestoneSaveData CLIENT_INSTANCE = new MilestoneSaveData("");

    private Map<UUID, MilestoneData> dataMap = new HashMap<>();

    public MilestoneSaveData(String name) {
        super(name);
    }

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(MilestoneSaveData::onPlayerLogin);
    }

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) {
            return;
        }
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        MilestoneSaveData saveData = getInstance(player.level);
        if (saveData != null) {
            TolkienNetwork.sendMilestonesToClients(saveData, player);
        }
    }

    // These are the methods you will use

    public static void addPlayerToMilestone(MilestoneTile tile, PlayerEntity player) {
        if (player.level.isClientSide) return;
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        if (saveData != null) {
            updateMilestone(tile);
            UUID uuid = tile.getUUID();
            MilestoneData data = saveData.dataMap.get(uuid);
            if (!data.name.equals(tile.milestoneName.get())) {
                data.name = tile.milestoneName.get();
                saveData.setDirty();
                TolkienNetwork.sendMilestonesToClients(saveData, null);
            }
            if (!data.players.contains(player.getUUID())) {
                data.players.add(player.getUUID());
                saveData.setDirty();
                TolkienNetwork.sendMilestonesToClients(saveData, (ServerPlayerEntity) player);
            }
        }
    }

    public static void updateMilestone(MilestoneTile tile) {
        if (tile.getLevel().isClientSide) return;
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        if (saveData != null) {
            UUID uuid = tile.getUUID();
            MilestoneData data = saveData.dataMap.computeIfAbsent(uuid, e -> new MilestoneData(tile.getLevel().dimension(), tile.getBlockPos(), uuid));
            data.name = tile.milestoneName.get();
            data.paymentItem = tile.paymentItem.get().getItem();
            data.distanceCost = tile.distanceCost.get();
            data.dimensionCost = tile.dimensionCost.get();


            TolkienNetwork.sendMilestonesToClients(saveData, null);
            saveData.setDirty();
        }
    }

    public static List<MilestoneData> getKnownByPlayer(PlayerEntity player) {
        MilestoneSaveData saveData = player.level.isClientSide ? CLIENT_INSTANCE : getInstance(player.level);
        List<MilestoneData> known = new ArrayList<>();
        if (saveData != null) {
            for (MilestoneData data : saveData.dataMap.values()) {
                if (data.players.contains(player.getUUID())) {
                    known.add(data);
                }
            }
        }
        return known;
    }

    @Nullable
    public static MilestoneData getMilestoneData(World world, UUID uuid) {
        MilestoneSaveData saveData = getInstance(world);
        if (saveData != null && saveData.dataMap.containsKey(uuid)) {
            return saveData.dataMap.get(uuid);
        }
        return null;
    }

    public static void removeMilestone(MilestoneTile tile) {
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        UUID id = tile.getUUID();
        if (saveData != null && saveData.dataMap.containsKey(id)) {
            saveData.dataMap.remove(id);
            saveData.setDirty();
        }
    }

    public static boolean isKnownByClient(UUID tileUUID, UUID playerUUID) {
        return CLIENT_INSTANCE.dataMap.containsKey(tileUUID) && CLIENT_INSTANCE.dataMap.get(tileUUID).players.contains(playerUUID);
    }

    // Internal Methods

    @Nullable
    public static MilestoneSaveData getInstance(World world) {
        if (world instanceof ServerWorld && world.getServer() != null) {
            ServerWorld level = world.getServer().getLevel(World.OVERWORLD);
            if (level != null) {
                return level.getDataStorage().computeIfAbsent(() -> new MilestoneSaveData(SAVE_DATA_NAME), SAVE_DATA_NAME);
            }
        }
        return null;
    }

    @Override
    public void load(CompoundNBT nbt) {
        dataMap.clear();
        ListNBT list = nbt.getList("data", 10);
        for (INBT inbt : list) {
            MilestoneData data = MilestoneData.read((CompoundNBT) inbt);
            dataMap.put(data.uuid, data);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        ListNBT list = new ListNBT();
        for (MilestoneData value : dataMap.values()) {
            list.add(value.write());
        }
        nbt.put("data", list);
        return nbt;
    }

    public void serialize(MCDataOutput output) {
        output.writeVarInt(dataMap.size());
        for (MilestoneData data : dataMap.values()) {
            data.serialize(output);
        }
    }

    public static void deserialize(MCDataInput input) {
        CLIENT_INSTANCE.dataMap.clear();
        int count = input.readVarInt();
        for (int i = 0; i < count; i++) {
            MilestoneData data = MilestoneData.deserialize(input);
            CLIENT_INSTANCE.dataMap.put(data.uuid, data);
        }
    }

    public static class MilestoneData {
        private BlockPos pos;
        private UUID uuid;
        private RegistryKey<World> worldKey;
        private List<UUID> players = new ArrayList<>();

        private String name;
        private Item paymentItem = Items.AIR;
        private int distanceCost = 0;
        private int dimensionCost = 0;

        public MilestoneData(RegistryKey<World> worldKey, BlockPos pos, UUID uuid) {
            this.pos = pos;
            this.uuid = uuid;
            this.worldKey = worldKey;
        }

        public BlockPos getPos() {
            return pos;
        }

        public UUID getUuid() {
            return uuid;
        }

        public RegistryKey<World> getWorldKey() {
            return worldKey;
        }

        public List<UUID> getPlayers() {
            return players;
        }

        public String getName() {
            return name;
        }

        public Item getPaymentItem() {
            return paymentItem;
        }

        public int getDistanceCost() {
            return distanceCost;
        }

        public int getDimensionCost() {
            return dimensionCost;
        }

        private CompoundNBT write() {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putLong("pos", pos.asLong());
            nbt.putUUID("uuid", uuid);
            nbt.putString("world", worldKey.location().toString());
            nbt.putString("name", name);
            nbt.putString("payment", paymentItem.getRegistryName().toString());
            nbt.putInt("dist_cost", distanceCost);
            nbt.putInt("dim_cost", dimensionCost);
            ListNBT list = new ListNBT();
            players.forEach(e -> list.add(StringNBT.valueOf(e.toString())));
            nbt.put("players", list);
            return nbt;
        }

        private static MilestoneData read(CompoundNBT nbt) {
            BlockPos pos = BlockPos.of(nbt.getLong("pos"));
            UUID uuid = nbt.getUUID("uuid");
            RegistryKey<World> key = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(nbt.getString("world")));
            MilestoneData data = new MilestoneData(key, pos, uuid);
            data.name = nbt.getString("name");
            data.paymentItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("payment")));
            data.distanceCost = nbt.getInt("dist_cost");
            data.dimensionCost = nbt.getInt("dim_cost");

            ListNBT list = nbt.getList("players", 8);
            list.forEach(e -> data.players.add(UUID.fromString(e.getAsString())));
            return data;
        }

        private void serialize(MCDataOutput output) {
            output.writePos(pos);
            output.writeUUID(uuid);
            output.writeResourceLocation(worldKey.location());
            output.writeString(name);
            output.writeResourceLocation(paymentItem.getRegistryName());
            output.writeVarInt(distanceCost);
            output.writeVarInt(dimensionCost);

            output.writeVarInt(players.size());
            for (UUID player : players) {
                output.writeUUID(player);
            }
        }

        public static MilestoneData deserialize(MCDataInput input) {
            BlockPos pos = input.readPos();
            UUID uuid = input.readUUID();
            RegistryKey<World> key = RegistryKey.create(Registry.DIMENSION_REGISTRY, input.readResourceLocation());
            MilestoneData data = new MilestoneData(key, pos, uuid);
            data.name = input.readString();
            data.paymentItem = ForgeRegistries.ITEMS.getValue(input.readResourceLocation());
            data.distanceCost = input.readVarInt();
            data.dimensionCost = input.readVarInt();

            int count = input.readVarInt();
            for (int i = 0; i < count; i++) {
                data.players.add(input.readUUID());
            }
            return data;
        }
    }
}
