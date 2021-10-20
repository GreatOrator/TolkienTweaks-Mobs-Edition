package com.greatorator.tolkienmobs.world.gen;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.BiomeGenerator;
import com.greatorator.tolkienmobs.world.gen.feature.TTMStoneSpikeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

/**
 * Created by brandon3055 on 20/10/2021
 */
@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TTMForgeFeatures {

    public static Feature<NoFeatureConfig> STONE_SPIKE = new TTMStoneSpikeFeature(NoFeatureConfig.CODEC);


    public static ConfiguredFeature<?, ? extends Feature<?>> STONE_SPIKE_CONFIG;
    public static ConfiguredFeature<?, ? extends Feature<?>> BLEAK_LAND_CONFIG;

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(STONE_SPIKE.setRegistryName("stone_spike_feature"));


        //Register Dependents.
        STONE_SPIKE_CONFIG = register("stone_spike", STONE_SPIKE.configured(IFeatureConfig.NONE).count(3));
        BLEAK_LAND_CONFIG = register("bleak_land", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(TTMFeatures.DEADTREE.weighted(0.1F), STONE_SPIKE_CONFIG.weighted(0.1F)), STONE_SPIKE_CONFIG)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));

    }

    public static void biomeLoading(BiomeLoadingEvent event) {
        if (event.getName().equals(BiomeGenerator.BIOME_MORDOR.getId())) {

            event.getGeneration().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, BLEAK_LAND_CONFIG);

            LOGGER.info("What? " + event);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, TolkienMobs.MODID + ":" + name, feature);
    }


}
