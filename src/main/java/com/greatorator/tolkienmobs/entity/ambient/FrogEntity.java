package com.greatorator.tolkienmobs.entity.ambient;

//
//public class FrogEntity extends AmbientEntity {
//    private static final DataParameter<Integer> FROG_TYPE = EntityDataManager.defineId(FrogEntity.class, DataSerializers.INT);
//    private static final ResourceLocation KILLER_FROG = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/murderfrog");
//    private int jumpTicks;
//    private int jumpDuration;
//    private boolean wasOnGround;
//    private int jumpDelayTicks;
//    private int insectTicks;
//
//    public FrogEntity(EntityType<? extends FrogEntity> type, World worldIn) {
//        super(type, worldIn);
//        this.jumpControl = new JumpHelperController(this);
//        this.moveControl = new MoveHelperController(this);
//        this.setSpeedModifier(0.0D);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(1, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new PanicGoal(this, 2.2D));
//        this.goalSelector.addGoal(2, new BreedGoal(this, 0.8D));
//        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(TolkienContent.INSECT.get(), TolkienContent.GOLDEN_INSECT.get()), true));
//        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 2.2D));
//        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
//        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, MonsterEntity.class, 4.0F, 2.2D, 2.2D));
//        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
//        this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
//    }
//
//    @Override
//    protected float getJumpPower() {
//        if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.5D))) {
//            Path path = this.navigation.getPath();
//            if (path != null && !path.isDone()) {
//                Vector3d vector3d = path.getNextEntityPos(this);
//                if (vector3d.y > this.getY() + 0.5D) {
//                    return 0.5F;
//                }
//            }
//
//            return this.moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
//        } else {
//            return 0.5F;
//        }
//    }
//
//    @Override
//    protected void jumpFromGround() {
//        super.jumpFromGround();
//        double d0 = this.moveControl.getSpeedModifier();
//        if (d0 > 0.0D) {
//            double d1 = getHorizontalDistanceSqr(this.getDeltaMovement());
//            if (d1 < 0.01D) {
//                this.moveRelative(0.1F, new Vector3d(0.0D, 0.0D, 1.0D));
//            }
//        }
//
//        if (!this.level.isClientSide) {
//            this.level.broadcastEntityEvent(this, (byte)1);
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public float getJumpCompletion(float p_175521_1_) {
//        return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
//    }
//
//    public void setSpeedModifier(double p_175515_1_) {
//        this.getNavigation().setSpeedModifier(p_175515_1_);
//        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), p_175515_1_);
//    }
//
//    @Override
//    public void setJumping(boolean p_70637_1_) {
//        super.setJumping(p_70637_1_);
//        if (p_70637_1_) {
//            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
//        }
//
//    }
//
//    public void startJumping() {
//        this.setJumping(true);
//        this.jumpDuration = 10;
//        this.jumpTicks = 0;
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(FROG_TYPE, 1);
//    }
//
//    @Override
//    public void customServerAiStep() {
//        if (this.jumpDelayTicks > 0) {
//            --this.jumpDelayTicks;
//        }
//
//        if (this.insectTicks > 0) {
//            this.insectTicks -= this.random.nextInt(3);
//            if (this.insectTicks < 0) {
//                this.insectTicks = 0;
//            }
//        }
//
//        if (this.onGround) {
//            if (!this.wasOnGround) {
//                this.setJumping(false);
//                this.checkLandingDelay();
//            }
//
//            if (this.getFrogType() == 99 && this.jumpDelayTicks == 0) {
//                LivingEntity livingentity = this.getTarget();
//                if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
//                    this.facePoint(livingentity.getX(), livingentity.getZ());
//                    this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
//                    this.startJumping();
//                    this.wasOnGround = true;
//                }
//            }
//
//            FrogEntity.JumpHelperController frogentity$jumphelpercontroller = (FrogEntity.JumpHelperController)this.jumpControl;
//            if (!frogentity$jumphelpercontroller.wantJump()) {
//                if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
//                    Path path = this.navigation.getPath();
//                    Vector3d vector3d = new Vector3d(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
//                    if (path != null && !path.isDone()) {
//                        vector3d = path.getNextEntityPos(this);
//                    }
//
//                    this.facePoint(vector3d.x, vector3d.z);
//                    this.startJumping();
//                }
//            } else if (!frogentity$jumphelpercontroller.canJump()) {
//                this.enableJumpControl();
//            }
//        }
//
//        this.wasOnGround = this.onGround;
//    }
//
//    @Override
//    public boolean canSpawnSprintParticle() {
//        return false;
//    }
//
//    private void facePoint(double p_175533_1_, double p_175533_3_) {
//        this.yRot = (float)(MathHelper.atan2(p_175533_3_ - this.getZ(), p_175533_1_ - this.getX()) * (double)(180F / (float)Math.PI)) - 90.0F;
//    }
//
//    private void enableJumpControl() {
//        ((FrogEntity.JumpHelperController)this.jumpControl).setCanJump(true);
//    }
//
//    private void disableJumpControl() {
//        ((FrogEntity.JumpHelperController)this.jumpControl).setCanJump(false);
//    }
//
//    private void setLandingDelay() {
//        if (this.moveControl.getSpeedModifier() < 2.2D) {
//            this.jumpDelayTicks = 10;
//        } else {
//            this.jumpDelayTicks = 1;
//        }
//
//    }
//
//    private void checkLandingDelay() {
//        this.setLandingDelay();
//        this.disableJumpControl();
//    }
//
//    @Override
//    public void aiStep() {
//        super.aiStep();
//        if (this.jumpTicks != this.jumpDuration) {
//            ++this.jumpTicks;
//        } else if (this.jumpDuration != 0) {
//            this.jumpTicks = 0;
//            this.jumpDuration = 0;
//            this.setJumping(false);
//        }
//
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("FrogType", this.getFrogType());
//        compound.putInt("InsectTicks", this.insectTicks);
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setFrogType(compound.getInt("FrogType"));
//        this.insectTicks = compound.getInt("InsectTicks");
//    }
//
//    protected SoundEvent getJumpSound() {
//        return SoundEvents.RABBIT_JUMP;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundGenerator.soundIdleToaddle.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundGenerator.soundHurtToaddle.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundGenerator.soundDeathToaddle.get();
//    }
//
//    @Override
//    public boolean doHurtTarget(Entity entityIn) {
//        if (this.getFrogType() == 99) {
//            this.playSound(SoundGenerator.soundAngryToaddle.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//            return entityIn.hurt(DamageSource.mobAttack(this), 8.0F);
//        } else {
//            return entityIn.hurt(DamageSource.mobAttack(this), 3.0F);
//        }
//    }
//
//    @Override
//    public SoundCategory getSoundSource() {
//        return this.getFrogType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
//    }
//
//    /**
//     * Called when the entity is attacked.
//     */
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        return this.isInvulnerableTo(source) ? false : super.hurt(source, amount);
//    }
//
//    private boolean isTemptingItem(Item itemIn) {
//        return itemIn == TolkienContent.INSECT.get() || itemIn == TolkienContent.GOLDEN_INSECT.get();
//    }
//
//    @Override
//    public FrogEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
//        FrogEntity frogentity = EntityGenerator.ENTITY_TTM_FROG.get().create(p_241840_1_);
//        int i = this.getRandomFrogType(p_241840_1_);
//        if (this.random.nextInt(20) != 0) {
//            if (p_241840_2_ instanceof FrogEntity && this.random.nextBoolean()) {
//                i = ((FrogEntity) p_241840_2_).getFrogType();
//            } else {
//                i = this.getFrogType();
//            }
//        }
//
//        frogentity.setFrogType(i);
//        return frogentity;
//    }
//
//    @Override
//    public boolean isFood(ItemStack stack) {
//        return this.isTemptingItem(stack.getItem());
//    }
//
//    public int getFrogType() {
//        return this.entityData.get(FROG_TYPE);
//    }
//
//    public void setFrogType(int frogTypeId) {
//        if (frogTypeId == 99) {
//            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
//            this.goalSelector.addGoal(4, new FrogEntity.EvilAttackGoal(this));
//            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
//            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
//            if (!this.hasCustomName()) {
//                this.setCustomName(new TranslationTextComponent(Util.makeDescriptionId("entity", KILLER_FROG)));
//            }
//        }
//        this.entityData.set(FROG_TYPE, frogTypeId);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int i = this.getRandomFrogType(worldIn);
//        if (spawnDataIn instanceof FrogEntity.FrogData) {
//            i = ((FrogEntity.FrogData) spawnDataIn).typeData;
//        } else {
//            spawnDataIn = new FrogEntity.FrogData(i);
//        }
//
//        this.setFrogType(i);
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    private int getRandomFrogType(IWorld p_213610_1_) {
//        Biome biome = p_213610_1_.getBiome(this.blockPosition());
//        int i = this.random.nextInt(100);
//        if (biome.getPrecipitation() == Biome.RainType.SNOW) {
//            return i < 80 ? 1 : 3;
//        } else if (biome.getBiomeCategory() == Biome.Category.DESERT) {
//            return 4;
//        } else {
//            return i < 50 ? 0 : (i < 90 ? 5 : 2);
//        }
//    }
//
//    public static boolean checkFrogSpawnRules(EntityType<FrogEntity> entityIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
//        BlockState blockstate = worldIn.getBlockState(pos.below());
//        int chance = 50; //1 in x
//        return random.nextInt(chance) == 0 && (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.SNOW) || blockstate.is(Blocks.SAND)) && pos.getY() < worldIn.getSeaLevel() + 4 && worldIn.getRawBrightness(pos, 0) > 8;
//    }
//
//    private boolean wantsMoreFood() {
//        return this.insectTicks == 0;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public void handleEntityEvent(byte p_70103_1_) {
//        if (p_70103_1_ == 1) {
//            this.spawnSprintParticle();
//            this.jumpDuration = 10;
//            this.jumpTicks = 0;
//        } else {
//            super.handleEntityEvent(p_70103_1_);
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public Vector3d getLeashOffset() {
//        return new Vector3d(0.0D, (double) (0.6F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
//    }
//
//    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
//        private final FrogEntity ttmfrog;
//
//        public AvoidEntityGoal(FrogEntity ttmfrog, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
//            super(ttmfrog, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
//            this.ttmfrog = ttmfrog;
//        }
//
//        @Override
//        public boolean canUse() {
//            return this.ttmfrog.getFrogType() != 99 && super.canUse();
//        }
//    }
//
//    static class EvilAttackGoal extends MeleeAttackGoal {
//        public EvilAttackGoal(FrogEntity ttmfrog) {
//            super(ttmfrog, 1.4D, true);
//        }
//
//        @Override
//        protected double getAttackReachSqr(LivingEntity attackTarget) {
//            return (double) (4.0F + attackTarget.getBbWidth());
//        }
//    }
//
//    public static class JumpHelperController extends JumpController {
//        private final FrogEntity ttmfrog;
//        private boolean canJump;
//
//        public JumpHelperController(FrogEntity ttmfrog) {
//            super(ttmfrog);
//            this.ttmfrog = ttmfrog;
//        }
//
//        public boolean wantJump() {
//            return this.jump;
//        }
//
//        public boolean canJump() {
//            return this.canJump;
//        }
//
//        public void setCanJump(boolean canJumpIn) {
//            this.canJump = canJumpIn;
//        }
//
//        @Override
//        public void tick() {
//            if (this.jump) {
//                this.ttmfrog.startJumping();
//                this.jump = false;
//            }
//        }
//    }
//
//    static class MoveHelperController extends MovementController {
//        private final FrogEntity ttmfrog;
//        private double nextJumpSpeed;
//
//        public MoveHelperController(FrogEntity ttmfrog) {
//            super(ttmfrog);
//            this.ttmfrog = ttmfrog;
//        }
//
//        @Override
//        public void tick() {
//            if (this.ttmfrog.onGround && !this.ttmfrog.jumping && !((FrogEntity.JumpHelperController) this.ttmfrog.jumpControl).wantJump()) {
//                this.ttmfrog.setSpeedModifier(0.0D);
//            } else if (this.hasWanted()) {
//                this.ttmfrog.setSpeedModifier(this.nextJumpSpeed);
//            }
//            super.tick();
//        }
//
//        @Override
//        public void setWantedPosition(double x, double y, double z, double speedIn) {
//            if (this.ttmfrog.isInWater()) {
//                speedIn = 1.5D;
//            }
//
//            super.setWantedPosition(x, y, z, speedIn);
//            if (speedIn > 0.0D) {
//                this.nextJumpSpeed = speedIn;
//            }
//        }
//    }
//
//    static class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
//        private final FrogEntity ttmfrog;
//
//        public PanicGoal(FrogEntity ttmfrog, double speedIn) {
//            super(ttmfrog, speedIn);
//            this.ttmfrog = ttmfrog;
//        }
//
//        @Override
//        public void tick() {
//            super.tick();
//            this.ttmfrog.setSpeedModifier(this.speedModifier);
//        }
//    }
//
//    public static class FrogData extends AgeableEntity.AgeableData {
//        public final int typeData;
//
//        public FrogData(int type) {
//            super(1.0F);
//            this.typeData = type;
//        }
//    }
//}