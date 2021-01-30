package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Created by brandon3055 on 7/11/18.
 */
public interface IFogyBiome {

    int getFogColour(PlayerEntity player);

    float getFogDensity(PlayerEntity player);
}
