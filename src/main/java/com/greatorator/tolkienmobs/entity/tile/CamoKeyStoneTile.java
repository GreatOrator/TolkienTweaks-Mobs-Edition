package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.DataFlags;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;

public class CamoKeyStoneTile extends TileBCore implements IRSSwitchable, IInteractTile, INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public final ManagedString keyCode = register(new ManagedString("KeyCode", "Set Code", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool keyConsume = register(new ManagedBool("consume_key", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool rsAlways = register(new ManagedBool("redstone_toggle", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool rsPulse = register(new ManagedBool("redstone_pulse", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool rsDelay = register(new ManagedBool("redstone_delay", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedInt tickDelay = register(new ManagedInt("tick_delay", 100, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedInt timeActive = register(new ManagedInt("time_active", 30, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));

    public CamoKeyStoneTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public CamoKeyStoneTile() {
        super(TTMContent.KEY_STONE_TILE.get());
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        openGUI(playerEntity, this, worldPosition);
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
        return new ContainerBCTile<>(TTMContent.KEY_STONE_CONTAINER, windowID, playerInventory, this);
    }

    @Override
    public void tick() {
        super.tick();
    }
}