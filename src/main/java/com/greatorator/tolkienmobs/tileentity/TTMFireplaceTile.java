package com.greatorator.tolkienmobs.tileentity;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedInt;
import com.brandon3055.brandonscore.lib.datamanager.ManagedShort;
import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.BlockTTMFireplace;
import com.greatorator.tolkienmobs.crafting.IFireplaceInventory;
import com.greatorator.tolkienmobs.handler.interfaces.IFireplaceRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.List;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_CONTAINER;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.util.Direction.*;

public class TTMFireplaceTile extends TileBCore implements ITickableTileEntity, INamedContainerProvider, IRSSwitchable, IFireplaceInventory {
    public static final ContainerSlotLayout.LayoutFactory<TTMFireplaceTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    public final ManagedBool isBurning = register(new ManagedBool("is_burning", false, SAVE_BOTH_SYNC_TILE));
    /**
     * The fuel value of the last item that was consumed.
     */
    public final ManagedInt fuelValue = register(new ManagedInt("fuel_value", 1, SAVE_BOTH_SYNC_CONTAINER));
    /**
     * The remaining fuel value from the last item that was consumed.
     */
    public final ManagedInt fuelRemaining = register(new ManagedInt("fuel_remaining", 0, SAVE_BOTH_SYNC_CONTAINER));
    /**
     * The total time required for the current recipe.
     */
    public final ManagedShort recipeTime = register(new ManagedShort("recipe_time", (short) 300, SAVE_BOTH_SYNC_CONTAINER));
    /**
     * The amount of time the current recipe has been running.
     */
    public final ManagedShort recipeProgress = register(new ManagedShort("recipe_progress", (short) 0, SAVE_BOTH_SYNC_CONTAINER));

    public TileItemStackHandler itemHandler = new TileItemStackHandler(4);

    public TTMFireplaceTile() {
        super(TTMContent.TMFIREPLACE_TILE.get());
        //Install item handler capability but don't expose it to other tiles.
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler).saveBoth().syncTile();
        //Exposed item capability by also wrapping it in a handler that lets us control IO
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot < 2).setExtractCheck((slot, stack) -> slot == 3), UP, DOWN, null);
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot == 2).setExtractCheck((slot, stack) -> slot == 3), NORTH, SOUTH, EAST, WEST, null);
        //Set up slot input validation.
        itemHandler.setStackValidator((slot, stack) -> slot <= 1 && isInput(stack) || slot == 2 && FurnaceTileEntity.isFuel(stack));
        //Add inventory change listener
        itemHandler.setContentsChangeListener(i -> inventoryChange());
    }

    private boolean isInput(ItemStack stack) {
        return true;
    }

    private boolean canCraft(IFireplaceRecipe recipe) {
        if (recipe != null) {
            ItemStack itemstack = recipe.assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack outSlot = itemHandler.getStackInSlot(3);
                if (outSlot.isEmpty()) {
                    return true;
                } else if (!outSlot.sameItem(itemstack)) {
                    return false;
                } else if (outSlot.getCount() + itemstack.getCount() <= this.getMaxStackSize() && outSlot.getCount() + itemstack.getCount() <= outSlot.getMaxStackSize()) {
                    return true;
                } else {
                    return outSlot.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private void completeCraft(IFireplaceRecipe recipe) {
        if (canCraft(recipe)) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.test(itemHandler.getStackInSlot(0))) {
                    itemHandler.extractItem(0, 1, false);
                } else if (ingredient.test(itemHandler.getStackInSlot(1))) {
                    itemHandler.extractItem(1, 1, false);
                }
            }

            ItemStack result = recipe.assemble(this);
            ItemStack outputSlot = itemHandler.getStackInSlot(3).copy();
            if (outputSlot.isEmpty()) {
                itemHandler.setStackInSlot(3, result);
            } else {
                outputSlot.grow(result.getCount());
                itemHandler.setStackInSlot(3, outputSlot);
            }
        }
    }

    private void inventoryChange() {
        IFireplaceRecipe recipe = level.getRecipeManager().getRecipeFor(TolkienMobs.FIREPLACE_RECIPE_TYPE, this, level).orElse(null);
        if (fuelRemaining.get() <= 0 && isTileEnabled() && canCraft(recipe)) {
            tryRefuel();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level == null || level.isClientSide) return;

        boolean last = isBurning.get();
        isBurning.set(fuelRemaining.get() > 0);
        if (isBurning.get() != last) {
            level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(BlockTTMFireplace.ACTIVE, isBurning.get()));
        }

        if (isBurning.get()) {
            fuelRemaining.dec();
        } else {
            return;
        }

        IFireplaceRecipe recipe = level.getRecipeManager().getRecipeFor(TolkienMobs.FIREPLACE_RECIPE_TYPE, this, level).orElse(null);

        if (isBurning.get() && isTileEnabled()) {
            if (canCraft(recipe)) {
                recipeProgress.inc();
                if (recipeProgress.get() >= recipeTime.get()) {
                    completeCraft(recipe);
                    recipeProgress.zero();
                }
            } else {
                recipeProgress.zero();
            }
        } else if (recipeProgress.get() > 0) {
            if (canCraft(recipe)) {
                recipeProgress.dec();
            } else {
                recipeProgress.zero();
            }
        }

        if (fuelRemaining.get() <= 0 && isTileEnabled() && canCraft(recipe)) {
            tryRefuel();
        }
    }

    public void tryRefuel() {
        ItemStack stack = itemHandler.getStackInSlot(2);
        if (!stack.isEmpty()) {
            int itemBurnTime = ForgeHooks.getBurnTime(stack);
            if (itemBurnTime > 0) {
                if (stack.getCount() == 1) {
                    stack = stack.getItem().getContainerItem(stack);
                } else {
                    stack.shrink(1);
                }
                fuelValue.set(itemBurnTime);
                fuelRemaining.add(itemBurnTime);
                itemHandler.setStackInSlot(2, stack);
            }
        }
    }

    public void awardUsedRecipesAndPopExperience(PlayerEntity player) {
        List<IRecipe<?>> list = this.getRecipesToAwardAndPopExperience(player.level, player.position());
        player.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public List<IRecipe<?>> getRecipesToAwardAndPopExperience(World world, Vector3d vector3d) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            world.getRecipeManager().byKey(entry.getKey()).ifPresent((p_235642_4_) -> {
                list.add(p_235642_4_);
                createExperience(world, vector3d, entry.getIntValue(), ((AbstractCookingRecipe)p_235642_4_).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(World world, Vector3d vector3d, int amount, float exp) {
        int i = MathHelper.floor((float)amount * exp);
        float f = MathHelper.frac((float)amount * exp);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        while(i > 0) {
            int j = ExperienceOrbEntity.getExperienceValue(i);
            i -= j;
            world.addFreshEntity(new ExperienceOrbEntity(world, vector3d.x, vector3d.y, vector3d.z, j));
        }

    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new ContainerBCTile<>(TTMContent.TMFIREPLACE_CONTAINER, id, playerInventory, this, SLOT_LAYOUT);
    }

    @Override
    public IItemHandler getItemHandler() {
        return itemHandler;
    }
}