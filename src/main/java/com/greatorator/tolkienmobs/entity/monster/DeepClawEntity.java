package com.greatorator.tolkienmobs.entity.monster;

//
//public class DeepClawEntity extends MonsterEntity {
//    private static final DataParameter<Integer> DEEPCLAW_TYPE = EntityDataManager.defineId(DeepClawEntity.class, DataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw1.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw2.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw3.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw4.png"));
//    });
//
//    public DeepClawEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 22.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.23D)
//                .add(Attributes.ATTACK_DAMAGE, 8.0D)
//                .add(Attributes.ARMOR, 10.0D);
//    }
//
//    @Override
//    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
//        return 0.5F;
//    }
//
//    /**
//     * Region for determining random skin
//     */
//    public ResourceLocation getDeepclawTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getDeepclawType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getDeepclawType() {
//        return this.entityData.get(DEEPCLAW_TYPE);
//    }
//
//    public void setDeepclawType(int type) {
//        if (type < 0 || type >= 5) {
//            type = this.random.nextInt(4);
//        }
//
//        this.entityData.set(DEEPCLAW_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(5, 1);
//        this.setDeepclawType(job);
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(DEEPCLAW_TYPE, 3);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("DeepclawType", this.getDeepclawType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setDeepclawType(compound.getInt("DeepclawType"));
//    }
//}
