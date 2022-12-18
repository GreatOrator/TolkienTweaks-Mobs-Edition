package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyFollowParentGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyHurtByTargetGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyNearPlayerGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyPanicGoal;
import com.greatorator.tolkienmobs.entity.passive.variant.PassiveVariant;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class HerdEntity extends Animal {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(HerdEntity.class, EntityDataSerializers.INT);

    protected HerdEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Bee.class, 8.0f, 1.5, 1.5));
        this.goalSelector.addGoal(3, new BabyPanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new BabyFollowParentGoal(this, 1.25D, 24.0D, 6.0D, 12.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new BabyHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new BabyNearPlayerGoal(this, 0.5F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverLevel, @Nonnull AgeableMob ageableMob) {
        return null;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor accessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        PassiveVariant variant = Util.getRandom(PassiveVariant.values(), this.random);
        setVariant(variant);
        AgeableMobGroupData ageableMobGroupData;
        if (data == null) {
            data = new AgeableMobGroupData(true);
        }
        if ((ageableMobGroupData = (AgeableMobGroupData)data).getGroupSize() > 1) {
            this.setAge(-24000);
        }
        ageableMobGroupData.increaseGroupSizeByOne();
        Random random = level.getRandom();
        this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random spawn bonus", random.nextInt(3), AttributeModifier.Operation.MULTIPLY_BASE));
        return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 1);
    }

    public PassiveVariant getVariant() {
        return PassiveVariant.byId(this.getTypeVariant() & 255);
    }

    protected int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    protected void setVariant(PassiveVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }
}