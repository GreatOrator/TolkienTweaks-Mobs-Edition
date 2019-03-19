package com.greatorator.tolkienmobs.world.types;

import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeSingleArda extends WorldType {
    public WorldTypeSingleArda(String name)
    {
        super(name);
        LogHelperTTM.info("Adventure on a smaller scale...");
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return new BiomeProviderSingle(BiomeInit.OLDFOREST);
    }
}