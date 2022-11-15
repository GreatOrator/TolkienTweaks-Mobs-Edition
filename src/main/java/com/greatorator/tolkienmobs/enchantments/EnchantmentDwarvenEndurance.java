package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, value = Dist.CLIENT)
public class EnchantmentDwarvenEndurance extends Enchantment {
    public EnchantmentDwarvenEndurance(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_HEAD, slots);
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
        return super.checkCompatibility(enchantment);
    }

    @SubscribeEvent
    public static void dwarfEndurance(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(TolkienEnchants.DWARF_ENDURANCE.get(), entity);
            int food = (level * 5);
            float saturation = (float) (level * 5);
            if (entity instanceof Player && level != 0) {
                Player player = (Player) event.getEntity();
                if (player.getFoodData().needsFood()) {
                    Level world = entity.getLevel();
                    world.playSound((Player)null, entity, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.3F, 0.5F);
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + food);
                    player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() + saturation);
                }
            }
        }
    }
}
