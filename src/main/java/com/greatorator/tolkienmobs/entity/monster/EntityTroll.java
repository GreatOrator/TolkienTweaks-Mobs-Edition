package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityHostiles;
import com.greatorator.tolkienmobs.entity.passive.EntityHobbit;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;

public class EntityTroll extends EntityHostiles implements IEntityAdditionalSpawnData {
    public EntityTroll(World worldIn) {
        super(worldIn);
        this.setSize(3.4F, 4.6F);
        this.setWeaponType(TTMFeatures.AXE_MORGULIRON);
        this.setRndMinMax(1,5);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAITTMAttack(this, 3.0D, false));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityTroll.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHobbit.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(11.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleTroll;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource parDamageSource)
    {
        return SoundInit.soundHurtTroll;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathTroll;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepTroll, 0.25F, 1.0F);
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.TROLL;
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }
}