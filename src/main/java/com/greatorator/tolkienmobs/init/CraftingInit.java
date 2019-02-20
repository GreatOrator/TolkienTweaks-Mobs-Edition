package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingInit {

    public static void init()
    {
        LogHelperTTM.info("Making everyone aware of our existence...");

        addOreRegistration();
        addSmeltingRecipes();
        addOreDictRecipe();

        LogHelperTTM.info("World now fully aware!");
    }

    private static void addSmeltingRecipes(){
        /* Furnace */
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

        /* Dusts, Nuggets, Ingots, Metal Blocks */
        OreDictionary.registerOre("dustMithril", TTMFeatures.DUST_MITHRIL);
        OreDictionary.registerOre("nuggetMithril", TTMFeatures.NUGGET_MITHRIL);
        OreDictionary.registerOre("ingotMithril", TTMFeatures.INGOT_MITHRIL);
        OreDictionary.registerOre("blockMithril", TTMFeatures.BLOCK_MITHRIL);
        OreDictionary.registerOre("dustSteel", TTMFeatures.DUST_MORGULIRON);
        OreDictionary.registerOre("nuggetSteel", TTMFeatures.NUGGET_MORGULIRON);
        OreDictionary.registerOre("ingotSteel", TTMFeatures.INGOT_MORGULIRON);
        OreDictionary.registerOre("blockSteel", TTMFeatures.BLOCK_MORGULIRON);
        OreDictionary.registerOre("gemDiamond", TTMFeatures.GEM_AMMOLITE);

        /* Ores */
        OreDictionary.registerOre("oreMithril", new ItemStack(TTMFeatures.ORE,1,0));
        OreDictionary.registerOre("oreMithril", new ItemStack(TTMFeatures.ORE,1,2));
        OreDictionary.registerOre("oreMithril", new ItemStack(TTMFeatures.ORE,1,4));
        OreDictionary.registerOre("oreSteel", new ItemStack(TTMFeatures.ORE,1,1));
        OreDictionary.registerOre("oreSteel", new ItemStack(TTMFeatures.ORE,1,3));
        OreDictionary.registerOre("oreSteel", new ItemStack(TTMFeatures.ORE,1,5));
        OreDictionary.registerOre("oreAmmolite", new ItemStack(TTMFeatures.ORE,1,6));
        OreDictionary.registerOre("oreAmmolite", new ItemStack(TTMFeatures.ORE,1,7));
        OreDictionary.registerOre("oreAmmolite", new ItemStack(TTMFeatures.ORE,1,8));

        /* Wood Blocks */
        OreDictionary.registerOre("logWood", new ItemStack(TTMFeatures.LOGS));
        OreDictionary.registerOre("plankWood", TTMFeatures.PLANKS);
        OreDictionary.registerOre("slabWood", TTMFeatures.HALF_SLAB);
        //OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIR));

        /* Plant life */
        OreDictionary.registerOre("treeSapling", TTMFeatures.SAPLINGS);
        OreDictionary.registerOre("treeLeaves", TTMFeatures.LEAVES);
        OreDictionary.registerOre("flower",TTMFeatures.FLOWERS);

        /* Foodstuffs */
        OreDictionary.registerOre("foodLembas", new ItemStack(TTMFeatures.LEMBAS));
        OreDictionary.registerOre("foodHoneyCake", new ItemStack(TTMFeatures.HONEY_CAKE));
        OreDictionary.registerOre("foodCram", new ItemStack(TTMFeatures.CRAM));
        OreDictionary.registerOre("foodMonsterFlesh", new ItemStack(TTMFeatures.MONSTER_FLESH));

        /* Monster Drops */
        OreDictionary.registerOre("feather", new ItemStack(TTMFeatures.CREBAIN_FEATHER));
        OreDictionary.registerOre("leather", new ItemStack(TTMFeatures.MUMAKIL_LEATHER));
    }

    private static void addOreDictRecipe(){
        GameRegistry.addShapedRecipe(new ResourceLocation(TolkienMobs.MODID + ":magicRing"),null,new ItemStack(TTMFeatures.TRINKET_RING),"AM ","M M"," M ",'A',TTMFeatures.GEM_AMMOLITE,'M',TTMFeatures.INGOT_MITHRIL);
        GameRegistry.addShapedRecipe(new ResourceLocation(TolkienMobs.MODID + ":magicAmulet"),null,new ItemStack(TTMFeatures.TRINKET_AMULET),"GMG","G G","MAM",'A',TTMFeatures.GEM_AMMOLITE,'M',TTMFeatures.INGOT_MITHRIL,'G',TTMFeatures.MUMAKIL_LEATHER);
        GameRegistry.addShapedRecipe(new ResourceLocation(TolkienMobs.MODID + ":magicCharm"),null,new ItemStack(TTMFeatures.TRINKET_CHARM),"MMG","CAC","MMM",'A',TTMFeatures.GEM_AMMOLITE,'M',TTMFeatures.INGOT_MITHRIL,'C',Blocks.HARDENED_CLAY,'G',TTMFeatures.MUMAKIL_LEATHER);
        GameRegistry.addShapedRecipe(new ResourceLocation(TolkienMobs.MODID + ":magicBelt"),null,new ItemStack(TTMFeatures.TRINKET_BELT),"GGG","M G","AMG",'A',TTMFeatures.GEM_AMMOLITE,'M',TTMFeatures.INGOT_MITHRIL,'G',TTMFeatures.MUMAKIL_LEATHER);


    }
}