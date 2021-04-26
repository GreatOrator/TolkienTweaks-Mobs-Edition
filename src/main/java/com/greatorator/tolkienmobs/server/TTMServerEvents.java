package com.greatorator.tolkienmobs.server;

import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class TTMServerEvents {

    public static void livingUpdate (LivingEvent.LivingUpdateEvent living){
        LivingEntity entity = living.getEntityLiving();
        BlockPos pos = entity.blockPosition();
        World worldIn = living.getEntity().level;

        //region/*---------------- Balrog's Mark -----------------*/
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.BALROG_MARK.get(), entity);

        if (entity.isOnGround() && level > 0) {
            BlockState blockstate = Blocks.MAGMA_BLOCK.defaultBlockState();
            float f = (float)Math.min(16, 2 + level);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset((double)(-f), -1.0D, (double)(-f)), pos.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerThan(entity.position(), (double)f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir(worldIn, blockpos$mutable)) {
                        BlockState blockstate2 = worldIn.getBlockState(blockpos);
                        if (blockstate2.getBlock() == Blocks.GRASS_BLOCK || blockstate2.getBlock() == Blocks.DIRT && worldIn.isUnobstructed(blockstate, blockpos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(entity, net.minecraftforge.common.util.BlockSnapshot.create(worldIn.dimension(), worldIn, blockpos), Direction.DOWN)) {
                            worldIn.setBlockAndUpdate(blockpos, blockstate);
                            worldIn.getBlockTicks().scheduleTick(blockpos, Blocks.MAGMA_BLOCK, MathHelper.nextInt(entity.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
        //endregion
        //region/*---------------- Hobbit Plow -----------------*/

        //endregion
    }
}
