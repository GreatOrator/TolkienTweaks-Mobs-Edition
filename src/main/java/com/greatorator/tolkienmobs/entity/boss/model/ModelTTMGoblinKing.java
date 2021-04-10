package com.greatorator.tolkienmobs.entity.boss.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

import static com.greatorator.tolkienmobs.utils.TTMRand.degToRad;

/** Goblin King - GreatOrator */
public class ModelTTMGoblinKing<T extends MonsterEntity> extends BipedModel<T> {
    public ModelRenderer GoblinLegL;
    public ModelRenderer GoblinLegR;
    public ModelRenderer GoblinBody;
    public ModelRenderer GoblinHead;
    public ModelRenderer GoblinArmR;
    public ModelRenderer GoblinRightArm;
    public ModelRenderer GoblinHandR;
    public ModelRenderer GoblinArmL;
    public ModelRenderer GoblinLeftArm;
    public ModelRenderer GoblinHandL;
    public ModelRenderer GoblinMouth;
    public ModelRenderer GoblinToothL;
    public ModelRenderer GoblinToothR;
    public ModelRenderer GoblinEarR1;
    public ModelRenderer GoblinEarL1;
    public ModelRenderer GoblinHair1;
    public ModelRenderer GoblinHair2;
    public ModelRenderer Crown;
    public ModelRenderer GoblinEarR2;
    public ModelRenderer GoblinEarR3;
    public ModelRenderer GoblinEarL2;
    public ModelRenderer GoblinEarL3;
    public ModelRenderer Crown_1;
    public ModelRenderer Crown_2;
    public ModelRenderer Crown_3;
    public ModelRenderer Crown_4;
    public ModelRenderer Crown_5;
    public ModelRenderer Crown_6;
    public ModelRenderer Crown_7;
    public ModelRenderer Crown_8;
    public ModelRenderer Crown_9;
    public ModelRenderer Crown_10;

