package com.greatorator.tolkienmobs.item.container;

import com.brandon3055.brandonscore.inventory.PlayerSlot;
import com.greatorator.tolkienmobs.container.KeyRingContainer;
import com.greatorator.tolkienmobs.container.capability.ItemStackCapability;
import com.greatorator.tolkienmobs.init.TolkienTags;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.*;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class KeyRingItem extends Item {

    public KeyRingItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, @Nonnull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (worldIn.isClientSide()) return new InteractionResultHolder<>(InteractionResult.PASS, itemStack);

        if (player.isShiftKeyDown()) {
            setActive(itemStack, !getActive(itemStack));
            return new InteractionResultHolder<>(InteractionResult.PASS, itemStack);
        }

        PlayerSlot slot = new PlayerSlot(player, hand);
        NetworkHooks.openGui((ServerPlayer) player, new KeyRingContainer.Provider(itemStack, slot), slot::toBuff);

        return new InteractionResultHolder<>(InteractionResult.PASS, itemStack);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return getActive(itemStack);
    }

    @Nonnull
    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext ctx) {
        Level world = ctx.getLevel();
        Player player = ctx.getPlayer();

        if (!(player instanceof ServerPlayer)) return InteractionResult.PASS;

        if (player.isShiftKeyDown()) {
            setActive(stack, !getActive(stack));
            return InteractionResult.PASS;
        }

        BlockPos pos = ctx.getClickedPos();
        Direction side = ctx.getClickedFace();
        ItemStack itemStack = ctx.getItemInHand();
        if (!(itemStack.getItem() instanceof KeyRingItem)) throw new AssertionError("Unexpected KeyRingItem type");
        KeyRingItem itemKeyRing = (KeyRingItem)itemStack.getItem();

        BlockEntity tileEntity = world.getBlockEntity(pos);

        if (tileEntity == null) return InteractionResult.PASS;
        if (world.isClientSide()) return InteractionResult.SUCCESS;

        IItemHandler tileInventory;
        LazyOptional<IItemHandler> capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
        if (capability.isPresent()) {
            tileInventory = capability.orElseThrow(AssertionError::new);
        } else if (tileEntity instanceof Container) {
            tileInventory = new InvWrapper((Container)tileEntity);
        } else {
            return InteractionResult.FAIL;
        }

        IItemHandlerModifiable itemStackHandlerKeyRing =  (IItemHandlerModifiable)itemKeyRing.getItemStackHandlerKeyRing(itemStack);
        for (int i = 0; i < itemStackHandlerKeyRing.getSlots(); i++) {
            ItemStack key = itemStackHandlerKeyRing.getStackInSlot(i);
            ItemStack keysWhichDidNotFit = ItemHandlerHelper.insertItemStacked(tileInventory, key, false);
            itemStackHandlerKeyRing.setStackInSlot(i, keysWhichDidNotFit);
        }
        tileEntity.setChanged();           // make sure that the tileEntity knows we have changed its contents

        CompoundTag nbt = itemStack.getOrCreateTag();
        int dirtyCounter = nbt.getInt("dirtyCounter");
        nbt.putInt("dirtyCounter", dirtyCounter + 1);
        itemStack.setTag(nbt);

        return InteractionResult.SUCCESS;
    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag oldCapNbt) {
        return new ItemStackCapability(stack, 9*2).setStackValidator(KeyRingItem::isKey);
    }

    @Nullable
    private static IItemHandler getItemStackHandlerKeyRing(ItemStack itemStack) {
        LazyOptional<IItemHandler> optional = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        if (optional.isPresent()) {
            return optional.orElseThrow(RuntimeException::new);
        }
        return null;
    }

    private static boolean isKey(ItemStack stack) {
        return stack.is(TolkienTags.items.KEYS);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof Player player && getActive(stack)) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack keyStack = player.getInventory().getItem(i);
                if (keyStack.is(TolkienTags.items.KEYS))
                    addKeyToInventory(stack, keyStack);
            }
        }
    }

    public static ItemStack addKeyToInventory(ItemStack keyHolder, ItemStack key) {
        IItemHandler handler = keyHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(new ItemStackHandler(KeyRingContainer.SLOTS));
        List<Integer> emptySlots = new ArrayList<>();
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stackInSlot = handler.getStackInSlot(i);
            if (stackInSlot.isEmpty()) emptySlots.add(i);
            if (!stackInSlot.isEmpty() && ItemStack.isSameItemSameTags(stackInSlot, key)) {
                int j = stackInSlot.getCount() + key.getCount();
                int maxSize = 1;
                if (j <= maxSize) {
                    key.setCount(0);
                    stackInSlot.setCount(j);
                } else if (stackInSlot.getCount() < maxSize) {
                    key.shrink(maxSize - stackInSlot.getCount());
                    stackInSlot.setCount(maxSize);
                }
                if (key.isEmpty()) {
                    return key;
                }
            }
        }
        if (emptySlots.isEmpty()) return key;
        handler.insertItem(emptySlots.get(0), key.split(key.getCount()), false);
        return key;
    }

    public static boolean getActive(ItemStack stack) {
        CompoundTag compound = stack.getTag();
        if (compound == null || !compound.contains("active")) return false;
        return compound.getBoolean("active");
    }

    public static boolean setActive(ItemStack stack, boolean active) {
        if (!active)
            stack.removeTagKey("active");
        else
            stack.getOrCreateTag().putBoolean("active", active);
        return active;
    }

    public static float getFullnessPropertyOverride(ItemStack itemStack, @Nullable ClientLevel world, @Nullable LivingEntity livingEntity, int i) {
        IItemHandler keyRing = getItemStackHandlerKeyRing(itemStack);
        if (keyRing == null) return 0;
        int count = 0;
        int j = i;
        for (j = 0; j < keyRing.getSlots(); j++) {
            count+=keyRing.getStackInSlot(j).getCount();
        }
        float fractionEmpty = count / (float)(keyRing.getSlots());
        return 0.0F + fractionEmpty;
    }
}