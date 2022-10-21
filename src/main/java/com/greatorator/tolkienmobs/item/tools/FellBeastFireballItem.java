package com.greatorator.tolkienmobs.item.tools;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FellBeastFireballItem extends Item {
   public FellBeastFireballItem(Properties p_i48499_1_) {
      super(p_i48499_1_);
   }

   @Override
   public ActionResultType useOn(ItemUseContext useContext) {
      World world = useContext.getLevel();
      BlockPos blockpos = useContext.getClickedPos();
      BlockState blockstate = world.getBlockState(blockpos);
      boolean flag = false;
      if (CampfireBlock.canLight(blockstate)) {
         this.playSound(world, blockpos);
         world.setBlockAndUpdate(blockpos, blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(true)));
         flag = true;
      } else {
         blockpos = blockpos.relative(useContext.getClickedFace());
         if (AbstractFireBlock.canBePlacedAt(world, blockpos, useContext.getHorizontalDirection())) {
            this.playSound(world, blockpos);
            world.setBlockAndUpdate(blockpos, AbstractFireBlock.getState(world, blockpos));
            flag = true;
         }
      }

      if (flag) {
         useContext.getItemInHand().shrink(1);
         return ActionResultType.sidedSuccess(world.isClientSide);
      } else {
         return ActionResultType.FAIL;
      }

   }

   private void playSound(World p_219995_1_, BlockPos p_219995_2_) {
      p_219995_1_.playSound((PlayerEntity)null, p_219995_2_, SoundEvents.FIREWORK_ROCKET_SHOOT, SoundCategory.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
   }
}