package com.greatorator.tolkienmobs.world.types;

import com.greatorator.tolkienmobs.world.gen.layer.GenLayerArda;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldTypeArda extends WorldType {
    public WorldTypeArda(String name)
    {
        super(name);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, ChunkGeneratorSettings chunkSettings)
    {
        return new GenLayerArda(worldSeed, parentLayer, this, chunkSettings);
    }
}
