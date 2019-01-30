package com.greatorator.tolkienmobs.world.types;

import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.layer.GenLayerArda;
import com.greatorator.tolkienmobs.world.gen.providers.BiomeProviderTTM;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.layer.GenLayer;

import javax.annotation.Nullable;
import java.util.List;

public class WorldTypeArda extends WorldType {
    public WorldTypeArda(String name)
    {
        super(name);
        LogHelperTTM.info("Begin our journey to Middle-earth...");
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return new BiomeProviderTTM(world);
    }
}