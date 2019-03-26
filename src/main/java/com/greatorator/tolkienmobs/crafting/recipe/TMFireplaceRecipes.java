package com.greatorator.tolkienmobs.crafting.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TMFireplaceRecipes {
    private static final TMFireplaceRecipes INSTANCE = new TMFireplaceRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
    private final Set<ItemStack> slot1Inputs = new HashSet<>();
    private final Set<ItemStack> slot2Inputs = new HashSet<>();

    public static TMFireplaceRecipes getInstance()
    {
        return INSTANCE;
    }

    private TMFireplaceRecipes()
    {
        /* This is where we place all of the recipes specific for the fireplace */
        addFireplaceRecipe(new ItemStack(TTMFeatures.FOOD_HONEY), new ItemStack(TTMFeatures.CRAM), new ItemStack(TTMFeatures.HONEY_CAKE), 3.0F);
    }


    public void addFireplaceRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
    {
        if(getFireplaceResult(input1, input2) != ItemStack.EMPTY) return;
        this.smeltingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
        slot1Inputs.add(input1);
        slot2Inputs.add(input2);
    }

    public ItemStack getFireplaceResult(ItemStack input1, ItemStack input2)
    {
        for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.smeltingList;
    }

    public float getFireplaceExperience(ItemStack stack)
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }

    public boolean isInput(int slot, ItemStack stack) {
        if (slot == 1) {
            for (ItemStack s : slot1Inputs) {
                if (compareItemStacks(s, stack)) {
                    return true;
                }
            }
        }
        else {
            for (ItemStack s : slot2Inputs) {
                if (compareItemStacks(s, stack)) {
                    return true;
                }
            }
        }

        return false;
    }
}