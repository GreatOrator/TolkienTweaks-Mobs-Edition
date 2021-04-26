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
 * Human - GreatOrator
 */
public class ModelTTMHuman<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
    public ModelRenderer HumanBody;
    public ModelRenderer HumanHead;
    public ModelRenderer HumanLegRight;
    public ModelRenderer HumanLegLeft;
    private ModelRenderer bipedRightArm;
    private ModelRenderer bipedLeftArm;

    public ModelTTMHuman() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.HumanHead = new ModelRenderer(this, 0, 0);
        this.HumanHead.setPos(0.0F, 0.0F, 0.0F);
        this.HumanHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setPos(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanBody = new ModelRenderer(this, 16, 16);
        this.HumanBody.setPos(0.0F, 0.0F, 0.0F);
        this.HumanBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.HumanLegLeft = new ModelRenderer(this, 16, 48);
        this.HumanLegLeft.setPos(1.9F, 12.0F, 0.0F);
        this.HumanLegLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanLegRight = new ModelRenderer(this, 0, 16);
        this.HumanLegRight.setPos(-1.9F, 12.0F, 0.0F);
        this.HumanLegRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setPos(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HumanBody.addChild(this.HumanHead);
        this.HumanBody.addChild(this.bipedLeftArm);
        this.HumanBody.addChild(this.HumanLegLeft);
        this.HumanBody.addChild(this.HumanLegRight);
        this.HumanBody.addChild(this.bipedRightArm);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.HumanHead, this.HumanBody, this.HumanLegRight, this.HumanLegLeft, this.bipedLeftArm, this.bipedRightArm);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity)entityIn).getUnhappyCounter() > 0;
        }

        this.HumanHead.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.HumanHead.xRot = headPitch * ((float)Math.PI / 180F);
        if (flag) {
            this.HumanHead.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.HumanHead.xRot = 0.4F;
        } else {
            this.HumanHead.zRot = 0.0F;
        }

        this.bipedRightArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.bipedLeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.HumanLegRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.HumanLegLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.HumanLegRight.yRot = 0.0F;
        this.HumanLegLeft.yRot = 0.0F;
    }

    public ModelRenderer getHead() {
        return this.HumanHead;
    }

    @Override
    public void hatVisible(boolean p_217146_1_) {

    }
}