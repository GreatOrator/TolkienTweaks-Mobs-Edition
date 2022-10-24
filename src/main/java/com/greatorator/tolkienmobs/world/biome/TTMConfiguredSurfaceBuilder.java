package com.greatorator.tolkienmobs.world.biome;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TTMConfiguredSurfaceBuilder {
    public static ConfiguredSurfaceBuilder<?> MORDOR_SURFACE = register("mordor_surface",
            SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
                    Blocks.GRASS_BLOCK.defaultBlockState(),
                    Blocks.BLACKSTONE.defaultBlockState(),
                    Blocks.BASALT.defaultBlockState()
            )));
    public static ConfiguredSurfaceBuilder<?> FANGORN_SURFACE = register("fangorn_surface",
            SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
                    Blocks.PODZOL.defaultBlockState(),
                    Blocks.COARSE_DIRT.defaultBlockState(),
                    Blocks.GRAVEL.defaultBlockState()
            )));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(TolkienMobs.MODID, name), csb);
    }
}
