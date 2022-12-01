package com.greatorator.tolkienmobs;

import com.greatorator.tolkienmobs.init.*;
import com.greatorator.tolkienmobs.world.gen.feature.config.TreeFeatureConfig;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

/**
 * Created by brandon3055 on 31/1/21
 */
public class TolkienContent {
    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        LOGGER.info("Creating the light of the Valar in the land of Arda...");
        TolkienPotions.EFFECTS.register(modBus);
        TolkienPotions.POTIONS.register(modBus);
        TolkienEnchants.ENCHANTS.register(modBus);
        LOGGER.info("Asking the Ainur to sing the music of Eru Iluvatar...");
        TolkienSounds.SOUND_EVENTS.register(modBus);
        LOGGER.info("Preparing the Dwarves...");
        TolkienBlocks.BLOCKS.register(modBus);
        LOGGER.info("Stocking the markets...");
        TolkienItems.ITEMS.register(modBus);
        TolkienFluids.FLUIDS.register(modBus);
        TolkienTiles.TILE.register(modBus);
        TolkienLootFunctions.LOOT_FUNCTIONS.register(modBus);
        TolkienRecipes.RECIPE_SERIALIZER.register(modBus);
        LOGGER.info("Populating the peoples of Middle-earth...");
        TolkienEntities.ENTITY.register(modBus);
        TolkienEntities.EGG_ITEMS.register(modBus);
        LOGGER.info("Setting the task master to work...");
        TolkienProfessions.PROFESSION.register(modBus);
        TolkienProfessions.POIT.register(modBus);
        LOGGER.info("Time to create the land...");
        TolkienDimensions.register();
        TreeFeatureConfig.FOLIAGE_PLACER_REGISTER.register(modBus);
        TreeFeatureConfig.TREE_DECORATORS.register(modBus);
        TreeFeatureConfig.TRUNK_PLACERS.register(modBus);
        TreeFeatureConfig.PLACEMENT_MODIFIERS.register(modBus);
//        TolkienBiomes.BIOMES.register(modBus);
//        TolkienStructures.STRUCTURES.register(modBus);
        modBus.addGenericListener(MenuType.class, TolkienContainers::registerContainers);
    }
}