package com.greatorator.tolkienmobs.entity.merchant;

//
//public class HobbitEntity extends VillagerEntity {
//
//    private static final DataParameter<Integer> HOBBIT_TYPE = EntityDataManager.defineId(HobbitEntity.class, DataSerializers.INT);
//    private static final DataParameter<VillagerData> HOBBIT_DATA = EntityDataManager.defineId(HobbitEntity.class, DataSerializers.VILLAGER_DATA);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit1.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit2.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit3.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit4.png"));
//        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit5.png"));
//        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit6.png"));
//        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit7.png"));
//        option.put(8, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit8.png"));
//        option.put(9, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit9.png"));
//        option.put(10, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit10.png"));
//        option.put(11, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit11.png"));
//        option.put(12, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit12.png"));
//        option.put(13, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit13.png"));
//        option.put(14, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit14.png"));
//        option.put(15, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit15.png"));
//        option.put(16, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit16.png"));
//    });
//
//    public HobbitEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
//        super(type, worldIn);
//        this.setRndMinMax(1,16);
//    }
//
//    /** Region for determining random skin */
//    public ResourceLocation getHobbitTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getHobbitType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getHobbitType() {
//        return this.entityData.get(HOBBIT_TYPE);
//    }
//
//    public void setHobbitType(int type) {
//        if (type < 0 || type >= 17) {
//            type = this.random.nextInt(16);
//        }
//
//        this.entityData.set(HOBBIT_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(1, 16);
//        this.setHobbitType(job);
//
//        if (job == 1 || job == 9) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.COIN_TRADER_PROFESSION.get()));
//        }
//        if (job == 2 || job == 10) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.GROCERY_STORE_PROFESSION.get()));
//        }
//        if (job == 3 || job == 11) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.JUNK_TRADER_PROFESSION.get()));
//        }
//        if (job == 4 || job == 12) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.PET_MERCHANT_PROFESSION.get()));
//        }
//        if (job == 5 || job == 13) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FARMER));
//        }
//        if (job == 6 || job == 14) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FISHERMAN));
//        }
//        if (job == 7 || job == 15) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.SHEPHERD));
//        }
//        if (job == 8 || job == 16) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.NONE));
//        }
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(HOBBIT_TYPE, 16);
//        this.entityData.define(HOBBIT_DATA, new VillagerData(VillagerType.PLAINS, ProfessionGenerator.UNEMPLOYED_PROFESSION.get(), 1));
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("HobbitType", this.getHobbitType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setHobbitType(compound.getInt("HobbitType"));
//    }
//}