package com.greatorator.tolkienmobs.api.tileentity;

import com.greatorator.tolkienmobs.api.gui.container.TTMContainer;
import com.greatorator.tolkienmobs.api.gui.container.inventory.TTMCombinedInventory;
import com.greatorator.tolkienmobs.api.gui.container.inventory.TTMFluidInventory;
import com.greatorator.tolkienmobs.api.gui.container.inventory.TTMInventory;
import com.greatorator.tolkienmobs.api.gui.container.slots.TTMItemSlot;
import com.greatorator.tolkienmobs.api.handler.TTMFluidTank;
import com.greatorator.tolkienmobs.api.handler.intface.ITTMInventoryCallback;
import com.greatorator.tolkienmobs.api.handler.intface.TTMGroupTank;
import com.greatorator.tolkienmobs.api.handler.intface.TTMSlotGroup;
import com.greatorator.tolkienmobs.api.network.TTMNetworking;
import com.greatorator.tolkienmobs.api.network.TTMTilePacket;
import com.greatorator.tolkienmobs.api.recipe.TTMRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TTMTile<T extends TTMRecipe> extends TileEntity implements ITickableTileEntity, ITTMInventoryCallback {
    public TTMInventory inventory = new TTMInventory(this);
    protected TTMFluidInventory tankInventory = new TTMFluidInventory(this);

    protected LazyOptional<?> itemCap = LazyOptional.empty();
    protected LazyOptional<?> fluidCap = LazyOptional.empty();
    protected LazyOptional<?> energyCap = LazyOptional.empty();

    protected List<Integer> itemInputCounts = new ArrayList<>();
    protected List<Integer> fluidInputCounts = new ArrayList<>();

    protected Optional<T> currentRecipe = Optional.empty();
    protected int progress = 0;
    protected int totalProgress = 0;

    protected TTMTile(TileEntityType<?> type, int itemInputs, int itemOutputs, int fluidInputs, int fluidInputsCapacity, int fluidOutputs, int fluidOutputsCapacity) {
        super(type);
        inventory.addSlots(TTMSlotGroup.INPUT, itemInputs);
        tankInventory.addTanks(TTMGroupTank.INPUT, fluidInputs, fluidInputsCapacity);
        inventory.addSlots(TTMSlotGroup.OUTPUT, itemOutputs);
        tankInventory.addTanks(TTMGroupTank.OUTPUT, fluidOutputs, fluidOutputsCapacity);

        updateHandlers();
    }

    protected TTMTile(TileEntityType<?> type, List<TTMItemSlot> inputSlots, List<TTMItemSlot> outputSlots, List<TTMFluidTank> inputTanks, List<TTMFluidTank> outputTanks, List<TTMFluidTank> fuelTanks) {
        super(type);
        inventory.addSlots(TTMSlotGroup.INPUT, inputSlots);
        inventory.addSlots(TTMSlotGroup.OUTPUT, outputSlots);

        tankInventory.addTanks(TTMGroupTank.FUEL, fuelTanks);
        tankInventory.addTanks(TTMGroupTank.INPUT, inputTanks);
        tankInventory.addTanks(TTMGroupTank.OUTPUT, outputTanks);

        updateHandlers();
    }

    public TTMTile<T> worldContext(BlockState state, IBlockReader world) {
        return this;
    }

    @Override
    public void tick() {}

    public void begin(T recipe) {}

    public void complete() {
        if (!validateInputs(itemInputCounts, fluidInputCounts)) {
            clear();
            return;
        }
        transferInputs();
        transferOutputs();
        setChanged();
    }

    public void clear() {
        progress = 0;
        totalProgress = 0;
        currentRecipe = Optional.empty();
        itemInputCounts = new ArrayList<>();
    }

    public boolean canBegin(T recipe) {
        List<Integer> itemInputCounts = recipe.getInputItemCounts(inventory);
        List<Integer> fluidInputCounts = recipe.getInputFluidCounts(tankInventory);

        return validateInputs(itemInputCounts, fluidInputCounts) && validateOutputs(recipe);
    }

    public boolean canComplete() {
        return progress <= 0;
    }

    protected boolean validateInputs(List<Integer> itemInputCounts, List<Integer> fluidInputCounts) {
        List<TTMItemSlot> inputSlots = inputSlots();
        List<TTMFluidTank> inputTanks = inputTanks();

        for (int i = 0; i < inputSlots.size() && i < itemInputCounts.size(); i++) {
            int inputCount = itemInputCounts.get(i);
            if (inputCount == 0 || (inputCount > 0 && inputSlots.get(i).getItemStack().getCount() < inputCount)) {
                return false;
            }
        }
        for (int i = 0; i < inputTanks.size() && i < fluidInputCounts.size(); ++i) {
            int inputCount = fluidInputCounts.get(i);
            FluidStack input = inputTanks.get(i).getFluidStack();
            if (inputCount > 0 && (input.isEmpty() || input.getAmount() < inputCount)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validateOutputs(T recipe) {
        List<TTMItemSlot> outputSlots = outputSlots();
        List<ItemStack> recipeOutputItems = recipe.getOutputItems();

        List<TTMFluidTank> outputTanks = outputTanks();
        List<FluidStack> recipeOutputFluids = recipe.getOutputFluids();

        boolean[] used = new boolean[outputSlots.size()];
        for (ItemStack recipeOutput : recipeOutputItems) {
            boolean matched = false;
            for (int i = 0; i < outputSlots.size(); i++) {
                if (used[i]) {
                    continue;
                }
                ItemStack output = outputSlots.get(i).getItemStack();
                if (output.getCount() + Math.max(0, recipeOutput.getCount()) > output.getMaxStackSize()) {
                    continue;
                }
                if (output.getItem() == recipeOutput.getItem() && ItemStack.tagMatches(output, recipeOutput)) {
                    used[i] = true;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                for (int i = 0; i < outputSlots.size(); ++i) {
                    if (used[i]) {
                        continue;
                    }
                    if (outputSlots.get(i).isEmpty()) {
                        used[i] = true;
                        matched = true;
                        break;
                    }
                }
            }

            if (!matched) {
                return false;
            }
        }

        used = new boolean[outputTanks.size()];
        for (FluidStack recipeOutput : recipeOutputFluids) {
            boolean matched = false;
            for (int i = 0; i < outputTanks.size(); ++i) {
                FluidStack output = outputTanks.get(i).getFluidStack();
                if (used[i] || outputTanks.get(i).getCapacity() - output.getAmount() < recipeOutput.getAmount()) {
                    continue;
                }
                if (output.getFluid() == recipeOutput.getFluid()) {
                    used[i] = true;
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                for (int i = 0; i < outputTanks.size(); ++i) {
                    if (used[i]) {
                        continue;
                    }
                    if (outputTanks.get(i).isEmpty()) {
                        used[i] = true;
                        matched = true;
                        break;
                    }
                }
            }
            if (!matched) {
                return false;
            }
        }

        return true;
    }

    protected void transferInputs() {
        for (int i = 0; i < itemInputCounts.size(); ++i) {
            inputSlots().get(i).consume(itemInputCounts.get(i));
        }
        for (int i = 0; i < fluidInputCounts.size(); ++i) {
            inputTanks().get(i).modify(-fluidInputCounts.get(i));
        }
    }

    protected void transferOutputs() {
        if (!currentRecipe.isPresent()) {
            return;
        }

        List<ItemStack> recipeOutputItems = currentRecipe.get().getOutputItems();
        List<Float> recipeOutputChances = currentRecipe.get().getOutputItemChances();
        List<FluidStack> recipeOutputFluids = currentRecipe.get().getOutputFluids();

        for (int i = 0, recipeOutputItemsSize = recipeOutputItems.size(); i < recipeOutputItemsSize; i++) {
            ItemStack recipeOutput = recipeOutputItems.get(i);
            float chance = recipeOutputChances.get(i);
            int outputCount = chance <= 1.0 ? recipeOutput.getCount() : (int) chance;

            while (level.random.nextFloat() < chance) {
                boolean matched = false;

                for (TTMItemSlot slot : outputSlots()) {
                    ItemStack output = slot.getItemStack();

                    if (output.getItem() == recipeOutput.getItem() && ItemStack.tagMatches(output, recipeOutput)
                            && output.getCount() + outputCount <= output.getMaxStackSize()) {
                        output.grow(outputCount);
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    for (TTMItemSlot slot : outputSlots()) {
                        if (slot.isEmpty()) {
                            slot.setItemStack(new ItemStack(recipeOutput.getItem(), outputCount));
                            break;
                        }
                    }
                }

                chance -= outputCount;
                outputCount = 1;
            }
        }

        for (FluidStack recipeOutput : recipeOutputFluids) {
            boolean matched = false;
            for (TTMFluidTank tank : outputTanks()) {
                FluidStack output = tank.getFluidStack();
                if (tank.getCapacity() - tank.getStored() >= recipeOutput.getAmount() && output.getFluid().equals(recipeOutput.getFluid())) {
                    output.setAmount(output.getAmount() + recipeOutput.getAmount());
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                for (TTMFluidTank tank : outputTanks()) {
                    if (tank.isEmpty()) {
                        tank.setFluidStack(recipeOutput.copy());
                        break;
                    }
                }
            }
        }
    }

    protected Optional<T> getRecipe() {
        return Optional.empty();
    }

    public TTMInventory getItemInv() {
        return inventory;
    }

    public TTMFluidInventory getFluidInv() {
        return tankInventory;
    }

    public TTMCombinedInventory getCombinedInv() {
        return new TTMCombinedInventory(inventory, tankInventory);
    }

    public TTMCombinedInventory getCombinedInputInv() {
        return new TTMCombinedInventory(inventory.getInputInventory(), tankInventory.getInputInventory());
    }

    public int getProgress() {
        return progress;
    }

    public int getTotalProgress() {
        return totalProgress;
    }

    protected void initHandlers() {
        inventory.initHandler();
        tankInventory.initHandler();
    }

    public List<TTMItemSlot> inputSlots() {
        return inventory.getInputSlots();
    }

    protected List<TTMItemSlot> outputSlots() {
        return inventory.getOutputSlots();
    }

    public List<TTMFluidTank> inputTanks() {
        return tankInventory.getInputTanks();
    }

    protected List<TTMFluidTank> outputTanks() {
        return tankInventory.getOutputTanks();
    }

    protected List<TTMFluidTank> fuelTanks() {
        return tankInventory.getFuelTanks();
    }

    public TTMItemSlot getSlot(int slot) {
        return inventory.getSlot(slot);
    }

    public TTMFluidTank getTank(int tank) {
        return tankInventory.getTank(tank);
    }

    @Override
    public boolean clearSlot(int slot) {
        if (slot >= inventory.getSlots()) {
            return false;
        }
        if (inventory.getSlot(slot).clear()) {
            onInventoryChange(slot);
            return true;
        }
        return false;
    }

    @Override
    public boolean clearTank(int tank) {
        if (tank >= tankInventory.getTanks()) {
            return false;
        }
        if (tankInventory.getTank(tank).clear()) {
            onTankChange(tank);
            return true;
        }
        return false;
    }

    @Override
    public void load(@Nonnull BlockState state, @Nonnull CompoundNBT tag) {
        super.load(state, tag);
        inventory.read(tag);
        tankInventory.read(tag);
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT tag) {
        super.save(tag);
        inventory.write(tag);
        tankInventory.write(tag);

        return tag;
    }

    protected void updateHandlers() {
        LazyOptional<?> prevItemCap = itemCap;
        IItemHandler invHandler = inventory.getHandler();
        itemCap = inventory.hasAccessibleSlots() ? LazyOptional.of(() -> invHandler) : LazyOptional.empty();
        prevItemCap.invalidate();

        LazyOptional<?> prevFluidCap = fluidCap;
        IFluidHandler fluidHandler = tankInventory.getHandler();
        fluidCap = tankInventory.hasAccessibleTanks() ? LazyOptional.of(() -> fluidHandler) : LazyOptional.empty();
        prevFluidCap.invalidate();
    }

    public TTMTilePacket getGuiPacket() {
        return new TTMTilePacket(getBlockPos(), tankInventory, progress, totalProgress);
    }

    public void handleGuiPacket(TTMTilePacket buffer) {
        for (int i = 0; i < buffer.fluids.size(); i++) {
            tankInventory.set(i, buffer.fluids.get(i));
        }

        progress = buffer.progress;
        totalProgress = buffer.totalProgress;
    }

    public void sendGuiNetworkData(TTMContainer<?> container, IContainerListener player) {
        if (player instanceof ServerPlayerEntity && (!(player instanceof FakePlayer))) {
            TTMNetworking.sendToClient(getGuiPacket(), (ServerPlayerEntity) player);
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory.hasAccessibleSlots()) {
            return getItemHandlerCapability(side);
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankInventory.hasAccessibleTanks()) {
            return getFluidHandlerCapability(side);
        }
        return super.getCapability(cap, side);
    }

    protected <T> LazyOptional<T> getItemHandlerCapability(@Nullable Direction side) {
        if (!itemCap.isPresent() && inventory.hasAccessibleSlots()) {
            IItemHandler handler = inventory.getHandler();
            itemCap = LazyOptional.of(() -> handler);
        }
        return itemCap.cast();
    }

    protected <T> LazyOptional<T> getFluidHandlerCapability(@Nullable Direction side) {
        if (!fluidCap.isPresent() && tankInventory.hasAccessibleTanks()) {
            IFluidHandler handler = tankInventory.getHandler();
            fluidCap = LazyOptional.of(() -> handler);
        }
        return fluidCap.cast();
    }
}