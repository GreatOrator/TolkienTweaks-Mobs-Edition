package com.greatorator.tolkienmobs.client.render.model.passive;

import com.greatorator.tolkienmobs.entity.passive.EntityTMFrog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * Frog - GreatOrator
 */
public class ModelTMFrog extends ModelBase {
    public ModelRenderer frogHead;
    public ModelRenderer frogFootLeft;
    public ModelRenderer frogLeftThigh;
    public ModelRenderer frogBody;
    public ModelRenderer frogRightArm;
    public ModelRenderer frogFootRight;
    public ModelRenderer frogRightThigh;
    public ModelRenderer frogLeftArm;
    public ModelRenderer frogEyeRight;
    public ModelRenderer frogEyeLeft;
    private float jumpRotation;

    public ModelTMFrog() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.frogBody = new ModelRenderer(this, 0, 0);
        this.frogBody.setRotationPoint(0.0F, 19.0F, 8.0F);
        this.frogBody.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10, 0.0F);
        this.setRotateAngle(frogBody, -0.3490658503988659F, 0.0F, 0.0F);
        this.frogFootLeft = new ModelRenderer(this, 10, 26);
        this.frogFootLeft.mirror = true;
        this.frogFootLeft.setRotationPoint(3.0F, 17.5F, 5.7F);
        this.frogFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 5, 0.0F);
        this.frogFootRight = new ModelRenderer(this, 10, 26);
        this.frogFootRight.setRotationPoint(-3.0F, 17.5F, 5.7F);
        this.frogFootRight.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 5, 0.0F);
        this.frogRightArm = new ModelRenderer(this, 0, 15);
        this.frogRightArm.setRotationPoint(-3.0F, 17.0F, -1.0F);
        this.frogRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(frogRightArm, -0.19198621771937624F, 0.0F, 0.0F);
        this.frogHead = new ModelRenderer(this, 32, 0);
        this.frogHead.setRotationPoint(0.0F, 17.0F, -1.0F);
        this.frogHead.addBox(-2.5F, -4.0F, -5.0F, 5, 3, 5, 0.0F);
        this.frogRightThigh = new ModelRenderer(this, 16, 15);
        this.frogRightThigh.setRotationPoint(-3.0F, 17.5F, 3.7F);
        this.frogRightThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(frogRightThigh, -0.3665191429188092F, 0.0F, 0.0F);
        this.frogLeftArm = new ModelRenderer(this, 0, 15);
        this.frogLeftArm.mirror = true;
        this.frogLeftArm.setRotationPoint(3.0F, 17.0F, -1.0F);
        this.frogLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(frogLeftArm, -0.19198621771937624F, 0.0F, 0.0F);
        this.frogEyeRight = new ModelRenderer(this, 0, 0);
        this.frogEyeRight.setRotationPoint(-2.1F, -3.0F, -2.0F);
        this.frogEyeRight.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.frogLeftThigh = new ModelRenderer(this, 16, 15);
        this.frogLeftThigh.mirror = true;
        this.frogLeftThigh.setRotationPoint(3.0F, 17.5F, 3.7F);
        this.frogLeftThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(frogLeftThigh, -0.3665191429188092F, 0.0F, 0.0F);
        this.frogEyeLeft = new ModelRenderer(this, 0, 0);
        this.frogEyeLeft.mirror = true;
        this.frogEyeLeft.setRotationPoint(2.1F, -3.0F, -2.0F);
        this.frogEyeLeft.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.frogHead.addChild(this.frogEyeRight);
        this.frogHead.addChild(this.frogEyeLeft);
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        float scaleFactor = 0.5F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

        if (this.isChild)
        {
            float f = 1.5F;
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.56666666F, 0.56666666F, 0.56666666F);
            GlStateManager.translate(0.0F, 22.0F * scale, 2.0F * scale);
            this.frogHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
            GlStateManager.translate(0.0F, 36.0F * scale, 0.0F);
            this.frogFootLeft.render(scale);
            this.frogFootRight.render(scale);
            this.frogLeftThigh.render(scale);
            this.frogRightThigh.render(scale);
            this.frogBody.render(scale);
            this.frogLeftArm.render(scale);
            this.frogRightArm.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            this.frogFootLeft.render(scale);
            this.frogFootRight.render(scale);
            this.frogLeftThigh.render(scale);
            this.frogRightThigh.render(scale);
            this.frogBody.render(scale);
            this.frogLeftArm.render(scale);
            this.frogRightArm.render(scale);
            this.frogHead.render(scale);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float f = ageInTicks - (float)entityIn.ticksExisted;
        EntityTMFrog entitytmfrog = (EntityTMFrog)entityIn;
        this.frogHead.rotateAngleX = headPitch * 0.017453292F;
        this.frogHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.jumpRotation = MathHelper.sin(entitytmfrog.setJumpCompletion(f) * (float)Math.PI);
        this.frogLeftThigh.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * 0.017453292F;
        this.frogRightThigh.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * 0.017453292F;
        this.frogFootLeft.rotateAngleX = this.jumpRotation * 50.0F * 0.017453292F;
        this.frogFootRight.rotateAngleX = this.jumpRotation * 50.0F * 0.017453292F;
        this.frogLeftArm.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * 0.017453292F;
        this.frogRightArm.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * 0.017453292F;
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.jumpRotation = MathHelper.sin(((EntityTMFrog)entitylivingbaseIn).setJumpCompletion(partialTickTime) * (float)Math.PI);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}