package com.greatorator.tolkienmobs.client.render.model.special;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelElementalGolem extends ModelTTM {
    public ModelRenderer GolemBody;
    public ModelRenderer GolemUpperLegL;
    public ModelRenderer GolemUpperLegR;
    public ModelRenderer GolemHead;
    public ModelRenderer GolemBodyPart;
    public ModelRenderer GolemBodyPart_1;
    public ModelRenderer GolemBodyPart_2;
    public ModelRenderer GolemShoulderL;
    public ModelRenderer GolemShoulderR;
    public ModelRenderer GolemBack;
    public ModelRenderer GolemBack_1;
    public ModelRenderer GolemShoulderPartL;
    public ModelRenderer GolemShoulderPartL_1;
    public ModelRenderer GolemShoulderPartL_2;
    public ModelRenderer GolemShoulderPartR;
    public ModelRenderer GolemShoulderPartR_1;
    public ModelRenderer GolemShoulderPartR_2;
    public ModelRenderer GolemArmL;
    public ModelRenderer GolemHandL;
    public ModelRenderer GolemArmR;
    public ModelRenderer GolemHandR;
    public ModelRenderer GolemMidLegL;
    public ModelRenderer GolemFootL;
    public ModelRenderer GolemFootPartL;
    public ModelRenderer GolemFootPartL_1;
    public ModelRenderer GolemFootPartL_2;
    public ModelRenderer GolemFootPartL_3;
    public ModelRenderer GolemFootPartL_4;
    public ModelRenderer GolemFootPartL_5;
    public ModelRenderer GolemFootPartL_6;
    public ModelRenderer GolemFootPartL_7;
    public ModelRenderer GolemMidLegR;
    public ModelRenderer GolemFootR;
    public ModelRenderer GolemFootPartR;
    public ModelRenderer GolemFootPartR_1;
    public ModelRenderer GolemFootPartR_2;
    public ModelRenderer GolemFootPartR_3;
    public ModelRenderer GolemFootPartR_4;
    public ModelRenderer GolemFootPartR_5;
    public ModelRenderer GolemFootPartR_6;
    public ModelRenderer GolemFootPartR_7;
    public static boolean renderGolem;

    public ModelElementalGolem() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.GolemHandL = new ModelRenderer(this, 20, 96);
        this.GolemHandL.mirror = true;
        this.GolemHandL.setRotationPoint(-0.5F, 8.0F, -0.5F);
        this.GolemHandL.addBox(-2.5F, 0.0F, -2.5F, 6, 5, 6, 0.0F);
        this.setRotateAngle(GolemHandL, 0.0F, 0.0F, 0.17453292519943295F);
        this.GolemFootL = new ModelRenderer(this, 17, 114);
        this.GolemFootL.mirror = true;
        this.GolemFootL.setRotationPoint(0.0F, 11.9F, -1.0F);
        this.GolemFootL.addBox(-5.0F, 0.0F, -5.5F, 10, 3, 11, 0.0F);
        this.setRotateAngle(GolemFootL, 0.0F, 0.0F, 0.08726646259971647F);
        this.GolemFootPartR_3 = new ModelRenderer(this, 82, 100);
        this.GolemFootPartR_3.setRotationPoint(0.0F, 1.0F, 0.1F);
        this.GolemFootPartR_3.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(GolemFootPartR_3, 0.0F, -1.5707963267948966F, 0.0F);
        this.GolemFootPartL_2 = new ModelRenderer(this, 82, 100);
        this.GolemFootPartL_2.mirror = true;
        this.GolemFootPartL_2.setRotationPoint(0.0F, 0.9F, 0.1F);
        this.GolemFootPartL_2.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(GolemFootPartL_2, 0.0F, 3.141592653589793F, 0.0F);
        this.GolemBodyPart = new ModelRenderer(this, 56, 81);
        this.GolemBodyPart.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemBodyPart.addBox(-7.0F, -8.8F, -8.5F, 14, 5, 4, 0.0F);
        this.setRotateAngle(GolemBodyPart, -0.4363323129985824F, 0.0F, 0.0F);
        this.GolemMidLegL = new ModelRenderer(this, 59, 107);
        this.GolemMidLegL.mirror = true;
        this.GolemMidLegL.setRotationPoint(4.5F, 5.6F, 1.0F);
        this.GolemMidLegL.addBox(-3.5F, 0.0F, -5.0F, 7, 13, 8, 0.0F);
        this.setRotateAngle(GolemMidLegL, 0.0F, 0.0F, 0.17453292519943295F);
        this.GolemFootPartL_1 = new ModelRenderer(this, 82, 100);
        this.GolemFootPartL_1.mirror = true;
        this.GolemFootPartL_1.setRotationPoint(0.0F, 0.9F, 0.1F);
        this.GolemFootPartL_1.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(GolemFootPartL_1, 0.0F, 1.5707963267948966F, 0.0F);
        this.GolemFootPartR_4 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartR_4.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartR_4.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartR_4, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemBodyPart_2 = new ModelRenderer(this, 56, 81);
        this.GolemBodyPart_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemBodyPart_2.addBox(-7.0F, -7.0F, -6.0F, 14, 5, 4, 0.0F);
        this.GolemShoulderL = new ModelRenderer(this, 47, 90);
        this.GolemShoulderL.mirror = true;
        this.GolemShoulderL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderL.addBox(0.7F, -13.0F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderL, 0.0F, 0.0F, -0.17453292519943295F);
        this.GolemShoulderPartL_2 = new ModelRenderer(this, 47, 90);
        this.GolemShoulderPartL_2.mirror = true;
        this.GolemShoulderPartL_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderPartL_2.addBox(0.7F, -11.7F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderPartL_2, 0.0F, 0.0F, 0.7853981633974483F);
        this.GolemArmR = new ModelRenderer(this, 0, 94);
        this.GolemArmR.setRotationPoint(-1.0F, 8.5F, 0.0F);
        this.GolemArmR.addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(GolemArmR, -0.13962634015954636F, 0.0F, -0.05585053606381855F);
        this.GolemBack_1 = new ModelRenderer(this, 92, 81);
        this.GolemBack_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemBack_1.addBox(-7.5F, -3.1F, 1.0F, 15, 3, 3, 0.0F);
        this.setRotateAngle(GolemBack_1, -0.5235987755982988F, 0.0F, 0.0F);
        this.GolemFootPartL_5 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartL_5.mirror = true;
        this.GolemFootPartL_5.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartL_5.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartL_5, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemFootPartL = new ModelRenderer(this, 82, 100);
        this.GolemFootPartL.mirror = true;
        this.GolemFootPartL.setRotationPoint(0.0F, 0.9F, 0.1F);
        this.GolemFootPartL.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.GolemShoulderPartL_1 = new ModelRenderer(this, 47, 90);
        this.GolemShoulderPartL_1.mirror = true;
        this.GolemShoulderPartL_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderPartL_1.addBox(4.3F, -15.9F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderPartL_1, 0.0F, 0.0F, 0.4363323129985824F);
        this.GolemShoulderR = new ModelRenderer(this, 47, 90);
        this.GolemShoulderR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderR.addBox(0.7F, -13.0F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderR, 0.0F, 3.141592653589793F, 0.17453292519943295F);
        this.GolemBody = new ModelRenderer(this, 0, 62);
        this.GolemBody.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.GolemBody.addBox(-8.0F, -12.0F, -4.0F, 16, 24, 8, 0.0F);
        this.GolemUpperLegR = new ModelRenderer(this, 90, 108);
        this.GolemUpperLegR.setRotationPoint(-1.5F, 4.5F, 0.0F);
        this.GolemUpperLegR.addBox(-8.0F, -3.0F, -5.0F, 9, 10, 10, 0.0F);
        this.setRotateAngle(GolemUpperLegR, -0.006283185307179587F, -0.006283185307179587F, 0.2617993877991494F);
        this.GolemMidLegR = new ModelRenderer(this, 59, 107);
        this.GolemMidLegR.setRotationPoint(-3.5F, 5.7F, 1.0F);
        this.GolemMidLegR.addBox(-3.5F, 0.0F, -5.0F, 7, 13, 8, 0.0F);
        this.setRotateAngle(GolemMidLegR, 0.0F, 0.0F, -0.17453292519943295F);
        this.GolemHandR = new ModelRenderer(this, 20, 96);
        this.GolemHandR.setRotationPoint(-0.5F, 8.0F, -0.5F);
        this.GolemHandR.addBox(-2.5F, 0.0F, -2.5F, 6, 5, 6, 0.0F);
        this.setRotateAngle(GolemHandR, 0.0F, 0.0F, -0.17453292519943295F);
        this.GolemFootPartR = new ModelRenderer(this, 82, 100);
        this.GolemFootPartR.setRotationPoint(0.0F, 1.0F, 0.1F);
        this.GolemFootPartR.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.GolemFootPartL_4 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartL_4.mirror = true;
        this.GolemFootPartL_4.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartL_4.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartL_4, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemFootPartR_1 = new ModelRenderer(this, 82, 100);
        this.GolemFootPartR_1.setRotationPoint(0.0F, 1.0F, 0.1F);
        this.GolemFootPartR_1.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(GolemFootPartR_1, 0.0F, 1.5707963267948966F, 0.0F);
        this.GolemFootPartR_5 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartR_5.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartR_5.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartR_5, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemHead = new ModelRenderer(this, 48, 62);
        this.GolemHead.setRotationPoint(0.0F, -16.5F, -2.0F);
        this.GolemHead.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.0F);
        this.GolemFootPartL_3 = new ModelRenderer(this, 82, 100);
        this.GolemFootPartL_3.mirror = true;
        this.GolemFootPartL_3.setRotationPoint(0.0F, 0.9F, 0.1F);
        this.GolemFootPartL_3.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(GolemFootPartL_3, 0.0F, -1.5707963267948966F, 0.0F);
        this.GolemBack = new ModelRenderer(this, 92, 87);
        this.GolemBack.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemBack.addBox(-7.5F, -7.7F, 2.0F, 15, 7, 3, 0.0F);
        this.GolemShoulderPartR = new ModelRenderer(this, 47, 90);
        this.GolemShoulderPartR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderPartR.addBox(4.5F, -13.4F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderPartR, 0.0F, 0.0F, 0.08726646259971647F);
        this.GolemShoulderPartR_1 = new ModelRenderer(this, 47, 90);
        this.GolemShoulderPartR_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderPartR_1.addBox(4.3F, -15.9F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderPartR_1, 0.0F, 0.0F, 0.4363323129985824F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 109);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(8.5F, -16.0F, 0.0F);
        this.bipedLeftArm.addBox(-2.0F, -1.0F, -3.0F, 6, 10, 6, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.2617993877991494F);
        this.GolemBodyPart_1 = new ModelRenderer(this, 56, 81);
        this.GolemBodyPart_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemBodyPart_1.addBox(-7.0F, -4.3F, -4.5F, 14, 5, 4, 0.0F);
        this.setRotateAngle(GolemBodyPart_1, 0.4363323129985824F, 0.0F, 0.0F);
        this.GolemFootPartR_2 = new ModelRenderer(this, 82, 100);
        this.GolemFootPartR_2.setRotationPoint(0.0F, 1.0F, 0.1F);
        this.GolemFootPartR_2.addBox(-2.5F, 0.0F, -8.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(GolemFootPartR_2, 0.0F, 3.141592653589793F, 0.0F);
        this.GolemShoulderPartL = new ModelRenderer(this, 47, 90);
        this.GolemShoulderPartL.mirror = true;
        this.GolemShoulderPartL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderPartL.addBox(4.5F, -13.4F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderPartL, 0.0F, 0.0F, 0.08726646259971647F);
        this.GolemFootPartL_7 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartL_7.mirror = true;
        this.GolemFootPartL_7.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartL_7.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartL_7, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemShoulderPartR_2 = new ModelRenderer(this, 47, 90);
        this.GolemShoulderPartR_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.GolemShoulderPartR_2.addBox(0.7F, -11.7F, -5.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(GolemShoulderPartR_2, 0.0F, 0.0F, 0.7853981633974483F);
        this.bipedRightArm = new ModelRenderer(this, 0, 109);
        this.bipedRightArm.setRotationPoint(-8.5F, -16.0F, 0.0F);
        this.bipedRightArm.addBox(-4.0F, -1.0F, -3.0F, 6, 10, 6, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.2617993877991494F);
        this.GolemFootPartL_6 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartL_6.mirror = true;
        this.GolemFootPartL_6.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartL_6.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartL_6, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemUpperLegL = new ModelRenderer(this, 90, 108);
        this.GolemUpperLegL.mirror = true;
        this.GolemUpperLegL.setRotationPoint(0.5F, 5.0F, 0.0F);
        this.GolemUpperLegL.addBox(0.0F, -3.0F, -5.0F, 9, 10, 10, 0.0F);
        this.setRotateAngle(GolemUpperLegL, 0.0F, 0.0F, -0.2617993877991494F);
        this.GolemFootPartR_7 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartR_7.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartR_7.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartR_7, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemFootR = new ModelRenderer(this, 17, 114);
        this.GolemFootR.setRotationPoint(0.0F, 11.9F, -1.0F);
        this.GolemFootR.addBox(-5.0F, 0.0F, -5.5F, 10, 3, 11, 0.0F);
        this.setRotateAngle(GolemFootR, 0.0F, 0.0F, -0.08726646259971647F);
        this.GolemFootPartR_6 = new ModelRenderer(this, 102, 97);
        this.GolemFootPartR_6.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.GolemFootPartR_6.addBox(-2.5F, 0.0F, -11.4F, 5, 2, 8, 0.0F);
        this.setRotateAngle(GolemFootPartR_6, 0.7853981633974483F, 0.0F, 0.0F);
        this.GolemArmL = new ModelRenderer(this, 0, 94);
        this.GolemArmL.mirror = true;
        this.GolemArmL.setRotationPoint(1.0F, 8.5F, 0.0F);
        this.GolemArmL.addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(GolemArmL, -0.13962634015954636F, 0.0F, 0.05585053606381855F);
        this.GolemArmL.addChild(this.GolemHandL);
        this.GolemMidLegL.addChild(this.GolemFootL);
        this.GolemFootR.addChild(this.GolemFootPartR_3);
        this.GolemFootL.addChild(this.GolemFootPartL_2);
        this.GolemBody.addChild(this.GolemBodyPart);
        this.GolemUpperLegL.addChild(this.GolemMidLegL);
        this.GolemFootL.addChild(this.GolemFootPartL_1);
        this.GolemFootPartR.addChild(this.GolemFootPartR_4);
        this.GolemBody.addChild(this.GolemBodyPart_2);
        this.GolemBody.addChild(this.GolemShoulderL);
        this.GolemShoulderL.addChild(this.GolemShoulderPartL_2);
        this.bipedRightArm.addChild(this.GolemArmR);
        this.GolemBody.addChild(this.GolemBack_1);
        this.GolemFootPartL_1.addChild(this.GolemFootPartL_5);
        this.GolemFootL.addChild(this.GolemFootPartL);
        this.GolemShoulderL.addChild(this.GolemShoulderPartL_1);
        this.GolemBody.addChild(this.GolemShoulderR);
        this.GolemUpperLegR.addChild(this.GolemMidLegR);
        this.GolemArmR.addChild(this.GolemHandR);
        this.GolemFootR.addChild(this.GolemFootPartR);
        this.GolemFootPartL.addChild(this.GolemFootPartL_4);
        this.GolemFootR.addChild(this.GolemFootPartR_1);
        this.GolemFootPartR_1.addChild(this.GolemFootPartR_5);
        this.GolemFootL.addChild(this.GolemFootPartL_3);
        this.GolemBody.addChild(this.GolemBack);
        this.GolemShoulderR.addChild(this.GolemShoulderPartR);
        this.GolemShoulderR.addChild(this.GolemShoulderPartR_1);
        this.GolemBody.addChild(this.GolemBodyPart_1);
        this.GolemFootR.addChild(this.GolemFootPartR_2);
        this.GolemShoulderL.addChild(this.GolemShoulderPartL);
        this.GolemFootPartL_3.addChild(this.GolemFootPartL_7);
        this.GolemShoulderR.addChild(this.GolemShoulderPartR_2);
        this.GolemFootPartL_2.addChild(this.GolemFootPartL_6);
        this.GolemFootPartR_3.addChild(this.GolemFootPartR_7);
        this.GolemMidLegR.addChild(this.GolemFootR);
        this.GolemFootPartR_2.addChild(this.GolemFootPartR_6);
        this.bipedLeftArm.addChild(this.GolemArmL);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if(getRenderGolem()){
            this.renderElementalGolem(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else {
            this.renderNormalGolem(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
    }

    public void renderElementalGolem(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.GolemBody.render(f5);
        this.GolemUpperLegR.render(f5);
        this.GolemHead.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightArm.render(f5);
        this.GolemUpperLegL.render(f5);
        //this.GolemExtraParts.render(f5);
    }

    public void renderNormalGolem(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.GolemBody.render(f5);
        this.GolemUpperLegR.render(f5);
        this.GolemHead.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightArm.render(f5);
        this.GolemUpperLegL.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float speed = 0.6662F; // Limb Swing Speed
        float angle = 1.4F; // How far the Limb swings

        this.GolemUpperLegL.rotateAngleX = (MathHelper.cos(limbSwing * speed)) * angle * limbSwingAmount;
        this.GolemUpperLegR.rotateAngleX = (MathHelper.cos(limbSwing * speed + (float) Math.PI)) * angle * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = (MathHelper.cos(limbSwing * speed)) * angle * limbSwingAmount;
        this.bipedLeftArm.rotateAngleX = (MathHelper.cos(limbSwing * speed + (float) Math.PI)) * angle * limbSwingAmount;

        this.GolemHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.GolemHead.rotateAngleX = headPitch * 0.017453292F;
    }

    public static void setRenderGolem(boolean renderGolem) {
        ModelElementalGolem.renderGolem = renderGolem;
    }

    private boolean getRenderGolem() {
        return renderGolem;
    }
}