package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class ModelTTMBrigand<T extends MonsterEntity> extends BipedModel<T> {
    public ModelRenderer leftSleeve;
    public ModelRenderer rightPants;
    public ModelRenderer rightSleeve;
    public ModelRenderer leftPants;
    public ModelRenderer jacket;
    private boolean ghostModel;

    public ModelTTMBrigand(float modelSize, boolean p_i46303_2_) {
        super(modelSize);
        this.ghostModel = p_i46303_2_;
        this.texWidth = 64;
        this.texHeight = 64;
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.jacket = new ModelRenderer(this, 16, 32);
        this.jacket.setPos(0.0F, 0.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.rightArm = new ModelRenderer(this, 40, 16);
        this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 48, 48);
        this.leftSleeve.setPos(5.0F, 2.0F, 0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.rightSleeve = new ModelRenderer(this, 40, 32);
        this.rightSleeve.setPos(-5.0F, 2.0F, 0.0F);
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftArm = new ModelRenderer(this, 32, 48);
        this.leftArm.setPos(5.0F, 2.0F, 0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.hat = new ModelRenderer(this, 32, 0);
        this.hat.setPos(0.0F, 0.0F, 0.0F);
        this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.setPos(-1.9F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightPants = new ModelRenderer(this, 0, 32);
        this.rightPants.setPos(-1.9F, 12.0F, 0.0F);
        this.rightPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftPants = new ModelRenderer(this, 0, 48);
        this.leftPants.setPos(1.9F, 12.0F, 0.0F);
        this.leftPants.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftLeg = new ModelRenderer(this, 16, 48);
        this.leftLeg.setPos(1.9F, 12.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        if (!ghostModel) {
            bodyParts().forEach(part -> part.visible = true);
            headParts().forEach(part -> part.visible = true);
        }
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket));
    }

    @Override
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

        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.jacket.copyFrom(this.body);

        this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    @Override
    public void translateToHand(HandSide hand, MatrixStack mStack) {
        this.getArm(hand).translateAndRotate(mStack);
        mStack.scale(1.0F, 1.0F, 1.0F);
    }
}