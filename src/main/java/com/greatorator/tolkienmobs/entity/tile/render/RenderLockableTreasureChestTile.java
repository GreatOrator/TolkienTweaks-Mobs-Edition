package com.greatorator.tolkienmobs.entity.tile.render;

import com.greatorator.tolkienmobs.block.LockableTreasureChestBlock;
import com.greatorator.tolkienmobs.entity.tile.LockableTreasureChestTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;

public class RenderLockableTreasureChestTile extends TileEntityRenderer<LockableTreasureChestTile> {
    public RenderLockableTreasureChestTile(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LockableTreasureChestTile te, float partialTicks, MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, int packedOverlay) {
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(LockableTreasureChestBlock.FACING);

        mStack.pushPose();
        mStack.translate(0.5, 0.5, 0.5);
        mStack.mulPose(new Quaternion(90, 0, 0, true));

        switch (facing) {
            case NORTH:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                break;
            case SOUTH:
                mStack.mulPose(new Quaternion(0, 0, -90, true));
                break;
            case WEST:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                break;
            case EAST:
                mStack.mulPose(new Quaternion(0, 0, 180, true));
                break;
        }

        mStack.popPose();
    }
}