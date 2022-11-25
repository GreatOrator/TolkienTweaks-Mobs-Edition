package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
import com.brandon3055.brandonscore.lib.datamanager.ManagedShort;
import com.greatorator.tolkienmobs.block.TrinketTableBlock;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import com.greatorator.tolkienmobs.recipe.TrinketRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_CONTAINER;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.core.Direction.*;

public class TrinketTableTile extends TileBCore implements MenuProvider, IInteractTile, IRSSwitchable {
    public static final ContainerSlotLayout.LayoutFactory<TrinketTableTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
    public final ManagedInt recipeProgress = register(new ManagedInt("recipe_progress", 0, SAVE_BOTH_SYNC_CONTAINER));
    public final ManagedInt fuelValue = register(new ManagedInt("fuel_value", 200, SAVE_BOTH_SYNC_CONTAINER));
    public final ManagedInt fuelRemaining = register(new ManagedInt("fuel_remaining", 0, SAVE_BOTH_SYNC_CONTAINER));
    public final ManagedShort recipeTime = register(new ManagedShort("recipe_time", (short) 200, SAVE_BOTH_SYNC_CONTAINER));
    public final ManagedBool isBurning = register(new ManagedBool("is_burning", false, SAVE_BOTH_SYNC_TILE));
    public TileItemStackHandler itemHandler = new TileItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public TrinketTableTile(BlockPos blockPos, BlockState blockState) {
        super(TolkienTiles.TRINKETTABLE_TILE.get(), blockPos, blockState);

        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler).saveBoth().syncTile();
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot < 2).setExtractCheck((slot, stack) -> slot == 3), UP, DOWN, null);
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot == 2).setExtractCheck((slot, stack) -> slot == 3), NORTH, SOUTH, EAST, WEST, null);

        itemHandler.setStackValidator((slot, stack) -> slot == 1 && isInput(stack) || slot == 2 && stack.is(TolkienItems.GEM_AMMOLITE.get()));
    }

    public void onRightClick(Player playerEntity, InteractionHand hand) {
        if (!playerEntity.level.isClientSide()) {
            NetworkHooks.openGui((ServerPlayer) playerEntity, this, worldPosition);
        }
    }

    private boolean isInput(ItemStack stack) {
        return true;
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide) return;

        boolean last = isBurning.get();
        isBurning.set(fuelRemaining.get() > 0);
        if (isBurning.get() != last) {
            level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(TrinketTableBlock.LIT, isBurning.get()));
        }

        if(hasRecipe(this)) {
            this.recipeProgress.inc();
            setChanged();
            if (this.recipeProgress.get() > this.fuelValue.get()) {
                craftItem(this);
            } else {
                this.resetProgress();
                setChanged();
            }
        }
    }

    private boolean hasRecipe(TrinketTableTile entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<TrinketRecipe> match = level.getRecipeManager().getRecipeFor(TrinketRecipe.Type.INSTANCE, inventory, level);
        if(match.isPresent()) {
            ItemStack itemstack = match.get().assemble((Container) this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack outSlot = match.get().getResultItem();
                if (outSlot.isEmpty()) {
                    return true;
                } else if (!outSlot.sameItem(itemstack)) {
                    return false;
                } else if (outSlot.getCount() + itemstack.getCount() <= ((Container) this).getMaxStackSize() && outSlot.getCount() + itemstack.getCount() <= outSlot.getMaxStackSize()) {
                    return true;
                } else {
                    return outSlot.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private void craftItem(TrinketTableTile entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<TrinketRecipe> match = level.getRecipeManager().getRecipeFor(TrinketRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            for (Ingredient ingredient : match.get().getIngredients()) {
                if (ingredient.test(itemHandler.getStackInSlot(0))) {
                    itemHandler.extractItem(0, 1, false);
                    itemHandler.extractItem(1, 1, false);
                    itemHandler.extractItem(2,1, false);}
            }

            ItemStack result = match.get().assemble((Container) this);
            ItemStack outputSlot = itemHandler.getStackInSlot(3).copy();
            if (outputSlot.isEmpty()) {
                itemHandler.setStackInSlot(3, result);
            } else {
                outputSlot.grow(result.getCount());
                itemHandler.setStackInSlot(3, outputSlot);
            }
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.recipeProgress.set(0);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.TRINKETTABLE_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
    }
}