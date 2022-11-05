package com.greatorator.tolkienmobs.entity.passive;

//
//public class AurochEntity extends HerdEntity {
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
//}