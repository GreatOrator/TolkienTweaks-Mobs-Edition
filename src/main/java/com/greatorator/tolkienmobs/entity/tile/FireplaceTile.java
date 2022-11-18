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
import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.block.FireplaceBlock;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienRecipes;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import com.greatorator.tolkienmobs.recipe.FireplaceRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_CONTAINER;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.core.Direction.*;

public class FireplaceTile extends TileBCore implements MenuProvider, IInteractTile, IRSSwitchable {
    public static final ContainerSlotLayout.LayoutFactory<FireplaceTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
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

    public FireplaceTile(BlockPos blockPos, BlockState blockState) {
        super(TolkienTiles.TMFIREPLACE_TILE.get(), blockPos, blockState);

        //Install item handler capability but don't expose it to other tiles.
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler).saveBoth().syncTile();
        //Exposed item capability by also wrapping it in a handler that lets us control IO
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot < 2).setExtractCheck((slot, stack) -> slot == 3), UP, DOWN, null);
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot == 2).setExtractCheck((slot, stack) -> slot == 3), NORTH, SOUTH, EAST, WEST, null);
        //Set up slot input validation.
        itemHandler.setStackValidator((slot, stack) -> slot <= 1 && isInput(stack) || slot == 2 && AbstractFurnaceBlockEntity.isFuel(stack));
        //Add inventory change listener
        itemHandler.setContentsChangeListener(i -> inventoryChange());
    }

    public void onRightClick(Player playerEntity, InteractionHand hand) {
        if (!playerEntity.level.isClientSide()) {
                NetworkHooks.openGui((ServerPlayer) playerEntity, this, worldPosition);
        }
    }

    private boolean isInput(ItemStack stack) {
        return true;
    }

    private boolean canCraft(FireplaceRecipe recipe) {
        if (recipe != null) {
            ItemStack itemstack = recipe.assemble((Container) this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack outSlot = itemHandler.getStackInSlot(3);
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

    private void completeCraft(FireplaceRecipe recipe) {
        if (canCraft(recipe)) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.test(itemHandler.getStackInSlot(0))) {
                    itemHandler.extractItem(0, 1, false);
                } else if (ingredient.test(itemHandler.getStackInSlot(1))) {
                    itemHandler.extractItem(1, 1, false);
                }
            }

            ItemStack result = recipe.assemble((Container) this);
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
        SimpleContainer inv = new SimpleContainer(4);
        RecipeManager mgr = level.getRecipeManager();

        FireplaceRecipe recipe = mgr.getRecipeFor(TolkienRecipes.FIREPLACE_RECIPE_TYPE.get(), inv, level).orElse(null);
//        Optional<Recipe<Inventory>> optRecipe = ObjectUtils.firstNonNull(new Optional[] { mgr
//                .getRecipeFor(RecipeType.SMELTING, inv, level), mgr
//                .getRecipeFor(RecipeType.CAMPFIRE_COOKING, inv, level), mgr
//                .getRecipeFor(RecipeType.SMOKING, inv, level), mgr
//                .getRecipeFor(TolkienRecipes.FIREPLACE_RECIPE_TYPE.get(), inv, level),
//                Optional.empty() });

        if (fuelRemaining.get() <= 0 && isTileEnabled() && canCraft(recipe)) {
            tryRefuel();
        }
    }

    @Override
    public void tick() {
        super.tick();
        SimpleContainer inv = new SimpleContainer(4);
        RecipeManager mgr = level.getRecipeManager();

        if (level == null || level.isClientSide) return;

        boolean last = isBurning.get();
        isBurning.set(fuelRemaining.get() > 0);
        if (isBurning.get() != last) {
            level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(FireplaceBlock.LIT, isBurning.get()));
        }

        if (isBurning.get()) {
            fuelRemaining.dec();
        } else {
            return;
        }

        FireplaceRecipe recipe = level.getRecipeManager().getRecipeFor(TolkienRecipes.FIREPLACE_RECIPE_TYPE.get(), inv, level).orElse(null);
//        Optional<Recipe<Inventory>> optRecipe = ObjectUtils.firstNonNull(new Optional[] { mgr
//                .getRecipeFor(RecipeType.SMELTING, inv, level), mgr
//                .getRecipeFor(RecipeType.CAMPFIRE_COOKING, inv, level), mgr
//                .getRecipeFor(RecipeType.SMOKING, inv, level), mgr
//                .getRecipeFor(TolkienRecipes.FIREPLACE_RECIPE_TYPE.get(), inv, level),
//                Optional.empty() });

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
            int itemBurnTime = ForgeHooks.getBurnTime(stack, TolkienRecipes.FIREPLACE_RECIPE_TYPE.get());
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

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<Recipe<?>> list = this.getRecipesToAwardAndPopExperience(player.getLevel(), player.position());
        player.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 vec3) {
        List<Recipe<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent((p_155023_) -> {
                list.add(p_155023_);
                createExperience(level, vec3, entry.getIntValue(), ((AbstractCookingRecipe)p_155023_).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(ServerLevel level, Vec3 vec3, int p_155001_, float p_155002_) {
        int i = Mth.floor((float)p_155001_ * p_155002_);
        float f = Mth.frac((float)p_155001_ * p_155002_);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        ExperienceOrb.award(level, vec3, i);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.TMFIREPLACE_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
    }
}