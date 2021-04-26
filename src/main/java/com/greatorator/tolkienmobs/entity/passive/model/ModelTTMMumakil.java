package com.greatorator.tolkienmobs.entity.passive.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import static com.greatorator.tolkienmobs.utils.TTMRand.degToRad;

/**
 * Mumakil - GreatOrator
 */
public class ModelTTMMumakil<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer MumuLegFL;
    public ModelRenderer MumuLegFR;
    public ModelRenderer MumuLegRR;
    public ModelRenderer MumuLegRL;
    public ModelRenderer MumuBody;
    public ModelRenderer MumuNeck;
    public ModelRenderer MumuHump1;
    public ModelRenderer MumuBottom;
    public ModelRenderer MumuHump2;
    public ModelRenderer MumuSkull;
    public ModelRenderer MumuEarL;
    public ModelRenderer MumuEarR;
    public ModelRenderer MumuTuskFR1;
    public ModelRenderer MumuTuskFR2;
    public ModelRenderer MumuTuskFR3;
    public ModelRenderer MumuTuskRR1;
    public ModelRenderer MumuTuskRR2;
    public ModelRenderer MumuTuskFL1;
    public ModelRenderer MumuTuskFL2;
    public ModelRenderer MumuTuskFL3;
    public ModelRenderer MumuTuskRL1;
    public ModelRenderer MumuTuskRL2;
    public ModelRenderer MumuTrunk1;
    public ModelRenderer MumuTrunk2;
    public ModelRenderer MumuTrunk3;

    /** Allows customization of baby animal */
    public ModelRenderer childSkull;
    public ModelRenderer childNeck;
    public ModelRenderer childEarL;
    public ModelRenderer childEarR;
    public ModelRenderer childTrunk1;

    // need some variables to help revert positions after a rearing animation
    protected float MumuNeckRotPointXDefault;
    protected float MumuNeckRotPointYDefault;
    protected float MumuNeckRotPointZDefault;
    protected float MumuBodyRotPointXDefault;
    protected float MumuBodyRotPointYDefault;
    protected float MumuBodyRotPointZDefault;
    protected float MumuLegFLRotPointXDefault;
    protected float MumuLegFLRotPointYDefault;
    protected float MumuLegFLRotPointZDefault;
    protected float MumuLegFRRotPointXDefault;
    protected float MumuLegFRRotPointYDefault;
    protected float MumuLegFRRotPointZDefault;

    /** Allows customization of baby animal */
    protected float childNeckRotPointXDefault;
    protected float childNeckRotPointYDefault;
    protected float childNeckRotPointZDefault;

    /** create an animation cycle for the rearing rearingCount will be the animation cycle counter */
    protected static float[][] rearingOffsetCycle = new float[][]
            {
                    // {headOffsetY, headoffsetZ, bodyOffsetY, bodyOffsetZ, MumuLegFLOffsetY, MumuLegFLOffsetZ, MumuLegFROffsetY, MumuLegFROffsetZ}
                    // animation starts from bottom as rearingCounter counts down
                    { -2F, 2F, -0.4F, 1F, -3, 1F, -3F, 1F, -1F, 1F },
                    { -4F, 4F, -0.8F, 2F, -6F, 1.75F, -6F, 2F, -2F, 2F },
                    { -6F, 5F, -1.2F, 3F, -8F, 2.5F, -8F, 3F, -3F, 3F },
                    { -8F, 6F, -1.5F, 4F, -10F, 3F, -10F, 4F, -4F, 4F },
                    { -9.5F, 7F, -1.8F, 5F, -11F, 3.5F, -11, 4.5F, -4.5F, 5F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -10.5F, 8.0F, -2.0F, 6.0F, -12F, 4F, -12F, 5F, -5F, 6F },
                    { -9.5F, 7F, -1.8F, 5F, -11F, 3.5F, -11, 4.5F, -4.5F, 5F },
                    { -8F, 6F, -1.5F, 4F, -10F, 3F, -10F, 4F, -4F, 4F },
                    { -6F, 5F, -1.2F, 3F, -8F, 2.5F, -8F, 3F, -3F, 3F },
                    { -4F, 4F, -0.8F, 2F, -6F, 1.75F, -6F, 2F, -2F, 2F },
                    { -2F, 2F, -0.4F, 1F, -3, 1F, -3F, 1F, -1F, 1F },
            };

    protected static float[][] rearingAngleCycle = new float[][]
            {
                    // {mainAngle, MumuTrunk1, MumuTrunk2, MumuTrunk3}
                    // animation starts from bottom as rearingCounter counts down
                    { -10F, -150F, -20F },
                    { -20F, -150F, -20F },
                    { -30F, -150F, -20F },
                    { -40F, -150F, -20F },
                    { -50F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -60F, -150F, -20F },
                    { -50F, -150F, -20F },
                    { -40F, -150F, -20F },
                    { -30F, -150F, -20F },
                    { -20F, -150F, -20F },
                    { -10F, -150F, -20F },
            };

    protected float field_78145_g = 8.0F;
    protected float field_78151_h = 4.0F;

    public ModelTTMMumakil() {
        this.texWidth = 256;
        this.texHeight = 256;

        this.MumuEarR = new ModelRenderer(this, 220, 12);
        this.MumuEarR.mirror = true;
        this.MumuEarR.setPos(-8.5F, -1.8F, -15.0F);
        this.MumuEarR.addBox(-3.5F, -6.1F, -0.5F, 11, 11, 1, 0.0F);
        this.setRotateAngle(MumuEarR, 0.0F, 0.5235987755982988F, 0.7853981633974483F);
        this.MumuTrunk1 = new ModelRenderer(this, 0, 43);
        this.MumuTrunk1.setPos(0.0F, 1.6F, -22.5F);
        this.MumuTrunk1.addBox(-3.5F, 0.0F, -2.5F, 7, 13, 5, 0.0F);
        this.setRotateAngle(MumuTrunk1, -0.17453292519943295F, 0.0F, 0.0F);
        this.MumuLegRR = new ModelRenderer(this, 88, 0);
        this.MumuLegRR.setPos(8.0F, -8.0F, 18.5F);
        this.MumuLegRR.addBox(0.0F, 0.0F, -5.5F, 11, 32, 11, 0.0F);
        this.MumuTuskFL3 = new ModelRenderer(this, 233, 24);
        this.MumuTuskFL3.setPos(10.4F, 24.9F, -33.9F);
        this.MumuTuskFL3.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskFL3, 0.9250245035569946F, -0.2617993877991494F, 0.0F);
        this.MumuTrunk3 = new ModelRenderer(this, 121, 43);
        this.MumuTrunk3.setPos(0.0F, 8.5F, 0.0F);
        this.MumuTrunk3.addBox(-2.5F, 0.0F, -1.5F, 5, 9, 3, 0.0F);
        this.setRotateAngle(MumuTrunk3, -0.2617993877991494F, 0.0F, 0.0F);
        this.MumuTrunk2 = new ModelRenderer(this, 24, 43);
        this.MumuTrunk2.setPos(0.0F, 12.5F, 0.0F);
        this.MumuTrunk2.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4, 0.0F);
        this.setRotateAngle(MumuTrunk2, -0.17453292519943295F, 0.0F, 0.0F);
        this.MumuTuskFL2 = new ModelRenderer(this, 211, 24);
        this.MumuTuskFL2.setPos(9.1F, 18.3F, -29.0F);
        this.MumuTuskFL2.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskFL2, 0.9250245035569946F, -0.2617993877991494F, 0.0F);
        this.MumuNeck = new ModelRenderer(this, 176, 0);
        MumuNeckRotPointXDefault = 0F;
        MumuNeckRotPointYDefault = -18F;
        MumuNeckRotPointZDefault = -21.5F;
        this.MumuNeck.setPos(MumuNeckRotPointXDefault, MumuNeckRotPointYDefault, MumuNeckRotPointZDefault);
        this.MumuNeck.addBox(-5.5F, -5.5F, -10.5F, 11, 11, 11, 0.0F);
        this.setRotateAngle(MumuNeck, 0.08726646259971647F, 0.0F, 0.0F);
        this.MumuTuskFL1 = new ModelRenderer(this, 198, 22);
        this.MumuTuskFL1.setPos(7.0F, 5.0F, -21.1F);
        this.MumuTuskFL1.addBox(-1.0F, -1.0F, -12.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskFL1, 1.0471975511965976F, -0.2617993877991494F, 0.0F);
        this.MumuTuskRL2 = new ModelRenderer(this, 231, 35);
        this.MumuTuskRL2.setPos(10.9F, 17.4F, -23.3F);
        this.MumuTuskRL2.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskRL2, 0.9250245035569946F, -0.6108652381980153F, 0.0F);
        this.MumuTuskRR1 = new ModelRenderer(this, 165, 0);
        this.MumuTuskRR1.setPos(-6.5F, 5.1F, -17.0F);
        this.MumuTuskRR1.addBox(-1.0F, -1.0F, -12.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskRR1, 1.0471975511965976F, 0.6108652381980153F, 0.0F);
        this.MumuHump2 = new ModelRenderer(this, 0, 115);
        this.MumuHump2.setPos(2.0F, -1.7F, 0.4F);
        this.MumuHump2.addBox(-17.0F, -12.5F, -0.2F, 33, 5, 17, 0.0F);
        this.setRotateAngle(MumuHump2, 0.022689280275926284F, 0.0F, 0.0F);
        this.MumuEarL = new ModelRenderer(this, 220, 0);
        this.MumuEarL.setPos(8.6F, -1.8F, -15.0F);
        this.MumuEarL.addBox(-7.5F, -6.0F, -0.5F, 11, 11, 1, 0.0F);
        this.setRotateAngle(MumuEarL, 0.0F, -0.5235987755982988F, -0.7853981633974483F);
        this.MumuTuskRL1 = new ModelRenderer(this, 209, 35);
        this.MumuTuskRL1.setPos(6.5F, 5.1F, -17.0F);
        this.MumuTuskRL1.addBox(-1.0F, -1.0F, -12.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskRL1, 1.0471975511965976F, -0.6108652381980153F, 0.0F);
        this.MumuLegFR = new ModelRenderer(this, 44, 0);
        MumuLegFRRotPointXDefault = 8.0F;
        MumuLegFRRotPointYDefault = -8.0F;
        MumuLegFRRotPointZDefault = -18.5F;
        this.MumuLegFR.setPos(MumuLegFRRotPointXDefault, MumuLegFRRotPointYDefault, MumuLegFRRotPointZDefault);
        this.MumuLegFR.addBox(0.0F, 0.0F, -5.5F, 11, 32, 11, 0.0F);
        this.MumuLegFL = new ModelRenderer(this, 0, 0);
        MumuLegFLRotPointXDefault = -2.0F;
        MumuLegFLRotPointYDefault = -8.0F;
        MumuLegFLRotPointZDefault = -18.5F;
        this.MumuLegFL.setPos(MumuLegFLRotPointXDefault, MumuLegFLRotPointYDefault, MumuLegFLRotPointZDefault);
        this.MumuLegFL.addBox(-17.0F, 0.0F, -5.5F, 11, 32, 11, 0.0F);
        this.MumuBottom = new ModelRenderer(this, 133, 80);
        this.MumuBottom.setPos(0.0F, 10.5F, 3.5F);
        this.MumuBottom.addBox(-11.0F, 0.0F, 0.0F, 23, 5, 35, 0.0F);
        this.setRotateAngle(MumuBottom, 0.08726646259971647F, 0.0F, 0.0F);
        this.MumuSkull = new ModelRenderer(this, 100, 120);
        this.MumuSkull.setPos(-3.0F, -3.0F, -13.5F);
        this.MumuSkull.addBox(-5.5F, -5.5F, -10.5F, 17, 17, 15, 0.0F);
        this.MumuTuskFR2 = new ModelRenderer(this, 77, 0);
        this.MumuTuskFR2.setPos(-9.1F, 18.3F, -29.0F);
        this.MumuTuskFR2.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskFR2, 0.9250245035569946F, 0.2617993877991494F, 0.0F);
        this.MumuTuskFR1 = new ModelRenderer(this, 33, 0);
        this.MumuTuskFR1.setPos(-7.0F, 5.0F, -21.1F);
        this.MumuTuskFR1.addBox(-1.0F, -1.0F, -12.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskFR1, 1.0471975511965976F, 0.2617993877991494F, 0.0F);
        this.MumuTuskFR3 = new ModelRenderer(this, 121, 0);
        this.MumuTuskFR3.setPos(-10.4F, 24.9F, -33.9F);
        this.MumuTuskFR3.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskFR3, 0.9250245035569946F, 0.2617993877991494F, 0.0F);
        this.MumuHump1 = new ModelRenderer(this, 121, 43);
        this.MumuHump1.setPos(2.0F, -2.1F, 15.5F);
        this.MumuHump1.addBox(-17.0F, -12.5F, 0.0F, 33, 5, 22, 0.0F);
        this.setRotateAngle(MumuHump1, -0.0942477796076938F, 0.0F, 0.0F);
        this.MumuTuskRR2 = new ModelRenderer(this, 176, 22);
        this.MumuTuskRR2.setPos(-10.9F, 17.4F, -23.3F);
        this.MumuTuskRR2.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(MumuTuskRR2, 0.9250245035569946F, 0.6108652381980153F, 0.0F);
        this.MumuBody = new ModelRenderer(this, 0, 43);
        MumuBodyRotPointXDefault = -1.5F;
        MumuBodyRotPointYDefault = -15.0F;
        MumuBodyRotPointZDefault = -23.2F;
        this.MumuBody.setPos(MumuBodyRotPointXDefault, MumuBodyRotPointYDefault, MumuBodyRotPointZDefault);
        this.MumuBody.addBox(-17.0F, -12.5F, 0.0F, 37, 25, 47, 0.0F);
        this.setRotateAngle(MumuBody, -0.11344640137963141F, 0.0F, 0.0F);
        this.MumuLegRL = new ModelRenderer(this, 132, 0);
        this.MumuLegRL.setPos(-2.0F, -8.0F, 18.5F);
        this.MumuLegRL.addBox(-17.0F, 0.0F, -5.5F, 11, 32, 11, 0.0F);
        this.MumuNeck.addChild(this.MumuEarR);
        this.MumuNeck.addChild(this.MumuTrunk1);
        this.MumuNeck.addChild(this.MumuTuskFL3);
        this.MumuNeck.addChild(this.MumuTuskFL2);
        this.MumuNeck.addChild(this.MumuTuskFL1);
        this.MumuNeck.addChild(this.MumuTuskRL2);
        this.MumuNeck.addChild(this.MumuTuskRR1);
        this.MumuBody.addChild(this.MumuHump2);
        this.MumuNeck.addChild(this.MumuEarL);
        this.MumuNeck.addChild(this.MumuTuskRL1);
        this.MumuBody.addChild(this.MumuBottom);
        this.MumuNeck.addChild(this.MumuSkull);
        this.MumuNeck.addChild(this.MumuTuskFR2);
        this.MumuNeck.addChild(this.MumuTuskFR1);
        this.MumuNeck.addChild(this.MumuTuskFR3);
        this.MumuBody.addChild(this.MumuHump1);
        this.MumuNeck.addChild(this.MumuTuskRR2);
        this.MumuTrunk1.addChild(this.MumuTrunk2);
        this.MumuTrunk2.addChild(this.MumuTrunk3);

        /* Parts for the child version */
        this.childSkull = new ModelRenderer(this, 100, 120);
        this.childSkull.setPos(-3.0F, -3.0F, -13.5F);
        this.childSkull.addBox(-5.5F, -5.5F, -10.5F, 17, 17, 15, 0.0F);
        this.childNeck = new ModelRenderer(this, 176, 0);
        this.childNeck.addBox(-5.5F, -5.5F, -10.5F, 11, 11, 11, 0.0F);
        childNeckRotPointXDefault = 0F;
        childNeckRotPointYDefault = -18F;
        childNeckRotPointZDefault = -21.5F;
        this.childNeck.setPos(childNeckRotPointXDefault, childNeckRotPointYDefault, childNeckRotPointZDefault);
        this.childEarR = new ModelRenderer(this, 220, 12);
        this.childEarR.addBox(-3.5F, -6.1F, -0.5F, 11, 11, 1, 0.0F);
        this.childEarR.setPos(-8.5F, -1.8F, -15.0F);
        this.childEarL = new ModelRenderer(this, 220, 0);
        this.childEarL.addBox(-7.5F, -6.0F, -0.5F, 11, 11, 1, 0.0F);
        this.childEarL.setPos(8.6F, -1.8F, -15.0F);
        this.childEarL.mirror = true;
        this.childTrunk1 = new ModelRenderer(this, 0, 43);
        this.childTrunk1.addBox(-3.5F, 0.0F, -2.5F, 7, 13, 5, 0.0F);
        this.childTrunk1.setPos(0.0F, 1.6F, -22.5F);
        this.childNeck.addChild(this.childSkull);
        this.childNeck.addChild(this.childEarR);
        this.childNeck.addChild(this.childEarL);
        this.childNeck.addChild(this.childTrunk1);
    }

    public Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.MumuNeck);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.MumuBody, this.MumuLegFL, this.MumuLegFR, this.MumuLegRL, this.MumuLegRR);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        /* return rotation point in case there was previous rearing animation */
        MumuNeck.setPos(MumuNeckRotPointXDefault, MumuNeckRotPointYDefault, MumuNeckRotPointZDefault);
        MumuBody.setPos(MumuBodyRotPointXDefault, MumuBodyRotPointYDefault, MumuBodyRotPointZDefault);
        MumuLegFL.setPos(MumuLegFLRotPointXDefault, MumuLegFLRotPointYDefault, MumuLegFLRotPointZDefault);
        MumuLegFR.setPos(MumuLegFRRotPointXDefault, MumuLegFRRotPointYDefault, MumuLegFRRotPointZDefault);
        childNeck.setPos(childNeckRotPointXDefault, childNeckRotPointYDefault, childNeckRotPointZDefault);

        MumuNeck.xRot = (headPitch);
        MumuNeck.yRot = degToRad(netHeadYaw);

        childNeck.xRot = degToRad(headPitch);
        childNeck.yRot = degToRad(netHeadYaw);

        /* swingSuppress goes to 0 when still so gates the movement */
        MumuLegRL.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 1.4F * limbSwingAmount;
        MumuLegRR.xRot = MathHelper.cos(ageInTicks * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        MumuLegFL.xRot = MathHelper.cos(ageInTicks * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        MumuLegFR.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 1.4F * limbSwingAmount;
        MumuTrunk1.xRot = MathHelper.cos(degToRad(entityIn.tickCount*7)) * degToRad(10);
        MumuTrunk2.xRot = MumuTrunk1.xRot * 3;
        MumuTrunk3.xRot = MumuTrunk2.xRot * 1;

        childTrunk1.xRot = MathHelper.cos(degToRad(entityIn.tickCount*7)) * degToRad(10);

        /* flick ears */
        MumuEarL.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(15);
        MumuEarR.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(-15);

        childEarL.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(15);
        childEarR.yRot = (float) Math.pow(MathHelper.cos(degToRad(entityIn.tickCount*3)), 6) * degToRad(-15);

        /* raise trunk if in water */
        if (entityIn.isInWater())
        {
            MumuTrunk1.xRot = degToRad(-150);
            MumuTrunk2.xRot = degToRad(-20);
            MumuTrunk3.xRot = degToRad(110);
        }
    }
}