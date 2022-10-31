package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, value = Dist.CLIENT)
public class EnchantmentElvenLongevity extends Enchantment {
    public static Set<UUID> playersWithExtraHealth = new HashSet<>();

    public EnchantmentElvenLongevity(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_CHEST, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem() instanceof BookItem;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.THORNS && enchantment != Enchantments.PROJECTILE_PROTECTION;
    }

    @SubscribeEvent
    public static void elvenLongevity(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();

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
    }
}