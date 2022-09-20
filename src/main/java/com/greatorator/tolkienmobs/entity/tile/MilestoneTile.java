package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedPos;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.MilestoneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_NBT_SYNC_TILE;
import static com.greatorator.tolkienmobs.block.MilestoneBlock.ACTIVE;

public class MilestoneTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public final ManagedBool isactive = register(new ManagedBool("is_active", false, SAVE_BOTH_SYNC_TILE));
    public final ManagedString milestoneName = register(new ManagedString("milestone_name", SAVE_BOTH_SYNC_TILE));
    public final ManagedString milestoneUUID = register(new ManagedString("milestone_uuid", SAVE_BOTH_SYNC_TILE));
    public final ManagedString milestoneDim = register(new ManagedString("dim_key", SAVE_BOTH_SYNC_TILE));
    public final ManagedPos milestonePos = register(new ManagedPos("milestone_pos", SAVE_NBT_SYNC_TILE));

    public List<String> users = new ArrayList<>();
    PlayerEntity player;
    static ITextComponent mName;
    public MilestoneTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public MilestoneTile() {
        super(TTMContent.MILESTONE_TILE.get());
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide() && playerEntity.isCreative()) {
            openGUI(playerEntity, this, worldPosition);
        }else {
            setMilestoneLocation();
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

    public void setMilestoneName() {

    }

    public void setMilestoneLocation() {
        if (Objects.equals(milestoneUUID.get(), "")) {
            milestoneUUID.set(String.valueOf(UUID.randomUUID()));
        }
        Direction facing = level.getBlockState(worldPosition).getValue(MilestoneBlock.FACING);

        if (milestonePos.get() == null) {
            milestonePos.set(worldPosition.relative(facing));
        }

        if (!isactive.get()) {
            isactive.set(true);
            Boolean activeState = isactive.get();
            level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(ACTIVE, activeState));
        }

        if (Objects.equals(milestoneDim.get(), "")) {
            milestoneDim.set(String.valueOf(this.level.dimension()));
        }


        LOGGER.info("Position of Milestone: " + milestonePos.get());
        LOGGER.info("Milestone Dimension: " + milestoneDim.get());
        LOGGER.info("UUID of Milestone: " + milestoneUUID.get());
        LOGGER.info("Milestone name: " + milestoneName.get());
        LOGGER.info("Is Milestone Active? " + isactive.get());
    }

    @OnlyIn(Dist.CLIENT)
    public void setMilestoneName(String key) {
        sendPacketToServer(output -> output.writeString(key), 0);
        milestoneName.set(key);
        LOGGER.info("Milestone setMilestoneName: " + milestoneName.get());
    }
}