package com.greatorator.tolkienmobs.world.biomes;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by brandon3055 on 7/11/18.
 */
public interface IFogyBiome {

    int getFogColour(EntityPlayer player);

    float getFogDensity(EntityPlayer player);
}
