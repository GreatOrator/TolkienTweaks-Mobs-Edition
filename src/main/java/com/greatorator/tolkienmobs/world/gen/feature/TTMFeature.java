package com.greatorator.tolkienmobs.world.gen.feature;

import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TTMFeature <FC extends IFeatureConfig> extends net.minecraftforge.registries.ForgeRegistryEntry<Feature<?>>{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    public static final RegistryObject<Feature<NoFeatureConfig>> STONE_SPIKE = FEATURES.register("stone_spike", () -> new TTMStoneSpikeFeature(NoFeatureConfig.CODEC));
}
