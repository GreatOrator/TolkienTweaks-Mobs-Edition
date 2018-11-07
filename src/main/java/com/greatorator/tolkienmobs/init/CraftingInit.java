package com.greatorator.tolkienmobs.init;


import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingInit {
    public static void init()
    {
        addOreRegistration();
        addSmeltingRecipes();
    }

    private static void addSmeltingRecipes(){
        GameRegistry.addSmelting(TTMFeatures.LOGS, new ItemStack(Items.COAL, 1, 1), 0.15F);
    }

    private static void addOreRegistration(){
        OreDictionary.registerOre("logWood", new ItemStack(TTMFeatures.LOGS));
        OreDictionary.registerOre("plankWood", TTMFeatures.PLANKS);
        OreDictionary.registerOre("treeSapling", TTMFeatures.SAPLINGS);
        OreDictionary.registerOre("treeLeaves", TTMFeatures.LEAVES);
        OreDictionary.registerOre("flower",TTMFeatures.FLOWERS);
        //OreDictionary.registerOre("slabWood", TTMFeatures.SLAB);
        //OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIR));


    }
}
