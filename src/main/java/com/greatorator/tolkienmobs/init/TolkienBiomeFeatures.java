package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.world.components.config.FallenLogConfig;
import com.greatorator.tolkienmobs.world.components.config.RootConfig;
import com.greatorator.tolkienmobs.world.components.feature.templates.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienBiomeFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    public static final RegistryObject<Feature<RootConfig>> ROOTS = FEATURES.register("roots", () -> new RootTemplate(RootConfig.CODEC));
    public static final RegistryObject<Feature<FallenLogConfig>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogTemplate(FallenLogConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> RUBBLE = FEATURES.register("rubble", () -> new RubbleTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> STONE_SPIKE = FEATURES.register("stone_spike", () -> new StoneSpikeTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> MALLORN_LEAFPILES = FEATURES.register("mallorn_leafpiles", () -> new MallornFallenLeavesTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> MIRKWOOD_LEAFPILES = FEATURES.register("mirkwood_leafpiles", () -> new MirkwoodFallenLeavesTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CULUMALDA_LEAFPILES = FEATURES.register("culumalda_leafpiles", () -> new CulumaldaFallenLeavesTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LEBETHRON_LEAFPILES = FEATURES.register("lebethron_leafpiles", () -> new LebethronFallenLeavesTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FANGORNOAK_LEAFPILES = FEATURES.register("fangornoak_leafpiles", () -> new FangornoakFallenLeavesTemplate(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ROCKPILE = FEATURES.register("rockpile", () -> new RockpileTemplate(NoneFeatureConfiguration.CODEC));
}
