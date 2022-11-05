package com.greatorator.tolkienmobs.entity.ambient.model;

/**
 * Frog - GreatOrator
 */
//
//public class FrogModel<E extends FrogEntity> extends EntityModel<FrogEntity> {
//    private final ModelRenderer head;
//    private final ModelRenderer Box_r1;
//    private final ModelRenderer Box_r2;
//    private final ModelRenderer rearFootRight;
//    private final ModelRenderer haunchRight;
//    private final ModelRenderer body;
//    private final ModelRenderer frontLegLeft;
//    private final ModelRenderer rearFootLeft;
//    private final ModelRenderer haunchLeft;
//    private final ModelRenderer frontLegRight;
//    private float jumpRotation;
//
//    public FrogModel() {
//        texWidth = 64;
//        texHeight = 32;
//
//        head = new ModelRenderer(this);
//        head.setPos(0.0F, 16.0F, -1.0F);
//        head.texOffs(32, 0).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
//
//        Box_r1 = new ModelRenderer(this);
//        Box_r1.setPos(-2.0F, 8.0F, -2.0F);
//        head.addChild(Box_r1);
//        setRotationAngle(Box_r1, 0.0F, -1.5708F, 0.0F);
//        Box_r1.texOffs(58, 3).addBox(-1.5F, -13.25F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
//
//        Box_r2 = new ModelRenderer(this);
//        Box_r2.setPos(-2.0F, 8.0F, -2.0F);
//        head.addChild(Box_r2);
//        setRotationAngle(Box_r2, 0.0F, 1.5708F, 0.0F);
//        Box_r2.texOffs(58, 0).addBox(-0.5F, -13.25F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
//
//        rearFootRight = new ModelRenderer(this);
//        rearFootRight.setPos(-3.0F, 17.5F, 3.7F);
//        rearFootRight.texOffs(8, 24).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, 0.0F, false);
//
//        haunchRight = new ModelRenderer(this);
//        haunchRight.setPos(-3.0F, 17.5F, 3.7F);
//        setRotationAngle(haunchRight, -0.3665F, 0.0F, 0.0F);
//        haunchRight.texOffs(16, 15).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F, 0.0F, false);
//
//        body = new ModelRenderer(this);
//        body.setPos(0.0F, 19.0F, 8.0F);
//        setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
//        body.texOffs(0, 0).addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F, 0.0F, false);
//
//        frontLegLeft = new ModelRenderer(this);
//        frontLegLeft.setPos(3.0F, 17.0F, -1.0F);
//        setRotationAngle(frontLegLeft, -0.192F, 0.0F, 0.0F);
//        frontLegLeft.texOffs(0, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
//
//        rearFootLeft = new ModelRenderer(this);
//        rearFootLeft.setPos(3.0F, 17.5F, 3.7F);
//        rearFootLeft.texOffs(8, 24).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, 0.0F, false);
//
//        haunchLeft = new ModelRenderer(this);
//        haunchLeft.setPos(3.0F, 17.5F, 3.7F);
//        setRotationAngle(haunchLeft, -0.3665F, 0.0F, 0.0F);
//        haunchLeft.texOffs(16, 15).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F, 0.0F, false);
//
//        frontLegRight = new ModelRenderer(this);
//        frontLegRight.setPos(-3.0F, 17.0F, -1.0F);
//        setRotationAngle(frontLegRight, -0.192F, 0.0F, 0.0F);
//        frontLegRight.texOffs(0, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
//        if (this.young) {
//            float f = 1.5F;
//            matrixStackIn.pushPose();
//            matrixStackIn.scale(0.56666666F, 0.56666666F, 0.56666666F);
//            matrixStackIn.translate(0.0D, 1.375D, 0.125D);
//            ImmutableList.of(this.head).forEach((p_228292_8_) -> {
//                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//            });
//            matrixStackIn.popPose();
//            matrixStackIn.pushPose();
//            matrixStackIn.scale(0.4F, 0.4F, 0.4F);
//            matrixStackIn.translate(0.0D, 2.25D, 0.0D);
//            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.body, this.haunchLeft, this.haunchRight, this.frontLegLeft, this.frontLegRight).forEach((p_228291_8_) -> {
//                p_228291_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//            });
//            matrixStackIn.popPose();
//        } else {
//            matrixStackIn.pushPose();
//            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
//            matrixStackIn.translate(0.0D, 1.0D, 0.0D);
//            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.body, this.haunchLeft, this.haunchRight, this.frontLegLeft, this.frontLegRight, this.head).forEach((p_228290_8_) -> {
//                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//            });
//            matrixStackIn.popPose();
//        }
//    }
//
//    @Override
//    public void setupAnim(FrogEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        float f = netHeadYaw - (float)entityIn.tickCount;
//        this.head.xRot = headPitch * ((float)Math.PI / 180F);
//        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
//        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float)Math.PI);
//        this.haunchLeft.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
//        this.haunchRight.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
//        this.rearFootLeft.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
//        this.rearFootRight.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
//        this.frontLegLeft.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
//        this.frontLegRight.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
//    }
//
//    @Override
//    public void prepareMobModel(FrogEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
//        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
//    }
//}