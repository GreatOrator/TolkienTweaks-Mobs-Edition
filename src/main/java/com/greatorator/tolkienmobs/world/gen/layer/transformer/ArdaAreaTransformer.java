package com.greatorator.tolkienmobs.world.gen.layer.transformer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.List;

public class ArdaAreaTransformer implements IAreaTransformer0 {
    private final List<Biome> biomes;
    private final ForgeRegistry<Biome> biomeRegistry;

    public ArdaAreaTransformer(List<Biome> biomes, ForgeRegistry<Biome> biomeRegistry) {
        this.biomes = biomes;
        this.biomeRegistry = biomeRegistry;
    }

    @Override
    public int applyPixel(INoiseRandom rand, int x, int z) {
        return biomeRegistry.getID(biomes.get(rand.nextRandom(biomes.size())));
    }
}
