package com.greatorator.tolkienmobs.entity.boss;

//
//public class WatcherEntity extends WaterMobEntity {
//    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_12)).setDarkenScreen(true);
//    private float tx;
//    private float ty;
//    private float tz;
//    private long nextAbilityUse = 0L;
//    private final static long coolDown = 15000L;
//
//    public WatcherEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
//        super(type, worldIn);
//        this.moveControl = new WatcherEntity.MoveHelperController(this);
//
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new WatcherEntity.MoveRandomGoal(this));
//        this.goalSelector.addGoal(1, new WatcherEntity.WanderGoal(this, 1.0D, 100));
//        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
//        this.goalSelector.addGoal(4, new WatcherEntity.AttackGoal(this));
//        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return MonsterEntity.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 300.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.40D)
//                .add(Attributes.ATTACK_DAMAGE, 17.0D)
//                .add(Attributes.FOLLOW_RANGE, 40.0D)
//                .add(Attributes.ARMOR, 24.0D);
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundGenerator.soundIdleWatcher.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundGenerator.soundDeathWatcher.get();
//    }
//
//    public void setMovementVector(float p_175568_1_, float p_175568_2_, float p_175568_3_) {
//        this.tx = p_175568_1_;
//        this.ty = p_175568_2_;
//        this.tz = p_175568_3_;
//    }
//
//    public boolean hasMovementVector() {
//        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
//    }
//
//    static class MoveHelperController extends MovementController {
//        private final WatcherEntity watcher;
//
//        public MoveHelperController(WatcherEntity p_i48945_1_) {
//            super(p_i48945_1_);
//            this.watcher = p_i48945_1_;
//        }
//
//        public void tick() {
//            if (this.watcher.isInWater()) {
//                this.watcher.setDeltaMovement(this.watcher.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
//            }
//
//            if (this.operation == Action.MOVE_TO && !this.watcher.getNavigation().isDone()) {
//                double lvt_1_1_ = this.wantedX - this.watcher.getX();
//                double lvt_3_1_ = this.wantedY - this.watcher.getY();
//                double lvt_5_1_ = this.wantedZ - this.watcher.getZ();
//                double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_;
//                if (lvt_7_1_ < 2.500000277905201E-7D) {
//                    this.mob.setZza(0.0F);
//                } else {
//                    float lvt_9_1_ = (float)(MathHelper.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
//                    this.watcher.yRot = this.rotlerp(this.watcher.yRot, lvt_9_1_, 10.0F);
//                    this.watcher.yBodyRot = this.watcher.yRot;
//                    this.watcher.yHeadRot = this.watcher.yRot;
//                    float lvt_10_1_ = (float)(this.speedModifier * this.watcher.getAttributeValue(Attributes.MOVEMENT_SPEED));
//                    if (this.watcher.isInWater()) {
//                        this.watcher.setSpeed(lvt_10_1_ * 0.02F);
//                        float lvt_11_1_ = -((float)(MathHelper.atan2(lvt_3_1_, (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_)) * 57.2957763671875D));
//                        lvt_11_1_ = MathHelper.clamp(MathHelper.wrapDegrees(lvt_11_1_), -85.0F, 85.0F);
//                        this.watcher.xRot = this.rotlerp(this.watcher.xRot, lvt_11_1_, 5.0F);
//                        float lvt_12_1_ = MathHelper.cos(this.watcher.xRot * 0.017453292F);
//                        float lvt_13_1_ = MathHelper.sin(this.watcher.xRot * 0.017453292F);
//                        this.watcher.zza = lvt_12_1_ * lvt_10_1_;
//                        this.watcher.yya = -lvt_13_1_ * lvt_10_1_;
//                    } else {
//                        this.watcher.setSpeed(lvt_10_1_ * 0.1F);
//                    }
//
//                }
//            } else {
//                this.watcher.setSpeed(0.0F);
//                this.watcher.setXxa(0.0F);
//                this.watcher.setYya(0.0F);
//                this.watcher.setZza(0.0F);
//            }
//        }
//    }
//
//    static class WanderGoal extends RandomWalkingGoal {
//        private final WatcherEntity watcher;
//
//        private WanderGoal(WatcherEntity entityTTMWatcher, double p_i48813_2_, int p_i48813_4_) {
//            super(entityTTMWatcher, p_i48813_2_, p_i48813_4_);
//            this.watcher = entityTTMWatcher;
//        }
//
//        public boolean canUse() {
//            return this.mob.isInWater() && super.canUse();
//        }
//    }
//
//    class MoveRandomGoal extends Goal {
//        private final WatcherEntity watcher;
//
//        public MoveRandomGoal(WatcherEntity entityTTMWatcher) {
//            this.watcher = entityTTMWatcher;
//        }
//
//        @Override
//        public boolean canUse() {
//            return true;
//        }
//
//        @Override
//        public void tick() {
//            int lvt_1_1_ = this.watcher.getNoActionTime();
//            if (lvt_1_1_ > 100) {
//                this.watcher.setMovementVector(0.0F, 0.0F, 0.0F);
//            } else if (this.watcher.getRandom().nextInt(50) == 0 || !this.watcher.wasTouchingWater || !this.watcher.hasMovementVector()) {
//                float lvt_2_1_ = this.watcher.getRandom().nextFloat() * 6.2831855F;
//                float lvt_3_1_ = MathHelper.cos(lvt_2_1_) * 0.2F;
//                float lvt_4_1_ = -0.1F + this.watcher.getRandom().nextFloat() * 0.2F;
//                float lvt_5_1_ = MathHelper.sin(lvt_2_1_) * 0.2F;
//                this.watcher.setMovementVector(lvt_3_1_, lvt_4_1_, lvt_5_1_);
//            }
//        }
//    }
//
//    static class AttackGoal extends MeleeAttackGoal {
//        public AttackGoal(WatcherEntity entityTMWatcher) {
//            super(entityTMWatcher, 1.0D, true);
//        }
//
//        @Override
//        public boolean canContinueToUse() {
//            float f = this.mob.getBrightness();
//            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
//                this.mob.setTarget((LivingEntity)null);
//                return false;
//            } else {
//                return super.canContinueToUse();
//            }
//        }
//
//        @Override
//        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
//            return (double)(4.0F + p_179512_1_.getBbWidth());
//        }
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        if (!super.hurt(source, amount)) {
//            return false;
//        } else if (!(this.level instanceof ServerWorld)) {
//            return false;
//        } else {
//            LivingEntity livingentity = this.getTarget();
//            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
//                livingentity = (LivingEntity) source.getEntity();
//            }
//
//            if (livingentity == null) return true;
//
//            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.healself.watcher").withStyle(TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.REGENERATION, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.speedup.watcher").withStyle(TextFormatting.AQUA), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2 * 20, 0));
//            }
//        }
//        return true;
//    }
//
//    private float getAttackDamage() {
//        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
//    }
//
//    @Override
//    public boolean doHurtTarget(Entity entityIn) {
//        this.level.broadcastEntityEvent(this, (byte) 4);
//        float f = this.getAttackDamage();
//        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
//        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
//        if (flag) {
//            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, 0.4F, 0.0D));
//            this.doEnchantDamageEffects(this, entityIn);
//            long time = System.currentTimeMillis();
//            if (super.doHurtTarget(entityIn)) {
//                if (entityIn instanceof PlayerEntity) {
//                    if (time > nextAbilityUse) {
//                        nextAbilityUse = time + coolDown;
//                        PlayerEntity player = (PlayerEntity) entityIn;
//                        int strength = 3;
//                        if (TTMRand.getRandom(10) <= 3) {
//                            player.addEffect(new EffectInstance(PotionGenerator.ELEMENTAL_DROWNING.get(), strength * 20, 0));
//                        } else {
//                            player.addEffect(new EffectInstance(PotionGenerator.SLEEPNESIA.get(), strength * 20, 0));
//                        }
//                    }
//                }
//            }
//        }
//
//        this.playSound(SoundGenerator.soundIdleWatcher.get(), 1.0F, 1.0F);
//        return flag;
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        this.populateDefaultEquipmentSlots(difficultyIn);
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        if (this.hasCustomName()) {
//            this.bossInfo.setName(this.getDisplayName());
//        }
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
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//    }
//}