package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import com.greatorator.tolkienmobs.utils.HarvestUtility;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class HobbitGrowthEnchantment extends BaseEnchantment {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public HobbitGrowthEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public boolean isEnabled() {
        return TolkienConfig.disableHobbitGrowth;
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

    @SubscribeEvent
    public void hobbitGrowth(LivingEvent.LivingUpdateEvent event) {
        Player p = (Player) event.getEntityLiving();
        ItemStack holding = p.getMainHandItem();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(TolkienEnchants.HOBBIT_GROWTH.get(), holding);

        LOGGER.info("Player is holding " + holding + ", with enchant level of " + enchantmentLevel + ".");

        if (isEnabled()) {
            if (enchantmentLevel > 0 && holding.isEnchanted() && EnchantmentHelper.getEnchantments(holding).containsKey(TolkienEnchants.HOBBIT_GROWTH.get()) && !holding.isEmpty()) {
                HarvestUtility.growNearbyRandomly(true, p.level, p.blockPosition(), p, enchantmentLevel);
            }
        }
    }
}