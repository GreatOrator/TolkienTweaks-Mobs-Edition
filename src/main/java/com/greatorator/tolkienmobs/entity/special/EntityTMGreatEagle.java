package com.greatorator.tolkienmobs.entity.special;

import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.entity.entityai.AIStates;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTMGreatEagle extends EntityTMBirds {
    public EntityTMGreatEagle(World parWorld)
    {
        super(parWorld);
        this.setSize(1.5F, 1.8F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.GWAIHIR;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getState() == AIStates.STATE_TAKING_OFF || getState() == AIStates.STATE_TRAVELLING)
        {
            return SoundInit.soundFlappingTMGreatEagle;
        }
        else
        {
            return SoundInit.soundCallTMGreatEagle;
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtTMGreatEagle;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathTMGreatEagle;
    }
}