package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.gui.container.BackpackContainer;
import com.greatorator.tolkienmobs.entity.tile.tiledata.DataTTMInventoryStateData;
import com.greatorator.tolkienmobs.entity.tile.tilezone.ZoneTTMInventoryContents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.IItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class BackpackTile extends TileEntity implements INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    private final PlayerEntity player = null;
    public TileItemStackHandler craftingItems = new TileItemStackHandler(9);
    public static final int INV_SLOTS_COUNT = 54;
    public static final int CRAFT_SLOTS_COUNT = 9;
    public static final int CRAFT_OUTPUT_SLOTS_COUNT = 1;
    public static final int TANK_INPUT_SLOTS_COUNT = 1;
    public static final int TANK_OUTPUT_SLOTS_COUNT = 1;
    public static final int TANK_SLOTS_COUNT = 1;
    public static final int TOTAL_SLOTS_COUNT = INV_SLOTS_COUNT + CRAFT_SLOTS_COUNT + CRAFT_OUTPUT_SLOTS_COUNT + TANK_SLOTS_COUNT + TANK_INPUT_SLOTS_COUNT + TANK_OUTPUT_SLOTS_COUNT;

    private final ZoneTTMInventoryContents invZoneContents;

    private final ZoneTTMInventoryContents craftZoneContents;
    private final ZoneTTMInventoryContents craftOutputZoneContents;

    private final ZoneTTMInventoryContents fluidZoneContents;
    private final ZoneTTMInventoryContents fluidInputZoneContents;
    private final ZoneTTMInventoryContents fluidOutputZoneContents;

    private final DataTTMInventoryStateData invStateData = new DataTTMInventoryStateData();

    private BackpackTile tile;
    private int fluidCapacity = 3000;
    private final LazyOptional<IFluidHandler> fluidTankLazy;
    private final FluidTank fluidTank;

    public BackpackTile() {
        super(TTMContent.BACKPACK_TILE.get());

        invZoneContents = ZoneTTMInventoryContents.createForTileEntity(INV_SLOTS_COUNT, this::canPlayerAccessInventory, this::setChanged);

        craftZoneContents = ZoneTTMInventoryContents.createForTileEntity(CRAFT_SLOTS_COUNT, this::canPlayerAccessInventory, this::setChanged);
        craftOutputZoneContents = ZoneTTMInventoryContents.createForTileEntity(CRAFT_OUTPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::setChanged);

        fluidZoneContents = ZoneTTMInventoryContents.createForTileEntity(TANK_SLOTS_COUNT, this::canPlayerAccessInventory, this::setChanged);
        fluidInputZoneContents = ZoneTTMInventoryContents.createForTileEntity(TANK_INPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::setChanged);
        fluidOutputZoneContents = ZoneTTMInventoryContents.createForTileEntity(TANK_OUTPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::setChanged);

        this.fluidTank = new FluidTank(FluidAttributes.BUCKET_VOLUME * 16);
        this.fluidTankLazy = LazyOptional.of(() -> fluidTank);
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide()) {
            ItemStack itemStack = playerEntity.getItemInHand(hand);
            if (itemStack.getItem() instanceof BucketItem) {
                Fluid fluid = ((BucketItem) itemStack.getItem()).getFluid();
                if (fluidTank.getFluidAmount() < 3000) {
                    fluidTank.fill(new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                }else {
                    playerEntity.sendMessage(new StringTextComponent("Tank is full: " + fluidTank.getFluidAmount()), Util.NIL_UUID);
                }
            } else {
                playerEntity.sendMessage(new StringTextComponent("Fluid Level: " + fluidTank.getFluidAmount()), Util.NIL_UUID);
                playerEntity.sendMessage(new StringTextComponent("Fluid Name: " + fluidTank.getFluid()
                        .getDisplayName().getString()), Util.NIL_UUID);
            }
        }
    }

    public boolean canPlayerAccessInventory(PlayerEntity player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.getBlockPos()) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.distanceToSqr(getBlockPos().getX() + X_CENTRE_OFFSET, getBlockPos().getY() + Y_CENTRE_OFFSET, getBlockPos().getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public void tick() {
        if (!level.isClientSide) return; // do nothing on client.
        setChanged();
    }

    public boolean willItemStackFit(ZoneTTMInventoryContents invZoneContents, int slotIndex, ItemStack itemStackOrigin) {
        ItemStack itemStackDestination = invZoneContents.getItem(slotIndex);

        if (itemStackDestination.isEmpty() || itemStackOrigin.isEmpty()) {
            return true;
        }

        if (!itemStackOrigin.sameItem(itemStackDestination)) {
            return false;
        }

        int sizeAfterMerge = itemStackDestination.getCount() + itemStackOrigin.getCount();
        if (sizeAfterMerge <= invZoneContents.getMaxStackSize() && sizeAfterMerge <= itemStackDestination.getMaxStackSize()) {
            return true;
        }
        return false;
    }

    static public boolean isItemValidForInputSlot(ItemStack itemStack) {
        ResourceLocation allowedItems = new ResourceLocation(TolkienMobs.MODID, "allowed_fluids");
        LOGGER.info("Valid items are:" + allowedItems);
//        if (!itemStack.getItem().is(ItemTags.getAllTags().getTag(allowedItems))) {
        return false;
//        }
//        return itemStack.getItem().is(ItemTags.getAllTags().getTag(allowedItems));
    }

    static public boolean isItemValidForOutputSlot(ItemStack itemStack) {
        return false;
    }

    private final String INV_SLOTS_NBT = "invSlots";
    private final String CRAFT_SLOTS_NBT = "craftSlots";
    private final String CRAFT_OUTPUT_SLOTS_NBT = "craftOutputSlots";
    private final String TANK_SLOTS_NBT = "tankSlots";
    private final String TANK_INPUT_SLOTS_NBT = "tankInputSlots";
    private final String TANK_OUTPUT_SLOTS_NBT = "tankOutputSlots";
    private final String SLEEPINGBAG_NBT = "sleepingBag";
    private final String CAMPFIRE_NBT = "campFire";

    @Override
    public CompoundNBT save(CompoundNBT parentNBTTagCompound)
    {
        super.save(parentNBTTagCompound); // The super call is required to save and load the tile's location

        invStateData.putIntoNBT(parentNBTTagCompound);
        parentNBTTagCompound.put(INV_SLOTS_NBT, invZoneContents.serializeNBT());
        parentNBTTagCompound.put(CRAFT_SLOTS_NBT, craftZoneContents.serializeNBT());
        parentNBTTagCompound.put(CRAFT_OUTPUT_SLOTS_NBT, craftOutputZoneContents.serializeNBT());
        parentNBTTagCompound.put(TANK_SLOTS_NBT, fluidZoneContents.serializeNBT());
        parentNBTTagCompound.put(TANK_INPUT_SLOTS_NBT, fluidInputZoneContents.serializeNBT());
        parentNBTTagCompound.put(TANK_OUTPUT_SLOTS_NBT, fluidOutputZoneContents.serializeNBT());
        return parentNBTTagCompound;
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbtTagCompound)
    {
        super.load(blockState, nbtTagCompound); // The super call is required to save and load the tile's location

        invStateData.readFromNBT(nbtTagCompound);

        CompoundNBT inventoryNBT = nbtTagCompound.getCompound(INV_SLOTS_NBT);
        invZoneContents.deserializeNBT(inventoryNBT);

        CompoundNBT craftNBT = nbtTagCompound.getCompound(CRAFT_SLOTS_NBT);
        craftZoneContents.deserializeNBT(craftNBT);
        inventoryNBT = nbtTagCompound.getCompound(CRAFT_OUTPUT_SLOTS_NBT);
        craftOutputZoneContents.deserializeNBT(inventoryNBT);

        CompoundNBT fluidNBT = nbtTagCompound.getCompound(TANK_SLOTS_NBT);
        fluidZoneContents.deserializeNBT(fluidNBT);
        inventoryNBT = nbtTagCompound.getCompound(TANK_INPUT_SLOTS_NBT);
        fluidInputZoneContents.deserializeNBT(inventoryNBT);
        inventoryNBT = nbtTagCompound.getCompound(TANK_OUTPUT_SLOTS_NBT);
        fluidOutputZoneContents.deserializeNBT(inventoryNBT);


        if (invZoneContents.getContainerSize() != INV_SLOTS_COUNT
                || craftZoneContents.getContainerSize() != CRAFT_SLOTS_COUNT
                || fluidZoneContents.getContainerSize() != TANK_SLOTS_COUNT
                || fluidZoneContents.getContainerSize() != TANK_INPUT_SLOTS_COUNT
        )
            throw new IllegalArgumentException("Corrupted NBT: Number of inventory slots did not match expected.");
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT updateTagDescribingTileEntityState = getUpdateTag();
        final int METADATA = 42; // arbitrary.
        return new SUpdateTileEntityPacket(this.worldPosition, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT updateTagDescribingTileEntityState = pkt.getTag();
        BlockState blockState = level.getBlockState(worldPosition);
        handleUpdateTag(blockState, updateTagDescribingTileEntityState);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT tag) {
        load(blockState, tag);
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return BackpackContainer.createContainerServerSide(windowID, playerInventory,
                invZoneContents, craftZoneContents, craftOutputZoneContents, fluidZoneContents, fluidInputZoneContents, fluidOutputZoneContents, invStateData);
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    public IItemHandler getInventory() {
        return null;
    }
}