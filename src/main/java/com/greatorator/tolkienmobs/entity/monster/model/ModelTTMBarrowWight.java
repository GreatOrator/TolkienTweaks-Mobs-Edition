package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Barrow Wight - GreatOrator
 */
public class ModelTTMBarrowWight<T extends MonsterEntity> extends BipedModel<T> {
    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedDeadmau5Head;
    public ModelRenderer bipedLeftLegwear;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedBodyWear;
    private boolean ghostModel;

    public ModelTTMBarrowWight(float modelSize, boolean ghostModel) {
        super(modelSize);
        this.ghostModel = ghostModel;
            this.texWidth = 64;
            this.texHeight = 64;
            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.setPos(-5.0F, 2.0F, 0.0F);
            this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
            this.rightArm = new ModelRenderer(this, 40, 16);
            this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
            this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
            this.bipedBody = new ModelRenderer(this, 16, 16);
            this.bipedBody.setPos(0.0F, 0.0F, 0.0F);
            this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
            this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
            this.bipedRightLegwear.setPos(-1.9F, 12.0F, 0.0F);
            this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
            this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
            this.bipedLeftLegwear.setPos(1.9F, 12.0F, 0.0F);
            this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
            this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
            this.bipedLeftLeg.setPos(1.9F, 12.0F, 0.0F);
            this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.setPos(5.0F, 2.0F, 0.0F);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
            this.bipedDeadmau5Head = new ModelRenderer(this, 32, 0);
            this.bipedDeadmau5Head.setPos(0.0F, 0.0F, 0.0F);
            this.bipedDeadmau5Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
            this.bipedBodyWear = new ModelRenderer(this, 16, 32);
            this.bipedBodyWear.setPos(0.0F, 0.0F, 0.0F);
            this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
            this.bipedRightLeg = new ModelRenderer(this, 0, 16);
            this.bipedRightLeg.setPos(-1.9F, 12.0F, 0.0F);
            this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
            this.bipedHead = new ModelRenderer(this, 0, 0);
            this.bipedHead.setPos(0.0F, 0.0F, 0.0F);
            this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
            this.leftArm = new ModelRenderer(this, 32, 48);
            this.leftArm.setPos(5.0F, 2.0F, 0.0F);
            this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.bipedHead);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.bipedBody, this.rightArm, this.leftArm, this.bipedRightLeg, this.bipedLeftLeg);
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

        this.bipedLeftLegwear.copyFrom(this.bipedLeftLeg);
        this.bipedRightLegwear.copyFrom(this.bipedRightLeg);
        this.bipedLeftArmwear.copyFrom(this.leftArm);
        this.bipedRightArmwear.copyFrom(this.rightArm);
        this.bipedBodyWear.copyFrom(this.bipedBody);

        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.bipedLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.bipedHead.yRot = netHeadYaw * 0.017453292F;
        this.bipedHead.xRot = headPitch * 0.017453292F;

        this.rightArm.x = -5.0F;
        this.leftArm.x = 5.0F;
    }
}