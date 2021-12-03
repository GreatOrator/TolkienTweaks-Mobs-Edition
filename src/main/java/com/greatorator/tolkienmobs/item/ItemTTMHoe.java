package com.greatorator.tolkienmobs.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.ForgeEventFactory;

public class ItemTTMHoe extends HoeItem {
    public ItemTTMHoe(IItemTier p_i231595_1_, int p_i231595_2_, float p_i231595_3_, Properties p_i231595_4_) {
        super(p_i231595_1_, p_i231595_2_, p_i231595_3_, p_i231595_4_);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType useOn (ItemUseContext itemUse){
        World world = itemUse.getLevel();
        BlockPos blockpos = itemUse.getClickedPos();
//        int radius = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), itemUse.getItemInHand());
        int radius = 4;
        int hook = ForgeEventFactory.onHoeUse(itemUse);
        if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        if (itemUse.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
            BlockState blockstate = world.getBlockState(blockpos).getToolModifiedState(world, blockpos, itemUse.getPlayer(), itemUse.getItemInHand(), ToolType.HOE);
            if (blockstate != null) {
                PlayerEntity playerentity = itemUse.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClientSide && playerentity != null) {
                    itemUse.getItemInHand().hurtAndBreak(radius, playerentity, (player) -> player.broadcastBreakEvent(itemUse.getHand()));
                }
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos targetPos = new BlockPos(blockpos.getX() + x, blockpos.getY(), blockpos.getZ() + z);
                        if (world.isEmptyBlock(targetPos.above())) {
                            BlockState blockState = world.getBlockState(targetPos).getToolModifiedState(world, targetPos, itemUse.getPlayer(), itemUse.getItemInHand(), ToolType.HOE);
                            if (blockState != null) {
                                if (!world.isClientSide) {
                                    world.setBlock(targetPos, blockState, 11);
                                }
                            }
                        }
                    }
                }
                return ActionResultType.sidedSuccess(world.isClientSide);
            }
        }
        return ActionResultType.PASS;
    }
}
