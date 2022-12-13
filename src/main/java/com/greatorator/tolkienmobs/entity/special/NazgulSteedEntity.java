package com.greatorator.tolkienmobs.entity.special;

import com.greatorator.tolkienmobs.handler.interfaces.CommonTraits;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class NazgulSteedEntity extends AbstractChestedHorse implements IAnimatable, CommonTraits {
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(NazgulSteedEntity.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory factory = new AnimationFactory(this);

    public NazgulSteedEntity(EntityType<? extends AbstractChestedHorse> entityType, Level level) {
        super(entityType, level);
        this.maxUpStep = 1.0F;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor accessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        if (accessor.getRandom().nextInt(100) == 0) {
            NazgulEntity nazgulEntity = TolkienEntities.ENTITY_TTM_NAZGUL.get().create(this.level);
            nazgulEntity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            nazgulEntity.finalizeSpawn(accessor, instance, type, (SpawnGroupData)null, (CompoundTag)null);
            this.setTamed(true);
            nazgulEntity.startRiding(this);
        }

        return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)1.0F)
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.JUMP_STRENGTH, 2.5D);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setChest(tag.getBoolean("ChestedSteed"));
        this.createInventory();
        if (this.hasChest()) {
            ListTag listtag = tag.getList("Items", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                int j = compoundtag.getByte("Slot") & 255;
                if (j >= 2 && j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, ItemStack.of(compoundtag));
                }
            }
        }
        this.updateContainerEquipment();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("ChestedSteed", this.hasChest());
        if (this.hasChest()) {
            ListTag listtag = new ListTag();

            for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte)i);
                    itemstack.save(compoundtag);
                    listtag.add(compoundtag);
                }
            }
            tag.put("Items", listtag);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_CHEST, false);
    }

    public void setChest(boolean p_30505_) {
        this.entityData.set(DATA_ID_CHEST, p_30505_);
    }

    public boolean hasChest() {
        return this.entityData.get(DATA_ID_CHEST);
    }

    /** Animation */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            double speed = getMovementSpeed(this);
            if (speed > (double)1.0F) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("run", true));
            }else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            }
        }else if (this.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("eat", false).addAnimation("chewing", true));
        }else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                10, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
