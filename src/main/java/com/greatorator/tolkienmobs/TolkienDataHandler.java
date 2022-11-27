package com.greatorator.tolkienmobs;

import com.greatorator.tolkienmobs.datagen.BlockStateGenerator;
import com.greatorator.tolkienmobs.datagen.ItemModelGenerator;
import com.greatorator.tolkienmobs.datagen.LangGenerator;
import com.greatorator.tolkienmobs.datagen.RecipeGenerator;
import com.greatorator.tolkienmobs.datagen.tags.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * Created by brandon3055 on 26/2/20.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TolkienDataHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        BlockTagGenerator blockGen = new BlockTagGenerator(gen, TolkienMobs.MODID, helper);

        if (event.includeClient()) {
            gen.addProvider(new LangGenerator(gen));
            gen.addProvider(new BlockStateGenerator(gen, helper));
            gen.addProvider(new ItemModelGenerator(gen, helper));
        }

        if (event.includeServer()) {
            gen.addProvider(new RecipeGenerator(gen));
            gen.addProvider(new TolkienLootHandler(gen));
            gen.addProvider(new BlockTagGenerator(gen, TolkienMobs.MODID, helper));
            gen.addProvider(new FluidTagGenerator(gen, TolkienMobs.MODID, helper));
            gen.addProvider(new ItemTagGenerator(gen, blockGen, TolkienMobs.MODID, helper));
            gen.addProvider(new EntityTagGenerator(gen, TolkienMobs.MODID, helper));
            gen.addProvider(new BiomeTagGenerator(gen, TolkienMobs.MODID, helper));
        }
    }
}
