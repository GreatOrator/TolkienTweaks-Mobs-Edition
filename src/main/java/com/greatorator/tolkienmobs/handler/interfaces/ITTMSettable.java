package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Borrowed from Twilight Forest */
public interface ITTMSettable {
    void setBlockAndNotify(World world, BlockPos pos, BlockState state);
}
