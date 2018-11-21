package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Cave Troll - GreatOrator
 */
public class ModelTroll extends ModelTolkienMobs {
    public ModelRenderer TrollBody;
    public ModelRenderer TrollBodyLower;
    public ModelRenderer TrollLegR;
    public ModelRenderer TrollLegL;
    public ModelRenderer TrollNeck;
    public ModelRenderer TrollArmL;
    public ModelRenderer TrollArmR;
    public ModelRenderer TrollLegMidR;
    public ModelRenderer TrollFootR;
    public ModelRenderer TrollNail1;
    public ModelRenderer TrollNail2;
    public ModelRenderer TrollLegMidL;
    public ModelRenderer TrollFootL;
    public ModelRenderer TrollNail3;
    public ModelRenderer TrollNail4;
    public ModelRenderer TrollHead;
    public ModelRenderer TrollNose;
    public ModelRenderer TrollArmLowerL;
    public ModelRenderer TrollArmLowerR;

    public ModelTroll() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.TrollArmLowerL = new ModelRenderer(this, 24, 46);
        this.TrollArmLowerL.mirror = true;
        this.TrollArmLowerL.setRotationPoint(3.0F, 8.0F, 0.0F);
        this.TrollArmLowerL.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(TrollArmLowerL, -0.08726646259971647F, 0.0F, 0.0F);
        this.TrollLegL = new ModelRenderer(this, 0, 35);
        this.TrollLegL.setRotationPoint(5.2F, 9.5F, -0.5F);
        this.TrollLegL.addBox(-2.5F, 0.0F, -2.5F, 6, 7, 6, 0.0F);
        this.setRotateAngle(TrollLegL, -0.08726646259971647F, 0.0F, 0.0F);
        this.TrollLegMidR = new ModelRenderer(this, 0, 25);
        this.TrollLegMidR.setRotationPoint(0.0F, 6.5F, 0.0F);
        this.TrollLegMidR.addBox(-2.0F, 0.0F, -2.0F, 5, 6, 5, 0.0F);
        this.setRotateAngle(TrollLegMidR, 0.08726646259971647F, 0.0F, 0.0F);
        this.TrollNail1 = new ModelRenderer(this, 19, 35);
        this.TrollNail1.setRotationPoint(-2.3F, 1.5F, -5.0F);
        this.TrollNail1.addBox(-0.5F, -0.5F, -0.5F, 2, 2, 2, 0.0F);
        this.setRotateAngle(TrollNail1, 0.0F, -0.00767944870877505F, 0.0F);
        this.TrollArmLowerR = new ModelRenderer(this, 24, 46);
        this.TrollArmLowerR.setRotationPoint(-3.0F, 8.0F, 0.0F);
        this.TrollArmLowerR.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(TrollArmLowerR, -0.08726646259971647F, 0.0F, 0.0F);
        this.TrollFootR = new ModelRenderer(this, 24, 36);
        this.TrollFootR.setRotationPoint(0.5F, 6.0F, 1.5F);
        this.TrollFootR.addBox(-2.5F, 0.0F, -5.0F, 5, 3, 7, 0.0F);
        this.TrollNeck = new ModelRenderer(this, 48, 14);
        this.TrollNeck.setRotationPoint(0.0F, -11.5F, -1.0F);
        this.TrollNeck.addBox(-3.5F, -4.5F, -3.0F, 7, 5, 6, 0.0F);
        this.setRotateAngle(TrollNeck, -0.17453292519943295F, 0.0F, 0.0F);
        this.TrollNail4 = new ModelRenderer(this, 19, 35);
        this.TrollNail4.setRotationPoint(1.3F, 1.5F, -5.0F);
        this.TrollNail4.addBox(-0.5F, -0.5F, -0.5F, 2, 2, 2, 0.0F);
        this.TrollLegR = new ModelRenderer(this, 0, 35);
        this.TrollLegR.setRotationPoint(-6.2F, 9.5F, -0.5F);
        this.TrollLegR.addBox(-2.5F, 0.0F, -2.5F, 6, 7, 6, 0.0F);
        this.setRotateAngle(TrollLegR, -0.08726646259971647F, 0.0F, 0.0F);
        this.TrollNail2 = new ModelRenderer(this, 19, 35);
        this.TrollNail2.setRotationPoint(1.3F, 1.5F, -5.0F);
        this.TrollNail2.addBox(-0.5F, -0.5F, -0.5F, 2, 2, 2, 0.0F);
        this.TrollLegMidL = new ModelRenderer(this, 0, 25);
        this.TrollLegMidL.setRotationPoint(0.0F, 6.5F, 0.0F);
        this.TrollLegMidL.addBox(-2.0F, 0.0F, -2.0F, 5, 6, 5, 0.0F);
        this.setRotateAngle(TrollLegMidL, 0.08726646259971647F, 0.0F, 0.0F);
        this.TrollBody = new ModelRenderer(this, 77, 0);
        this.TrollBody.setRotationPoint(0.0F, -1.0F, -0.2F);
        this.TrollBody.addBox(-9.0F, -12.0F, -3.5F, 18, 12, 7, 0.0F);
        this.TrollNose = new ModelRenderer(this, 0, 32);
        this.TrollNose.setRotationPoint(0.0F, 0.7F, -8.5F);
        this.TrollNose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(TrollNose, 0.17453292519943295F, 0.0F, 0.0F);
        this.TrollHead = new ModelRenderer(this, 47, 0);
        this.TrollHead.setRotationPoint(0.0F, -1.5F, -2.2F);
        this.TrollHead.addBox(-4.5F, -3.5F, -6.0F, 9, 8, 6, 0.0F);
        this.setRotateAngle(TrollHead, 0.17453292519943295F, 0.0F, 0.0F);
        this.TrollBodyLower = new ModelRenderer(this, 81, 19);
        this.TrollBodyLower.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.TrollBodyLower.addBox(-8.5F, 0.0F, -3.0F, 17, 11, 6, 0.0F);
        this.TrollNail3 = new ModelRenderer(this, 19, 35);
        this.TrollNail3.setRotationPoint(-2.3F, 1.5F, -5.0F);
        this.TrollNail3.addBox(-0.5F, -0.5F, -0.5F, 2, 2, 2, 0.0F);
        this.setRotateAngle(TrollNail3, 0.0F, -0.00767944870877505F, 0.0F);
        this.TrollArmL = new ModelRenderer(this, 0, 47);
        this.TrollArmL.mirror = true;
        this.TrollArmL.setRotationPoint(8.8F, -10.0F, 0.0F);
        this.TrollArmL.addBox(0.0F, -2.5F, -3.0F, 6, 11, 6, 0.0F);
        this.TrollFootL = new ModelRenderer(this, 24, 36);
        this.TrollFootL.setRotationPoint(0.5F, 6.0F, 1.5F);
        this.TrollFootL.addBox(-2.5F, 0.0F, -5.0F, 5, 3, 7, 0.0F);
        this.TrollArmR = new ModelRenderer(this, 0, 47);
        this.TrollArmR.setRotationPoint(-8.8F, -10.0F, 0.0F);
        this.TrollArmR.addBox(-6.0F, -2.5F, -3.0F, 6, 11, 6, 0.0F);
        this.TrollArmL.addChild(this.TrollArmLowerL);
        this.TrollBody.addChild(this.TrollLegL);
        this.TrollLegR.addChild(this.TrollLegMidR);
        this.TrollFootR.addChild(this.TrollNail1);
        this.TrollArmR.addChild(this.TrollArmLowerR);
        this.TrollLegMidR.addChild(this.TrollFootR);
        this.TrollBody.addChild(this.TrollNeck);
        this.TrollFootL.addChild(this.TrollNail4);
        this.TrollBody.addChild(this.TrollLegR);
        this.TrollFootR.addChild(this.TrollNail2);
        this.TrollLegL.addChild(this.TrollLegMidL);
        this.TrollNeck.addChild(this.TrollNose);
        this.TrollNeck.addChild(this.TrollHead);
        this.TrollBody.addChild(this.TrollBodyLower);
        this.TrollFootL.addChild(this.TrollNail3);
        this.TrollBody.addChild(this.TrollArmL);
        this.TrollLegMidL.addChild(this.TrollFootL);
        this.TrollBody.addChild(this.TrollArmR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 2.0F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        this.TrollBody.render(f5);

        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.TrollLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.TrollLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.TrollArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.TrollArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.TrollNeck.rotateAngleY = netHeadYaw * 0.017453292F;
        this.TrollNeck.rotateAngleX = headPitch * 0.017453292F;
    }
}
