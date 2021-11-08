package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TTMContent;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Mordor Orc - GreatOrator
 */
public class ModelTTMMordorOrc<T extends MonsterEntity> extends BipedModel<T> {
    public ModelRenderer OrcLegL;
    public ModelRenderer OrcHead;
    public ModelRenderer OrcBody;
    public ModelRenderer OrcLegR;
    public ModelRenderer OrcArmLUpper;
    public ModelRenderer OrcNose;
    public ModelRenderer OrcBrowL;
    public ModelRenderer OrcBrowR;
    public ModelRenderer OrcJaw;
    public ModelRenderer Tooth1;
    public ModelRenderer Tooth2;
    public ModelRenderer Tooth3;
    public ModelRenderer Tooth4;
    public ModelRenderer OrcHairTop;
    public ModelRenderer OrcHairBack;
    public ModelRenderer OrcEar1;
    public ModelRenderer OrcEar2;
    public ModelRenderer OrcEar3;
    public ModelRenderer OrcEar4;
    public ModelRenderer OrcBodyUpper;
    public ModelRenderer OrcArmRUpper;
    private boolean ghostModel;

    public ModelTTMMordorOrc(float modelSize, boolean ghostModel) {
        super(modelSize);
        this.ghostModel = ghostModel;
        this.texWidth = 128;
        this.texHeight = 64;
        this.Tooth1 = new ModelRenderer(this, 0, 0);
        this.Tooth1.setPos(3.7F, -2.0F, -3.7F);
        this.Tooth1.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcHead = new ModelRenderer(this, 0, 0);
        this.OrcHead.setPos(0.0F, -2.0F, 0.0F);
        this.OrcHead.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8, 0.0F);
        this.OrcArmLUpper = new ModelRenderer(this, 32, 37);
        this.OrcArmLUpper.setPos(-1.0F, -0.5F, -1.0F);
        this.OrcArmLUpper.addBox(-3.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.OrcArmRUpper = new ModelRenderer(this, 56, 37);
        this.OrcArmRUpper.setPos(-1.0F, -0.5F, -1.0F);
        this.OrcArmRUpper.addBox(-1.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.OrcBody = new ModelRenderer(this, 0, 48);
        this.OrcBody.setPos(0.0F, 0.0F, 0.0F);
        this.OrcBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.OrcBrowR = new ModelRenderer(this, 45, 0);
        this.OrcBrowR.setPos(0.9F, -6.0F, -3.8F);
        this.OrcBrowR.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.OrcHairBack = new ModelRenderer(this, 52, 10);
        this.OrcHairBack.setPos(0.0F, -8.0F, 4.0F);
        this.OrcHairBack.addBox(-1.5F, -0.5F, -0.5F, 3, 10, 1, 0.0F);
        this.Tooth2 = new ModelRenderer(this, 0, 4);
        this.Tooth2.setPos(-3.7F, -2.0F, -3.7F);
        this.Tooth2.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.leftArm = new ModelRenderer(this, 40, 48);
        this.leftArm.setPos(-7.0F, 2.5F, 0.0F);
        this.leftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcBrowL = new ModelRenderer(this, 32, 0);
        this.OrcBrowL.setPos(-2.8F, -6.0F, -3.8F);
        this.OrcBrowL.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.Tooth3 = new ModelRenderer(this, 4, 0);
        this.Tooth3.setPos(2.2F, -1.0F, -3.7F);
        this.Tooth3.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcHairTop = new ModelRenderer(this, 32, 10);
        this.OrcHairTop.setPos(0.0F, -8.0F, -2.5F);
        this.OrcHairTop.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 7, 0.0F);
        this.OrcLegR = new ModelRenderer(this, 72, 48);
        this.OrcLegR.setPos(1.9F, 12.0F, 0.0F);
        this.OrcLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightArm = new ModelRenderer(this, 24, 48);
        this.rightArm.setPos(7.0F, 2.5F, 0.0F);
        this.rightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcNose = new ModelRenderer(this, 25, 0);
        this.OrcNose.setPos(0.0F, -3.5F, -3.7F);
        this.OrcNose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(OrcNose, 0.7853981633974483F, -0.6684611035138281F, -3.6651914291880923F);
        this.OrcBodyUpper = new ModelRenderer(this, 0, 36);
        this.OrcBodyUpper.setPos(-1.0F, 0.0F, -1.0F);
        this.OrcBodyUpper.addBox(-4.0F, 0.0F, -2.0F, 10, 6, 6, 0.0F);
        this.OrcLegL = new ModelRenderer(this, 56, 48);
        this.OrcLegL.setPos(-1.9F, 12.0F, 0.0F);
        this.OrcLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcJaw = new ModelRenderer(this, 32, 3);
        this.OrcJaw.setPos(0.0F, 0.0F, 0.0F);
        this.OrcJaw.addBox(-4.5F, -1.0F, -4.5F, 9, 3, 4, 0.0F);
        this.Tooth4 = new ModelRenderer(this, 4, 4);
        this.Tooth4.setPos(-2.2F, -1.0F, -3.7F);
        this.Tooth4.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcEar1 = new ModelRenderer(this, 0, 12);
        this.OrcEar1.setPos(4.0F, -4.5F, 0.0F);
        this.OrcEar1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(OrcEar1, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcEar2 = new ModelRenderer(this, 0, 12);
        this.OrcEar2.setPos(4.0F, -3.7F, 0.6F);
        this.OrcEar2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(OrcEar2, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcEar3 = new ModelRenderer(this, 0, 12);
        this.OrcEar3.setPos(-4.0F, -4.5F, 0.0F);
        this.OrcEar3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(OrcEar3, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcEar4 = new ModelRenderer(this, 0, 12);
        this.OrcEar4.setPos(-4.0F, -3.7F, 0.6F);
        this.OrcEar4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(OrcEar4, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcHead.addChild(this.OrcBrowL);
        this.OrcHead.addChild(this.OrcEar4);
        this.OrcHead.addChild(this.Tooth4);
        this.OrcHead.addChild(this.OrcBrowR);
        this.OrcHead.addChild(this.OrcEar1);
        this.OrcHead.addChild(this.OrcNose);
        this.OrcHead.addChild(this.OrcEar2);
        this.leftArm.addChild(this.OrcArmLUpper);
        this.OrcHead.addChild(this.OrcJaw);
        this.OrcHead.addChild(this.OrcHairTop);
        this.OrcHead.addChild(this.OrcHairBack);
        this.OrcHead.addChild(this.Tooth1);
        this.OrcHead.addChild(this.Tooth2);
        this.OrcBody.addChild(this.OrcBodyUpper);
        this.OrcHead.addChild(this.OrcEar3);
        this.OrcHead.addChild(this.Tooth3);
        this.rightArm.addChild(this.OrcArmRUpper);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.OrcHead);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.OrcBody, this.rightArm, this.rightArm, this.leftArm, this.OrcLegR, this.OrcLegL);
    }

    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.rightArmPose = BipedModel.ArmPose.EMPTY;
        this.leftArmPose = BipedModel.ArmPose.EMPTY;
        ItemStack itemstack = entityIn.getItemInHand(Hand.MAIN_HAND);
        if (itemstack.getItem() == TTMContent.SWORD_MORGULIRON.get() && entityIn.isAggressive()) {
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
        ItemStack stack = entityIn.getMainHandItem();
        if (this.riding) {
            this.rightArm.xRot = -0.62831855F;
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = -0.62831855F;
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.OrcLegL.xRot = -1.4137167F;
            this.OrcLegL.yRot = 0.31415927F;
            this.OrcLegL.zRot = 0.07853982F;
            this.OrcLegR.xRot = -1.4137167F;
            this.OrcLegR.yRot = -0.31415927F;
            this.OrcLegR.zRot = -0.07853982F;
        }else {
            this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

            this.OrcLegL.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.OrcLegR.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

            this.OrcHead.yRot = netHeadYaw * 0.017453292F;
            this.OrcHead.xRot = headPitch * 0.017453292F;

        }

        if (entityIn.isAggressive() && (stack.isEmpty() || stack.getItem() != Items.BOW)) {
            float lvt_8_1_ = MathHelper.sin(this.attackTime * 3.1415927F);
            float lvt_9_1_ = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * 3.1415927F);
            this.rightArm.zRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightArm.yRot = -(0.1F - lvt_8_1_ * 0.6F);
            this.leftArm.yRot = 0.1F - lvt_8_1_ * 0.6F;
            this.rightArm.xRot = -1.5707964F;
            this.leftArm.xRot = -1.5707964F;
            ModelRenderer var10000 = this.rightArm;
            var10000.xRot -= lvt_8_1_ * 1.2F - lvt_9_1_ * 0.4F;
            var10000 = this.leftArm;
            var10000.xRot -= lvt_8_1_ * 1.2F - lvt_9_1_ * 0.4F;
            ModelHelper.bobArms(this.rightArm, this.leftArm, ageInTicks);
        }
        this.rightArm.x = -8.0F;
        this.leftArm.x = 8.0F;
    }

    @Override
    public void translateToHand(HandSide hand, MatrixStack mStack) {
        this.getArm(hand).translateAndRotate(mStack);
        mStack.scale(1, 1, 1);
    }
}