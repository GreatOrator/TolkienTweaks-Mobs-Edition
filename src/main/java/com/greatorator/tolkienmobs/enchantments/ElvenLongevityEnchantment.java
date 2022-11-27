package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.event.entity.WorldEvents;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, value = Dist.CLIENT)
public class ElvenLongevityEnchantment extends BaseEnchantment {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    private static final UUID ELVEN_LONGEVITY_ID = UUID.fromString("a038f128-6432-11ed-81ce-0242ac120002");
    protected static final String ELVEN_LONGEVITY = "ElvenLongevity";
    public static Set<UUID> EXTRA_HEALTH = new HashSet<>();

    public ElvenLongevityEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_CHEST, slots);
    }

    @Override
    public boolean isEnabled() {
        return TolkienConfig.disableElvenLongevity;
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
        return isEnabled() && (stack.getItem() instanceof ArmorItem) && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlot.CHEST;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
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
        int level = EnchantmentHelper.getEnchantmentLevel(TolkienEnchants.ELVEN_LONGEVITY.get(), entity);
        float absorb = (float) (10 * level);
        AttributeModifier healthModifier = new AttributeModifier(ELVEN_LONGEVITY_ID, ELVEN_LONGEVITY, absorb, AttributeModifier.Operation.ADDITION);
        AttributeModifier baseModifier = entity.getAttribute(Attributes.MAX_HEALTH).getModifier(WorldEvents.PLAYER_BASE_HEALTH);
        boolean extraHealth = EXTRA_HEALTH.contains(entity.getUUID());
        boolean hasExtraHealth = level > 0;

        if (entity.level.isClientSide) {
            if (hasExtraHealth && !extraHealth) {
                EXTRA_HEALTH.add(entity.getUUID());
                entity.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthModifier);
                entity.heal(6.0F);
            }else if (!hasExtraHealth && extraHealth){
                EXTRA_HEALTH.remove(entity.getUUID());
                entity.getAttribute(Attributes.MAX_HEALTH).removeModifier(healthModifier);
                entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(baseModifier.getAmount() - (healthModifier.getAmount() + 20));
            }
        }
    }
}