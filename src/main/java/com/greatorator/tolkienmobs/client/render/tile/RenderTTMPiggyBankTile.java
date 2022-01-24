package com.greatorator.tolkienmobs.client.render.tile;

import com.greatorator.tolkienmobs.block.BlockTTMPiggyBank;
import com.greatorator.tolkienmobs.entity.tile.TTMPiggyBankTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;

public class RenderTTMPiggyBankTile extends TileEntityRenderer<TTMPiggyBankTile> {
    public RenderTTMPiggyBankTile(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TTMPiggyBankTile te, float partialTicks, MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, int packedOverlay) {
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(BlockTTMPiggyBank.FACING);

        mStack.pushPose();
        mStack.translate(0.5, 0.5, 0.5);
        mStack.mulPose(new Quaternion(90, 0, 0, true));

        switch (facing) {
            case NORTH:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                mStack.scale(0.5F, 0.5F,0.5F);
                break;
            case SOUTH:
                mStack.mulPose(new Quaternion(0, 0, -90, true));
                mStack.scale(0.5F, 0.5F,0.5F);
                break;
            case WEST:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                mStack.scale(0.5F, 0.5F,0.5F);
                break;
            case EAST:
                mStack.mulPose(new Quaternion(0, 0, 180, true));
                mStack.scale(0.5F, 0.5F,0.5F);
                break;
        }

        mStack.popPose();
    }
}
