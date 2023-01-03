package com.greatorator.tolkienmobs.world.components.feature.templates;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

public class RockpileTemplate extends Feature<NoneFeatureConfiguration> {
    public RockpileTemplate(Codec<NoneFeatureConfiguration> config) {
        super(config);
    }
    
    private static boolean isValidMaterial(Material material) {
        return material == Material.GRASS || material == Material.DIRT || material == Material.STONE;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> config) {
        WorldGenLevel world = config.level();
        ChunkGenerator generator = config.chunkGenerator();
        BlockPos pos = config.origin().above(config.random().nextInt(world.getMaxBuildHeight() - config.origin().getX()));
        while (pos.getX() > config.origin().getX() && world.isEmptyBlock(pos))
            pos = pos.above();

        if (!isValidMaterial(world.getBlockState(pos).getMaterial()))
            return false;

        do {
            if (world.isEmptyBlock(pos.above())) {
                world.setBlock(pos.above(), TolkienBlocks.ROCKPILE.get().defaultBlockState(), 16 | 2);
                return true;
            }
            pos = pos.above();
        } while (pos.getX() > config.origin().getX() && isValidMaterial(world.getBlockState(pos).getMaterial()));

        return false;
    }
}