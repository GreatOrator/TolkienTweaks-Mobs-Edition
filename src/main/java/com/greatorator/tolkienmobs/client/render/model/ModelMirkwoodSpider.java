package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

/**
 * Mirkwood Spider - GreatOrator
 */
public class ModelMirkwoodSpider extends ModelBase {
    public ModelRenderer SpiderHead;
    public ModelRenderer SpiderMandibleA1;
    public ModelRenderer SpiderMandibleA2;
    public ModelRenderer SpiderMandibleB1;
    public ModelRenderer SpiderMandibleB2;
    public ModelRenderer SpiderThorax;
    public ModelRenderer SpiderAbdomen;
    public ModelRenderer SpiderLegA;
    public ModelRenderer SpiderLegA2;
    public ModelRenderer SpiderLegA3;
    public ModelRenderer SpiderLegB;
    public ModelRenderer SpiderLegB2;
    public ModelRenderer SpiderLegB3;
    public ModelRenderer SpiderLegC;
    public ModelRenderer SpiderLegC2;
    public ModelRenderer SpiderLegC3;
    public ModelRenderer SpiderLegD;
    public ModelRenderer SpiderLegD2;
    public ModelRenderer SpiderLegD3;
    public ModelRenderer SpiderLegE;
    public ModelRenderer SpiderLegE2;
    public ModelRenderer SpiderLegE3;
    public ModelRenderer SpiderLegF;
    public ModelRenderer SpiderLegF2;
    public ModelRenderer SpiderLegF3;
    public ModelRenderer SpiderLegG;
    public ModelRenderer SpiderLegG2;
    public ModelRenderer SpiderLegG3;
    public ModelRenderer SpiderLegH;
    public ModelRenderer SpiderLegH2;
    public ModelRenderer SpiderLegH3;

