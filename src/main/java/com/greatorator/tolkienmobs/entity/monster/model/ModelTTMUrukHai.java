package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Uruk Hai - GreatOrator
 */
public class ModelTTMUrukHai<T extends MonsterEntity> extends BipedModel<T> {
    public ModelRenderer UrukHaiLegL;
    public ModelRenderer UrukHaiHead;
    public ModelRenderer UrukHaiBody;
    public ModelRenderer UrukHaiLegR;
    public ModelRenderer UrukHaiArmLUpper;
    public ModelRenderer UrukHaiNose;
    public ModelRenderer UrukHaiBrowL;
    public ModelRenderer UrukHaiBrowR;
    public ModelRenderer Tooth3;
    public ModelRenderer Tooth4;
    public ModelRenderer UrukHaiHairTop;
    public ModelRenderer UrukHaiHairBack;
    public ModelRenderer UrukHaiEar1;
    public ModelRenderer UrukHaiEar2;
    public ModelRenderer UrukHaiEar3;
    public ModelRenderer UrukHaiEar4;
    public ModelRenderer UrukHaiHairBack2;
    public ModelRenderer UrukHaiHairBack3;
    public ModelRenderer UrukHaiBodyUpper;
    public ModelRenderer UrukHaiArmRUpper;
    private boolean ghostModel;

