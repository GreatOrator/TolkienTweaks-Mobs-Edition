package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCore;
import com.brandon3055.brandonscore.inventory.PlayerSlot;
import com.brandon3055.brandonscore.inventory.SlotCheckValid;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.item.container.KeyRingItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
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

    public static final int SLOTS = 18;
    private PlayerSlot slot;
    public ItemStack stack;
    private IItemHandler stackItemHandler;

    public KeyRingContainer(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
        super(TolkienContainers.KEY_RING_CONTAINER, windowId, playerInv, extraData);
        this.slot = PlayerSlot.fromBuff(extraData);
        onContainerOpen();
    }

    public KeyRingContainer(@Nullable MenuType type, int windowId, Inventory player, PlayerSlot slot) {
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
        Inventory playerInv = player.getInventory();
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
    public boolean stillValid(Player playerIn) {
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
    public void initializeContents(int stateId, List<ItemStack> stacks, ItemStack carried) {
        super.initializeContents(stateId, stacks, carried);
        ItemStack stack = slot.getStackInSlot(player);
        if (stack != this.stack && !stack.isEmpty() && stack.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
            this.stack = stack; //Because the client side stack is invalidated every time the server sends an update.
            initItemHandler();
        }
    }

    @Override
    public void setItem(int slotID, int stateId, ItemStack stack) {
        super.setItem(slotID, stateId, stack);
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

    public static class Provider implements MenuProvider {
        private ItemStack stack;

        private PlayerSlot slot;

        public Provider(ItemStack stack, PlayerSlot slot) {
            this.stack = stack;
            this.slot = slot;
        }

        @Override
        public Component getDisplayName() {
            return stack.getHoverName().plainCopy().append(" ").append(new TranslatableComponent("gui.tolkienmobs.key_ring.title"));
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int menuID, Inventory playerInventory, Player playerEntity) {
            return new KeyRingContainer(TolkienContainers.KEY_RING_CONTAINER, menuID, playerInventory, slot);
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
