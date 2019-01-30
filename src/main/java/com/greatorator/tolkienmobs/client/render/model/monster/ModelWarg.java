package com.greatorator.tolkienmobs.client.render.model.monster;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * Warg - GreatOrator
 */
public class ModelWarg extends ModelTolkienMobs {
    public ModelRenderer WargLegFL;
    public ModelRenderer WargLegRL;
    public ModelRenderer WargLegRR;
    public ModelRenderer WargLegFR;
    public ModelRenderer WargChest;
    public ModelRenderer WargBody;
    public ModelRenderer WargTail;
    public ModelRenderer WargNeck;
    public ModelRenderer WargLegFLM;
    public ModelRenderer WargLegFLF;
    public ModelRenderer WargLegRLM1;
    public ModelRenderer WargLegRLM2;
    public ModelRenderer WargLegRLF;
    public ModelRenderer WargLegRRM1;
    public ModelRenderer WargLegRRM2;
    public ModelRenderer WargLegRRF;
    public ModelRenderer WargLegFRM;
    public ModelRenderer WargLegFRF;
    public ModelRenderer WargHump;
    public ModelRenderer WargHead;
    public ModelRenderer WargSnout;
    public ModelRenderer WargNose;
    public ModelRenderer WargTooth2;
    public ModelRenderer WargTooth1;
    public ModelRenderer WargTooth3;
    public ModelRenderer WargTooth4;
    public ModelRenderer WargEar1;
    public ModelRenderer WargEar2;

