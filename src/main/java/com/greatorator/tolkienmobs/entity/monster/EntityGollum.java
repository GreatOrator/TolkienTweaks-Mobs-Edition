package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGollum extends EntityMob {
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/gollum");

    public EntityGollum(World worldIn) {
        super(worldIn);
        setSize(1.0F, 1.0F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityHuron.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGoblin.class, true));
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAITTMAttack(this, 1.0D, false));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        return ientitylivingdata;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleGollum;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtGollum;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathGollum;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepGollum, 0.25F, 1.0F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    protected boolean isValidLightLevel() {
        return false;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }
}
