package com.greatorator.tolkienmobs.entity.ambient.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMFrog;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Frog - GreatOrator
 */
public class ModelTTMFrog<E extends EntityTTMFrog> extends EntityModel<EntityTTMFrog> {
    public ModelRenderer frogHead;
    public ModelRenderer frogFootLeft;
    public ModelRenderer frogLeftThigh;
    public ModelRenderer frogBody;
    public ModelRenderer frogRightArm;
    public ModelRenderer frogFootRight;
    public ModelRenderer frogRightThigh;
    public ModelRenderer frogLeftArm;
    public ModelRenderer frogEyeRight;
    public ModelRenderer frogEyeLeft;
    private float jumpRotation;

    public ModelTTMFrog() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.frogBody = new ModelRenderer(this, 0, 0);
        this.frogBody.setPos(0.0F, 19.0F, 8.0F);
        this.frogBody.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10, 0.0F);
        this.setRotateAngle(frogBody, -0.3490658503988659F, 0.0F, 0.0F);
        this.frogFootLeft = new ModelRenderer(this, 10, 26);
        this.frogFootLeft.mirror = true;
        this.frogFootLeft.setPos(3.0F, 17.5F, 5.7F);
        this.frogFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 5, 0.0F);
        this.frogFootRight = new ModelRenderer(this, 10, 26);
        this.frogFootRight.setPos(-3.0F, 17.5F, 5.7F);
        this.frogFootRight.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 5, 0.0F);
        this.frogRightArm = new ModelRenderer(this, 0, 15);
        this.frogRightArm.setPos(-3.0F, 17.0F, -1.0F);
        this.frogRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(frogRightArm, -0.19198621771937624F, 0.0F, 0.0F);
        this.frogHead = new ModelRenderer(this, 32, 0);
        this.frogHead.setPos(0.0F, 17.0F, -1.0F);
        this.frogHead.addBox(-2.5F, -4.0F, -5.0F, 5, 3, 5, 0.0F);
        this.frogRightThigh = new ModelRenderer(this, 16, 15);
        this.frogRightThigh.setPos(-3.0F, 17.5F, 3.7F);
        this.frogRightThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(frogRightThigh, -0.3665191429188092F, 0.0F, 0.0F);
        this.frogLeftArm = new ModelRenderer(this, 0, 15);
        this.frogLeftArm.mirror = true;
        this.frogLeftArm.setPos(3.0F, 17.0F, -1.0F);
        this.frogLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(frogLeftArm, -0.19198621771937624F, 0.0F, 0.0F);
        this.frogEyeRight = new ModelRenderer(this, 0, 0);
        this.frogEyeRight.setPos(-2.1F, -3.0F, -2.0F);
        this.frogEyeRight.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.frogLeftThigh = new ModelRenderer(this, 16, 15);
        this.frogLeftThigh.mirror = true;
        this.frogLeftThigh.setPos(3.0F, 17.5F, 3.7F);
        this.frogLeftThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(frogLeftThigh, -0.3665191429188092F, 0.0F, 0.0F);
        this.frogEyeLeft = new ModelRenderer(this, 0, 0);
        this.frogEyeLeft.mirror = true;
        this.frogEyeLeft.setPos(2.1F, -3.0F, -2.0F);
        this.frogEyeLeft.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.frogHead.addChild(this.frogEyeRight);
        this.frogHead.addChild(this.frogEyeLeft);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            float f = 1.5F;
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.56666666F, 0.56666666F, 0.56666666F);
            matrixStackIn.translate(0.0D, 1.375D, 0.125D);
            ImmutableList.of(this.frogHead).forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.4F, 0.4F, 0.4F);
            matrixStackIn.translate(0.0D, 2.25D, 0.0D);
            ImmutableList.of(this.frogFootLeft, this.frogFootRight, this.frogLeftThigh, this.frogRightThigh, this.frogBody, this.frogLeftArm, this.frogRightArm).forEach((p_228291_8_) -> {
                p_228291_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        } else {
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
            matrixStackIn.translate(0.0D, 1.0D, 0.0D);
            ImmutableList.of(this.frogFootLeft, this.frogFootRight, this.frogLeftThigh, this.frogRightThigh, this.frogBody, this.frogLeftArm, this.frogRightArm, this.frogHead).forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }
    }

    @Override
    public void setupAnim(EntityTTMFrog entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float)entityIn.tickCount;
        this.frogHead.xRot = headPitch * 0.017453292F;
        this.frogHead.yRot = netHeadYaw * 0.017453292F;
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float)Math.PI);
        this.frogLeftThigh.xRot = (this.jumpRotation * 50.0F - 21.0F) * 0.017453292F;
        this.frogRightThigh.xRot = (this.jumpRotation * 50.0F - 21.0F) * 0.017453292F;
        this.frogFootLeft.xRot = this.jumpRotation * 50.0F * 0.017453292F;
        this.frogFootRight.xRot = this.jumpRotation * 50.0F * 0.017453292F;
        this.frogLeftArm.xRot = (this.jumpRotation * -40.0F - 11.0F) * 0.017453292F;
        this.frogRightArm.xRot = (this.jumpRotation * -40.0F - 11.0F) * 0.017453292F;
    }

    @Override
    public void prepareMobModel(EntityTTMFrog entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
    }
}