package com.greatorator.tolkienmobs.item.tools;

import codechicken.lib.inventory.InventoryUtils;
import com.brandon3055.brandonscore.inventory.PlayerSlot;
import com.greatorator.tolkienmobs.container.KeyRingContainer;
import com.greatorator.tolkienmobs.container.capability.ItemStackInventory;
import com.greatorator.tolkienmobs.handler.LoreItem;
import com.greatorator.tolkienmobs.handler.TTMTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TTMContent.KEY_RING;

public class KeyRingItem extends LoreItem {

    public KeyRingItem(Item.Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player instanceof ServerPlayerEntity) {
            PlayerSlot slot = new PlayerSlot(player, hand);
            NetworkHooks.openGui((ServerPlayerEntity) player, new KeyRingContainer.Provider(stack, slot), slot::toBuff);
        }
        return ActionResult.pass(stack);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext ctx) {
        World world = ctx.getLevel();
        PlayerEntity player = ctx.getPlayer();
        if (!(player instanceof ServerPlayerEntity)) return ActionResultType.PASS;

        BlockPos pos = ctx.getClickedPos();
        Direction side = ctx.getClickedFace();
        ItemStack itemStack = ctx.getItemInHand();
        if (!(itemStack.getItem() instanceof KeyRingItem)) throw new AssertionError("Unexpected KeyRingItem type");
        KeyRingItem itemKeyRing = (KeyRingItem)itemStack.getItem();

        TileEntity tileEntity = world.getBlockEntity(pos);

        if (tileEntity == null) return ActionResultType.PASS;
        if (world.isClientSide()) return ActionResultType.SUCCESS; // always succeed on client side

        IItemHandler tileInventory;
        LazyOptional<IItemHandler> capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
        if (capability.isPresent()) {
            tileInventory = capability.orElseThrow(AssertionError::new);
        } else if (tileEntity instanceof IInventory) {
            tileInventory = new InvWrapper((IInventory)tileEntity);
        } else {
            return ActionResultType.FAIL;
        }

        IItemHandlerModifiable itemStackHandlerKeyRing =  (IItemHandlerModifiable)itemKeyRing.getItemStackHandlerKeyRing(itemStack);
        for (int i = 0; i < itemStackHandlerKeyRing.getSlots(); i++) {
            ItemStack key = itemStackHandlerKeyRing.getStackInSlot(i);
            ItemStack keysWhichDidNotFit = ItemHandlerHelper.insertItemStacked(tileInventory, key, false);
            itemStackHandlerKeyRing.setStackInSlot(i, keysWhichDidNotFit);
        }
        tileEntity.setChanged();           // make sure that the tileEntity knows we have changed its contents

        CompoundNBT nbt = itemStack.getOrCreateTag();
        int dirtyCounter = nbt.getInt("dirtyCounter");
        nbt.putInt("dirtyCounter", dirtyCounter + 1);
        itemStack.setTag(nbt);

        return ActionResultType.SUCCESS;
    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
        return new ItemStackInventory(stack, 9*2).setStackValidator(KeyRingItem::isKey);
    }

    @Nullable
    private static IItemHandler getItemStackHandlerKeyRing(ItemStack itemStack) {
        LazyOptional<IItemHandler> optional = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        if (optional.isPresent()) {
            return optional.orElseThrow(RuntimeException::new);
        }
        return null; //If this returns null then someone has broken something.
    }

    private static boolean isKey(ItemStack stack) {
        return stack.getItem().is(TTMTags.items.KEYS);
    }

    public static void onItemPickup(EntityItemPickupEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getItem().getItem();
        if (!(player instanceof ServerPlayerEntity) || !isKey(stack)) return;

        for (int i = 0; i < player.inventory.getContainerSize(); i++) {
            ItemStack pouch = player.inventory.getItem(i);
            if (!pouch.isEmpty() && pouch.getItem() == KEY_RING.get()) {
                IItemHandler handler = getItemStackHandlerKeyRing(pouch);
                ItemStack remainder = InventoryUtils.insertItem(handler, stack, false);
                if (remainder.isEmpty()) {
                    event.getItem().setItem(ItemStack.EMPTY);
                    event.getItem().remove();
                    player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.level.random.nextFloat() - player.level.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    event.setCanceled(true);
                    return;
                } else {
                    if (!ItemStack.matches(remainder, stack)) {
                        event.getItem().setItem(remainder);
                        player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.level.random.nextFloat() - player.level.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    public static float getFullnessPropertyOverride(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity) {
        IItemHandler keyRing = getItemStackHandlerKeyRing(itemStack);
        if (keyRing == null) return 0;
        int count = 0;
        for (int i = 0; i < keyRing.getSlots(); i++) {
            count+=keyRing.getStackInSlot(i).getCount();
        }
        float fractionEmpty = count / (float)(keyRing.getSlots());
        return 0.0F + fractionEmpty;
    }
}