package com.greatorator.tolkienmobs.world.gen.layer;

import com.greatorator.tolkienmobs.init.BiomeInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesArda extends GenLayer {
    protected Biome[] allowedBiomes = {
            BiomeInit.BARROW_DOWNS,
            BiomeInit.DAGORLAD,
            BiomeInit.FANGORN,
            BiomeInit.FIRIEN,
            BiomeInit.GLADDEN,
            BiomeInit.HARADWAITH,
            BiomeInit.HITHAEGLIR,
            BiomeInit.IRON_HILLS,
            BiomeInit.LORINAND,
            BiomeInit.NINDALF,
            BiomeInit.MIRKWOOD,
            BiomeInit.MORDOR,
            BiomeInit.SHIRE
    };

    public GenLayerBiomesArda(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerBiomesArda(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
            }
        }
        return dest;
    }
}