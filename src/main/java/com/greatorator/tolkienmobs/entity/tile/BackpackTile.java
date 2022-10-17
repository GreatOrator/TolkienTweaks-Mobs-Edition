package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.datamanager.DataFlags;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedByte;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.BackpackBlock;
import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.container.BackpackContainer;
import com.greatorator.tolkienmobs.handler.TTMTags;
import com.greatorator.tolkienmobs.lib.TileFluidHandler;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
import java.util.ArrayList;

/**
 * Overhauled by brandon3055 on 07/09/2022
 */
public class BackpackTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public final ManagedBool isSleepingbagDeployed = register(new ManagedBool("sleepingbag_is_deployed", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedBool isCampfireDeployed = register(new ManagedBool("campfire_is_deployed", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedByte sizeUpgrade = register(new ManagedByte("size_upgrade_installed", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedByte craftUpgrade = register(new ManagedByte("crafting_upgrade_installed", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedByte tankUpgrade = register(new ManagedByte("tank_upgrade_installed", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedByte bedUpgrade = register(new ManagedByte("bed_upgrade_installed", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedByte fireUpgrade = register(new ManagedByte("campfire_upgrade_installed", DataFlags.SAVE_NBT_SYNC_TILE));

    public TileFluidHandler fluidTank = new TileFluidHandler(FluidAttributes.BUCKET_VOLUME * 16);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(54);
    public TileItemStackHandler upgradeInventory = new TileItemStackHandler(5);
    public TileItemStackHandler craftingItems = new TileItemStackHandler(9);
    public TileItemStackHandler fluidItems = new TileItemStackHandler(2).setSlotLimit(1);
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
        //Easier to just ignore the slot and update all flags.
        //This only happens when the inventory changes, so we don't need maximum efficiency.
        byte size = 0;
        byte craft = 0;
        byte tank = 0;
        byte bed = 0;
        byte fire = 0;
        for (int i = 0; i < upgradeInventory.getSlots(); i++) {
            ItemStack stack = upgradeInventory.getStackInSlot(i);
            if (stack.getItem() == TTMContent.ITEM_BACKPACK_UPGRADE_SIZE.get()) {
                size += stack.getCount();
            } else if (stack.getItem() == TTMContent.ITEM_BACKPACK_UPGRADE_CRAFTING.get()) {
                craft += stack.getCount();
            } else if (stack.getItem() == TTMContent.ITEM_BACKPACK_UPGRADE_FLUID.get()) {
                tank += stack.getCount();
            } else if (stack.getItem() == TTMContent.ITEM_BACKPACK_UPGRADE_SLEEPING.get()) {
                bed += stack.getCount();
            } else if (stack.getItem() == TTMContent.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get()) {
                fire += stack.getCount();
            }
        }

        if (size != sizeUpgrade.get()) {
            LOGGER.info("sizeUpgrade Changed? " + size);
            sizeUpgrade.set(size);
        }
        if (craft != craftUpgrade.get()) {
            LOGGER.info("craftUpgrade Changed? " + craft);
            craftUpgrade.set(craft);
        }
        if (tank != tankUpgrade.get()) {
            LOGGER.info("tankUpgrade Changed? " + tank);
            tankUpgrade.set(tank);
        }
        if (bed != bedUpgrade.get()) {
            LOGGER.info("bedUpgrade Changed? " + bed);
            bedUpgrade.set(bed);
        }
        if (fire != fireUpgrade.get()) {
            LOGGER.info("fireUpgrade Changed? " + fire);
            fireUpgrade.set(fire);
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

    //This is the method that gets called when you call "tile.sendPacketToServer(mcDataOutput -> {}, id)" from the GUI
    @Override
    public void receivePacketFromClient(MCDataInput data, ServerPlayerEntity client, int id) {
        if (id == 0) {//Bed button was pressed
            if (!isSleepingbagDeployed.get()) {
                deploySleepingbag();
            } else {
                removeSleepingbag();
            }
        } else if (id == 1) {//Campfire button was pressed
            if (!isCampfireDeployed.get()) {
                deployCampfire();
            } else {
                removeCampfire();
            }
        }
    }

    private void deploySleepingbag() {
        Direction facing = level.getBlockState(worldPosition).getValue(BackpackBlock.FACING);
        BlockPos sleepingBagPos1 = worldPosition.relative(facing);
        BlockPos sleepingBagPos2 = sleepingBagPos1.relative(facing);

        if (!level.isClientSide && isCampfireDeployed.get()) {
            // Make sure nothing in the way, especially the campfire
            this.isCampfireDeployed.set(false);
            level.playSound(null, worldPosition.relative(facing), SoundEvents.WOOD_BREAK, SoundCategory.BLOCKS, 0.5F, 1.0F);
            level.setBlockAndUpdate(worldPosition.relative(facing), Blocks.AIR.defaultBlockState());
        }
        this.isSleepingbagDeployed.set(true);

        level.setBlockAndUpdate(sleepingBagPos1, TTMContent.SLEEPING_BAG_BLUE.get().defaultBlockState().setValue(SleepingBagBlock.FACING, facing).setValue(SleepingBagBlock.PART, BedPart.FOOT));
        level.setBlockAndUpdate(sleepingBagPos2, TTMContent.SLEEPING_BAG_BLUE.get().defaultBlockState().setValue(SleepingBagBlock.FACING, facing).setValue(SleepingBagBlock.PART, BedPart.HEAD));

        level.updateNeighborsAt(worldPosition, TTMContent.SLEEPING_BAG_BLUE.get());
        level.updateNeighborsAt(sleepingBagPos2, TTMContent.SLEEPING_BAG_BLUE.get());
    }

    private void removeSleepingbag() {
        Direction facing = level.getBlockState(worldPosition).getValue(BackpackBlock.FACING);
        BlockPos sleepingBagPos1 = worldPosition.relative(facing);
        BlockPos sleepingBagPos2 = sleepingBagPos1.relative(facing);

        this.isSleepingbagDeployed.set(false);

        level.playSound(null, sleepingBagPos2, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
        level.setBlockAndUpdate(sleepingBagPos2, Blocks.AIR.defaultBlockState());
        level.setBlockAndUpdate(sleepingBagPos1, Blocks.AIR.defaultBlockState());
    }

    private void deployCampfire() {
        Direction facing = level.getBlockState(worldPosition).getValue(BackpackBlock.FACING);

        if (!level.isClientSide && isSleepingbagDeployed.get()) {
            BlockPos sleepingBagPos1 = worldPosition.relative(facing);
            BlockPos sleepingBagPos2 = sleepingBagPos1.relative(facing);

            // Make sure nothing in the way, especially the sleepingbag
            this.isSleepingbagDeployed.set(false);

            level.playSound(null, sleepingBagPos2, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
            level.setBlockAndUpdate(sleepingBagPos2, Blocks.AIR.defaultBlockState());
            level.setBlockAndUpdate(sleepingBagPos1, Blocks.AIR.defaultBlockState());
        }
        this.isCampfireDeployed.set(true);

        level.setBlockAndUpdate(worldPosition.relative(facing), Blocks.CAMPFIRE.defaultBlockState());
    }

    private void removeCampfire() {
        Direction facing = level.getBlockState(worldPosition).getValue(BackpackBlock.FACING);

        this.isCampfireDeployed.set(false);

        level.playSound(null, worldPosition.relative(facing), SoundEvents.WOOD_BREAK, SoundCategory.BLOCKS, 0.5F, 1.0F);
        level.setBlockAndUpdate(worldPosition.relative(facing), Blocks.AIR.defaultBlockState());
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

    public boolean isItemValidForSlot(int index, ItemStack stack) {
            return stack.getItem().is(TTMTags.items.UPGRADES);
    }

    @Override
    public void tick() {
        super.tick();
    }
}