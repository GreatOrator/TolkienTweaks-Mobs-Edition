package com.greatorator.tolkienmobs.entity.tile.render;

import com.greatorator.tolkienmobs.block.MilestoneBlock;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.handler.MilestoneHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

public class MilestoneTileRender implements BlockEntityRenderer<MilestoneTile> {
    public MilestoneTileRender(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(MilestoneTile te, float partialTicks, PoseStack mStack, MultiBufferSource getter, int packedLight, int packedOverlay) {
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(MilestoneBlock.FACING);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();

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
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        mStack.popPose();

        if (MilestoneHandler.isKnownByClient(te.getUUID(), Minecraft.getInstance().player.getUUID())) {
            drawNameString(te, mStack, getter, Component.nullToEmpty(te.milestoneName.get()), packedLight);
        }
    }

    private void drawNameString(MilestoneTile te, PoseStack matrixStack, MultiBufferSource buffer, Component message, int light) {
        Minecraft mc = Minecraft.getInstance();
        Font fontRenderer = mc.font;
        float scale = 0.015F;
        int alpha = (int) (0.4F * 255.0F) << 24;
        float width = (-fontRenderer.width(message) / 2);

        matrixStack.pushPose();

        matrixStack.translate(0.5D, 2.0D, 0.5D);
        matrixStack.mulPose(Vector3f.YN.rotationDegrees(180F));
        matrixStack.scale(scale, -scale, scale);
        matrixStack.mulPose(mc.getEntityRenderDispatcher().cameraOrientation());
        Matrix4f matrix4f = matrixStack.last().pose();
        fontRenderer.drawInBatch(message, width, 0.0F, 553648127, false, matrix4f, buffer, false, alpha, light);
        fontRenderer.drawInBatch(message, width, 0.0F, 65535, true, matrix4f, buffer, true, 0, light);

        matrixStack.popPose();
    }
}
