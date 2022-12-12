package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.entity.HerdEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyFollowParentGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyHurtByTargetGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyNearPlayerGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyPanicGoal;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AurochEntity extends HerdEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public AurochEntity(EntityType<? extends HerdEntity> type, Level level) {
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

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, Items.MILK_BUCKET.getDefaultInstance());
            player.setItemInHand(hand, itemstack1);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverLevel, @Nonnull AgeableMob ageableMob) {
        return TolkienEntities.ENTITY_TTM_AUROCH.get().create(serverLevel);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    /** Animation */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
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
//    private static final DataParameter<Integer> AUROCH_TYPE = EntityDataManager.defineId(AurochEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch1.png"));
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch2.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch3.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch4.png"));
//    });
//
//    public AurochEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundEvents.COW_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundEvents.COW_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundEvents.COW_DEATH;
//    }
//
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
//    }
//
//    @Override
//    protected float getSoundVolume()
//    {
//        return 0.4F;
//    }
//
//    @Override
//    public AurochEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
//        return EntityGenerator.ENTITY_TTM_AUROCH.get().create(p_241840_1_);
//    }
//
//    /** Region for determining random skin */
//    public ResourceLocation getAurochTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getAurochType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getAurochType() {
//        return this.entityData.get(AUROCH_TYPE);
//    }
//
//    public void setAurochType(int type) {
//        if (type < 0 || type >= 5) {
//            type = this.random.nextInt(4);
//        }
//
//        this.entityData.set(AUROCH_TYPE, type);
//    }
//
//    @Override
//    @Nullable
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(1, 4);
//        this.setAurochType(job);
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(AUROCH_TYPE, 1);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("AurochType", this.getAurochType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setAurochType(compound.getInt("AurochType"));
//    }
//
//    @Override
//    public IPacket<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
}