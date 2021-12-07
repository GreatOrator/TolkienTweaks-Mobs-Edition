package com.greatorator.tolkienmobs.crafting.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.interfaces.ITTMFireplaceRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class TTMFireplaceRecipe implements ITTMFireplaceRecipe {
    private static ResourceLocation id = null;
    private final ItemStack output;
    private static NonNullList<Ingredient> recipeItems = null;

    public TTMFireplaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        if(recipeItems.get(0).test(inv.getItem(0))) {
            return recipeItems.get(1).test(inv.getItem(1));
        }
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public ItemStack getIcon() {
        return new ItemStack(TTMContent.TTMFIREPLACE.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return TTMContent.TMFIREPLACE_SERIALIZER.get();
    }

    public static class FireplaceRecipeType implements IRecipeType<TTMFireplaceRecipe> {
        @Override
        public String toString() {
            return TTMFireplaceRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>implements IRecipeSerializer<TTMFireplaceRecipe> {
        @Override
        public TTMFireplaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new TTMFireplaceRecipe(id, output, recipeItems);
        }

        @Nullable
        @Override
        public TTMFireplaceRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new TTMFireplaceRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, TTMFireplaceRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }
//    private static final TMFireplaceRecipes INSTANCE = new TMFireplaceRecipes();
//    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
//    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
//    private final Set<ItemStack> slot1Inputs = new HashSet<>();
//    private final Set<ItemStack> slot2Inputs = new HashSet<>();
//
//    public static TMFireplaceRecipes getInstance()
//    {
//        return INSTANCE;
//    }
//
//    private TMFireplaceRecipes()
//    {
//        /* This is where we place all of the recipes specific for the fireplace */
//        addFireplaceRecipe(new ItemStack(TTMFeatures.FOOD_HONEY), new ItemStack(TTMFeatures.CRAM), new ItemStack(TTMFeatures.HONEY_CAKE), 3.0F);
//    }
//
//
//    public void addFireplaceRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
//    {
//        if(getFireplaceResult(input1, input2) != ItemStack.EMPTY) return;
//        this.smeltingList.put(input1, input2, result);
//        this.experienceList.put(result, Float.valueOf(experience));
//        slot1Inputs.add(input1);
//        slot2Inputs.add(input2);
//    }
//
//    public ItemStack getFireplaceResult(ItemStack input1, ItemStack input2)
//    {
//        for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
//        {
//            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
//            {
//                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
//                {
//                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
//                    {
//                        return (ItemStack)ent.getValue();
//                    }
//                }
//            }
//        }
//        return ItemStack.EMPTY;
//    }
//
//    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
//    {
//        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
//    }
//
//    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
//    {
//        return this.smeltingList;
//    }
//
//    public float getFireplaceExperience(ItemStack stack)
//    {
//        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
//        {
//            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
//            {
//                return ((Float)entry.getValue()).floatValue();
//            }
//        }
//        return 0.0F;
//    }
//
//    public boolean isInput(int slot, ItemStack stack) {
//        if (slot == 1) {
//            for (ItemStack s : slot1Inputs) {
//                if (compareItemStacks(s, stack)) {
//                    return true;
//                }
//            }
//        }
//        else {
//            for (ItemStack s : slot2Inputs) {
//                if (compareItemStacks(s, stack)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
}
