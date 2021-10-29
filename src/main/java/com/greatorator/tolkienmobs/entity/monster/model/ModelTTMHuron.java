package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Huron - GreatOrator
 */
public class ModelTTMHuron<T extends MonsterEntity> extends SegmentedModel<T> {
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

    public ModelTTMHuron() {
        this(0.0F);
    }

    public ModelTTMHuron(float modelSize) {
        this.texWidth = 128;
        this.texHeight = 64;
        this.HuronHatTop = new ModelRenderer(this, 84, 16);
        this.HuronHatTop.setPos(-4.0F, -12.0F, -4.0F);
        this.HuronHatTop.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
        this.HuronLegBR = new ModelRenderer(this, 24, 0);
        this.HuronLegBR.setPos(3.0F, 18.0F, 4.0F);
        this.HuronLegBR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronHead = new ModelRenderer(this, 72, 0);
        this.HuronHead.setPos(0.0F, 0.0F, 0.0F);
        this.HuronHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.HuronNose = new ModelRenderer(this, 22, 0);
        this.HuronNose.setPos(-0.7F, -3.2F, -4.9F);
        this.HuronNose.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.HuronLegFR = new ModelRenderer(this, 40, 0);
        this.HuronLegFR.setPos(3.0F, 18.0F, -4.0F);
        this.HuronLegFR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronLegFL = new ModelRenderer(this, 56, 0);
        this.HuronLegFL.setPos(-3.0F, 18.0F, -4.0F);
        this.HuronLegFL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronArmR = new ModelRenderer(this, 40, 10);
        this.HuronArmR.setPos(4.0F, -0.1F, -2.0F);
        this.HuronArmR.addBox(0.0F, 0.0F, 0.0F, 4, 14, 4, 0.0F);
        this.HuronArmL = new ModelRenderer(this, 24, 10);
        this.HuronArmL.setPos(-8.0F, -0.1F, -2.0F);
        this.HuronArmL.addBox(0.0F, 0.0F, 0.0F, 4, 14, 4, 0.0F);
        this.HuronHat = new ModelRenderer(this, 42, 16);
        this.HuronHat.setPos(-7.0F, -9.5F, -7.0F);
        this.HuronHat.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
        this.HuronLegBL = new ModelRenderer(this, 104, 0);
        this.HuronLegBL.setPos(-3.0F, 18.0F, 4.0F);
        this.HuronLegBL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HuronBody = new ModelRenderer(this, 0, 0);
        this.HuronBody.setPos(0.0F, 0.0F, 0.0F);
        this.HuronBody.addBox(-4.0F, 0.0F, -2.0F, 8, 18, 4, 0.0F);
        this.HuronHead.addChild(this.HuronNose);
        this.HuronHead.addChild(this.HuronHat);
        this.HuronHead.addChild(this.HuronHatTop);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.HuronHead, this.HuronBody, this.HuronArmR, this.HuronArmL, this.HuronLegFR, this.HuronLegFL, this.HuronLegBR, this.HuronLegBL);
    }

    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.HuronHead.yRot = p_225597_5_ * 0.017453292F;
        this.HuronHead.xRot = p_225597_6_ * 0.017453292F;
        this.HuronLegFR.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.HuronLegFL.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + 3.1415927F) * 1.4F * p_225597_3_;
        this.HuronLegBR.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + 3.1415927F) * 1.4F * p_225597_3_;
        this.HuronLegBL.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;

        this.HuronArmR.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.HuronArmL.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_;
    }
}