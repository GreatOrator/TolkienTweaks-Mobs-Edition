package com.greatorator.tolkienmobs.entity.ambient.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMThrush;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

///* Thrush - GreatOrator */
public class ModelTTMThrush extends SegmentedModel<EntityTTMThrush> {
    public ModelRenderer ThrushBody;
    public ModelRenderer ThrushTail1;
    public ModelRenderer ThrushLegL1;
    public ModelRenderer ThrushLegR1;
    public ModelRenderer ThrushHead;
    public ModelRenderer ThrushWingL1;
    public ModelRenderer ThrushWingR1;
    public ModelRenderer ThrushTail2;
    public ModelRenderer ThrushTail3;
    public ModelRenderer ThrushLegL2;
    public ModelRenderer ThrushLegR2;
    public ModelRenderer ThrushBeakTop;
    public ModelRenderer ThrushBeakBottom;
    public ModelRenderer ThrushWingL2;
    public ModelRenderer ThrushWingR2;

    public ModelTTMThrush() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.ThrushTail3 = new ModelRenderer(this, -3, 9);
        this.ThrushTail3.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.ThrushTail3.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(ThrushTail3, 0.0F, -0.2665117767795341F, 0.0F);
        this.ThrushLegL2 = new ModelRenderer(this, 0, 5);
        this.ThrushLegL2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.ThrushLegL2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(ThrushLegL2, -1.3613568165555772F, 0.0F, 0.0F);
        this.ThrushLegL1 = new ModelRenderer(this, 0, 3);
        this.ThrushLegL1.setRotationPoint(1.3F, 1.5F, 0.0F);
        this.ThrushLegL1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(ThrushLegL1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushWingL2 = new ModelRenderer(this, 2, 9);
        this.ThrushWingL2.setRotationPoint(5.0F, -1.0F, -1.5F);
        this.ThrushWingL2.addBox(0.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushHead = new ModelRenderer(this, 12, 0);
        this.ThrushHead.setRotationPoint(0.5F, -0.1F, -3.0F);
        this.ThrushHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
        this.setRotateAngle(ThrushHead, 0.5235987755982988F, 0.0F, 0.0F);
        this.ThrushLegR1 = new ModelRenderer(this, 0, 3);
        this.ThrushLegR1.setRotationPoint(-0.3F, 1.5F, 0.0F);
        this.ThrushLegR1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(ThrushLegR1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushWingL1 = new ModelRenderer(this, 0, 9);
        this.ThrushWingL1.setRotationPoint(1.0F, 1.0F, 1.3F);
        this.ThrushWingL1.addBox(1.0F, -1.0F, -3.0F, 4, 0, 3, 0.0F);
        this.ThrushWingR1 = new ModelRenderer(this, 0, 9);
        this.ThrushWingR1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.ThrushWingR1.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushBody = new ModelRenderer(this, 0, 0);
        this.ThrushBody.setRotationPoint(0.0F, 20.3F, 0.5F);
        this.ThrushBody.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(ThrushBody, -0.5235987755982988F, 0.0F, 0.0F);
        this.ThrushTail1 = new ModelRenderer(this, -4, 9);
        this.ThrushTail1.setRotationPoint(0.0F, -0.8F, 4.3F);
        this.ThrushTail1.addBox(-0.5F, -0.5F, -1.5F, 2, 0, 4, 0.0F);
        this.setRotateAngle(ThrushTail1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushBeakTop = new ModelRenderer(this, 0, 0);
        this.ThrushBeakTop.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.ThrushBeakTop.addBox(-0.5F, -0.8F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ThrushBeakTop, 0.08726646259971647F, 0.0F, 0.0F);
        this.ThrushBeakBottom = new ModelRenderer(this, 20, 3);
        this.ThrushBeakBottom.setRotationPoint(0.0F, 0.4F, 0.0F);
        this.ThrushBeakBottom.addBox(-0.5F, -0.9F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ThrushBeakBottom, -0.08726646259971647F, 0.0F, 0.0F);
        this.ThrushTail2 = new ModelRenderer(this, -3, 9);
        this.ThrushTail2.setRotationPoint(1.0F, 0.0F, -1.5F);
        this.ThrushTail2.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(ThrushTail2, 0.0F, 0.2617993877991494F, 0.0F);
        this.ThrushWingR2 = new ModelRenderer(this, 2, 9);
        this.ThrushWingR2.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.ThrushWingR2.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushLegR2 = new ModelRenderer(this, 0, 5);
        this.ThrushLegR2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.ThrushLegR2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(ThrushLegR2, -1.3613568165555772F, 0.0F, 0.0F);
        this.ThrushTail1.addChild(this.ThrushTail3);
        this.ThrushLegL1.addChild(this.ThrushLegL2);
        this.ThrushBody.addChild(this.ThrushLegL1);
        this.ThrushWingL1.addChild(this.ThrushWingL2);
        this.ThrushBody.addChild(this.ThrushHead);
        this.ThrushBody.addChild(this.ThrushLegR1);
        this.ThrushBody.addChild(this.ThrushWingL1);
        this.ThrushBody.addChild(this.ThrushWingR1);
        this.ThrushBody.addChild(this.ThrushTail1);
        this.ThrushHead.addChild(this.ThrushBeakTop);
        this.ThrushBeakTop.addChild(this.ThrushBeakBottom);
        this.ThrushTail1.addChild(this.ThrushTail2);
        this.ThrushWingR1.addChild(this.ThrushWingR2);
        this.ThrushLegR1.addChild(this.ThrushLegR2);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.ThrushBody);
    }

    public void setRotationAngles(EntityTTMThrush entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotationAngles(getThrushState(entityIn), entityIn.ticksExisted, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public void setLivingAnimations(EntityTTMThrush entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.setLivingAnimations(getThrushState(entityIn));
    }

    public void renderOnShoulder(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
        this.setLivingAnimations(ModelTTMThrush.State.ON_SHOULDER);
        this.setRotationAngles(ModelTTMThrush.State.ON_SHOULDER, p_228284_9_, p_228284_5_, p_228284_6_, 0.0F, p_228284_7_, p_228284_8_);
        this.getParts().forEach((p_228285_4_) -> {
            p_228285_4_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        });
    }

    private void setRotationAngles(ModelTTMThrush.State p_217162_1_, int p_217162_2_, float p_217162_3_, float p_217162_4_, float p_217162_5_, float p_217162_6_, float p_217162_7_) {
        this.ThrushHead.rotateAngleX = p_217162_7_ * 0.017453292F;
        this.ThrushHead.rotateAngleY = p_217162_6_ * 0.017453292F;
        this.ThrushHead.rotateAngleZ = 0.0F;
        this.ThrushHead.rotationPointX = 0.5F;
        this.ThrushBody.rotationPointX = 0.0F;
        this.ThrushTail1.rotationPointX = 0.0F;
        this.ThrushWingR1.rotationPointX = 1.0F;
        this.ThrushWingL1.rotationPointX = 1.0F;
        switch(p_217162_1_) {
            case SITTING:
                break;
            case PARTY:
                float f1 = MathHelper.cos((float)p_217162_2_);
                float f2 = MathHelper.sin((float)p_217162_2_);
                this.ThrushHead.rotationPointX = f1;
                this.ThrushHead.rotationPointY = 15.69F + f2;
                this.ThrushHead.rotateAngleX = 0.0F;
                this.ThrushHead.rotateAngleY = 0.0F;
                this.ThrushHead.rotateAngleZ = MathHelper.sin((float)p_217162_2_) * 0.4F;
                this.ThrushBody.rotationPointX = f1;
                this.ThrushBody.rotationPointY = -0.1F + f2;
                this.ThrushWingL1.rotateAngleZ = -0.0873F - p_217162_5_;
                this.ThrushWingL1.rotationPointX = 1.0F + f1;
                this.ThrushWingL1.rotationPointY = 1.65F + f2;
                this.ThrushWingL1.rotationPointZ = -0.3F + f1;
                this.ThrushWingR1.rotateAngleZ = 0.0873F + p_217162_5_;
                this.ThrushWingR1.rotationPointX = -1.0F + f1;
                this.ThrushWingR1.rotationPointY = -0.05F + f2;
                this.ThrushTail1.rotationPointX = f1;
                this.ThrushTail1.rotationPointY = -0.8F + f2;
                break;
            case STANDING:
                this.ThrushLegL1.rotateAngleX += MathHelper.cos(p_217162_3_ * 0.6662F) * 1.4F * p_217162_4_;
                this.ThrushLegR1.rotateAngleX += MathHelper.cos(p_217162_3_ * 0.6662F + (float)Math.PI) * 1.4F * p_217162_4_;
            case FLYING:
            case ON_SHOULDER:
            default:
                float f3 = p_217162_5_ * 0.3F;
                this.ThrushHead.rotationPointY = f3;
                this.ThrushTail1.rotateAngleX = 1.015F + MathHelper.cos(p_217162_3_ * 0.6662F) * 0.3F * p_217162_4_;
                this.ThrushTail1.rotationPointY = -0.8F + f3;
                this.ThrushBody.rotationPointY = -0.1F + f3;
                this.ThrushWingL1.rotateAngleZ = -0.0873F - p_217162_5_;
                this.ThrushWingL1.rotationPointY = 1.65F + f3;
                this.ThrushWingL1.rotationPointZ = -0.3F + f3;
                this.ThrushWingR1.rotateAngleZ = 0.0873F + p_217162_5_;
                this.ThrushWingR1.rotationPointY = -0.05F + f3;
                this.ThrushLegL1.rotationPointY = 1.5F + f3;
                this.ThrushLegR1.rotationPointY = 1.5F + f3;
        }

    }

    private void setLivingAnimations(ModelTTMThrush.State p_217160_1_) {
        this.ThrushBody.rotateAngleX = 0.4937F;
        this.ThrushWingL1.rotateAngleX = -((float)Math.PI * 2F / 9F);
        this.ThrushWingL1.rotateAngleY = -(float)Math.PI;
        this.ThrushWingR1.rotateAngleX = -((float)Math.PI * 2F / 9F);
        this.ThrushWingR1.rotateAngleY = -(float)Math.PI;
        this.ThrushLegL1.rotateAngleX = -0.0299F;
        this.ThrushLegR1.rotateAngleX = -0.0299F;
        this.ThrushLegL1.rotationPointY = 1.5F;
        this.ThrushLegR1.rotationPointY = 1.5F;
        switch(p_217160_1_) {
            case SITTING:
                float f = 1.9F;
                this.ThrushHead.rotationPointY = -0.1F;
                this.ThrushTail1.rotateAngleX = 1.5388988F;
                this.ThrushTail1.rotationPointY = 1.1F;
                this.ThrushBody.rotationPointY = 20.3F;
                this.ThrushWingL1.rotateAngleZ = -0.0873F;
                this.ThrushWingL1.rotationPointY = 0.0F;
                this.ThrushWingR1.rotateAngleZ = 0.0873F;
                this.ThrushWingR1.rotationPointY = 0.0F;
                ++this.ThrushLegL1.rotationPointY;
                ++this.ThrushLegR1.rotationPointY;
                ++this.ThrushLegL1.rotateAngleX;
                ++this.ThrushLegR1.rotateAngleX;
                break;
            case PARTY:
                this.ThrushLegL1.rotateAngleZ = -0.34906584F;
                this.ThrushLegR1.rotateAngleZ = 0.34906584F;
            case STANDING:
            case ON_SHOULDER:
            default:
                this.ThrushLegL1.rotateAngleZ = 0.0F;
                this.ThrushLegR1.rotateAngleZ = 0.0F;
            case FLYING:
                this.ThrushLegL1.rotateAngleX += ((float)Math.PI * 2F / 9F);
                this.ThrushLegR1.rotateAngleX += ((float)Math.PI * 2F / 9F);
        }

    }

    private static ModelTTMThrush.State getThrushState(EntityTTMThrush p_217158_0_) {
        if (p_217158_0_.isPartying()) {
            return ModelTTMThrush.State.PARTY;
        } else if (p_217158_0_.isEntitySleeping()) {
            return ModelTTMThrush.State.SITTING;
        } else {
            return p_217158_0_.isFlying() ? ModelTTMThrush.State.FLYING : ModelTTMThrush.State.STANDING;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @OnlyIn(Dist.CLIENT)
    public static enum State {
        FLYING,
        STANDING,
        SITTING,
        PARTY,
        ON_SHOULDER;
    }
}