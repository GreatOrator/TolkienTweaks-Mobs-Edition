package com.greatorator.tolkienmobs.datagen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BiomeGenerator {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS
            = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, MODID);

    public static void register(IEventBus modBus) {
        BIOMES.register(modBus);
    }
}
