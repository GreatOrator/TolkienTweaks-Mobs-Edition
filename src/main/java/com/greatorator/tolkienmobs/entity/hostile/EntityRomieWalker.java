package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/** Idea provided by Romie **/
public class EntityRomieWalker extends EntityTMHostiles {

    public EntityRomieWalker(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 2.0F);
        this.experienceValue = TTMConfig.romieXP;
        this.setTtmEffect(MobEffects.POISON);
        this.isImmuneToFire = true;
        this.setMobMentality(true, SoundInit.soundAngryRomieWalker);
        this.setRndMinMax(1,5);
    }

    @Override
    public void fall(float parDistance, float parDamageMultiplier)
    {
    }

    @Override
    public boolean getCanSpawnHere() {
        int willSpawn = this.spawnChance();

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && willSpawn <= 10;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIBreakDoor(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityTMHostiles.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(17.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(TTMConfig.romieSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(TTMConfig.romieKnockback);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackDamage());
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getArmorStrength());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getHealthLevel());
    }

    @Override
    public double getAttackDamage() {
        return TTMConfig.romieAttack;
    }

    @Override
    public double getArmorStrength() {
        return TTMConfig.romieArmor;
    }

    @Override
    public double getHealthLevel() {
        return TTMConfig.romieHealth;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleRomieWalker;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtRomieWalker;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathRomieWalker;
    }
}