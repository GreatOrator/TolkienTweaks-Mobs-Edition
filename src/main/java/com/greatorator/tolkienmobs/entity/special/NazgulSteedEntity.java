package com.greatorator.tolkienmobs.entity.special;

//
//public class NazgulSteedEntity extends HorseEntity {
//    public NazgulSteedEntity(EntityType<? extends HorseEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return MobEntity.createMobAttributes()
//                .add(Attributes.MOVEMENT_SPEED, (double)1.0F)
//                .add(Attributes.MAX_HEALTH, 80.0D)
//                .add(Attributes.JUMP_STRENGTH, 2.5D);
//    }
//
//    @Nullable
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        if (TTMRand.getRandomInteger(15, 1) == 2) {
//            NazgulEntity entitynazgul = EntityGenerator.ENTITY_TTM_NAZGUL.get().create(this.level);
//            entitynazgul.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, 0.0F);
//            entitynazgul.finalizeSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT)null);
//            entitynazgul.startRiding(this);
//        }
//        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//    }
//
//    @Override
//    public boolean causeFallDamage(float distance, float damageMultiplier) {
//        return false;
//    }
//
//    @Override
//    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
//    }
//}