    public ModelWarg() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.WargEar1 = new ModelRenderer(this, 0, 44);
        this.WargEar1.setRotationPoint(-4.0F, -4.0F, -7.5F);
        this.WargEar1.addBox(-0.5F, -4.5F, -1.0F, 1, 5, 3, 0.0F);
        this.setRotateAngle(WargEar1, 0.0F, -0.3490658503988659F, -0.17453292519943295F);
        this.WargLegRR = new ModelRenderer(this, 0, 0);
        this.WargLegRR.setRotationPoint(5.0F, 7.1F, 14.5F);
        this.WargLegRR.addBox(0.0F, -1.5F, -1.5F, 3, 10, 3, 0.0F);
        this.setRotateAngle(WargLegRR, 0.17453292519943295F, 0.0F, 0.0F);
        this.WargHead = new ModelRenderer(this, 19, 11);
        this.WargHead.setRotationPoint(-1.5F, -3.5F, -4.8F);
        this.WargHead.addBox(-2.5F, -2.5F, -9.0F, 9, 13, 9, 0.0F);
        this.WargLegRRM2 = new ModelRenderer(this, 0, 0);
        this.WargLegRRM2.setRotationPoint(0.0F, 13.6F, 2.9F);
        this.WargLegRRM2.addBox(0.0F, -1.5F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(WargLegRRM2, -0.3490658503988659F, 0.0F, 0.0F);
        this.WargLegRL = new ModelRenderer(this, 0, 0);
        this.WargLegRL.setRotationPoint(-5.0F, 7.1F, 14.5F);
        this.WargLegRL.addBox(-3.0F, -1.5F, -1.5F, 3, 10, 3, 0.0F);
        this.setRotateAngle(WargLegRL, 0.17453292519943295F, 0.0F, 0.0F);
        this.WargTooth2 = new ModelRenderer(this, 15, 44);
        this.WargTooth2.setRotationPoint(-1.6F, 4.6F, -20.1F);
        this.WargTooth2.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(WargTooth2, 0.07853981633974483F, 0.0F, -0.07853981633974483F);
        this.WargLegRRM1 = new ModelRenderer(this, 0, 0);
        this.WargLegRRM1.setRotationPoint(0.0F, 8.9F, 0.6F);
        this.WargLegRRM1.addBox(0.0F, -1.5F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(WargLegRRM1, 0.5759586531581287F, 0.0F, 0.0F);
        this.WargTooth4 = new ModelRenderer(this, 15, 44);
        this.WargTooth4.setRotationPoint(1.3F, 6.5F, -20.1F);
        this.WargTooth4.addBox(-0.5F, -2.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(WargTooth4, 0.07853981633974483F, 0.0F, 0.0F);
        this.WargChest = new ModelRenderer(this, 0, 33);
        this.WargChest.setRotationPoint(0.0F, 5.5F, -17.0F);
        this.WargChest.addBox(-10.5F, -8.0F, 0.0F, 21, 12, 19, 0.0F);
        this.setRotateAngle(WargChest, -0.017453292519943295F, 0.0F, 0.0F);
        this.WargLegFLF = new ModelRenderer(this, 12, 0);
        this.WargLegFLF.setRotationPoint(0.0F, 16.0F, -4.2F);
        this.WargLegFLF.addBox(-3.0F, -1.5F, -1.0F, 3, 5, 2, 0.0F);
        this.setRotateAngle(WargLegFLF, -1.7453292519943295F, 0.0F, 0.0F);
        this.WargTail = new ModelRenderer(this, 0, 54);
        this.WargTail.setRotationPoint(0.0F, 3.0F, 15.5F);
        this.WargTail.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.setRotateAngle(WargTail, 0.04363323129985824F, 0.0F, 0.0F);
        this.WargLegFRM = new ModelRenderer(this, 0, 0);
        this.WargLegFRM.setRotationPoint(0.0F, 5.4F, -0.4F);
        this.WargLegFRM.addBox(0.0F, -1.5F, -1.5F, 3, 13, 3, 0.0F);
        this.setRotateAngle(WargLegFRM, -0.3490658503988659F, 0.0F, 0.0F);
        this.WargLegRRF = new ModelRenderer(this, 12, 0);
        this.WargLegRRF.setRotationPoint(0.0F, 17.0F, 1.7F);
        this.WargLegRRF.addBox(0.0F, -1.5F, -1.5F, 3, 5, 2, 0.0F);
        this.setRotateAngle(WargLegRRF, -1.7453292519943295F, 0.0F, 0.0F);
        this.WargTooth1 = new ModelRenderer(this, 15, 44);
        this.WargTooth1.setRotationPoint(2.6F, 4.6F, -20.1F);
        this.WargTooth1.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(WargTooth1, 0.07853981633974483F, 0.0F, 0.07853981633974483F);
        this.WargBody = new ModelRenderer(this, 12, 39);
        this.WargBody.setRotationPoint(3.0F, 6.8F, 2.0F);
        this.WargBody.addBox(-10.5F, -8.0F, 0.0F, 15, 11, 14, 0.0F);
        this.setRotateAngle(WargBody, -0.017453292519943295F, 0.0F, 0.0F);
        this.WargNose = new ModelRenderer(this, 11, 48);
        this.WargNose.setRotationPoint(0.0F, -1.0F, -20.5F);
        this.WargNose.addBox(-0.5F, -0.5F, -0.5F, 2, 2, 2, 0.0F);
        this.WargLegFR = new ModelRenderer(this, 0, 0);
        this.WargLegFR.setRotationPoint(8.0F, 6.5F, -14.5F);
        this.WargLegFR.addBox(0.0F, -1.5F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(WargLegFR, 0.17453292519943295F, 0.0F, 0.0F);
        this.WargSnout = new ModelRenderer(this, 61, 38);
        this.WargSnout.setRotationPoint(0.5F, 1.5F, -11.5F);
        this.WargSnout.addBox(-2.5F, -2.5F, -9.0F, 5, 7, 7, 0.0F);
        this.WargLegFL = new ModelRenderer(this, 0, 0);
        this.WargLegFL.setRotationPoint(-8.0F, 6.5F, -14.5F);
        this.WargLegFL.addBox(-3.0F, -1.5F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(WargLegFL, 0.17453292519943295F, 0.0F, 0.0F);
        this.WargNeck = new ModelRenderer(this, 24, 45);
        this.WargNeck.setRotationPoint(0.0F, 3.0F, -16.5F);
        this.WargNeck.addBox(-2.5F, -2.5F, -9.0F, 6, 6, 9, 0.0F);
        this.setRotateAngle(WargNeck, 0.17453292519943295F, 0.0F, 0.0F);
        this.WargLegRLM1 = new ModelRenderer(this, 0, 0);
        this.WargLegRLM1.setRotationPoint(0.0F, 8.9F, 0.6F);
        this.WargLegRLM1.addBox(-3.0F, -1.5F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(WargLegRLM1, 0.5759586531581287F, 0.0F, 0.0F);
        this.WargLegFRF = new ModelRenderer(this, 12, 0);
        this.WargLegFRF.setRotationPoint(0.0F, 16.0F, -4.2F);
        this.WargLegFRF.addBox(0.0F, -1.5F, -1.0F, 3, 5, 2, 0.0F);
        this.setRotateAngle(WargLegFRF, -1.7453292519943295F, 0.0F, 0.0F);
        this.WargLegFLM = new ModelRenderer(this, 0, 0);
        this.WargLegFLM.setRotationPoint(0.0F, 5.4F, -0.4F);
        this.WargLegFLM.addBox(-3.0F, -1.5F, -1.5F, 3, 13, 3, 0.0F);
        this.setRotateAngle(WargLegFLM, -0.3490658503988659F, 0.0F, 0.0F);
        this.WargTooth3 = new ModelRenderer(this, 15, 44);
        this.WargTooth3.setRotationPoint(-0.3F, 6.5F, -20.1F);
        this.WargTooth3.addBox(-0.5F, -2.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(WargTooth3, 0.07853981633974483F, 0.0F, 0.0F);
        this.WargEar2 = new ModelRenderer(this, 0, 44);
        this.WargEar2.mirror = true;
        this.WargEar2.setRotationPoint(5.0F, -4.0F, -7.5F);
        this.WargEar2.addBox(-0.5F, -4.5F, -1.0F, 1, 5, 3, 0.0F);
        this.setRotateAngle(WargEar2, 0.0F, 0.3490658503988659F, 0.17453292519943295F);
        this.WargHump = new ModelRenderer(this, 8, 40);
        this.WargHump.setRotationPoint(2.0F, -2.0F, 0.0F);
        this.WargHump.addBox(-10.5F, -8.0F, 0.0F, 17, 5, 17, 0.0F);
        this.setRotateAngle(WargHump, 0.017453292519943295F, 0.0F, 0.0F);
        this.WargLegRLM2 = new ModelRenderer(this, 0, 0);
        this.WargLegRLM2.setRotationPoint(0.0F, 13.6F, 2.9F);
        this.WargLegRLM2.addBox(-3.0F, -1.5F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(WargLegRLM2, -0.3490658503988659F, 0.0F, 0.0F);
        this.WargLegRLF = new ModelRenderer(this, 12, 0);
        this.WargLegRLF.setRotationPoint(0.0F, 17.0F, 1.7F);
        this.WargLegRLF.addBox(-3.0F, -1.5F, -1.5F, 3, 5, 2, 0.0F);
        this.setRotateAngle(WargLegRLF, -1.7453292519943295F, 0.0F, 0.0F);
        this.WargNeck.addChild(this.WargEar1);
        this.WargNeck.addChild(this.WargHead);
        this.WargLegRR.addChild(this.WargLegRRM2);
        this.WargNeck.addChild(this.WargTooth2);
        this.WargLegRR.addChild(this.WargLegRRM1);
        this.WargNeck.addChild(this.WargTooth4);
        this.WargLegFL.addChild(this.WargLegFLF);
        this.WargLegFR.addChild(this.WargLegFRM);
        this.WargLegRR.addChild(this.WargLegRRF);
        this.WargNeck.addChild(this.WargTooth1);
        this.WargNeck.addChild(this.WargNose);
        this.WargNeck.addChild(this.WargSnout);
        this.WargLegRL.addChild(this.WargLegRLM1);
        this.WargLegFR.addChild(this.WargLegFRF);
        this.WargLegFL.addChild(this.WargLegFLM);
        this.WargNeck.addChild(this.WargTooth3);
        this.WargNeck.addChild(this.WargEar2);
        this.WargChest.addChild(this.WargHump);
        this.WargLegRL.addChild(this.WargLegRLM2);
        this.WargLegRL.addChild(this.WargLegRLF);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        this.WargLegRR.render(f5);
        this.WargLegRL.render(f5);
        this.WargChest.render(f5);
        this.WargTail.render(f5);
        this.WargBody.render(f5);
        this.WargLegFR.render(f5);
        this.WargLegFL.render(f5);
        this.WargNeck.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase parEntityLivingBase, float parLimbSwingAngle, float parMaxLimbSwingDistance, float parHeadAngleChangeRate)
    {
        WargTail.rotateAngleY = MathHelper.cos(parLimbSwingAngle * 0.6662F) * 1.4F * parMaxLimbSwingDistance;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        this.WargLegFR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.WargLegRR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.WargLegFL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.WargLegRL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.WargNeck.rotateAngleX = headPitch * 0.017453292F;
        this.WargNeck.rotateAngleY = netHeadYaw * 0.017453292F;
        this.WargTail.rotateAngleX = ageInTicks;
    }
}