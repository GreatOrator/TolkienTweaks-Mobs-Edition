package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCore;
import com.brandon3055.brandonscore.inventory.PlayerSlot;
import com.brandon3055.brandonscore.inventory.SlotCheckValid;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.item.tools.KeyRingItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class KeyRingContainer extends ContainerBCore<KeyRingItem> {
    public List<Slot> playerSlots = new ArrayList<>();
    public List<Slot> mainSlots = new ArrayList<>();

    private PlayerSlot slot;
    public ItemStack stack;
    private IItemHandler stackItemHandler;

    public KeyRingContainer(int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        super(TTMContent.KEY_RING_CONTAINER, windowId, playerInv, extraData);
        this.slot = PlayerSlot.fromBuff(extraData);
        onContainerOpen();
    }

    public KeyRingContainer(@Nullable ContainerType type, int windowId, PlayerInventory player, PlayerSlot slot) {
        super(type, windowId, player);
        this.slot = slot;
        onContainerOpen();
    }

    private void initItemHandler() {
        LazyOptional<IItemHandler> optional = stack.getCapability(ITEM_HANDLER_CAPABILITY);
        stackItemHandler = optional.orElseThrow(RuntimeException::new);
    }

    private void onContainerOpen() {
        stack = slot.getStackInSlot(player);
        PlayerInventory playerInv = player.inventory;
        initItemHandler();
        IItemHandler contentHandler = new TransientItemHandlerWrapper(() -> stackItemHandler);

        //Player Inventory
        for (int i = 0; i < playerInv.items.size(); i++) {
            playerSlots.add(addSlot(new SlotCheckValid.IInv(playerInv, i, 0, 0)));
        }

        //Main Inventory
        for (int i = 0; i < contentHandler.getSlots(); i++) {
            mainSlots.add(addSlot(new SlotCheckValid(contentHandler, i, 0, 0)));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        if (stack != slot.getStackInSlot(player)) {
            return false;
        }
        if (stackItemHandler != stack.getCapability(ITEM_HANDLER_CAPABILITY).orElse(null)) {
            return false; //I don't think this is actually possible... But just in case.
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void clientTick() {
        ItemStack stack = slot.getStackInSlot(player);
        if (stack != this.stack && !stack.isEmpty() && stack.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
            this.stack = stack; //Because the client side stack is invalidated every time the server sends an update.
            initItemHandler();
        }
    }

    @Override
    public void setAll(List<ItemStack> stacks) {
        super.setAll(stacks);
        ItemStack stack = slot.getStackInSlot(player);
        if (stack != this.stack && !stack.isEmpty() && stack.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
            this.stack = stack; //Because the client side stack is invalidated every time the server sends an update.
            initItemHandler();
        }
    }

    @Override
    public void setItem(int slotID, ItemStack stack) {
        super.setItem(slotID, stack);
        stack = slot.getStackInSlot(player);
        if (stack != this.stack && !stack.isEmpty() && stack.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
            this.stack = stack; //Because the client side stack is invalidated every time the server sends an update.
            initItemHandler();
        }
    }

    @Override
    public LazyOptional<IItemHandler> getItemHandler() {
        return LazyOptional.of(() -> stackItemHandler);
    }

    public static class Provider implements INamedContainerProvider {
        private ItemStack stack;

        private PlayerSlot slot;

        public Provider(ItemStack stack, PlayerSlot slot) {
            this.stack = stack;
            this.slot = slot;
        }

        @Override
        public ITextComponent getDisplayName() {
            return stack.getHoverName().plainCopy().append(" ").append(new TranslationTextComponent("gui.tolkienmobs.key_ring.title"));
        }
        @Nullable
        @Override
        public Container createMenu(int menuID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            return new KeyRingContainer(TTMContent.KEY_RING_CONTAINER, menuID, playerInventory, slot);
        }

    }
    public static class TransientItemHandlerWrapper implements IItemHandlerModifiable {

        private Supplier<IItemHandler> getHandler;

        public TransientItemHandlerWrapper(Supplier<IItemHandler> getHandler) {
            this.getHandler = getHandler;
        }

        @Override
        public int getSlots() {
            return getHandler.get().getSlots();
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return getHandler.get().getStackInSlot(slot);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return getHandler.get().insertItem(slot, stack, simulate);
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return getHandler.get().extractItem(slot, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return getHandler.get().getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return getHandler.get().isItemValid(slot, stack);
        }
        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            ((IItemHandlerModifiable)getHandler.get()).setStackInSlot(slot, stack);
        }

    }
}
