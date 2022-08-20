package com.greatorator.tolkienmobs.item;

import com.greatorator.tolkienmobs.client.gui.TTMEditSignScreen;
import com.greatorator.tolkienmobs.entity.tile.TTMSignBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TTMSignItem extends WallOrFloorItem {
   public TTMSignItem(Item.Properties properties, Block signBlock, Block wallSignBlock) {
      super(signBlock, wallSignBlock, properties);
   }

   @Override
   protected boolean updateCustomBlockEntityTag(BlockPos blockPos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState blockState) {
      boolean flag = super.updateCustomBlockEntityTag(blockPos, world, player, stack, blockState);
      if (!flag && player != null) {
         if (world.isClientSide) {
            openGui((TTMSignBlockEntity)world.getBlockEntity(blockPos));
         }else {
            TileEntity tile = world.getBlockEntity(blockPos);
            if (tile instanceof TTMSignBlockEntity) {
               ((TTMSignBlockEntity) tile).setAllowedPlayerEditor(player);
            }
         }
      }


      return flag;
   }

   @OnlyIn(Dist.CLIENT)
   private void openGui(TTMSignBlockEntity tile) {
      Minecraft.getInstance().setScreen(new TTMEditSignScreen(tile));
   }
}