    public ModelMirkwoodSpider() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.SpiderLegH = new ModelRenderer(this, 80, 36);
        this.SpiderLegH.setRotationPoint(-4.0F, 16.8F, 2.0F);
        this.SpiderLegH.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH, 0.9162978572970231F, 0.7853981633974483F, 1.0786134777324956F);
        this.SpiderLegD = new ModelRenderer(this, 80, 12);
        this.SpiderLegD.setRotationPoint(-4.0F, 16.8F, 0.0F);
        this.SpiderLegD.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD, 0.0F, -0.39269908169872414F, 0.4118977034706618F);
        this.SpiderLegD3 = new ModelRenderer(this, 44, 28);
        this.SpiderLegD3.setRotationPoint(-22.1F, 2.4F, 0.0F);
        this.SpiderLegD3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD3, -0.1954768762233649F, 0.0F, -1.3962634015954636F);
        this.SpiderLegC3 = new ModelRenderer(this, 80, 28);
        this.SpiderLegC3.setRotationPoint(21.6F, 2.8F, -1.3F);
        this.SpiderLegC3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC3, -0.11519173063162574F, -0.17453292519943295F, 4.537856055185257F);
        this.SpiderLegF2 = new ModelRenderer(this, 80, 20);
        this.SpiderLegF2.setRotationPoint(-15.3F, -0.2F, 0.0F);
        this.SpiderLegF2.addBox(-7.4F, -0.7F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF2, 0.0F, 0.0F, -0.2530727415391778F);
        this.SpiderThorax = new ModelRenderer(this, 0, 52);
        this.SpiderThorax.setRotationPoint(0.0F, 16.8F, 0.3F);
        this.SpiderThorax.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.SpiderLegG3 = new ModelRenderer(this, 44, 40);
        this.SpiderLegG3.setRotationPoint(18.4F, 6.4F, 0.0F);
        this.SpiderLegG3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG3, -0.013962634015954637F, 0.0F, 1.7453292519943295F);
        this.SpiderLegA3 = new ModelRenderer(this, 44, 4);
        this.SpiderLegA3.setRotationPoint(19.7F, 1.2F, -4.8F);
        this.SpiderLegA3.addBox(0.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA3, 0.598647933434055F, 0.9337511498169663F, 1.1693705988362009F);
        this.SpiderLegH3 = new ModelRenderer(this, 80, 16);
        this.SpiderLegH3.setRotationPoint(-18.4F, 6.4F, 0.0F);
        this.SpiderLegH3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH3, 0.0F, 0.0F, -1.7453292519943295F);
        this.SpiderMandibleA2 = new ModelRenderer(this, 0, 48);
        this.SpiderMandibleA2.setRotationPoint(2.6F, 1.5F, -9.5F);
        this.SpiderMandibleA2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleA2, 0.0F, 2.0943951023931953F, 0.0F);
        this.SpiderMandibleB2 = new ModelRenderer(this, 11, 48);
        this.SpiderMandibleB2.setRotationPoint(-3.5F, 1.5F, -9.9F);
        this.SpiderMandibleB2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleB2, 0.0F, 1.0471975511965976F, 0.0F);
        this.SpiderAbdomen = new ModelRenderer(this, 0, 0);
        this.SpiderAbdomen.setRotationPoint(0.0F, 14.2F, 11.7F);
        this.SpiderAbdomen.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
        this.setRotateAngle(SpiderAbdomen, 0.10471975511965977F, 0.0F, 0.0F);
        this.SpiderLegE2 = new ModelRenderer(this, 80, 44);
        this.SpiderLegE2.setRotationPoint(14.9F, 0.1F, 0.0F);
        this.SpiderLegE2.addBox(-7.4F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE2, 0.0F, -3.2026791774095944F, 0.7295476273336298F);
        this.SpiderLegB = new ModelRenderer(this, 44, 12);
        this.SpiderLegB.setRotationPoint(-4.0F, 16.8F, -1.0F);
        this.SpiderLegB.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB, 0.0F, -0.7853981633974483F, 0.5567600313861911F);
        this.SpiderLegH2 = new ModelRenderer(this, 80, 8);
        this.SpiderLegH2.setRotationPoint(-14.5F, 0.0F, 0.0F);
        this.SpiderLegH2.addBox(-7.4F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH2, 0.0F, 0.0F, -0.9599310885968813F);
        this.SpiderLegG2 = new ModelRenderer(this, 44, 44);
        this.SpiderLegG2.setRotationPoint(14.7F, 0.4F, 0.0F);
        this.SpiderLegG2.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG2, 0.0F, 0.0F, 0.9599310885968813F);
        this.SpiderLegE = new ModelRenderer(this, 44, 24);
        this.SpiderLegE.setRotationPoint(4.0F, 16.8F, 1.0F);
        this.SpiderLegE.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE, 0.0F, -0.4223696789826278F, -0.5497787143782138F);
        this.SpiderLegF3 = new ModelRenderer(this, 80, 4);
        this.SpiderLegF3.setRotationPoint(-21.9F, 2.2F, 0.0F);
        this.SpiderLegF3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF3, 0.019198621771937624F, 0.0F, -1.3962634015954636F);
        this.SpiderMandibleB1 = new ModelRenderer(this, 11, 46);
        this.SpiderMandibleB1.setRotationPoint(-3.5F, 1.5F, -6.0F);
        this.SpiderMandibleB1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleB1, 0.0F, 1.5707963267948966F, 0.0F);
        this.SpiderLegG = new ModelRenderer(this, 44, 36);
        this.SpiderLegG.setRotationPoint(4.0F, 16.8F, 2.0F);
        this.SpiderLegG.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG, 0.9162978572970231F, -0.7853981633974483F, -1.0786134777324956F);
        this.SpiderLegA = new ModelRenderer(this, 44, 0);
        this.SpiderLegA.setRotationPoint(4.0F, 16.8F, -1.0F);
        this.SpiderLegA.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA, 0.0F, 0.890117918517108F, -0.7853981633974483F);
        this.SpiderLegD2 = new ModelRenderer(this, 80, 32);
        this.SpiderLegD2.setRotationPoint(-15.6F, -0.8F, -1.5F);
        this.SpiderLegD2.addBox(-7.4F, -0.3F, 0.6F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD2, 0.0F, 0.0F, -0.3141592653589793F);
        this.SpiderLegC = new ModelRenderer(this, 80, 0);
        this.SpiderLegC.setRotationPoint(4.0F, 16.8F, 0.0F);
        this.SpiderLegC.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC, 0.0F, 0.39269908169872414F, -0.45378560551852565F);
        this.SpiderMandibleA1 = new ModelRenderer(this, 0, 46);
        this.SpiderMandibleA1.setRotationPoint(2.5F, 1.5F, -6.0F);
        this.SpiderMandibleA1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleA1, 0.0F, 1.5707963267948966F, 0.0F);
        this.SpiderLegC2 = new ModelRenderer(this, 44, 32);
        this.SpiderLegC2.setRotationPoint(15.1F, 0.1F, 0.0F);
        this.SpiderLegC2.addBox(-7.4F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC2, -0.017453292519943295F, 3.3161255787892263F, 0.3490658503988659F);
        this.SpiderLegF = new ModelRenderer(this, 80, 24);
        this.SpiderLegF.setRotationPoint(-4.0F, 16.8F, 1.0F);
        this.SpiderLegF.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF, 0.0F, 0.39269908169872414F, 0.4084070449666731F);
        this.SpiderLegB2 = new ModelRenderer(this, 44, 20);
        this.SpiderLegB2.setRotationPoint(-15.0F, -0.6F, -0.2F);
        this.SpiderLegB2.addBox(-7.4F, -0.5F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB2, 0.0F, -0.7853981633974483F, -0.26354471705114374F);
        this.SpiderLegB3 = new ModelRenderer(this, 44, 16);
        this.SpiderLegB3.setRotationPoint(-20.0F, 1.4F, -5.4F);
        this.SpiderLegB3.addBox(-15.3F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB3, 0.6719517620178168F, -0.7853981633974483F, -1.117010721276371F);
        this.SpiderHead = new ModelRenderer(this, 0, 20);
        this.SpiderHead.setRotationPoint(0.0F, 16.8F, -4.0F);
        this.SpiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.SpiderLegA2 = new ModelRenderer(this, 44, 8);
        this.SpiderLegA2.setRotationPoint(14.8F, 0.0F, -0.1F);
        this.SpiderLegA2.addBox(-0.6F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA2, 0.0F, 0.7853981633974483F, 0.26354471705114374F);
        this.SpiderLegE3 = new ModelRenderer(this, 80, 40);
        this.SpiderLegE3.setRotationPoint(20.3F, 5.4F, 0.4F);
        this.SpiderLegE3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE3, 0.0F, 0.0F, 1.3962634015954636F);
        this.SpiderLegD.addChild(this.SpiderLegD3);
        this.SpiderLegC.addChild(this.SpiderLegC3);
        this.SpiderLegF.addChild(this.SpiderLegF2);
        this.SpiderLegG.addChild(this.SpiderLegG3);
        this.SpiderLegA.addChild(this.SpiderLegA3);
        this.SpiderLegH.addChild(this.SpiderLegH3);
        this.SpiderHead.addChild(this.SpiderMandibleA2);
        this.SpiderHead.addChild(this.SpiderMandibleB2);
        this.SpiderLegE.addChild(this.SpiderLegE2);
        this.SpiderLegH.addChild(this.SpiderLegH2);
        this.SpiderLegG.addChild(this.SpiderLegG2);
        this.SpiderLegF.addChild(this.SpiderLegF3);
        this.SpiderHead.addChild(this.SpiderMandibleB1);
        this.SpiderLegD.addChild(this.SpiderLegD2);
        this.SpiderHead.addChild(this.SpiderMandibleA1);
        this.SpiderLegC.addChild(this.SpiderLegC2);
        this.SpiderLegB.addChild(this.SpiderLegB2);
        this.SpiderLegB.addChild(this.SpiderLegB3);
        this.SpiderLegA.addChild(this.SpiderLegA2);
        this.SpiderLegE.addChild(this.SpiderLegE3);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.SpiderLegH.render(f5);
        this.SpiderLegD.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.SpiderThorax.offsetX, this.SpiderThorax.offsetY, this.SpiderThorax.offsetZ);
        GlStateManager.translate(this.SpiderThorax.rotationPointX * f5, this.SpiderThorax.rotationPointY * f5, this.SpiderThorax.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.0D, 1.4D);
        GlStateManager.translate(-this.SpiderThorax.offsetX, -this.SpiderThorax.offsetY, -this.SpiderThorax.offsetZ);
        GlStateManager.translate(-this.SpiderThorax.rotationPointX * f5, -this.SpiderThorax.rotationPointY * f5, -this.SpiderThorax.rotationPointZ * f5);
        this.SpiderThorax.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.SpiderAbdomen.offsetX, this.SpiderAbdomen.offsetY, this.SpiderAbdomen.offsetZ);
        GlStateManager.translate(this.SpiderAbdomen.rotationPointX * f5, this.SpiderAbdomen.rotationPointY * f5, this.SpiderAbdomen.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.2D, 1.3D);
        GlStateManager.translate(-this.SpiderAbdomen.offsetX, -this.SpiderAbdomen.offsetY, -this.SpiderAbdomen.offsetZ);
        GlStateManager.translate(-this.SpiderAbdomen.rotationPointX * f5, -this.SpiderAbdomen.rotationPointY * f5, -this.SpiderAbdomen.rotationPointZ * f5);
        this.SpiderAbdomen.render(f5);
        GlStateManager.popMatrix();
        this.SpiderLegB.render(f5);
        this.SpiderLegE.render(f5);
        this.SpiderLegG.render(f5);
        this.SpiderLegA.render(f5);
        this.SpiderLegC.render(f5);
        this.SpiderLegF.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.SpiderHead.offsetX, this.SpiderHead.offsetY, this.SpiderHead.offsetZ);
        GlStateManager.translate(this.SpiderHead.rotationPointX * f5, this.SpiderHead.rotationPointY * f5, this.SpiderHead.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.2D, 1.2D);
        GlStateManager.translate(-this.SpiderHead.offsetX, -this.SpiderHead.offsetY, -this.SpiderHead.offsetZ);
        GlStateManager.translate(-this.SpiderHead.rotationPointX * f5, -this.SpiderHead.rotationPointY * f5, -this.SpiderHead.rotationPointZ * f5);
        this.SpiderHead.render(f5);
        GlStateManager.popMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn){
        this.SpiderHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.SpiderHead.rotateAngleX = headPitch * 0.017453292F;

        this.SpiderLegA.rotateAngleZ = -((float)Math.PI / 4F);
        this.SpiderLegB.rotateAngleZ = ((float)Math.PI / 4F);
        this.SpiderLegC.rotateAngleZ = -0.58119464F;
        this.SpiderLegD.rotateAngleZ = 0.58119464F;
        this.SpiderLegE.rotateAngleZ = -0.58119464F;
        this.SpiderLegF.rotateAngleZ = 0.58119464F;
        this.SpiderLegG.rotateAngleZ = -((float)Math.PI / 4F);
        this.SpiderLegH.rotateAngleZ = ((float)Math.PI / 4F);
        float f1 = -0.0F;
        float f2 = 0.3926991F;
        this.SpiderLegA.rotateAngleY = ((float)Math.PI / 4F);
        this.SpiderLegB.rotateAngleY = -((float)Math.PI / 4F);
        this.SpiderLegC.rotateAngleY = 0.3926991F;
        this.SpiderLegD.rotateAngleY = -0.3926991F;
        this.SpiderLegE.rotateAngleY = -0.3926991F;
        this.SpiderLegF.rotateAngleY = 0.3926991F;
        this.SpiderLegG.rotateAngleY = -((float)Math.PI / 4F);
        this.SpiderLegH.rotateAngleY = ((float)Math.PI / 4F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        this.SpiderLegA.rotateAngleY += f3;
        this.SpiderLegB.rotateAngleY += -f3;
        this.SpiderLegC.rotateAngleY += f4;
        this.SpiderLegD.rotateAngleY += -f4;
        this.SpiderLegE.rotateAngleY += f5;
        this.SpiderLegF.rotateAngleY += -f5;
        this.SpiderLegG.rotateAngleY += f6;
        this.SpiderLegH.rotateAngleY += -f6;
        this.SpiderLegA.rotateAngleZ += f7;
        this.SpiderLegB.rotateAngleZ += -f7;
        this.SpiderLegC.rotateAngleZ += f8;
        this.SpiderLegD.rotateAngleZ += -f8;
        this.SpiderLegE.rotateAngleZ += f9;
        this.SpiderLegF.rotateAngleZ += -f9;
        this.SpiderLegG.rotateAngleZ += f10;
        this.SpiderLegH.rotateAngleZ += -f10;
    }
}