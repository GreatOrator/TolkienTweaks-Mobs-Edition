//package com.greatorator.tolkienmobs.handler.interfaces;
//
//import com.greatorator.tolkienmobs.block.CamoFluidBlock;
//import com.greatorator.tolkienmobs.entity.tile.CamoFluidTile;
//import com.greatorator.tolkienmobs.handler.DefaultCamoFluidBehavior;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.fluid.FlowingFluid;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.item.BucketItem;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.World;
//
//public interface IFluidHelper {
//    IFluidHelper NOOP = (fluidSource, stack) -> stack;
//
//    ItemStack dispense(IFluidSource fluidSource, ItemStack stack);
//
//    static void bootStrap() {
//        IFluidHelper ifluidbehavior1 = new DefaultCamoFluidBehavior() {
//            private final DefaultCamoFluidBehavior defaultDispenseItemBehavior = new DefaultCamoFluidBehavior();
//
//            @Override
//            public ItemStack execute(IFluidSource fluidSource, ItemStack stack) {
//                BucketItem bucketitem = (BucketItem)stack.getItem();
//                BlockPos blockpos = fluidSource.getPos().relative(fluidSource.getBlockState().getValue(CamoFluidBlock.FACING));
//                World world = fluidSource.getLevel();
//                if (bucketitem.emptyBucket((PlayerEntity)null, world, blockpos, (BlockRayTraceResult)null)) {
//                    bucketitem.checkExtraContent(world, stack, blockpos);
//                    return new ItemStack(Items.BUCKET);
//                } else {
//                    return this.defaultDispenseItemBehavior.dispense(fluidSource, stack);
//                }
//            }
//        };
//        CamoFluidBlock.registerBehavior(Items.LAVA_BUCKET, ifluidbehavior1);
//        CamoFluidBlock.registerBehavior(Items.WATER_BUCKET, ifluidbehavior1);
//    }
//}
