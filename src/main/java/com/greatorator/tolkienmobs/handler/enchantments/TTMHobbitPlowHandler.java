package com.greatorator.tolkienmobs.handler.enchantments;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TTMHobbitPlowHandler {

//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void hobbitPlow(BlockEvent.BreakEvent event) {
//        PlayerEntity player = event.getPlayer();
//        if (!(player instanceof ServerPlayerEntity)) {
//            return;
//        }
//    }
//
//    public ActionResultType useOn (ItemUseContext itemUse){
//        World world = itemUse.getLevel();
//        BlockPos blockpos = itemUse.getClickedPos();
//        int hook = ForgeEventFactory.onHoeUse(itemUse);
//        if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
//        if (itemUse.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
//            BlockState blockstate = world.getBlockState(blockpos).getToolModifiedState(world, blockpos, itemUse.getPlayer(), itemUse.getItemInHand(), ToolType.HOE);
//            if (blockstate != null) {
//                PlayerEntity playerentity = itemUse.getPlayer();
//                world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                if (!world.isClientSide) {
//                    for(int i = -1; i < 2; ++i) {
//                        for(int j = -1; j < 2; ++j) {
//                            world.setBlock(x + i, y, z + j, blockstate, 11);
//                            return true;
//                        }
//                    }                    if (playerentity != null) {
//                        itemUse.getItemInHand().hurtAndBreak(1, playerentity, (player) -> {
//                            player.broadcastBreakEvent(itemUse.getHand());
//                        });
//                    }
//                }
//
//                return ActionResultType.sidedSuccess(world.isClientSide);
//            }
//        }
//
//        return ActionResultType.PASS;
//    }
}