    public ModelTTMUrukHai(float modelSize, boolean p_i46303_2_) {
        super(modelSize);
        this.ghostModel = p_i46303_2_;
        this.texWidth = 128;
        this.texHeight = 64;
        this.UrukHaiHairBack3 = new ModelRenderer(this, 52, 12);
        this.UrukHaiHairBack3.setPos(0.0F, 3.0F, 4.0F);
        this.UrukHaiHairBack3.addBox(-1.5F, -0.5F, -0.5F, 3, 3, 1, 0.0F);
        this.leftArm = new ModelRenderer(this, 40, 48);
        this.leftArm.setPos(-7.0F, 2.5F, 0.0F);
        this.leftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Tooth3 = new ModelRenderer(this, 4, 0);
        this.Tooth3.mirror = true;
        this.Tooth3.setPos(1.5F, -0.5F, -3.7F);
        this.Tooth3.addBox(-0.5F, -1.5F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth3, 0.17453292519943295F, 0.0F, 0.0F);
        this.UrukHaiArmRUpper = new ModelRenderer(this, 56, 37);
        this.UrukHaiArmRUpper.setPos(-1.0F, -0.5F, -1.0F);
        this.UrukHaiArmRUpper.addBox(-1.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.UrukHaiHairBack = new ModelRenderer(this, 52, 10);
        this.UrukHaiHairBack.setPos(0.0F, -8.0F, 4.0F);
        this.UrukHaiHairBack.addBox(-1.5F, -0.5F, -0.5F, 3, 10, 1, 0.0F);
        this.UrukHaiEar2 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar2.setPos(4.0F, -3.7F, 0.6F);
        this.UrukHaiEar2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(UrukHaiEar2, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiEar4 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar4.setPos(-4.0F, -3.7F, 0.6F);
        this.UrukHaiEar4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(UrukHaiEar4, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiLegR = new ModelRenderer(this, 72, 48);
        this.UrukHaiLegR.setPos(1.9F, 12.0F, 0.0F);
        this.UrukHaiLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.UrukHaiArmLUpper = new ModelRenderer(this, 32, 37);
        this.UrukHaiArmLUpper.setPos(-1.0F, -0.5F, -1.0F);
        this.UrukHaiArmLUpper.addBox(-3.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.UrukHaiHairTop = new ModelRenderer(this, 32, 10);
        this.UrukHaiHairTop.setPos(0.0F, -8.0F, -2.5F);
        this.UrukHaiHairTop.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 7, 0.0F);
        this.rightArm = new ModelRenderer(this, 24, 48);
        this.rightArm.setPos(7.0F, 2.5F, 0.0F);
        this.rightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.UrukHaiEar1 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar1.setPos(4.0F, -4.5F, 0.0F);
        this.UrukHaiEar1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(UrukHaiEar1, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiHairBack2 = new ModelRenderer(this, 48, 10);
        this.UrukHaiHairBack2.setPos(1.0F, 2.0F, 4.0F);
        this.UrukHaiHairBack2.addBox(-1.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.Tooth4 = new ModelRenderer(this, 4, 4);
        this.Tooth4.mirror = true;
        this.Tooth4.setPos(-1.5F, -0.5F, -3.7F);
        this.Tooth4.addBox(-0.5F, -1.5F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth4, 0.17453292519943295F, 0.0F, 0.0F);
        this.UrukHaiBodyUpper = new ModelRenderer(this, 0, 36);
        this.UrukHaiBodyUpper.setPos(-1.0F, 0.0F, -1.0F);
        this.UrukHaiBodyUpper.addBox(-4.0F, 0.0F, -2.0F, 10, 6, 6, 0.0F);
        this.UrukHaiNose = new ModelRenderer(this, 25, 0);
        this.UrukHaiNose.setPos(0.0F, -3.5F, -3.7F);
        this.UrukHaiNose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(UrukHaiNose, 0.7853981633974483F, -0.6684611035138281F, -3.6651914291880923F);
        this.UrukHaiLegL = new ModelRenderer(this, 56, 48);
        this.UrukHaiLegL.setPos(-1.9F, 12.0F, 0.0F);
        this.UrukHaiLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.UrukHaiBody = new ModelRenderer(this, 0, 48);
        this.UrukHaiBody.setPos(0.0F, 0.0F, 0.0F);
        this.UrukHaiBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.UrukHaiEar3 = new ModelRenderer(this, 0, 12);
        this.UrukHaiEar3.setPos(-4.0F, -4.5F, 0.0F);
        this.UrukHaiEar3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(UrukHaiEar3, 0.6108652381980153F, 0.0F, 0.0F);
        this.UrukHaiBrowL = new ModelRenderer(this, 32, 0);
        this.UrukHaiBrowL.setPos(-2.8F, -6.0F, -3.8F);
        this.UrukHaiBrowL.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(UrukHaiBrowL, 0.0F, 0.0F, 0.08726646259971647F);
        this.UrukHaiHead = new ModelRenderer(this, 0, 0);
        this.UrukHaiHead.setPos(0.0F, -1.0F, 0.0F);
        this.UrukHaiHead.addBox(-4.0F, -8.0F, -4.0F, 8, 9, 8, 0.0F);
        this.UrukHaiBrowR = new ModelRenderer(this, 45, 0);
        this.UrukHaiBrowR.setPos(0.9F, -5.8F, -3.8F);
        this.UrukHaiBrowR.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(UrukHaiBrowR, 0.0F, 0.0F, -0.08726646259971647F);
        this.UrukHaiHead.addChild(this.UrukHaiHairBack3);
        this.UrukHaiHead.addChild(this.Tooth3);
        this.rightArm.addChild(this.UrukHaiArmRUpper);
        this.UrukHaiHead.addChild(this.UrukHaiHairBack);
        this.UrukHaiHead.addChild(this.UrukHaiEar2);
        this.UrukHaiHead.addChild(this.UrukHaiEar4);
        this.leftArm.addChild(this.UrukHaiArmLUpper);
        this.UrukHaiHead.addChild(this.UrukHaiHairTop);
        this.UrukHaiHead.addChild(this.UrukHaiEar1);
        this.UrukHaiHead.addChild(this.UrukHaiHairBack2);
        this.UrukHaiHead.addChild(this.Tooth4);
        this.UrukHaiBody.addChild(this.UrukHaiBodyUpper);
        this.UrukHaiHead.addChild(this.UrukHaiNose);
        this.UrukHaiHead.addChild(this.UrukHaiEar3);
        this.UrukHaiHead.addChild(this.UrukHaiBrowL);
        this.UrukHaiHead.addChild(this.UrukHaiBrowR);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.UrukHaiHead);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.UrukHaiBody, this.rightArm, this.leftArm, this.UrukHaiLegR, this.UrukHaiLegL);
    }

    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.rightArmPose = BipedModel.ArmPose.EMPTY;
        this.leftArmPose = BipedModel.ArmPose.EMPTY;
        ItemStack itemstack = entityIn.getItemInHand(Hand.MAIN_HAND);
        if (itemstack.getItem() instanceof net.minecraft.item.SwordItem || itemstack.getItem() instanceof net.minecraft.item.AxeItem && entityIn.isAggressive()) {
            if (entityIn.getMainArm() == HandSide.RIGHT) {
                this.rightArmPose = ArmPose.ITEM;
            } else {
                this.leftArmPose = ArmPose.ITEM;
            }
        }

        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.UrukHaiLegL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.UrukHaiLegR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.UrukHaiHead.yRot = netHeadYaw * 0.017453292F;
        this.UrukHaiHead.xRot = headPitch * 0.017453292F;

        this.rightArm.x = -8.0F;
        this.leftArm.x = 8.0F;
    }

    @Override
    public void translateToHand(HandSide hand, MatrixStack mStack) {
        this.getArm(hand).translateAndRotate(mStack);
        mStack.scale(1, 1, 1);
    }
}