package com.greatorator.tolkienmobs.entity.merchant;

//
//public class DesertDwellerEntity extends VillagerEntity {
//    private static final DataParameter<Integer> DWELLER_TYPE = EntityDataManager.defineId(DesertDwellerEntity.class, DataSerializers.INT);
//    private static final DataParameter<VillagerData> DWELLER_DATA = EntityDataManager.defineId(DesertDwellerEntity.class, DataSerializers.VILLAGER_DATA);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
//        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant1.png"));
//        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant2.png"));
//        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant3.png"));
//        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant4.png"));
//        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant5.png"));
//        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant6.png"));
//        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant7.png"));
//        option.put(8, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant8.png"));
//        option.put(9, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant9.png"));
//        option.put(10, new ResourceLocation(TolkienMobs.MODID, "textures/entity/desertdweller/haradmerchant10.png"));
//    });
//
//    public DesertDwellerEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
//        super(type, worldIn);
//        this.setRndMinMax(1,16);
//    }
//
//    /** Region for determining random skin */
//    public ResourceLocation getDwellerTypeName() {
//        return TEXTURE_BY_ID.getOrDefault(this.getDwellerType(), TEXTURE_BY_ID.get(1));
//    }
//
//    public int getDwellerType() {
//        return this.entityData.get(DWELLER_TYPE);
//    }
//
//    public void setDwellerType(int type) {
//        if (type < 0 || type >= 11) {
//            type = this.random.nextInt(10);
//        }
//
//        this.entityData.set(DWELLER_TYPE, type);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        int job = TTMRand.getRandomInteger(1, 10);
//        this.setDwellerType(job);
//
//        if (job == 0 || job == 7) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.COIN_TRADER_PROFESSION.get()));
//        }
//        if (job == 1 || job == 8) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.GROCERY_STORE_PROFESSION.get()));
//        }
//        if (job == 2 || job == 9) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.JUNK_TRADER_PROFESSION.get()));
//        }
//        if (job == 3 || job == 10) {
//            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.PET_MERCHANT_PROFESSION.get()));
//        }
//        if (job == 4) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FARMER));
//        }
//        if (job == 5) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FISHERMAN));
//        }
//        if (job == 6) {
//            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.NONE));
//        }
//
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(DWELLER_TYPE, 6);
//        this.entityData.define(DWELLER_DATA, new VillagerData(VillagerType.PLAINS, ProfessionGenerator.UNEMPLOYED_PROFESSION.get(), 1));
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putInt("DwellerType", this.getDwellerType());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.setDwellerType(compound.getInt("DwellerType"));
//    }
//}