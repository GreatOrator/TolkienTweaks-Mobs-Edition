package com.greatorator.tolkienmobs.entity.special;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NazgulEntity extends MonsterEntity {
    private final ServerBossEvent nazgulEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public NazgulEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.925F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.ARMOR, 25.0D);
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance instance) {
        super.populateDefaultEquipmentSlots(instance);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(TolkienItems.SWORD_MORGULIRON.get()));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    /** Sounds */
    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundIdleWitchKing.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TolkienSounds.soundHurtWitchKing.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundHurtWitchKing.get();
    }

    /** Special Attack */
    @Override
    public boolean doHurtTarget(Entity entityIn) {
        long time = System.currentTimeMillis();
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof Player) {
                if (time > nextAbilityUse) {
                    nextAbilityUse = time + coolDown;
                    Player player = (Player) entityIn;
                    BlockPos blockpos = player.blockPosition();
                    int strength = 2;
                    level.playSound(null, blockpos, TolkienSounds.soundIdleWitchKing.get(), SoundSource.HOSTILE, 1.0F + level.random.nextFloat(), level.random.nextFloat() * 0.7F + 0.3F);
                    player.addEffect(new MobEffectInstance(TolkienPotions.PARALYSING_FEAR.get(), strength * 20, 0));
                }
            }
        }
        return true;
    }

    /** Boss Section */
//    public int getInvulnerableTicks() {
//        return this.entityData.get(DATA_ID_INV);
//    }

//    public void setInvulnerableTicks(int invulnerableTicks) {
//        this.entityData.set(DATA_ID_INV, invulnerableTicks);
//    }

    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
//        tag.putInt("Invul", this.getInvulnerableTicks());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
//        this.setInvulnerableTicks(tag.getInt("Invul"));
        if (this.hasCustomName()) {
            this.nazgulEvent.setName(this.getDisplayName());
        }
        reassessWeaponGoal();
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        this.nazgulEvent.setName(this.getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
//        if (this.getInvulnerableTicks() > 0) {
//            int k1 = this.getInvulnerableTicks() - 1;
//            this.balrogEvent.setProgress(1.0F - (float) k1 / 220.0F);
//        }
        this.nazgulEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

//    public void makeInvulnerable() {
//        this.setInvulnerableTicks(220);
//        this.balrogEvent.setProgress(0.0F);
//        this.setHealth(this.getMaxHealth() / 3.0F);
//    }

    @Override
    public void startSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.nazgulEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.nazgulEvent.removePlayer(serverPlayer);
    }
}