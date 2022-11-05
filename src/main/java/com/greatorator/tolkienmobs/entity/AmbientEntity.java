package com.greatorator.tolkienmobs.entity;

//
//public class AmbientEntity extends AnimalEntity {
//
//    protected AmbientEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        return this.isInvulnerableTo(source) ? false : super.hurt(source, amount);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return MobEntity.createMobAttributes()
//                .add(Attributes.MAX_HEALTH, 10.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.25D)
//                .add(Attributes.FOLLOW_RANGE, 16D)
//                .add(Attributes.ATTACK_DAMAGE, 2.0D);
//    }
//
//    @Override
//    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
//        return 0.13F;
//    }
//
//    @Override
//    public IPacket<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//
//    public static int spawnChance() {
//        int i = TTMRand.getRandomInteger(100, 1);
//        return i;
//    }
//
//    @Nullable
//    @Override
//    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
//        return null;
//    }
//
//    protected boolean isValidLightLevel() {
//        return true;
//    }
//
//    public boolean getCanSpawnHere() {
//        int i = MathHelper.floor(this.getX());
//        int j = MathHelper.floor(this.getBoundingBox().minY);
//        int k = MathHelper.floor(this.getZ());
//        BlockPos blockpos = new BlockPos(i, j, k);
//
//        return this.level.getDifficulty() != Difficulty.PEACEFUL && this.isValidLightLevel() && spawnChance() < 5;
//    }
//
//    @Override
//    public boolean removeWhenFarAway(double p_213397_1_) {
//        return true;
//    }
//
//    public static boolean checkTTMAmbientSpawn(EntityType<? extends AmbientEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
//        int chance = 50; //1 in x
//        return random.nextInt(chance) == 0 && checkMobSpawnRules(type, world, reason, pos, random);
//    }
//}
