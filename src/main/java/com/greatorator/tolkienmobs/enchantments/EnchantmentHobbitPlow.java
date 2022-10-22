package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class EnchantmentHobbitPlow extends Enchantment {
    public EnchantmentHobbitPlow(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
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
        if(stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE))
        {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if(stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE))
        {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
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
    public static void hobbitPlow(BlockEvent.BlockToolInteractEvent event) {
        PlayerEntity player = event.getPlayer();
        World world = (World) event.getWorld();
        BlockPos blockPos = event.getPos();
        ItemStack holding = event.getHeldItemStack();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), holding);

        if (holding.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), holding) > 0 && !world.isClientSide()) {
            Block targetBlock = event.getWorld().getBlockState(blockPos).getBlock();
            if (targetBlock == Blocks.GRASS_BLOCK || targetBlock == Blocks.DIRT || targetBlock == Blocks.PODZOL) {
                event.setCanceled(true);
                world.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                world.setBlockAndUpdate(blockPos, Fluids.WATER.defaultFluidState().createLegacyBlock());

                if (!player.isCreative()) {
                    player.getItemInHand(Hand.MAIN_HAND).hurtAndBreak((enchantmentLevel * 2) + 1, player, entity -> entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
                }

                for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                    for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                        BlockPos targetPos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                        if (world.isEmptyBlock(targetPos.above()) && world.getBlockState(targetPos).getMaterial().blocksMotion()) {
                        world.setBlockAndUpdate(targetPos, Blocks.FARMLAND.defaultBlockState());
                        world.playSound(player, blockPos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}