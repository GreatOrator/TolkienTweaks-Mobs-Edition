package com.greatorator.tolkienmobs.entity.boss.fellbeast;

//
//public class FellBeastEntity extends MobEntity implements IMob {
//    private static final Logger LOGGER = LogManager.getLogger();
//    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
//    public static final DataParameter<Integer> DATA_PHASE = EntityDataManager.defineId(FellBeastEntity.class, DataSerializers.INT);
//    private static final EntityPredicate CRYSTAL_DESTROY_TARGETING = (new EntityPredicate()).range(64.0D);
//    public final double[][] positions = new double[64][3];
//    public int posPointer = -1;
//    private final FellBeastPartEntity[] subEntities;
//    public final FellBeastPartEntity head;
//    private final FellBeastPartEntity neck;
//    private final FellBeastPartEntity body;
//    private final FellBeastPartEntity tail1;
//    private final FellBeastPartEntity tail2;
//    private final FellBeastPartEntity tail3;
//    private final FellBeastPartEntity wing1;
//    private final FellBeastPartEntity wing2;
//    public float oFlapTime;
//    public float flapTime;
//    public boolean inWall;
//    public int fellbeastDeathTime;
//    public float yRotA;
//    @Nullable
//    public MorgulCrystalEntity nearestCrystal;
//    @Nullable
//    private final FellBeastFightManager fellbeastFight;
//    private final FellBeastPhaseManager phaseManager;
//    private int growlTime = 100;
//    private int sittingDamageReceived;
//    private final PathPoint[] nodes = new PathPoint[24];
//    private final int[] nodeAdjacency = new int[24];
//    private final PathHeap openSet = new PathHeap();
//
//    public FellBeastEntity(EntityType<? extends FellBeastEntity> p_i50230_1_, World world) {
//        super(EntityGenerator.ENTITY_FELL_BEAST.get(), world);
//        this.head = new FellBeastPartEntity(this, "head", 1.0F, 1.0F);
//        this.neck = new FellBeastPartEntity(this, "neck", 3.0F, 3.0F);
//        this.body = new FellBeastPartEntity(this, "body", 5.0F, 3.0F);
//        this.tail1 = new FellBeastPartEntity(this, "tail", 2.0F, 2.0F);
//        this.tail2 = new FellBeastPartEntity(this, "tail", 2.0F, 2.0F);
//        this.tail3 = new FellBeastPartEntity(this, "tail", 2.0F, 2.0F);
//        this.wing1 = new FellBeastPartEntity(this, "wing", 4.0F, 2.0F);
//        this.wing2 = new FellBeastPartEntity(this, "wing", 4.0F, 2.0F);
//        this.subEntities = new FellBeastPartEntity[]{this.head, this.neck, this.body, this.tail1, this.tail2, this.tail3, this.wing1, this.wing2};
//        this.setHealth(this.getMaxHealth());
//        this.noPhysics = true;
//        this.noCulling = true;
//        if (world instanceof ServerWorld) {
//            this.fellbeastFight = ((TolkienServerLevel)world).fellbeastFight();
//        } else {
//            this.fellbeastFight = null;
//        }
//
//        this.phaseManager = new FellBeastPhaseManager(this);
//    }
//
//    @Override
//    public double getPassengersRidingOffset()
//    {
//        return super.getPassengersRidingOffset() - 3.5D;
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//
//        WitchKingEntity entitywitchking = EntityGenerator.ENTITY_TTM_WITCHKING.get().create(this.level);
//        entitywitchking.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, 0.0F);
//        entitywitchking.finalizeSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT)null);
//        entitywitchking.startRiding(this);
//
//        return spawnDataIn;
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 200.0D);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.getEntityData().define(DATA_PHASE, FellBeastPhaseType.HOVERING.getId());
//    }
//
//    public double[] getLatencyPos(int p_70974_1_, float p_70974_2_) {
//        if (this.isDeadOrDying()) {
//            p_70974_2_ = 0.0F;
//        }
//
//        p_70974_2_ = 1.0F - p_70974_2_;
//        int i = this.posPointer - p_70974_1_ & 63;
//        int j = this.posPointer - p_70974_1_ - 1 & 63;
//        double[] adouble = new double[3];
//        double d0 = this.positions[i][0];
//        double d1 = MathHelper.wrapDegrees(this.positions[j][0] - d0);
//        adouble[0] = d0 + d1 * (double)p_70974_2_;
//        d0 = this.positions[i][1];
//        d1 = this.positions[j][1] - d0;
//        adouble[1] = d0 + d1 * (double)p_70974_2_;
//        adouble[2] = MathHelper.lerp((double)p_70974_2_, this.positions[i][2], this.positions[j][2]);
//        return adouble;
//    }
//
//    @Override
//    public void aiStep() {
//        if (this.level.isClientSide) {
//            this.setHealth(this.getHealth());
//            if (!this.isSilent()) {
//                float f = MathHelper.cos(this.flapTime * ((float)Math.PI * 2F));
//                float f1 = MathHelper.cos(this.oFlapTime * ((float)Math.PI * 2F));
//                if (f1 <= -0.3F && f >= -0.3F) {
//                    this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundGenerator.soundFlapFellBeast.get(), this.getSoundSource(), 5.0F, 0.8F + this.random.nextFloat() * 0.3F, false);
//                }
//
//                if (!this.phaseManager.getCurrentPhase().isSitting() && --this.growlTime < 0) {
//                    this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundGenerator.soundIdleFellBeast.get(), this.getSoundSource(), 2.5F, 0.8F + this.random.nextFloat() * 0.3F, false);
//                    this.growlTime = 200 + this.random.nextInt(200);
//                }
//            }
//        }
//
//        this.oFlapTime = this.flapTime;
//        if (this.isDeadOrDying()) {
//            float f11 = (this.random.nextFloat() - 0.5F) * 8.0F;
//            float f13 = (this.random.nextFloat() - 0.5F) * 4.0F;
//            float f14 = (this.random.nextFloat() - 0.5F) * 8.0F;
//            this.level.addParticle(ParticleTypes.EXPLOSION, this.getX() + (double)f11, this.getY() + 2.0D + (double)f13, this.getZ() + (double)f14, 0.0D, 0.0D, 0.0D);
//        } else {
//            this.checkCrystals();
//            Vector3d vector3d4 = this.getDeltaMovement();
//            float f12 = 0.2F / (MathHelper.sqrt(getHorizontalDistanceSqr(vector3d4)) * 10.0F + 1.0F);
//            f12 = f12 * (float)Math.pow(2.0D, vector3d4.y);
//            if (this.phaseManager.getCurrentPhase().isSitting()) {
//                this.flapTime += 0.1F;
//            } else if (this.inWall) {
//                this.flapTime += f12 * 0.5F;
//            } else {
//                this.flapTime += f12;
//            }
//
//            this.yRot = MathHelper.wrapDegrees(this.yRot);
//            if (this.isNoAi()) {
//                this.flapTime = 0.5F;
//            } else {
//                if (this.posPointer < 0) {
//                    for(int i = 0; i < this.positions.length; ++i) {
//                        this.positions[i][0] = (double)this.yRot;
//                        this.positions[i][1] = this.getY();
//                    }
//                }
//
//                if (++this.posPointer == this.positions.length) {
//                    this.posPointer = 0;
//                }
//
//                this.positions[this.posPointer][0] = (double)this.yRot;
//                this.positions[this.posPointer][1] = this.getY();
//                if (this.level.isClientSide) {
//                    if (this.lerpSteps > 0) {
//                        double d7 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
//                        double d0 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
//                        double d1 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
//                        double d2 = MathHelper.wrapDegrees(this.lerpYRot - (double)this.yRot);
//                        this.yRot = (float)((double)this.yRot + d2 / (double)this.lerpSteps);
//                        this.xRot = (float)((double)this.xRot + (this.lerpXRot - (double)this.xRot) / (double)this.lerpSteps);
//                        --this.lerpSteps;
//                        this.setPos(d7, d0, d1);
//                        this.setRot(this.yRot, this.xRot);
//                    }
//
//                    this.phaseManager.getCurrentPhase().doClientTick();
//                } else {
//                    IFellBeastPhase iphase = this.phaseManager.getCurrentPhase();
//                    iphase.doServerTick();
//                    if (this.phaseManager.getCurrentPhase() != iphase) {
//                        iphase = this.phaseManager.getCurrentPhase();
//                        iphase.doServerTick();
//                    }
//
//                    Vector3d vector3d = iphase.getFlyTargetLocation();
//                    if (vector3d != null) {
//                        double d8 = vector3d.x - this.getX();
//                        double d9 = vector3d.y - this.getY();
//                        double d10 = vector3d.z - this.getZ();
//                        double d3 = d8 * d8 + d9 * d9 + d10 * d10;
//                        float f6 = iphase.getFlySpeed();
//                        double d4 = (double)MathHelper.sqrt(d8 * d8 + d10 * d10);
//                        if (d4 > 0.0D) {
//                            d9 = MathHelper.clamp(d9 / d4, (double)(-f6), (double)f6);
//                        }
//
//                        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, d9 * 0.01D, 0.0D));
//                        this.yRot = MathHelper.wrapDegrees(this.yRot);
//                        double d5 = MathHelper.clamp(MathHelper.wrapDegrees(180.0D - MathHelper.atan2(d8, d10) * (double)(180F / (float)Math.PI) - (double)this.yRot), -50.0D, 50.0D);
//                        Vector3d vector3d1 = vector3d.subtract(this.getX(), this.getY(), this.getZ()).normalize();
//                        Vector3d vector3d2 = (new Vector3d((double)MathHelper.sin(this.yRot * ((float)Math.PI / 180F)), this.getDeltaMovement().y, (double)(-MathHelper.cos(this.yRot * ((float)Math.PI / 180F))))).normalize();
//                        float f8 = Math.max(((float)vector3d2.dot(vector3d1) + 0.5F) / 1.5F, 0.0F);
//                        this.yRotA *= 0.8F;
//                        this.yRotA = (float)((double)this.yRotA + d5 * (double)iphase.getTurnSpeed());
//                        this.yRot += this.yRotA * 0.1F;
//                        float f9 = (float)(2.0D / (d3 + 1.0D));
//                        float f10 = 0.06F;
//                        this.moveRelative(0.06F * (f8 * f9 + (1.0F - f9)), new Vector3d(0.0D, 0.0D, -1.0D));
//                        if (this.inWall) {
//                            this.move(MoverType.SELF, this.getDeltaMovement().scale((double)0.8F));
//                        } else {
//                            this.move(MoverType.SELF, this.getDeltaMovement());
//                        }
//
//                        Vector3d vector3d3 = this.getDeltaMovement().normalize();
//                        double d6 = 0.8D + 0.15D * (vector3d3.dot(vector3d2) + 1.0D) / 2.0D;
//                        this.setDeltaMovement(this.getDeltaMovement().multiply(d6, (double)0.91F, d6));
//                    }
//                }
//
//                this.yBodyRot = this.yRot;
//                Vector3d[] avector3d = new Vector3d[this.subEntities.length];
//
//                for(int j = 0; j < this.subEntities.length; ++j) {
//                    avector3d[j] = new Vector3d(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
//                }
//
//                float f15 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * ((float)Math.PI / 180F);
//                float f16 = MathHelper.cos(f15);
//                float f2 = MathHelper.sin(f15);
//                float f17 = this.yRot * ((float)Math.PI / 180F);
//                float f3 = MathHelper.sin(f17);
//                float f18 = MathHelper.cos(f17);
//                this.tickPart(this.body, (double)(f3 * 0.5F), 0.0D, (double)(-f18 * 0.5F));
//                this.tickPart(this.wing1, (double)(f18 * 4.5F), 2.0D, (double)(f3 * 4.5F));
//                this.tickPart(this.wing2, (double)(f18 * -4.5F), 2.0D, (double)(f3 * -4.5F));
//                if (!this.level.isClientSide && this.hurtTime == 0) {
//                    this.knockBack(this.level.getEntities(this, this.wing1.getBoundingBox().inflate(4.0D, 2.0D, 4.0D).move(0.0D, -2.0D, 0.0D), EntityPredicates.NO_CREATIVE_OR_SPECTATOR));
//                    this.knockBack(this.level.getEntities(this, this.wing2.getBoundingBox().inflate(4.0D, 2.0D, 4.0D).move(0.0D, -2.0D, 0.0D), EntityPredicates.NO_CREATIVE_OR_SPECTATOR));
//                    this.hurt(this.level.getEntities(this, this.head.getBoundingBox().inflate(1.0D), EntityPredicates.NO_CREATIVE_OR_SPECTATOR));
//                    this.hurt(this.level.getEntities(this, this.neck.getBoundingBox().inflate(1.0D), EntityPredicates.NO_CREATIVE_OR_SPECTATOR));
//                }
//
//                float f4 = MathHelper.sin(this.yRot * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
//                float f19 = MathHelper.cos(this.yRot * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
//                float f5 = this.getHeadYOffset();
//                this.tickPart(this.head, (double)(f4 * 6.5F * f16), (double)(f5 + f2 * 6.5F), (double)(-f19 * 6.5F * f16));
//                this.tickPart(this.neck, (double)(f4 * 5.5F * f16), (double)(f5 + f2 * 5.5F), (double)(-f19 * 5.5F * f16));
//                double[] adouble = this.getLatencyPos(5, 1.0F);
//
//                for(int k = 0; k < 3; ++k) {
//                    FellBeastPartEntity fellbeastpartentity = null;
//                    if (k == 0) {
//                        fellbeastpartentity = this.tail1;
//                    }
//
//                    if (k == 1) {
//                        fellbeastpartentity = this.tail2;
//                    }
//
//                    if (k == 2) {
//                        fellbeastpartentity = this.tail3;
//                    }
//
//                    double[] adouble1 = this.getLatencyPos(12 + k * 2, 1.0F);
//                    float f7 = this.yRot * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
//                    float f20 = MathHelper.sin(f7);
//                    float f21 = MathHelper.cos(f7);
//                    float f22 = 1.5F;
//                    float f23 = (float)(k + 1) * 2.0F;
//                    this.tickPart(fellbeastpartentity, (double)(-(f3 * 1.5F + f20 * f23) * f16), adouble1[1] - adouble[1] - (double)((f23 + 1.5F) * f2) + 1.5D, (double)((f18 * 1.5F + f21 * f23) * f16));
//                }
//
//                if (!this.level.isClientSide) {
//                    this.inWall = this.checkWalls(this.head.getBoundingBox()) | this.checkWalls(this.neck.getBoundingBox()) | this.checkWalls(this.body.getBoundingBox());
//                    if (this.fellbeastFight != null) {
//                        this.fellbeastFight.updateFellBeast(this);
//                    }
//                }
//
//                for(int l = 0; l < this.subEntities.length; ++l) {
//                    this.subEntities[l].xo = avector3d[l].x;
//                    this.subEntities[l].yo = avector3d[l].y;
//                    this.subEntities[l].zo = avector3d[l].z;
//                    this.subEntities[l].xOld = avector3d[l].x;
//                    this.subEntities[l].yOld = avector3d[l].y;
//                    this.subEntities[l].zOld = avector3d[l].z;
//                }
//
//            }
//        }
//    }
//
//    private void tickPart(FellBeastPartEntity p_226526_1_, double p_226526_2_, double p_226526_4_, double p_226526_6_) {
//        p_226526_1_.setPos(this.getX() + p_226526_2_, this.getY() + p_226526_4_, this.getZ() + p_226526_6_);
//    }
//
//    private float getHeadYOffset() {
//        if (this.phaseManager.getCurrentPhase().isSitting()) {
//            return -1.0F;
//        } else {
//            double[] adouble = this.getLatencyPos(5, 1.0F);
//            double[] adouble1 = this.getLatencyPos(0, 1.0F);
//            return (float)(adouble[1] - adouble1[1]);
//        }
//    }
//
//    private void checkCrystals() {
//        if (this.nearestCrystal != null) {
//            if (this.nearestCrystal.removed) {
//                this.nearestCrystal = null;
//            } else if (this.tickCount % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
//                this.setHealth(this.getHealth() + 1.0F);
//            }
//        }
//
//        if (this.random.nextInt(10) == 0) {
//            List<MorgulCrystalEntity> list = this.level.getEntitiesOfClass(MorgulCrystalEntity.class, this.getBoundingBox().inflate(32.0D));
//            MorgulCrystalEntity morgulCrystalEntity = null;
//            double d0 = Double.MAX_VALUE;
//
//            for(MorgulCrystalEntity endercrystalentity1 : list) {
//                double d1 = endercrystalentity1.distanceToSqr(this);
//                if (d1 < d0) {
//                    d0 = d1;
//                    morgulCrystalEntity = endercrystalentity1;
//                }
//            }
//
//            this.nearestCrystal = morgulCrystalEntity;
//        }
//
//    }
//
//    private void knockBack(List<Entity> p_70970_1_) {
//        double d0 = (this.body.getBoundingBox().minX + this.body.getBoundingBox().maxX) / 2.0D;
//        double d1 = (this.body.getBoundingBox().minZ + this.body.getBoundingBox().maxZ) / 2.0D;
//
//        for(Entity entity : p_70970_1_) {
//            if (entity instanceof LivingEntity) {
//                double d2 = entity.getX() - d0;
//                double d3 = entity.getZ() - d1;
//                double d4 = Math.max(d2 * d2 + d3 * d3, 0.1D);
//                entity.push(d2 / d4 * 4.0D, (double)0.2F, d3 / d4 * 4.0D);
//                if (!this.phaseManager.getCurrentPhase().isSitting() && ((LivingEntity)entity).getLastHurtByMobTimestamp() < entity.tickCount - 2) {
//                    entity.hurt(DamageSource.mobAttack(this), 5.0F);
//                    this.doEnchantDamageEffects(this, entity);
//                }
//            }
//        }
//
//    }
//
//    private void hurt(List<Entity> p_70971_1_) {
//        for(Entity entity : p_70971_1_) {
//            if (entity instanceof LivingEntity) {
//                entity.hurt(DamageSource.mobAttack(this), 10.0F);
//                this.doEnchantDamageEffects(this, entity);
//            }
//        }
//
//    }
//
//    private float rotWrap(double p_70973_1_) {
//        return (float)MathHelper.wrapDegrees(p_70973_1_);
//    }
//
//    private boolean checkWalls(AxisAlignedBB p_70972_1_) {
//        int i = MathHelper.floor(p_70972_1_.minX);
//        int j = MathHelper.floor(p_70972_1_.minY);
//        int k = MathHelper.floor(p_70972_1_.minZ);
//        int l = MathHelper.floor(p_70972_1_.maxX);
//        int i1 = MathHelper.floor(p_70972_1_.maxY);
//        int j1 = MathHelper.floor(p_70972_1_.maxZ);
//        boolean flag = false;
//        boolean flag1 = false;
//
////        for(int k1 = i; k1 <= l; ++k1) {
////            for(int l1 = j; l1 <= i1; ++l1) {
////                for(int i2 = k; i2 <= j1; ++i2) {
////                    BlockPos blockpos = new BlockPos(k1, l1, i2);
////                    BlockState blockstate = this.level.getBlockState(blockpos);
////                    Block block = blockstate.getBlock();
////                    if (!blockstate.isAir(this.level, blockpos) && blockstate.getMaterial() != Material.FIRE) {
////                        if (net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.level, blockpos, this) && !BlockTags.DRAGON_IMMUNE.contains(block)) {
////                            flag1 = this.level.removeBlock(blockpos, false) || flag1;
////                        } else {
////                            flag = true;
////                        }
////                    }
////                }
////            }
////        }
//
//        if (flag1) {
//            BlockPos blockpos1 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
//            this.level.levelEvent(2008, blockpos1, 0);
//        }
//
//        return flag;
//    }
//
//    public boolean hurt(FellBeastPartEntity p_213403_1_, DamageSource p_213403_2_, float p_213403_3_) {
//        if (this.phaseManager.getCurrentPhase().getPhase() == FellBeastPhaseType.DYING) {
//            return false;
//        } else {
//            p_213403_3_ = this.phaseManager.getCurrentPhase().onHurt(p_213403_2_, p_213403_3_);
//            if (p_213403_1_ != this.head) {
//                p_213403_3_ = p_213403_3_ / 4.0F + Math.min(p_213403_3_, 1.0F);
//            }
//
//            if (p_213403_3_ < 0.01F) {
//                return false;
//            } else {
//                if (p_213403_2_.getEntity() instanceof PlayerEntity || p_213403_2_.isExplosion()) {
//                    float f = this.getHealth();
//                    this.reallyHurt(p_213403_2_, p_213403_3_);
//                    if (this.isDeadOrDying() && !this.phaseManager.getCurrentPhase().isSitting()) {
//                        this.setHealth(1.0F);
//                        this.phaseManager.setPhase(FellBeastPhaseType.DYING);
//                    }
//
//                    if (this.phaseManager.getCurrentPhase().isSitting()) {
//                        this.sittingDamageReceived = (int)((float)this.sittingDamageReceived + (f - this.getHealth()));
//                        if ((float)this.sittingDamageReceived > 0.25F * this.getMaxHealth()) {
//                            this.sittingDamageReceived = 0;
//                            this.phaseManager.setPhase(FellBeastPhaseType.TAKEOFF);
//                        }
//                    }
//                }
//
//                return true;
//            }
//        }
//    }
//
//    @Override
//    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
//        if (p_70097_1_ instanceof EntityDamageSource && ((EntityDamageSource)p_70097_1_).isThorns()) {
//            this.hurt(this.body, p_70097_1_, p_70097_2_);
//        }
//
//        return false;
//    }
//
//    protected boolean reallyHurt(DamageSource p_82195_1_, float p_82195_2_) {
//        return super.hurt(p_82195_1_, p_82195_2_);
//    }
//
//    @Override
//    public void kill() {
//        this.remove();
//        if (this.fellbeastFight != null) {
//            this.fellbeastFight.updateFellBeast(this);
//            this.fellbeastFight.setFellBeastKilled(this);
//        }
//
//    }
//
//    @Override
//    protected void tickDeath() {
//        if (this.fellbeastFight != null) {
//            this.fellbeastFight.updateFellBeast(this);
//        }
//
//        ++this.fellbeastDeathTime;
//        if (this.fellbeastDeathTime >= 180 && this.fellbeastDeathTime <= 200) {
//            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
//            float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
//            float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
//            this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + 2.0D + (double)f1, this.getZ() + (double)f2, 0.0D, 0.0D, 0.0D);
//        }
//
//        boolean flag = this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
//        int i = 500;
//        if (this.fellbeastFight != null && !this.fellbeastFight.hasPreviouslyKilledFellBeast()) {
//            i = 12000;
//        }
//
//        if (!this.level.isClientSide) {
//            if (this.fellbeastDeathTime > 150 && this.fellbeastDeathTime % 5 == 0 && flag) {
//                this.dropExperience(MathHelper.floor((float)i * 0.08F));
//            }
//
//            if (this.fellbeastDeathTime == 1 && !this.isSilent()) {
//                this.level.globalLevelEvent(1028, this.blockPosition(), 0);
//            }
//        }
//
//        this.move(MoverType.SELF, new Vector3d(0.0D, (double)0.1F, 0.0D));
//        this.yRot += 20.0F;
//        this.yBodyRot = this.yRot;
//        if (this.fellbeastDeathTime == 200 && !this.level.isClientSide) {
//            if (flag) {
//                this.dropExperience(MathHelper.floor((float)i * 0.2F));
//            }
//
//            if (this.fellbeastFight != null) {
//                this.fellbeastFight.setFellBeastKilled(this);
//            }
//
//            this.remove();
//        }
//
//    }
//
//    private void dropExperience(int p_184668_1_) {
//        while(p_184668_1_ > 0) {
//            int i = ExperienceOrbEntity.getExperienceValue(p_184668_1_);
//            p_184668_1_ -= i;
//            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY(), this.getZ(), i));
//        }
//
//    }
//
//    public int findClosestNode() {
//        if (this.nodes[0] == null) {
//            for(int i = 0; i < 24; ++i) {
//                int j = 5;
//                int l;
//                int i1;
//                if (i < 12) {
//                    l = MathHelper.floor(60.0F * MathHelper.cos(2.0F * (-(float)Math.PI + 0.2617994F * (float)i)));
//                    i1 = MathHelper.floor(60.0F * MathHelper.sin(2.0F * (-(float)Math.PI + 0.2617994F * (float)i)));
//                } else if (i < 20) {
//                    int lvt_3_1_ = i - 12;
//                    l = MathHelper.floor(40.0F * MathHelper.cos(2.0F * (-(float)Math.PI + ((float)Math.PI / 8F) * (float)lvt_3_1_)));
//                    i1 = MathHelper.floor(40.0F * MathHelper.sin(2.0F * (-(float)Math.PI + ((float)Math.PI / 8F) * (float)lvt_3_1_)));
//                    j += 10;
//                } else {
//                    int k1 = i - 20;
//                    l = MathHelper.floor(20.0F * MathHelper.cos(2.0F * (-(float)Math.PI + ((float)Math.PI / 4F) * (float)k1)));
//                    i1 = MathHelper.floor(20.0F * MathHelper.sin(2.0F * (-(float)Math.PI + ((float)Math.PI / 4F) * (float)k1)));
//                }
//
//                int j1 = Math.max(this.level.getSeaLevel() + 10, this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(l, 0, i1)).getY() + j);
//                this.nodes[i] = new PathPoint(l, j1, i1);
//            }
//
//            this.nodeAdjacency[0] = 6146;
//            this.nodeAdjacency[1] = 8197;
//            this.nodeAdjacency[2] = 8202;
//            this.nodeAdjacency[3] = 16404;
//            this.nodeAdjacency[4] = 32808;
//            this.nodeAdjacency[5] = 32848;
//            this.nodeAdjacency[6] = 65696;
//            this.nodeAdjacency[7] = 131392;
//            this.nodeAdjacency[8] = 131712;
//            this.nodeAdjacency[9] = 263424;
//            this.nodeAdjacency[10] = 526848;
//            this.nodeAdjacency[11] = 525313;
//            this.nodeAdjacency[12] = 1581057;
//            this.nodeAdjacency[13] = 3166214;
//            this.nodeAdjacency[14] = 2138120;
//            this.nodeAdjacency[15] = 6373424;
//            this.nodeAdjacency[16] = 4358208;
//            this.nodeAdjacency[17] = 12910976;
//            this.nodeAdjacency[18] = 9044480;
//            this.nodeAdjacency[19] = 9706496;
//            this.nodeAdjacency[20] = 15216640;
//            this.nodeAdjacency[21] = 13688832;
//            this.nodeAdjacency[22] = 11763712;
//            this.nodeAdjacency[23] = 8257536;
//        }
//
//        return this.findClosestNode(this.getX(), this.getY(), this.getZ());
//    }
//
//    public int findClosestNode(double p_184663_1_, double p_184663_3_, double p_184663_5_) {
//        float f = 10000.0F;
//        int i = 0;
//        PathPoint pathpoint = new PathPoint(MathHelper.floor(p_184663_1_), MathHelper.floor(p_184663_3_), MathHelper.floor(p_184663_5_));
//        int j = 0;
//        if (this.fellbeastFight == null || this.fellbeastFight.getCrystalsAlive() == 0) {
//            j = 12;
//        }
//
//        for(int k = j; k < 24; ++k) {
//            if (this.nodes[k] != null) {
//                float f1 = this.nodes[k].distanceToSqr(pathpoint);
//                if (f1 < f) {
//                    f = f1;
//                    i = k;
//                }
//            }
//        }
//
//        return i;
//    }
//
//    @Nullable
//    public Path findPath(int p_184666_1_, int p_184666_2_, @Nullable PathPoint p_184666_3_) {
//        for(int i = 0; i < 24; ++i) {
//            PathPoint pathpoint = this.nodes[i];
//            pathpoint.closed = false;
//            pathpoint.f = 0.0F;
//            pathpoint.g = 0.0F;
//            pathpoint.h = 0.0F;
//            pathpoint.cameFrom = null;
//            pathpoint.heapIdx = -1;
//        }
//
//        PathPoint pathpoint4 = this.nodes[p_184666_1_];
//        PathPoint pathpoint5 = this.nodes[p_184666_2_];
//        pathpoint4.g = 0.0F;
//        pathpoint4.h = pathpoint4.distanceTo(pathpoint5);
//        pathpoint4.f = pathpoint4.h;
//        this.openSet.clear();
//        this.openSet.insert(pathpoint4);
//        PathPoint pathpoint1 = pathpoint4;
//        int j = 0;
//        if (this.fellbeastFight == null || this.fellbeastFight.getCrystalsAlive() == 0) {
//            j = 12;
//        }
//
//        while(!this.openSet.isEmpty()) {
//            PathPoint pathpoint2 = this.openSet.pop();
//            if (pathpoint2.equals(pathpoint5)) {
//                if (p_184666_3_ != null) {
//                    p_184666_3_.cameFrom = pathpoint5;
//                    pathpoint5 = p_184666_3_;
//                }
//
//                return this.reconstructPath(pathpoint4, pathpoint5);
//            }
//
//            if (pathpoint2.distanceTo(pathpoint5) < pathpoint1.distanceTo(pathpoint5)) {
//                pathpoint1 = pathpoint2;
//            }
//
//            pathpoint2.closed = true;
//            int k = 0;
//
//            for(int l = 0; l < 24; ++l) {
//                if (this.nodes[l] == pathpoint2) {
//                    k = l;
//                    break;
//                }
//            }
//
//            for(int i1 = j; i1 < 24; ++i1) {
//                if ((this.nodeAdjacency[k] & 1 << i1) > 0) {
//                    PathPoint pathpoint3 = this.nodes[i1];
//                    if (!pathpoint3.closed) {
//                        float f = pathpoint2.g + pathpoint2.distanceTo(pathpoint3);
//                        if (!pathpoint3.inOpenSet() || f < pathpoint3.g) {
//                            pathpoint3.cameFrom = pathpoint2;
//                            pathpoint3.g = f;
//                            pathpoint3.h = pathpoint3.distanceTo(pathpoint5);
//                            if (pathpoint3.inOpenSet()) {
//                                this.openSet.changeCost(pathpoint3, pathpoint3.g + pathpoint3.h);
//                            } else {
//                                pathpoint3.f = pathpoint3.g + pathpoint3.h;
//                                this.openSet.insert(pathpoint3);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (pathpoint1 == pathpoint4) {
//            return null;
//        } else {
//            LOGGER.debug("Failed to find path from {} to {}", p_184666_1_, p_184666_2_);
//            if (p_184666_3_ != null) {
//                p_184666_3_.cameFrom = pathpoint1;
//                pathpoint1 = p_184666_3_;
//            }
//
//            return this.reconstructPath(pathpoint4, pathpoint1);
//        }
//    }
//
//    private Path reconstructPath(PathPoint p_184669_1_, PathPoint p_184669_2_) {
//        List<PathPoint> list = Lists.newArrayList();
//        PathPoint pathpoint = p_184669_2_;
//        list.add(0, p_184669_2_);
//
//        while(pathpoint.cameFrom != null) {
//            pathpoint = pathpoint.cameFrom;
//            list.add(0, pathpoint);
//        }
//
//        return new Path(list, new BlockPos(p_184669_2_.x, p_184669_2_.y, p_184669_2_.z), true);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compoundNBT) {
//        super.addAdditionalSaveData(compoundNBT);
//        compoundNBT.putInt("FellBeastPhase", this.phaseManager.getCurrentPhase().getPhase().getId());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compoundNBT) {
//        super.readAdditionalSaveData(compoundNBT);
//        if (this.hasCustomName()) {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//
//        if (compoundNBT.contains("FellBeastPhase")) {
//            this.phaseManager.setPhase(FellBeastPhaseType.getById(compoundNBT.getInt("FellBeastPhase")));
//        }
//
//    }
//
//    @Override
//    public void setCustomName(@Nullable ITextComponent name) {
//        super.setCustomName(name);
//        this.bossInfo.setName(this.getDisplayName());
//    }
//
//    @Override
//    public void startSeenByPlayer(ServerPlayerEntity player) {
//        super.startSeenByPlayer(player);
//        this.bossInfo.addPlayer(player);
//    }
//
//    @Override
//    public void stopSeenByPlayer(ServerPlayerEntity player) {
//        super.stopSeenByPlayer(player);
//        this.bossInfo.removePlayer(player);
//    }
//
//    @Override
//    protected void customServerAiStep() {
//        if (this.tickCount % 20 == 0) {
//            this.heal(1.0F);
//        }
//
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//    }
//
//    @Override
//    public void checkDespawn() {
//    }
//
//    public FellBeastPartEntity[] getSubEntities() {
//        return this.subEntities;
//    }
//
//    @Override
//    public boolean isPickable() {
//        return false;
//    }
//
//    @Override
//    public SoundCategory getSoundSource() {
//        return SoundCategory.HOSTILE;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundGenerator.soundIdleFellBeast.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
//        return SoundEvents.ENDER_DRAGON_HURT;
//    }
//
//    @Override
//    protected float getSoundVolume() {
//        return 5.0F;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public float getHeadPartYOffset(int p_184667_1_, double[] p_184667_2_, double[] p_184667_3_) {
//        IFellBeastPhase iphase = this.phaseManager.getCurrentPhase();
//        FellBeastPhaseType<? extends IFellBeastPhase> phasetype = iphase.getPhase();
//        double d0;
//        if (phasetype != FellBeastPhaseType.LANDING && phasetype != FellBeastPhaseType.TAKEOFF) {
//            if (iphase.isSitting()) {
//                d0 = (double)p_184667_1_;
//            } else if (p_184667_1_ == 6) {
//                d0 = 0.0D;
//            } else {
//                d0 = p_184667_3_[1] - p_184667_2_[1];
//            }
//        } else {
//            BlockPos blockpos = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
//            float f = Math.max(MathHelper.sqrt(blockpos.distSqr(this.position(), true)) / 4.0F, 1.0F);
//            d0 = (double)((float)p_184667_1_ / f);
//        }
//
//        return (float)d0;
//    }
//
//    public Vector3d getHeadLookVector(float p_184665_1_) {
//        IFellBeastPhase iphase = this.phaseManager.getCurrentPhase();
//        FellBeastPhaseType<? extends IFellBeastPhase> phasetype = iphase.getPhase();
//        Vector3d vector3d;
//        if (phasetype != FellBeastPhaseType.LANDING && phasetype != FellBeastPhaseType.TAKEOFF) {
//            if (iphase.isSitting()) {
//                float f4 = this.xRot;
//                float f5 = 1.5F;
//                this.xRot = -45.0F;
//                vector3d = this.getViewVector(p_184665_1_);
//                this.xRot = f4;
//            } else {
//                vector3d = this.getViewVector(p_184665_1_);
//            }
//        } else {
//            BlockPos blockpos = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
//            float f = Math.max(MathHelper.sqrt(blockpos.distSqr(this.position(), true)) / 4.0F, 1.0F);
//            float f1 = 6.0F / f;
//            float f2 = this.xRot;
//            float f3 = 1.5F;
//            this.xRot = -f1 * 1.5F * 5.0F;
//            vector3d = this.getViewVector(p_184665_1_);
//            this.xRot = f2;
//        }
//
//        return vector3d;
//    }
//
//    public void onCrystalDestroyed(MorgulCrystalEntity crystalEntity, BlockPos pos, DamageSource source) {
//        PlayerEntity playerentity;
//        if (source.getEntity() instanceof PlayerEntity) {
//            playerentity = (PlayerEntity)source.getEntity();
//        } else {
//            playerentity = this.level.getNearestPlayer(CRYSTAL_DESTROY_TARGETING, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
//        }
//
//        if (crystalEntity == this.nearestCrystal) {
//            this.hurt(this.head, DamageSource.explosion(playerentity), 10.0F);
//        }
//
//        this.phaseManager.getCurrentPhase().onCrystalDestroyed(crystalEntity, pos, source, playerentity);
//    }
//
//    @Override
//    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
//        if (DATA_PHASE.equals(p_184206_1_) && this.level.isClientSide) {
//            this.phaseManager.setPhase(FellBeastPhaseType.getById(this.getEntityData().get(DATA_PHASE)));
//        }
//
//        super.onSyncedDataUpdated(p_184206_1_);
//    }
//
//    public FellBeastPhaseManager getFellBeastPhaseManager() {
//        return this.phaseManager;
//    }
//
//    @Nullable
//    public FellBeastFightManager getFellBeastFight() {
//        return this.fellbeastFight;
//    }
//
//    @Override
//    public boolean addEffect(EffectInstance p_195064_1_) {
//        return false;
//    }
//
//    @Override
//    protected boolean canRide(Entity p_184228_1_) {
//        return false;
//    }
//
//    @Override
//    public boolean canChangeDimensions() {
//        return false;
//    }
//
//    @Override
//    public boolean isMultipartEntity() {
//        return true;
//    }
//
//    @Override
//    public net.minecraftforge.entity.PartEntity<?>[] getParts() {
//        return this.subEntities;
//    }
//}