package com.greatorator.tolkienmobs.handler;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.event.entity.ServerEntityEvents;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
            UUID uuid = UUID.fromString(tile.milestoneUUID.get());
            MilestoneData data = saveData.dataMap.computeIfAbsent(uuid, e -> new MilestoneData(tile.getLevel().dimension(), tile.getBlockPos(), uuid));
            if (!data.players.contains(player.getUUID())) {
                data.players.add(player.getUUID());
                saveData.setDirty();
            }
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

    public static void removeMilestone(MilestoneTile tile) {
        MilestoneSaveData saveData = getInstance(tile.getLevel());
        UUID id = UUID.fromString(tile.milestoneUUID.get());
        if (saveData != null && saveData.dataMap.containsKey(id)) {
            saveData.dataMap.remove(id);
            saveData.setDirty();
        }
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

        private CompoundNBT write() {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putLong("pos", pos.asLong());
            nbt.putUUID("uuid", uuid);
            nbt.putString("world", worldKey.getRegistryName().toString());
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
            ListNBT list = nbt.getList("players", 8);
            list.forEach(e -> data.players.add(UUID.fromString(e.getAsString())));
            return data;
        }

        private void serialize(MCDataOutput output) {
            output.writePos(pos);
            output.writeUUID(uuid);
            output.writeResourceLocation(worldKey.getRegistryName());
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
            int count = input.readVarInt();
            for (int i = 0; i < count; i++) {
                data.players.add(input.readUUID());
            }
            return data;
        }
    }
}
