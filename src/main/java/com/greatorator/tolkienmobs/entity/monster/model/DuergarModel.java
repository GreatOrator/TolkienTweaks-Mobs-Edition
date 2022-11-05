package com.greatorator.tolkienmobs.entity.monster.model;

/**
 * Dwarf - GreatOrator
 */
//
//public class DuergarModel<T extends MonsterEntity> extends BipedModel<T> {
//    private final ModelRenderer root;
//    private final ModelRenderer body;
//    private final ModelRenderer head;
//    private final ModelRenderer DwarfBeardMain;
//    private final ModelRenderer DwarfBeard1;
//    private final ModelRenderer DwarfBeard2;
//    private final ModelRenderer DwarfBeard3;
//    private final ModelRenderer DwarfBeard4;
//    private final ModelRenderer DwarfBeard5;
//    private final ModelRenderer DwarfBeard6;
//    private final ModelRenderer DwarfBeard7;
//    private final ModelRenderer DwarfBeard8;
//    private final ModelRenderer DwarfBeard9;
//    private final ModelRenderer DwarfBeard0;
//    private final ModelRenderer DwarfBeard11;
//    private final ModelRenderer DwarfBeard12;
//    private final ModelRenderer DwarfBeard13;
//    private final ModelRenderer DwarfBeard14;
//    private final ModelRenderer DwarfBeard15;
//    private final ModelRenderer DwarfHelmetMain;
//    private final ModelRenderer DwarfHelmet1;
//    private final ModelRenderer DwarfHelmet2;
//    private final ModelRenderer DwarfHelmet3;
//    private final ModelRenderer DwarfHelmet4;
//    private final ModelRenderer DwarfHelmet5;
//    private final ModelRenderer DwarfBrowR;
//    private final ModelRenderer DwarfBrowL;
//    private final ModelRenderer rightLeg;
//    private final ModelRenderer leftLeg;
//    private final ModelRenderer leftArm;
//    private final ModelRenderer rightArm;
//
//    public DuergarModel(float modelSize) {
//        super(modelSize);
//        texWidth = 64;
//        texHeight = 64;
//
//        root = new ModelRenderer(this);
//        root.setPos(0.0F, 0.0F, 0.0F);
//
//
//        body = new ModelRenderer(this);
//        body.setPos(1.0F, 6.0F, 0.0F);
//        root.addChild(body);
//        body.texOffs(16, 16).addBox(-5.0F, 0.0F, -2.0F, 9.0F, 9.0F, 4.0F, 0.0F, false);
//
//        head = new ModelRenderer(this);
//        head.setPos(0.5F, 6.0F, 0.0F);
//        root.addChild(head);
//        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
//
//        DwarfBeardMain = new ModelRenderer(this);
//        DwarfBeardMain.setPos(3.0F, -0.5F, -3.7F);
//        head.addChild(DwarfBeardMain);
//        setRotationAngle(DwarfBeardMain, 0.0F, -0.0156F, 0.0F);
//        DwarfBeardMain.texOffs(0, 62).addBox(-6.5F, -0.5F, -0.5F, 7.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard1 = new ModelRenderer(this);
//        DwarfBeard1.setPos(0.7F, 0.0F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard1);
//        DwarfBeard1.texOffs(0, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 7.0F, 0.0F, false);
//
//        DwarfBeard2 = new ModelRenderer(this);
//        DwarfBeard2.setPos(-6.7F, 0.0F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard2);
//        DwarfBeard2.texOffs(0, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 7.0F, 0.0F, false);
//
//        DwarfBeard3 = new ModelRenderer(this);
//        DwarfBeard3.setPos(0.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard3);
//        DwarfBeard3.texOffs(0, 62).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard4 = new ModelRenderer(this);
//        DwarfBeard4.setPos(-6.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard4);
//        DwarfBeard4.texOffs(0, 62).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard5 = new ModelRenderer(this);
//        DwarfBeard5.setPos(-5.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard5);
//        DwarfBeard5.texOffs(0, 52).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard6 = new ModelRenderer(this);
//        DwarfBeard6.setPos(-0.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard6);
//        DwarfBeard6.texOffs(0, 52).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard7 = new ModelRenderer(this);
//        DwarfBeard7.setPos(-1.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard7);
//        DwarfBeard7.texOffs(0, 52).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard8 = new ModelRenderer(this);
//        DwarfBeard8.setPos(-4.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard8);
//        DwarfBeard8.texOffs(0, 52).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard9 = new ModelRenderer(this);
//        DwarfBeard9.setPos(-3.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard9);
//        DwarfBeard9.texOffs(0, 52).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard0 = new ModelRenderer(this);
//        DwarfBeard0.setPos(-2.5F, 0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard0);
//        DwarfBeard0.texOffs(0, 52).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard11 = new ModelRenderer(this);
//        DwarfBeard11.setPos(-4.5F, -0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard11);
//        setRotationAngle(DwarfBeard11, 0.0F, -0.0157F, 0.0F);
//        DwarfBeard11.texOffs(0, 62).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard12 = new ModelRenderer(this);
//        DwarfBeard12.setPos(0.7F, -0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard12);
//        DwarfBeard12.texOffs(0, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
//
//        DwarfBeard13 = new ModelRenderer(this);
//        DwarfBeard13.setPos(-6.7F, -0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard13);
//        DwarfBeard13.texOffs(0, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
//
//        DwarfBeard14 = new ModelRenderer(this);
//        DwarfBeard14.setPos(0.5F, -0.7F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard14);
//        setRotationAngle(DwarfBeard14, 0.0F, -0.0157F, 0.0F);
//        DwarfBeard14.texOffs(0, 62).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfBeard15 = new ModelRenderer(this);
//        DwarfBeard15.setPos(-2.0F, -1.6F, 0.0F);
//        DwarfBeardMain.addChild(DwarfBeard15);
//        setRotationAngle(DwarfBeard15, 0.0F, -0.0157F, 0.0F);
//        DwarfBeard15.texOffs(0, 62).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfHelmetMain = new ModelRenderer(this);
//        DwarfHelmetMain.setPos(4.5F, -6.5F, -4.5F);
//        head.addChild(DwarfHelmetMain);
//        setRotationAngle(DwarfHelmetMain, 0.0F, -0.0157F, 0.0F);
//        DwarfHelmetMain.texOffs(0, 29).addBox(-9.5F, -0.5F, -0.5F, 10.0F, 1.0F, 10.0F, 0.0F, false);
//
//        DwarfHelmet1 = new ModelRenderer(this);
//        DwarfHelmet1.setPos(0.0F, -2.1F, 0.0F);
//        DwarfHelmetMain.addChild(DwarfHelmet1);
//        DwarfHelmet1.texOffs(0, 40).addBox(-9.0F, 0.0F, 0.0F, 9.0F, 2.0F, 9.0F, 0.0F, false);
//
//        DwarfHelmet2 = new ModelRenderer(this);
//        DwarfHelmet2.setPos(-2.5F, -2.5F, -0.5F);
//        DwarfHelmetMain.addChild(DwarfHelmet2);
//        DwarfHelmet2.texOffs(7, 29).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F, false);
//
//        DwarfHelmet3 = new ModelRenderer(this);
//        DwarfHelmet3.setPos(-3.5F, -1.5F, -0.5F);
//        DwarfHelmetMain.addChild(DwarfHelmet3);
//        DwarfHelmet3.texOffs(27, 29).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 3.0F, 10.0F, 0.0F, false);
//
//        DwarfHelmet4 = new ModelRenderer(this);
//        DwarfHelmet4.setPos(0.5F, -0.5F, 4.5F);
//        DwarfHelmetMain.addChild(DwarfHelmet4);
//        DwarfHelmet4.texOffs(27, 29).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 3.0F, 5.0F, 0.0F, false);
//
//        DwarfHelmet5 = new ModelRenderer(this);
//        DwarfHelmet5.setPos(0.5F, 2.5F, 4.5F);
//        DwarfHelmetMain.addChild(DwarfHelmet5);
//        DwarfHelmet5.texOffs(27, 29).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
//
//        DwarfBrowR = new ModelRenderer(this);
//        DwarfBrowR.setPos(3.0F, -5.5F, -4.0F);
//        head.addChild(DwarfBrowR);
//        setRotationAngle(DwarfBrowR, 0.0F, -0.0157F, 0.0F);
//        DwarfBrowR.texOffs(0, 62).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//        DwarfBrowL = new ModelRenderer(this);
//        DwarfBrowL.setPos(-1.0F, -5.5F, -4.0F);
//        head.addChild(DwarfBrowL);
//        setRotationAngle(DwarfBrowL, 0.0F, -0.0157F, 0.0F);
//        DwarfBrowL.texOffs(0, 62).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
//
//        rightLeg = new ModelRenderer(this);
//        rightLeg.setPos(3.0F, 15.0F, 0.0F);
//        root.addChild(rightLeg);
//        rightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
//
//        leftLeg = new ModelRenderer(this);
//        leftLeg.setPos(-2.0F, 15.0F, 0.0F);
//        root.addChild(leftLeg);
//        leftLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);
//
//        leftArm = new ModelRenderer(this);
//        leftArm.setPos(-5.0F, 8.0F, 0.0F);
//        root.addChild(leftArm);
//        leftArm.texOffs(42, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);
//
//        rightArm = new ModelRenderer(this);
//        rightArm.setPos(6.0F, 8.0F, 0.0F);
//        root.addChild(rightArm);
//        rightArm.texOffs(42, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> headParts() {
//        return ImmutableList.of(this.head);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> bodyParts() {
//        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.leftLeg, this.rightLeg, this.leftArm, this.rightArm, this.body));
//    }
//
//    @Override
//    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        this.rightArmPose = ArmPose.EMPTY;
//        this.leftArmPose = ArmPose.EMPTY;
//        ItemStack itemstack = entityIn.getItemInHand(Hand.MAIN_HAND);
//        if (itemstack.getItem() instanceof net.minecraft.item.SwordItem || itemstack.getItem() instanceof net.minecraft.item.AxeItem && entityIn.isAggressive()) {
//            if (entityIn.getMainArm() == HandSide.RIGHT) {
//                this.rightArmPose = ArmPose.ITEM;
//            } else {
//                this.leftArmPose = ArmPose.ITEM;
//            }
//        }
//        this.swimAmount = entityIn.getSwimAmount(partialTick);
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
//        rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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