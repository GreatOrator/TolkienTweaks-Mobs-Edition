package com.greatorator.tolkienmobs.event;

import com.greatorator.tolkienmobs.world.components.placements.OrePlacement;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class WorldEvents {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacement.ORE_AMMOLITE);
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacement.ORE_MITHRIL);
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacement.ORE_MORGULIRON);
    }
}
