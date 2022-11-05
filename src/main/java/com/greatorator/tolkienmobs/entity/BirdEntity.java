package com.greatorator.tolkienmobs.entity;

//
//public class BirdEntity extends AnimalEntity implements IFlyingAnimal {
//    protected static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(BirdEntity.class, DataSerializers.BYTE);
//    private BirdEntity.AttackPhase attackPhase = BirdEntity.AttackPhase.CIRCLE;
//
//    public float flap;
//    public float flapSpeed;
//    public float oFlapSpeed;
//    public float oFlap;
//    private float flapping = 1.0F;
//    private Vector3d moveTargetPoint = Vector3d.ZERO;
//    private BlockPos anchorPoint = BlockPos.ZERO;
//    private boolean orderedToSit;
//
//    protected BirdEntity(EntityType<? extends AnimalEntity> entityType, World world) {
//        super(entityType, world);
//        this.moveControl = new TTMFlyingMovementController(this, 60, true);
//        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
//        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
//    }
//
//    @Override
//    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
//        return false;
//    }
//
//    @Override
//    protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
//    }
//
//    @Override
//    public float getWalkTargetValue(BlockPos p_180484_1_) {
//        return this.getWalkTargetValue(p_180484_1_, this.level);
//    }
//
//    @Override
//    public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
//        return 0.0F;
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(DATA_FLAGS_ID, (byte)0);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//        this.goalSelector.addGoal(1, new BirdEntity.PickAttackGoal());
//        this.goalSelector.addGoal(2, new TTMSitGoal(this));
//        this.goalSelector.addGoal(2, new TTMWaterAvoidingRandomFlyingGoal(this, 1.0D));
//        this.goalSelector.addGoal(2, new BirdEntity.SweepAttackGoal());
//        this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
//        this.goalSelector.addGoal(3, new BirdEntity.OrbitPointGoal());
//        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(1, new TTMHurtByTargetGoal(this));
//        this.targetSelector.addGoal(1, new BirdEntity.AttackMonsterGoal());
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
//    }
//
//    @Nonnull
//    @Override
//    protected PathNavigator createNavigation(@Nonnull World worldIn) {
//        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
//        flyingpathnavigator.setCanOpenDoors(false);
//        flyingpathnavigator.setCanFloat(true);
//        flyingpathnavigator.setCanPassDoors(true);
//        return flyingpathnavigator;
//    }
//
//    @Override
//    public boolean doHurtTarget(Entity p_70652_1_) {
//        return p_70652_1_.hurt(DamageSource.mobAttack(this), 3.0F);
//    }
//
//    @Override
//    public void aiStep() {
//        super.aiStep();
//        this.calculateFlapping();
//    }
//
//    private void calculateFlapping() {
//        this.oFlap = this.flap;
//        this.oFlapSpeed = this.flapSpeed;
//        this.flapSpeed = (float)((double)this.flapSpeed + (double)(!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
//        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
//        if (!this.onGround && this.flapping < 1.0F) {
//            this.flapping = 1.0F;
//        }
//
//        this.flapping = (float)((double)this.flapping * 0.9D);
//        Vector3d vector3d = this.getDeltaMovement();
//        if (!this.onGround && vector3d.y < 0.0D) {
//            this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
//        }
//
//        this.flap += this.flapping * 2.0F;
//    }
//
//    @Override
//    protected float getVoicePitch() {
//        return getPitch(this.random);
//    }
//
//    public static float getPitch(Random p_192000_0_) {
//        return (p_192000_0_.nextFloat() - p_192000_0_.nextFloat()) * 0.2F + 1.0F;
//    }
//
//    @Override
//    protected float playFlySound(float p_191954_1_) {
//        this.playSound(SoundGenerator.soundFlappingTMGreatEagle.get(), 0.15F, 1.0F);
//        return p_191954_1_ + this.flapSpeed / 2.0F;
//    }
//
//    @Override
//    protected boolean makeFlySound() {
//        return true;
//    }
//
//    public boolean isFlying() {
//        return !this.onGround;
//    }
//
//    public boolean isOrderedToSit() {
//        return this.orderedToSit;
//    }
//
//    public boolean isTame() {
//        return (this.entityData.get(DATA_FLAGS_ID) & 4) != 0;
//    }
//
//    @Nullable
//    public LivingEntity getOwner() {
//        return null;
//    }
//
//    public boolean isInSittingPose() {
//        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
//    }
//
//    public void setInSittingPose(boolean p_233686_1_) {
//        byte b0 = this.entityData.get(DATA_FLAGS_ID);
//        if (p_233686_1_) {
//            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
//        } else {
//            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
//        }
//    }
//
//    // Attack AI region
//    enum AttackPhase {
//        CIRCLE,
//        SWOOP;
//    }
//
//    abstract class MoveGoal extends Goal {
//        public MoveGoal() {
//            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
//        }
//
//        protected boolean touchingTarget() {
//            return BirdEntity.this.moveTargetPoint.distanceToSqr(BirdEntity.this.getX(), BirdEntity.this.getY(), BirdEntity.this.getZ()) < 4.0D;
//        }
//    }
//
//    class OrbitPointGoal extends BirdEntity.MoveGoal {
//        private float angle;
//        private float distance;
//        private float height;
//        private float clockwise;
//
//        private OrbitPointGoal() {
//        }
//
//        @Override
//        public boolean canUse() {
//            return BirdEntity.this.getTarget() == null || BirdEntity.this.attackPhase == BirdEntity.AttackPhase.CIRCLE;
//        }
//
//        @Override
//        public void start() {
//            this.distance = 5.0F + BirdEntity.this.random.nextFloat() * 10.0F;
//            this.height = -4.0F + BirdEntity.this.random.nextFloat() * 9.0F;
//            this.clockwise = BirdEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
//            this.selectNext();
//        }
//
//        @Override
//        public void tick() {
//            if (BirdEntity.this.random.nextInt(350) == 0) {
//                this.height = -4.0F + BirdEntity.this.random.nextFloat() * 9.0F;
//            }
//
//            if (BirdEntity.this.random.nextInt(250) == 0) {
//                ++this.distance;
//                if (this.distance > 15.0F) {
//                    this.distance = 5.0F;
//                    this.clockwise = -this.clockwise;
//                }
//            }
//
//            if (BirdEntity.this.random.nextInt(450) == 0) {
//                this.angle = BirdEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
//                this.selectNext();
//            }
//
//            if (this.touchingTarget()) {
//                this.selectNext();
//            }
//
//            if (BirdEntity.this.moveTargetPoint.y < BirdEntity.this.getY() && !BirdEntity.this.level.isEmptyBlock(BirdEntity.this.blockPosition().below(1))) {
//                this.height = Math.max(1.0F, this.height);
//                this.selectNext();
//            }
//
//            if (BirdEntity.this.moveTargetPoint.y > BirdEntity.this.getY() && !BirdEntity.this.level.isEmptyBlock(BirdEntity.this.blockPosition().above(1))) {
//                this.height = Math.min(-1.0F, this.height);
//                this.selectNext();
//            }
//
//        }
//
//        private void selectNext() {
//            if (BlockPos.ZERO.equals(BirdEntity.this.anchorPoint)) {
//                BirdEntity.this.anchorPoint = BirdEntity.this.blockPosition();
//            }
//
//            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
//            BirdEntity.this.moveTargetPoint = Vector3d.atLowerCornerOf(BirdEntity.this.anchorPoint).add((double)(this.distance * MathHelper.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * MathHelper.sin(this.angle)));
//        }
//    }
//
//    class SweepAttackGoal extends BirdEntity.MoveGoal {
//        private SweepAttackGoal() {
//        }
//
//        @Override
//        public boolean canUse() {
//            return BirdEntity.this.getTarget() != null && BirdEntity.this.attackPhase == BirdEntity.AttackPhase.SWOOP;
//        }
//
//        @Override
//        public boolean canContinueToUse() {
//            LivingEntity livingentity = BirdEntity.this.getTarget();
//            if (livingentity == null) {
//                return false;
//            } else if (!livingentity.isAlive()) {
//                return false;
//            } else if (!(livingentity instanceof PlayerEntity) || !((PlayerEntity)livingentity).isSpectator() && !((PlayerEntity)livingentity).isCreative()) {
//                if (!this.canUse()) {
//                    return false;
//                } else {
//                    if (BirdEntity.this.tickCount % 20 == 0) {
//                        List<CatEntity> list = BirdEntity.this.level.getEntitiesOfClass(CatEntity.class, BirdEntity.this.getBoundingBox().inflate(16.0D), EntityPredicates.ENTITY_STILL_ALIVE);
//                        if (!list.isEmpty()) {
//                            for(CatEntity catentity : list) {
//                                catentity.hiss();
//                            }
//
//                            return false;
//                        }
//                    }
//
//                    return true;
//                }
//            } else {
//                return false;
//            }
//        }
//
//        @Override
//        public void start() {
//        }
//
//        @Override
//        public void stop() {
//            BirdEntity.this.setTarget((LivingEntity)null);
//            BirdEntity.this.attackPhase = BirdEntity.AttackPhase.CIRCLE;
//        }
//
//        @Override
//        public void tick() {
//            LivingEntity livingentity = BirdEntity.this.getTarget();
//            BirdEntity.this.moveTargetPoint = new Vector3d(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
//            if (BirdEntity.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
//                BirdEntity.this.doHurtTarget(livingentity);
//                BirdEntity.this.attackPhase = BirdEntity.AttackPhase.CIRCLE;
//                if (!BirdEntity.this.isSilent()) {
//                    BirdEntity.this.level.levelEvent(1039, BirdEntity.this.blockPosition(), 0);
//                }
//            } else if (BirdEntity.this.horizontalCollision || BirdEntity.this.hurtTime > 0) {
//                BirdEntity.this.attackPhase = BirdEntity.AttackPhase.CIRCLE;
//            }
//        }
//    }
//
//    class PickAttackGoal extends Goal {
//        private int nextSweepTick;
//
//        private PickAttackGoal() {
//        }
//
//        @Override
//        public boolean canUse() {
//            LivingEntity livingentity = BirdEntity.this.getTarget();
//            return livingentity != null ? BirdEntity.this.canAttack(BirdEntity.this.getTarget(), EntityPredicate.DEFAULT) : false;
//        }
//
//        @Override
//        public void start() {
//            this.nextSweepTick = 10;
//            BirdEntity.this.attackPhase = BirdEntity.AttackPhase.CIRCLE;
//            this.setAnchorAboveTarget();
//        }
//
//        @Override
//        public void stop() {
//            BirdEntity.this.anchorPoint = BirdEntity.this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, BirdEntity.this.anchorPoint).above(10 + BirdEntity.this.random.nextInt(20));
//        }
//
//        @Override
//        public void tick() {
//            if (BirdEntity.this.attackPhase == BirdEntity.AttackPhase.CIRCLE) {
//                --this.nextSweepTick;
//                if (this.nextSweepTick <= 0) {
//                    BirdEntity.this.attackPhase = BirdEntity.AttackPhase.SWOOP;
//                    this.setAnchorAboveTarget();
//                    this.nextSweepTick = (8 + BirdEntity.this.random.nextInt(4)) * 20;
//                    BirdEntity.this.playSound(SoundGenerator.soundAttackTMGreatEagle.get(), 10.0F, 0.95F + BirdEntity.this.random.nextFloat() * 0.1F);
//                }
//            }
//        }
//
//        private void setAnchorAboveTarget() {
//            BirdEntity.this.anchorPoint = BirdEntity.this.getTarget().blockPosition().above(20 + BirdEntity.this.random.nextInt(20));
//            if (BirdEntity.this.anchorPoint.getY() < BirdEntity.this.level.getSeaLevel()) {
//                BirdEntity.this.anchorPoint = new BlockPos(BirdEntity.this.anchorPoint.getX(), BirdEntity.this.level.getSeaLevel() + 1, BirdEntity.this.anchorPoint.getZ());
//            }
//        }
//    }
//
//    class AttackMonsterGoal extends Goal {
//        private int nextScanTick = 20;
//
//        private AttackMonsterGoal() {
//        }
//
//        @Override
//        public boolean canUse() {
//            if (this.nextScanTick > 0) {
//                --this.nextScanTick;
//                return false;
//            } else {
//                this.nextScanTick = 60;
//                List<MonsterEntity> list = BirdEntity.this.level.getEntitiesOfClass(MonsterEntity.class, BirdEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D), EntityPredicates.ATTACK_ALLOWED);
//                if (!list.isEmpty()) {
//                    list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());
//
//                    for(MonsterEntity monsterentity : list) {
//                        if (BirdEntity.this.canAttack(monsterentity, EntityPredicate.DEFAULT)) {
//                            BirdEntity.this.setTarget(monsterentity);
//                            return true;
//                        }
//                    }
//                }
//
//                return false;
//            }
//        }
//
//        @Override
//        public boolean canContinueToUse() {
//            LivingEntity livingentity = BirdEntity.this.getTarget();
//            return livingentity != null ? BirdEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
//        }
//    }
//    // End region
//
//    @Nullable
//    @Override
//    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
//        return null;
//    }
//}