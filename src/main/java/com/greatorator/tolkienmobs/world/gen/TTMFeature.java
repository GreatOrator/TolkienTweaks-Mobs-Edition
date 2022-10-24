package com.greatorator.tolkienmobs.world.gen;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.gen.feature.TTMFallenLogFeature;
import com.greatorator.tolkienmobs.world.gen.feature.TTMRubbleFeature;
import com.greatorator.tolkienmobs.world.gen.feature.TTMStoneSpikeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by brandon3055 on 20/10/2021
 */
@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TTMFeature {

    public static Feature<NoFeatureConfig> STONE_SPIKE = new TTMStoneSpikeFeature(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SMALL_LOG = new TTMFallenLogFeature(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> RANDOM_RUBBLE = new TTMRubbleFeature(NoFeatureConfig.CODEC);


    public static ConfiguredFeature<?, ? extends Feature<?>> STONE_SPIKE_CONFIG;
    public static ConfiguredFeature<?, ? extends Feature<?>> SMALL_LOG_CONFIG;
    public static ConfiguredFeature<?, ? extends Feature<?>> RANDOM_RUBBLE_CONFIG;
    public static ConfiguredFeature<?, ? extends Feature<?>> BLEAK_LAND_CONFIG;

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(STONE_SPIKE.setRegistryName("stone_spike_feature"));
        event.getRegistry().register(SMALL_LOG.setRegistryName("small_log_feature"));
        event.getRegistry().register(RANDOM_RUBBLE.setRegistryName("random_rubble_feature"));


        //Register Dependents.
        STONE_SPIKE_CONFIG = register("stone_spike", STONE_SPIKE.configured(IFeatureConfig.NONE).range(128).squared().count(3));
        SMALL_LOG_CONFIG = register("small_log", SMALL_LOG.configured(IFeatureConfig.NONE).range(128).squared().count(10));
        RANDOM_RUBBLE_CONFIG = register("random_rubble", RANDOM_RUBBLE.configured(IFeatureConfig.NONE).range(128).squared().count(10));
        BLEAK_LAND_CONFIG = register("bleak_land", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(RANDOM_RUBBLE_CONFIG.weighted(0.4F), SMALL_LOG_CONFIG.weighted(0.05F)), SMALL_LOG_CONFIG)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));

    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, TolkienMobs.MODID + ":" + name, feature);
    }


}
