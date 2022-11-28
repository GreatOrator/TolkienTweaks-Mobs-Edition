package com.greatorator.tolkienmobs.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienDimensions {
    public static final ResourceKey<Level> ARDA_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(MODID, "arda"));
    public static final ResourceKey<DimensionType> ARDA_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, ARDA_KEY.getRegistryName());


    public static void register() {
        System.out.println(NAME + " - Dimensions");
    }
}
