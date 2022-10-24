package com.greatorator.tolkienmobs.container.capability;

import com.greatorator.tolkienmobs.entity.tile.CamoSpawnerTile;
import com.greatorator.tolkienmobs.event.entity.WorldEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.Function;

import static net.minecraftforge.event.ForgeEventFactory.canEntitySpawnSpawner;

public class CamoSpawnerLogic extends AbstractSpawner {
    private final CamoSpawnerTile tile;
    private double mobRotation;
    private double prevMobRotation;
    private static final Map<String, ResourceLocation> rlCache = new WeakHashMap<>();

    public CamoSpawnerLogic(CamoSpawnerTile tile) {
        this.tile = tile;
    }

    @Override
    public void tick() {
        if (!this.isNearPlayer()) {
            this.prevMobRotation = this.mobRotation;
        } else {
            BlockPos blockpos = this.getPos();
            World world = this.getLevel();
            if (world.isClientSide && tile.spawnerParticles.get()) {
                double d3 = (float) blockpos.getX() + world.random.nextFloat();
                double d4 = (float) blockpos.getY() + world.random.nextFloat();
                double d5 = (float) blockpos.getZ() + world.random.nextFloat();
                world.addParticle(ParticleTypes.SMOKE, d3, d4, d5, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d3, d4, d5, 0.0D, 0.0D, 0.0D);

                if (tile.spawnDelay.get() > 0) {
                    tile.spawnDelay.dec();
                }

                this.prevMobRotation = this.mobRotation;
                this.mobRotation = (this.mobRotation + (double) (1000.0F / ((float) tile.spawnDelay.get() + 200.0F))) % 360.0D;
            } else {
                if (tile.spawnDelay.get() == -1) {
                    this.resetTimer();
                }

                if (tile.spawnDelay.get() > 0) {
                    tile.spawnDelay.dec();
                    return;
                }
                boolean flag = false;
                int successCount = 0;
                for (int i = 0; i < tile.spawnCount.get(); ++i) {
                    double spawnX = (double) blockpos.getX() + (world.random.nextDouble() - world.random.nextDouble()) * (double) tile.spawnRange.get() + 0.5D;
                    double spawnY = blockpos.getY() + world.random.nextInt(3) - 1;
                    double spawnZ = (double) blockpos.getZ() + (world.random.nextDouble() - world.random.nextDouble()) * (double) tile.spawnRange.get() + 0.5D;
                    if(!tile.entityTags.isEmpty()) {
                        String randTag = tile.entityTags.get(world.random.nextInt(tile.entityTags.size()));
                        Entity entity = createEntity(world, randTag);
                        entity.moveTo(spawnX, spawnY, spawnZ, 0, 0);

                        int nearbyCount = world.getEntitiesOfClass(entity.getClass(), (new AxisAlignedBB(blockpos.getX(), blockpos.getY(), blockpos.getZ(), blockpos.getX() + 1, blockpos.getY() + 1, blockpos.getZ() + 1)).inflate(tile.clusterRange.get())).size();
                        if (nearbyCount >= tile.maxCluster.get()) {
                            this.resetTimer();
                            return;
                        }
                        MobEntity entityliving = entity instanceof MobEntity ? (MobEntity) entity : null;
                        entity.moveTo(entity.getX(), entity.getY(), entity.getZ(), world.random.nextFloat() * 360.0F, 0.0F);
                        boolean canSpawn;
                        if (tile.ignoreSpawnReq.get()) {
                            Event.Result result = ForgeEventFactory.canEntitySpawn(entityliving, world, (float) entity.getX(), (float) entity.getY(), (float) entity.getZ(), this, SpawnReason.SPAWNER);
                            canSpawn = isNotColliding(entity) && (result == Event.Result.DEFAULT || result == Event.Result.ALLOW);
                        }else {
                            canSpawn = canEntitySpawnSpawner(entityliving, world, (float) entity.getX(), (float) entity.getY(), (float) entity.getZ(), this);
                        }
                        if (canSpawn) {
                            if (entityliving == null || canEntitySpawnSpawner((MobEntity) entityliving, getLevel(), (float) entity.getX(), (float) entity.getY(), (float) entity.getZ(), this)) {
                                if (!tile.requirePlayer.get() && entity instanceof MobEntity) {
                                    ((MobEntity) entity).setPersistenceRequired();
                                    entity.getPersistentData().putLong("TTMSpawnedMob", System.currentTimeMillis());
                                    WorldEvents.onMobSpawnedBySpawner((MobEntity) entity);
                                }

                                if (!((ServerWorld) world).tryAddFreshEntityWithPassengers(entity)) {
                                    this.resetTimer();
                                    return;
                                }

                                world.levelEvent(2004, blockpos, 0);

                                if (entityliving != null) {
                                    if (entity instanceof MobEntity) {
                                        ((MobEntity) entity).spawnAnim();
                                    }
                                }

                                flag = true;
                                successCount++;
                            }
                        }
                    }

                    if (successCount >= tile.maxCluster.get()) {
                        break;
                    }
                }

                if (flag) {
                    this.resetTimer();
                }
            }
        }
    }

