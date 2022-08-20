package com.greatorator.tolkienmobs.item;

import com.greatorator.tolkienmobs.client.gui.GuiTTMMirkwoodSignScreen;
import com.greatorator.tolkienmobs.entity.tile.TTMMirkwoodSignTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TTMMirkwoodSignItem extends WallOrFloorItem {
   public TTMMirkwoodSignItem(Properties properties, Block signBlock, Block wallSignBlock) {
      super(signBlock, wallSignBlock, properties);
   }

   @Override
   protected boolean updateCustomBlockEntityTag(BlockPos blockPos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState blockState) {
      boolean flag = super.updateCustomBlockEntityTag(blockPos, world, player, stack, blockState);
      if (!flag && player != null) {
         if (world.isClientSide) {
            openGui((TTMMirkwoodSignTile)world.getBlockEntity(blockPos));
         }else {
            TileEntity tile = world.getBlockEntity(blockPos);
            if (tile instanceof TTMMirkwoodSignTile) {
               ((TTMMirkwoodSignTile) tile).setAllowedPlayerEditor(player);
            }
         }
      }


      return flag;
   }

   @OnlyIn(Dist.CLIENT)
   private void openGui(TTMMirkwoodSignTile tile) {
      Minecraft.getInstance().setScreen(new GuiTTMMirkwoodSignScreen(tile));
   }
}