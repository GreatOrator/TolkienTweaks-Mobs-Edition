package com.greatorator.tolkienmobs.entity.special;

import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.BirdEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class GreatEagleEntity extends BirdEntity {
    public GreatEagleEntity(EntityType<? extends BirdEntity> entityIn, World worldIn) {
        super(entityIn, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return FlyingEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.ARMOR, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 21.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.75F)
                .add(Attributes.FLYING_SPEED, (double) 1.4F);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return p_213348_2_.height * 0.6F;
    }

    // Sound Region
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundGenerator.soundCallTMGreatEagle.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtTMGreatEagle.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundDeathTMGreatEagle.get();
    }
    // End Region
}