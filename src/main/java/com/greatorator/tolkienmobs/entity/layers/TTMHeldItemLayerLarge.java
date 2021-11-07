package com.greatorator.tolkienmobs.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;

public class TTMHeldItemLayerLarge extends HeldItemLayer {
    public TTMHeldItemLayerLarge(IEntityRenderer p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int p_225628_3_, LivingEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        boolean lvt_11_1_ = entity.getMainArm() == HandSide.RIGHT;
        ItemStack lvt_12_1_ = lvt_11_1_ ? entity.getOffhandItem() : entity.getMainHandItem();
        ItemStack lvt_13_1_ = lvt_11_1_ ? entity.getMainHandItem() : entity.getOffhandItem();
        if (!lvt_12_1_.isEmpty() || !lvt_13_1_.isEmpty()) {
            matrixStack.pushPose();
            matrixStack.scale(1.0F, 1.0F, 2.5F);
            this.renderArmWithItem(entity, lvt_13_1_, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStack, renderTypeBuffer, p_225628_3_);
            this.renderArmWithItem(entity, lvt_12_1_, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStack, renderTypeBuffer, p_225628_3_);
            matrixStack.popPose();
        }
    }

    private void renderArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_, IRenderTypeBuffer p_229135_6_, int p_229135_7_) {
        if (!p_229135_2_.isEmpty()) {
            p_229135_5_.pushPose();
            ((IHasArm)this.getParentModel()).translateToHand(p_229135_4_, p_229135_5_);
            p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            p_229135_5_.mulPose(Vector3f.ZP.rotationDegrees(0.0F));
            boolean lvt_8_1_ = p_229135_4_ == HandSide.LEFT;
            p_229135_5_.translate((double)((float)(lvt_8_1_ ? 1 : -7.5) / 8.0F), 0.125D, -0.625D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, lvt_8_1_, p_229135_5_, p_229135_6_, p_229135_7_);
            p_229135_5_.popPose();
        }
    }
}
