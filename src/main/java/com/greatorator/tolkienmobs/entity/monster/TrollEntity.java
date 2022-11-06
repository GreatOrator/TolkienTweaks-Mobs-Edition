package com.greatorator.tolkienmobs.entity.monster;

//
//public class TrollEntity extends MonsterEntity {
//    private static final DataParameter<Integer> TROLL_TYPE = EntityDataManager.defineId(TrollEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll1.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll2.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll3.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll4.png"));
//    });
//
//    /** Set up using weapons **/
//    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
//        @Override
//        public void stop() {
//            super.stop();
//            TrollEntity.this.setAggressive(false);
//        }
//
//        @Override
//        public void start() {
//            super.start();
//            TrollEntity.this.setAggressive(true);
//        }
//    };
//    /** End Region **/
//
//    public TrollEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 30.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.23D)
//                .add(Attributes.ATTACK_DAMAGE, 11.0D)
//                .add(Attributes.ARMOR, 9.0D);
//    }
//
//    @Override
//    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
//        return 2.4F;
//    }
//
//    /** Set up using weapons **/
//    @Override
//    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
//        super.populateDefaultEquipmentSlots(p_180481_1_);
//        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(TolkienContent.CLUB_WOODEN.get()));
//    }
//
//    @Override
//    public void reassessWeaponGoal() {
//        if (this.level != null && !this.level.isClientSide) {
//            this.goalSelector.removeGoal(this.meleeGoal);
//            ItemStack itemstack = this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, TolkienContent.CLUB_WOODEN.get()));
//            if (itemstack.getItem() == TolkienContent.CLUB_WOODEN.get()) {
//                this.goalSelector.addGoal(4, this.meleeGoal);
//            }
//        }
//    }
//
//    @Override
//    public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {
//        super.setItemSlot(p_184201_1_, p_184201_2_);
//        if (!this.level.isClientSide) {
//            this.reassessWeaponGoal();
//        }
//
//    }
//    /** End Region **/
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundGenerator.soundIdleTroll.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource parDamageSource)
//    {
//        return SoundGenerator.soundHurtTroll.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundGenerator.soundDeathTroll.get();
//    }
//
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundGenerator.soundStepTroll.get(), 0.25F, 1.0F);
//    }
//
//    /**
//     * Region for determining random skin
//     */
//    public ResourceLocation getTrollTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getTrollType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getTrollType() {
//        return this.entityData.get(TROLL_TYPE);
//    }
//
//    public void setTrollType(int type) {
//        if (type < 0 || type >= 5) {
//            type = this.random.nextInt(4);
//        }
//
//        this.entityData.set(TROLL_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(5, 1);
//        this.setTrollType(job);
//        this.populateDefaultEquipmentSlots(difficultyIn);
//        this.reassessWeaponGoal();
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(TROLL_TYPE, 3);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("TrollType", this.getTrollType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setTrollType(compound.getInt("TrollType"));
//        this.reassessWeaponGoal();
//    }
//}