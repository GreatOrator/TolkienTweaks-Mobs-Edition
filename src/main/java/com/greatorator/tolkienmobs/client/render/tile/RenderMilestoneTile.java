package com.greatorator.tolkienmobs.client.render.tile;

import com.greatorator.tolkienmobs.block.MilestoneBlock;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.handler.MilestoneSaveData;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;

public class RenderMilestoneTile extends TileEntityRenderer<MilestoneTile> {
    public RenderMilestoneTile(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(MilestoneTile te, float partialTicks, MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, int packedOverlay) {
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(MilestoneBlock.FACING);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

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

        if (MilestoneSaveData.isKnownByClient(te.getUUID(), Minecraft.getInstance().player.getUUID())) {
            drawNameString(te, mStack, getter, ITextComponent.nullToEmpty(te.milestoneName.get()/*.replace("_", " ")*/), packedLight);
        }
    }

    private void drawNameString(MilestoneTile te, MatrixStack matrixStack, IRenderTypeBuffer buffer, ITextComponent message, int light) {
        FontRenderer fontRenderer = this.renderer.font;
        Minecraft mc = Minecraft.getInstance();
        final float rotation = te.getBlockState().getValue(MilestoneBlock.FACING).toYRot();
        float f3 = 0.015F;
        float opacity = mc.options.getBackgroundOpacity(0.25F);
        int alpha = (int) (opacity * 255.0F) << 24;
        float width = (-fontRenderer.width(message) / 2);

        matrixStack.pushPose();

        matrixStack.translate(0.5D, 2.0D, 0.5D);
        matrixStack.mulPose(Vector3f.YN.rotationDegrees(rotation));
        matrixStack.scale(f3, -f3, f3);
        Matrix4f matrix4f = matrixStack.last().pose();
        fontRenderer.drawInBatch(message, width, 0.0F, 553648127, false, matrix4f, buffer, false, alpha, light);
        fontRenderer.drawInBatch(message, width, 0.0F, 65535, true, matrix4f, buffer, true, 0, light);

        matrixStack.popPose();
    }
}
