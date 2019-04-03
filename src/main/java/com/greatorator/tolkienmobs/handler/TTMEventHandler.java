package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.init.EnchantmentsInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
public class TTMEventHandler {
    @SubscribeEvent(priority= EventPriority.HIGHEST, receiveCanceled=true)
    public void onEvent(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.BALROG_MARK, living);
        BlockPos pos = living.getPosition();
        World worldIn = event.getEntity().world;

        if (living.onGround && living instanceof EntityPlayer && EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.BALROG_MARK, living) !=0)
        {
            float f = (float)Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f)))
            {
                if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * f))
                {
                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.AIR)
                    {
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);

                        if (iblockstate1.getBlock() == Blocks.GRASS || iblockstate1.getBlock() == Blocks.DIRT && worldIn.mayPlace(Blocks.MAGMA, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null))
                        {
                            worldIn.setBlockState(blockpos$mutableblockpos1, Blocks.MAGMA.getDefaultState());
                            worldIn.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Blocks.MAGMA, MathHelper.getInt(living.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
    }
}
