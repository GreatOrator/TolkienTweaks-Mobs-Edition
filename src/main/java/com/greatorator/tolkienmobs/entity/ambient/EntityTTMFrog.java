package com.greatorator.tolkienmobs.entity.ambient;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMAmbients;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

//
public class EntityTTMFrog extends EntityTTMAmbients {
    private static final DataParameter<Integer> FROG_TYPE = EntityDataManager.defineId(EntityTTMFrog.class, DataSerializers.INT);
    private static final ResourceLocation KILLER_FROG = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/murderfrog");
    private int jumpTicks;
    private int jumpDuration;
    protected boolean onGround;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private int insectTicks;

    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_green.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_red.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_black.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_rainbow.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_yellow.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_white.png"));
    });

    public EntityTTMFrog(EntityType<? extends EntityTTMFrog> type, World worldIn) {
        super(type, worldIn);
        this.jumpControl = new EntityTTMFrog.JumpHelperController(this);
        this.moveControl = new EntityTTMFrog.MoveHelperController(this);
        this.setMovementSpeed(0.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new EntityTTMFrog.PanicGoal(this, 2.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(TTMContent.INSECT.get(), TTMContent.GOLDEN_INSECT.get()), true));
        this.goalSelector.addGoal(4, new EntityTTMFrog.AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new EntityTTMFrog.AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new EntityTTMFrog.AvoidEntityGoal<>(this, MonsterEntity.class, 4.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    protected float getJumpPower() {
        if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.5D))) {
            Path path = this.navigation.getPath();
            if (path != null && !path.isDone()) {
                Vector3d vector3d = path.getNextEntityPos(this);
                if (vector3d.y > this.getY() + 0.5D) {
                    return 0.5F;
                }
            }

            return this.moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
        } else {
            return 0.5F;
        }
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jumpFromGround() {
        super.jumpFromGround();
        double d0 = this.moveControl.getSpeedModifier();
        if (d0 > 0.0D) {
            double d1 = getHorizontalDistanceSqr(this.getDeltaMovement());
            if (d1 < 0.01D) {
                this.moveRelative(0.1F, new Vector3d(0.0D, 0.0D, 1.0D));
            }
        }

        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 1);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getJumpCompletion(float p_175521_1_) {
        return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + p_175521_1_) / (float) this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed) {
        this.getNavigation().setSpeedModifier(newSpeed);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), newSpeed);
    }

    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }

    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROG_TYPE, 1);
    }

    public void customServerAiStep() {
        if (this.currentMoveTypeDuration > 0) {
            --this.currentMoveTypeDuration;
        }

        if (this.insectTicks > 0) {
            this.insectTicks -= this.random.nextInt(3);
            if (this.insectTicks < 0) {
                this.insectTicks = 0;
            }
        }

        if (this.onGround) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            if (this.getFrogType() == 99 && this.currentMoveTypeDuration == 0) {
                LivingEntity livingentity = this.getTarget();
                if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
                    this.calculateRotationYaw(livingentity.getX(), livingentity.getZ());
                    this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
                    this.startJumping();
                    this.wasOnGround = true;
                }
            }

            EntityTTMFrog.JumpHelperController frogentity$jumphelpercontroller = (EntityTTMFrog.JumpHelperController) this.jumpControl;
            if (!frogentity$jumphelpercontroller.getIsJumping()) {
                if (this.moveControl.hasWanted() && this.currentMoveTypeDuration == 0) {
                    Path path = this.navigation.getPath();
                    Vector3d vector3d = new Vector3d(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
                    if (path != null && !path.isDone()) {
                        vector3d = path.getNextEntityPos(this);
                    }

                    this.calculateRotationYaw(vector3d.x, vector3d.z);
                    this.startJumping();
                }
            } else if (!frogentity$jumphelpercontroller.canJump()) {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    public boolean canSpawnSprintParticle() {
        return false;
    }

    private void calculateRotationYaw(double x, double z) {
        this.yRot = (float) (MathHelper.atan2(z - this.getZ(), x - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F;
    }

    private void enableJumpControl() {
        ((EntityTTMFrog.JumpHelperController) this.jumpControl).setCanJump(true);
    }

    private void disableJumpControl() {
        ((EntityTTMFrog.JumpHelperController) this.jumpControl).setCanJump(false);
    }

    private void updateMoveTypeDuration() {
        if (this.moveControl.getSpeedModifier() < 2.2D) {
            this.currentMoveTypeDuration = 10;
        } else {
            this.currentMoveTypeDuration = 1;
        }

    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void aiStep() {
        super.aiStep();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }

    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.3F);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("FrogType", this.getFrogType());
        compound.putInt("MoreInsectTicks", this.insectTicks);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setFrogType(compound.getInt("FrogType"));
        this.insectTicks = compound.getInt("MoreInsectTicks");
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.RABBIT_JUMP;
    }

    protected SoundEvent getAmbientSound() {
        return SoundGenerator.soundIdleToaddle.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtToaddle.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundDeathToaddle.get();
    }

    public boolean doHurtTarget(Entity entityIn) {
        if (this.getFrogType() == 99) {
            this.playSound(SoundGenerator.soundAngryToaddle.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            return entityIn.hurt(DamageSource.mobAttack(this), 8.0F);
        } else {
            return entityIn.hurt(DamageSource.mobAttack(this), 3.0F);
        }
    }

    public SoundCategory getSoundSource() {
        return this.getFrogType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource source, float amount) {
        return this.isInvulnerableTo(source) ? false : super.hurt(source, amount);
    }

    private boolean isFrogBreedingItem(Item itemIn) {
        return itemIn == TTMContent.INSECT.get() || itemIn == TTMContent.GOLDEN_INSECT.get();
    }

    public EntityTTMFrog getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        EntityTTMFrog frogentity = EntityGenerator.ENTITY_TTM_FROG.get().create(p_241840_1_);
        int i = this.getRandomFrogType(p_241840_1_);
        if (this.random.nextInt(20) != 0) {
            if (p_241840_2_ instanceof EntityTTMFrog && this.random.nextBoolean()) {
                i = ((EntityTTMFrog) p_241840_2_).getFrogType();
            } else {
                i = this.getFrogType();
            }
        }

        frogentity.setFrogType(i);
        return frogentity;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isFood(ItemStack stack) {
        return this.isFrogBreedingItem(stack.getItem());
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getFrogTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getFrogType(), TEXTURE_BY_ID.get(0));
    }

    public int getFrogType() {
        return this.entityData.get(FROG_TYPE);
    }

    public void setFrogType(int frogTypeId) {
        if (frogTypeId == 99) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
            this.goalSelector.addGoal(4, new EntityTTMFrog.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeDescriptionId("entity", KILLER_FROG)));
            }
        }

        this.entityData.set(FROG_TYPE, frogTypeId);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = this.getRandomFrogType(worldIn);
        if (spawnDataIn instanceof EntityTTMFrog.FrogData) {
            i = ((EntityTTMFrog.FrogData) spawnDataIn).typeData;
        } else {
            spawnDataIn = new EntityTTMFrog.FrogData(i);
        }

        this.setFrogType(i);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private int getRandomFrogType(IWorld p_213610_1_) {
        Biome biome = p_213610_1_.getBiome(this.blockPosition());
        int i = this.random.nextInt(100);
        if (biome.getPrecipitation() == Biome.RainType.SNOW) {
            return i < 80 ? 1 : 3;
        } else if (biome.getBiomeCategory() == Biome.Category.DESERT) {
            return 4;
        } else {
            return i < 50 ? 0 : (i < 90 ? 5 : 2);
        }
    }

    public static boolean checkRabbitSpawnRules(EntityType<EntityTTMFrog> p_223321_0_, IWorld p_223321_1_, SpawnReason reason, BlockPos p_223321_3_, Random p_223321_4_) {
        BlockState blockstate = p_223321_1_.getBlockState(p_223321_3_.below());
        return (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.SNOW) || blockstate.is(Blocks.SAND)) && p_223321_1_.getRawBrightness(p_223321_3_, 0) > 8;
    }

    private boolean isInsectEaten() {
        return this.insectTicks == 0;
    }

    public static boolean checkFrogSpawn(EntityType<EntityTTMFrog> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
//        int chance = 2; //1 in 2
        //This if is a little nasty but its more efficient than iterating over a cubic area.
        if (world.isWaterAt(pos.offset(2, 0, 2)) || world.isWaterAt(pos.offset(-2, 0, -2)) || world.isWaterAt(pos.offset(-2, 0, 2)) || world.isWaterAt(pos.offset(2, 0, -2)) || world.isWaterAt(pos.offset(2, -1, 2)) || world.isWaterAt(pos.offset(-2, -1, -2)) || world.isWaterAt(pos.offset(-2, -1, 2)) || world.isWaterAt(pos.offset(2, -1, -2))) {
            return true;//random.nextInt(chance) == 0;// && checkMobSpawnRules(type, world, reason, pos, random);
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 1) {
            this.spawnSprintParticle();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleEntityEvent(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (double) (0.6F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
    }

    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final EntityTTMFrog ttmfrog;

        public AvoidEntityGoal(EntityTTMFrog ttmfrog, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
            super(ttmfrog, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.ttmfrog = ttmfrog;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.ttmfrog.getFrogType() != 99 && super.canUse();
        }
    }

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(EntityTTMFrog ttmfrog) {
            super(ttmfrog, 1.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double) (4.0F + attackTarget.getBbWidth());
        }
    }

    public class JumpHelperController extends JumpController {
        private final EntityTTMFrog ttmfrog;
        private boolean canJump;

        public JumpHelperController(EntityTTMFrog ttmfrog) {
            super(ttmfrog);
            this.ttmfrog = ttmfrog;
        }

        public boolean getIsJumping() {
            return this.jump;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn) {
            this.canJump = canJumpIn;
        }

        /**
         * Called to actually make the entity jump if isJumping is true.
         */
        public void tick() {
            if (this.jump) {
                this.ttmfrog.startJumping();
                this.jump = false;
            }

        }
    }

    static class MoveHelperController extends MovementController {
        private final EntityTTMFrog ttmfrog;
        private double nextJumpSpeed;

        public MoveHelperController(EntityTTMFrog ttmfrog) {
            super(ttmfrog);
            this.ttmfrog = ttmfrog;
        }

        public void tick() {
            if (this.ttmfrog.onGround && !this.ttmfrog.jumping && !((EntityTTMFrog.JumpHelperController) this.ttmfrog.jumpControl).getIsJumping()) {
                this.ttmfrog.setMovementSpeed(0.0D);
            } else if (this.hasWanted()) {
                this.ttmfrog.setMovementSpeed(this.nextJumpSpeed);
            }

            super.tick();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setWantedPosition(double x, double y, double z, double speedIn) {
            if (this.ttmfrog.isInWater()) {
                speedIn = 1.5D;
            }

            super.setWantedPosition(x, y, z, speedIn);
            if (speedIn > 0.0D) {
                this.nextJumpSpeed = speedIn;
            }

        }
    }

    static class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        private final EntityTTMFrog ttmfrog;

        public PanicGoal(EntityTTMFrog ttmfrog, double speedIn) {
            super(ttmfrog, speedIn);
            this.ttmfrog = ttmfrog;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            super.tick();
            this.ttmfrog.setMovementSpeed(this.speedModifier);
        }
    }

    public static class FrogData extends AgeableEntity.AgeableData {
        public final int typeData;

        public FrogData(int type) {
            super(1.0F);
            this.typeData = type;
        }
    }
}