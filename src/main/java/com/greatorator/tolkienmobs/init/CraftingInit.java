package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingInit {
    public static void init()
    {
        LogHelperTTM.info("Making everyone aware of our existence...");

        addOreRegistration();
        addSmeltingRecipes();

        LogHelperTTM.info("World now fully aware!");
    }

    private static void addSmeltingRecipes(){
        /** Furnace */
        GameRegistry.addSmelting(TTMFeatures.LOGS, new ItemStack(Items.COAL, 1, 1), 0.15F);
        GameRegistry.addSmelting(TTMFeatures.DUST_MITHRIL, new ItemStack(TTMFeatures.INGOT_MITHRIL, 1), 0.15F);
        GameRegistry.addSmelting(TTMFeatures.DUST_MORGULIRON, new ItemStack(TTMFeatures.INGOT_MORGULIRON, 1), 0.15F);
        GameRegistry.addSmelting(new ItemStack(TTMFeatures.ORE,1,0), new ItemStack(TTMFeatures.INGOT_MITHRIL, 1), 0.15F);
        GameRegistry.addSmelting(new ItemStack(TTMFeatures.ORE,1,2), new ItemStack(TTMFeatures.INGOT_MITHRIL, 1), 0.15F);
        GameRegistry.addSmelting(new ItemStack(TTMFeatures.ORE,1,4), new ItemStack(TTMFeatures.INGOT_MITHRIL, 1), 0.15F);
        GameRegistry.addSmelting(new ItemStack(TTMFeatures.ORE,1,1), new ItemStack(TTMFeatures.INGOT_MORGULIRON, 1), 0.15F);
        GameRegistry.addSmelting(new ItemStack(TTMFeatures.ORE,1,3), new ItemStack(TTMFeatures.INGOT_MORGULIRON, 1), 0.15F);
        GameRegistry.addSmelting(new ItemStack(TTMFeatures.ORE,1,5), new ItemStack(TTMFeatures.INGOT_MORGULIRON, 1), 0.15F);
    }

    private static void addOreRegistration(){

        /** Dusts, Nuggets, Ingots, Metal Blocks */
        OreDictionary.registerOre("dustMithril", TTMFeatures.DUST_MITHRIL);
        OreDictionary.registerOre("nuggetMithril", TTMFeatures.NUGGET_MITHRIL);
        OreDictionary.registerOre("ingotMithril", TTMFeatures.INGOT_MITHRIL);
        OreDictionary.registerOre("blockMithril", TTMFeatures.BLOCK_MITHRIL);
        OreDictionary.registerOre("dustSteel", TTMFeatures.DUST_MORGULIRON);
        OreDictionary.registerOre("nuggetSteel", TTMFeatures.NUGGET_MORGULIRON);
        OreDictionary.registerOre("ingotSteel", TTMFeatures.INGOT_MORGULIRON);
        OreDictionary.registerOre("blockSteel", TTMFeatures.BLOCK_MORGULIRON);

        /** Ores */
        OreDictionary.registerOre("oreMithril", new ItemStack(TTMFeatures.ORE,1,0));
        OreDictionary.registerOre("oreMithril", new ItemStack(TTMFeatures.ORE,1,2));
        OreDictionary.registerOre("oreMithril", new ItemStack(TTMFeatures.ORE,1,4));
        OreDictionary.registerOre("oreSteel", new ItemStack(TTMFeatures.ORE,1,1));
        OreDictionary.registerOre("oreSteel", new ItemStack(TTMFeatures.ORE,1,3));
        OreDictionary.registerOre("oreSteel", new ItemStack(TTMFeatures.ORE,1,5));

        /** Wood Blocks */
        OreDictionary.registerOre("logWood", new ItemStack(TTMFeatures.LOGS));
        OreDictionary.registerOre("plankWood", TTMFeatures.PLANKS);
        OreDictionary.registerOre("slabWood", TTMFeatures.HALF_SLAB);
        //OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIR));

        /** Plant life */
        OreDictionary.registerOre("treeSapling", TTMFeatures.SAPLINGS);
        OreDictionary.registerOre("treeLeaves", TTMFeatures.LEAVES);
        OreDictionary.registerOre("flower",TTMFeatures.FLOWERS);

        /** Foodstuffs */
        OreDictionary.registerOre("foodLembas", new ItemStack(TTMFeatures.LEMBAS));

        /** Monster Drops */
        OreDictionary.registerOre("feather", new ItemStack(TTMFeatures.CREBAIN_FEATHER));
        OreDictionary.registerOre("leather", new ItemStack(TTMFeatures.MUMAKIL_LEATHER));


    }
}
