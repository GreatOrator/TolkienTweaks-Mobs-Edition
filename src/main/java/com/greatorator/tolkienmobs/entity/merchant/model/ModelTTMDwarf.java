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
 * Dwarf - GreatOrator
 */
public class ModelTTMDwarf<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
    public ModelRenderer DwarfBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer DwarfHead;
    public ModelRenderer DwarfLegR;
    public ModelRenderer DwarfLegL;
    public ModelRenderer DwarfBeardMain;
    public ModelRenderer DwarfHelmetMain;
    public ModelRenderer DwarfBrowR;
    public ModelRenderer DwarfBrowL;
    public ModelRenderer DwarfBeard1;
    public ModelRenderer DwarfBeard2;
    public ModelRenderer DwarfBeard3;
    public ModelRenderer DwarfBeard4;
    public ModelRenderer DwarfBeard5;
    public ModelRenderer DwarfBeard6;
    public ModelRenderer DwarfBeard7;
    public ModelRenderer DwarfBeard8;
    public ModelRenderer DwarfBeard9;
    public ModelRenderer DwarfBeard0;
    public ModelRenderer DwarfBeard11;
    public ModelRenderer DwarfBeard12;
    public ModelRenderer DwarfBeard13;
    public ModelRenderer DwarfBeard14;
    public ModelRenderer DwarfBeard15;
    public ModelRenderer DwarfHelmet1;
    public ModelRenderer DwarfHelmet2;
    public ModelRenderer DwarfHelmet3;
    public ModelRenderer DwarfHelmet4;
    public ModelRenderer DwarfHelmet5;

    public ModelTTMDwarf() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.DwarfBeard5 = new ModelRenderer(this, 0, 52);
        this.DwarfBeard5.setRotationPoint(5.5F, 0.7F, 0.0F);
        this.DwarfBeard5.addBox(-0.5F, -0.5F, -0.5F, 1, 8, 1, 0.0F);
        this.DwarfHelmet4 = new ModelRenderer(this, 27, 29);
        this.DwarfHelmet4.setRotationPoint(-0.5F, -0.5F, 4.5F);
        this.DwarfHelmet4.addBox(0.0F, 0.0F, 0.0F, 10, 3, 5, 0.0F);
        this.DwarfBeard4 = new ModelRenderer(this, 0, 62);
        this.DwarfBeard4.setRotationPoint(6.5F, 0.7F, 0.0F);
        this.DwarfBeard4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.DwarfBeard9 = new ModelRenderer(this, 0, 52);
        this.DwarfBeard9.setRotationPoint(3.5F, 0.7F, 0.0F);
        this.DwarfBeard9.addBox(-0.5F, -0.5F, -0.5F, 1, 5, 1, 0.0F);
        this.DwarfBrowR = new ModelRenderer(this, 0, 62);
        this.DwarfBrowR.setRotationPoint(-3.0F, -5.5F, -4.0F);
        this.DwarfBrowR.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(DwarfBrowR, 0.0F, 0.015707963267948967F, 0.0F);
        this.DwarfBeard8 = new ModelRenderer(this, 0, 52);
        this.DwarfBeard8.setRotationPoint(4.5F, 0.7F, 0.0F);
        this.DwarfBeard8.addBox(-0.5F, -0.5F, -0.5F, 1, 9, 1, 0.0F);
        this.DwarfBeard12 = new ModelRenderer(this, 0, 56);
        this.DwarfBeard12.setRotationPoint(-0.7F, -0.7F, 0.0F);
        this.DwarfBeard12.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 6, 0.0F);
        this.DwarfHead = new ModelRenderer(this, 0, 0);
        this.DwarfHead.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.DwarfHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.DwarfBeard1 = new ModelRenderer(this, 0, 56);
        this.DwarfBeard1.setRotationPoint(-0.7F, 0.0F, 0.0F);
        this.DwarfBeard1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 7, 0.0F);
        this.DwarfHelmetMain = new ModelRenderer(this, 0, 29);
        this.DwarfHelmetMain.setRotationPoint(-4.5F, -6.5F, -4.5F);
        this.DwarfHelmetMain.addBox(-0.5F, -0.5F, -0.5F, 10, 1, 10, 0.0F);
        this.setRotateAngle(DwarfHelmetMain, 0.0F, 0.015707963267948967F, 0.0F);
        this.DwarfBeard7 = new ModelRenderer(this, 0, 52);
        this.DwarfBeard7.setRotationPoint(1.5F, 0.7F, 0.0F);
        this.DwarfBeard7.addBox(-0.5F, -0.5F, -0.5F, 1, 9, 1, 0.0F);
        this.DwarfLegL = new ModelRenderer(this, 0, 16);
        this.DwarfLegL.mirror = true;
        this.DwarfLegL.setRotationPoint(3.0F, 9.0F, 0.0F);
        this.DwarfLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 42, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 9, 4, 0.0F);
        this.DwarfBeard2 = new ModelRenderer(this, 0, 56);
        this.DwarfBeard2.setRotationPoint(6.7F, 0.0F, 0.0F);
        this.DwarfBeard2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 7, 0.0F);
        this.DwarfLegR = new ModelRenderer(this, 0, 16);
        this.DwarfLegR.setRotationPoint(-2.0F, 9.0F, 0.0F);
        this.DwarfLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
        this.DwarfBeard0 = new ModelRenderer(this, 0, 52);
        this.DwarfBeard0.setRotationPoint(2.5F, 0.7F, 0.0F);
        this.DwarfBeard0.addBox(-0.5F, -0.5F, -0.5F, 1, 4, 1, 0.0F);
        this.DwarfBeard14 = new ModelRenderer(this, 0, 62);
        this.DwarfBeard14.setRotationPoint(-0.5F, -0.7F, 0.0F);
        this.DwarfBeard14.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(DwarfBeard14, 0.0F, 0.015707963267948967F, 0.0F);
        this.DwarfHelmet3 = new ModelRenderer(this, 27, 29);
        this.DwarfHelmet3.setRotationPoint(3.5F, -1.5F, -0.5F);
        this.DwarfHelmet3.addBox(0.0F, 0.0F, 0.0F, 2, 3, 10, 0.0F);
        this.DwarfHelmet2 = new ModelRenderer(this, 7, 29);
        this.DwarfHelmet2.setRotationPoint(2.5F, -2.5F, -0.5F);
        this.DwarfHelmet2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F);
        this.DwarfBeard13 = new ModelRenderer(this, 0, 56);
        this.DwarfBeard13.setRotationPoint(6.7F, -0.7F, 0.0F);
        this.DwarfBeard13.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 6, 0.0F);
        this.DwarfHelmet5 = new ModelRenderer(this, 27, 29);
        this.DwarfHelmet5.setRotationPoint(-0.5F, 2.5F, 4.5F);
        this.DwarfHelmet5.addBox(0.0F, 0.0F, 0.0F, 10, 1, 2, 0.0F);
        this.DwarfBeard6 = new ModelRenderer(this, 0, 52);
        this.DwarfBeard6.setRotationPoint(0.5F, 0.7F, 0.0F);
        this.DwarfBeard6.addBox(-0.5F, -0.5F, -0.5F, 1, 8, 1, 0.0F);
        this.DwarfBrowL = new ModelRenderer(this, 0, 62);
        this.DwarfBrowL.setRotationPoint(1.0F, -5.5F, -4.0F);
        this.DwarfBrowL.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(DwarfBrowL, 0.0F, 0.015707963267948967F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 42, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(6.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(0.0F, -2.0F, -2.0F, 4, 9, 4, 0.0F);
        this.DwarfBeard3 = new ModelRenderer(this, 0, 62);
        this.DwarfBeard3.setRotationPoint(-0.5F, 0.7F, 0.0F);
        this.DwarfBeard3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.DwarfBody = new ModelRenderer(this, 16, 16);
        this.DwarfBody.setRotationPoint(-1.0F, 6.0F, 0.0F);
        this.DwarfBody.addBox(-4.0F, 0.0F, -2.0F, 9, 9, 4, 0.0F);
        this.DwarfBeardMain = new ModelRenderer(this, 0, 62);
        this.DwarfBeardMain.setRotationPoint(-3.0F, -0.5F, -3.7F);
        this.DwarfBeardMain.addBox(-0.5F, -0.5F, -0.5F, 7, 1, 1, 0.0F);
        this.setRotateAngle(DwarfBeardMain, 0.0F, 0.01564206586527315F, 0.0F);
        this.DwarfBeard11 = new ModelRenderer(this, 0, 62);
        this.DwarfBeard11.setRotationPoint(4.5F, -0.7F, 0.0F);
        this.DwarfBeard11.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(DwarfBeard11, 0.0F, 0.015707963267948967F, 0.0F);
        this.DwarfBeard15 = new ModelRenderer(this, 0, 62);
        this.DwarfBeard15.setRotationPoint(2.0F, -1.6F, 0.0F);
        this.DwarfBeard15.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(DwarfBeard15, 0.0F, 0.015707963267948967F, 0.0F);
        this.DwarfHelmet1 = new ModelRenderer(this, 0, 40);
        this.DwarfHelmet1.setRotationPoint(0.0F, -2.1F, 0.0F);
        this.DwarfHelmet1.addBox(0.0F, 0.0F, 0.0F, 9, 2, 9, 0.0F);
        this.DwarfBeardMain.addChild(this.DwarfBeard5);
        this.DwarfHelmetMain.addChild(this.DwarfHelmet4);
        this.DwarfBeardMain.addChild(this.DwarfBeard4);
        this.DwarfBeardMain.addChild(this.DwarfBeard9);
        this.DwarfHead.addChild(this.DwarfBrowR);
        this.DwarfBeardMain.addChild(this.DwarfBeard8);
        this.DwarfBeardMain.addChild(this.DwarfBeard12);
        this.DwarfBody.addChild(this.DwarfHead);
        this.DwarfBeardMain.addChild(this.DwarfBeard1);
        this.DwarfHead.addChild(this.DwarfHelmetMain);
        this.DwarfBeardMain.addChild(this.DwarfBeard7);
        this.DwarfBody.addChild(this.DwarfLegL);
        this.DwarfBody.addChild(this.bipedRightArm);
        this.DwarfBeardMain.addChild(this.DwarfBeard2);
        this.DwarfBody.addChild(this.DwarfLegR);
        this.DwarfBeardMain.addChild(this.DwarfBeard0);
        this.DwarfBeardMain.addChild(this.DwarfBeard14);
        this.DwarfHelmetMain.addChild(this.DwarfHelmet3);
        this.DwarfHelmetMain.addChild(this.DwarfHelmet2);
        this.DwarfBeardMain.addChild(this.DwarfBeard13);
        this.DwarfHelmetMain.addChild(this.DwarfHelmet5);
        this.DwarfBeardMain.addChild(this.DwarfBeard6);
        this.DwarfHead.addChild(this.DwarfBrowL);
        this.DwarfBody.addChild(this.bipedLeftArm);
        this.DwarfBeardMain.addChild(this.DwarfBeard3);
        this.DwarfHead.addChild(this.DwarfBeardMain);
        this.DwarfBeardMain.addChild(this.DwarfBeard11);
        this.DwarfBeardMain.addChild(this.DwarfBeard15);
        this.DwarfHelmetMain.addChild(this.DwarfHelmet1);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.DwarfBody);
    }

    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity)entityIn).getShakeHeadTicks() > 0;
        }

        this.DwarfHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.DwarfHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        if (flag) {
            this.DwarfHead.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.DwarfHead.rotateAngleX = 0.4F;
        } else {
            this.DwarfHead.rotateAngleZ = 0.0F;
        }

        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.DwarfLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.DwarfLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.DwarfHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.DwarfHead.rotateAngleX = headPitch * 0.017453292F;

        this.bipedRightArm.rotationPointX = -4.9F;
        this.bipedLeftArm.rotationPointX = 4.9F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public ModelRenderer getModelHead() {
        return this.DwarfHead;
    }

    @Override
    public void func_217146_a(boolean p_217146_1_) {

    }
}