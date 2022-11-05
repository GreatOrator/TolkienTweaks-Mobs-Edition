package com.greatorator.tolkienmobs.entity.boss;

//
//public class GwahirEntity extends BirdEntity {
//    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_12)).setDarkenScreen(true);
//
//    public GwahirEntity(EntityType<? extends BirdEntity> entityIn, World worldIn) {
//        super(entityIn, worldIn);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return FlyingEntity.createMobAttributes()
//                .add(Attributes.MAX_HEALTH, 240.0D)
//                .add(Attributes.ARMOR, 30.0D)
//                .add(Attributes.ATTACK_DAMAGE, 28.0D)
//                .add(Attributes.MOVEMENT_SPEED, (double)0.85F)
//                .add(Attributes.FLYING_SPEED, (double) 1.6F);
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        if (!super.hurt(source, amount)) {
//            return false;
//        } else if (!(this.level instanceof ServerWorld)) {
//            return false;
//        } else {
//            ServerWorld serverworld = (ServerWorld) this.level;
//            LivingEntity livingentity = this.getTarget();
//            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
//                livingentity = (LivingEntity) source.getEntity();
//            }
//
//            if (livingentity == null) return true;
//
//            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(Effects.WATER_BREATHING)) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nodrown.gwahir").withStyle(TextFormatting.DARK_BLUE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.WATER_BREATHING, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(Effects.FIRE_RESISTANCE)) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.onfire.gwahir").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.healself.gwahir").withStyle(TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.REGENERATION, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.speedup.gwahir").withStyle(TextFormatting.AQUA), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2 * 20, 0));
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        if (this.hasCustomName()) {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//    }
//
//    @Override
//    public void setCustomName(@Nullable ITextComponent name) {
//        super.setCustomName(name);
//        this.bossInfo.setName(this.getDisplayName());
//    }
//
//    @Override
//    public void startSeenByPlayer(ServerPlayerEntity player) {
//        super.startSeenByPlayer(player);
//        this.bossInfo.addPlayer(player);
//    }
//
//    @Override
//    public void stopSeenByPlayer(ServerPlayerEntity player) {
//        super.stopSeenByPlayer(player);
//        this.bossInfo.removePlayer(player);
//    }
//
//    @Override
//    protected void customServerAiStep() {
//        if (this.tickCount % 20 == 0) {
//            this.heal(1.0F);
//        }
//
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//    }
//
//    @Override
//    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
//        return p_213348_2_.height * 0.6F;
//    }
//
//    // Sound Region
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundGenerator.soundCallTMGreatEagle.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundGenerator.soundHurtTMGreatEagle.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundGenerator.soundDeathTMGreatEagle.get();
//    }
//    // End Region
//}