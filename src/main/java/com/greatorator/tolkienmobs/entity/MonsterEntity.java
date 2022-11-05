package com.greatorator.tolkienmobs.entity;

//
//public class MonsterEntity extends net.minecraft.entity.monster.MonsterEntity implements IRangedAttackMob {
//    private final RangedBowAttackGoal<MonsterEntity> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
//    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
//        @Override
//        public void stop() {
//            super.stop();
//            MonsterEntity.this.setAggressive(false);
//        }
//
//        @Override
//        public void start() {
//            super.start();
//            MonsterEntity.this.setAggressive(true);
//        }
//    };
//
//    protected MonsterEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
//        super(type, worldIn);
//        this.reassessWeaponGoal();
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
//    }
//
//    @Override
//    public void performRangedAttack(LivingEntity livingEntity, float v) {
//        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
//        AbstractArrowEntity abstractarrowentity = this.getArrow(itemstack, v);
//        if (this.getMainHandItem().getItem() instanceof net.minecraft.item.BowItem)
//            abstractarrowentity = ((net.minecraft.item.BowItem)this.getMainHandItem().getItem()).customArrow(abstractarrowentity);
//        double d0 = livingEntity.getX() - this.getX();
//        double d1 = livingEntity.getY(0.3333333333333333D) - abstractarrowentity.getY();
//        double d2 = livingEntity.getZ() - this.getZ();
//        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
//        abstractarrowentity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
//        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
//        this.level.addFreshEntity(abstractarrowentity);
//    }
//    @Override
//    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
//        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
//    }
//
//    @Override
//    protected void populateDefaultEquipmentEnchantments(DifficultyInstance p_180483_1_) {
//    }
//
//    protected AbstractArrowEntity getArrow(ItemStack p_213624_1_, float p_213624_2_) {
//        return ProjectileHelper.getMobArrow(this, p_213624_1_, p_213624_2_);
//    }
//
//    public void reassessWeaponGoal() {
//        if (this.level != null && !this.level.isClientSide) {
//            this.goalSelector.removeGoal(this.meleeGoal);
//            this.goalSelector.removeGoal(this.bowGoal);
//            ItemStack itemstack = this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW));
//            if (itemstack.getItem() == Items.BOW) {
//                int i = 20;
//                if (this.level.getDifficulty() != Difficulty.HARD) {
//                    i = 40;
//                }
//
//                this.bowGoal.setMinAttackInterval(i);
//                this.goalSelector.addGoal(4, this.bowGoal);
//            } else {
//                this.goalSelector.addGoal(4, this.meleeGoal);
//            }
//
//        }
//    }
//}
