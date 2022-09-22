package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.*;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Locale;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;

public class CamoKeyStoneTile extends TileBCore implements IRSSwitchable, IInteractTile, INamedContainerProvider, ITickableTileEntity {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public final ManagedString keyCode = register(new ManagedString("KeyCode", "Set Code...", SAVE_BOTH_SYNC_TILE));
    public final ManagedBool keyConsume = register(new ManagedBool("consume_key", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedBool rsAlways = register(new ManagedBool("consume_key", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedBool rsPulse = register(new ManagedBool("consume_key", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedBool rsDelay = register(new ManagedBool("consume_key", DataFlags.SAVE_NBT_SYNC_TILE));
    public final ManagedLong tickDelay = register(new ManagedLong("tick_delay", 100, SAVE_BOTH_SYNC_TILE ));
    public final ManagedInt powerLevel = register(new ManagedInt("power_level", 0, SAVE_BOTH_SYNC_TILE ));
    public final ManagedEnum<Mode> keyMode = register(new ManagedEnum<>("mode", Mode.NORMAL, SAVE_BOTH_SYNC_TILE));

    public CamoKeyStoneTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public CamoKeyStoneTile() {
        super(TTMContent.KEY_STONE_TILE.get());
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);

        if (!playerEntity.level.isClientSide()) {
            openGUI(playerEntity, this, worldPosition);
        } else if (stack.getItem() instanceof KeyBaseItem && (((KeyBaseItem) stack.getItem()).getKey(stack).equals(keyCode.get()))) {
            LOGGER.info("You have the right key!");
        } else {
            LOGGER.info("Something broke");
        }
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void setKeystoneCode(String key) {
        sendPacketToServer(output -> output.writeString(key), 0);
        keyCode.set(key);
        LOGGER.info("Key Stone set: " + keyCode.get());
    }

    public void setTickDelayCode(String text) {
        sendPacketToServer(output -> output.writeString(text), 1);
        tickDelay.set(Long.parseLong(text));
        LOGGER.info("Tick Delay set: " + tickDelay.get());
    }

    public enum Mode {
        PULSE(0, 50, 5, 0.3F),
        TOGGLE(1, 15, 20, 0.4F),
        NORMAL(2, 10, 40, 0.6F);

        public final int index;
        public final int energyPerFuelUnit;
        public final int powerOutput;
        private float animFanSpeed;

        Mode(int index, int energyPerFuelUnit, int powerOutput, float animFanSpeed) {
            this.index = index;
            this.energyPerFuelUnit = energyPerFuelUnit;
            this.powerOutput = powerOutput;
            this.animFanSpeed = animFanSpeed;
        }

        public Mode next(boolean prev) {
            if (prev) {
                return values()[index - 1 < 0 ? values().length - 1 : index - 1];
            }
            return values()[index + 1 == values().length ? 0 : index + 1];
        }

        public int getEfficiency() {
            return (int) ((energyPerFuelUnit / 10F) * 100F);
        }

        public String unlocalizedName() {
            return "screen.tolkienmobs.keystone.mode_" + name().toLowerCase(Locale.ENGLISH);
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
    }
}