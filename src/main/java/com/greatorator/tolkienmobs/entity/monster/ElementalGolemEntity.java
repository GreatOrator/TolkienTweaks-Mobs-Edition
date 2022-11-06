package com.greatorator.tolkienmobs.entity.monster;

//
//public class ElementalGolemEntity extends MonsterEntity implements IAngerable {
//    private static final DataParameter<Integer> ELEMENT_TYPE = EntityDataManager.defineId(ElementalGolemEntity.class, DataSerializers.INT);
//    private static final DataParameter<Integer> GOLEM_TYPE = EntityDataManager.defineId(ElementalGolemEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_none.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_earth.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_air.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_fire.png"));
//        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_water.png"));
//    });
//    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
//    private static int attackAnimationTick;
//    private int remainingPersistentAngerTime;
//    private UUID persistentAngerTarget;
//    private long nextAbilityUse = 0L;
//    private final static long coolDown = 15000L;
//
//    public ElementalGolemEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
//        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
//        this.goalSelector.addGoal(2, new ReturnToVillageGoal(this, 0.6D, false));
//        this.goalSelector.addGoal(4, new PatrolVillageGoal(this, 0.6D));
//        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
//        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (p_234199_0_) -> {
//            return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof CreeperEntity);
//        }));
//        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return MobEntity.createMobAttributes()
//                .add(Attributes.MAX_HEALTH, 100.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.25D)
//                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
//                .add(Attributes.ATTACK_DAMAGE, 15.0D);
//    }
//
//    @Override
//    public void aiStep() {
//        super.aiStep();
//        if (this.attackAnimationTick > 0) {
//            --this.attackAnimationTick;
//        }
//
//        if (getHorizontalDistanceSqr(this.getDeltaMovement()) > (double)2.5000003E-7F && this.random.nextInt(5) == 0) {
//            int i = MathHelper.floor(this.getX());
//            int j = MathHelper.floor(this.getY() - (double)0.2F);
//            int k = MathHelper.floor(this.getZ());
//            BlockPos pos = new BlockPos(i, j, k);
//            BlockState blockstate = this.level.getBlockState(pos);
//            if (!blockstate.isAir(this.level, pos)) {
//                this.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), 4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
//            }
//        }
//
//        if (!this.level.isClientSide) {
//            this.updatePersistentAnger((ServerWorld)this.level, true);
//        }
//
//    }
//
//    private float getAttackDamage() {
//        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public int getAttackAnimationTick() {
//        return this.attackAnimationTick;
//    }
//
//    @Override
//    public boolean doHurtTarget(Entity entityIn) {
//        this.attackAnimationTick = 10;
//        this.level.broadcastEntityEvent(this, (byte)4);
//        float f = this.getAttackDamage();
//        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
//        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
//        if (flag) {
//            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
//            this.doEnchantDamageEffects(this, entityIn);
//            if (this.getGolemType() == 1) { /* Stone */
//                long time = System.currentTimeMillis();
//                if (super.doHurtTarget(entityIn)) {
//                    if (entityIn instanceof PlayerEntity) {
//                        if (time > nextAbilityUse) {
//                            nextAbilityUse = time + coolDown;
//                            PlayerEntity player = (PlayerEntity) entityIn;
//                            int strength = 2;
//                            player.addEffect(new EffectInstance(PotionGenerator.INVENTORY_CORROSION.get(), strength * 20, 0));
//                        }
//                    }
//                }
//            } else if (this.getGolemType() == 2) { /* Earth */
//                long time = System.currentTimeMillis();
//                if (super.doHurtTarget(entityIn)) {
//                    if (entityIn instanceof PlayerEntity) {
//                        if (time > nextAbilityUse) {
//                            nextAbilityUse = time + coolDown;
//                            PlayerEntity player = (PlayerEntity) entityIn;
//                            int strength = 2;
//                            if (TTMRand.getRandom(10) <= 3) {
//                                player.addEffect(new EffectInstance(PotionGenerator.ELEMENTAL_FLYING.get(), strength * 20, 0));
//                            } else {
//                                player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, strength * 20, 0));
//                            }
//                        }
//                    }
//                }
//            } else if (this.getGolemType() == 3) { /* Air */
//                long time = System.currentTimeMillis();
//                if (super.doHurtTarget(entityIn)) {
//                    if (entityIn instanceof PlayerEntity) {
//                        if (time > nextAbilityUse) {
//                            nextAbilityUse = time + coolDown;
//                            PlayerEntity player = (PlayerEntity) entityIn;
//                            int strength = 2;
//                            if (TTMRand.getRandom(10) <= 3) {
//                                player.addEffect(new EffectInstance(PotionGenerator.ELEMENTAL_TORNADO.get(), strength * 20, 0));
//                            } else {
//                                player.addEffect(new EffectInstance(PotionGenerator.ELEMENTAL_LIGHTNING.get(), strength * 20, 0));
//                            }
//                        }
//                    }
//                }
//            } else if (this.getGolemType() == 4) { /* Fire */
//                long time = System.currentTimeMillis();
//                if (super.doHurtTarget(entityIn)) {
//                    if (entityIn instanceof PlayerEntity) {
//                        if (time > nextAbilityUse) {
//                            nextAbilityUse = time + coolDown;
//                            PlayerEntity player = (PlayerEntity) entityIn;
//                            int strength = 2;
//                            if (TTMRand.getRandom(10) <= 3) {
//                                player.addEffect(new EffectInstance(PotionGenerator.ELEMENTAL_BURNING.get(), strength * 20, 0));
//                            } else {
//                                player.addEffect(new EffectInstance(Effects.WEAKNESS, strength * 20, 0));
//                            }
//                        }
//                    }
//                }
//            } else if (this.getGolemType() == 5) { /* Water */
//                long time = System.currentTimeMillis();
//                if (super.doHurtTarget(entityIn)) {
//                    if (entityIn instanceof PlayerEntity) {
//                        if (time > nextAbilityUse) {
//                            nextAbilityUse = time + coolDown;
//                            PlayerEntity player = (PlayerEntity) entityIn;
//                            int strength = 2;
//                            if (TTMRand.getRandom(10) <= 3) {
//                                player.addEffect(new EffectInstance(PotionGenerator.ELEMENTAL_DROWNING.get(), strength * 20, 0));
//                            } else {
//                                player.addEffect(new EffectInstance(Effects.BLINDNESS, strength * 20, 0));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
//        return flag;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public void handleEntityEvent(byte p_70103_1_) {
//        if (p_70103_1_ == 4)
//            this.attackAnimationTick = 10;
//            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
//    }
//
//    @Override
//    public int getRemainingPersistentAngerTime() {
//        return this.remainingPersistentAngerTime;
//    }
//
//    @Override
//    public void setRemainingPersistentAngerTime(int i) {
//        this.remainingPersistentAngerTime = i;
//    }
//
//    @Nullable
//    @Override
//    public UUID getPersistentAngerTarget() {
//        return this.persistentAngerTarget;
//    }
//
//    @Override
//    public void setPersistentAngerTarget(@Nullable UUID uuid) {
//        this.persistentAngerTarget = uuid;
//    }
//
//    @Override
//    public void startPersistentAngerTimer() {
//        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
//    }
//
//    /**
//     * Region for determining random skin
//     */
//
//    public void setGolemType(int golemType) {
//        this.entityData.set(ELEMENT_TYPE, golemType);
//    }
//
//    public int getGolemType() {
//        return this.entityData.get(ELEMENT_TYPE);
//    }
//
//    public ResourceLocation getElementalGolemTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getElementalGolemType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getElementalGolemType() {
//        return this.entityData.get(GOLEM_TYPE);
//    }
//
//    public void setElementalGolemType(int type) {
//        if (type < 0 || type >= 6) {
//            type = this.random.nextInt(5);
//        }
//
//        this.entityData.set(GOLEM_TYPE, type);
//        this.entityData.set(ELEMENT_TYPE, type);
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundGenerator.soundDeathGolem.get();
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(5, 1);
//        this.setElementalGolemType(job);
//        this.setGolemType(job);
//        this.reassessWeaponGoal();
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(GOLEM_TYPE, 1);
//        this.entityData.define(ELEMENT_TYPE, 1);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("ElementalGolemType", this.getElementalGolemType());
//        compound.putInt("ElementalType", this.getGolemType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setElementalGolemType(compound.getInt("ElementalGolemType"));
//        this.setGolemType(compound.getInt("ElementalType"));
//    }
//}
