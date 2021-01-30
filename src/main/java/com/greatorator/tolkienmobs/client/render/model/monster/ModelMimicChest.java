//package com.greatorator.tolkienmobs.client.render.model.monster;
//
//import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.MathHelper;
//
//public class ModelMimicChest extends ModelTTM {
//    public ModelRenderer box;
//    public ModelRenderer boxLid;
//    public ModelRenderer box2;
//    public ModelRenderer boxLid2;
//    public ModelRenderer leftLeg;
//    public ModelRenderer rightLeg;
//    public ModelRenderer leftbackLeg;
//    public ModelRenderer rightbackLeg;
//    public ModelRenderer MimicTeeth;
//    public ModelRenderer MimicGums;
//    public ModelRenderer MimicGums_1;
//    public ModelRenderer MimicGums_2;
//    public ModelRenderer MimicGums_3;
//    public ModelRenderer MimicThroat;
//    public ModelRenderer MimicTongue;
//    public ModelRenderer MimicTongue_1;
//    public ModelRenderer MimicTongue_2;
//    public ModelRenderer MimicTeeth_1;
//    public ModelRenderer MimicThroat_1;
//    public ModelRenderer MimicLatch;
//    public ModelRenderer MimicGums_4;
//    public ModelRenderer MimicGums_5;
//    public ModelRenderer MimicGums_6;
//    public ModelRenderer MimicGums_7;
//    public ModelRenderer MimicEye;
//    public ModelRenderer MimiEyePart;
//    public ModelRenderer MimicEyePart;
//    public ModelRenderer MimicEyePart_1;
//    public ModelRenderer MimiEyePart_1;
//    public ModelRenderer MimiEyePart_2;
//    public ModelRenderer MimiEyePart_3;
//    public ModelRenderer leftLeg_1;
//    public ModelRenderer rightLeg_1;
//    public ModelRenderer leftbackLeg_1;
//    public ModelRenderer rightbackLeg_1;
//    public static boolean renderChest;
//    private boolean mimicAttack;
//
//    public ModelMimicChest()
//    {
//        this.textureWidth = 128;
//        this.textureHeight = 64;
//        this.MimiEyePart_3 = new ModelRenderer(this, 48, 11);
//        this.MimiEyePart_3.setRotationPoint(0.0F, -0.1F, 0.0F);
//        this.MimiEyePart_3.addBox(-1.0F, -1.0F, -2.0F, 2, 1, 4, 0.0F);
//        this.rightLeg_1 = new ModelRenderer(this, 112, 20);
//        this.rightLeg_1.setRotationPoint(-0.3F, 3.5F, -0.3F);
//        this.rightLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(rightLeg_1, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
//        this.box = new ModelRenderer(this, 0, 0);
//        this.box.setRotationPoint(0.0F, 9.0F, 0.0F);
//        this.box.addBox(-8.0F, 0.0F, -8.0F, 16, 10, 16, 0.0F);
//        this.MimicTongue = new ModelRenderer(this, 0, 27);
//        this.MimicTongue.setRotationPoint(0.0F, 1.5F, 3.5F);
//        this.MimicTongue.addBox(-3.0F, -1.0F, -8.0F, 6, 1, 8, 0.0F);
//        this.setRotateAngle(MimicTongue, -0.3665191429188092F, 0.0F, 0.0F);
//        this.MimiEyePart_1 = new ModelRenderer(this, 50, 0);
//        this.MimiEyePart_1.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MimiEyePart_1.addBox(-4.0F, -1.0F, -1.0F, 8, 1, 2, 0.0F);
//        this.MimicGums_6 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_6.setRotationPoint(0.0F, 0.0F, 15.0F);
//        this.MimicGums_6.addBox(0.0F, 0.0F, 0.0F, 16, 6, 1, 0.0F);
//        this.rightLeg = new ModelRenderer(this, 112, 20);
//        this.rightLeg.setRotationPoint(-5.8F, 16.0F, -5.8F);
//        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(rightLeg, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
//        this.rightbackLeg_1 = new ModelRenderer(this, 112, 20);
//        this.rightbackLeg_1.setRotationPoint(-0.3F, 3.5F, -0.3F);
//        this.rightbackLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(rightbackLeg_1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
//        this.MimicEyePart_1 = new ModelRenderer(this, 50, 8);
//        this.MimicEyePart_1.setRotationPoint(0.0F, -0.7F, 0.0F);
//        this.MimicEyePart_1.addBox(-2.0F, -1.0F, -1.0F, 4, 1, 2, 0.0F);
//        this.MimicTeeth = new ModelRenderer(this, 68, 46);
//        this.MimicTeeth.setRotationPoint(0.5F, -2.5F, 0.5F);
//        this.MimicTeeth.addBox(-8.0F, 0.0F, -8.0F, 15, 3, 15, 0.0F);
//        this.MimiEyePart = new ModelRenderer(this, 61, 0);
//        this.MimiEyePart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MimiEyePart.addBox(-1.0F, -1.0F, -4.0F, 2, 1, 8, 0.0F);
//        this.MimicThroat = new ModelRenderer(this, 68, 0);
//        this.MimicThroat.setRotationPoint(0.5F, 0.5F, 0.5F);
//        this.MimicThroat.addBox(-8.0F, 0.0F, -8.0F, 15, 2, 15, 0.0F);
//        this.boxLid = new ModelRenderer(this, 0, 42);
//        this.boxLid.setRotationPoint(-8.0F, 9.0F, 8.0F);
//        this.boxLid.addBox(0.0F, 0.0F, 0.0F, 16, 6, 16, 0.0F);
//        this.setRotateAngle(boxLid, 3.141592653589793F, 0.0F, 0.0F);
//        this.MimicEye = new ModelRenderer(this, 50, 0);
//        this.MimicEye.setRotationPoint(8.0F, 1.0F, 8.0F);
//        this.MimicEye.addBox(-3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F);
//        this.rightbackLeg = new ModelRenderer(this, 112, 20);
//        this.rightbackLeg.setRotationPoint(-5.8F, 16.0F, 5.8F);
//        this.rightbackLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(rightbackLeg, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
//        this.MimicEyePart = new ModelRenderer(this, 50, 0);
//        this.MimicEyePart.setRotationPoint(0.0F, -0.5F, 0.0F);
//        this.MimicEyePart.addBox(-3.0F, -1.0F, -2.0F, 6, 1, 4, 0.0F);
//        this.leftLeg = new ModelRenderer(this, 112, 20);
//        this.leftLeg.setRotationPoint(5.8F, 16.0F, -5.8F);
//        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(leftLeg, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
//        this.MimicLatch = new ModelRenderer(this, 0, 0);
//        this.MimicLatch.setRotationPoint(6.9F, -2.0F, 15.5F);
//        this.MimicLatch.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
//        this.MimicGums_4 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_4.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MimicGums_4.addBox(0.0F, 0.0F, 0.0F, 16, 6, 1, 0.0F);
//        this.MimicThroat_1 = new ModelRenderer(this, 68, 0);
//        this.MimicThroat_1.setRotationPoint(0.5F, 0.5F, 0.5F);
//        this.MimicThroat_1.addBox(0.0F, 0.0F, 0.0F, 15, 2, 15, 0.0F);
//        this.MimicGums_3 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_3.setRotationPoint(15.0F, 0.0F, 0.0F);
//        this.MimicGums_3.addBox(-8.0F, 0.0F, -8.0F, 1, 6, 16, 0.0F);
//        this.leftbackLeg = new ModelRenderer(this, 112, 20);
//        this.leftbackLeg.setRotationPoint(5.8F, 16.0F, 5.8F);
//        this.leftbackLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(leftbackLeg, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
//        this.MimicTongue_1 = new ModelRenderer(this, 2, 30);
//        this.MimicTongue_1.setRotationPoint(0.0F, 0.0F, -7.8F);
//        this.MimicTongue_1.addBox(-3.0F, -1.0F, -5.0F, 6, 1, 5, 0.0F);
//        this.setRotateAngle(MimicTongue_1, 0.20943951023931953F, 0.0F, 0.0F);
//        this.MimicGums_7 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_7.setRotationPoint(15.0F, 0.0F, 0.0F);
//        this.MimicGums_7.addBox(0.0F, 0.0F, 0.0F, 1, 6, 16, 0.0F);
//        this.leftLeg_1 = new ModelRenderer(this, 112, 20);
//        this.leftLeg_1.setRotationPoint(0.3F, 3.5F, -0.3F);
//        this.leftLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(leftLeg_1, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
//        this.leftbackLeg_1 = new ModelRenderer(this, 112, 20);
//        this.leftbackLeg_1.setRotationPoint(0.3F, 3.5F, -0.3F);
//        this.leftbackLeg_1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
//        this.setRotateAngle(leftbackLeg_1, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
//        this.MimiEyePart_2 = new ModelRenderer(this, 50, 0);
//        this.MimiEyePart_2.setRotationPoint(0.0F, 0.0F, -0.1F);
//        this.MimiEyePart_2.addBox(-2.0F, -1.0F, -3.0F, 4, 1, 6, 0.0F);
//        this.MimicGums_1 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_1.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MimicGums_1.addBox(-8.0F, 0.0F, -8.0F, 1, 6, 16, 0.0F);
//        this.MimicTeeth_1 = new ModelRenderer(this, 68, 46);
//        this.MimicTeeth_1.setRotationPoint(0.5F, -2.4F, 0.5F);
//        this.MimicTeeth_1.addBox(0.0F, 0.0F, 0.0F, 15, 3, 15, 0.0F);
//        this.MimicGums = new ModelRenderer(this, 68, 21);
//        this.MimicGums.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MimicGums.addBox(-8.0F, 0.0F, -8.0F, 16, 6, 1, 0.0F);
//        this.MimicTongue_2 = new ModelRenderer(this, 2, 30);
//        this.MimicTongue_2.setRotationPoint(0.0F, -0.1F, -4.7F);
//        this.MimicTongue_2.addBox(-3.0F, -1.0F, -5.0F, 6, 1, 5, 0.0F);
//        this.setRotateAngle(MimicTongue_2, 0.3490658503988659F, 0.0F, 0.0F);
//        this.MimicGums_5 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_5.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.MimicGums_5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 16, 0.0F);
//        this.MimicGums_2 = new ModelRenderer(this, 68, 21);
//        this.MimicGums_2.setRotationPoint(0.0F, 0.0F, 15.0F);
//        this.MimicGums_2.addBox(-8.0F, 0.0F, -8.0F, 16, 6, 1, 0.0F);
//        this.MimicEyePart_1.addChild(this.MimiEyePart_3);
//        this.rightLeg.addChild(this.rightLeg_1);
//        this.box.addChild(this.MimicTongue);
//        this.MimicEye.addChild(this.MimiEyePart_1);
//        this.boxLid.addChild(this.MimicGums_6);
//        this.rightbackLeg.addChild(this.rightbackLeg_1);
//        this.MimicEye.addChild(this.MimicEyePart_1);
//        this.box.addChild(this.MimicTeeth);
//        this.MimicEye.addChild(this.MimiEyePart);
//        this.box.addChild(this.MimicThroat);
//        this.boxLid.addChild(this.MimicEye);
//        this.MimicEye.addChild(this.MimicEyePart);
//        this.boxLid.addChild(this.MimicLatch);
//        this.boxLid.addChild(this.MimicGums_4);
//        this.boxLid.addChild(this.MimicThroat_1);
//        this.box.addChild(this.MimicGums_3);
//        this.MimicTongue.addChild(this.MimicTongue_1);
//        this.boxLid.addChild(this.MimicGums_7);
//        this.leftLeg.addChild(this.leftLeg_1);
//        this.leftbackLeg.addChild(this.leftbackLeg_1);
//        this.MimicEyePart.addChild(this.MimiEyePart_2);
//        this.box.addChild(this.MimicGums_1);
//        this.boxLid.addChild(this.MimicTeeth_1);
//        this.box.addChild(this.MimicGums);
//        this.MimicTongue_1.addChild(this.MimicTongue_2);
//        this.boxLid.addChild(this.MimicGums_5);
//        this.box.addChild(this.MimicGums_2);
//
//        this.box2 = new ModelRenderer(this, 0, 0);
//        this.box2.setRotationPoint(0.0F, 14.0F, 0.0F);
//        this.box2.addBox(-8.0F, 0.0F, -8.0F, 16, 10, 16, 0.0F);
//        this.boxLid2 = new ModelRenderer(this, 0, 42);
//        this.boxLid2.setRotationPoint(-8.0F, 14.0F, 8.0F);
//        this.boxLid2.addBox(0.0F, 0.0F, 0.0F, 16, 6, 16, 0.0F);
//        this.setRotateAngle(boxLid2, 3.141592653589793F, 0.0F, 0.0F);
//        this.boxLid2.addChild(this.MimicLatch);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        if(getRenderChest()){
//            this.renderMimicChest(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
//        }
//        else {
//            this.renderMimic(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
//        }
//    }
//
//    public void renderMimicChest(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.boxLid2.render(f5);
//        this.box2.render(f5);
//    }
//
//    public void renderMimic(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.boxLid.render(f5);
//        this.box.render(f5);
//        this.leftLeg.render(f5);
//        this.leftbackLeg.render(f5);
//        this.rightLeg.render(f5);
//        this.rightbackLeg.render(f5);
//        setMimicAttack(true);
//    }
//
//    @Override
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//        if(isMimicAttack()){
//            float baseBackLegRotation = 0.2617993877991494F;
//            float baseFrontLegRotation = -0.2617993877991494F;
//            float baseLidRotation = 2.356194490192345F;
//            float baseTongueRotation = -0.3665191429188092F;
//            float hinge = Math.min(limbSwingAmount, 0.6F);
//                this.boxLid.rotateAngleX = baseLidRotation + ((MathHelper.cos(ageInTicks * 0.6662F) * 1.0F - (float) Math.PI / 3) * hinge);
//
//                this.leftLeg.rotateAngleX = baseFrontLegRotation + (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
//                this.leftbackLeg.rotateAngleX = baseBackLegRotation + (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
//                this.rightLeg.rotateAngleX = baseFrontLegRotation + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount);
//                this.rightbackLeg.rotateAngleX = baseBackLegRotation + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount);
//
//                MimicTongue.rotateAngleX = baseTongueRotation + (MathHelper.cos(degToRad(entityIn.ticksExisted * 7)) * degToRad(10));
//                MimicTongue_1.rotateAngleX = MimicTongue.rotateAngleX * 3;
//                MimicTongue_2.rotateAngleX = MimicTongue_1.rotateAngleX * 1;
//        }
//    }
//
//    public static void setRenderChest(boolean renderChest) {
//        ModelMimicChest.renderChest = renderChest;
//    }
//
//    private boolean getRenderChest() {
//        return renderChest;
//    }
//
//    public void setMimicAttack(boolean mimicAttack) {
//        this.mimicAttack = mimicAttack;
//    }
//
//    public boolean isMimicAttack() {
//        return mimicAttack;
//    }
//}