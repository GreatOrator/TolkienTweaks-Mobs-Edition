package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.DataFlags;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.block.CamoKeyStoneBlock.ACTIVE;
import static com.greatorator.tolkienmobs.block.CamoKeyStoneBlock.POWERED;

public class CamoKeyStoneTile extends TileBCore implements IRSSwitchable, IInteractTile, MenuProvider {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public final ManagedString keyCode = register(new ManagedString("KeyCode", "Set_Code", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool keyConsume = register(new ManagedBool("consume_key", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool rsAlways = register(new ManagedBool("redstone_toggle", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool rsPulse = register(new ManagedBool("redstone_pulse", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool rsDelay = register(new ManagedBool("redstone_delay", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool blockActive = register(new ManagedBool("block_active", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool blockPowered = register(new ManagedBool("block_powered", DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedInt tickDelay = register(new ManagedInt("tick_delay", 100, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedInt timeActive = register(new ManagedInt("time_active", 30, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));

    private int activeTime = 0;

    public CamoKeyStoneTile(BlockPos pos, BlockState state) {
        super(TolkienTiles.KEY_STONE_TILE.get(), pos, state);
    }

    public void onRightClick(BlockState state, Player playerEntity, InteractionHand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);

        if (playerEntity.isCreative() && playerEntity.isCrouching()) {
            NetworkHooks.openGui((ServerPlayer) playerEntity, this, worldPosition);
        } else if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getCode(stack).equals(keyCode.get()))) {
            redstoneMode(state, level, worldPosition, playerEntity, hand);
            int uses = KeyBaseItem.getUses(stack);

            if (KeyBaseItem.getUses(stack) >= 0) {
                level.sendBlockUpdated(worldPosition, state, state, 3);

                if (uses == 0) {
                    stack.shrink(1);
                    level.playSound((Player) null, worldPosition, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 0.3F, 0.6F);
                    playerEntity.sendMessage(new TranslatableComponent(MODID + ".msg.key_used").withStyle(ChatFormatting.RED), Util.NIL_UUID);
                }
                uses--;
                KeyBaseItem.setUses(stack, uses);
            }
        } else {
            playerEntity.sendMessage(new TranslatableComponent(MODID + ".msg.wrong_key").withStyle(ChatFormatting.RED), Util.NIL_UUID);
        }

        if (keyConsume.get() && !playerEntity.isCreative()) {
            stack.shrink(1);
        }
    }

    public void redstoneMode(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand) {
        if (rsAlways.get()) {
            world.setBlockAndUpdate(blockPos, world.getBlockState(blockPos).cycle(ACTIVE));
            world.playSound((Player)null, blockPos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.6F);
            world.setBlock(blockPos, world.getBlockState(blockPos).cycle(POWERED), 3);
        } else if (rsPulse.get()) {
            world.setBlockAndUpdate(blockPos, world.getBlockState(blockPos).setValue(ACTIVE, true));
            world.setBlock(blockPos, world.getBlockState(blockPos).setValue(POWERED, true), 3);
            blockPowered.set(true);
            blockActive.set(true);
            world.playSound((Player)null, blockPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
        } else if (rsDelay.get() && tickDelay.get() > 0) {
            world.setBlockAndUpdate(blockPos, world.getBlockState(blockPos).setValue(ACTIVE, true));
            world.setBlock(blockPos, world.getBlockState(blockPos).setValue(POWERED, true), 3);
            blockPowered.set(true);
            blockActive.set(true);
            world.playSound((Player)null, blockPos, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.KEY_STONE_CONTAINER, windowID, playerInventory, this);
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide()) return;

        if (rsPulse.get() && blockPowered.get() && blockActive.get()) {
            if (activeTime == timeActive.get()) {
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(ACTIVE, false).setValue(POWERED, false));
                blockActive.set(false);
                blockPowered.set(false);
                level.playSound((Player)null, worldPosition, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.4F);

                activeTime = 0;
            }
            activeTime++;
        } else if (rsDelay.get() && blockPowered.get() && blockActive.get()) {
            if (activeTime == tickDelay.get()) {
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(ACTIVE, false).setValue(POWERED, false));
                blockActive.set(false);
                blockPowered.set(false);
                level.playSound((Player)null, worldPosition, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.4F);

                activeTime = 0;
            }
            activeTime++;
        }
    }
}