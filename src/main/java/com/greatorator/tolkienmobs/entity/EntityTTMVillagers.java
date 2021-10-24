package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTTMVillagers extends VillagerEntity {
    private int texture_index;
    private int rndMax;
    private int rndMin;

    public EntityTTMVillagers(EntityType<? extends EntityTTMVillagers> type, World worldIn) {
        super(type, worldIn);
        this.texture_index = TTMRand.getRandomInteger(rndMax, rndMin);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY), SoundEvents.WANDERING_TRADER_DISAPPEARED, (p_213733_1_) -> {
            return !this.level.isDay() && !p_213733_1_.isInvisible();
        }));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, new ItemStack(Items.MILK_BUCKET), SoundEvents.WANDERING_TRADER_REAPPEARED, (p_213736_1_) -> {
            return this.level.isDay() && p_213736_1_.isInvisible();
        }));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.85F;
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.5D).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.isTrading() && !this.isSleeping() && !player.isSecondaryUseActive()) {
            if (this.isBaby()) {
                this.shakeHead();
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            } else {
                boolean flag = this.getOffers().isEmpty();
                if (hand == Hand.MAIN_HAND) {
                    if (flag && !this.level.isClientSide) {
                        this.shakeHead();
                    }

                    player.awardStat(Stats.TALKED_TO_VILLAGER);
                }

                if (flag) {
                    return ActionResultType.sidedSuccess(this.level.isClientSide);
                } else {
                    if (!this.level.isClientSide && !this.offers.isEmpty()) {
                        this.displayMerchantGui(player);
                    }

                    return ActionResultType.sidedSuccess(this.level.isClientSide);
                }
            }
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void shakeHead() {
        this.setUnhappyCounter(40);
        if (!this.level.isClientSide()) {
            this.playSound(SoundEvents.VILLAGER_NO, this.getSoundVolume(), this.getVoicePitch());
        }

    }

    private void displayMerchantGui(PlayerEntity player) {
        this.recalculateSpecialPricesFor(player);
        this.setTradingPlayer(player);
        this.openTradingScreen(player, this.getDisplayName(), this.getVillagerData().getLevel());
    }

    private void recalculateSpecialPricesFor(PlayerEntity playerIn) {
        int i = this.getPlayerReputation(playerIn);
        if (i != 0) {
            for(MerchantOffer merchantoffer : this.getOffers()) {
                merchantoffer.addToSpecialPriceDiff(-MathHelper.floor((float)i * merchantoffer.getPriceMultiplier()));
            }
        }

        if (playerIn.hasEffect(Effects.HERO_OF_THE_VILLAGE)) {
            EffectInstance effectinstance = playerIn.getEffect(Effects.HERO_OF_THE_VILLAGE);
            int k = effectinstance.getAmplifier();

            for(MerchantOffer merchantoffer1 : this.getOffers()) {
                double d0 = 0.3D + 0.0625D * (double)k;
                int j = (int)Math.floor(d0 * (double)merchantoffer1.getBaseCostA().getCount());
                merchantoffer1.addToSpecialPriceDiff(-Math.max(j, 1));
            }
        }

    }
}