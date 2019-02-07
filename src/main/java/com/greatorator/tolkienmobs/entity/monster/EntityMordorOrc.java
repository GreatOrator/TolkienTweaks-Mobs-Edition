package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityTTM;
import com.greatorator.tolkienmobs.entity.passive.EntityDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityElves;
import com.greatorator.tolkienmobs.entity.passive.EntityHobbit;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.entity.passive.EntityHuman;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;

public class EntityMordorOrc extends EntityTTM implements IEntityAdditionalSpawnData {

    public EntityMordorOrc(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.8F);
        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
        this.setRndMinMax(1,5);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAITTMAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityMordorOrc.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHobbit.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHuman.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDwarf.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityElves.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.MORC;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }
}