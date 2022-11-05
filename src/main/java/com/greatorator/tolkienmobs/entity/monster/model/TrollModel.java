package com.greatorator.tolkienmobs.entity.monster.model;

/**
 * Cave Troll - GreatOrator
 */
//
//public class TrollModel<T extends MonsterEntity> extends BipedModel<T> {
//    private final ModelRenderer head;
//    private final ModelRenderer body;
//    private final ModelRenderer leftArm;
//    private final ModelRenderer TrollArmLowerL;
//    private final ModelRenderer rightArm;
//    private final ModelRenderer TrollArmLowerR;
//    private final ModelRenderer rightLeg;
//    private final ModelRenderer TrollLegMidR;
//    private final ModelRenderer TrollFootR;
//    private final ModelRenderer TrollNail1;
//    private final ModelRenderer TrollNail2;
//    private final ModelRenderer leftLeg;
//    private final ModelRenderer TrollLegMidL;
//    private final ModelRenderer TrollFootL;
//    private final ModelRenderer TrollNail3;
//    private final ModelRenderer TrollNail4;
//
//    public TrollModel(float modelSize) {
//        super(modelSize);
//        texWidth = 128;
//        texHeight = 64;
//
//        head = new ModelRenderer(this);
//        head.setPos(0.0F, -12.5F, -1.2F);
//        setRotationAngle(head, -0.1745F, 0.0F, 0.0F);
//        head.texOffs(0, 32).addBox(-0.5F, 0.2F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        head.texOffs(48, 14).addBox(-3.5F, -4.5F, -3.0F, 7.0F, 5.0F, 6.0F, 0.0F, false);
//        head.texOffs(47, 0).addBox(-4.5F, -5.0F, -8.2F, 9.0F, 8.0F, 6.0F, 0.0F, false);
//
//        body = new ModelRenderer(this);
//        body.setPos(0.0F, -1.0F, -0.2F);
//        body.texOffs(77, 0).addBox(-9.0F, -12.0F, -3.5F, 18.0F, 12.0F, 7.0F, 0.0F, false);
//        body.texOffs(81, 19).addBox(-8.5F, -0.5F, -3.0F, 17.0F, 11.0F, 6.0F, 0.0F, false);
//
//        leftArm = new ModelRenderer(this);
//        leftArm.setPos(-8.8F, -11.0F, -0.2F);
//        leftArm.texOffs(0, 47).addBox(-6.0F, -2.5F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, true);
//
//        TrollArmLowerL = new ModelRenderer(this);
//        TrollArmLowerL.setPos(-3.0F, 8.0F, 0.0F);
//        leftArm.addChild(TrollArmLowerL);
//        setRotationAngle(TrollArmLowerL, -0.0873F, 0.0F, 0.0F);
//        TrollArmLowerL.texOffs(24, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, true);
//
//        rightArm = new ModelRenderer(this);
//        rightArm.setPos(8.8F, -11.0F, -0.2F);
//        rightArm.texOffs(0, 47).addBox(0.0F, -2.5F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, false);
//
//        TrollArmLowerR = new ModelRenderer(this);
//        TrollArmLowerR.setPos(3.0F, 8.5F, 0.0F);
//        rightArm.addChild(TrollArmLowerR);
//        setRotationAngle(TrollArmLowerR, -0.0873F, 0.0F, 0.0F);
//        TrollArmLowerR.texOffs(24, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);
//
//        rightLeg = new ModelRenderer(this);
//        rightLeg.setPos(6.2F, 8.5F, -0.7F);
//        setRotationAngle(rightLeg, -0.0873F, 0.0F, 0.0F);
//        rightLeg.texOffs(0, 35).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
//
//        TrollLegMidR = new ModelRenderer(this);
//        TrollLegMidR.setPos(0.0F, 6.5F, 0.0F);
//        rightLeg.addChild(TrollLegMidR);
//        setRotationAngle(TrollLegMidR, 0.0873F, 0.0F, 0.0F);
//        TrollLegMidR.texOffs(0, 25).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);
//
//        TrollFootR = new ModelRenderer(this);
//        TrollFootR.setPos(-0.5F, 6.0F, 1.5F);
//        TrollLegMidR.addChild(TrollFootR);
//        TrollFootR.texOffs(24, 36).addBox(-2.5F, 0.0F, -5.0F, 5.0F, 3.0F, 7.0F, 0.0F, false);
//
//        TrollNail1 = new ModelRenderer(this);
//        TrollNail1.setPos(2.3F, 1.5F, -5.0F);
//        TrollFootR.addChild(TrollNail1);
//        setRotationAngle(TrollNail1, 0.0F, 0.0077F, 0.0F);
//        TrollNail1.texOffs(19, 35).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//        TrollNail2 = new ModelRenderer(this);
//        TrollNail2.setPos(-1.3F, 1.5F, -5.0F);
//        TrollFootR.addChild(TrollNail2);
//        TrollNail2.texOffs(19, 35).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//        leftLeg = new ModelRenderer(this);
//        leftLeg.setPos(-5.2F, 8.5F, -0.7F);
//        setRotationAngle(leftLeg, -0.0873F, 0.0F, 0.0F);
//        leftLeg.texOffs(0, 35).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
//
//        TrollLegMidL = new ModelRenderer(this);
//        TrollLegMidL.setPos(0.0F, 6.5F, 0.0F);
//        leftLeg.addChild(TrollLegMidL);
//        setRotationAngle(TrollLegMidL, 0.0873F, 0.0F, 0.0F);
//        TrollLegMidL.texOffs(0, 25).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);
//
//        TrollFootL = new ModelRenderer(this);
//        TrollFootL.setPos(-0.5F, 6.0F, 1.5F);
//        TrollLegMidL.addChild(TrollFootL);
//        TrollFootL.texOffs(24, 36).addBox(-2.5F, 0.0F, -5.0F, 5.0F, 3.0F, 7.0F, 0.0F, false);
//
//        TrollNail3 = new ModelRenderer(this);
//        TrollNail3.setPos(2.3F, 1.5F, -5.0F);
//        TrollFootL.addChild(TrollNail3);
//        setRotationAngle(TrollNail3, 0.0F, 0.0077F, 0.0F);
//        TrollNail3.texOffs(19, 35).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//
//        TrollNail4 = new ModelRenderer(this);
//        TrollNail4.setPos(-1.3F, 1.5F, -5.0F);
//        TrollFootL.addChild(TrollNail4);
//        TrollNail4.texOffs(19, 35).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
//    }
//
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//
//    @Override
//    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        this.rightArmPose = BipedModel.ArmPose.EMPTY;
//        this.leftArmPose = BipedModel.ArmPose.EMPTY;
//        ItemStack itemstack = entityIn.getItemInHand(Hand.MAIN_HAND);
//        if (itemstack.getItem() instanceof net.minecraft.item.SwordItem || itemstack.getItem() instanceof net.minecraft.item.AxeItem && entityIn.isAggressive()) {
//            if (entityIn.getMainArm() == HandSide.RIGHT) {
//                this.rightArmPose = ArmPose.ITEM;
//            } else {
//                this.leftArmPose = ArmPose.ITEM;
//            }
//        }
//        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
//    }
//
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        boolean flag = entityIn.getFallFlyingTicks() > 4;
//        boolean flag1 = entityIn.isVisuallySwimming();
//        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
//        if (flag) {
//            this.head.xRot = (-(float)Math.PI / 4F);
//        } else if (this.swimAmount > 0.0F) {
//            if (flag1) {
//                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float)Math.PI / 4F));
//            } else {
//                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, headPitch * ((float)Math.PI / 180F));
//            }
//        } else {
//            this.head.xRot = headPitch * ((float)Math.PI / 180F);
//        }
//
//        this.body.yRot = 0.0F;
//        float f = 1.0F;
//        if (flag) {
//            f = (float)entityIn.getDeltaMovement().lengthSqr();
//            f = f / 0.2F;
//            f = f * f * f;
//        }
//
//        if (f < 1.0F) {
//            f = 1.0F;
//        }
//
//        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
//        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
//        this.rightArm.zRot = 0.0F;
//        this.leftArm.zRot = 0.0F;
//        this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
//        this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
//        this.rightLeg.yRot = 0.0F;
//        this.leftLeg.yRot = 0.0F;
//        this.rightLeg.zRot = 0.0F;
//        this.leftLeg.zRot = 0.0F;
//        if (this.riding) {
//            this.rightArm.xRot += (-(float)Math.PI / 5F);
//            this.leftArm.xRot += (-(float)Math.PI / 5F);
//            this.rightLeg.xRot = -1.4137167F;
//            this.rightLeg.yRot = ((float)Math.PI / 10F);
//            this.rightLeg.zRot = 0.07853982F;
//            this.leftLeg.xRot = -1.4137167F;
//            this.leftLeg.yRot = (-(float)Math.PI / 10F);
//            this.leftLeg.zRot = -0.07853982F;
//        }
//
//        this.rightArm.yRot = 0.0F;
//        this.leftArm.yRot = 0.0F;
//        boolean flag2 = entityIn.getMainArm() == HandSide.RIGHT;
//        boolean flag3 = flag2 ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
//        if (flag2 != flag3) {
//            this.poseLeftArm(entityIn);
//            this.poseRightArm(entityIn);
//        } else {
//            this.poseRightArm(entityIn);
//            this.poseLeftArm(entityIn);
//        }
//
//        this.setupAttackAnimation(entityIn, ageInTicks);
//        if (this.crouching) {
//            this.body.xRot = 0.5F;
//            this.rightArm.xRot += 0.4F;
//            this.leftArm.xRot += 0.4F;
//        } else {
//            this.body.xRot = 0.0F;
//        }
//
//        ModelHelper.bobArms(this.rightArm, this.leftArm, ageInTicks);
//        if (this.swimAmount > 0.0F) {
//            float f1 = limbSwing % 26.0F;
//            HandSide handside = this.getAttackArm(entityIn);
//            float f2 = handside == HandSide.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
//            float f3 = handside == HandSide.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
//            if (f1 < 14.0F) {
//                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, 0.0F);
//                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, 0.0F);
//                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
//                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
//                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, (float)Math.PI + 1.8707964F * this.quadraticArmUpdate(f1) / this.quadraticArmUpdate(14.0F));
//                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, (float)Math.PI - 1.8707964F * this.quadraticArmUpdate(f1) / this.quadraticArmUpdate(14.0F));
//            } else if (f1 >= 14.0F && f1 < 22.0F) {
//                float f6 = (f1 - 14.0F) / 8.0F;
//                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, ((float)Math.PI / 2F) * f6);
//                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, ((float)Math.PI / 2F) * f6);
//                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
//                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
//                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, 5.012389F - 1.8707964F * f6);
//                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, 1.2707963F + 1.8707964F * f6);
//            } else if (f1 >= 22.0F && f1 < 26.0F) {
//                float f4 = (f1 - 22.0F) / 4.0F;
//                this.leftArm.xRot = this.rotlerpRad(f3, this.leftArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
//                this.rightArm.xRot = MathHelper.lerp(f2, this.rightArm.xRot, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
//                this.leftArm.yRot = this.rotlerpRad(f3, this.leftArm.yRot, (float)Math.PI);
//                this.rightArm.yRot = MathHelper.lerp(f2, this.rightArm.yRot, (float)Math.PI);
//                this.leftArm.zRot = this.rotlerpRad(f3, this.leftArm.zRot, (float)Math.PI);
//                this.rightArm.zRot = MathHelper.lerp(f2, this.rightArm.zRot, (float)Math.PI);
//            }
//
//            float f7 = 0.3F;
//            float f5 = 0.33333334F;
//            this.leftLeg.xRot = MathHelper.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
//            this.rightLeg.xRot = MathHelper.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
//        }
//        this.hat.copyFrom(this.head);
//    }
//
//    private void poseRightArm(T entityIn) {
//        switch(this.rightArmPose) {
//            case EMPTY:
//                this.rightArm.yRot = 0.0F;
//                break;
//            case BLOCK:
//                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424779F;
//                this.rightArm.yRot = (-(float)Math.PI / 6F);
//                break;
//            case ITEM:
//                this.rightArm.xRot = this.rightArm.xRot * 0.5F - ((float)Math.PI / 10F);
//                this.rightArm.yRot = 0.0F;
//                break;
//            case THROW_SPEAR:
//                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float)Math.PI;
//                this.rightArm.yRot = 0.0F;
//                break;
//            case BOW_AND_ARROW:
//                this.rightArm.yRot = -0.1F + this.head.yRot;
//                this.leftArm.yRot = 0.1F + this.head.yRot + 0.4F;
//                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                break;
//            case CROSSBOW_CHARGE:
//                ModelHelper.animateCrossbowCharge(this.rightArm, this.leftArm, entityIn, true);
//                break;
//            case CROSSBOW_HOLD:
//                ModelHelper.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
//        }
//    }
//
//    private void poseLeftArm(T entityIn) {
//        switch(this.leftArmPose) {
//            case EMPTY:
//                this.leftArm.yRot = 0.0F;
//                break;
//            case BLOCK:
//                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.9424779F;
//                this.leftArm.yRot = ((float)Math.PI / 6F);
//                break;
//            case ITEM:
//                this.leftArm.xRot = this.leftArm.xRot * 0.5F - ((float)Math.PI / 10F);
//                this.leftArm.yRot = 0.0F;
//                break;
//            case THROW_SPEAR:
//                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float)Math.PI;
//                this.leftArm.yRot = 0.0F;
//                break;
//            case BOW_AND_ARROW:
//                this.rightArm.yRot = -0.1F + this.head.yRot - 0.4F;
//                this.leftArm.yRot = 0.1F + this.head.yRot;
//                this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                this.leftArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
//                break;
//            case CROSSBOW_CHARGE:
//                ModelHelper.animateCrossbowCharge(this.rightArm, this.leftArm, entityIn, false);
//                break;
//            case CROSSBOW_HOLD:
//                ModelHelper.animateCrossbowHold(this.rightArm, this.leftArm, this.head, false);
//        }
//    }
//
//    @Override
//    protected void setupAttackAnimation(T entityIn, float p_230486_2_) {
//        if (!(this.attackTime <= 0.0F)) {
//            HandSide handside = this.getAttackArm(entityIn);
//            ModelRenderer modelrenderer = this.getArm(handside);
//            float f = this.attackTime;
//            this.body.yRot = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
//            if (handside == HandSide.LEFT) {
//                this.body.yRot *= -1.0F;
//            }
//
//            this.rightArm.z = MathHelper.sin(this.body.yRot) * 5.0F;
//            this.rightArm.x = -MathHelper.cos(this.body.yRot) * 5.0F;
//            this.leftArm.z = -MathHelper.sin(this.body.yRot) * 5.0F;
//            this.leftArm.x = MathHelper.cos(this.body.yRot) * 5.0F;
//            this.rightArm.yRot += this.body.yRot;
//            this.leftArm.yRot += this.body.yRot;
//            this.leftArm.xRot += this.body.yRot;
//            f = 1.0F - this.attackTime;
//            f = f * f;
//            f = f * f;
//            f = 1.0F - f;
//            float f1 = MathHelper.sin(f * (float)Math.PI);
//            float f2 = MathHelper.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
//            modelrenderer.xRot = (float)((double)modelrenderer.xRot - ((double)f1 * 1.2D + (double)f2));
//            modelrenderer.yRot += this.body.yRot * 2.0F;
//            modelrenderer.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * -0.4F;
//        }
//    }
//
//    @Override
//    protected float rotlerpRad(float p_205060_1_, float p_205060_2_, float p_205060_3_) {
//        float f = (p_205060_3_ - p_205060_2_) % ((float)Math.PI * 2F);
//        if (f < -(float)Math.PI) {
//            f += ((float)Math.PI * 2F);
//        }
//
//        if (f >= (float)Math.PI) {
//            f -= ((float)Math.PI * 2F);
//        }
//
//        return p_205060_2_ + p_205060_1_ * f;
//    }
//
//    private float quadraticArmUpdate(float p_203068_1_) {
//        return -65.0F * p_203068_1_ + p_203068_1_ * p_203068_1_;
//    }
//
//    @Override
//    public void translateToHand(HandSide hand, MatrixStack mStack) {
//        this.getArm(hand).translateAndRotate(mStack);
//        mStack.scale(1.5F, 1.5F, 1.5F);
//        mStack.translate(0.18, 0.2, 0);
//    }
//
//    @Override
//    protected ModelRenderer getArm(HandSide hand) {
//        return hand == HandSide.LEFT ? this.leftArm : this.rightArm;
//    }
//
//    @Override
//    protected HandSide getAttackArm(T entityIn) {
//        HandSide handside = entityIn.getMainArm();
//        return entityIn.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
//    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//}