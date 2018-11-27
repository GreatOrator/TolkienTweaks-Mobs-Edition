package com.greatorator.tolkienmobs.client.render.model.monster;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import com.greatorator.tolkienmobs.entity.monster.EntityGoblin;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Goblin - GreatOrator
 */
public class ModelGoblin extends ModelTolkienMobs {
    public ModelRenderer GoblinLegL;
    public ModelRenderer GoblinLegR;
    public ModelRenderer GoblinBody;
    public ModelRenderer GoblinHead;
    public ModelRenderer GoblinArmR;
    public ModelRenderer GoblinHandR;
    public ModelRenderer GoblinArmL;
    public ModelRenderer GoblinHandL;
    public ModelRenderer GoblinMouth;
    public ModelRenderer GoblinToothL;
    public ModelRenderer GoblinToothR;
    public ModelRenderer GoblinEarR1;
    public ModelRenderer GoblinEarL1;
    public ModelRenderer GoblinHair1;
    public ModelRenderer GoblinHair2;
    public ModelRenderer GoblinEarR2;
    public ModelRenderer GoblinEarR3;
    public ModelRenderer GoblinEarL2;
    public ModelRenderer GoblinEarL3;

    public ModelGoblin() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.GoblinToothL = new ModelRenderer(this, 0, 0);
        this.GoblinToothL.setRotationPoint(-2.5F, 6.0F, -2.2F);
        this.GoblinToothL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.GoblinBody = new ModelRenderer(this, 24, 22);
        this.GoblinBody.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.GoblinBody.addBox(-2.5F, 0.0F, -2.0F, 5, 6, 4, 0.0F);
        this.GoblinEarL1 = new ModelRenderer(this, 34, 4);
        this.GoblinEarL1.setRotationPoint(-4.0F, 1.0F, -0.5F);
        this.GoblinEarL1.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.GoblinArmR = new ModelRenderer(this, 0, 25);
        this.GoblinArmR.setRotationPoint(-0.5F, 1.0F, -0.5F);
        this.GoblinArmR.addBox(-1.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
        this.GoblinEarR3 = new ModelRenderer(this, 26, 4);
        this.GoblinEarR3.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.GoblinEarR3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.GoblinLegR = new ModelRenderer(this, 54, 20);
        this.GoblinLegR.setRotationPoint(1.4F, 22.0F, -0.2F);
        this.GoblinLegR.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 3, 0.0F);
        this.GoblinHair2 = new ModelRenderer(this, 50, 6);
        this.GoblinHair2.setRotationPoint(0.0F, -0.2F, 3.0F);
        this.GoblinHair2.addBox(-1.0F, -0.5F, -0.5F, 2, 7, 1, 0.0F);
        this.GoblinMouth = new ModelRenderer(this, 0, 11);
        this.GoblinMouth.setRotationPoint(0.0F, 6.0F, 0.5F);
        this.GoblinMouth.addBox(-3.0F, 0.0F, -2.0F, 6, 1, 4, 0.0F);
        this.GoblinHead = new ModelRenderer(this, 0, 0);
        this.GoblinHead.setRotationPoint(0.0F, 9.0F, -0.5F);
        this.GoblinHead.addBox(-4.0F, 0.0F, -2.5F, 8, 6, 5, 0.0F);
        this.GoblinHandL = new ModelRenderer(this, 12, 19);
        this.GoblinHandL.setRotationPoint(-1.5F, 2.0F, -0.5F);
        this.GoblinHandL.addBox(-1.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
        this.GoblinEarR2 = new ModelRenderer(this, 26, 2);
        this.GoblinEarR2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.GoblinEarR2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.GoblinEarR1 = new ModelRenderer(this, 26, 0);
        this.GoblinEarR1.setRotationPoint(4.0F, 1.0F, -0.5F);
        this.GoblinEarR1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.GoblinEarR1.mirror = true;
        this.GoblinEarL3 = new ModelRenderer(this, 38, 0);
        this.GoblinEarL3.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.GoblinEarL3.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 29);
        this.bipedLeftArm.setRotationPoint(3.5F, 16.5F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.GoblinToothR = new ModelRenderer(this, 0, 2);
        this.GoblinToothR.setRotationPoint(1.5F, 6.0F, -2.2F);
        this.GoblinToothR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.GoblinLegL = new ModelRenderer(this, 54, 26);
        this.GoblinLegL.setRotationPoint(-1.4F, 22.0F, -0.2F);
        this.GoblinLegL.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 3, 0.0F);
        this.GoblinHair1 = new ModelRenderer(this, 46, 0);
        this.GoblinHair1.setRotationPoint(0.0F, -0.2F, -1.5F);
        this.GoblinHair1.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 5, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 8, 29);
        this.bipedRightArm.setRotationPoint(-3.5F, 16.5F, 0.0F);
        this.bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.GoblinEarL2 = new ModelRenderer(this, 36, 2);
        this.GoblinEarL2.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.GoblinEarL2.addBox(-4.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.GoblinArmL = new ModelRenderer(this, 12, 25);
        this.GoblinArmL.setRotationPoint(-0.5F, 1.0F, -0.5F);
        this.GoblinArmL.addBox(-1.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
        this.GoblinHandR = new ModelRenderer(this, 0, 19);
        this.GoblinHandR.setRotationPoint(0.5F, 2.0F, -0.5F);
        this.GoblinHandR.addBox(-1.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
        this.GoblinHead.addChild(this.GoblinToothL);
        this.GoblinHead.addChild(this.GoblinEarL1);
        this.bipedLeftArm.addChild(this.GoblinArmR);
        this.GoblinEarR1.addChild(this.GoblinEarR3);
        this.GoblinHead.addChild(this.GoblinHair2);
        this.GoblinHead.addChild(this.GoblinMouth);
        this.bipedRightArm.addChild(this.GoblinHandL);
        this.GoblinEarR1.addChild(this.GoblinEarR2);
        this.GoblinHead.addChild(this.GoblinEarR1);
        this.GoblinEarL1.addChild(this.GoblinEarL3);
        this.GoblinHead.addChild(this.GoblinToothR);
        this.GoblinHead.addChild(this.GoblinHair1);
        this.GoblinEarL1.addChild(this.GoblinEarL2);
        this.bipedRightArm.addChild(this.GoblinArmL);
        this.bipedLeftArm.addChild(this.GoblinHandR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.GoblinBody.render(f5);
        this.GoblinLegR.render(f5);
        this.GoblinHead.render(f5);
        this.bipedLeftArm.render(f5);
        this.GoblinLegL.render(f5);
        this.bipedRightArm.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.rightArmPose = ModelBiped.ArmPose.EMPTY;
        this.leftArmPose = ModelBiped.ArmPose.EMPTY;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);

        if (itemstack.getItem() == Items.BOW && ((EntityGoblin) entitylivingbaseIn).isSwingingArms()) {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
                this.rightArmPose = ModelBiped.ArmPose.ITEM;
            }
            else {
                this.leftArmPose = ModelBiped.ArmPose.ITEM;
            }
        }

        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.GoblinLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.GoblinLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.GoblinHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.GoblinHead.rotateAngleX = headPitch * 0.017453292F;

        this.bipedRightArm.rotationPointX = -3.5F;
        this.bipedLeftArm.rotationPointX = 3.5F;

        // flick ears
        GoblinEarR1.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(15);	        this.bipedLeftArm.rotationPointX = 3.5F;
        GoblinEarL1.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(-15);
    }

    public void postRenderArm(float scale, EnumHandSide side) {
        float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(side);
        modelrenderer.rotationPointX += f;
        modelrenderer.postRender(scale);
        modelrenderer.rotationPointX -= f;
    }
}