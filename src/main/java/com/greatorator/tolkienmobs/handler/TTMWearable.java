package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.tile.inventory.ITTMBackpack;
import com.greatorator.tolkienmobs.entity.tile.inventory.TTMBackpackInventory;
import com.greatorator.tolkienmobs.network.BackpackPacketHandler;
import com.greatorator.tolkienmobs.utils.TTMReference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.PacketDistributor;

public class TTMWearable implements ITTMBackpack {
    private ItemStack wearable = ItemStack.EMPTY;
    private final PlayerEntity playerEntity;
    private final TTMBackpackInventory inventory;

    public TTMWearable(final PlayerEntity playerEntity)
    {
        this.playerEntity = playerEntity;
        this.inventory = new TTMBackpackInventory(this.wearable, playerEntity, TTMReference.WEARABLE_SCREEN_ID);
    }

    @Override
    public boolean hasWearable()
    {
        return !this.wearable.isEmpty();
    }

    @Override
    public ItemStack getWearable()
    {
        return this.wearable;
    }

    @Override
    public void setWearable(ItemStack stack)
    {
        this.wearable = stack;
    }

    @Override
    public void removeWearable()
    {
        this.wearable = ItemStack.EMPTY;
        this.inventory.setStack(ItemStack.EMPTY);
    }

    public TTMBackpackInventory getInventory()
    {
        return this.inventory;
    }

    @Override
    public void setContents(ItemStack stack)
    {
        this.inventory.setStack(stack);

        if(!stack.isEmpty())
        {
            this.inventory.loadAllData(stack.getOrCreateTag());
        }
    }

    @Override
    public void synchronise()
    {
        if(playerEntity != null && !playerEntity.level.isClientSide)
        {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)playerEntity;
            TTMCapabilities.getCapability(serverPlayerEntity).ifPresent(cap -> TolkienMobs.NETWORK.send(PacketDistributor.PLAYER.with(() -> serverPlayerEntity), new BackpackPacketHandler(this.wearable.save(new CompoundNBT()), serverPlayerEntity.getId())));
        }
    }

    @Override
    public void synchroniseToOthers(PlayerEntity player)
    {
        if(player != null && !player.level.isClientSide)
        {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            TTMCapabilities.getCapability(serverPlayerEntity).ifPresent(cap -> TolkienMobs.NETWORK.send(PacketDistributor.TRACKING_ENTITY.with(() -> serverPlayerEntity), new BackpackPacketHandler(this.wearable.save(new CompoundNBT()), serverPlayerEntity.getId())));
        }
    }

    @Override
    public CompoundNBT saveTag() {
        return null;
    }

    @Override
    public void loadTag(CompoundNBT compoundTag) {

    }
}
