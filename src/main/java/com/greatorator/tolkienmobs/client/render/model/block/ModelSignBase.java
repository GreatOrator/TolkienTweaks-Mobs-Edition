package com.greatorator.tolkienmobs.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Sign Base - GreatOrator
 */
public class ModelSignBase extends ModelBase {
    public ModelRenderer SignBase;
    public ModelRenderer SignChain;
    public ModelRenderer SignFrame;
    public ModelRenderer SignFrame_1;
    public ModelRenderer SignFrame_2;
    public ModelRenderer SignFrame_3;
    public ModelRenderer SignChain_1;

    public ModelSignBase() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.SignChain = new ModelRenderer(this, 40, 0);
        this.SignChain.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.SignChain.addBox(6.0F, -7.0F, 0.0F, 1, 7, 0, 0.0F);
        this.SignFrame = new ModelRenderer(this, 0, 9);
        this.SignFrame.setRotationPoint(0.0F, -0.5F, -0.5F);
        this.SignFrame.addBox(-8.0F, -4.0F, -0.5F, 16, 1, 2, 0.0F);
        this.SignFrame_3 = new ModelRenderer(this, 34, 0);
        this.SignFrame_3.setRotationPoint(-0.9F, -0.5F, -0.5F);
        this.SignFrame_3.addBox(8.0F, -4.0F, -0.5F, 1, 9, 2, 0.0F);
        this.SignBase = new ModelRenderer(this, 0, 0);
        this.SignBase.setRotationPoint(0.0F, 19.5F, 0.0F);
        this.SignBase.addBox(-7.5F, -4.0F, -0.5F, 15, 8, 1, 0.0F);
        this.SignFrame_1 = new ModelRenderer(this, 0, 9);
        this.SignFrame_1.setRotationPoint(0.0F, -0.5F, -0.5F);
        this.SignFrame_1.addBox(-8.0F, 4.0F, -0.5F, 16, 1, 2, 0.0F);
        this.SignFrame_2 = new ModelRenderer(this, 34, 0);
        this.SignFrame_2.setRotationPoint(-0.1F, -0.5F, -0.5F);
        this.SignFrame_2.addBox(-8.0F, -4.0F, -0.5F, 1, 9, 2, 0.0F);
        this.SignChain_1 = new ModelRenderer(this, 40, 0);
        this.SignChain_1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.SignChain_1.addBox(-6.0F, -7.0F, 0.0F, 1, 7, 0, 0.0F);
        this.SignBase.addChild(this.SignFrame);
        this.SignBase.addChild(this.SignFrame_3);
        this.SignBase.addChild(this.SignFrame_1);
        this.SignBase.addChild(this.SignFrame_2);
        this.SignChain.addChild(this.SignChain_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.SignChain.render(f5);
        this.SignBase.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
