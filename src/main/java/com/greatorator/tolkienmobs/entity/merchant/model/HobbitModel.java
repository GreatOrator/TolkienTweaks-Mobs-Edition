package com.greatorator.tolkienmobs.entity.merchant.model;

/**
 * Hobbit - GreatOrator
 */
//
//public class HobbitModel<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
//    public ModelRenderer HobbitLegL;
//    public ModelRenderer HobbitLegR;
//    public ModelRenderer bipedLeftArm;
//    public ModelRenderer bipedRightArm;
//    public ModelRenderer HobbitBody;
//    public ModelRenderer HobbitHead;
//
//    public HobbitModel() {
//        this.texWidth = 64;
//        this.texHeight = 64;
//        this.HobbitBody = new ModelRenderer(this, 16, 16);
//        this.HobbitBody.setPos(0.0F, 0.0F, 0.0F);
//        this.HobbitBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
//        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
//        this.bipedLeftArm.setPos(-5.0F, 2.0F, 0.0F);
//        this.bipedLeftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
//        this.HobbitLegR = new ModelRenderer(this, 16, 48);
//        this.HobbitLegR.setPos(1.9F, 12.0F, 0.0F);
//        this.HobbitLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
//        this.HobbitLegL = new ModelRenderer(this, 0, 16);
//        this.HobbitLegL.setPos(-1.9F, 12.0F, 0.0F);
//        this.HobbitLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
//        this.bipedRightArm = new ModelRenderer(this, 32, 48);
//        this.bipedRightArm.setPos(5.0F, 2.0F, 0.0F);
//        this.bipedRightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
//        this.HobbitHead = new ModelRenderer(this, 0, 0);
//        this.HobbitHead.setPos(0.0F, 0.0F, 0.0F);
//        this.HobbitHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
//    }
//
//    @Override
//    public ModelRenderer getHead() {
//        return this.HobbitHead;
//    }
//
//    @Override
//    public Iterable<ModelRenderer> parts() {
//        return ImmutableList.of(this.HobbitHead, this.HobbitBody, this.HobbitLegR, this.HobbitLegL, this.bipedLeftArm, this.bipedRightArm);
//    }
//
//    /**
//     * Sets this entity's model rotation angles
//     */
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        boolean flag = false;
//        if (entityIn instanceof AbstractVillagerEntity) {
//            flag = ((AbstractVillagerEntity)entityIn).getUnhappyCounter() > 0;
//        }
//
//        this.HobbitHead.yRot = netHeadYaw * ((float)Math.PI / 180F);
//        this.HobbitHead.xRot = headPitch * ((float)Math.PI / 180F);
//        if (flag) {
//            this.HobbitHead.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
//            this.HobbitHead.xRot = 0.4F;
//        } else {
//            this.HobbitHead.zRot = 0.0F;
//        }
//
//        this.bipedRightArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.bipedLeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
//        this.HobbitLegR.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
//        this.HobbitLegL.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
//        this.HobbitLegR.yRot = 0.0F;
//        this.HobbitLegL.yRot = 0.0F;
//    }
//
//    @Override
//    public void hatVisible(boolean p_217146_1_) {
//
//    }
//
//}