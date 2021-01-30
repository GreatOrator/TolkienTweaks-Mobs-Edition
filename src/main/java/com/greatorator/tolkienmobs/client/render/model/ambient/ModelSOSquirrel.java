//package com.greatorator.tolkienmobs.client.render.model.ambient;
//
//import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.MathHelper;
//
///**
// * SOSquirrel - Boyd151995 & GreatOrator
// */
//public class ModelSOSquirrel extends ModelTTM {
//    public ModelRenderer body;
//    public ModelRenderer BackLegR;
//    public ModelRenderer BackLegL;
//    public ModelRenderer FrontLegR;
//    public ModelRenderer FrontLegL;
//    public ModelRenderer Neck;
//    public ModelRenderer bodyback;
//    public ModelRenderer bodyfront;
//    public ModelRenderer tail;
//    public ModelRenderer bodyback2;
//    public ModelRenderer tail2;
//    public ModelRenderer tail3;
//    public ModelRenderer tail4;
//    public ModelRenderer tail5;
//    public ModelRenderer bodyback3;
//    public ModelRenderer BackFootR;
//    public ModelRenderer BackFootL;
//    public ModelRenderer FrontFootR;
//    public ModelRenderer FrontFootL;
//    public ModelRenderer headmain;
//    public ModelRenderer headmain2;
//    public ModelRenderer headmain3;
//    public ModelRenderer headmain4;
//    public ModelRenderer eyeR;
//    public ModelRenderer eyeL;
//    public ModelRenderer earR;
//    public ModelRenderer snout;
//    public ModelRenderer snout2;
//    public ModelRenderer snout1;
//    public ModelRenderer snout3;
//    public ModelRenderer earL;
//    public ModelRenderer earpartR1;
//    public ModelRenderer earpartR2;
//    public ModelRenderer earpartR3;
//    public ModelRenderer snout4;
//    public ModelRenderer nose;
//    public ModelRenderer earpartL1;
//    public ModelRenderer earpartL2;
//    public ModelRenderer earpartL3;
//
//    public ModelSOSquirrel() {
//        this.textureWidth = 128;
//        this.textureHeight = 128;
//        this.body = new ModelRenderer(this, 80, 82);
//        this.body.setRotationPoint(-5.0F, -10.0F, 0.0F);
//        this.body.addBox(0.0F, 0.0F, 0.0F, 10, 14, 14, 0.0F);
//        this.setRotateAngle(body, -0.9599310885968813F, 0.0F, 0.0F);
//        this.nose = new ModelRenderer(this, 0, 121);
//        this.nose.setRotationPoint(1.0F, -0.4F, -0.2F);
//        this.nose.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
//        this.tail3 = new ModelRenderer(this, 78, 0);
//        this.tail3.setRotationPoint(1.0F, 16.0F, 0.5F);
//        this.tail3.addBox(0.0F, 0.0F, 0.0F, 8, 13, 10, 0.0F);
//        this.setRotateAngle(tail3, 0.5235987755982988F, 0.0F, 0.0F);
//        this.BackFootL = new ModelRenderer(this, 0, 103);
//        this.BackFootL.setRotationPoint(0.5F, 3.0F, 7.0F);
//        this.BackFootL.addBox(0.0F, 0.0F, 0.0F, 4, 5, 3, 0.0F);
//        this.earpartL1 = new ModelRenderer(this, 91, 121);
//        this.earpartL1.setRotationPoint(-0.7F, 0.3F, -0.9F);
//        this.earpartL1.addBox(0.0F, -4.0F, 0.0F, 1, 4, 2, 0.0F);
//        this.setRotateAngle(earpartL1, 0.0F, 0.0F, 0.03490658503988659F);
//        this.bodyfront = new ModelRenderer(this, 84, 86);
//        this.bodyfront.setRotationPoint(-1.0F, 4.5F, -8.5F);
//        this.bodyfront.addBox(0.0F, 0.0F, 0.0F, 12, 14, 10, 0.0F);
//        this.setRotateAngle(bodyfront, 0.5235987755982988F, 0.0F, 0.0F);
//        this.bodyback2 = new ModelRenderer(this, 80, 82);
//        this.bodyback2.setRotationPoint(0.0F, 0.4F, 9.6F);
//        this.bodyback2.addBox(0.0F, 0.0F, 0.0F, 16, 15, 7, 0.0F);
//        this.setRotateAngle(bodyback2, -0.7075564787585011F, 0.0F, 0.0F);
//        this.Neck = new ModelRenderer(this, 48, 110);
//        this.Neck.setRotationPoint(0.0F, -6.0F, -8.0F);
//        this.Neck.addBox(-4.0F, -4.0F, -10.0F, 8, 8, 10, 0.0F);
//        this.setRotateAngle(Neck, -0.7853981633974483F, 0.0F, 0.0F);
//        this.snout = new ModelRenderer(this, 15, 102);
//        this.snout.setRotationPoint(3.0F, 5.0F, -14.0F);
//        this.snout.addBox(0.0F, 0.0F, 0.0F, 4, 3, 6, 0.0F);
//        this.eyeR = new ModelRenderer(this, 0, 115);
//        this.eyeR.setRotationPoint(-0.5F, 4.0F, -9.5F);
//        this.eyeR.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
//        this.headmain = new ModelRenderer(this, 49, 108);
//        this.headmain.setRotationPoint(-5.0F, -4.5F, -11.5F);
//        this.headmain.addBox(0.0F, 0.0F, -9.0F, 10, 10, 10, 0.0F);
//        this.setRotateAngle(headmain, 0.8726646259971648F, 0.0F, 0.0F);
//        this.earpartR2 = new ModelRenderer(this, 91, 121);
//        this.earpartR2.setRotationPoint(0.0F, -0.5F, -1.2F);
//        this.earpartR2.addBox(0.0F, -5.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(earpartR2, 0.0F, 0.0F, -0.15707963267948966F);
//        this.snout2 = new ModelRenderer(this, 15, 102);
//        this.snout2.setRotationPoint(3.1F, 5.0F, -13.0F);
//        this.snout2.addBox(0.0F, 0.0F, 0.0F, 4, 3, 6, 0.0F);
//        this.setRotateAngle(snout2, 0.0F, 0.2181661564992912F, 0.0F);
//        this.tail4 = new ModelRenderer(this, 78, 0);
//        this.tail4.setRotationPoint(0.5F, 7.0F, 2.5F);
//        this.tail4.addBox(0.0F, 0.0F, 0.0F, 7, 9, 9, 0.0F);
//        this.setRotateAngle(tail4, -0.593411945678072F, 0.0F, 0.0F);
//        this.snout4 = new ModelRenderer(this, 35, 101);
//        this.snout4.setRotationPoint(0.5F, 0.0F, 0.0F);
//        this.snout4.addBox(0.0F, 0.0F, 0.0F, 3, 3, 6, 0.0F);
//        this.setRotateAngle(snout4, 0.42446407408502096F, 0.0F, 0.0F);
//        this.headmain4 = new ModelRenderer(this, 51, 108);
//        this.headmain4.setRotationPoint(1.0F, -1.0F, -1.9F);
//        this.headmain4.addBox(0.0F, 0.0F, -6.0F, 8, 12, 8, 0.0F);
//        this.earpartR1 = new ModelRenderer(this, 91, 121);
//        this.earpartR1.setRotationPoint(-0.3F, 0.3F, -1.2F);
//        this.earpartR1.addBox(0.0F, -4.0F, 0.0F, 1, 4, 2, 0.0F);
//        this.setRotateAngle(earpartR1, 0.0F, 0.0F, -0.03490658503988659F);
//        this.snout1 = new ModelRenderer(this, 15, 102);
//        this.snout1.setRotationPoint(3.0F, 5.0F, -14.0F);
//        this.snout1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 6, 0.0F);
//        this.setRotateAngle(snout1, 0.0F, -0.2181661564992912F, 0.0F);
//        this.bodyback3 = new ModelRenderer(this, 80, 82);
//        this.bodyback3.setRotationPoint(0.0F, 0.0F, 6.6F);
//        this.bodyback3.addBox(0.0F, 0.0F, 0.0F, 16, 15, 6, 0.0F);
//        this.setRotateAngle(bodyback3, -0.1980948701013564F, 0.0F, 0.0F);
//        this.bodyback = new ModelRenderer(this, 80, 82);
//        this.bodyback.setRotationPoint(-3.0F, -0.2F, 13.0F);
//        this.bodyback.addBox(0.0F, 0.0F, 0.0F, 16, 12, 10, 0.0F);
//        this.setRotateAngle(bodyback, -0.13962634015954636F, 0.0F, 0.0F);
//        this.tail5 = new ModelRenderer(this, 78, 0);
//        this.tail5.setRotationPoint(0.5F, 4.0F, 1.0F);
//        this.tail5.addBox(0.0F, 0.0F, 0.0F, 6, 9, 9, 0.0F);
//        this.setRotateAngle(tail5, -0.593411945678072F, 0.0F, 0.0F);
//        this.headmain3 = new ModelRenderer(this, 54, 116);
//        this.headmain3.setRotationPoint(1.0F, 1.5F, -4.5F);
//        this.headmain3.addBox(0.0F, 0.0F, -6.0F, 8, 6, 6, 0.0F);
//        this.tail2 = new ModelRenderer(this, 78, 0);
//        this.tail2.setRotationPoint(1.0F, 16.0F, -6.5F);
//        this.tail2.addBox(0.0F, 0.0F, 0.0F, 10, 16, 11, 0.0F);
//        this.setRotateAngle(tail2, 0.9599310885968813F, 0.0F, 0.0F);
//        this.FrontLegL = new ModelRenderer(this, 28, 110);
//        this.FrontLegL.mirror = true;
//        this.FrontLegL.setRotationPoint(3.0F, -4.0F, -7.0F);
//        this.FrontLegL.addBox(0.0F, -1.0F, -3.0F, 4, 12, 6, 0.0F);
//        this.setRotateAngle(FrontLegL, -0.5235987755982988F, 0.0F, 0.0F);
//        this.headmain2 = new ModelRenderer(this, 48, 114);
//        this.headmain2.setRotationPoint(-1.0F, 1.0F, -1.2F);
//        this.headmain2.addBox(0.0F, 0.0F, -6.0F, 12, 8, 6, 0.0F);
//        this.earL = new ModelRenderer(this, 91, 121);
//        this.earL.mirror = true;
//        this.earL.setRotationPoint(9.5F, 0.9F, -4.0F);
//        this.earL.addBox(-0.5F, -4.0F, -1.5F, 1, 4, 3, 0.0F);
//        this.setRotateAngle(earL, 0.0F, 0.0F, 0.2617993877991494F);
//        this.BackLegR = new ModelRenderer(this, 0, 113);
//        this.BackLegR.setRotationPoint(-7.0F, 14.0F, 3.0F);
//        this.BackLegR.addBox(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
//        this.setRotateAngle(BackLegR, -1.5707963267948966F, 0.0F, 0.0F);
//        this.earR = new ModelRenderer(this, 91, 121);
//        this.earR.setRotationPoint(0.3F, 0.9F, -4.0F);
//        this.earR.addBox(-0.5F, -4.0F, -1.5F, 1, 4, 3, 0.0F);
//        this.setRotateAngle(earR, 0.0F, 0.0F, -0.2617993877991494F);
//        this.earpartL2 = new ModelRenderer(this, 91, 121);
//        this.earpartL2.setRotationPoint(-1.0F, -0.6F, -0.9F);
//        this.earpartL2.addBox(0.0F, -5.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(earpartL2, 0.0F, 0.0F, 0.15707963267948966F);
//        this.BackLegL = new ModelRenderer(this, 0, 113);
//        this.BackLegL.mirror = true;
//        this.BackLegL.setRotationPoint(2.0F, 14.0F, 3.0F);
//        this.BackLegL.addBox(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
//        this.setRotateAngle(BackLegL, -1.5707963267948966F, 0.0F, 0.0F);
//        this.earpartL3 = new ModelRenderer(this, 91, 121);
//        this.earpartL3.setRotationPoint(-0.5F, -0.6F, 0.5F);
//        this.earpartL3.addBox(0.0F, -5.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(earpartL3, 0.0F, 0.0F, 0.13439035240356337F);
//        this.FrontFootL = new ModelRenderer(this, 31, 115);
//        this.FrontFootL.setRotationPoint(2.0F, 9.0F, 2.0F);
//        this.FrontFootL.addBox(-1.5F, 0.0F, -2.0F, 3, 9, 4, 0.0F);
//        this.setRotateAngle(FrontFootL, -0.792554013330625F, 0.0F, 0.0F);
//        this.tail = new ModelRenderer(this, 78, 0);
//        this.tail.setRotationPoint(2.0F, 7.0F, 7.0F);
//        this.tail.addBox(0.0F, 0.0F, -7.0F, 12, 16, 13, 0.0F);
//        this.setRotateAngle(tail, -2.844886680750757F, 0.0F, 0.0F);
//        this.eyeL = new ModelRenderer(this, 0, 115);
//        this.eyeL.mirror = true;
//        this.eyeL.setRotationPoint(8.5F, 4.0F, -9.5F);
//        this.eyeL.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
//        this.FrontFootR = new ModelRenderer(this, 31, 115);
//        this.FrontFootR.setRotationPoint(-2.0F, 9.0F, 2.0F);
//        this.FrontFootR.addBox(-1.5F, 0.0F, -2.0F, 3, 9, 4, 0.0F);
//        this.setRotateAngle(FrontFootR, -0.7853981633974483F, 0.0F, 0.0F);
//        this.earpartR3 = new ModelRenderer(this, 91, 121);
//        this.earpartR3.setRotationPoint(0.5F, -0.6F, 0.5F);
//        this.earpartR3.addBox(0.0F, -5.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(earpartR3, 0.0F, 0.0F, -0.13439035240356337F);
//        this.BackFootR = new ModelRenderer(this, 0, 103);
//        this.BackFootR.setRotationPoint(0.5F, 3.0F, 7.0F);
//        this.BackFootR.addBox(0.0F, 0.0F, 0.0F, 4, 5, 3, 0.0F);
//        this.snout3 = new ModelRenderer(this, 35, 101);
//        this.snout3.setRotationPoint(3.5F, 8.0F, -7.0F);
//        this.snout3.addBox(0.0F, 0.0F, -6.0F, 3, 2, 6, 0.0F);
//        this.setRotateAngle(snout3, -0.3113667385557884F, 0.0F, 0.0F);
//        this.FrontLegR = new ModelRenderer(this, 28, 110);
//        this.FrontLegR.setRotationPoint(-3.0F, -4.0F, -7.0F);
//        this.FrontLegR.addBox(-4.0F, -1.0F, -3.0F, 4, 12, 6, 0.0F);
//        this.setRotateAngle(FrontLegR, -0.5235987755982988F, 0.0F, 0.0F);
//        this.snout.addChild(this.nose);
//        this.tail2.addChild(this.tail3);
//        this.BackLegL.addChild(this.BackFootL);
//        this.earL.addChild(this.earpartL1);
//        this.body.addChild(this.bodyfront);
//        this.bodyback.addChild(this.bodyback2);
//        this.headmain.addChild(this.snout);
//        this.headmain.addChild(this.eyeR);
//        this.Neck.addChild(this.headmain);
//        this.earR.addChild(this.earpartR2);
//        this.headmain.addChild(this.snout2);
//        this.tail3.addChild(this.tail4);
//        this.snout.addChild(this.snout4);
//        this.headmain.addChild(this.headmain4);
//        this.earR.addChild(this.earpartR1);
//        this.headmain.addChild(this.snout1);
//        this.bodyback2.addChild(this.bodyback3);
//        this.body.addChild(this.bodyback);
//        this.tail4.addChild(this.tail5);
//        this.headmain.addChild(this.headmain3);
//        this.tail.addChild(this.tail2);
//        this.headmain.addChild(this.headmain2);
//        this.headmain.addChild(this.earL);
//        this.headmain.addChild(this.earR);
//        this.earL.addChild(this.earpartL2);
//        this.earpartL2.addChild(this.earpartL3);
//        this.FrontLegL.addChild(this.FrontFootL);
//        this.bodyback.addChild(this.tail);
//        this.headmain.addChild(this.eyeL);
//        this.FrontLegR.addChild(this.FrontFootR);
//        this.earpartR2.addChild(this.earpartR3);
//        this.BackLegR.addChild(this.BackFootR);
//        this.headmain.addChild(this.snout3);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.body.render(f5);
//        this.Neck.render(f5);
//        this.FrontLegL.render(f5);
//        this.BackLegR.render(f5);
//        this.BackLegL.render(f5);
//        this.FrontLegR.render(f5);
//    }
//
//    @Override
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
//        float baseLegRotation = -1.5707963267948966F; // needs to be the original value of arm.rotateAngleX
//        float baseNeckRotation = -0.7853981633974483F; // needs to be the original value of leg.rotateAngleX
//        float baseTailRotation = -2.844886680750757F; // needs to be the original value of tail.rotateAngleX
//
//        this.FrontLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.FrontLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//
//        this.BackLegR.rotateAngleX = baseLegRotation + (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
//        this.BackLegL.rotateAngleX = baseLegRotation + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount);
//
//        this.Neck.rotateAngleY = baseNeckRotation + (netHeadYaw * 0.017453292F);
//        this.Neck.rotateAngleX = baseNeckRotation + (headPitch * 0.017453292F);
//
//        if (limbSwingAmount > 0.2) {
//            float flick = Math.min(limbSwingAmount, 0.6F);
//            this.tail.rotateAngleX = baseTailRotation + ((MathHelper.cos(ageInTicks * 0.6662F) * 1.0F - (float) Math.PI / 3) * flick);
//        } else {
//            this.tail.rotateAngleX = baseTailRotation + (0.2F + MathHelper.cos(ageInTicks * 0.3335F) * 0.15F);
//        }
//    }
//}
