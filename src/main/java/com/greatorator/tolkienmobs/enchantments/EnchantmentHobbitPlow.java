package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class EnchantmentHobbitPlow extends Enchantment {
    public EnchantmentHobbitPlow(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 10 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof HoeItem ? true : super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof HoeItem ? true : super.canEnchant(stack);
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @SubscribeEvent
    public static void hobbitPlow(PlayerInteractEvent.RightClickBlock event) {
        ItemStack holding = event.getItemStack();
        Player player = event.getPlayer();
        if (holding.isEmpty() || event.getWorld().isClientSide()) return;

        Level world = event.getWorld();
        BlockPos blockPos = event.getPos();

        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(TolkienEnchants.HOBBIT_PLOW.get(), holding);

        if (enchantmentLevel > 0 && !world.isClientSide()) {
            Block targetBlock = event.getWorld().getBlockState(blockPos).getBlock();
            if (targetBlock == Blocks.GRASS_BLOCK || targetBlock == Blocks.DIRT || targetBlock == Blocks.PODZOL) {
                event.setCanceled(true);
                world.setBlockAndUpdate(blockPos, Blocks.WATER.defaultBlockState());

                if (!player.isCreative()) {
                    player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak((enchantmentLevel * 2) + 1, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                }

                for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                    for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                        BlockPos targetPos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                        if (world.isEmptyBlock(targetPos.above()) && world.getBlockState(targetPos).getMaterial().blocksMotion()) {
                            world.setBlockAndUpdate(targetPos, Blocks.FARMLAND.defaultBlockState());
                            world.playSound(player, blockPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}