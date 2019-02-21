package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.crafting.recipe.TrinketPotionRecipe;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IngredientNBT;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.GameData;

public class CraftingInit {

    public static void init()
    {
        LogHelperTTM.info("Making everyone aware of our existence...");

        addOreRegistration();
        addSmeltingRecipes();
        addOreDictRecipe();


        //This does not need to stay here
        Ingredient potionIngredient = Ingredient.fromStacks(new ItemStack(Items.POTIONITEM, 1, 32767), new ItemStack(Items.LINGERING_POTION, 1, 32767), new ItemStack(Items.SPLASH_POTION, 1, 32767));
        registerTrinketRecipe(TTMFeatures.TRINKET_RING, potionIngredient);
        registerTrinketRecipe(TTMFeatures.TRINKET_AMULET, potionIngredient);
        registerTrinketRecipe(TTMFeatures.TRINKET_BELT, potionIngredient);
        registerTrinketRecipe(TTMFeatures.TRINKET_CHARM, potionIngredient);



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
    }


    private static void registerTrinketRecipe(Item trinket, Ingredient potionIngredient) {
        ResourceLocation name = getNameForRecipe(new ItemStack(trinket));
        ItemStack trinketStack = new ItemStack(trinket);
        Ingredient trinketIngred = new IngredNBT(trinketStack);
        TrinketPotionRecipe recipe = new TrinketPotionRecipe(name.getResourceDomain(), trinketStack, NonNullList.from(Ingredient.EMPTY, potionIngredient, trinketIngred));
        recipe.setRegistryName(new ResourceLocation(name.getResourceDomain(), name.getResourcePath() + "_potion"));
        GameData.register_impl(recipe);
    }

    private static ResourceLocation getNameForRecipe(ItemStack output) {
        ModContainer activeContainer = Loader.instance().activeModContainer();
        ResourceLocation baseLoc = new ResourceLocation(activeContainer.getModId(), output.getItem().getRegistryName().getResourcePath());
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;
        while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
            index++;
            recipeLoc = new ResourceLocation(activeContainer.getModId(), baseLoc.getResourcePath() + "_" + index);
        }
        return recipeLoc;
    }

    public static class IngredNBT extends IngredientNBT {
        public IngredNBT(final ItemStack stack) {
            super(stack);
        }
    }
}