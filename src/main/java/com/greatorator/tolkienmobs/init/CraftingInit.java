package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.crafting.recipe.TrinketPotionRecipe;
import com.greatorator.tolkienmobs.handler.TTMFuel;
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

        TTMFuel ttmFuel = new TTMFuel();
        GameRegistry.registerFuelHandler(ttmFuel);

        ttmFuel.addFuel(TTMFeatures.SAPLINGS, 100);
    }

    private static void addOreRegistration(){

        /* Dusts, Nuggets, Ingots, Metal Blocks */
        OreDictionary.registerOre("dustDwarvenSilver", new ItemStack(TTMFeatures.DUST_MITHRIL));
        OreDictionary.registerOre("nuggetDwarvenSilver", new ItemStack(TTMFeatures.NUGGET_MITHRIL));
        OreDictionary.registerOre("ingotDwarvenSilver", new ItemStack(TTMFeatures.INGOT_MITHRIL));
        OreDictionary.registerOre("blockDwarvenSilver", new ItemStack(TTMFeatures.BLOCK_MITHRIL));
        OreDictionary.registerOre("doorIron", new ItemStack(TTMFeatures.ITEM_DOOR_MITHRIL));
        OreDictionary.registerOre("barsIron", new ItemStack(TTMFeatures.MITHRIL_BARS));
        OreDictionary.registerOre("dustMorgulIron", new ItemStack(TTMFeatures.DUST_MORGULIRON));
        OreDictionary.registerOre("nuggetMorgulIron", new ItemStack(TTMFeatures.NUGGET_MORGULIRON));
        OreDictionary.registerOre("ingotMorgulIron", new ItemStack(TTMFeatures.INGOT_MORGULIRON));
        OreDictionary.registerOre("blockMorgulIron", new ItemStack(TTMFeatures.BLOCK_MORGULIRON));
        OreDictionary.registerOre("barsIron ", new ItemStack(TTMFeatures.MORGULIRON_BARS));
        OreDictionary.registerOre("doorIron", new ItemStack(TTMFeatures.ITEM_DOOR_MORGULIRON));
        OreDictionary.registerOre("gemAmmolite", new ItemStack(TTMFeatures.GEM_AMMOLITE));

        /* Ores */
        OreDictionary.registerOre("oreDwarvenSilver", new ItemStack(TTMFeatures.ORE,1,0));
        OreDictionary.registerOre("oreDwarvenSilver", new ItemStack(TTMFeatures.ORE,1,2));
        OreDictionary.registerOre("oreDwarvenSilver", new ItemStack(TTMFeatures.ORE,1,4));
        OreDictionary.registerOre("oreMorgulIron", new ItemStack(TTMFeatures.ORE,1,1));
        OreDictionary.registerOre("oreMorgulIron", new ItemStack(TTMFeatures.ORE,1,3));
        OreDictionary.registerOre("oreMorgulIron", new ItemStack(TTMFeatures.ORE,1,5));
        OreDictionary.registerOre("oreAmmolite", new ItemStack(TTMFeatures.ORE,1,6));
        OreDictionary.registerOre("oreAmmolite", new ItemStack(TTMFeatures.ORE,1,7));
        OreDictionary.registerOre("oreAmmolite", new ItemStack(TTMFeatures.ORE,1,8));

        /* Wood Blocks */
        OreDictionary.registerOre("logWood", new ItemStack(TTMFeatures.LOGS));
        OreDictionary.registerOre("plankWood", new ItemStack(TTMFeatures.PLANKS));
        OreDictionary.registerOre("slabWood", new ItemStack(TTMFeatures.HALF_SLAB));
        OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIRS_LEBETHRON));
        OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIRS_CULUMALDA));
        OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIRS_MALLORN));
        OreDictionary.registerOre("stairWood", new ItemStack(TTMFeatures.STAIRS_MIRKWOOD));
        OreDictionary.registerOre("doorWood", new ItemStack(TTMFeatures.ITEM_DOOR_MALLORN));
        OreDictionary.registerOre("doorWood", new ItemStack(TTMFeatures.ITEM_DOOR_MIRKWOOD));
        OreDictionary.registerOre("doorWood", new ItemStack(TTMFeatures.ITEM_DOOR_LEBETHRON));
        OreDictionary.registerOre("doorWood", new ItemStack(TTMFeatures.ITEM_DOOR_CULUMALDA));
        OreDictionary.registerOre("fenceWood", new ItemStack(TTMFeatures.FENCE_MALLORN));
        OreDictionary.registerOre("fenceWood", new ItemStack(TTMFeatures.FENCE_MIRKWOOD));
        OreDictionary.registerOre("fenceWood", new ItemStack(TTMFeatures.FENCE_CULUMALDA));
        OreDictionary.registerOre("fenceWood", new ItemStack(TTMFeatures.FENCE_LEBETHRON));
        OreDictionary.registerOre("fenceGateWood", new ItemStack(TTMFeatures.FENCE_GATE_MALLORN));
        OreDictionary.registerOre("fenceGateWood", new ItemStack(TTMFeatures.FENCE_GATE_MIRKWOOD));
        OreDictionary.registerOre("fenceGateWood", new ItemStack(TTMFeatures.FENCE_GATE_CULUMALDA));
        OreDictionary.registerOre("fenceGateWood", new ItemStack(TTMFeatures.FENCE_GATE_LEBETHRON));

        /* Plant life */
        OreDictionary.registerOre("treeSapling", new ItemStack(TTMFeatures.SAPLINGS));
        OreDictionary.registerOre("treeLeaves", new ItemStack(TTMFeatures.LEAVES));
        OreDictionary.registerOre("flower", new ItemStack(TTMFeatures.FLOWERS));
        OreDictionary.registerOre("mushroom", new ItemStack(TTMFeatures.TM_MUSHROOM));

        /* Foodstuffs */
        OreDictionary.registerOre("foodLembas", new ItemStack(TTMFeatures.LEMBAS));
        OreDictionary.registerOre("foodHoneyCake", new ItemStack(TTMFeatures.HONEY_CAKE));
        OreDictionary.registerOre("foodCram", new ItemStack(TTMFeatures.CRAM));
        OreDictionary.registerOre("flesh", new ItemStack(TTMFeatures.MONSTER_FLESH));
        OreDictionary.registerOre("foodInsect", new ItemStack(TTMFeatures.INSECT));
        OreDictionary.registerOre("foodInsect", new ItemStack(TTMFeatures.GOLDEN_INSECT));
        OreDictionary.registerOre("foodNut", new ItemStack(TTMFeatures.TREE_ACORN));
        OreDictionary.registerOre("foodNut", new ItemStack(TTMFeatures.GOLDEN_TREE_ACORN));
        OreDictionary.registerOre("dropHoney", new ItemStack(TTMFeatures.FOOD_HONEY));
        OreDictionary.registerOre("foodHoneydrop", new ItemStack(TTMFeatures.FOOD_HONEY));

        /* Monster Drops */
        OreDictionary.registerOre("feather", new ItemStack(TTMFeatures.CREBAIN_FEATHER));
        OreDictionary.registerOre("feather", new ItemStack(TTMFeatures.BIRD_FEATHER));
        OreDictionary.registerOre("leather", new ItemStack(TTMFeatures.MUMAKIL_LEATHER));
        OreDictionary.registerOre("fur", new ItemStack(TTMFeatures.MONSTER_FUR));

        /* Miscellaneous */
        OreDictionary.registerOre("glassBottle", new ItemStack(TTMFeatures.BOTTLE_FANCY));
        OreDictionary.registerOre("itemCoinBronze", new ItemStack(TTMFeatures.ITEM_COIN_BRONZE));
        OreDictionary.registerOre("itemCoinSilver", new ItemStack(TTMFeatures.ITEM_COIN_SILVER));
        OreDictionary.registerOre("itemCoinGold", new ItemStack(TTMFeatures.ITEM_COIN_GOLD));
        OreDictionary.registerOre("itemCoinMithril", new ItemStack(TTMFeatures.ITEM_COIN_MITHRIL));
        OreDictionary.registerOre("itemCoinFaction", new ItemStack(TTMFeatures.ITEM_FACTIONCOIN));
        OreDictionary.registerOre("itemTokenFaction", new ItemStack(TTMFeatures.ITEM_FACTIONTOKEN));
        OreDictionary.registerOre("itemTokenSigil", new ItemStack(TTMFeatures.ITEM_DARKSIGIL));
        OreDictionary.registerOre("itemTokenCave", new ItemStack(TTMFeatures.ITEM_CAVECOMPLETE));
        OreDictionary.registerOre("itemTokenWatcher", new ItemStack(TTMFeatures.ITEM_WATCHERCOMPLETE));
    }

    private static void addOreDictRecipe(){
        Ingredient potionIngredient = Ingredient.fromStacks(new ItemStack(Items.POTIONITEM, 1, 32767), new ItemStack(Items.LINGERING_POTION, 1, 32767), new ItemStack(Items.SPLASH_POTION, 1, 32767));
        registerTrinketRecipe(TTMFeatures.TRINKET_RING, potionIngredient);
        registerTrinketRecipe(TTMFeatures.TRINKET_AMULET, potionIngredient);
        registerTrinketRecipe(TTMFeatures.TRINKET_BELT, potionIngredient);
        registerTrinketRecipe(TTMFeatures.TRINKET_CHARM, potionIngredient);
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