package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

import static net.minecraft.core.Direction.UP;

public class MithrilBarrelTile extends TileBCore implements MenuProvider, IInteractTile, IRSSwitchable {
   public static final ContainerSlotLayout.LayoutFactory<MithrilBarrelTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
   public TileItemStackHandler itemHandler = new TileItemStackHandler(54);

   public MithrilBarrelTile(BlockPos pos, BlockState state) {
      super(TolkienTiles.BARREL_MITHRIL_TILE.get(), pos, state);
      //Exposed item capability by also wrapping it in a handler that lets us control IO
      capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot > 0), UP, null);
      //Install item handler capability but don't expose it to other tiles.
      capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler).saveBoth().syncTile();
      //Set up slot input validation.
      itemHandler.setStackValidator((slot, stack) -> slot >= 0 && isInput(stack));
      //Add inventory change listener
      itemHandler.setContentsChangeListener(i -> inventoryChange());
   }

   private boolean isInput(ItemStack stack) {
      return true;
   }

   private void inventoryChange() {

   }

   @Nullable
   @Override
   public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
      return new ContainerBCTile<>(TolkienContainers.BARREL_MITHRIL_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
   }
}