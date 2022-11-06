package com.greatorator.tolkienmobs.handler;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by brandon3055 on 11/10/2022
 */
public class MilestoneSaveData extends SavedData {
    private static final String SAVE_DATA_NAME = "ttm_milestone_data";
    private static final MilestoneSaveData CLIENT_INSTANCE = new MilestoneSaveData();

    private static final Map<UUID, MilestoneData> dataMap = new HashMap<>();

    public MilestoneSaveData() {
        super();
    }

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(MilestoneSaveData::onPlayerLogin);
    }

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer player = (ServerPlayer) event.getPlayer();
        MilestoneSaveData saveData = getInstance(player.level);
        if (saveData != null) {
            TolkienNetwork.sendMilestonesToClients(saveData, player);
        }
    }

    // These are the methods you will use

    public static void addPlayerToMilestone(MilestoneTile tile, Player player) {
        if (player.level.isClientSide) return;
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        if (saveData != null) {
            updateMilestone(tile);
            UUID uuid = tile.getUUID();
            MilestoneData data = dataMap.get(uuid);
            if (!data.name.equals(tile.milestoneName.get())) {
                data.name = tile.milestoneName.get();
                saveData.setDirty();
                TolkienNetwork.sendMilestonesToClients(saveData, null);
            }
            if (!data.players.contains(player.getUUID())) {
                data.players.add(player.getUUID());
                saveData.setDirty();
                TolkienNetwork.sendMilestonesToClients(saveData, (ServerPlayer) player);
            }
        }
    }

    public static void updateMilestone(MilestoneTile tile) {
        if (tile.getLevel().isClientSide) return;
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        if (saveData != null) {
            UUID uuid = tile.getUUID();
            MilestoneData data = dataMap.computeIfAbsent(uuid, e -> new MilestoneData(tile.getLevel().dimension(), tile.getBlockPos(), uuid));
            data.name = tile.milestoneName.get();
            data.paymentItem = tile.paymentItem.get().getItem();
            data.distanceCost = tile.distanceCost.get();
            data.dimensionCost = tile.dimensionCost.get();


            TolkienNetwork.sendMilestonesToClients(saveData, null);
            saveData.setDirty();
        }
    }

    public static List<MilestoneData> getKnownByPlayer(Player player) {
        MilestoneSaveData saveData = player.level.isClientSide ? CLIENT_INSTANCE : getInstance(player.level);
        List<MilestoneData> known = new ArrayList<>();
        if (saveData != null) {
            for (MilestoneData data : dataMap.values()) {
                if (data.players.contains(player.getUUID())) {
                    known.add(data);
                }
            }
        }
        return known;
    }

    @Nullable
    public static MilestoneData getMilestoneData(Level world, UUID uuid) {
        MilestoneSaveData saveData = getInstance(world);
        if (saveData != null && dataMap.containsKey(uuid)) {
            return dataMap.get(uuid);
        }
        return null;
    }

    public static void removeMilestone(MilestoneTile tile) {
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        UUID id = tile.getUUID();
        if (saveData != null && dataMap.containsKey(id)) {
            dataMap.remove(id);
            saveData.setDirty();
        }
    }

    public static boolean isKnownByClient(UUID tileUUID, UUID playerUUID) {
        return dataMap.containsKey(tileUUID) && dataMap.get(tileUUID).players.contains(playerUUID);
    }

    // Internal Methods

    @Nullable
    public static MilestoneSaveData getInstance(Level world) {
        if (world instanceof ServerLevel && world.getServer() != null) {
            ServerLevel level = world.getServer().getLevel(Level.OVERWORLD);
            if (level != null) {
                return level.getDataStorage().computeIfAbsent(MilestoneSaveData::load, MilestoneSaveData::new, MilestoneSaveData.SAVE_DATA_NAME);
            }
        }
        return null;
    }

    public static MilestoneSaveData load(CompoundTag nbt) {
        dataMap.clear();
        MilestoneSaveData saveData = new MilestoneSaveData();
        ListTag list = nbt.getList("data", 10);
        for (Tag inbt : list) {
            MilestoneData data = MilestoneData.read((CompoundTag) inbt);
            dataMap.put(data.uuid, data);
        }
        return saveData;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        ListTag list = new ListTag();
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
        private ResourceKey<Level> worldKey;
        private List<UUID> players = new ArrayList<>();

        private String name;
        private Item paymentItem = Items.AIR;
        private int distanceCost = 0;
        private int dimensionCost = 0;

        public MilestoneData(ResourceKey<Level> worldKey, BlockPos pos, UUID uuid) {
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

        public ResourceKey<Level> getWorldKey() {
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

        private CompoundTag write() {
            CompoundTag nbt = new CompoundTag();
            nbt.putLong("pos", pos.asLong());
            nbt.putUUID("uuid", uuid);
            nbt.putString("world", worldKey.location().toString());
            nbt.putString("name", name);
            nbt.putString("payment", paymentItem.getRegistryName().toString());
            nbt.putInt("dist_cost", distanceCost);
            nbt.putInt("dim_cost", dimensionCost);
            ListTag list = new ListTag();
            players.forEach(e -> list.add(StringTag.valueOf(e.toString())));
            nbt.put("players", list);
            return nbt;
        }

        private static MilestoneData read(CompoundTag nbt) {
            BlockPos pos = BlockPos.of(nbt.getLong("pos"));
            UUID uuid = nbt.getUUID("uuid");
            ResourceKey<Level> key = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(nbt.getString("world")));
            MilestoneData data = new MilestoneData(key, pos, uuid);
            data.name = nbt.getString("name");
            data.paymentItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("payment")));
            data.distanceCost = nbt.getInt("dist_cost");
            data.dimensionCost = nbt.getInt("dim_cost");

            ListTag list = nbt.getList("players", 8);
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
            ResourceKey<Level> key = ResourceKey.create(Registry.DIMENSION_REGISTRY, input.readResourceLocation());
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
