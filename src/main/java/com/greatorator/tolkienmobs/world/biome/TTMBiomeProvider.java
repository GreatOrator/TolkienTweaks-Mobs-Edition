package com.greatorator.tolkienmobs.world.biome;

import com.greatorator.tolkienmobs.datagen.BiomeGenerator;
import com.greatorator.tolkienmobs.world.gen.layer.ArdaLayer;
import com.greatorator.tolkienmobs.world.gen.layer.transformer.ArdaAreaTransformer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;
import java.util.stream.Collectors;

public class TTMBiomeProvider extends BiomeProvider {
    public static final Codec<TTMBiomeProvider> PRIMAL_CODEC = RecordCodecBuilder.create((builder) -> {
        return builder
                .group(Codec.LONG.fieldOf("seed").stable().forGetter((biomeProvider) -> biomeProvider.seed),
                        Codec.BOOL.optionalFieldOf("legacy_biome_init_layer", Boolean.valueOf(false), Lifecycle.stable()).forGetter((biomeProvider) -> biomeProvider.legacyBiomeInitLayer),
                        Codec.BOOL.fieldOf("large_biomes").orElse(true).stable().forGetter((biomeProvider) -> biomeProvider.largeBiomes),
                        RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((biomeProvider) -> biomeProvider.biomes))
                .apply(builder, builder.stable(TTMBiomeProvider::new));
    });

    private final long seed;
    private final boolean legacyBiomeInitLayer;
    private final boolean largeBiomes;
    private final ArdaLayer noiseBiomeLayer;
    private final Registry<Biome> biomes;

    public TTMBiomeProvider(long seed, boolean legacyLayer, boolean largeBiome, Registry<Biome> biomeRegistry) {
        super(biomeRegistry.stream().collect(Collectors.toList()));
        this.seed = seed;

        List<Biome> biomes = new ArrayList<Biome>();
        for (RegistryObject<Biome> b : BiomeGenerator.getBiomes()) {
            biomes.add(b.get());
        }

        this.legacyBiomeInitLayer = legacyLayer;
        this.largeBiomes = largeBiome;
        this.noiseBiomeLayer = buildLayerGen(seed, biomes, (ForgeRegistry<Biome>) ForgeRegistries.BIOMES);
        this.biomes = biomeRegistry;
    }

    private static ArdaLayer buildLayerGen(long seed, List<Biome> biomes, ForgeRegistry<Biome> biomeRegistry) {
        IAreaFactory<LazyArea> iareafactory = buildLayers(biomes, biomeRegistry, (l) -> {
            return new LazyAreaLayerContext(25, seed, l);
        });
        return new ArdaLayer(iareafactory);
    }

    private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> buildLayers(List<Biome> biomes, ForgeRegistry<Biome> biomeRegistry,
                                                                                                    LongFunction<C> seedHandler) {
        IAreaFactory<T> iareafactory = new ArdaAreaTransformer(biomes, biomeRegistry).run(seedHandler.apply(1L));
        iareafactory = LayerUtil.zoom(2001L, ZoomLayer.NORMAL, iareafactory, 4, seedHandler);
        return iareafactory;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return noiseBiomeLayer.get(biomes, x, z);
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return PRIMAL_CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BiomeProvider withSeed(long seed) {
        return new TTMBiomeProvider(seed, this.legacyBiomeInitLayer, this.largeBiomes, this.biomes);
    }

    private static float getRandomNoise(SimplexNoiseGenerator noiseGenerator, int x, int z) {
        int i = x / 2;
        int j = z / 2;
        int k = x % 2;
        int l = z % 2;
        float f = 100.0F - MathHelper.sqrt((float)(x * x + z * z)) * 8.0F;
        f = MathHelper.clamp(f, -100.0F, 80.0F);

        for(int i1 = -12; i1 <= 12; ++i1) {
            for(int j1 = -12; j1 <= 12; ++j1) {
                long k1 = (long)(i + i1);
                long l1 = (long)(j + j1);
                if (k1 * k1 + l1 * l1 > 4096L && noiseGenerator.getValue((double)k1, (double)l1) < (double)-0.9F) {
                    float f1 = (MathHelper.abs((float)k1) * 3439.0F + MathHelper.abs((float)l1) * 147.0F) % 13.0F + 9.0F;
                    float f2 = (float)(k - i1 * 2);
                    float f3 = (float)(l - j1 * 2);
                    float f4 = 100.0F - MathHelper.sqrt(f2 * f2 + f3 * f3) * f1;
                    f4 = MathHelper.clamp(f4, -100.0F, 80.0F);
                    f = Math.max(f, f4);
                }
            }
        }

        return f;
    }
}
