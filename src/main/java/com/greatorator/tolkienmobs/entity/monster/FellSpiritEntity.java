package com.greatorator.tolkienmobs.entity.monster;

//
//public class FellSpiritEntity extends MonsterEntity {
//    private static final DataParameter<Integer> FELLSPIRIT_TYPE = EntityDataManager.defineId(FellSpiritEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/fellspirit/fellspirit1.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/fellspirit/fellspirit2.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/fellspirit/fellspirit3.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/fellspirit/fellspirit4.png"));
//    });
//
//    public FellSpiritEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
//        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
//        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
//        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 16.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.23D)
//                .add(Attributes.ATTACK_DAMAGE, 3.0D)
//                .add(Attributes.ARMOR, 5.0D);
//    }
//
//    @Override
//    public void aiStep() {
//        if (this.isAlive()) {
//            boolean flag = this.shouldBurnInDay() && this.isSunBurnTick();
//            if (flag) {
//                ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.HEAD);
//                if (!itemstack.isEmpty()) {
//                    if (itemstack.isDamageableItem()) {
//                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
//                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
//                            this.broadcastBreakEvent(EquipmentSlotType.HEAD);
//                            this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
//                        }
//                    }
//
//                    flag = false;
//                }
//
//                if (flag) {
//                    this.setSecondsOnFire(8);
//                }
//            }
//        }
//
//        super.aiStep();
//    }
//
//    protected boolean shouldBurnInDay() {
//        return true;
//    }
//
//    @Override
//    public CreatureAttribute getMobType()
//    {
//        return CreatureAttribute.UNDEAD;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundGenerator.soundIdleBarrowWight.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundGenerator.soundHurtBarrowWight.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundGenerator.soundHurtBarrowWight.get();
//    }
//
//    /**
//     * Region for determining random skin
//     */
//    public ResourceLocation getFellSpiritTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getFellSpiritType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getFellSpiritType() {
//        return this.entityData.get(FELLSPIRIT_TYPE);
//    }
//
//    public void setFellSpiritType(int type) {
//        if (type < 0 || type >= 5) {
//            type = this.random.nextInt(4);
//        }
//
//        this.entityData.set(FELLSPIRIT_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(5, 1);
//        this.setFellSpiritType(job);
//        this.populateDefaultEquipmentSlots(difficultyIn);
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(FELLSPIRIT_TYPE, 3);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("FellSpiritType", this.getFellSpiritType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setFellSpiritType(compound.getInt("FellSpiritType"));
//    }
//}