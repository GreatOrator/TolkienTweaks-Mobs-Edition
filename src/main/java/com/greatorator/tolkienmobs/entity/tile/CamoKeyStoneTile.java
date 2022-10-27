package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.DataFlags;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.block.CamoKeyStoneBlock.ACTIVE;
import static com.greatorator.tolkienmobs.block.CamoKeyStoneBlock.POWERED;

public class CamoKeyStoneTile extends TileBCore implements IRSSwitchable, IInteractTile, INamedContainerProvider, ITickableTileEntity {
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

    public CamoKeyStoneTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public CamoKeyStoneTile() {
        super(TTMContent.KEY_STONE_TILE.get());
    }

    public void onRightClick(BlockState state, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);

        if (playerEntity.isCreative() && playerEntity.isCrouching()) {
            openGUI(playerEntity, this, worldPosition);
        } else if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getKey(stack).equals(keyCode.get()))) {
            redstoneMode(state, level, worldPosition, playerEntity, hand);
        } else {
            playerEntity.sendMessage(new TranslationTextComponent(MODID + ".msg.wrong_key").withStyle(TextFormatting.RED), Util.NIL_UUID);
        }

        if (keyConsume.get() && !playerEntity.isCreative()) {
            stack.shrink(1);
        }
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    public void redstoneMode(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand) {
        if (rsAlways.get()) {
            world.setBlockAndUpdate(blockPos, world.getBlockState(blockPos).cycle(ACTIVE));
            world.playSound((PlayerEntity)null, blockPos, SoundEvents.LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);
            world.setBlock(blockPos, world.getBlockState(blockPos).cycle(POWERED), 3);
        } else if (rsPulse.get()) {
            world.setBlockAndUpdate(blockPos, world.getBlockState(blockPos).setValue(ACTIVE, true));
            world.setBlock(blockPos, world.getBlockState(blockPos).setValue(POWERED, true), 3);
            blockPowered.set(true);
            blockActive.set(true);
            world.playSound((PlayerEntity)null, blockPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
        } else if (rsDelay.get() && tickDelay.get() > 0) {
            world.setBlockAndUpdate(blockPos, world.getBlockState(blockPos).setValue(ACTIVE, true));
            world.setBlock(blockPos, world.getBlockState(blockPos).setValue(POWERED, true), 3);
            blockPowered.set(true);
            blockActive.set(true);
            world.playSound((PlayerEntity)null, blockPos, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
        }
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerBCTile<>(TTMContent.KEY_STONE_CONTAINER, windowID, playerInventory, this);
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
                level.playSound((PlayerEntity)null, worldPosition, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.4F);

                activeTime = 0;
            }
            activeTime++;
        } else if (rsDelay.get() && blockPowered.get() && blockActive.get()) {
            if (activeTime == tickDelay.get()) {
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(ACTIVE, false).setValue(POWERED, false));
                blockActive.set(false);
                blockPowered.set(false);
                level.playSound((PlayerEntity)null, worldPosition, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.4F);

                activeTime = 0;
            }
            activeTime++;
        }
    }
}