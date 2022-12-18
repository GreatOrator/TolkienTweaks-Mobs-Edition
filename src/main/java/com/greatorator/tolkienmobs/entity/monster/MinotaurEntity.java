package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MinotaurEntity extends MonsterEntity {
    public MinotaurEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 3.3125F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GoblinEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MordorOrcEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GoblinKingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, UrukHaiEntity.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance instance) {
        super.populateDefaultEquipmentSlots(instance);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(TolkienItems.AXE_MORGULIRON.get()));
    }
}