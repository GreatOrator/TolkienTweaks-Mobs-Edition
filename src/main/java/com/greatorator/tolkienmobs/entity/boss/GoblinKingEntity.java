package com.greatorator.tolkienmobs.entity.boss;

import codechicken.lib.math.MathHelper;
import com.greatorator.tolkienmobs.entity.BossEntity;
import com.greatorator.tolkienmobs.entity.monster.GoblinEntity;
import com.greatorator.tolkienmobs.event.entity.GoblinEvent;
import com.greatorator.tolkienmobs.event.server.ServerEvents;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraftforge.eventbus.api.Event;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Objects;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class GoblinKingEntity extends BossEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public GoblinKingEntity(EntityType<? extends BossEntity> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 100.0D);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(GoblinEntity.class));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float distanceFactor) {
        Snowball snowballentity = new Snowball(this.level, this);
        double d0 = entity.getEyeY() - (double)1.1F;
        double d1 = entity.getX() - this.getX();
        double d2 = d0 - snowballentity.getY();
        double d3 = entity.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(snowballentity);
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.PINK;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!super.hurt(source, amount)) {
            return false;
        } else if (!(this.level instanceof ServerLevel serverworld)) {
            return false;
        } else {
            LivingEntity livingentity = this.getTarget();
            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
                livingentity = (LivingEntity) source.getEntity();
            }

            if (livingentity == null) return true;

            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(MobEffects.WATER_BREATHING)) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.nodrown").withStyle(ChatFormatting.DARK_BLUE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.onfire").withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.healself").withStyle(ChatFormatting.LIGHT_PURPLE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(MobEffects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.speedup").withStyle(ChatFormatting.AQUA), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2 * 20, 0));
            }

            int xPos = Mth.floor(this.getX());
            int yPos = Mth.floor(this.getY());
            int zPos = Mth.floor(this.getZ());

            GoblinEvent.SummonAidEvent event = ServerEvents.fireGoblinSummonAid(this, level, xPos, yPos, zPos, livingentity, this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue());

            if (event.getResult() == Event.Result.DENY) {

                if (livingentity instanceof Player) {
                    livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.nohelp").withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                }
                return true;
            }
            if (event.getResult() == Event.Result.ALLOW || (double) this.random.nextFloat() < this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {

                if (livingentity instanceof Player) {
                    livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.helpcomming").withStyle(ChatFormatting.DARK_GREEN), Util.NIL_UUID);
                }

                GoblinEntity goblinentity = event.getCustomSummonedAid() != null && event.getResult() == Event.Result.ALLOW ? event.getCustomSummonedAid() : TolkienEntities.ENTITY_TTM_GOBLIN.get().create(this.level);

                for (int l = 0; l < 50; ++l) {
                    int spawnX = xPos + Mth.nextInt(this.random, 7, 10) * Mth.nextInt(this.random, -1, 1);
                    int spawnY = yPos + Mth.nextInt(this.random, 7, 10) * Mth.nextInt(this.random, -1, 1);
                    int spawnZ = zPos + Mth.nextInt(this.random, 7, 10) * Mth.nextInt(this.random, -1, 1);
                    BlockPos blockpos = new BlockPos(spawnX, spawnY, spawnZ);
                    EntityType<?> entitytype = goblinentity.getType();
                    SpawnPlacements.Type entityspawnplacementregistry$placementtype = SpawnPlacements.getPlacementType(entitytype);
                    if (NaturalSpawner.isSpawnPositionOk(entityspawnplacementregistry$placementtype, this.level, blockpos, entitytype) && SpawnPlacements.checkSpawnRules(entitytype, serverworld, MobSpawnType.REINFORCEMENT, blockpos, this.level.random)) {
                        goblinentity.setPos((double) spawnX, (double) spawnY, (double) spawnZ);
                        if (!this.level.hasNearbyAlivePlayer((double) spawnX, (double) spawnY, (double) spawnZ, 7.0D) && this.level.isUnobstructed(goblinentity) && this.level.noCollision(goblinentity) && !this.level.containsAnyLiquid(goblinentity.getBoundingBox())) {
                            goblinentity.setTarget(livingentity);
                            goblinentity.finalizeSpawn(serverworld, this.level.getCurrentDifficultyAt(goblinentity.blockPosition()), MobSpawnType.REINFORCEMENT, (SpawnGroupData) null, (CompoundTag) null);
                            serverworld.addFreshEntityWithPassengers(goblinentity);
                            Objects.requireNonNull(this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE)).addPermanentModifier(new AttributeModifier("Goblin reinforcement caller charge", (double) -0.05F, AttributeModifier.Operation.ADDITION));
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if(!event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }else if (this.swinging && !this.getRanged()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            return PlayState.CONTINUE;
        }else if (this.isAggressive() && this.getRanged()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("ranged", false));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Sounds */
    @Override
    protected SoundEvent getAmbientSound()
    {
        return TolkienSounds.soundIdleGoblin.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return TolkienSounds.soundHurtGoblin.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return TolkienSounds.soundDeathGoblin.get();
    }
}