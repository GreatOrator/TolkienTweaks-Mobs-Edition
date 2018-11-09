package com.greatorator.tolkienmobs.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * com.greatorator.tolkienmobs.entity.model.ModelMirkwoodSpider - GreatOrator
 */
public class ModelMirkwoodSpider extends ModelBase {
    public ModelRenderer SpiderThorax;
    public ModelRenderer SpiderLegH;
    public ModelRenderer SpiderLegD;
    public ModelRenderer SpiderLegB;
    public ModelRenderer SpiderHead;
    public ModelRenderer SpiderAbdomen;
    public ModelRenderer SpiderLegG;
    public ModelRenderer SpiderLegE;
    public ModelRenderer SpiderLegC;
    public ModelRenderer SpiderLegA;
    public ModelRenderer SpiderLegF;
    public ModelRenderer SpiderMandibleA1;
    public ModelRenderer SpiderMandibleA2;
    public ModelRenderer SpiderMandibleB1;
    public ModelRenderer SpiderMandibleB2;

    public ModelMirkwoodSpider() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.SpiderLegB = new ModelRenderer(this, 24, 52);
        this.SpiderLegB.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.SpiderLegB.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
        this.SpiderMandibleB2 = new ModelRenderer(this, 11, 46);
        this.SpiderMandibleB2.setRotationPoint(-4.0F, 18.0F, -15.6F);
        this.SpiderMandibleB2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleB2, 0.0F, 1.0471975511965976F, 0.0F);
        this.SpiderLegC = new ModelRenderer(this, 61, 56);
        this.SpiderLegC.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.SpiderLegC.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC, 0.0F, 0.39269908169872414F, 0.5811946409141118F);
        this.SpiderMandibleB1 = new ModelRenderer(this, 11, 48);
        this.SpiderMandibleB1.setRotationPoint(-4.0F, 18.0F, -11.6F);
        this.SpiderMandibleB1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleB1, 0.0F, 1.5707963267948966F, 0.0F);
        this.SpiderMandibleA1 = new ModelRenderer(this, 0, 48);
        this.SpiderMandibleA1.setRotationPoint(3.0F, 18.0F, -11.6F);
        this.SpiderMandibleA1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleA1, 0.0F, 1.5707963267948966F, 0.0F);
        this.SpiderThorax = new ModelRenderer(this, 0, 52);
        this.SpiderThorax.setRotationPoint(0.0F, 14.8F, 0.3F);
        this.SpiderThorax.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.SpiderHead = new ModelRenderer(this, 73, 16);
        this.SpiderHead.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.SpiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.SpiderLegH = new ModelRenderer(this, 24, 60);
        this.SpiderLegH.setRotationPoint(-4.0F, 15.0F, 2.0F);
        this.SpiderLegH.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH, 0.0F, 0.7853981633974483F, -0.7853981633974483F);
        this.SpiderMandibleA2 = new ModelRenderer(this, 0, 46);
        this.SpiderMandibleA2.setRotationPoint(3.0F, 18.0F, -15.6F);
        this.SpiderMandibleA2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleA2, 0.0F, 2.0943951023931953F, 0.0F);
        this.SpiderAbdomen = new ModelRenderer(this, 0, 12);
        this.SpiderAbdomen.setRotationPoint(0.0F, 12.0F, 12.0F);
        this.SpiderAbdomen.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
        this.setRotateAngle(SpiderAbdomen, 0.10471975511965977F, 0.0F, 0.0F);
        this.SpiderLegG = new ModelRenderer(this, 24, 48);
        this.SpiderLegG.setRotationPoint(4.0F, 15.0F, 2.0F);
        this.SpiderLegG.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG, 0.0F, -0.7853981633974483F, 0.7853981633974483F);
        this.SpiderLegD = new ModelRenderer(this, 24, 56);
        this.SpiderLegD.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.SpiderLegD.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD, 0.0F, -0.39269908169872414F, -0.5811946409141118F);
        this.SpiderLegF = new ModelRenderer(this, 61, 48);
        this.SpiderLegF.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.SpiderLegF.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF, 0.0F, 0.39269908169872414F, -0.5811946409141118F);
        this.SpiderLegE = new ModelRenderer(this, 61, 60);
        this.SpiderLegE.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.SpiderLegE.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE, 0.0F, -0.39269908169872414F, 0.5811946409141118F);
        this.SpiderLegA = new ModelRenderer(this, 61, 52);
        this.SpiderLegA.setRotationPoint(4.0F, 15.0F, -1.0F);
        this.SpiderLegA.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
        this.SpiderHead.addChild(this.SpiderMandibleA1);
        this.SpiderMandibleA1.addChild(this.SpiderMandibleA2);
        this.SpiderHead.addChild(this.SpiderMandibleB1);
        this.SpiderMandibleB1.addChild(this.SpiderMandibleB2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.SpiderLegB.render(f5);
        this.SpiderMandibleB2.render(f5);
        this.SpiderLegC.render(f5);
        this.SpiderMandibleB1.render(f5);
        this.SpiderMandibleA1.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.SpiderThorax.offsetX, this.SpiderThorax.offsetY, this.SpiderThorax.offsetZ);
        GlStateManager.translate(this.SpiderThorax.rotationPointX * f5, this.SpiderThorax.rotationPointY * f5, this.SpiderThorax.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.0D, 1.4D);
        GlStateManager.translate(-this.SpiderThorax.offsetX, -this.SpiderThorax.offsetY, -this.SpiderThorax.offsetZ);
        GlStateManager.translate(-this.SpiderThorax.rotationPointX * f5, -this.SpiderThorax.rotationPointY * f5, -this.SpiderThorax.rotationPointZ * f5);
        this.SpiderThorax.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.SpiderHead.offsetX, this.SpiderHead.offsetY, this.SpiderHead.offsetZ);
        GlStateManager.translate(this.SpiderHead.rotationPointX * f5, this.SpiderHead.rotationPointY * f5, this.SpiderHead.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.2D, 1.2D);
        GlStateManager.translate(-this.SpiderHead.offsetX, -this.SpiderHead.offsetY, -this.SpiderHead.offsetZ);
        GlStateManager.translate(-this.SpiderHead.rotationPointX * f5, -this.SpiderHead.rotationPointY * f5, -this.SpiderHead.rotationPointZ * f5);
        this.SpiderHead.render(f5);
        GlStateManager.popMatrix();
        this.SpiderLegH.render(f5);
        this.SpiderMandibleA2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.SpiderAbdomen.offsetX, this.SpiderAbdomen.offsetY, this.SpiderAbdomen.offsetZ);
        GlStateManager.translate(this.SpiderAbdomen.rotationPointX * f5, this.SpiderAbdomen.rotationPointY * f5, this.SpiderAbdomen.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.2D, 1.3D);
        GlStateManager.translate(-this.SpiderAbdomen.offsetX, -this.SpiderAbdomen.offsetY, -this.SpiderAbdomen.offsetZ);
        GlStateManager.translate(-this.SpiderAbdomen.rotationPointX * f5, -this.SpiderAbdomen.rotationPointY * f5, -this.SpiderAbdomen.rotationPointZ * f5);
        this.SpiderAbdomen.render(f5);
        GlStateManager.popMatrix();
        this.SpiderLegG.render(f5);
        this.SpiderLegD.render(f5);
        this.SpiderLegF.render(f5);
        this.SpiderLegE.render(f5);
        this.SpiderLegA.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
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