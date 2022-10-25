package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.util.Direction.UP;

public class LockableTreasureChestTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public static final ContainerSlotLayout.LayoutFactory<LockableTreasureChestTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.mainInventory);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(27);
    public final ManagedString keyCode = register(new ManagedString("KeyCode", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));

    public LockableTreasureChestTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public LockableTreasureChestTile() {
        super(TTMContent.LOCKABLE_TREASURE_CHEST_TILE.get());

        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(mainInventory).setInsertCheck((slot, stack) -> slot > 0), UP, null);
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth().syncTile();
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide()) {
            openGUI(playerEntity, this, worldPosition);
        }
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerBCTile<>(TTMContent.LOCKABLE_TREASURE_CHEST_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
    }
}
