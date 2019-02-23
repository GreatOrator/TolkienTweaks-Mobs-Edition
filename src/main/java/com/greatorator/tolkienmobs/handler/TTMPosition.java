package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTestTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.Stack;

public class TTMPosition {
    private int posX;
    private int posY;
    private int posZ;

    private int radius;

    public TTMPosition(BlockPos pos, int radius) {
        this.posX = pos.getX();
        this.posY = pos.getY();
        this.posZ = pos.getZ();
        this.radius = radius;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public int getRadius() {
        return radius;
    }

    public void generate(World world, Random rand) {
        for (int y = posY; y > posY - radius; y -= 2) {
            if (radius > 1) {
                radius--;
            }
            BlockPos center = new BlockPos(posX, y, posZ);
            Stack<BlockPos> pendingCoords = new Stack<BlockPos>();
            pendingCoords.add(center);
            while (!pendingCoords.isEmpty()) {
                BlockPos coord = pendingCoords.pop();
                if (world.getBlockState(coord).getMaterial().isReplaceable() || world.getBlockState(coord.up()).getMaterial().isReplaceable()) {
                    world.setBlockState(coord, TTMFeatures.LOGS.getDefaultState());
                }
                for (EnumFacing direction : WorldGenTestTree.DIRECTIONS) {
                    BlockPos neighborCoord = new BlockPos(coord.getX(), coord.getY(), coord.getZ());
                    IBlockState block = world.getBlockState(neighborCoord);
                    IBlockState above = world.getBlockState(neighborCoord.up());
                    if (!pendingCoords.contains(neighborCoord) && getDistanceBetweenChunkCoordinates(center, neighborCoord) <= radius && (block.getMaterial().isReplaceable() || (above.getMaterial().isReplaceable() && block.getBlock() != TTMFeatures.LOGS))) {
                        pendingCoords.add(neighborCoord);
                    }
                }
            }
        }
    }

    private int getDistanceBetweenChunkCoordinates(BlockPos a, BlockPos b) {
        return (int) Math.round(Math.sqrt(a.distanceSq(b.getX(), b.getY(), b.getZ())));
    }
}