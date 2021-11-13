package com.greatorator.tolkienmobs.entity.special;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class EntityTMGreatEagle extends TameableEntity {
    protected EntityTMGreatEagle(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return null;
    }
}
//    public EntityTMGreatEagle(World parWorld)
//    {
//        super(parWorld);
//        this.setSize(1.5F, 1.8F);
//    }
//
//    @Override
//    protected void applyEntityAttributes()
//    {
//        super.applyEntityAttributes();
//        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
//        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
//        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        return LootInit.GWAIHIR;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        if (getState() == AIStates.STATE_TAKING_OFF || getState() == AIStates.STATE_TRAVELLING)
//        {
//            return SoundInit.soundFlappingTMGreatEagle;
//        }
//        else
//        {
//            return SoundInit.soundCallTMGreatEagle;
//        }
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundInit.soundHurtTMGreatEagle;
//    }
//
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathTMGreatEagle;
//    }
//}