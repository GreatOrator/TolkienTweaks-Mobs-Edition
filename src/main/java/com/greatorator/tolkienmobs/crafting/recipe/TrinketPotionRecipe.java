package com.greatorator.tolkienmobs.crafting.recipe;

import codechicken.lib.util.ArrayUtils;
import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketRing;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by brandon3055 on 21/02/19.
 */
public class TrinketPotionRecipe extends ShapelessRecipes {
    public TrinketPotionRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
        super(group, output, ingredients);
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return super.matches(inv, worldIn) && getValidPotion(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack trinket = super.getCraftingResult(inv);
        Potion potion = getValidPotion(inv);

        if (potion != null){ //Just in case
            ItemTrinketRing.addPotionToTrinket(trinket, potion);
        }

        return trinket;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return super.getRecipeOutput();
    }

    @Nullable
    private Potion getValidPotion(InventoryCrafting inv) {
        Potion potion = null;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.isEmpty() || !(stack.getItem() instanceof ItemPotion)) {
                continue;
            }

            if (potion != null) { //There is more than one potion in the crafting grid so the recipe is not valid.
                return null;
            }

            List<PotionEffect> effects = PotionUtils.getEffectsFromStack(stack);
            if (effects.size() != 1) { //There is more then one effect on the bottle so the recipe is not valid.
                return null;
            }

            //Finally make sure the potion we found is in the white list.
            potion = effects.get(0).getPotion();
            if (!ArrayUtils.contains(TTMConfig.potionArray, potion)) {
                return null;
            }
        }

        return potion;
    }
}
