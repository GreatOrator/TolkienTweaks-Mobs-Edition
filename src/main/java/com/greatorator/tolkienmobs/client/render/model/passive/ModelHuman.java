package com.greatorator.tolkienmobs.client.render.model.passive;

import com.greatorator.tolkienmobs.client.render.model.ModelTolkienMobs;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Human - GreatOrator
 */
public class ModelHuman extends ModelTolkienMobs {
    public ModelRenderer HumanBody;
    public ModelRenderer HumanHead;
    public ModelRenderer HumanLegRight;
    public ModelRenderer HumanArmRight;
    public ModelRenderer HumanArmLeft;
    public ModelRenderer HumanLegLeft;

    public ModelHuman() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.HumanHead = new ModelRenderer(this, 0, 0);
        this.HumanHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HumanHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.HumanArmLeft = new ModelRenderer(this, 32, 48);
        this.HumanArmLeft.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.HumanArmLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanBody = new ModelRenderer(this, 16, 16);
        this.HumanBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HumanBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.HumanLegLeft = new ModelRenderer(this, 16, 48);
        this.HumanLegLeft.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.HumanLegLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanLegRight = new ModelRenderer(this, 0, 16);
        this.HumanLegRight.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.HumanLegRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanArmRight = new ModelRenderer(this, 40, 16);
        this.HumanArmRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.HumanArmRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanBody.addChild(this.HumanHead);
        this.HumanBody.addChild(this.HumanArmLeft);
        this.HumanBody.addChild(this.HumanLegLeft);
        this.HumanBody.addChild(this.HumanLegRight);
        this.HumanBody.addChild(this.HumanArmRight);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.HumanBody.render(f5);
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
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.HumanLegLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HumanLegRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.HumanArmRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.HumanArmLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.HumanHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.HumanHead.rotateAngleX = headPitch * 0.017453292F;
    }
}