    public ModelTTMGoblinKing(float modelSize, boolean p_i46303_2_) {
        super(modelSize);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.GoblinLeftArm = new ModelRenderer(this, 0, 29);
        this.GoblinLeftArm.mirror = true;
        this.GoblinLeftArm.setRotationPoint(-4.0F, 14.5F, 0.0F);
        this.GoblinLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.GoblinArmR = new ModelRenderer(this, 0, 25);
        this.GoblinArmR.setRotationPoint(-0.5F, 1.0F, -0.5F);
        this.GoblinArmR.addBox(-1.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
        this.GoblinLegL = new ModelRenderer(this, 54, 25);
        this.GoblinLegL.setRotationPoint(-1.4F, 20.0F, -0.2F);
        this.GoblinLegL.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 3, 0.0F);
        this.GoblinEarR3 = new ModelRenderer(this, 27, 4);
        this.GoblinEarR3.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.GoblinEarR3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.GoblinArmL = new ModelRenderer(this, 0, 25);
        this.GoblinArmL.mirror = true;
        this.GoblinArmL.setRotationPoint(-0.5F, 1.0F, -0.5F);
        this.GoblinArmL.addBox(-1.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
        this.Crown_3 = new ModelRenderer(this, 32, 19);
        this.Crown_3.setRotationPoint(0.1F, -0.2F, 0.6F);
        this.Crown_3.addBox(-5.0F, 0.0F, 2.3F, 10, 1, 1, 0.0F);
        this.GoblinEarL3 = new ModelRenderer(this, 27, 4);
        this.GoblinEarL3.mirror = true;
        this.GoblinEarL3.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.GoblinEarL3.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.Crown_1 = new ModelRenderer(this, 32, 12);
        this.Crown_1.setRotationPoint(-0.2F, -0.2F, 0.0F);
        this.Crown_1.addBox(-5.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F);
        this.GoblinHandL = new ModelRenderer(this, 0, 19);
        this.GoblinHandL.mirror = true;
        this.GoblinHandL.setRotationPoint(-1.5F, 2.0F, -0.5F);
        this.GoblinHandL.addBox(-1.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
        this.Crown_6 = new ModelRenderer(this, 34, 19);
        this.Crown_6.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.Crown_6.addBox(-5.2F, -0.7F, 3.0F, 1, 1, 1, 0.0F);
        this.GoblinLegR = new ModelRenderer(this, 54, 25);
        this.GoblinLegR.setRotationPoint(1.4F, 20.0F, -0.2F);
        this.GoblinLegR.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 3, 0.0F);
        this.Crown_2 = new ModelRenderer(this, 32, 19);
        this.Crown_2.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.Crown_2.addBox(-5.0F, 0.0F, -4.0F, 10, 1, 1, 0.0F);
        this.GoblinRightArm = new ModelRenderer(this, 0, 29);
        this.GoblinRightArm.setRotationPoint(4.0F, 14.5F, 0.0F);
        this.GoblinRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.GoblinEarL2 = new ModelRenderer(this, 27, 2);
        this.GoblinEarL2.mirror = true;
        this.GoblinEarL2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.GoblinEarL2.addBox(-4.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.GoblinHandR = new ModelRenderer(this, 0, 19);
        this.GoblinHandR.setRotationPoint(0.5F, 2.0F, -0.5F);
        this.GoblinHandR.addBox(-1.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
        this.Crown_10 = new ModelRenderer(this, 34, 19);
        this.Crown_10.setRotationPoint(7.0F, 0.9F, 0.0F);
        this.Crown_10.addBox(-4.0F, 6.0F, -4.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Crown_10, 0.0F, 0.0F, 0.7853981633974483F);
        this.GoblinHair1 = new ModelRenderer(this, 46, 0);
        this.GoblinHair1.setRotationPoint(0.0F, -0.2F, -1.5F);
        this.GoblinHair1.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 5, 0.0F);
        this.GoblinEarR2 = new ModelRenderer(this, 27, 2);
        this.GoblinEarR2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.GoblinEarR2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.GoblinEarR1 = new ModelRenderer(this, 27, 0);
        this.GoblinEarR1.setRotationPoint(4.0F, 1.0F, -0.5F);
        this.GoblinEarR1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.setRotateAngle(GoblinEarR1, 0.0F, 0.0F, -0.3490658503988659F);
        this.GoblinEarL1 = new ModelRenderer(this, 27, 0);
        this.GoblinEarL1.mirror = true;
        this.GoblinEarL1.setRotationPoint(-4.0F, 1.0F, -0.5F);
        this.GoblinEarL1.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.setRotateAngle(GoblinEarL1, 0.0F, 0.0F, 0.3490658503988659F);
        this.Crown_9 = new ModelRenderer(this, 39, 15);
        this.Crown_9.setRotationPoint(2.0F, 0.3F, 0.0F);
        this.Crown_9.addBox(0.0F, -0.7F, -4.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Crown_9, 0.0F, 0.0F, 0.7853981633974483F);
        this.GoblinHead = new ModelRenderer(this, 0, 0);
        this.GoblinHead.setRotationPoint(0.0F, 4.5F, -0.5F);
        this.GoblinHead.addBox(-5.0F, 0.0F, -3.5F, 10, 8, 7, 0.0F);
        this.GoblinToothR = new ModelRenderer(this, 0, 0);
        this.GoblinToothR.setRotationPoint(1.5F, 7.5F, -3.3F);
        this.GoblinToothR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.GoblinHair2 = new ModelRenderer(this, 50, 6);
        this.GoblinHair2.setRotationPoint(0.0F, -0.2F, 3.5F);
        this.GoblinHair2.addBox(-1.0F, -0.5F, -0.5F, 2, 7, 1, 0.0F);
        this.GoblinToothL = new ModelRenderer(this, 0, 0);
        this.GoblinToothL.setRotationPoint(-2.5F, 7.5F, -3.3F);
        this.GoblinToothL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Crown_7 = new ModelRenderer(this, 34, 19);
        this.Crown_7.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.Crown_7.addBox(4.2F, -0.7F, 2.9F, 1, 1, 1, 0.0F);
        this.Crown_8 = new ModelRenderer(this, 39, 15);
        this.Crown_8.setRotationPoint(-1.5F, -0.2F, 0.0F);
        this.Crown_8.addBox(-1.8F, -0.7F, -4.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Crown_8, 0.0F, 0.0F, -0.7853981633974483F);
        this.Crown = new ModelRenderer(this, 32, 12);
        this.Crown.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.Crown.addBox(4.2F, -0.2F, -4.1F, 1, 1, 8, 0.0F);
        this.GoblinBody = new ModelRenderer(this, 34, 21);
        this.GoblinBody.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.GoblinBody.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4, 0.0F);
        this.Crown_5 = new ModelRenderer(this, 34, 19);
        this.Crown_5.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.Crown_5.addBox(-5.2F, -0.7F, -4.0F, 1, 1, 1, 0.0F);
        this.GoblinMouth = new ModelRenderer(this, 9, 15);
        this.GoblinMouth.setRotationPoint(0.0F, 8.0F, -0.5F);
        this.GoblinMouth.addBox(-4.0F, 0.0F, -2.5F, 8, 1, 6, 0.0F);
        this.Crown_4 = new ModelRenderer(this, 34, 19);
        this.Crown_4.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.Crown_4.addBox(4.2F, -0.7F, -4.0F, 1, 1, 1, 0.0F);
        this.GoblinRightArm.addChild(this.GoblinArmR);
        this.GoblinEarR1.addChild(this.GoblinEarR3);
        this.GoblinLeftArm.addChild(this.GoblinArmL);
        this.Crown.addChild(this.Crown_3);
        this.GoblinEarL1.addChild(this.GoblinEarL3);
        this.Crown.addChild(this.Crown_1);
        this.GoblinLeftArm.addChild(this.GoblinHandL);
        this.Crown.addChild(this.Crown_6);
        this.Crown.addChild(this.Crown_2);
        this.GoblinEarL1.addChild(this.GoblinEarL2);
        this.GoblinRightArm.addChild(this.GoblinHandR);
        this.Crown.addChild(this.Crown_10);
        this.GoblinHead.addChild(this.GoblinHair1);
        this.GoblinEarR1.addChild(this.GoblinEarR2);
        this.GoblinHead.addChild(this.GoblinEarR1);
        this.GoblinHead.addChild(this.GoblinEarL1);
        this.Crown.addChild(this.Crown_9);
        this.GoblinHead.addChild(this.GoblinToothR);
        this.GoblinHead.addChild(this.GoblinHair2);
        this.GoblinHead.addChild(this.GoblinToothL);
        this.Crown.addChild(this.Crown_7);
        this.Crown.addChild(this.Crown_8);
        this.GoblinHead.addChild(this.Crown);
        this.Crown.addChild(this.Crown_5);
        this.GoblinHead.addChild(this.GoblinMouth);
        this.Crown.addChild(this.Crown_4);
    }

    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.GoblinHead);
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.GoblinBody, this.GoblinRightArm, this.GoblinLeftArm, this.GoblinLegR, this.GoblinLegL);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.rightArmPose = BipedModel.ArmPose.EMPTY;
        this.leftArmPose = BipedModel.ArmPose.EMPTY;
        ItemStack itemstack = entityIn.getHeldItem(Hand.MAIN_HAND);
        if (itemstack.getItem() instanceof net.minecraft.item.SwordItem || itemstack.getItem() instanceof net.minecraft.item.AxeItem && entityIn.isAggressive()) {
            if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
                this.rightArmPose = ArmPose.ITEM;
            } else {
                this.leftArmPose = ArmPose.ITEM;
            }
        }

        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        ItemStack itemstack = entityIn.getHeldItemMainhand();
        if (entityIn.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof net.minecraft.item.BowItem))) {
            float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);

            this.GoblinRightArm.rotateAngleZ = 0.0F;
            this.GoblinLeftArm.rotateAngleZ = 0.0F;
            this.GoblinRightArm.rotateAngleY = -(0.1F - f * 0.6F);
            this.GoblinLeftArm.rotateAngleY = 0.1F - f * 0.6F;
            this.GoblinRightArm.rotateAngleX = (-(float)Math.PI / 2F);
            this.GoblinLeftArm.rotateAngleX = (-(float)Math.PI / 2F);
            this.GoblinRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.GoblinLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;

            ModelHelper.func_239101_a_(this.GoblinRightArm, this.GoblinLeftArm, ageInTicks);
        }
        this.GoblinLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.GoblinRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.GoblinLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.GoblinLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.GoblinHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.GoblinHead.rotateAngleX = headPitch * 0.017453292F;

        this.GoblinRightArm.rotationPointX = 4.0F;
        this.GoblinLeftArm.rotationPointX = -4.0F;
        // flick ears
        GoblinEarR1.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(15);
        GoblinEarL1.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(-15);
    }

    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
        float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(sideIn);
        modelrenderer.rotationPointX += f;
        modelrenderer.translateRotate(matrixStackIn);
        modelrenderer.rotationPointX -= f;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}