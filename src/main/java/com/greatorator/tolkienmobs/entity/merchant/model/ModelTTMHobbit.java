package com.greatorator.tolkienmobs.entity.merchant.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Hobbit - GreatOrator
 */
public class ModelTTMHobbit<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
    public ModelRenderer HobbitLegL;
    public ModelRenderer HobbitLegR;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;
    public ModelRenderer HobbitBody;
    public ModelRenderer HobbitHead;

    public ModelTTMHobbit() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.HobbitBody = new ModelRenderer(this, 16, 16);
        this.HobbitBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HobbitBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitLegR = new ModelRenderer(this, 16, 48);
        this.HobbitLegR.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.HobbitLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitLegL = new ModelRenderer(this, 0, 16);
        this.HobbitLegL.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.HobbitLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 32, 48);
        this.bipedRightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HobbitHead = new ModelRenderer(this, 0, 0);
        this.HobbitHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HobbitHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.HobbitHead, this.HobbitBody, this.HobbitLegR, this.HobbitLegL, this.bipedLeftArm, this.bipedRightArm);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity)entityIn).getShakeHeadTicks() > 0;
        }

        this.HobbitHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.HobbitHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        if (flag) {
            this.HobbitHead.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.HobbitHead.rotateAngleX = 0.4F;
        } else {
            this.HobbitHead.rotateAngleZ = 0.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.HobbitLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.HobbitLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.HobbitLegR.rotateAngleY = 0.0F;
        this.HobbitLegL.rotateAngleY = 0.0F;
    }

    public ModelRenderer getModelHead() {
        return this.HobbitHead;
    }

    @Override
    public void func_217146_a(boolean p_217146_1_) {

    }

}