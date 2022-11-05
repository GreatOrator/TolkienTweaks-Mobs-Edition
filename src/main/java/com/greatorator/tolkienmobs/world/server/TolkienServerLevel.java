package com.greatorator.tolkienmobs.world.server;


import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import com.greatorator.tolkienmobs.entity.boss.fellbeast.phase.FellBeastFightManager;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executor;

public class TolkienServerLevel extends ServerLevel {
    private final Int2ObjectMap<Entity> entitiesById = new Int2ObjectLinkedOpenHashMap<>();
    private final FellBeastFightManager fellbeastFight;
    public TolkienServerLevel(MinecraftServer p_i241885_1_, Executor p_i241885_2_, LevelStorageSource.LevelStorageAccess p_i241885_3_, ServerLevelData p_i241885_4_, ResourceKey<Level> p_i241885_5_, Holder<DimensionType> p_i241885_6_, ChunkProgressListener p_i241885_7_, ChunkGenerator p_i241885_8_, boolean p_i241885_9_, long p_i241885_10_, List<CustomSpawner> p_i241885_12_, boolean p_i241885_13_) {
        super(p_i241885_1_, p_i241885_2_, p_i241885_3_, p_i241885_4_, p_i241885_5_, p_i241885_6_, p_i241885_7_, p_i241885_8_, p_i241885_9_, p_i241885_10_, p_i241885_12_, p_i241885_13_);
        long i = p_i241885_1_.getWorldData().worldGenSettings().seed();
        if (this.dimensionType().createDragonFight()) {
            this.fellbeastFight = null;
        } else {
            this.fellbeastFight = null;
//            this.fellbeastFight = new FellBeastFightManager(this, i, p_i241885_1_.getWorldData().endDragonFightData());
        }
    }

    public List<FellBeastEntity> getFellBeasts() {
        List<FellBeastEntity> list = Lists.newArrayList();

//        for(Entity entity : this.entitiesById.values()) {
//            if (entity instanceof FellBeastEntity && entity.isAlive()) {
//                list.add((FellBeastEntity)entity);
//            }
//        }

        return list;
    }

    @Nullable
    public FellBeastFightManager fellbeastFight() {
        return this.fellbeastFight;
    }

}