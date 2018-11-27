package com.greatorator.tolkienmobs.client.render.model.monster;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Huron - GreatOrator
 */
public class ModelHuron extends ModelTolkienMobs {
    public ModelRenderer HuronBody;
    public ModelRenderer HuronLegBR;
    public ModelRenderer HuronLegFR;
    public ModelRenderer HuronLegFL;
    public ModelRenderer HuronHead;
    public ModelRenderer HuronLegBL;
    public ModelRenderer HuronArmR;
    public ModelRenderer HuronArmL;
    public ModelRenderer HuronHat;
    public ModelRenderer HuronHatTop;
    public ModelRenderer HuronNose;

    public ModelHuron() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.HuronHatTop = new ModelRenderer(this, 84, 16);
        this.HuronHatTop.setRotationPoint(-4.0F, -12.0F, -4.0F);
        this.HuronHatTop.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
        this.HuronLegBR = new ModelRenderer(this, 24, 0);
        this.HuronLegBR.setRotationPoint(3.0F, 18.0F, 4.0F);
        this.HuronLegBR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronHead = new ModelRenderer(this, 72, 0);
        this.HuronHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HuronHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.HuronNose = new ModelRenderer(this, 22, 0);
        this.HuronNose.setRotationPoint(-0.7F, -3.2F, -4.9F);
        this.HuronNose.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.HuronLegFR = new ModelRenderer(this, 40, 0);
        this.HuronLegFR.setRotationPoint(3.0F, 18.0F, -4.0F);
        this.HuronLegFR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronLegFL = new ModelRenderer(this, 56, 0);
        this.HuronLegFL.setRotationPoint(-3.0F, 18.0F, -4.0F);
        this.HuronLegFL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronArmR = new ModelRenderer(this, 40, 10);
        this.HuronArmR.setRotationPoint(4.0F, -0.1F, -2.0F);
        this.HuronArmR.addBox(0.0F, 0.0F, 0.0F, 4, 14, 4, 0.0F);
        this.HuronArmL = new ModelRenderer(this, 24, 10);
        this.HuronArmL.setRotationPoint(-8.0F, -0.1F, -2.0F);
        this.HuronArmL.addBox(0.0F, 0.0F, 0.0F, 4, 14, 4, 0.0F);
        this.HuronHat = new ModelRenderer(this, 42, 16);
        this.HuronHat.setRotationPoint(-7.0F, -9.5F, -7.0F);
        this.HuronHat.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
        this.HuronLegBL = new ModelRenderer(this, 104, 0);
        this.HuronLegBL.setRotationPoint(-3.0F, 18.0F, 4.0F);
        this.HuronLegBL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronBody = new ModelRenderer(this, 0, 0);
        this.HuronBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HuronBody.addBox(-4.0F, 0.0F, -2.0F, 8, 18, 4, 0.0F);
        this.HuronHead.addChild(this.HuronNose);
        this.HuronHead.addChild(this.HuronHat);
        this.HuronHead.addChild(this.HuronHatTop);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float scaleFactor = 1.8F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        this.HuronLegBR.render(f5);
        this.HuronHead.render(f5);
        this.HuronLegFR.render(f5);
        this.HuronLegFL.render(f5);
        this.HuronArmR.render(f5);
        this.HuronArmL.render(f5);
        this.HuronLegBL.render(f5);
        this.HuronBody.render(f5);

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
        this.HuronLegFL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HuronLegBL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HuronLegFR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.HuronLegBR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.HuronArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HuronArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.HuronHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.HuronHead.rotateAngleX = headPitch * 0.017453292F;
    }
}
