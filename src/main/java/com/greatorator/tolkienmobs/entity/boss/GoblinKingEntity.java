package com.greatorator.tolkienmobs.entity.boss;

//
//public class GoblinKingEntity extends GoblinEntity implements IRangedAttackMob {
//    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
//    private static final DataParameter<Integer> GOBLINKING_TYPE = EntityDataManager.defineId(GoblinKingEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblinking.png"));
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblinking.png"));
//    });
//
//    public GoblinKingEntity(EntityType<? extends GoblinKingEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(GoblinEntity.class));
//        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
//        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return MonsterEntity.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 120.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.35D)
//                .add(Attributes.ATTACK_DAMAGE, 10.0D)
//                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 100.0D);
//    }
//
//    @Override
//    public void performRangedAttack(LivingEntity target, float distanceFactor) {
//        SnowballEntity snowballentity = new SnowballEntity(this.level, this);
//        double d0 = target.getEyeY() - (double)1.1F;
//        double d1 = target.getX() - this.getX();
//        double d2 = d0 - snowballentity.getY();
//        double d3 = target.getZ() - this.getZ();
//        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
//        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
//        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
//        this.level.addFreshEntity(snowballentity);
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        if (!super.hurt(source, amount)) {
//            return false;
//        } else if (!(this.level instanceof ServerWorld)) {
//            return false;
//        } else {
//            ServerWorld serverworld = (ServerWorld)this.level;
//            LivingEntity livingentity = this.getTarget();
//            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
//                livingentity = (LivingEntity)source.getEntity();
//            }
//
//            if (livingentity == null) return true;
//
//            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(Effects.WATER_BREATHING)) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nodrown").withStyle(TextFormatting.DARK_BLUE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.WATER_BREATHING, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(Effects.FIRE_RESISTANCE)) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.onfire").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.healself").withStyle(TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.REGENERATION, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.speedup").withStyle(TextFormatting.AQUA), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2 * 20, 0));
//            }
//
//            int xPos = MathHelper.floor(this.getX());
//            int yPos = MathHelper.floor(this.getY());
//            int zPos = MathHelper.floor(this.getZ());
//
//            GoblinEvent.SummonAidEvent event = ServerEvents.fireGoblinSummonAid(this, level, xPos, yPos, zPos, livingentity, this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue());
//
//            if (event.getResult() == Event.Result.DENY) {
//
//                if (livingentity instanceof PlayerEntity){
//                    livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nohelp").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
//                }
//                return true;
//            }
//            if (event.getResult() == Event.Result.ALLOW  ||
//                    livingentity != null && (double)this.random.nextFloat() < this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
//
//                if (livingentity instanceof PlayerEntity){
//                    livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.helpcomming").withStyle(TextFormatting.DARK_GREEN), Util.NIL_UUID);
//                }
//
//                GoblinEntity goblinentity = event.getCustomSummonedAid() != null && event.getResult() == Event.Result.ALLOW ? event.getCustomSummonedAid() : EntityGenerator.ENTITY_TTM_GOBLIN.get().create(this.level);
//
//                for(int l = 0; l < 50; ++l) {
//                    int spawnX = xPos + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
//                    int spawnY = yPos + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
//                    int spawnZ = zPos + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
//                    BlockPos blockpos = new BlockPos(spawnX, spawnY, spawnZ);
//                    EntityType<?> entitytype = goblinentity.getType();
//                    EntitySpawnPlacementRegistry.PlacementType entityspawnplacementregistry$placementtype = EntitySpawnPlacementRegistry.getPlacementType(entitytype);
//                    if (WorldEntitySpawner.isSpawnPositionOk(entityspawnplacementregistry$placementtype, this.level, blockpos, entitytype) && EntitySpawnPlacementRegistry.checkSpawnRules(entitytype, serverworld, SpawnReason.REINFORCEMENT, blockpos, this.level.random)) {
//                        goblinentity.setPos((double)spawnX, (double)spawnY, (double)spawnZ);
//                        if (!this.level.hasNearbyAlivePlayer((double)spawnX, (double)spawnY, (double)spawnZ, 7.0D) && this.level.isUnobstructed(goblinentity) && this.level.noCollision(goblinentity) && !this.level.containsAnyLiquid(goblinentity.getBoundingBox())) {
//                            if (livingentity != null)
//                                goblinentity.setTarget(livingentity);
//                            goblinentity.finalizeSpawn(serverworld, this.level.getCurrentDifficultyAt(goblinentity.blockPosition()), SpawnReason.REINFORCEMENT, (ILivingEntityData)null, (CompoundNBT)null);
//                            serverworld.addFreshEntityWithPassengers(goblinentity);
//                            this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Goblin reinforcement caller charge", (double)-0.05F, AttributeModifier.Operation.ADDITION));
//                            break;
//                        }
//                    }
//                }
//            }
//            return true;
//        }
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundGenerator.soundIdleGoblin.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundGenerator.soundHurtGoblin.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundGenerator.soundDeathGoblin.get();
//    }
//
//    /**
//     * Region for determining random skin
//     */
//    public ResourceLocation getGoblinKingTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getGoblinKingType(), TEXTURE_BY_ID.get(0));
//    }
//
//    public int getGoblinKingType() {
//        return this.entityData.get(GOBLINKING_TYPE);
//    }
//
//    public void setGoblinKingType(int type) {
//        if (type < 0 || type >= 3) {
//            type = this.random.nextInt(2);
//        }
//
//        this.entityData.set(GOBLINKING_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(1, 1);
//        this.setGoblinKingType(job);
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(GOBLINKING_TYPE, 1);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("GoblinKingType", this.getGoblinKingType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setGoblinKingType(compound.getInt("GoblinKingType"));
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
//
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//    }
//
//    @Override
//    public IPacket<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//}