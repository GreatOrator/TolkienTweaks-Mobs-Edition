//package com.greatorator.tolkienmobs.client.render.model.boss;
//
//import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMGoblin;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.EnumHandSide;
//import net.minecraft.util.math.MathHelper;
//
///** Goblin King - GreatOrator */
//public class ModelGoblinKing extends ModelTTM {
//    public ModelRenderer GoblinLegL;
//    public ModelRenderer GoblinLegR;
//    public ModelRenderer GoblinBody;
//    public ModelRenderer GoblinHead;
//    public ModelRenderer GoblinArmR;
//    public ModelRenderer GoblinHandR;
//    public ModelRenderer GoblinArmL;
//    public ModelRenderer GoblinHandL;
//    public ModelRenderer GoblinMouth;
//    public ModelRenderer GoblinToothL;
//    public ModelRenderer GoblinToothR;
//    public ModelRenderer GoblinEarR1;
//    public ModelRenderer GoblinEarL1;
//    public ModelRenderer GoblinHair1;
//    public ModelRenderer GoblinHair2;
//    public ModelRenderer Crown;
//    public ModelRenderer GoblinEarR2;
//    public ModelRenderer GoblinEarR3;
//    public ModelRenderer GoblinEarL2;
//    public ModelRenderer GoblinEarL3;
//    public ModelRenderer Crown_1;
//    public ModelRenderer Crown_2;
//    public ModelRenderer Crown_3;
//    public ModelRenderer Crown_4;
//    public ModelRenderer Crown_5;
//    public ModelRenderer Crown_6;
//    public ModelRenderer Crown_7;
//    public ModelRenderer Crown_8;
//    public ModelRenderer Crown_9;
//    public ModelRenderer Crown_10;
//
//    public ModelGoblinKing() {
//        this.textureWidth = 64;
//        this.textureHeight = 32;
//        this.bipedLeftArm = new ModelRenderer(this, 0, 29);
//        this.bipedLeftArm.mirror = true;
//        this.bipedLeftArm.setRotationPoint(-4.0F, 14.5F, 0.0F);
//        this.bipedLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
//        this.GoblinArmR = new ModelRenderer(this, 0, 25);
//        this.GoblinArmR.setRotationPoint(-0.5F, 1.0F, -0.5F);
//        this.GoblinArmR.addBox(-1.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
//        this.GoblinLegL = new ModelRenderer(this, 54, 25);
//        this.GoblinLegL.setRotationPoint(-1.4F, 20.0F, -0.2F);
//        this.GoblinLegL.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 3, 0.0F);
//        this.GoblinEarR3 = new ModelRenderer(this, 27, 4);
//        this.GoblinEarR3.setRotationPoint(0.0F, 2.0F, 0.0F);
//        this.GoblinEarR3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.GoblinArmL = new ModelRenderer(this, 0, 25);
//        this.GoblinArmL.mirror = true;
//        this.GoblinArmL.setRotationPoint(-0.5F, 1.0F, -0.5F);
//        this.GoblinArmL.addBox(-1.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
//        this.Crown_3 = new ModelRenderer(this, 32, 19);
//        this.Crown_3.setRotationPoint(0.1F, -0.2F, 0.6F);
//        this.Crown_3.addBox(-5.0F, 0.0F, 2.3F, 10, 1, 1, 0.0F);
//        this.GoblinEarL3 = new ModelRenderer(this, 27, 4);
//        this.GoblinEarL3.mirror = true;
//        this.GoblinEarL3.setRotationPoint(0.0F, 2.0F, 0.0F);
//        this.GoblinEarL3.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.Crown_1 = new ModelRenderer(this, 32, 12);
//        this.Crown_1.setRotationPoint(-0.2F, -0.2F, 0.0F);
//        this.Crown_1.addBox(-5.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F);
//        this.GoblinHandL = new ModelRenderer(this, 0, 19);
//        this.GoblinHandL.mirror = true;
//        this.GoblinHandL.setRotationPoint(-1.5F, 2.0F, -0.5F);
//        this.GoblinHandL.addBox(-1.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
//        this.Crown_6 = new ModelRenderer(this, 34, 19);
//        this.Crown_6.setRotationPoint(0.0F, -0.2F, 0.0F);
//        this.Crown_6.addBox(-5.2F, -0.7F, 3.0F, 1, 1, 1, 0.0F);
//        this.GoblinLegR = new ModelRenderer(this, 54, 25);
//        this.GoblinLegR.setRotationPoint(1.4F, 20.0F, -0.2F);
//        this.GoblinLegR.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 3, 0.0F);
//        this.Crown_2 = new ModelRenderer(this, 32, 19);
//        this.Crown_2.setRotationPoint(0.0F, -0.2F, 0.0F);
//        this.Crown_2.addBox(-5.0F, 0.0F, -4.0F, 10, 1, 1, 0.0F);
//        this.bipedRightArm = new ModelRenderer(this, 0, 29);
//        this.bipedRightArm.setRotationPoint(4.0F, 14.5F, 0.0F);
//        this.bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
//        this.GoblinEarL2 = new ModelRenderer(this, 27, 2);
//        this.GoblinEarL2.mirror = true;
//        this.GoblinEarL2.setRotationPoint(0.0F, 1.0F, 0.0F);
//        this.GoblinEarL2.addBox(-4.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
//        this.GoblinHandR = new ModelRenderer(this, 0, 19);
//        this.GoblinHandR.setRotationPoint(0.5F, 2.0F, -0.5F);
//        this.GoblinHandR.addBox(-1.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
//        this.Crown_10 = new ModelRenderer(this, 34, 19);
//        this.Crown_10.setRotationPoint(7.0F, 0.9F, 0.0F);
//        this.Crown_10.addBox(-4.0F, 6.0F, -4.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(Crown_10, 0.0F, 0.0F, 0.7853981633974483F);
//        this.GoblinHair1 = new ModelRenderer(this, 46, 0);
//        this.GoblinHair1.setRotationPoint(0.0F, -0.2F, -1.5F);
//        this.GoblinHair1.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 5, 0.0F);
//        this.GoblinEarR2 = new ModelRenderer(this, 27, 2);
//        this.GoblinEarR2.setRotationPoint(0.0F, 1.0F, 0.0F);
//        this.GoblinEarR2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
//        this.GoblinEarR1 = new ModelRenderer(this, 27, 0);
//        this.GoblinEarR1.setRotationPoint(4.0F, 1.0F, -0.5F);
//        this.GoblinEarR1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
//        this.setRotateAngle(GoblinEarR1, 0.0F, 0.0F, -0.3490658503988659F);
//        this.GoblinEarL1 = new ModelRenderer(this, 27, 0);
//        this.GoblinEarL1.mirror = true;
//        this.GoblinEarL1.setRotationPoint(-4.0F, 1.0F, -0.5F);
//        this.GoblinEarL1.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
//        this.setRotateAngle(GoblinEarL1, 0.0F, 0.0F, 0.3490658503988659F);
//        this.Crown_9 = new ModelRenderer(this, 39, 15);
//        this.Crown_9.setRotationPoint(2.0F, 0.3F, 0.0F);
//        this.Crown_9.addBox(0.0F, -0.7F, -4.0F, 1, 4, 1, 0.0F);
//        this.setRotateAngle(Crown_9, 0.0F, 0.0F, 0.7853981633974483F);
//        this.GoblinHead = new ModelRenderer(this, 0, 0);
//        this.GoblinHead.setRotationPoint(0.0F, 4.5F, -0.5F);
//        this.GoblinHead.addBox(-5.0F, 0.0F, -3.5F, 10, 8, 7, 0.0F);
//        this.GoblinToothR = new ModelRenderer(this, 0, 0);
//        this.GoblinToothR.setRotationPoint(1.5F, 7.5F, -3.3F);
//        this.GoblinToothR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.GoblinHair2 = new ModelRenderer(this, 50, 6);
//        this.GoblinHair2.setRotationPoint(0.0F, -0.2F, 3.5F);
//        this.GoblinHair2.addBox(-1.0F, -0.5F, -0.5F, 2, 7, 1, 0.0F);
//        this.GoblinToothL = new ModelRenderer(this, 0, 0);
//        this.GoblinToothL.setRotationPoint(-2.5F, 7.5F, -3.3F);
//        this.GoblinToothL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.Crown_7 = new ModelRenderer(this, 34, 19);
//        this.Crown_7.setRotationPoint(0.0F, -0.2F, 0.0F);
//        this.Crown_7.addBox(4.2F, -0.7F, 2.9F, 1, 1, 1, 0.0F);
//        this.Crown_8 = new ModelRenderer(this, 39, 15);
//        this.Crown_8.setRotationPoint(-1.5F, -0.2F, 0.0F);
//        this.Crown_8.addBox(-1.8F, -0.7F, -4.0F, 1, 4, 1, 0.0F);
//        this.setRotateAngle(Crown_8, 0.0F, 0.0F, -0.7853981633974483F);
//        this.Crown = new ModelRenderer(this, 32, 12);
//        this.Crown.setRotationPoint(0.0F, -0.2F, 0.0F);
//        this.Crown.addBox(4.2F, -0.2F, -4.1F, 1, 1, 8, 0.0F);
//        this.GoblinBody = new ModelRenderer(this, 34, 21);
//        this.GoblinBody.setRotationPoint(0.0F, 13.0F, 0.0F);
//        this.GoblinBody.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4, 0.0F);
//        this.Crown_5 = new ModelRenderer(this, 34, 19);
//        this.Crown_5.setRotationPoint(0.0F, -0.2F, 0.0F);
//        this.Crown_5.addBox(-5.2F, -0.7F, -4.0F, 1, 1, 1, 0.0F);
//        this.GoblinMouth = new ModelRenderer(this, 9, 15);
//        this.GoblinMouth.setRotationPoint(0.0F, 8.0F, -0.5F);
//        this.GoblinMouth.addBox(-4.0F, 0.0F, -2.5F, 8, 1, 6, 0.0F);
//        this.Crown_4 = new ModelRenderer(this, 34, 19);
//        this.Crown_4.setRotationPoint(0.0F, -0.2F, 0.0F);
//        this.Crown_4.addBox(4.2F, -0.7F, -4.0F, 1, 1, 1, 0.0F);
//        this.bipedRightArm.addChild(this.GoblinArmR);
//        this.GoblinEarR1.addChild(this.GoblinEarR3);
//        this.bipedLeftArm.addChild(this.GoblinArmL);
//        this.Crown.addChild(this.Crown_3);
//        this.GoblinEarL1.addChild(this.GoblinEarL3);
//        this.Crown.addChild(this.Crown_1);
//        this.bipedLeftArm.addChild(this.GoblinHandL);
//        this.Crown.addChild(this.Crown_6);
//        this.Crown.addChild(this.Crown_2);
//        this.GoblinEarL1.addChild(this.GoblinEarL2);
//        this.bipedRightArm.addChild(this.GoblinHandR);
//        this.Crown.addChild(this.Crown_10);
//        this.GoblinHead.addChild(this.GoblinHair1);
//        this.GoblinEarR1.addChild(this.GoblinEarR2);
//        this.GoblinHead.addChild(this.GoblinEarR1);
//        this.GoblinHead.addChild(this.GoblinEarL1);
//        this.Crown.addChild(this.Crown_9);
//        this.GoblinHead.addChild(this.GoblinToothR);
//        this.GoblinHead.addChild(this.GoblinHair2);
//        this.GoblinHead.addChild(this.GoblinToothL);
//        this.Crown.addChild(this.Crown_7);
//        this.Crown.addChild(this.Crown_8);
//        this.GoblinHead.addChild(this.Crown);
//        this.Crown.addChild(this.Crown_5);
//        this.GoblinHead.addChild(this.GoblinMouth);
//        this.Crown.addChild(this.Crown_4);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.GoblinBody.render(f5);
//        this.GoblinLegR.render(f5);
//        this.GoblinHead.render(f5);
//        this.bipedLeftArm.render(f5);
//        this.GoblinLegL.render(f5);
//        this.bipedRightArm.render(f5);
//    }
//
//    public void setLivingAnimations(LivingEntity LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
//        this.rightArmPose = ArmPose.EMPTY;
//        this.leftArmPose = ArmPose.EMPTY;
//        ItemStack itemstack = LivingEntityIn.getHeldItem(EnumHand.MAIN_HAND);
//
//        if (itemstack.getItem() == Items.BOW && ((EntityTMGoblin) LivingEntityIn).isSwingingArms()) {
//            if (LivingEntityIn.getPrimaryHand() == EnumHandSide.RIGHT) {
//                this.rightArmPose = ArmPose.ITEM;
//            }
//            else {
//                this.leftArmPose = ArmPose.ITEM;
//            }
//        }
//
//        super.setLivingAnimations(LivingEntityIn, limbSwing, limbSwingAmount, partialTickTime);
//    }
//
//    @Override
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
//        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
//
//        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//
//        this.GoblinLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.GoblinLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//
//        this.GoblinHead.rotateAngleY = netHeadYaw * 0.017453292F;
//        this.GoblinHead.rotateAngleX = headPitch * 0.017453292F;
//
//        this.bipedRightArm.rotationPointX = 4.0F;
//        this.bipedLeftArm.rotationPointX = -4.0F;
//
//        // flick ears
//        GoblinEarR1.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(15);
//        GoblinEarL1.rotateAngleY = (float) Math.pow(MathHelper.cos(degToRad(entityIn.ticksExisted*3)), 6) * degToRad(-15);
//    }
//}