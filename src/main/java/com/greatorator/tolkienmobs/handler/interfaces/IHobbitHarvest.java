package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface IHobbitHarvest {
    boolean canHarvest(BlockState state);

    boolean harvest(World world, BlockPos pos, BlockState state, @Nonnull PlayerEntity player, boolean replant);
}
