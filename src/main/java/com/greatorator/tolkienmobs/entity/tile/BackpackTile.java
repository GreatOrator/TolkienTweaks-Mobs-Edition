package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.BackpackBlock;
import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.container.BackpackContainer;
import com.greatorator.tolkienmobs.lib.TileFluidHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Overhauled by brandon3055 on 07/09/2022
 */
public class BackpackTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public TileFluidHandler fluidTank = new TileFluidHandler(FluidAttributes.BUCKET_VOLUME * 16);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(54);
    public TileItemStackHandler upgradeInventory = new TileItemStackHandler(5);
    public TileItemStackHandler craftingItems = new TileItemStackHandler(9);
    public TileItemStackHandler fluidItems = new TileItemStackHandler(2).setSlotLimit(1);
    private boolean isSleepingBagDeployed = false;
    public ArrayList<ItemStack> list = new ArrayList<ItemStack>();

    public BackpackTile() {
        super(TTMContent.BACKPACK_TILE.get());
        capManager.setManaged("fluid_tank", CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, fluidTank).saveBoth().syncContainer();
//        capManager.setManaged("main_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, invZoneContents).saveBoth(); //You would use this to make the inventory accessible to automation.
        capManager.setInternalManaged("main_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth();
        capManager.setInternalManaged("upgrade_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, upgradeInventory).saveBoth();
        capManager.setInternalManaged("crafting_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, craftingItems).saveBoth();
        capManager.setInternalManaged("fluid_inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, fluidItems).saveBoth();

        //Ensure fluidItems only accepts fluid storage items.
        fluidItems.setStackValidator(stack -> stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent());
        fluidItems.setContentsChangeListener(this::fluiditemSlotChange); //<-- call fluiditemSlotChange if the fluid item inventory is modified

        //Ensure upgradeInventory only accepts upgrade items.
        upgradeInventory.setStackValidator(this::isItemValidForSlot);
        upgradeInventory.setContentsChangeListener(this::upgradeSlotChange); //<-- call upgradeSlotChange if the item inventory is modified
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide()) {
            if (!FluidUtil.interactWithFluidHandler(playerEntity, hand, fluidTank)) {
                //Open the gui if no bucket interaction occurs.
                openGUI(playerEntity, this, worldPosition);
            }
        }
    }

    private void upgradeSlotChange(int slot) {
        ItemStack stack = upgradeInventory.getStackInSlot(slot);
        if (stack.isEmpty()) {
            return;
        }

        if (slot == 0) { //Input Slot
//            FluidActionResult result = FluidUtil.tryEmptyContainer(stack, fluidTank, fluidTank.getSpace(), null, true);
//            if (result.isSuccess()) {
//                fluidItems.setStackInSlot(slot, result.getResult());
//            }
        } else { //Output Slot
//            FluidActionResult result = FluidUtil.tryFillContainer(stack, fluidTank, fluidTank.getFluidAmount(), null, true);
//            if (result.isSuccess()) {
//                fluidItems.setStackInSlot(slot, result.getResult());
//            }
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
        BlockPos sleepingBagPos1 = worldPosition.relative(facing);
        BlockPos sleepingBagPos2 = sleepingBagPos1.relative(facing);

        if (id == 0) { //Bed button was pressed
            level.setBlockAndUpdate(sleepingBagPos1, TTMContent.SLEEPING_BAG_BLUE.get().defaultBlockState().setValue(SleepingBagBlock.FACING, facing).setValue(SleepingBagBlock.PART, BedPart.FOOT));
            level.setBlockAndUpdate(sleepingBagPos2, TTMContent.SLEEPING_BAG_BLUE.get().defaultBlockState().setValue(SleepingBagBlock.FACING, facing).setValue(SleepingBagBlock.PART, BedPart.HEAD));

            level.updateNeighborsAt(worldPosition, TTMContent.SLEEPING_BAG_BLUE.get());
            level.updateNeighborsAt(sleepingBagPos2, TTMContent.SLEEPING_BAG_BLUE.get());
        } else if (id == 1) { //Campfire button was pressed
            level.setBlockAndUpdate(worldPosition.relative(facing), Blocks.CAMPFIRE.defaultBlockState());
        }
    }

    public boolean isSleepingBagDeployed()
    {
        return this.isSleepingBagDeployed;
    }

    public void setSleepingBagDeployed(boolean isSleepingBagDeployed)
    {
        this.isSleepingBagDeployed = isSleepingBagDeployed;
    }

    public boolean removeSleepingBag(World world)
    {
        Direction blockFacing = this.getBlockDirection(world.getBlockEntity(getBlockPos()));

        this.isThereSleepingBag(blockFacing);

        if(this.isSleepingBagDeployed)
        {
            BlockPos sleepingBagPos1 = getBlockPos().relative(blockFacing);
            BlockPos sleepingBagPos2 = sleepingBagPos1.relative(blockFacing);

            if(world.getBlockState(sleepingBagPos1).getBlock() == TTMContent.SLEEPING_BAG_BLUE.get() && world.getBlockState(sleepingBagPos2).getBlock() == TTMContent.SLEEPING_BAG_BLUE.get())
            {
                world.playSound(null, sleepingBagPos2, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                world.setBlockAndUpdate(sleepingBagPos2, Blocks.AIR.defaultBlockState());
                world.setBlockAndUpdate(sleepingBagPos1, Blocks.AIR.defaultBlockState());
                this.isSleepingBagDeployed = false;
                this.setChanged();
                return true;
            }
        }
        else
        {
            this.isSleepingBagDeployed = false;
            this.setChanged();
            return true;
        }
        return false;
    }

    public boolean isThereSleepingBag(Direction direction)
    {
        if(level.getBlockState(getBlockPos().relative(direction)).getBlock() == TTMContent.SLEEPING_BAG_BLUE.get() && level.getBlockState(getBlockPos().relative(direction).relative(direction)).getBlock() == TTMContent.SLEEPING_BAG_BLUE.get())
        {
            return true;
        }
        else
        {
            this.isSleepingBagDeployed = false;
            return false;
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
            for (int i = 0; i < upgradeInventory.getSlots(); i++) {
                LOGGER.info("List of items in upgrade inventory: " + upgradeInventory.getStackInSlot(i));
            }
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    public Direction getBlockDirection(TileEntity tile)
    {
        if(tile instanceof BackpackTile)
        {
            if(level == null || !(level.getBlockState(getBlockPos()).getBlock() instanceof BackpackBlock))
            {
                return Direction.NORTH;
            }
            return level.getBlockState(getBlockPos()).getValue(BackpackBlock.FACING);
        }
        return Direction.NORTH;
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index <= 5) {
            return ItemTags.createOptional(new ResourceLocation("forge", "upgrades")).contains(stack.getItem());
        }
        else return false;
    }

}