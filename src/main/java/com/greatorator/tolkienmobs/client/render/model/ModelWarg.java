package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * ModelWarg - GreatOrator
 */
public class ModelWarg extends ModelBase {
    public ModelRenderer WargLegRF;
    public ModelRenderer WargChest;
    public ModelRenderer WargBody;
    public ModelRenderer WargLegRR;
    public ModelRenderer WargLegLR;
    public ModelRenderer WargLegLF;
    public ModelRenderer WargTail;
    public ModelRenderer WargHead;
    public ModelRenderer WargEarL;
    public ModelRenderer WargEarR;
    public ModelRenderer WargSnout;

    public ModelWarg() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.WargTail = new ModelRenderer(this, 22, 14);
        this.WargTail.setRotationPoint(-0.5F, 12.0F, 10.0F);
        this.WargTail.addBox(0.0F, 0.0F, -1.0F, 1, 7, 1, 0.0F);
        this.WargLegRF = new ModelRenderer(this, 12, 0);
        this.WargLegRF.setRotationPoint(3.5F, 14.0F, -6.5F);
        this.WargLegRF.addBox(0.0F, 0.0F, -1.0F, 3, 10, 3, 0.0F);
        this.WargLegRR = new ModelRenderer(this, 0, 0);
        this.WargLegRR.setRotationPoint(2.5F, 15.0F, 7.0F);
        this.WargLegRR.addBox(0.0F, 0.0F, -1.0F, 3, 9, 3, 0.0F);
        this.WargHead = new ModelRenderer(this, 0, 19);
        this.WargHead.setRotationPoint(-2.0F, 13.0F, -10.5F);
        this.WargHead.addBox(-2.0F, -3.0F, -2.0F, 8, 7, 4, 0.0F);
        this.WargEarL = new ModelRenderer(this, 1, 50);
        this.WargEarL.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.WargEarL.addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(WargEarL, 0.0F, 0.5235987755982988F, 0.0F);
        this.WargSnout = new ModelRenderer(this, 24, 0);
        this.WargSnout.setRotationPoint(0.5F, -0.5F, -1.0F);
        this.WargSnout.addBox(-0.5F, 0.0F, -5.0F, 4, 4, 5, 0.0F);
        this.WargChest = new ModelRenderer(this, 0, 47);
        this.WargChest.setRotationPoint(-1.0F, 14.0F, -5.5F);
        this.WargChest.addBox(-5.0F, -3.0F, -3.0F, 12, 9, 8, 0.0F);
        this.setRotateAngle(WargChest, 1.5707963267948966F, 0.0F, 0.0F);
        this.WargBody = new ModelRenderer(this, 0, 30);
        this.WargBody.setRotationPoint(-1.0F, 14.0F, 2.5F);
        this.WargBody.addBox(-4.0F, -2.0F, -3.0F, 10, 10, 7, 0.0F);
        this.setRotateAngle(WargBody, 1.5707963267948966F, 0.0F, 0.0F);
        this.WargLegLR = new ModelRenderer(this, 0, 0);
        this.WargLegLR.setRotationPoint(-5.5F, 15.0F, 7.0F);
        this.WargLegLR.addBox(0.0F, 0.0F, -1.0F, 3, 9, 3, 0.0F);
        this.WargEarR = new ModelRenderer(this, 1, 50);
        this.WargEarR.setRotationPoint(2.2F, 0.0F, -2.5F);
        this.WargEarR.addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(WargEarR, 0.0F, -0.5235987755982988F, 0.0F);
        this.WargLegLF = new ModelRenderer(this, 12, 0);
        this.WargLegLF.setRotationPoint(-6.5F, 14.0F, -6.5F);
        this.WargLegLF.addBox(0.0F, 0.0F, -1.0F, 3, 10, 3, 0.0F);
        this.WargHead.addChild(this.WargEarL);
        this.WargHead.addChild(this.WargSnout);
        this.WargHead.addChild(this.WargEarR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.WargTail.render(f5);
        this.WargLegRF.render(f5);
        this.WargLegRR.render(f5);
        this.WargHead.render(f5);
        this.WargChest.render(f5);
        this.WargBody.render(f5);
        this.WargLegLR.render(f5);
        this.WargLegLF.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.WargTail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.WargBody.setRotationPoint(-1.0F, 14.0F, 2.5F);
        this.WargBody.rotateAngleX = ((float)Math.PI / 2F);
        this.WargChest.setRotationPoint(-1.0F, 14.0F, -5.5F);
        this.WargChest.rotateAngleX = this.WargBody.rotateAngleX;
        this.WargTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.WargLegRF.setRotationPoint(3.5F, 14.0F, -6.5F);
        this.WargLegRR.setRotationPoint(2.5F, 15.0F, 7.0F);
        this.WargLegLR.setRotationPoint(-5.5F, 15.0F, 7.0F);
        this.WargLegLF.setRotationPoint(-6.5F, 14.0F, -6.5F);
        this.WargLegRF.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.WargLegLF.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.WargLegLR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.WargLegRR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.WargHead.rotateAngleX = headPitch * 0.017453292F;
        this.WargHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.WargTail.rotateAngleX = ageInTicks;
    }
}
