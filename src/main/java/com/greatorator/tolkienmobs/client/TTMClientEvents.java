package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by brandon3055 on 20/02/19.
 */
public class TTMClientEvents {

    public static List<UUID> playersWithUphillStep = new ArrayList<>();
    public static List<UUID> playersWithExtraHealth = new ArrayList<>();
    public static List<UUID> playersWithHardStance = new ArrayList<>();
    public static List<UUID> playersCoweringInFear = new ArrayList<>();
    public static List<UUID> playersWayTooTired = new ArrayList<>();

    public static void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        PlayerEntity player = Minecraft.getInstance().player;
        Entity render = Minecraft.getInstance().getCameraEntity();

        //region/*---------------- Elvish Nimbleness -----------------*/
        if (entity.level.isClientSide) {
            boolean highStepListed = playersWithUphillStep.contains(entity.getUUID()) && entity.maxUpStep >= 1f;
            boolean hasHighStep = entity.getEffect(PotionGenerator.ELF_NIMBLENESS.get()) != null;

            if (hasHighStep && !highStepListed) {
                playersWithUphillStep.add(entity.getUUID());
                entity.maxUpStep = 1.0625f;
            }

            if (!hasHighStep && highStepListed) {
                playersWithUphillStep.remove(entity.getUUID());
                entity.maxUpStep = 0.6F;
            }
        }
        //endregion
        //region/*---------------- Elvish Longevity -----------------*/
        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.ELVEN_LONGEVITY.get(), entity);
            float absorb = (float) (10 * level);
            boolean extraHealthListed = playersWithExtraHealth.contains(entity.getUUID());
            boolean hasExtraHealth = level > 0;

            if (hasExtraHealth && entity.getAbsorptionAmount() == 0 || !extraHealthListed) {
                playersWithExtraHealth.add(entity.getUUID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (absorb));
            }

            if (!hasExtraHealth && extraHealthListed) {
                playersWithExtraHealth.add(entity.getUUID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (absorb));
            }
        }
        //endregion
        //region/*---------------- Gondor Resolve -----------------*/
        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.GONDOR_RESOLVE.get(), entity);
            ModifiableAttributeInstance battleResolve = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
            AttributeModifier gondorResolve = new AttributeModifier(UUID.randomUUID(), "GondorResolve", 0.25D * level, AttributeModifier.Operation.ADDITION);
            boolean hardStanceListed = playersWithHardStance.contains(entity.getUUID());
            boolean hasHardStance = level > 0;

            if (hasHardStance && !hardStanceListed) {
                playersWithHardStance.add(entity.getUUID());
                assert battleResolve != null;
                battleResolve.addPermanentModifier(gondorResolve);
            }

            if (!hasHardStance && hardStanceListed) {
                playersWithHardStance.remove(entity.getUUID());
                assert battleResolve != null;
                battleResolve.removeModifier(gondorResolve);
            }
        }
        //endregion
        //region/*---------------- Dwarven Endurance -----------------*/
        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.DWARF_ENDURANCE.get(), entity);

            if (entity instanceof PlayerEntity && level != 0) {
                ((PlayerEntity) entity).getFoodData().eat(level + 1, 1.0F);
            }
        }
        //endregion
        //region/*---------------- Sleepnesia -----------------*/
        boolean goneToSleepListed = playersWayTooTired.contains(entity.getUUID());
        boolean hasGoneToSleep = entity.getEffect(PotionGenerator.SLEEPNESIA.get()) != null;
        ModifiableAttributeInstance nightyNight = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeModifier sleepTight = new AttributeModifier(UUID.randomUUID(), "SleepyTime", -10F, AttributeModifier.Operation.ADDITION);

        if (entity.level.isClientSide) {
            if (player != null) {
                if (hasGoneToSleep && !goneToSleepListed) {
                    playersWayTooTired.add(entity.getUUID());
                    player.setForcedPose(Pose.SLEEPING);
                    nightyNight.addPermanentModifier(sleepTight);
                }

                if (!hasGoneToSleep && goneToSleepListed) {
                    playersWayTooTired.add(entity.getUUID());
                    player.setForcedPose(null);
                    nightyNight.removeModifier(sleepTight);
                }
            }
            //endregion
        }
    }
}