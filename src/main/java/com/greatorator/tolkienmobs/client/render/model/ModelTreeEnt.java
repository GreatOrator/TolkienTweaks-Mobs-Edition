package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Huron - GreatOrator
 */
public class ModelTreeEnt extends ModelTolkienMobs {
    public ModelRenderer EntBody;
    public ModelRenderer EntLegBR;
    public ModelRenderer EntLegFR;
    public ModelRenderer EntLegFL;
    public ModelRenderer EntHead;
    public ModelRenderer EntLegBL;
    public ModelRenderer EntArmR;
    public ModelRenderer EntArmL;
    public ModelRenderer EntHat;
    public ModelRenderer EntHatTop;
    public ModelRenderer EntNose;

    public ModelTreeEnt() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.EntHatTop = new ModelRenderer(this, 84, 16);
        this.EntHatTop.setRotationPoint(-4.0F, -12.0F, -4.0F);
        this.EntHatTop.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
        this.EntLegBR = new ModelRenderer(this, 24, 0);
        this.EntLegBR.setRotationPoint(3.0F, 18.0F, 4.0F);
        this.EntLegBR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.EntHead = new ModelRenderer(this, 72, 0);
        this.EntHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.EntHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.EntNose = new ModelRenderer(this, 22, 0);
        this.EntNose.setRotationPoint(-0.7F, -3.2F, -4.9F);
        this.EntNose.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.EntLegFR = new ModelRenderer(this, 40, 0);
        this.EntLegFR.setRotationPoint(3.0F, 18.0F, -4.0F);
        this.EntLegFR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.EntLegFL = new ModelRenderer(this, 56, 0);
        this.EntLegFL.setRotationPoint(-3.0F, 18.0F, -4.0F);
        this.EntLegFL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.EntArmR = new ModelRenderer(this, 40, 10);
        this.EntArmR.setRotationPoint(4.0F, -0.1F, -2.0F);
        this.EntArmR.addBox(0.0F, 0.0F, 0.0F, 4, 14, 4, 0.0F);
        this.EntArmL = new ModelRenderer(this, 24, 10);
        this.EntArmL.setRotationPoint(-8.0F, -0.1F, -2.0F);
        this.EntArmL.addBox(0.0F, 0.0F, 0.0F, 4, 14, 4, 0.0F);
        this.EntHat = new ModelRenderer(this, 42, 16);
        this.EntHat.setRotationPoint(-7.0F, -9.5F, -7.0F);
        this.EntHat.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
        this.EntLegBL = new ModelRenderer(this, 104, 0);
        this.EntLegBL.setRotationPoint(-3.0F, 18.0F, 4.0F);
        this.EntLegBL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.EntBody = new ModelRenderer(this, 0, 0);
        this.EntBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.EntBody.addBox(-4.0F, 0.0F, -2.0F, 8, 18, 4, 0.0F);
        this.EntHead.addChild(this.EntNose);
        this.EntHead.addChild(this.EntHat);
        this.EntHead.addChild(this.EntHatTop);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 1.8F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        this.EntLegBR.render(f5);
        this.EntHead.render(f5);
        this.EntLegFR.render(f5);
        this.EntLegFL.render(f5);
        this.EntArmR.render(f5);
        this.EntArmL.render(f5);
        this.EntLegBL.render(f5);
        this.EntBody.render(f5);

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
        this.EntLegFL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.EntLegBL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.EntLegFR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.EntLegBR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.EntArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.EntArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.EntHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.EntHead.rotateAngleX = headPitch * 0.017453292F;
    }
}
