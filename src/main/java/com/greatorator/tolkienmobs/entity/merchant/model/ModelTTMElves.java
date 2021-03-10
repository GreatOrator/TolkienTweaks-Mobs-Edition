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
 * Elf - GreatOrator
 */
public class ModelTTMElves<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
    public ModelRenderer ElfBody;
    public ModelRenderer ElfHead;
    public ModelRenderer ElfLegRight;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer ElfLegLeft;
    public ModelRenderer ElfEarLeft1;
    public ModelRenderer ElfEarRight1;
    public ModelRenderer ElfEarLeft2;
    public ModelRenderer ElfEarLeft3;
    public ModelRenderer ElfEarRight2;
    public ModelRenderer ElfEarRight3;

    public ModelTTMElves() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.ElfEarRight3 = new ModelRenderer(this, 35, 10);
        this.ElfEarRight3.setRotationPoint(0.0F, -2.0F, 2.7F);
        this.ElfEarRight3.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.ElfEarRight1 = new ModelRenderer(this, 35, 10);
        this.ElfEarRight1.setRotationPoint(-4.0F, -3.5F, -1.6F);
        this.ElfEarRight1.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 2, 0.0F);
        this.ElfEarLeft2 = new ModelRenderer(this, 35, 10);
        this.ElfEarLeft2.setRotationPoint(0.0F, -1.0F, 1.0F);
        this.ElfEarLeft2.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.ElfBody = new ModelRenderer(this, 16, 16);
        this.ElfBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ElfBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.ElfEarLeft1 = new ModelRenderer(this, 35, 10);
        this.ElfEarLeft1.mirror = true;
        this.ElfEarLeft1.setRotationPoint(4.0F, -3.5F, -1.6F);
        this.ElfEarLeft1.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 2, 0.0F);
        this.ElfHead = new ModelRenderer(this, 0, 0);
        this.ElfHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ElfHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.ElfLegRight = new ModelRenderer(this, 0, 16);
        this.ElfLegRight.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.ElfLegRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.ElfEarRight2 = new ModelRenderer(this, 35, 10);
        this.ElfEarRight2.setRotationPoint(0.0F, -1.0F, 1.0F);
        this.ElfEarRight2.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.ElfLegLeft = new ModelRenderer(this, 16, 48);
        this.ElfLegLeft.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.ElfLegLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.ElfEarLeft3 = new ModelRenderer(this, 35, 10);
        this.ElfEarLeft3.setRotationPoint(0.0F, -2.0F, 2.7F);
        this.ElfEarLeft3.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.ElfEarRight1.addChild(this.ElfEarRight3);
        this.ElfHead.addChild(this.ElfEarRight1);
        this.ElfEarLeft1.addChild(this.ElfEarLeft2);
        this.ElfBody.addChild(this.bipedRightArm);
        this.ElfHead.addChild(this.ElfEarLeft1);
        this.ElfBody.addChild(this.ElfHead);
        this.ElfBody.addChild(this.ElfLegRight);
        this.ElfEarRight1.addChild(this.ElfEarRight2);
        this.ElfBody.addChild(this.bipedLeftArm);
        this.ElfBody.addChild(this.ElfLegLeft);
        this.ElfEarLeft1.addChild(this.ElfEarLeft3);
    }

public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.ElfHead, this.ElfBody, this.ElfLegRight, this.ElfLegLeft, this.bipedLeftArm, this.bipedRightArm);
        }

/**
 * Sets this entity's model rotation angles
 */
public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
        flag = ((AbstractVillagerEntity)entityIn).getShakeHeadTicks() > 0;
        }

        this.ElfHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.ElfHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        if (flag) {
        this.ElfHead.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * ageInTicks);
        this.ElfHead.rotateAngleX = 0.4F;
        } else {
        this.ElfHead.rotateAngleZ = 0.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.ElfLegRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.ElfLegLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.ElfLegRight.rotateAngleY = 0.0F;
        this.ElfLegLeft.rotateAngleY = 0.0F;
        }

public ModelRenderer getModelHead() {
        return this.ElfHead;
        }

@Override
public void func_217146_a(boolean p_217146_1_) {

        }
}