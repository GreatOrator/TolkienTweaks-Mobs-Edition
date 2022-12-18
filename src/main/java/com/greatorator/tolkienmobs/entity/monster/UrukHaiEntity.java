package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UrukHaiEntity extends MonsterEntity {
    public UrukHaiEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.875F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance instance) {
        super.populateDefaultEquipmentSlots(instance);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(TolkienItems.SWORD_URUK.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(TolkienItems.URUK_BOW.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 34.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 8.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundIdleOrc.get();
    }
}