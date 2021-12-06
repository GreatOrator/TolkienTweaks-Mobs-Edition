package com.greatorator.tolkienmobs.tileentity;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TTMFireplaceTile extends TileEntity {
    public final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);


    public TTMFireplaceTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TTMFireplaceTile() {
        this(TTMContent.TMFIREPLACE_TILE.get());
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0: return stack.getItem() == TTMContent.DUST_MORGULIRON.get();
                    case 1: return stack.getItem() == TTMContent.GEM_AMMOLITE.get();
                    case 2: return stack.getItem() == Items.COAL;
                    case 3: return stack.getItem() == TTMContent.INGOT_MORGULIRON.get();
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        //if we wanted sidedness we would add it here
        return super.getCapability(cap, side);
    }

    public BlockState getBlockState(Block block) {
        return null;
    }


    ////    private ItemStack smelting = ItemStack.EMPTY;
//
//    private ManagedString customName = register("customName", new ManagedString("")).saveToTile().saveToItem().syncViaContainer().finish();
//    public ManagedInt burnTime = register("burnTime", new ManagedInt(0)).saveToTile().syncViaTile().syncViaContainer().finish();
//    public ManagedInt currentBurnTime = register("currentBurnTime", new ManagedInt(0)).saveToTile().syncViaContainer().finish();
//    public ManagedInt cookTime = register("cookTime", new ManagedInt(0)).saveToTile().syncViaContainer().finish();
//    public ManagedInt totalCookTime = register("totalCookTime ", new ManagedInt(300)).saveToTile().saveToItem().syncViaContainer().finish();
//
//    public TileTMFireplace() {
//        setInventorySize(4);
//        setShouldRefreshOnBlockChange(); //Stops the tile data getting deleted when you update the block state.
//    }
//
//
//    public boolean hasCustomName() {
//        return this.customName.value != null && !this.customName.value.isEmpty();
//    }
//
//    public void setCustomName(String customName) {
//        this.customName.value = customName;
//    }
//
//    @Override
//    public ITextComponent getDisplayName() {
//        return this.hasCustomName() ? new TextComponentString(this.customName.value) : new TextComponentTranslation("container.tmfireplace");
//    }
//
//
//    public boolean isBurning() {
//        return this.burnTime.value > 0;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public static boolean isBurning(TileTMFireplace te) {
//        return te.getField(0) > 0;
//    }
//
//    @Override
//    public void update() {
//        super.update(); //This is required for all of BC's sync magic to work.
//
//        //We dont need to process the logic client side.
//        if (world.isRemote) return;
//
//        IBlockState state = getState(getBlockTypeSafe(TTMFeatures.BLOCK_TMFIREPLACE));
//        boolean stateFlag = state.getValue(BlockTMFireplace.BURNING);
//
//        if (stateFlag && burnTime.value <= 0) {
//            world.setBlockState(pos, state.withProperty(BlockTMFireplace.BURNING, false));
//            updateBlock();
//        }
//        else if (!stateFlag && burnTime.value > 0) {
//            world.setBlockState(pos, state.withProperty(BlockTMFireplace.BURNING, true));
//            updateBlock();
//        }
//
//        if (this.isBurning()) {
//            --this.burnTime.value;
//        }
//
//        ItemStack fuel = itemHandler.getStackInSlot(2);
//
//        if (this.isBurning() || !fuel.isEmpty() && (!(itemHandler.getStackInSlot(0).isEmpty() && itemHandler.getStackInSlot(1).isEmpty()))) {
//            if (!this.isBurning() && this.canSmelt()) {
//                this.burnTime.value = getItemBurnTime(fuel);
//                this.currentBurnTime.value = burnTime.value;
//
//                if (this.isBurning() && !fuel.isEmpty()) {
//                    Item item = fuel.getItem();
//                    fuel.shrink(1);
//
//                    if (fuel.isEmpty()) {
//                        ItemStack item1 = item.getContainerItem(fuel);
//                        ((IItemHandlerModifiable) itemHandler).setStackInSlot(2, item1);
//                    }
//                    markDirty();
//                }
//            }
//        }
//
//        if (this.isBurning() && this.canSmelt()) {
//            cookTime.value++;
//            if (cookTime.value >= totalCookTime.value) {
//                doSmelt();
//                cookTime.value = 0;
//                markDirty();
//            }
//        }
//        else if (cookTime.value > 0) {
//            cookTime.value--;
//        }
//    }
//
//    private void doSmelt() {
//        ItemStack result = TMFireplaceRecipes.getInstance().getFireplaceResult(itemHandler.getStackInSlot(0), itemHandler.getStackInSlot(1)).copy();
//        if (result.isEmpty()) {
//            //Do vanilla smelt?
//            ItemStack output1 = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(0)).copy(); //Remember to copy these before you start manipulating them
//            ItemStack output2 = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(1)).copy();
//
//            ItemStack outputSlot = getStackInSlot(3);
//
//            if (!output1.isEmpty() && output1.getItem() instanceof ItemFood) {
//                if (outputSlot.isEmpty()) {
//                    setInventorySlotContents(3, output1);
//                    outputSlot = getStackInSlot(3);
//                    decrStackSize(0, 1);
//                }
//                else if (outputSlot.isItemEqual(output1) && outputSlot.getCount() + output1.getCount() < outputSlot.getMaxStackSize()) {
//                    outputSlot.setCount(Math.min(outputSlot.getMaxStackSize(), outputSlot.getCount() + output1.getCount()));
//                    decrStackSize(0, 1);
//                }
//            }
//
//            if (!output2.isEmpty() && output2.getItem() instanceof ItemFood) {
//                if (outputSlot.isEmpty()) {
//                    setInventorySlotContents(3, output2);
//                    decrStackSize(1, 1);
//                }
//                else if (outputSlot.isItemEqual(output2) && outputSlot.getCount() + output2.getCount() < outputSlot.getMaxStackSize()) {
//                    outputSlot.setCount(Math.min(outputSlot.getMaxStackSize(), outputSlot.getCount() + output2.getCount()));
//                    decrStackSize(1, 1);
//                }
//            }
//        }
//        else {
//            ItemStack outputSlot = itemHandler.getStackInSlot(3);
//            if (outputSlot.isEmpty()) {
//                setInventorySlotContents(3, result);
//            }
//            else {
//                outputSlot.setCount(Math.min(outputSlot.getMaxStackSize(), outputSlot.getCount() + result.getCount()));
//            }
//            decrStackSize(0, 1);
//            decrStackSize(1, 1);
//        }
//    }
//
//    private boolean canSmelt() {
//        if (itemHandler.getStackInSlot(0).isEmpty() && itemHandler.getStackInSlot(1).isEmpty()) {
//            return false;
//        }
//
//        ItemStack result = TMFireplaceRecipes.getInstance().getFireplaceResult(itemHandler.getStackInSlot(0), itemHandler.getStackInSlot(1));
//        if (result.isEmpty()) {
//            //Else check for vanilla food smelting recipes?
//            ItemStack output1 = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(0));
//            ItemStack output2 = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(1));
//
//            if ((output1.isEmpty() || !(output1.getItem() instanceof ItemFood)) && (output2.isEmpty() || !(output2.getItem() instanceof ItemFood))) {
//                return false;
//            }
//
//            ItemStack outputSlot = itemHandler.getStackInSlot(3);
//
//            if (outputSlot.isEmpty()) {
//                return true;
//            }
//
//            if (outputSlot.isItemEqual(output1) && outputSlot.getCount() + output1.getCount() < outputSlot.getMaxStackSize()) {
//                return true;
//            }
//
//            if (outputSlot.isItemEqual(output2) && outputSlot.getCount() + output2.getCount() < outputSlot.getMaxStackSize()) {
//                return true;
//            }
//
//            return false;
//        }
//        else {
//            ItemStack output = itemHandler.getStackInSlot(3);
//            if (output.isEmpty()) {
//                return true;
//            }
//            if (!output.isItemEqual(result)) {
//                return false;
//            }
//            int res = output.getCount() + result.getCount();
//            return res <= 64 && res <= output.getMaxStackSize();
//        }
//    }
//
//    public static int getItemBurnTime(ItemStack fuel) {
//        if (fuel.isEmpty()) return 0;
//        else {
//            Item item = fuel.getItem();
//
//            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
//                Block block = Block.getBlockFromItem(item);
//
//                if (block == Blocks.WOODEN_SLAB) return 150;
//                if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
//                if (block == Blocks.COAL_BLOCK) return 16000;
//            }
//
//            if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName())) return 200;
//            if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName())) return 200;
//            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName())) return 200;
//            if (item == Items.STICK) return 100;
//            if (item == Items.COAL) return 1600;
//            if (item == Items.LAVA_BUCKET) return 20000;
//            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
//            if (item == Items.BLAZE_ROD) return 2400;
//
//            return GameRegistry.getFuelValue(fuel);
//        }
//    }
//
//    public double getBurnProgress() {
//        return currentBurnTime.value == 0 ? 0 : (double) burnTime.value / (double) currentBurnTime.value;
//    }
//
//    public double getSmeltProgress() {
//        return totalCookTime.value == 0 ? 0 : (double) cookTime.value / (double) totalCookTime.value;
//    }
//
//    @Override
//    public boolean isItemValidForSlot(int index, ItemStack stack) {
//        if (index == 0 || index == 1) {
//            ItemStack output = FurnaceRecipes.instance().getSmeltingResult(stack);
//            return (!output.isEmpty() && output.getItem() instanceof ItemFood) || TMFireplaceRecipes.getInstance().isInput(index, stack);
//        }
//        else if (index == 2) {
//            return getItemBurnTime(stack) > 0;
//        }
//        return false;
//    }
//
//    @Override
//    public int[] getSlotsForFace(EnumFacing side) {
//        return new int[]{0, 1, 2, 3};
//    }
//
//    @Override
//    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
//        return isItemValidForSlot(index, itemStackIn);
//    }
//
//    @Override
//    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
//        return index == 3;
//    }
//
//    @Override
//    public void setInventorySlotContents(int index, ItemStack stack) {
//        super.setInventorySlotContents(index, stack);
//        updateBlock();
//    }
//
//    @Override
//    public ItemStack decrStackSize(int index, int count) {
//        ItemStack ret = super.decrStackSize(index, count);
//        updateBlock();
//        return ret;
//    }
//
//    @Override
//    public ItemStack removeStackFromSlot(int index) {
//        ItemStack ret = super.removeStackFromSlot(index);
//        updateBlock();
//        return ret;
//    }
}