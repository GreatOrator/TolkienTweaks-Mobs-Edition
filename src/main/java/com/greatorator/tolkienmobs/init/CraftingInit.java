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
        //Furnace
        GameRegistry.addSmelting(TTMFeatures.LOGS, new ItemStack(Items.COAL, 1, 1), 0.15F);
        //GameRegistry.addSmelting(TTMFeatures., new ItemStack(TTMFeatures.INGOT_MITHRIL, 1), 0.15F);
        //GameRegistry.addSmelting(TTMFeatures., new ItemStack(TTMFeatures.INGOT_MORGULIRON, 1), 0.15F);
    }

    private static void addOreRegistration(){

        //Dusts, Nuggets, Ingots, Metal Blocks
        OreDictionary.registerOre("dustMithril", TTMFeatures.DUST_MITHRIL);
        OreDictionary.registerOre("nuggetMithril", TTMFeatures.NUGGET_MITHRIL);
        OreDictionary.registerOre("ingotMithril", TTMFeatures.INGOT_MITHRIL);
        OreDictionary.registerOre("blockMithril", TTMFeatures.BLOCK_MITHRIL);
        OreDictionary.registerOre("dustSteel", TTMFeatures.DUST_MORGULIRON);
        OreDictionary.registerOre("nuggetSteel", TTMFeatures.NUGGET_MORGULIRON);
        OreDictionary.registerOre("ingotSteel", TTMFeatures.INGOT_MORGULIRON);
        OreDictionary.registerOre("blockSteel", TTMFeatures.BLOCK_MORGULIRON);

        //Ores
        //OreDictionary.registerOre("oreMithril", TTMFeatures.);
        //OreDictionary.registerOre("oreSteel", TTMFeatures.);

        //Wood Blocks
        OreDictionary.registerOre("logWood", new ItemStack(TTMFeatures.LOGS));
        OreDictionary.registerOre("plankWood", TTMFeatures.PLANKS);
        OreDictionary.registerOre("slabWood", TTMFeatures.HALF_SLAB);
        //OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIR));

        //Plant life
        OreDictionary.registerOre("treeSapling", TTMFeatures.SAPLINGS);
        OreDictionary.registerOre("treeLeaves", TTMFeatures.LEAVES);
        OreDictionary.registerOre("flower",TTMFeatures.FLOWERS);

        //Foodstuffs
        //OreDictionary.registerOre("foodLembas", new ItemStack(TTMFeatures.LEMBAS));


    }
}
