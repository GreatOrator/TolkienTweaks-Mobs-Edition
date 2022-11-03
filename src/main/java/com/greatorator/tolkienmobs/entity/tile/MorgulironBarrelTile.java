package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
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
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.core.Direction.UP;

public class MorgulironBarrelTile extends TileBCore implements MenuProvider, IInteractTile, IRSSwitchable {
   public static final ContainerSlotLayout.LayoutFactory<MorgulironBarrelTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
   public TileItemStackHandler itemHandler = new TileItemStackHandler(54);
   public final ManagedBool isOpen = register(new ManagedBool("is_open", false, SAVE_BOTH_SYNC_TILE));

   public MorgulironBarrelTile(BlockPos pos, BlockState state) {
      super(TolkienTiles.BARREL_MORGULIRON_TILE.get(), pos, state);
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

   public IItemHandler getItemHandler() {
      return itemHandler;
   }

   @Nullable
   @Override
   public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
      return new ContainerBCTile<>(TolkienContainers.BARREL_MORGULIRON_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
   }
}