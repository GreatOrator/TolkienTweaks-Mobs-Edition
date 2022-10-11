package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Barrow Wight - GreatOrator
 */
public class BarrowWightModel<T extends MonsterEntity> extends BipedModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftLeg;
    private boolean ghostModel;

    public BarrowWightModel(float modelSize, boolean ghostModel) {
        super(modelSize);
        texWidth = 64;
        texHeight = 64;
        this.ghostModel = ghostModel;

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(head, -0.1047F, 0.0873F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        body.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(-5.0F, 2.0F, 0.0F);
        rightArm.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        rightArm.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setPos(5.0F, 2.0F, 0.0F);
        leftArm.texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        leftArm.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setPos(-1.9F, 12.0F, 0.0F);
        rightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        rightLeg.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setPos(1.9F, 12.0F, 0.0F);
        leftLeg.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        leftLeg.texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        if (!ghostModel) {
            bodyParts().forEach(part -> part.visible = false);
            headParts().forEach(part -> part.visible = false);
        }
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.leftLeg, this.rightLeg, this.leftArm, this.rightArm, this.body));
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
        this.swimAmount = entityIn.getSwimAmount(partialTick);
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entityIn.getFallFlyingTicks() > 4;
        boolean flag1 = entityIn.isVisuallySwimming();
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.head.xRot = (-(float)Math.PI / 4F);
        } else if (this.swimAmount > 0.0F) {
            if (flag1) {
                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float)Math.PI / 4F));
            } else {
                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, headPitch * ((float)Math.PI / 180F));
            }
        } else {
            this.head.xRot = headPitch * ((float)Math.PI / 180F);
        }

        this.body.yRot = 0.0F;
        this.rightArm.z = 0.0F;
        this.rightArm.x = -5.0F;
        this.leftArm.z = 0.0F;
        this.leftArm.x = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float)entityIn.getDeltaMovement().lengthSqr();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
        this.rightLeg.zRot = 0.0F;
        this.leftLeg.zRot = 0.0F;
        if (this.riding) {
            this.rightArm.xRot += (-(float)Math.PI / 5F);
            this.leftArm.xRot += (-(float)Math.PI / 5F);
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = ((float)Math.PI / 10F);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        }

        this.rightArm.yRot = 0.0F;
        this.leftArm.yRot = 0.0F;
        boolean flag2 = entityIn.getMainArm() == HandSide.RIGHT;
        boolean flag3 = flag2 ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
        if (flag2 != flag3) {
            this.poseLeftArm(entityIn);
            this.poseRightArm(entityIn);
        } else {
            this.poseRightArm(entityIn);
            this.poseLeftArm(entityIn);
        }

        this.setupAttackAnimation(entityIn, ageInTicks);
        if (this.crouching) {
            this.body.xRot = 0.5F;
            this.rightArm.xRot += 0.4F;
            this.leftArm.xRot += 0.4F;
            this.rightLeg.z = 4.0F;
            this.leftLeg.z = 4.0F;
            this.rightLeg.y = 12.2F;
            this.leftLeg.y = 12.2F;
            this.head.y = 4.2F;
            this.body.y = 3.2F;
            this.leftArm.y = 5.2F;
            this.rightArm.y = 5.2F;
        } else {
            this.body.xRot = 0.0F;
            this.rightLeg.z = 0.1F;
            this.leftLeg.z = 0.1F;
            this.rightLeg.y = 12.0F;
            this.leftLeg.y = 12.0F;
            this.head.y = 0.0F;
            this.body.y = 0.0F;
            this.leftArm.y = 2.0F;
            this.rightArm.y = 2.0F;
        }

        ModelHelper.bobArms(this.rightArm, this.leftArm, ageInTicks);
        if (this.swimAmount > 0.0F) {
            float f1 = limbSwing % 26.0F;
            HandSide handside = this.getAttackArm(entityIn);
            float f2 = handside == HandSide.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            float f3 = handside == HandSide.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            if (f1 < 14.0F) {
                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, 0.0F);
                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, 0.0F);
                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, (float)Math.PI + 1.8707964F * this.quadraticArmUpdate(f1) / this.quadraticArmUpdate(14.0F));
                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, (float)Math.PI - 1.8707964F * this.quadraticArmUpdate(f1) / this.quadraticArmUpdate(14.0F));
            } else if (f1 >= 14.0F && f1 < 22.0F) {
                float f6 = (f1 - 14.0F) / 8.0F;
                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, ((float)Math.PI / 2F) * f6);
                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, ((float)Math.PI / 2F) * f6);
                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, 5.012389F - 1.8707964F * f6);
                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, 1.2707963F + 1.8707964F * f6);
            } else if (f1 >= 22.0F && f1 < 26.0F) {
                float f4 = (f1 - 22.0F) / 4.0F;
                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, (float)Math.PI);
                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, (float)Math.PI);
            }

            float f7 = 0.3F;
            float f5 = 0.33333334F;
            this.leftLeg.xRot = MathHelper.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
            this.rightLeg.xRot = MathHelper.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
        }
        this.hat.copyFrom(this.head);
    }

    private void poseRightArm(T entityIn) {
        switch(this.rightArmPose) {
            case EMPTY:
                this.rightArm.yRot = 0.0F;
                break;
            case BLOCK:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424779F;
                this.rightArm.yRot = (-(float)Math.PI / 6F);
                break;
            case ITEM:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - ((float)Math.PI / 10F);
                this.rightArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float)Math.PI;
                this.rightArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot;
                this.leftArm.yRot = 0.1F + this.head.yRot + 0.4F;
                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                ModelHelper.animateCrossbowCharge(this.rightArm, this.leftArm, entityIn, true);
                break;
            case CROSSBOW_HOLD:
                ModelHelper.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
        }
    }

    private void poseLeftArm(T entityIn) {
        switch(this.leftArmPose) {
            case EMPTY:
                this.leftArm.yRot = 0.0F;
                break;
            case BLOCK:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.9424779F;
                this.leftArm.yRot = ((float)Math.PI / 6F);
                break;
            case ITEM:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - ((float)Math.PI / 10F);
                this.leftArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float)Math.PI;
                this.leftArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot - 0.4F;
                this.leftArm.yRot = 0.1F + this.head.yRot;
                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                ModelHelper.animateCrossbowCharge(this.rightArm, this.leftArm, entityIn, false);
                break;
            case CROSSBOW_HOLD:
                ModelHelper.animateCrossbowHold(this.rightArm, this.leftArm, this.head, false);
        }
    }

    @Override
    protected void setupAttackAnimation(T entityIn, float p_230486_2_) {
        if (!(this.attackTime <= 0.0F)) {
            HandSide handside = this.getAttackArm(entityIn);
            ModelRenderer modelrenderer = this.getArm(handside);
            float f = this.attackTime;
            this.body.yRot = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.body.yRot *= -1.0F;
            }

            this.rightArm.z = MathHelper.sin(this.body.yRot) * 5.0F;
            this.rightArm.x = -MathHelper.cos(this.body.yRot) * 5.0F;
            this.leftArm.z = -MathHelper.sin(this.body.yRot) * 5.0F;
            this.leftArm.x = MathHelper.cos(this.body.yRot) * 5.0F;
            this.rightArm.yRot += this.body.yRot;
            this.leftArm.yRot += this.body.yRot;
            this.leftArm.xRot += this.body.yRot;
            f = 1.0F - this.attackTime;
            f = f * f;
            f = f * f;
            f = 1.0F - f;
            float f1 = MathHelper.sin(f * (float)Math.PI);
            float f2 = MathHelper.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            modelrenderer.xRot = (float)((double)modelrenderer.xRot - ((double)f1 * 1.2D + (double)f2));
            modelrenderer.yRot += this.body.yRot * 2.0F;
            modelrenderer.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * -0.4F;
        }
    }

    @Override
    protected float rotlerpRad(float p_205060_1_, float p_205060_2_, float p_205060_3_) {
        float f = (p_205060_3_ - p_205060_2_) % ((float)Math.PI * 2F);
        if (f < -(float)Math.PI) {
            f += ((float)Math.PI * 2F);
        }

        if (f >= (float)Math.PI) {
            f -= ((float)Math.PI * 2F);
        }

        return p_205060_2_ + p_205060_1_ * f;
    }

    private float quadraticArmUpdate(float p_203068_1_) {
        return -65.0F * p_203068_1_ + p_203068_1_ * p_203068_1_;
    }

    @Override
    public void translateToHand(HandSide hand, MatrixStack mStack) {
        this.getArm(hand).translateAndRotate(mStack);
    }

    @Override
    protected ModelRenderer getArm(HandSide hand) {
        return hand == HandSide.LEFT ? this.leftArm : this.rightArm;
    }

    @Override
    protected HandSide getAttackArm(T entityIn) {
        HandSide handside = entityIn.getMainArm();
        return entityIn.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}