package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, value = Dist.CLIENT)
public class DwarvenEnduranceEnchantment extends BaseEnchantment {
    public DwarvenEnduranceEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_HEAD, slots);
    }

    @Override
    public boolean isEnabled() {
        return TolkienConfig.disableDwarvenEndurance;
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
        return isEnabled() && (stack.getItem() instanceof ArmorItem) && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlot.HEAD;
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
                    player.getFoodData().eat(food, saturation);
                    world.gameEvent(player, GameEvent.EAT, player.eyeBlockPosition());
                    player.gameEvent(GameEvent.EAT);
                }
            }
        }
    }
}
