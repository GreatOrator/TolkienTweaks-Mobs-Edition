package com.greatorator.tolkienmobs.handler.handler_old.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public interface ITTMStructureSummon {
    boolean generate(World worldIn, Random r, BlockPos bp);
}
