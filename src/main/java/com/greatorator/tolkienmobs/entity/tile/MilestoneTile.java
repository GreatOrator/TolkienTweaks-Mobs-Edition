package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.vec.Vector3;
import com.brandon3055.brandonscore.api.TimeKeeper;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.TeleportUtils;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
import com.brandon3055.brandonscore.lib.datamanager.ManagedStack;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.brandon3055.brandonscore.network.BCoreNetwork;
import com.greatorator.tolkienmobs.block.MilestoneBlock;
import com.greatorator.tolkienmobs.handler.MilestoneHandler;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_NBT_SYNC_TILE;

public class MilestoneTile extends TileBCore implements MenuProvider, IRSSwitchable, IInteractTile {
    public final ManagedString milestoneName = register(new ManagedString("milestone_name", "Unnamed_Milestone", SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedStack paymentItem = register(new ManagedStack("payment_item", SAVE_NBT_SYNC_TILE));
    //The distance a single item will allow you to travel
    public final ManagedInt distanceCost = register(new ManagedInt("distance_cost", SAVE_NBT_SYNC_TILE));
    //The cost for an interdimensional trip
    public final ManagedInt dimensionCost = register(new ManagedInt("dimension_cost", SAVE_NBT_SYNC_TILE));
    private final ManagedString milestoneUUID = register(new ManagedString("milestone_uuid", SAVE_NBT_SYNC_TILE));

    public MilestoneTile(BlockPos pos, BlockState state) {
        super(TolkienTiles.MILESTONE_TILE.get(), pos, state);
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
        if (MilestoneHandler.isKnownByClient(getUUID(), Minecraft.getInstance().player.getUUID())) {
            BlockState state = level.getBlockState(worldPosition);
            if (state.getBlock() == TolkienBlocks.MILESTONE_BLOCK.get() && !state.getValue(MilestoneBlock.LIT)) {
                level.setBlock(worldPosition, state.setValue(MilestoneBlock.LIT, true), 0);
            }
        }
    }

    @Override
    public InteractionResult onBlockUse(BlockState state, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            if (!player.isCreative()) {
                MilestoneHandler.addPlayerToMilestone(this, player);
            }

            openGUI(player, this, worldPosition);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.MILESTONE_CONTAINER, windowID, playerInventory, this);
    }

    public void openGUI(Player player, MenuProvider containerSupplier, BlockPos pos) {
        if (!player.level.isClientSide) {
            NetworkHooks.openGui((ServerPlayer) player, containerSupplier, pos);
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

    public ItemStack getTravelCost(MilestoneHandler.MilestoneData dest) {
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
    public void receivePacketFromClient(MCDataInput input, ServerPlayer client, int id) {
        super.receivePacketFromClient(input, client, id);

        if (id <= 2 && !client.isCreative()) return;

        switch (id) {
            case 0:
                ItemStack stack = client.getItemInHand(InteractionHand.MAIN_HAND).copy();
                stack.setCount(1);
                client.sendMessage(new TranslatableComponent("tolkienmobs.msg.payment").append(stack.getItem().getName(stack)), Util.NIL_UUID);
                paymentItem.set(stack);
                MilestoneHandler.updateMilestone(this);
                break;
            case 1:
                distanceCost.set(input.readVarInt());
                MilestoneHandler.updateMilestone(this);
                break;
            case 2:
                dimensionCost.set(input.readVarInt());
                MilestoneHandler.updateMilestone(this);
                break;
            case 3:
                MilestoneHandler.MilestoneData data = MilestoneHandler.getMilestoneData(level, input.readUUID());
                if (data == null) {
                    client.sendMessage(new TranslatableComponent("tolkienmobs.msg.destination"), Util.NIL_UUID);
                    break;
                }

                ItemStack cost = getTravelCost(data);
                if (!cost.isEmpty()) {
                    int found = 0;
                    for (ItemStack item : client.getInventory().items) {
                        if (item.sameItem(cost)) found += item.getCount();
                    }
                    if (found < cost.getCount()) {
                        client.sendMessage(new TranslatableComponent("tolkienmobs.msg.payment.insufficient"), Util.NIL_UUID);
                        break;
                    }
                    int needed = cost.getCount();
                    for (ItemStack item : client.getInventory().items) {
                        if (item.sameItem(cost)) {
                            int count = item.getCount();
                            item.shrink(Math.min(count, needed));
                            needed -= count;
                            if (needed <= 0) break;
                        }
                    }
                }

                BCoreNetwork.sendSound(client.level, client.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.3F, 0.5F, false);
                TeleportUtils.teleportEntity(client, data.getWorldKey(), Vector3.fromBlockPosCenter(data.getPos()).add(0, 1, 0));
                BCoreNetwork.sendSound(client.level, client.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.3F, 0.5F, false);

                break;
        }
    }
}