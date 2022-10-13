package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.vec.Vector3;
import com.brandon3055.brandonscore.api.TimeKeeper;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.TeleportUtils;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
import com.brandon3055.brandonscore.lib.datamanager.ManagedStack;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.brandon3055.brandonscore.network.BCoreNetwork;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.MilestoneBlock;
import com.greatorator.tolkienmobs.handler.MilestoneSaveData;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_NBT_SYNC_TILE;

public class MilestoneTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity, IInteractTile {
    public final ManagedString milestoneName = register(new ManagedString("milestone_name", "Unnamed_Milestone", SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedStack paymentItem = register(new ManagedStack("payment_item", SAVE_NBT_SYNC_TILE));
    //The distance a single item will allow you to travel
    public final ManagedInt distanceCost = register(new ManagedInt("distance_cost", SAVE_NBT_SYNC_TILE));
    //The cost for an interdimensional trip
    public final ManagedInt dimensionCost = register(new ManagedInt("dimension_cost", SAVE_NBT_SYNC_TILE));
    private final ManagedString milestoneUUID = register(new ManagedString("milestone_uuid", SAVE_NBT_SYNC_TILE));


    public MilestoneTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public MilestoneTile() {
        super(TTMContent.MILESTONE_TILE.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide && TimeKeeper.getClientTick() % 20 == 0) {
            updateClientState();
        }
    }

    //This is a terrible hack, but it's the simplest way to set the block to active for specific players
    @OnlyIn(Dist.CLIENT)
    public void updateClientState() {
        if (MilestoneSaveData.isKnownByClient(getUUID(), Minecraft.getInstance().player.getUUID())) {
            BlockState state = level.getBlockState(worldPosition);
            if (state.getBlock() == TTMContent.MILESTONE_BLOCK.get() && !state.getValue(MilestoneBlock.ACTIVE)) {
                level.setBlock(worldPosition, state.setValue(MilestoneBlock.ACTIVE, true), 0);
            }
        }
    }

    @Override
    public ActionResultType onBlockUse(BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!level.isClientSide) {
            if (!player.isCreative()) {
                MilestoneSaveData.addPlayerToMilestone(this, player);
            }

            openGUI(player, this, worldPosition);
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerBCTile<>(TTMContent.MILESTONE_CONTAINER, windowID, playerInventory, this);
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos) {
        if (!player.level.isClientSide) {
            NetworkHooks.openGui((ServerPlayerEntity) player, containerSupplier, pos);
        }
    }

    private UUID uuidCache = null;
    public UUID getUUID() {
        if (uuidCache == null) {
            if (milestoneUUID.get().isEmpty()) {
                if (level.isClientSide) {
                    return UUID.randomUUID();
                }
                milestoneUUID.set(UUID.randomUUID().toString());
            }
            uuidCache = UUID.fromString(milestoneUUID.get());
        }
        return uuidCache;
    }

    public ItemStack getTravelCost(MilestoneSaveData.MilestoneData dest) {
        if (dest.getPaymentItem() == Items.AIR) return ItemStack.EMPTY;
        ItemStack stack = new ItemStack(dest.getPaymentItem());

        if (!dest.getWorldKey().getRegistryName().equals(level.dimension().getRegistryName())) {
            stack.setCount(dest.getDimensionCost());
        } else {
            double dist = Math.sqrt(dest.getPos().distSqr(getBlockPos()));
            stack.setCount(Math.max(1, (int) (dist / dest.getDistanceCost())));
        }

        return stack;
    }

    @Override
    public void receivePacketFromClient(MCDataInput input, ServerPlayerEntity client, int id) {
        super.receivePacketFromClient(input, client, id);

        if (id <= 2 && !client.isCreative()) return;

        switch (id) {
            case 0:
                ItemStack stack = client.getItemInHand(Hand.MAIN_HAND).copy();
                stack.setCount(1);
                client.sendMessage(new StringTextComponent("Payment item set to " + stack.getItem()), Util.NIL_UUID);
                paymentItem.set(stack);
                MilestoneSaveData.updateMilestone(this);
                break;
            case 1:
                distanceCost.set(input.readVarInt());
                MilestoneSaveData.updateMilestone(this);
                break;
            case 2:
                dimensionCost.set(input.readVarInt());
                MilestoneSaveData.updateMilestone(this);
                break;
            case 3:
                MilestoneSaveData.MilestoneData data = MilestoneSaveData.getMilestoneData(level, input.readUUID());
                if (data == null) {
                    client.sendMessage(new StringTextComponent("Destination not found"), Util.NIL_UUID);
                    break;
                }

                ItemStack cost = getTravelCost(data);
                if (!cost.isEmpty()) {
                    int found = 0;
                    for (ItemStack item : client.inventory.items) {
                        if (item.sameItem(cost)) found += item.getCount();
                    }
                    if (found < cost.getCount()) {
                        client.sendMessage(new StringTextComponent("Insufficient payment!"), Util.NIL_UUID);
                        break;
                    }
                    int needed = cost.getCount();
                    for (ItemStack item : client.inventory.items) {
                        if (item.sameItem(cost)) {
                            int count = item.getCount();
                            item.shrink(Math.min(count, needed));
                            needed -= count;
                            if (needed <= 0) break;
                        }
                    }
                }

                BCoreNetwork.sendSound(client.level, client.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.3F, 0.5F, false);
                TeleportUtils.teleportEntity(client, data.getWorldKey(), Vector3.fromBlockPosCenter(data.getPos()).add(0, 1, 0));
                BCoreNetwork.sendSound(client.level, client.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.3F, 0.5F, false);

                break;
        }
    }
}