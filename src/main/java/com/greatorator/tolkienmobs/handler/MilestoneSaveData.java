package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by brandon3055 on 11/10/2022
 */
public class MilestoneSaveData extends WorldSavedData {
    private static final String SAVE_DATA_NAME = "ttm_milestone_data";

    private Map<UUID, MilestoneData> dataMap = new HashMap<>();

    public MilestoneSaveData(String name) {
        super(name);
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

    //TODO add some cleanup code to remove any milestones that no longer exist.
    public static List<MilestoneData> getKnownByPlayer(PlayerEntity player) {
        if (player.level.isClientSide) return Collections.emptyList();
        MilestoneSaveData saveData = getInstance(player.level);
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
    }
}
