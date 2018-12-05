package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityHerds;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityAuroch extends EntityHerds {
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/auroch");
    public EntityAuroch(World worldIn) {
        super(worldIn);

        this.setSize(1.2F, 2.1F);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_COW_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public EntityAuroch createChild(EntityAgeable par1EntityAgeable)
    {
        return new EntityAuroch(world);
    }
}