    public boolean isNotColliding(Entity entity) {
        World world = this.getLevel();
        return !world.containsAnyLiquid(entity.getBoundingBox()) && world.noCollision(entity, entity.getBoundingBox());
    }

    public static Entity createEntity(World world, String entityTag) {
        try {
            CompoundNBT entityData = JsonToNBT.parseTag(entityTag);
            if (!entityData.contains("id")) return null;

            Entity entity = EntityType.create(entityData, world).get();
            if (entity == null) {
                entity = EntityType.PIG.create(world);
            }
            else {
                MobEntity entityliving = (MobEntity) entity;
                entityliving.yHeadRot = entityliving.yBodyRot;
                entityliving.yHeadRotO = entityliving.yBodyRot;
                entityliving.finalizeSpawn((ServerWorld)world, world.getCurrentDifficultyAt(new BlockPos(0,0,0)), SpawnReason.SPAWNER, null, null);

                CompoundNBT nbttagcompound1 = entity.saveWithoutId(new CompoundNBT());
                UUID uuid = entity.getUUID();
                nbttagcompound1.merge(entityData);
                entity.load(nbttagcompound1);
                entity.setUUID(uuid);
            }
            return entity;
        }
        catch (Throwable e) {
            return EntityType.PIG.create(world);
        }
    }

    private void resetTimer() {
        if (tile.maxSpawnDelay.get() <= tile.minSpawnDelay.get()) {
            tile.spawnDelay.set((short) tile.minSpawnDelay.get());
        } else {
            int i = tile.maxSpawnDelay.get() - tile.minSpawnDelay.get();
            tile.spawnDelay.set((short) (tile.minSpawnDelay.get() + this.getLevel().random.nextInt(i)));
        }

        this.broadcastEvent(1);
    }

    public boolean isNearPlayer() {
        BlockPos blockpos = this.getPos();
        return this.getLevel().hasNearbyAlivePlayer((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D, (double)tile.activationRange.get()) && tile.requirePlayer.get();
    }

    @Override
    public void broadcastEvent(int id) {
        tile.getLevel().blockEvent(tile.getBlockPos(), Blocks.SPAWNER, id, 0);
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    @Override
    public Entity getOrCreateDisplayEntity() {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.nextSpawnData.getTag(), this.getLevel(), Function.identity());
            if (this.nextSpawnData.getTag().size() == 1 && this.nextSpawnData.getTag().contains("id", 8) && this.displayEntity instanceof MobEntity) {
            }
        }

        return this.displayEntity;
    }

    @Override
    public World getLevel() {
        return tile.getLevel();
    }

    @Override
    public BlockPos getPos() {
        return tile.getBlockPos();
    }
}
