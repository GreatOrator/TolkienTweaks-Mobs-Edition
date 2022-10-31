package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.container.gui.TolkienSignScreen;
import com.greatorator.tolkienmobs.entity.tile.TolkienSignTile;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TolkienSignItem extends StandingAndWallBlockItem {
   public TolkienSignItem(Item.Properties properties, Block signBlock, Block wallSignBlock) {
      super(signBlock, wallSignBlock, properties);
   }

   @Override
   protected boolean updateCustomBlockEntityTag(BlockPos blockPos, Level world, @Nullable Player player, ItemStack stack, BlockState blockState) {
      boolean flag = super.updateCustomBlockEntityTag(blockPos, world, player, stack, blockState);
      if (!flag && player != null) {
         if (world.isClientSide) {
            openGui((TolkienSignTile) world.getBlockEntity(blockPos));
         }else {
            BlockEntity tile = world.getBlockEntity(blockPos);
            if (tile instanceof TolkienSignTile) {
               ((TolkienSignTile) tile).setAllowedPlayerEditor(player);
            }
         }
      }
      return flag;
   }

   @OnlyIn(Dist.CLIENT)
   private void openGui(TolkienSignTile tile) {
      Minecraft.getInstance().setScreen(new TolkienSignScreen(tile, true));
   }
}