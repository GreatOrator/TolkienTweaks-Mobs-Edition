package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedPos;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.MilestoneBlock;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.*;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.*;
import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;
import static com.greatorator.tolkienmobs.block.MilestoneBlock.ACTIVE;

public class MilestoneTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public final ManagedBool isactive = register(new ManagedBool("is_active", false, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedString milestoneName = register(new ManagedString("milestone_name", "Unnamed_Milestone", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedString milestoneUUID = register(new ManagedString("milestone_uuid", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedString playerUUID = register(new ManagedString("player_uuid", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedString milestoneDim = register(new ManagedString("dim_key", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedPos milestonePos = register(new ManagedPos("milestone_pos", SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));

    public Set<UUID> owners = new HashSet<>();
    public static List<TileEntity> milestones = new ArrayList<>();

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

    public void setMilestoneLocation() {
        if (Objects.equals(milestoneUUID.get(), "")) {
            milestoneUUID.set(String.valueOf(UUID.randomUUID()));
        }
        //            playerUUID.set(String.valueOf(player.getUUID()));
        Direction facing = level.getBlockState(worldPosition).getValue(MilestoneBlock.FACING);

        isactive.set(true);
        milestonePos.set(worldPosition.relative(facing));
        milestoneDim.set(String.valueOf(this.level.dimension()));


        LOGGER.info("Is Milestone Active? " + isactive.get());
        LOGGER.info("Milestone name: " + milestoneName.get());
        LOGGER.info("Position of Milestone: " + milestonePos.get());
        LOGGER.info("Milestone Dimension: " + milestoneDim.get());
        LOGGER.info("UUID of Milestone: " + milestoneUUID.get());
        LOGGER.info("UUID of Player: " + playerUUID.get());
        getMilestones();
        LOGGER.info("Milestones list: " + milestones);

        level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(ACTIVE, isactive.get()));
        setChanged();
    }

    @Override
    public void receivePacketFromClient(MCDataInput data, ServerPlayerEntity client, int id) {
        BlockPos blockPos = milestonePos.get();
        EntityTeleportEvent.TeleportCommand event = net.minecraftforge.event.ForgeEventFactory.onEntityTeleportCommand(client, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        if (id == 0) {
            if (isactive.get()) {// Milestone Activated
                RegistryKey<World> key = World.OVERWORLD;
//                client.changeDimension(level.getServer().getLevel(key), new WarpMode(level.getServer().getLevel(key)));
                level.playSound((PlayerEntity)null, blockPos, SoundEvents.ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 0.3F, 0.5F);
                client.teleportTo(event.getTargetX() + 1, event.getTargetY(), event.getTargetZ() + 1);

            }
        }
    }

    public static void getMilestones () {
//        CompoundNBT compound = new CompoundNBT();
//        IChunk ichunk;
//
//        ListNBT listnbt = compound.getList("TileEntities", 10);
//
//        for(int j = 0; j < listnbt.size(); ++j) {
//            CompoundNBT compoundnbt = listnbt.getCompound(j);
//                BlockPos blockpos = new BlockPos(compoundnbt.getInt("x"), compoundnbt.getInt("y"), compoundnbt.getInt("z"));
//                TileEntity tileentity = TileEntity.loadStatic(ichunk.getBlockState(blockpos), compoundnbt);
//            if (tileentity instanceof MilestoneTile) {
//                milestones.add(tileentity);
//            }
//        }
    }

    @OnlyIn(Dist.CLIENT)
    public void setMilestone(String key) {
//        sendPacketToServer(output -> output.writeString(key), 0);
        TolkienNetwork.sendMilestoneUpdate(key);
        LOGGER.info("Milestone setMilestoneName: " + milestoneName.get());
    }
}