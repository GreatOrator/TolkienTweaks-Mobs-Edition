package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityTTM;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.entity.passive.EntityHobbit;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;

public class EntityUrukHai extends EntityTTM implements IEntityAdditionalSpawnData {

    public EntityUrukHai(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 2.6F);
        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
        this.setRndMinMax(1,5);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAITTMAttack(this, 1.0D, false));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityUrukHai.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHobbit.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(34.0D);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.URUK;
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }
}