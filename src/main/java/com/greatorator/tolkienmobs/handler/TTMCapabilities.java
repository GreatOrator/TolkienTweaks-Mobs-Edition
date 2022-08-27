package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.entity.tile.inventory.ITTMBackpack;
import com.greatorator.tolkienmobs.entity.tile.inventory.TTMBackpackCapabilities;
import com.greatorator.tolkienmobs.entity.tile.inventory.TTMBackpackInventory;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.TTMCurios;
import com.greatorator.tolkienmobs.item.tools.ItemTTMBackpack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class TTMCapabilities {
    public static LazyOptional<ITTMBackpack> getCapability(final PlayerEntity player)
    {
        return player.getCapability(TTMBackpackCapabilities.TRAVELERS_BACKPACK_CAPABILITY, TTMBackpackCapabilities.DEFAULT_FACING);
    }

    public static void synchronise(PlayerEntity player)
    {
        TTMCapabilities.getCapability(player)
                .ifPresent(ITTMBackpack::synchronise);
    }

    public static void synchroniseToOthers(PlayerEntity player)
    {
        TTMCapabilities.getCapability(player)
                .ifPresent(i -> i.synchroniseToOthers(player));
    }

    public static boolean isWearingBackpack(PlayerEntity player)
    {
        if(TTMHelper.isCuriosInstalled)
        {
            return TTMCurios.getCurioBackpack(player).isPresent();
        }

        LazyOptional<ITTMBackpack> cap = getCapability(player);
        ItemStack backpack = cap.lazyMap(ITTMBackpack::getWearable).orElse(ItemStack.EMPTY);

        return cap.map(ITTMBackpack::hasWearable).orElse(false) && backpack.getItem() instanceof ItemTTMBackpack;
    }

    public static ItemStack getWearingBackpack(PlayerEntity player)
    {
        if(TTMHelper.isCuriosInstalled)
        {
            return TTMCurios.getCurioBackpackStack(player);
        }

        LazyOptional<ITTMBackpack> cap = getCapability(player);
        ItemStack backpack = cap.map(ITTMBackpack::getWearable).orElse(ItemStack.EMPTY);

        return isWearingBackpack(player) ? backpack : ItemStack.EMPTY;
    }

    public static void equipBackpack(PlayerEntity player, ItemStack stack)
    {
        LazyOptional<ITTMBackpack> cap = getCapability(player);

        if(!cap.map(ITTMBackpack::hasWearable).orElse(false))
        {
            cap.ifPresent(inv -> inv.setWearable(stack));
            cap.ifPresent(inv -> inv.setContents(stack));
            player.level.playSound(null, player.blockPosition(), SoundEvents.ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 1.0F, (1.0F + (player.level.random.nextFloat() - player.level.random.nextFloat()) * 0.2F) * 0.7F);

            //Sync
            synchronise(player);
            synchroniseToOthers(player);
        }
    }

    @Nullable
    public static TTMBackpackInventory getBackpackInv(PlayerEntity player)
    {
        ItemStack wearable = getWearingBackpack(player);

        if(wearable.getItem() instanceof ItemTTMBackpack)
        {
            return TTMCapabilities.getCapability(player).map(ITTMBackpack::getInventory).orElse(null);
        }
        return null;
    }
}
