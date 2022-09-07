//package com.greatorator.tolkienmobs.entity.tile.tilezone;
//
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraftforge.items.ItemStackHandler;
//
//import java.util.function.Predicate;
//
//public class ZoneTTMInventoryContents implements IInventory {
//
//    public static ZoneTTMInventoryContents createForTileEntity(int size,
//                                                               Predicate<PlayerEntity> canPlayerAccessInventoryLambda,
//                                                               Notify markDirtyNotificationLambda) {
//        return new ZoneTTMInventoryContents(size, canPlayerAccessInventoryLambda, markDirtyNotificationLambda);
//    }
//
//    public static ZoneTTMInventoryContents createForClientSideContainer(int size) {
//        return new ZoneTTMInventoryContents(size);
//    }
//
//    public CompoundNBT serializeNBT()  {
//        return backpackComponentContents.serializeNBT();
//    }
//
//    public void deserializeNBT(CompoundNBT nbt)   {
//        backpackComponentContents.deserializeNBT(nbt);
//    }
//
//    public void setCanPlayerAccessInventoryLambda(Predicate<PlayerEntity> canPlayerAccessInventoryLambda) {
//        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
//    }
//
//    public void setMarkDirtyNotificationLambda(Notify markDirtyNotificationLambda) {
//        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
//    }
//
//    public void setOpenInventoryNotificationLambda(Notify openInventoryNotificationLambda) {
//        this.openInventoryNotificationLambda = openInventoryNotificationLambda;
//    }
//
//    public void setCloseInventoryNotificationLambda(Notify closeInventoryNotificationLambda) {
//        this.closeInventoryNotificationLambda = closeInventoryNotificationLambda;
//    }
//
//    @Override
//    public boolean stillValid(PlayerEntity player) {
//        return canPlayerAccessInventoryLambda.test(player);
//    }
//
//    @Override
//    public boolean canPlaceItem(int index, ItemStack stack) {
//        return backpackComponentContents.isItemValid(index, stack);
//    }
//
//    @FunctionalInterface
//    public interface Notify {
//        void invoke();
//    }
//
//    @Override
//    public void setChanged() {
//        markDirtyNotificationLambda.invoke();
//    }
//
//    @Override
//    public void startOpen(PlayerEntity player) {
//        openInventoryNotificationLambda.invoke();
//    }
//
//    @Override
//    public void stopOpen(PlayerEntity player) {
//        closeInventoryNotificationLambda.invoke();
//    }
//
//    @Override
//    public int getContainerSize() {
//        return backpackComponentContents.getSlots();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        for (int i = 0; i < backpackComponentContents.getSlots(); ++i) {
//            if (!backpackComponentContents.getStackInSlot(i).isEmpty()) return false;
//        }
//        return true;
//    }
//
//    @Override
//    public ItemStack getItem(int index) {
//        return backpackComponentContents.getStackInSlot(index);
//    }
//
//    @Override
//    public ItemStack removeItem(int index, int count) {
//        if (count < 0) throw new IllegalArgumentException("count should be >= 0:" + count);
//        return backpackComponentContents.extractItem(index, count, false);
//    }
//
//    @Override
//    public ItemStack removeItemNoUpdate(int index) {
//        int maxPossibleItemStackSize = backpackComponentContents.getSlotLimit(index);
//        return backpackComponentContents.extractItem(index, maxPossibleItemStackSize, false);
//    }
//
//    @Override
//    public void setItem(int index, ItemStack stack) {
//        backpackComponentContents.setStackInSlot(index, stack);
//    }
//
//    @Override
//    public void clearContent() {
//        for (int i = 0; i < backpackComponentContents.getSlots(); ++i) {
//            backpackComponentContents.setStackInSlot(i, ItemStack.EMPTY);
//        }
//    }
//
//    public ItemStack increaseStackSize(int index, ItemStack itemStackToInsert) {
//        ItemStack leftoverItemStack = backpackComponentContents.insertItem(index, itemStackToInsert, false);
//        return leftoverItemStack;
//    }
//
//    public boolean doesItemStackFit(int index, ItemStack itemStackToInsert) {
//        ItemStack leftoverItemStack = backpackComponentContents.insertItem(index, itemStackToInsert, true);
//        return leftoverItemStack.isEmpty();
//    }
//
//    private ZoneTTMInventoryContents(int size) {
//        this.backpackComponentContents = new ItemStackHandler(size);
//    }
//
//    private ZoneTTMInventoryContents(int size, Predicate<PlayerEntity> canPlayerAccessInventoryLambda, Notify markDirtyNotificationLambda) {
//        this.backpackComponentContents = new ItemStackHandler(size);
//        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
//        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
//    }
//
//    private Predicate<PlayerEntity> canPlayerAccessInventoryLambda = x-> true;
//
//    private Notify markDirtyNotificationLambda = ()->{};
//
//    private Notify openInventoryNotificationLambda = ()->{};
//
//    private Notify closeInventoryNotificationLambda = ()->{};
//
//    private final ItemStackHandler backpackComponentContents;
//}
