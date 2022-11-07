package com.greatorator.tolkienmobs.container.capability;

import com.greatorator.tolkienmobs.entity.tile.CamoSpawnerTile;
import com.greatorator.tolkienmobs.event.entity.WorldEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.Function;

public class CamoSpawnerCapability extends BaseSpawner {
    private final CamoSpawnerTile tile;
    private double mobRotation;
    private double prevMobRotation;
    private static final Map<String, ResourceLocation> rlCache = new WeakHashMap<>();

    public CamoSpawnerCapability(CamoSpawnerTile tile) {
        this.tile = tile;
    }

    public void clientTick(Level p_151320_, BlockPos p_151321_) {
        if (!this.isNearPlayer(p_151320_, p_151321_)) {
            this.prevMobRotation = this.mobRotation;
        } else {
            double d0 = (double)p_151321_.getX() + p_151320_.random.nextDouble();
            double d1 = (double)p_151321_.getY() + p_151320_.random.nextDouble();
            double d2 = (double)p_151321_.getZ() + p_151320_.random.nextDouble();
            p_151320_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            p_151320_.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            if (tile.spawnDelay.get() > 0) {
                tile.spawnDelay.dec();
            }

            this.prevMobRotation = this.mobRotation;
            this.mobRotation = (this.mobRotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0D;
        }

    }

    public void serverTick(ServerLevel level, BlockPos blockPos) {
        if (this.isNearPlayer(level, blockPos)) {
            if (tile.spawnDelay.get() == -1) {
                this.resetTimer();
            }

            if (tile.spawnDelay.get() > 0) {
                tile.spawnDelay.dec();
            } else {
                if (tile.entityTags.isEmpty()) {
                    return;
                }
                boolean flag = false;
                int successCount = 0;
                for (int i = 0; i < tile.spawnCount.get() + 3; ++i) {
                    CompoundTag randTag = tile.entityTags.get(level.random.nextInt(tile.entityTags.size()));
                    Entity entity = createEntity(level, randTag);
                    if (entity == null) {
                        continue;
                    }

                    do {
                        double spawnX = (double) blockPos.getX() + (level.random.nextDouble() - level.random.nextDouble()) * (double) this.spawnRange + 0.5D;
                        double spawnY = blockPos.getY() + level.random.nextInt(3) - 1;
                        double spawnZ = (double) blockPos.getZ() + (level.random.nextDouble() - level.random.nextDouble()) * (double) this.spawnRange + 0.5D;
                        entity.absMoveTo(spawnX, spawnY, spawnZ, 0, 0);
                    } while (entity.blockPosition().getX() == tile.getBlockPos().getX() && entity.blockPosition().getZ() == tile.getBlockPos().getZ());

                    if (!level.noCollision(entity.getBoundingBox())) {
                        continue;
                    }

                    int nearbyCount = level.getEntitiesOfClass(entity.getClass(), (new AABB(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + 1)).inflate(tile.clusterRange.get())).size();
                    if (nearbyCount >= tile.maxCluster.get()) {
                        this.resetTimer();
                        return;
                    }

                    Mob mobEntity = entity instanceof Mob ? (Mob) entity : null;
                    entity.moveTo(entity.getX(), entity.getY(), entity.getZ(), level.random.nextFloat() * 360.0F, 0.0F);

                    if (mobEntity == null || canEntitySpawnSpawner(mobEntity, getLevel(), (float) entity.getX(), (float) entity.getY(), (float) entity.getZ(), this)) {
                        if (!tile.requirePlayer.get() && entity instanceof Mob) {
                            ((Mob) entity).setPersistenceRequired();
                            entity.getPersistentData().putLong("TTMSpawnedMob", System.currentTimeMillis());
                            WorldEvents.onMobSpawnedBySpawner((Mob) entity);
                        }

                        if (!((ServerLevel) level).tryAddFreshEntityWithPassengers(entity)) {
                            this.resetTimer();
                            return;
                        }

                        level.levelEvent(2004, blockPos, 0);

                        if (mobEntity != null) {
                            mobEntity.spawnAnim();
                        }

                        flag = true;
                        successCount++;
                    }

                    if (successCount >= tile.spawnCount.get()) {
                        break;
                    }
                }

                if (flag) {
                    this.resetTimer();
                }
            }
        }
    }

    public static Entity createEntity(Level world, CompoundTag entityData) {
        try {
            if (!entityData.contains("id")) return null;

            Entity entity = EntityType.create(entityData, world).get();
            if (entity == null) {
                entity = EntityType.PIG.create(world);
            } else {
                Mob entityliving = (Mob) entity;
                entityliving.yHeadRot = entityliving.yBodyRot;
                entityliving.yHeadRotO = entityliving.yBodyRot;
                entityliving.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(new BlockPos(0, 0, 0)), MobSpawnType.SPAWNER, null, null);

                CompoundTag nbttagcompound1 = entity.saveWithoutId(new CompoundTag());
                UUID uuid = entity.getUUID();
                nbttagcompound1.merge(entityData);
                entity.load(nbttagcompound1);
                entity.setUUID(uuid);
            }
            return entity;
        } catch (Throwable e) {
            return EntityType.PIG.create(world);
        }
    }

    public boolean canEntitySpawnSpawner(Mob entity, Level world, float x, float y, float z, BaseSpawner spawner) {
        Event.Result result = ForgeEventFactory.canEntitySpawn(entity, world, x, y, z, spawner, MobSpawnType.SPAWNER);
        if (result == Event.Result.DEFAULT) {
            return (tile.ignoreSpawnReq.get() || entity.checkSpawnRules(world, MobSpawnType.SPAWNER));
        } else {
            return result == Event.Result.ALLOW;
        }
    }

    private void resetTimer() {
        if (tile.maxSpawnDelay.get() <= tile.minSpawnDelay.get()) {
            tile.spawnDelay.set((short) tile.minSpawnDelay.get());
        } else {
            int i = tile.maxSpawnDelay.get() - tile.minSpawnDelay.get();
            tile.spawnDelay.set((short) (tile.minSpawnDelay.get() + this.getLevel().random.nextInt(i)));
        }

        this.broadcastEvent(tile.getLevel(), tile.getBlockPos(), 1);
    }

    public boolean isNearPlayer(Level level, BlockPos blockpos) {
        return !tile.requirePlayer.get() || level.hasNearbyAlivePlayer((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.5D, (double) blockpos.getZ() + 0.5D, (double) tile.activationRange.get());
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    @Override
    public Entity getOrCreateDisplayEntity(Level world) {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.nextSpawnData.getEntityToSpawn(), this.getLevel(), Function.identity());
            if (this.nextSpawnData.getEntityToSpawn().size() == 1 && this.nextSpawnData.getEntityToSpawn().contains("id", 8) && this.displayEntity instanceof Mob) {
            }
        }
        return this.displayEntity;
    }

    @Override
    public void broadcastEvent(Level level, BlockPos blockPos, int id) {
        level.blockEvent(tile.getBlockPos(), Blocks.SPAWNER, id, 0);
    }

    public Level getLevel() {
        return tile.getLevel();
    }

    public BlockPos getPos() {
        return tile.getBlockPos();
    }
}
