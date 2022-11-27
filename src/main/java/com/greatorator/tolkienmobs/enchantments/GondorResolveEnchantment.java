package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, value = Dist.CLIENT)
public class GondorResolveEnchantment extends BaseEnchantment {
    public static Set<UUID> playersWithHardStance = new HashSet<>();
    private static final AttributeModifier gondorResolve = new AttributeModifier(UUID.randomUUID(), "GondorResolve", 0.25D, AttributeModifier.Operation.ADDITION);

    public GondorResolveEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_LEGS, slots);
    }

    @Override
    public boolean isEnabled() {
        return TolkienConfig.disableGondorResolve;
    }

    @Override
    public boolean isTradeable() {
        return isEnabled() && super.isTradeable();
    }

    @Override
    public boolean isDiscoverable() {
        return isEnabled() && super.isDiscoverable();
    }

    @Override
    public boolean isAllowedOnBooks() {
        return isEnabled() && super.isAllowedOnBooks();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return isEnabled() && (stack.getItem() instanceof ArmorItem) && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlot.LEGS;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.THORNS && enchantment != Enchantments.PROJECTILE_PROTECTION;
    }

    @SubscribeEvent
    public static void gondorResolve(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(TolkienEnchants.GONDOR_RESOLVE.get(), entity);
            AttributeInstance battleResolve = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
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
    }
}