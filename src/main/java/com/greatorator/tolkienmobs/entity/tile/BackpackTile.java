package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.BackpackBlock;
import com.greatorator.tolkienmobs.container.BackpackContainer;
import com.greatorator.tolkienmobs.lib.TileFluidHandler;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

/**
 * Overhauled by brandon3055 on 07/09/2022
 */
public class BackpackTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public TileFluidHandler fluidTank = new TileFluidHandler(FluidAttributes.BUCKET_VOLUME * 16);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(54);
    public TileItemStackHandler craftingItems = new TileItemStackHandler(9);
    public TileItemStackHandler fluidItems = new TileItemStackHandler(2).setSlotLimit(1);

    public BackpackTile() {
        super(TTMContent.BACKPACK_TILE.get());
        capManager.setManaged("fluid_tank", CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, fluidTank).saveBoth().syncContainer();
//        capManager.setManaged("main_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, invZoneContents).saveBoth(); //You would use this to make the inventory accessible to automation.
        capManager.setInternalManaged("main_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth();
        capManager.setInternalManaged("crafting_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, craftingItems).saveBoth();
        capManager.setInternalManaged("fluid_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, fluidItems).saveBoth();

        //Ensure fluidItems only accepts fluid storage items.
        fluidItems.setStackValidator(stack -> stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent());
        fluidItems.setContentsChangeListener(this::fluiditemSlotChange); //<-- call fluiditemSlotChange if the fluid item inventory is modified
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide()) {
            if (!FluidUtil.interactWithFluidHandler(playerEntity, hand, fluidTank)) {
                //Open the gui if no bucket interaction occurs.
                openGUI(playerEntity, this, worldPosition);
            }
        }
    }

    private void fluiditemSlotChange(int slot) {
        ItemStack stack = fluidItems.getStackInSlot(slot);
        if (stack.isEmpty()) {
            return;
        }

        if (slot == 0) { //Input Slot
            FluidActionResult result = FluidUtil.tryEmptyContainer(stack, fluidTank, fluidTank.getSpace(), null, true);
            if (result.isSuccess()) {
                fluidItems.setStackInSlot(slot, result.getResult());
            }
        } else { //Output Slot
            FluidActionResult result = FluidUtil.tryFillContainer(stack, fluidTank, fluidTank.getFluidAmount(), null, true);
            if (result.isSuccess()) {
                fluidItems.setStackInSlot(slot, result.getResult());
            }
        }
    }

    @Override
    public void tick() {
        //What you had here is what most people would refer to as a 'lag machine' It's not needed.
    }

    //This is the method that gets called when you call "tile.sendPacketToServer(mcDataOutput -> {}, id)" from the GUI
    @Override
    public void receivePacketFromClient(MCDataInput data, ServerPlayerEntity client, int id) {
        Direction facing = level.getBlockState(worldPosition).getValue(BackpackBlock.FACING);

        if (id == 0) { //Bed button was pressed
            level.setBlock(worldPosition.relative(facing), TTMContent.SLEEPING_BAG_BLUE.get().defaultBlockState(), 26);
            level.setBlock(worldPosition.relative(facing).north(), TTMContent.SLEEPING_BAG_BLUE.get().defaultBlockState().setValue(BedBlock.PART, BedPart.HEAD), 26);
        } else if (id == 1) { //Campfire button was pressed
            level.setBlockAndUpdate(worldPosition.relative(facing), Blocks.CAMPFIRE.defaultBlockState());
        } else if (id == 2) { //Upgrade button was pressed
            level.setBlockAndUpdate(worldPosition.relative(facing), Blocks.STONE.defaultBlockState());
        }
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BackpackContainer(TTMContent.BACKPACK_CONTAINER, windowID, playerInventory, this);
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }
}