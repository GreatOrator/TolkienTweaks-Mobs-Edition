package com.greatorator.tolkienmobs.entity.ambient;

//
//public class SquirrelEntity extends AmbientEntity {
//    private static final DataParameter<Integer> SQUIRREL_TYPE = EntityDataManager.defineId(SquirrelEntity.class, DataSerializers.INT);
//    private static final ResourceLocation KILLER_SQUIRREL = new ResourceLocation(TolkienMobs.MODID, "textures/entity/sosquirrel/killer_squirrel");
//
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/sosquirrel/sosquirrel.png"));
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/sosquirrel/sosquirrel2.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/sosquirrel/sosquirrel3.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/sosquirrel/sosquirrel4.png"));
//    });
//
//    public SquirrelEntity(EntityType<? extends SquirrelEntity> type, World worldIn)
//    {
//        super(type, worldIn);
//    }
//
//    @Override
//    protected void registerGoals()
//    {
//        this.goalSelector.addGoal(1, new SwimGoal(this));
//        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
//        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(TolkienTags.items.ACORNS), true));
//        this.goalSelector.addGoal(5, new PanicGoal(this, 1.3F));
//        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, PlayerEntity.class, 2.0F, 0.8F, 1.4F));
//        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
//    }
//
//    @Override
//    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
//        return 0.13F;
//    }
//
//    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
//        private final SquirrelEntity ttmSquirrel;
//
//        public AvoidEntityGoal(SquirrelEntity ttmSquirrel, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
//            super(ttmSquirrel, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
//            this.ttmSquirrel = ttmSquirrel;
//        }
//
//        /**
//         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
//         * method as well.
//         */
//        @Override
//        public boolean canUse() {
//            return this.ttmSquirrel.getSquirrelType() != 99 && super.canUse();
//        }
//    }
//
//    /** Region for determining random skin */
//    public ResourceLocation getSquirrelTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getSquirrelType(), TEXTURE_BY_ID.get(0));
//    }
//
//    public int getSquirrelType() {
//        return this.entityData.get(SQUIRREL_TYPE);
//    }
//
//    public void setSquirrelType(int type) {
//        if (type == 99) {
//            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
//            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
//            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WolfEntity.class, true));
//            this.goalSelector.addGoal(4, new SquirrelEntity.EvilAttackGoal(this));
//            if (!this.hasCustomName()) {
//                this.setCustomName(new TranslationTextComponent(Util.makeDescriptionId("entity", KILLER_SQUIRREL)));
//            }
//        }
//
//        this.entityData.set(SQUIRREL_TYPE, type);
//        if (type < 0 || type >= 4) {
//            type = this.random.nextInt(3);
//        }
//
//        this.entityData.set(SQUIRREL_TYPE, type);
//    }
//
//    static class EvilAttackGoal extends MeleeAttackGoal {
//        public EvilAttackGoal(SquirrelEntity squirrel) {
//            super(squirrel, 1.4D, true);
//        }
//
//        @Override
//        protected double getAttackReachSqr(LivingEntity attackTarget) {
//            return (double)(4.0F + attackTarget.getBbWidth());
//        }
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(SQUIRREL_TYPE, 1);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("SquirrelType", this.getSquirrelType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setSquirrelType(compound.getInt("SquirrelType"));
//    }
//
//    protected SoundEvent getJumpSound()
//    {
//        return SoundGenerator.soundStepSOSquirrel.get();
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundGenerator.soundIdleSOSquirrel.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundGenerator.soundHurtSOSquirrel.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundGenerator.soundDeathSOSquirrel.get();
//    }
//
//    @Override
//    public SoundCategory getSoundSource()
//    {
//        return this.getSquirrelType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
//    }
//
//    @Override
//    public boolean doHurtTarget(Entity entityIn)
//    {
//        if (this.getSquirrelType() == 99)
//        {
//            this.playSound(SoundGenerator.soundAngrySOSquirrel.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//            return entityIn.hurt(DamageSource.mobAttack(this), 8.0F);
//        }
//        else
//        {
//            return entityIn.hurt(DamageSource.mobAttack(this), 3.0F);
//        }
//    }
//
//    private boolean isSquirrelBreedingItem(Item itemIn)
//    {
//        return itemIn == TolkienContent.TREE_ACORN.get() || itemIn == TolkienContent.GOLDEN_TREE_ACORN.get();
//    }
//
//    @Override
//    public boolean isFood(ItemStack stack)
//    {
//        return this.isSquirrelBreedingItem(stack.getItem());
//    }
//
//    @Override
//    public float getWalkTargetValue(BlockPos pos) {
//        // prefer standing on leaves
//        Material underMaterial = this.level.getBlockState(pos.below()).getMaterial();
//        if (underMaterial == Material.LEAVES) {
//            return 12.0F;
//        }
//        if (underMaterial == Material.WOOD) {
//            return 15.0F;
//        }
//        if (underMaterial == Material.DIRT) {
//            return 10.0F;
//        }
//
//        return this.level.getLightEmission(pos) - 0.5F;
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
//    {
//        int i = this.getRandomSquirrelType(worldIn);
//        if (spawnDataIn instanceof SquirrelEntity.SquirrelData) {
//            i = ((SquirrelEntity.SquirrelData)spawnDataIn).typeData;
//        } else {
//            spawnDataIn = new SquirrelEntity.SquirrelData(i);
//        }
//
//        this.setSquirrelType(i);
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    private int getRandomSquirrelType(IWorld worldIn)
//    {
//        Biome biome = worldIn.getBiome(this.blockPosition());
//        int i = this.random.nextInt(100);
//
//        if (biome.getPrecipitation() == Biome.RainType.SNOW) {
//            return i < 80 ? 1 : 3;
//        } else if (biome.getBiomeCategory() == Biome.Category.DESERT) {
//            return 4;
//        } else {
//            return i < 50 ? 0 : (i < 90 ? 4 : 2);
//        }
//    }
//
//    public static class SquirrelData extends AgeableEntity.AgeableData {
//        public final int typeData;
//
//        public SquirrelData(int type) {
//            super(1.0F);
//            this.typeData = type;
//        }
//    }
//
//    @Nullable
//    @Override
//    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
//        return null;
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getDefaultLootTable() {
//        return null;
//    }
//}