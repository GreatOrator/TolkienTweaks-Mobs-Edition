package com.greatorator.tolkienmobs.item.tools;

import com.brandon3055.brandonscore.inventory.PlayerSlot;
import com.greatorator.tolkienmobs.container.CoinPouchContainer;
import com.greatorator.tolkienmobs.container.capability.ItemStackInventory;
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

public class CoinPouchItem extends Item {

    public CoinPouchItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            setActive(stack, !getActive(stack));
            return new InteractionResultHolder<>(InteractionResult.PASS, stack);
        }else if (player instanceof ServerPlayer){
            PlayerSlot slot = new PlayerSlot(player, hand);
            NetworkHooks.openGui((ServerPlayer) player, new CoinPouchContainer.Provider(stack, slot), slot::toBuff);
        }
        return InteractionResultHolder.pass(stack);
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
        if (!(itemStack.getItem() instanceof CoinPouchItem)) throw new AssertionError("Unexpected CoinPouchItem type");
        CoinPouchItem itemKeyRing = (CoinPouchItem)itemStack.getItem();

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

        IItemHandlerModifiable itemStackHandlerCoinPouch =  (IItemHandlerModifiable)itemKeyRing.getItemStackHandlerCoinPouch(itemStack);
        for (int i = 0; i < itemStackHandlerCoinPouch.getSlots(); i++) {
            ItemStack coin = itemStackHandlerCoinPouch.getStackInSlot(i);
            ItemStack coinsWhichDidNotFit = ItemHandlerHelper.insertItemStacked(tileInventory, coin, false);
            itemStackHandlerCoinPouch.setStackInSlot(i, coinsWhichDidNotFit);
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
        return new ItemStackInventory(stack, 9*2).setStackValidator(CoinPouchItem::isCoin);
    }

    @Nullable
    private static IItemHandler getItemStackHandlerCoinPouch(ItemStack itemStack) {
        LazyOptional<IItemHandler> optional = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        if (optional.isPresent()) {
            return optional.orElseThrow(RuntimeException::new);
        }
        return null; //If this returns null then someone has broken something.
    }

    private static boolean isCoin(ItemStack stack) {
        return stack.is(TolkienTags.items.COINS);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int itemSlot, boolean isSelected) {
        //if (world.getDayTime() % 20 == 0) return;
        if (entity instanceof Player player && getActive(stack)) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack coinStack = player.getInventory().getItem(i);
                if (stack.is(TolkienTags.items.COINS))
                    addCoinToInventory(stack, coinStack);
            }
        }
    }

    public static ItemStack addCoinToInventory(ItemStack keyHolder, ItemStack coin) {
        IItemHandler handler = keyHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(new ItemStackHandler(CoinPouchContainer.CARRIED_SLOT_SIZE));
        List<Integer> emptySlots = new ArrayList<>();
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stackInSlot = handler.getStackInSlot(i);
            if (stackInSlot.isEmpty()) emptySlots.add(i);
            if (!stackInSlot.isEmpty() && ItemStack.isSameItemSameTags(stackInSlot, coin)) {
                int j = stackInSlot.getCount() + coin.getCount();
                int maxSize = 64;
                if (j <= maxSize) {
                    coin.setCount(0);
                    stackInSlot.setCount(j);
                } else if (stackInSlot.getCount() < maxSize) {
                    coin.shrink(maxSize - stackInSlot.getCount());
                    stackInSlot.setCount(maxSize);
                }
                if (coin.isEmpty()) {
                    return coin;
                }
            }
        }
        if (emptySlots.isEmpty()) return coin;
        handler.insertItem(emptySlots.get(0), coin.split(coin.getCount()), false);
        return coin;
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

    public static float getFullnessPropertyOverride(ItemStack itemStack, @Nullable ClientLevel world, @Nullable LivingEntity entity, int i) {
        IItemHandler coinPouch = getItemStackHandlerCoinPouch(itemStack);
        if (coinPouch == null) return 0;
        int count = 0;
        int j = i;
        for (j = 0; j < coinPouch.getSlots(); j++) {
            count+=coinPouch.getStackInSlot(j).getCount();
        }
        float fractionEmpty = count / (float)(coinPouch.getSlots() * 64);
        return 0.0F + fractionEmpty;
    }
}