package com.greatorator.tolkienmobs.entity.tile.render;

import com.greatorator.tolkienmobs.block.PiggyBankBlock;
import com.greatorator.tolkienmobs.entity.tile.PiggyBankTile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class RenderPiggyBankTile implements BlockEntityRenderer<PiggyBankTile> {
    public RenderPiggyBankTile(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(PiggyBankTile te, float partialTicks, PoseStack mStack, MultiBufferSource getter, int packedLight, int packedOverlay) {
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(PiggyBankBlock.FACING);

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
