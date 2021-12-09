package com.greatorator.tolkienmobs.crafting;//package com.greatorator.tolkienmobs.crafting.recipe;

import com.greatorator.tolkienmobs.handler.interfaces.ITTMTrinketRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by brandon3055 on 21/02/19.
 */
public class TTMTrinketPotionRecipe implements ITTMTrinketRecipe {
    @Override
    public boolean matches(IInventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack assemble(IInventory inventory) {
        return null;
    }

    @Override
    public ItemStack getResultItem() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }
//    public TrinketPotionRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
//        super(group, output, ingredients);
//    }
//
//    @Override
//    public boolean matches(InventoryCrafting inv, World worldIn) {
//        return super.matches(inv, worldIn) && getValidPotion(inv) != null;
//    }
//
//    @Override
//    public ItemStack getCraftingResult(InventoryCrafting inv) {
//        ItemStack trinket = super.getCraftingResult(inv);
//        Potion potion = getValidPotion(inv);
//
//        if (potion != null){ //Just in case
//            ItemTrinketRing.addPotionToTrinket(trinket, potion);
//        }
//
//        return trinket;
//    }
//
//    @Override
//    public ItemStack getRecipeOutput() {
//        return super.getRecipeOutput();
//    }
//
//    @Nullable
//    private Potion getValidPotion(InventoryCrafting inv) {
//        Potion potion = null;
//        for (int i = 0; i < inv.getSizeInventory(); i++) {
//            ItemStack stack = inv.getStackInSlot(i);
//            if (stack.isEmpty() || !(stack.getItem() instanceof ItemPotion)) {
//                continue;
//            }
//
//            if (potion != null) { //There is more than one potion in the crafting grid so the recipe is not valid.
//                return null;
//            }
//
//            List<PotionEffect> effects = PotionUtils.getEffectsFromStack(stack);
//            if (effects.size() != 1) { //There is more then one effect on the bottle so the recipe is not valid.
//                return null;
//            }
//
//            //Finally make sure the potion we found is in the white list.
//            potion = effects.get(0).getPotion();
//            if (!ArrayUtils.contains(TTMConfig_Old.potionArray, potion)) {
//                return null;
//            }
//        }
//
//        return potion;
//    }
}