package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.vec.Vector3;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedVector3;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;

public class MilestoneTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public final ManagedBool isactive = register(new ManagedBool("is_active", false, SAVE_BOTH_SYNC_TILE));
    public final ManagedVector3 milestonePos = register(new ManagedVector3("milestone_pos", new Vector3(0, -1, 0), SAVE_BOTH_SYNC_TILE));

    public List<String> users = new ArrayList<>();

    public MilestoneTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public MilestoneTile() {
        super(TTMContent.MILESTONE_TILE.get());
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide()) {
            openGUI(playerEntity, this, worldPosition);
        }
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerBCTile<>(TTMContent.MILESTONE_CONTAINER, windowID, playerInventory, this);
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }
}