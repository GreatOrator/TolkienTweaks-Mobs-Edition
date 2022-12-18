package com.greatorator.tolkienmobs.entity.ambient;

import com.google.common.collect.Sets;
import com.greatorator.tolkienmobs.entity.BirdEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.bird.BirdFollowOwnerGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.bird.BirdSitWhenOrderedToGoal;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings({"removal" })
public class ThrushEntity extends BirdEntity {
    private static final Set<Item> FOOD_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, TolkienItems.PIPEWEED_SEEDS.get());
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(ThrushEntity.class, EntityDataSerializers.BYTE);
    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(ThrushEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private boolean orderedToSit;

    public ThrushEntity(EntityType<? extends BirdEntity> entityType, Level world) {
        super(entityType, world);
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.reassessTameGoals();
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.1875F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BirdSitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(6, new BirdFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public ThrushEntity getBreedOffspring(@Nonnull ServerLevel serverLevel, @Nonnull AgeableMob ageableMob) {
        return TolkienEntities.ENTITY_TTM_THRUSH.get().create(serverLevel);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (!this.isTame() && FOOD_ITEMS.contains(itemstack.getItem())) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.level.isClientSide) {
                if (this.random.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }
            }

            if (!(item instanceof DyeItem)) {
                InteractionResult interactionresult = super.mobInteract(player, hand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                }
                return interactionresult;
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (!this.getFlying() && this.isTame() && this.isOwnedBy(player)) {
            if (!this.level.isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    public boolean isTame() {
        return (this.entityData.get(DATA_FLAGS_ID) & 4) != 0;
    }

    @Nullable
    public LivingEntity getOwner() {
        try {
            UUID uuid = this.getOwnerUUID();
            return uuid == null ? null : this.level.getPlayerByUUID(uuid);
        } catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }

    public boolean isOwnedBy(LivingEntity entity) {
        return entity == this.getOwner();
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse((UUID)null);
    }

    public void setOwnerUUID(@Nullable UUID p_21817_) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(p_21817_));
    }

    public void setTame(boolean p_21836_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_21836_) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 4));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -5));
        }
        this.reassessTameGoals();
    }

    protected void reassessTameGoals() {
    }

    public void tame(Player player) {
        this.setTame(true);
        this.setOwnerUUID(player.getUUID());
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayer)player, this);
        }
    }

    public boolean isInSittingPose() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    @Override
    public boolean isOrderedToSit() {
        return this.orderedToSit;
    }

    public void setOrderedToSit(boolean sitting) {
        this.orderedToSit = sitting;
    }

    public void setInSittingPose(boolean sitting) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (sitting) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.getOwnerUUID() != null) {
            nbt.putUUID("Owner", this.getOwnerUUID());
        }

        nbt.putBoolean("Sitting", this.orderedToSit);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        UUID uuid;
        if (nbt.hasUUID("Owner")) {
            uuid = nbt.getUUID("Owner");
        } else {
            String s = nbt.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }

        if (uuid != null) {
            try {
                this.setOwnerUUID(uuid);
                this.setTame(true);
            } catch (Throwable throwable) {
                this.setTame(false);
            }
        }
        this.orderedToSit = nbt.getBoolean("Sitting");
        this.setInSittingPose(this.orderedToSit);
    }

    /** Sounds */
    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return getAmbient(this.level, this.level.random);
    }

    public static SoundEvent getAmbient(Level level, Random random) {
        return TolkienSounds.soundIdleTMThrush.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TolkienSounds.soundHurtTMThrush.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundDeathTMThrush.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    @Override
    public float getVoicePitch() {
        return getPitch(this.random);
    }

    public static float getPitch(Random random) {
        return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.NEUTRAL;
    }

    /** Spawn */
    public static boolean checkThrushSpawn(EntityType<ThrushEntity> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(entityType, accessor, spawnType, pos, random);
    }
}