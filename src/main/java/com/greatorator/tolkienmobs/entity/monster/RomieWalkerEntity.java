package com.greatorator.tolkienmobs.entity.monster;

/** Idea provided by Romie **/
//
//public class RomieWalkerEntity extends MonsterEntity {
//    private static final DataParameter<Integer> ROMIE_TYPE = EntityDataManager.defineId(RomieWalkerEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/romiewalker/romiewalker0.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/romiewalker/romiewalker1.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/romiewalker/romiewalker2.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/romiewalker/romiewalker3.png"));
//        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/romiewalker/romiewalker4.png"));
//    });
//    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (p_213697_0_) -> {
//        return p_213697_0_ == Difficulty.HARD;
//    };
//    private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
//    private boolean canBreakDoors;
//
//    public RomieWalkerEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 20.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.23D)
//                .add(Attributes.ATTACK_DAMAGE, 3.0D)
//                .add(Attributes.ARMOR, 5.0D);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
//        this.addBehaviourGoals();
//    }
//
//    protected void addBehaviourGoals() {
//        this.goalSelector.addGoal(2, new TTMRomieWalkerAttackGoal(this, 1.0D, false));
//        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
//        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
//        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
//    }
//
//    public boolean canBreakDoors() {
//        return this.canBreakDoors;
//    }
//
//    public void setCanBreakDoors(boolean p_146070_1_) {
//        if (this.supportsBreakDoorGoal() && GroundPathHelper.hasGroundPathNavigation(this)) {
//            if (this.canBreakDoors != p_146070_1_) {
//                this.canBreakDoors = p_146070_1_;
//                ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(p_146070_1_);
//                if (p_146070_1_) {
//                    this.goalSelector.addGoal(1, this.breakDoorGoal);
//                } else {
//                    this.goalSelector.removeGoal(this.breakDoorGoal);
//                }
//            }
//        } else if (this.canBreakDoors) {
//            this.goalSelector.removeGoal(this.breakDoorGoal);
//            this.canBreakDoors = false;
//        }
//
//    }
//
//    protected boolean supportsBreakDoorGoal() {
//        return true;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundGenerator.soundIdleRomieWalker.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundGenerator.soundHurtRomieWalker.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundGenerator.soundDeathRomieWalker.get();
//    }
//
//    /**
//     * Region for determining random skin
//     */
//    public ResourceLocation getRomieWalkerTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getRomieWalkerType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getRomieWalkerType() {
//        return this.entityData.get(ROMIE_TYPE);
//    }
//
//    public void setRomieWalkerType(int type) {
//        if (type < 0 || type >= 6) {
//            type = this.random.nextInt(4);
//        }
//
//        this.entityData.set(ROMIE_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(6, 1);
//        this.setRomieWalkerType(job);
//        this.populateDefaultEquipmentSlots(difficultyIn);
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(ROMIE_TYPE, 3);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("RomieWalkerType", this.getRomieWalkerType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setRomieWalkerType(compound.getInt("RomieWalkerType"));
//    }
//}