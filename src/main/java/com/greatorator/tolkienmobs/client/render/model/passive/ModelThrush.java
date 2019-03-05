package com.greatorator.tolkienmobs.client.render.model.passive;

import com.greatorator.tolkienmobs.client.render.model.ModelTTM;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/* Thrush - GreatOrator */
public class ModelThrush extends ModelTTM {
    public ModelRenderer ThrushBody;
    public ModelRenderer ThrushTail1;
    public ModelRenderer ThrushLegL1;
    public ModelRenderer ThrushLegR1;
    public ModelRenderer ThrushHead;
    public ModelRenderer ThrushWingL1;
    public ModelRenderer ThrushWingR1;
    public ModelRenderer ThrushTail2;
    public ModelRenderer ThrushTail3;
    public ModelRenderer ThrushLegL2;
    public ModelRenderer ThrushLegR2;
    public ModelRenderer ThrushBeakTop;
    public ModelRenderer ThrushBeakBottom;
    public ModelRenderer ThrushWingL2;
    public ModelRenderer ThrushWingR2;

    public ModelThrush() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.ThrushTail3 = new ModelRenderer(this, -3, 9);
        this.ThrushTail3.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.ThrushTail3.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(ThrushTail3, 0.0F, -0.2665117767795341F, 0.0F);
        this.ThrushLegL2 = new ModelRenderer(this, 0, 5);
        this.ThrushLegL2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.ThrushLegL2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(ThrushLegL2, -1.3613568165555772F, 0.0F, 0.0F);
        this.ThrushLegL1 = new ModelRenderer(this, 0, 3);
        this.ThrushLegL1.setRotationPoint(1.3F, 1.5F, 0.0F);
        this.ThrushLegL1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(ThrushLegL1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushWingL2 = new ModelRenderer(this, 2, 9);
        this.ThrushWingL2.setRotationPoint(5.0F, -1.0F, -1.5F);
        this.ThrushWingL2.addBox(0.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushHead = new ModelRenderer(this, 12, 0);
        this.ThrushHead.setRotationPoint(0.5F, -0.1F, -3.0F);
        this.ThrushHead.addBox(-1.0F, -1.5F, -2.5F, 2, 2, 3, 0.0F);
        this.setRotateAngle(ThrushHead, 0.5235987755982988F, 0.0F, 0.0F);
        this.ThrushLegR1 = new ModelRenderer(this, 0, 3);
        this.ThrushLegR1.setRotationPoint(-0.3F, 1.5F, 0.0F);
        this.ThrushLegR1.addBox(-0.5F, 0.5F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(ThrushLegR1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushWingL1 = new ModelRenderer(this, 0, 9);
        this.ThrushWingL1.setRotationPoint(1.0F, 1.0F, 1.3F);
        this.ThrushWingL1.addBox(1.0F, -1.0F, -3.0F, 4, 0, 3, 0.0F);
        this.ThrushWingR1 = new ModelRenderer(this, 0, 9);
        this.ThrushWingR1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.ThrushWingR1.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushBody = new ModelRenderer(this, 0, 0);
        this.ThrushBody.setRotationPoint(0.0F, 20.3F, 0.5F);
        this.ThrushBody.addBox(-1.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(ThrushBody, -0.5235987755982988F, 0.0F, 0.0F);
        this.ThrushTail1 = new ModelRenderer(this, -4, 9);
        this.ThrushTail1.setRotationPoint(0.0F, -0.8F, 4.3F);
        this.ThrushTail1.addBox(-0.5F, -0.5F, -1.5F, 2, 0, 4, 0.0F);
        this.setRotateAngle(ThrushTail1, 0.2617993877991494F, 0.0F, 0.0F);
        this.ThrushBeakTop = new ModelRenderer(this, 0, 0);
        this.ThrushBeakTop.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.ThrushBeakTop.addBox(-0.5F, -0.8F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ThrushBeakTop, 0.08726646259971647F, 0.0F, 0.0F);
        this.ThrushBeakBottom = new ModelRenderer(this, 20, 3);
        this.ThrushBeakBottom.setRotationPoint(0.0F, 0.4F, 0.0F);
        this.ThrushBeakBottom.addBox(-0.5F, -0.9F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ThrushBeakBottom, -0.08726646259971647F, 0.0F, 0.0F);
        this.ThrushTail2 = new ModelRenderer(this, -3, 9);
        this.ThrushTail2.setRotationPoint(1.0F, 0.0F, -1.5F);
        this.ThrushTail2.addBox(-1.0F, -0.5F, 0.0F, 2, 0, 3, 0.0F);
        this.setRotateAngle(ThrushTail2, 0.0F, 0.2617993877991494F, 0.0F);
        this.ThrushWingR2 = new ModelRenderer(this, 2, 9);
        this.ThrushWingR2.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.ThrushWingR2.addBox(-4.0F, 0.0F, -1.5F, 4, 0, 3, 0.0F);
        this.ThrushLegR2 = new ModelRenderer(this, 0, 5);
        this.ThrushLegR2.setRotationPoint(0.0F, 2.4F, 0.5F);
        this.ThrushLegR2.addBox(-0.5F, 0.5F, 0.0F, 1, 1, 0, 0.0F);
        this.setRotateAngle(ThrushLegR2, -1.3613568165555772F, 0.0F, 0.0F);
        this.ThrushTail1.addChild(this.ThrushTail3);
        this.ThrushLegL1.addChild(this.ThrushLegL2);
        this.ThrushBody.addChild(this.ThrushLegL1);
        this.ThrushWingL1.addChild(this.ThrushWingL2);
        this.ThrushBody.addChild(this.ThrushHead);
        this.ThrushBody.addChild(this.ThrushLegR1);
        this.ThrushBody.addChild(this.ThrushWingL1);
        this.ThrushBody.addChild(this.ThrushWingR1);
        this.ThrushBody.addChild(this.ThrushTail1);
        this.ThrushHead.addChild(this.ThrushBeakTop);
        this.ThrushBeakTop.addChild(this.ThrushBeakBottom);
        this.ThrushTail1.addChild(this.ThrushTail2);
        this.ThrushWingR1.addChild(this.ThrushWingR2);
        this.ThrushLegR1.addChild(this.ThrushLegR2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.ThrushBody.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
