package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
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

public class EntityTTMVillager extends VillagerEntity {
    private int texture_index;
    private int rndMax;
    private int rndMin;

    public EntityTTMVillager(EntityType<? extends EntityTTMVillager> type, World worldIn) {
        super(type, worldIn);
        this.texture_index = TTMRand.getRandomInteger(rndMax, rndMin);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.INVISIBILITY), SoundEvents.ENTITY_WANDERING_TRADER_DISAPPEARED, (p_213733_1_) -> {
            return !this.world.isDaytime() && !p_213733_1_.isInvisible();
        }));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, new ItemStack(Items.MILK_BUCKET), SoundEvents.ENTITY_WANDERING_TRADER_REAPPEARED, (p_213736_1_) -> {
            return this.world.isDaytime() && p_213736_1_.isInvisible();
        }));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void onVillagerTrade(MerchantOffer offer) {
        if (offer.getDoesRewardExp()) {
            int i = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
        }
    }

    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.hasCustomer() && !this.isSleeping() && !player.isSecondaryUseActive()) {
            if (this.isChild()) {
                this.shakeHead();
                return ActionResultType.func_233537_a_(this.world.isRemote);
            } else {
                boolean flag = this.getOffers().isEmpty();
                if (hand == Hand.MAIN_HAND) {
                    if (flag && !this.world.isRemote) {
                        this.shakeHead();
                    }

                    player.addStat(Stats.TALKED_TO_VILLAGER);
                }

                if (flag) {
                    return ActionResultType.func_233537_a_(this.world.isRemote);
                } else {
                    if (!this.world.isRemote && !this.offers.isEmpty()) {
                        this.displayMerchantGui(player);
                    }

                    return ActionResultType.func_233537_a_(this.world.isRemote);
                }
            }
        } else {
            return super.func_230254_b_(player, hand);
        }
    }

    private void shakeHead() {
        this.setShakeHeadTicks(40);
        if (!this.world.isRemote()) {
            this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
        }

    }

    private void displayMerchantGui(PlayerEntity player) {
        this.recalculateSpecialPricesFor(player);
        this.setCustomer(player);
        this.openMerchantContainer(player, this.getDisplayName(), this.getVillagerData().getLevel());
    }

    private void recalculateSpecialPricesFor(PlayerEntity playerIn) {
        int i = this.getPlayerReputation(playerIn);
        if (i != 0) {
            for(MerchantOffer merchantoffer : this.getOffers()) {
                merchantoffer.increaseSpecialPrice(-MathHelper.floor((float)i * merchantoffer.getPriceMultiplier()));
            }
        }

        if (playerIn.isPotionActive(Effects.HERO_OF_THE_VILLAGE)) {
            EffectInstance effectinstance = playerIn.getActivePotionEffect(Effects.HERO_OF_THE_VILLAGE);
            int k = effectinstance.getAmplifier();

            for(MerchantOffer merchantoffer1 : this.getOffers()) {
                double d0 = 0.3D + 0.0625D * (double)k;
                int j = (int)Math.floor(d0 * (double)merchantoffer1.getBuyingStackFirst().getCount());
                merchantoffer1.increaseSpecialPrice(-Math.max(j, 1));
            }
        }

    }
}