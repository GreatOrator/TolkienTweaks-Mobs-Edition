package com.greatorator.tolkienmobs.entity.passive.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Auroch - GreatOrator
 */
public class AurochModel<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer AurochBody;
    public ModelRenderer AurochBody_1;
    public ModelRenderer AurochBody_2;
    public ModelRenderer AurochHead;
    public ModelRenderer AurochLegFR;
    public ModelRenderer AurochLegBR;
    public ModelRenderer AurochLegFL;
    public ModelRenderer AurochLegBL;
    public ModelRenderer AurochHorn;
    public ModelRenderer AurochHorn_1;
    public ModelRenderer AurochHorn_2;
    public ModelRenderer AurochHorn_3;
    public ModelRenderer AurochHorn_4;
    public ModelRenderer AurochHorn_5;
    public ModelRenderer AurochHorn_6;
    public ModelRenderer AurochHorn_7;
    public ModelRenderer AurochHorn_8;
    public ModelRenderer AurochHorn_9;
    public ModelRenderer AurochHorn_10;
    public ModelRenderer AurochHorn_11;
    public ModelRenderer AurochHorn_12;
    public ModelRenderer AurochLegFR_1;
    public ModelRenderer AurochLegBR_1;
    public ModelRenderer AurochLegFL_1;
    public ModelRenderer AurochLegBL_1;

    public AurochModel() {
        this.texWidth = 128;
        this.texHeight = 64;
        this.AurochHorn_4 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_4.setPos(3.0F, -0.2F, 0.2F);
        this.AurochHorn_4.addBox(0.0F, -2.0F, -0.8F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_4, 0.0F, 0.2617993877991494F, 0.3490658503988659F);
        this.AurochBody = new ModelRenderer(this, 14, 44);
        this.AurochBody.setPos(0.0F, 11.5F, 0.0F);
        this.AurochBody.addBox(-5.5F, -5.5F, -9.0F, 11, 11, 9, 0.0F);
        this.setRotateAngle(AurochBody, -0.08726646259971647F, 0.0F, 0.0F);
        this.AurochLegBL = new ModelRenderer(this, 0, 55);
        this.AurochLegBL.setPos(1.8F, 3.1F, 9.5F);
        this.AurochLegBL.addBox(0.0F, -2.5F, -2.0F, 3, 5, 4, 0.0F);
        this.setRotateAngle(AurochLegBL, 0.08726646259971647F, 0.0F, 0.0F);
        this.AurochHead = new ModelRenderer(this, 0, 0);
        this.AurochHead.setPos(0.0F, 8.3F, -8.5F);
        this.AurochHead.addBox(-3.5F, -3.5F, -4.0F, 7, 7, 4, 0.0F);
        this.setRotateAngle(AurochHead, -0.010471975511965976F, 0.0F, 0.0F);
        this.AurochHorn_11 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_11.mirror = true;
        this.AurochHorn_11.setPos(-3.4F, -0.1F, 0.2F);
        this.AurochHorn_11.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_11, 0.0F, -0.5235987755982988F, 0.0F);
        this.AurochHorn_2 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_2.mirror = true;
        this.AurochHorn_2.setPos(-2.5F, -1.1F, 0.0F);
        this.AurochHorn_2.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_2, 0.0F, -0.2617993877991494F, -0.2617993877991494F);
        this.AurochHorn_7 = new ModelRenderer(this, 100, 25);
        this.AurochHorn_7.setPos(2.9F, -1.0F, 0.2F);
        this.AurochHorn_7.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
        this.AurochHorn_8 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_8.mirror = true;
        this.AurochHorn_8.setPos(-3.2F, -0.3F, 0.0F);
        this.AurochHorn_8.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_8, 0.0F, -0.2617993877991494F, -0.6981317007977318F);
        this.AurochHorn_5 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_5.setPos(2.9F, -0.2F, 0.0F);
        this.AurochHorn_5.addBox(0.0F, -2.0F, -0.8F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_5, 0.0F, 0.3490658503988659F, 0.17453292519943295F);
        this.AurochLegBR = new ModelRenderer(this, 0, 55);
        this.AurochLegBR.mirror = true;
        this.AurochLegBR.setPos(-1.8F, 3.1F, 9.5F);
        this.AurochLegBR.addBox(-3.0F, -2.5F, -2.0F, 3, 5, 4, 0.0F);
        this.setRotateAngle(AurochLegBR, 0.08726646259971647F, 0.0F, 0.0F);
        this.AurochHorn_6 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_6.setPos(3.2F, 0.0F, 0.2F);
        this.AurochHorn_6.addBox(0.0F, -2.0F, -0.8F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_6, 0.0F, 0.3490658503988659F, 0.0F);
        this.AurochHorn_1 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_1.setPos(2.2F, -0.1F, 0.0F);
        this.AurochHorn_1.addBox(0.0F, -2.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_1, 0.0F, 0.2617993877991494F, 0.2617993877991494F);
        this.AurochLegFL = new ModelRenderer(this, 0, 35);
        this.AurochLegFL.setPos(3.8F, 3.5F, -7.4F);
        this.AurochLegFL.addBox(0.0F, -2.5F, -2.0F, 3, 5, 4, 0.0F);
        this.setRotateAngle(AurochLegFL, 0.08726646259971647F, 0.0F, 0.0F);
        this.AurochHorn_3 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_3.setPos(2.4F, -0.6F, -0.2F);
        this.AurochHorn_3.addBox(0.0F, -2.0F, -0.8F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_3, 0.0F, 0.2617993877991494F, 0.6981317007977318F);
        this.AurochHorn_12 = new ModelRenderer(this, 100, 25);
        this.AurochHorn_12.mirror = true;
        this.AurochHorn_12.setPos(-3.6F, 0.0F, 0.0F);
        this.AurochHorn_12.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
        this.AurochHorn_10 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_10.mirror = true;
        this.AurochHorn_10.setPos(-3.4F, -0.1F, 0.1F);
        this.AurochHorn_10.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_10, 0.0F, -0.3490658503988659F, -0.17453292519943295F);
        this.AurochBody_2 = new ModelRenderer(this, 54, 24);
        this.AurochBody_2.setPos(0.0F, -1.0F, -1.0F);
        this.AurochBody_2.addBox(-4.5F, -4.5F, 0.0F, 9, 9, 11, 0.0F);
        this.setRotateAngle(AurochBody_2, -0.17453292519943295F, 0.0F, 0.0F);
        this.AurochLegFR = new ModelRenderer(this, 0, 35);
        this.AurochLegFR.mirror = true;
        this.AurochLegFR.setPos(-3.8F, 3.5F, -7.4F);
        this.AurochLegFR.addBox(-3.0F, -2.5F, -2.0F, 3, 5, 4, 0.0F);
        this.setRotateAngle(AurochLegFR, 0.08726646259971647F, 0.0F, 0.0F);
        this.AurochHorn_9 = new ModelRenderer(this, 100, 20);
        this.AurochHorn_9.mirror = true;
        this.AurochHorn_9.setPos(-3.3F, -0.2F, 0.0F);
        this.AurochHorn_9.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(AurochHorn_9, 0.0F, -0.2617993877991494F, -0.3490658503988659F);
        this.AurochBody_1 = new ModelRenderer(this, 54, 44);
        this.AurochBody_1.setPos(0.0F, 1.0F, 0.0F);
        this.AurochBody_1.addBox(-4.5F, -4.5F, 0.0F, 9, 9, 11, 0.0F);
        this.AurochLegBL_1 = new ModelRenderer(this, 0, 25);
        this.AurochLegBL_1.setPos(1.2F, 1.5F, 0.0F);
        this.AurochLegBL_1.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.AurochHorn = new ModelRenderer(this, 100, 16);
        this.AurochHorn.setPos(0.0F, -3.5F, -1.5F);
        this.AurochHorn.addBox(-3.0F, -2.0F, -1.0F, 6, 2, 2, 0.0F);
        this.AurochLegBR_1 = new ModelRenderer(this, 0, 25);
        this.AurochLegBR_1.mirror = true;
        this.AurochLegBR_1.setPos(-1.2F, 1.5F, 0.0F);
        this.AurochLegBR_1.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.AurochLegFR_1 = new ModelRenderer(this, 0, 44);
        this.AurochLegFR_1.mirror = true;
        this.AurochLegFR_1.setPos(-1.2F, 1.6F, 0.0F);
        this.AurochLegFR_1.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
        this.AurochLegFL_1 = new ModelRenderer(this, 0, 44);
        this.AurochLegFL_1.setPos(1.2F, 1.6F, 0.0F);
        this.AurochLegFL_1.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
        this.AurochHorn_3.addChild(this.AurochHorn_4);
        this.AurochBody.addChild(this.AurochLegBL);
        this.AurochHorn_10.addChild(this.AurochHorn_11);
        this.AurochHorn.addChild(this.AurochHorn_2);
        this.AurochHorn_6.addChild(this.AurochHorn_7);
        this.AurochHorn_2.addChild(this.AurochHorn_8);
        this.AurochHorn_4.addChild(this.AurochHorn_5);
        this.AurochBody.addChild(this.AurochLegBR);
        this.AurochHorn_5.addChild(this.AurochHorn_6);
        this.AurochHorn.addChild(this.AurochHorn_1);
        this.AurochBody.addChild(this.AurochLegFL);
        this.AurochHorn_1.addChild(this.AurochHorn_3);
        this.AurochHorn_11.addChild(this.AurochHorn_12);
        this.AurochHorn_9.addChild(this.AurochHorn_10);
        this.AurochBody.addChild(this.AurochBody_2);
        this.AurochBody.addChild(this.AurochLegFR);
        this.AurochHorn_8.addChild(this.AurochHorn_9);
        this.AurochBody.addChild(this.AurochBody_1);
        this.AurochLegBL.addChild(this.AurochLegBL_1);
        this.AurochHead.addChild(this.AurochHorn);
        this.AurochLegBR.addChild(this.AurochLegBR_1);
        this.AurochLegFR.addChild(this.AurochLegFR_1);
        this.AurochLegFL.addChild(this.AurochLegFL_1);
    }

    @Override
    public Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.AurochHead);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.AurochBody);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.AurochLegFR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.AurochLegBL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.AurochLegFL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.AurochLegBR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.AurochHead.yRot = netHeadYaw * 0.017453292F;
        this.AurochHead.xRot = headPitch * 0.017453292F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}