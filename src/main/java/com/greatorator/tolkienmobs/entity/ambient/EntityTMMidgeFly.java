package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.MobEffects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Borrowed from Twilight Forest */
public class EntityTMMidgeFly extends EntityTMHostiles {
    public EntityTMMidgeFly(World world) {
        super(world);
        setSize(.7F, 1.9F);
        this.stepHeight = 2.1f;
        this.setTtmEffect(MobEffects.HUNGER);
        this.setLootTable(LootInit.MIDGEFLY);
        this.setTtmDuration(140);
    }

    @Override
    public double getAttackDamage() {
        return 3.0D;
    }

    @Override
    public double getArmorStrength() {
        return 0.0D;
    }

    @Override
    public double getHealthLevel() {
        return 12.0D;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.soundIdleMidgeFly;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (world.getBiome(new BlockPos(this)) == Biomes.SWAMPLAND) {
            return world.checkNoEntityCollision(getEntityBoundingBox()) && world.getCollisionBoxes(this, getEntityBoundingBox()).size() == 0;
        } else {
            return super.getCanSpawnHere();
        }
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }
}
