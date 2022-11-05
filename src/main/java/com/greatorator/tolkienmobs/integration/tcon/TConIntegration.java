package com.greatorator.tolkienmobs.integration.tcon;

import com.greatorator.tolkienmobs.integration.IntegrationHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class TConIntegration {
    private static boolean tconLoaded;
    public static final String TCON_ID = "tconstruct";

    public static void initialize() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        tconLoaded = IntegrationHelper.isTCONInstalled;

        if (tconLoaded) {
            TConMaterialManager.FLUIDS.register(modBus);
            TConMaterialManager.MODIFIERS.register(modBus);
        }
    }

    public static void tConDatagen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        AbstractMaterialSpriteProvider sprites = new TConDataGenerator.MaterialSprites();
        AbstractMaterialDataProvider materials = new TConDataGenerator.MaterialDatagen(generator);

        generator.addProvider(new TConDataGenerator.FluidTagGenerator(generator, helper));
        generator.addProvider(new TConDataGenerator.MaterialRenders(generator, sprites));
        generator.addProvider(new TConDataGenerator.MaterialStats(generator, materials));
        generator.addProvider(new TConDataGenerator.MaterialTraits(generator, materials));
        generator.addProvider(new TConDataGenerator.MaterialDatagen(generator));
        generator.addProvider(new TConDataGenerator.MaterialRecipes(generator));
        generator.addProvider(new TConDataGenerator.SmelteryRecipes(generator));
    }
}
