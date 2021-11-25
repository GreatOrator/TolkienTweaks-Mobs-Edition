package com.greatorator.tolkienmobs.world.gen.layer;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class ArdaLayer {
    private final LazyArea area;

    public ArdaLayer(IAreaFactory<LazyArea> area) {
        this.area = area.make();
    }

    public Biome get(Registry<Biome> biomes, int x, int z) {
        int i = area.get(x, z);

        RegistryKey<Biome> registrykey = ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getKey(i);
        if (registrykey == null) {
            throw new IllegalStateException("Unknown biome id emitted by layers: " + i);
        }

        Biome biome = biomes.get(registrykey);
        if (biome == null) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
            }

            System.err.println("Unknown biome id: " + i);
            return biomes.get(((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getKey(0));
        }

        return biome;
    }
}
