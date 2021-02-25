package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

    public static void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        PlayerEntity player = Minecraft.getInstance().player;
        Entity render = Minecraft.getInstance().getRenderViewEntity();

        //region/*---------------- Elvish Nimbleness -----------------*/
        if (entity.world.isRemote) {
            boolean highStepListed = playersWithUphillStep.contains(entity.getUniqueID()) && entity.stepHeight >= 1f;
            boolean hasHighStep = entity.getActivePotionEffect(PotionGenerator.ELF_NIMBLENESS.get()) != null;

            if (hasHighStep && !highStepListed) {
                playersWithUphillStep.add(entity.getUniqueID());
                entity.stepHeight = 1.0625f;
            }

            if (!hasHighStep && highStepListed) {
                playersWithUphillStep.remove(entity.getUniqueID());
                entity.stepHeight = 0.6F;
            }
        }
        //endregion
        //region/*---------------- Elvish Longevity -----------------*/
        if (entity.world.isRemote) {
            int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentGenerator.ELVEN_LONGEVITY.get(), entity);
            float absorb = (float)(10 * level);
            boolean extraHealthListed = playersWithExtraHealth.contains(entity.getUniqueID());
            boolean hasExtraHealth = level > 0;

            if (hasExtraHealth && entity.getAbsorptionAmount() == 0 || !extraHealthListed) {
                playersWithExtraHealth.add(entity.getUniqueID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (absorb));
            }

            if (!hasExtraHealth && extraHealthListed) {
                playersWithExtraHealth.add(entity.getUniqueID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (absorb));
            }
        }
        //endregion
        //region/*---------------- Gondor Resolve -----------------*/
        if (entity.world.isRemote) {
            int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentGenerator.GONDOR_RESOLVE.get(), entity);
            ModifiableAttributeInstance battleResolve = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
            AttributeModifier gondorResolve = new AttributeModifier(UUID.randomUUID(), "GondorResolve", 0.25D * level, AttributeModifier.Operation.ADDITION);
            boolean hardStanceListed = playersWithHardStance.contains(entity.getUniqueID());
            boolean hasHardStance = level > 0;

            if (hasHardStance && !hardStanceListed) {
                playersWithHardStance.add(entity.getUniqueID());
                assert battleResolve != null;
                battleResolve.applyPersistentModifier(gondorResolve);
            }

            if (!hasHardStance && hardStanceListed) {
                playersWithHardStance.remove(entity.getUniqueID());
                assert battleResolve != null;
                battleResolve.removeModifier(gondorResolve);
            }
        }
        //endregion
        //region/*---------------- Dwarven Endurance -----------------*/
        if (entity.world.isRemote) {
            int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentGenerator.DWARF_ENDURANCE.get(), entity);

            if (entity instanceof PlayerEntity && level != 0) {
                ((PlayerEntity) entity).getFoodStats().addStats(level + 1, 1.0F);
            }
        }
    }
}