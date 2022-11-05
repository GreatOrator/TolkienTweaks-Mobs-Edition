package com.greatorator.tolkienmobs.entity.boss.fellbeast.render;

//
//@OnlyIn(Dist.CLIENT)
//public class FellBeastRender extends EntityRenderer<FellBeastEntity> {
//   public static final ResourceLocation FELL_CRYSTAL_BEAM_LOCATION = new ResourceLocation(MODID + ":textures/entity/morgul_crystal/morgul_crystal_beam.png");
//   private static final ResourceLocation FELLBEAST_EXPLODING_LOCATION = new ResourceLocation(MODID + ":textures/entity/fellbeast/fellbeast_exploding.png");
//   private static final ResourceLocation FELLBEAST_LOCATION = new ResourceLocation(MODID + ":textures/entity/fellbeast/fellbeast.png");
//   private static final ResourceLocation FELLBEAST_EYES_LOCATION = new ResourceLocation(MODID + ":textures/entity/fellbeast/fellbeast_eyes.png");
//   private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(FELLBEAST_LOCATION);
//   private static final RenderType DECAL = RenderType.entityDecal(FELLBEAST_LOCATION);
//   private static final RenderType EYES = RenderType.eyes(FELLBEAST_EYES_LOCATION);
//   private static final RenderType BEAM = RenderType.entitySmoothCutout(FELL_CRYSTAL_BEAM_LOCATION);
//   private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);
//   private final FellBeastRender.FellBeastModel model = new FellBeastRender.FellBeastModel();
//
//   public FellBeastRender(EntityRendererManager rendererManager) {
//      super(rendererManager);
//      this.shadowRadius = 0.5F;
//   }
//
//   @Override
//   public void render(FellBeastEntity beastEntity, float vector, float angle, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int type) {
//      matrixStack.pushPose();
//      float f = (float)beastEntity.getLatencyPos(7, angle)[0];
//      float f1 = (float)(beastEntity.getLatencyPos(5, angle)[1] - beastEntity.getLatencyPos(10, angle)[1]);
//      matrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));
//      matrixStack.mulPose(Vector3f.XP.rotationDegrees(f1 * 10.0F));
//      matrixStack.translate(0.0D, 0.0D, 1.0D);
//      matrixStack.scale(-1.0F, -1.0F, 1.0F);
//      matrixStack.translate(0.0D, (double)-1.501F, 0.0D);
//      boolean flag = beastEntity.hurtTime > 0;
//      this.model.prepareMobModel(beastEntity, 0.0F, 0.0F, angle);
//      if (beastEntity.fellbeastDeathTime > 0) {
//         float f2 = (float)beastEntity.fellbeastDeathTime / 200.0F;
//         IVertexBuilder ivertexbuilder = renderTypeBuffer.getBuffer(RenderType.dragonExplosionAlpha(FELLBEAST_EXPLODING_LOCATION, f2));
//         this.model.renderToBuffer(matrixStack, ivertexbuilder, type, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//         IVertexBuilder ivertexbuilder1 = renderTypeBuffer.getBuffer(DECAL);
//         this.model.renderToBuffer(matrixStack, ivertexbuilder1, type, OverlayTexture.pack(0.0F, flag), 1.0F, 1.0F, 1.0F, 1.0F);
//      } else {
//         IVertexBuilder ivertexbuilder3 = renderTypeBuffer.getBuffer(RENDER_TYPE);
//         this.model.renderToBuffer(matrixStack, ivertexbuilder3, type, OverlayTexture.pack(0.0F, flag), 1.0F, 1.0F, 1.0F, 1.0F);
//      }
//
//      IVertexBuilder ivertexbuilder4 = renderTypeBuffer.getBuffer(EYES);
//      this.model.renderToBuffer(matrixStack, ivertexbuilder4, type, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//      if (beastEntity.fellbeastDeathTime > 0) {
//         float f5 = ((float)beastEntity.fellbeastDeathTime + angle) / 200.0F;
//         float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
//         Random random = new Random(432L);
//         IVertexBuilder ivertexbuilder2 = renderTypeBuffer.getBuffer(RenderType.lightning());
//         matrixStack.pushPose();
//         matrixStack.translate(0.0D, -1.0D, -2.0D);
//
//         for(int i = 0; (float)i < (f5 + f5 * f5) / 2.0F * 60.0F; ++i) {
//            matrixStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
//            matrixStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
//            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F));
//            matrixStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
//            matrixStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
//            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F + f5 * 90.0F));
//            float f3 = random.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
//            float f4 = random.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
//            Matrix4f matrix4f = matrixStack.last().pose();
//            int j = (int)(255.0F * (1.0F - f7));
//            vertex01(ivertexbuilder2, matrix4f, j);
//            vertex2(ivertexbuilder2, matrix4f, f3, f4);
//            vertex3(ivertexbuilder2, matrix4f, f3, f4);
//            vertex01(ivertexbuilder2, matrix4f, j);
//            vertex3(ivertexbuilder2, matrix4f, f3, f4);
//            vertex4(ivertexbuilder2, matrix4f, f3, f4);
//            vertex01(ivertexbuilder2, matrix4f, j);
//            vertex4(ivertexbuilder2, matrix4f, f3, f4);
//            vertex2(ivertexbuilder2, matrix4f, f3, f4);
//         }
//
//         matrixStack.popPose();
//      }
//
//      matrixStack.popPose();
//      if (beastEntity.nearestCrystal != null) {
//         matrixStack.pushPose();
//         float f6 = (float)(beastEntity.nearestCrystal.getX() - MathHelper.lerp((double)angle, beastEntity.xo, beastEntity.getX()));
//         float f8 = (float)(beastEntity.nearestCrystal.getY() - MathHelper.lerp((double)angle, beastEntity.yo, beastEntity.getY()));
//         float f9 = (float)(beastEntity.nearestCrystal.getZ() - MathHelper.lerp((double)angle, beastEntity.zo, beastEntity.getZ()));
//         renderCrystalBeams(f6, f8 + MorgulCrystalRenderer.getY(beastEntity.nearestCrystal, angle), f9, angle, beastEntity.tickCount, matrixStack, renderTypeBuffer, type);
//         matrixStack.popPose();
//      }
//
//      super.render(beastEntity, vector, angle, matrixStack, renderTypeBuffer, type);
//   }
//
//   private static void vertex01(IVertexBuilder vertexBuilder, Matrix4f matrix4f, int p_229061_2_) {
//      vertexBuilder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, p_229061_2_).endVertex();
//      vertexBuilder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, p_229061_2_).endVertex();
//   }
//
//   private static void vertex2(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float p_229060_2_, float p_229060_3_) {
//      vertexBuilder.vertex(matrix4f, -HALF_SQRT_3 * p_229060_3_, p_229060_2_, -0.5F * p_229060_3_).color(104, 196, 97, 0).endVertex();
//   }
//
//   private static void vertex3(IVertexBuilder vertexBuilder, Matrix4f p_229062_1_, float p_229062_2_, float p_229062_3_) {
//      vertexBuilder.vertex(p_229062_1_, HALF_SQRT_3 * p_229062_3_, p_229062_2_, -0.5F * p_229062_3_).color(104, 196, 97, 0).endVertex();
//   }
//
//   private static void vertex4(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float p_229063_2_, float p_229063_3_) {
//      vertexBuilder.vertex(matrix4f, 0.0F, p_229063_2_, 1.0F * p_229063_3_).color(104, 196, 97, 0).endVertex();
//   }
//
//   public static void renderCrystalBeams(float partialTicks, float radius, float alpha, float p_229059_3_, int rotation, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int color) {
//      float f = MathHelper.sqrt(partialTicks * partialTicks + alpha * alpha);
//      float f1 = MathHelper.sqrt(partialTicks * partialTicks + radius * radius + alpha * alpha);
//      matrixStack.pushPose();
//      matrixStack.translate(0.0D, 2.0D, 0.0D);
//      matrixStack.mulPose(Vector3f.YP.rotation((float)(-Math.atan2((double)alpha, (double)partialTicks)) - ((float)Math.PI / 2F)));
//      matrixStack.mulPose(Vector3f.XP.rotation((float)(-Math.atan2((double)f, (double)radius)) - ((float)Math.PI / 2F)));
//      IVertexBuilder ivertexbuilder = renderTypeBuffer.getBuffer(BEAM);
//      float f2 = 0.0F - ((float)rotation + p_229059_3_) * 0.01F;
//      float f3 = MathHelper.sqrt(partialTicks * partialTicks + radius * radius + alpha * alpha) / 32.0F - ((float)rotation + p_229059_3_) * 0.01F;
//      int i = 8;
//      float f4 = 0.0F;
//      float f5 = 0.75F;
//      float f6 = 0.0F;
//      MatrixStack.Entry matrixstack$entry = matrixStack.last();
//      Matrix4f matrix4f = matrixstack$entry.pose();
//      Matrix3f matrix3f = matrixstack$entry.normal();
//
//      for(int j = 1; j <= 8; ++j) {
//         float f7 = MathHelper.sin((float)j * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
//         float f8 = MathHelper.cos((float)j * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
//         float f9 = (float)j / 8.0F;
//         ivertexbuilder.vertex(matrix4f, f4 * 0.2F, f5 * 0.2F, 0.0F).color(0, 0, 0, 255).uv(f6, f2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(color).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
//         ivertexbuilder.vertex(matrix4f, f4, f5, f1).color(255, 255, 255, 255).uv(f6, f3).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(color).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
//         ivertexbuilder.vertex(matrix4f, f7, f8, f1).color(255, 255, 255, 255).uv(f9, f3).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(color).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
//         ivertexbuilder.vertex(matrix4f, f7 * 0.2F, f8 * 0.2F, 0.0F).color(0, 0, 0, 255).uv(f9, f2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(color).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
//         f4 = f7;
//         f5 = f8;
//         f6 = f9;
//      }
//
//      matrixStack.popPose();
//   }
//
//   @Override
//   public ResourceLocation getTextureLocation(FellBeastEntity beastEntity) {
//      return FELLBEAST_LOCATION;
//   }
//
//   @OnlyIn(Dist.CLIENT)
//   public static class FellBeastModel extends EntityModel<FellBeastEntity> {
//      private final ModelRenderer neck;
//      private final ModelRenderer body;
//      private final ModelRenderer leftRearLeg;
//      private final ModelRenderer leftRearLegTip;
//      private final ModelRenderer leftRearFoot;
//      private final ModelRenderer talon_l3_r1;
//      private final ModelRenderer talon_l2_r1;
//      private final ModelRenderer main_l4_r1;
//      private final ModelRenderer main_l3_r1;
//      private final ModelRenderer rightWing;
//      private final ModelRenderer rightWingTip;
//      private final ModelRenderer leftWing;
//      private final ModelRenderer leftWingTip;
//      private final ModelRenderer rightRearLeg;
//      private final ModelRenderer rightRearLegTip;
//      private final ModelRenderer rightRearFoot;
//      private final ModelRenderer talon_r3_r1;
//      private final ModelRenderer talon_r2_r1;
//      private final ModelRenderer main_r4_r1;
//      private final ModelRenderer main_r3_r1;
//      private final ModelRenderer head;
//      private final ModelRenderer jaw;
//      @Nullable
//      private FellBeastEntity entity;
//      private float a;
//
//      public FellBeastModel() {
//         this.texWidth = 256;
//         this.texHeight = 256;
//         float f = -16.0F;
//         this.head = new ModelRenderer(this);
//         this.head.addBox("upperlip", -6.0F, -4.0F, -24.0F, 12, 5, 16, 0.0F, 176, 44);
//         this.head.addBox("upperhead", -8.0F, -8.0F, -10.0F, 16, 16, 16, 0.0F, 112, 30);
//         this.jaw = new ModelRenderer(this);
//         this.jaw.setPos(0.0F, 4.0F, -8.0F);
//         this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16, 0.0F, 176, 65);
//         this.head.addChild(this.jaw);
//         this.neck = new ModelRenderer(this);
//         this.neck.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F, 192, 104);
//         this.neck.addBox("scale", 0.1F, -13.0F, -8.0F, 0, 12, 12, 0.0F, 228, 232);
//         this.body = new ModelRenderer(this);
//         this.body.setPos(0.0F, 4.0F, 8.0F);
//         this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64, 0.0F, 0, 0);
//         this.body.addBox("scale", -0.1F, -11.0F, -10.0F, 0, 12, 16, 0.0F, 208, 228);
//         this.body.addBox("scale", -0.1F, -11.0F, 10.0F, 0, 12, 16, 0.0F, 208, 228);
//         this.body.addBox("scale", -0.1F, -11.0F, 30.0F, 0, 12, 16, 0.0F, 208, 228);
//         this.leftWing = new ModelRenderer(this);
//         this.leftWing.mirror = true;
//         this.leftWing.setPos(12.0F, 5.0F, 2.0F);
//         this.leftWing.addBox("bone", 0.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, 112, 88);
//         this.leftWing.addBox("skin", 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 88);
//         this.leftWingTip = new ModelRenderer(this);
//         this.leftWingTip.mirror = true;
//         this.leftWingTip.setPos(56.0F, 0.0F, 0.0F);
//         this.leftWingTip.addBox("bone", 0.0F, -2.0F, -2.0F, 56, 4, 4, 0.0F, 112, 136);
//         this.leftWingTip.addBox("skin", 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 144);
//         this.leftWing.addChild(this.leftWingTip);
//         this.leftRearLeg = new ModelRenderer(this);
//         this.leftRearLeg.setPos(16.0F, 16.0F, 42.0F);
//         this.leftRearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, 0, 0);
//         this.leftRearLegTip = new ModelRenderer(this);
//         this.leftRearLegTip.setPos(0.0F, 32.0F, -4.0F);
//         this.leftRearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12, 0.0F, 196, 0);
//         this.leftRearLeg.addChild(this.leftRearLegTip);
//         this.leftRearFoot = new ModelRenderer(this);
//         this.leftRearFoot.setPos(0.2F, 30.0F, 9.0F);
//         this.setRotationAngle(leftRearFoot, 0.7517F, 0.0F, 0.0F);
//         this.leftRearFoot.addBox("main", -1.4F, -1.1659F, -12.4803F, 2, 4, 14, 0.0F, 175, 238);
//         this.leftRearFoot.addBox("main", -3.6F, -1.1659F, -8.5803F, 6, 4, 6, 0.0F, 175, 246);
//         this.leftRearFoot.addBox("main", -0.5F, -1.1659F, -17.0803F, 0, 4, 5, 0.0F, 151, 244);
//         this.leftRearLegTip.addChild(this.leftRearFoot);
//         this.talon_l3_r1 = new ModelRenderer(this);
//         this.talon_l3_r1.setPos(-24.4F, 2.8341F, -6.4803F);
//         this.setRotationAngle(talon_l3_r1, 0.0F, 0.2182F, 0.0F);
//         this.talon_l3_r1.addBox("talon", 20.3F, -4.0F, -5.7F, 0, 4, 5, 0.0F, 151, 244);
//         this.leftRearFoot.addChild(talon_l3_r1);
//         this.talon_l2_r1 = new ModelRenderer(this);
//         this.talon_l2_r1.setPos(-0.4F, 2.8341F, -6.4803F);
//         this.setRotationAngle(talon_l2_r1, 0.0F, -0.2182F, 0.0F);
//         this.talon_l2_r1.addBox("talon", 3.0F, -4.0F, -11.0F, 0, 4, 5, 0.0F, 151, 244);
//         this.leftRearFoot.addChild(talon_l2_r1);
//         this.main_l4_r1 = new ModelRenderer(this);
//         this.main_l4_r1.setPos(-12.4F, 41.8341F, -7.4803F);
//         this.setRotationAngle(main_l4_r1, 0.0F, -0.2182F, 0.0F);
//         this.main_l4_r1.addBox("toe", 14.0F, -43.0F, -8.0F, 2, 4, 14, 0.0F, 175, 238);
//         this.leftRearFoot.addChild(main_l4_r1);
//         this.main_l3_r1 = new ModelRenderer(this);
//         this.main_l3_r1.setPos(-12.4F, 41.8341F, -7.4803F);
//         this.setRotationAngle(main_l3_r1, 0.0F, 0.2182F, 0.0F);
//         this.main_l3_r1.addBox("toe", 7.4F, -43.0F, -2.8F, 2, 4, 14, 0.0F, 175, 238);
//         this.leftRearFoot.addChild(main_l3_r1);
//         this.rightWing = new ModelRenderer(this);
//         this.rightWing.setPos(-12.0F, 5.0F, 2.0F);
//         this.rightWing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, 112, 88);
//         this.rightWing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 88);
//         this.rightWingTip = new ModelRenderer(this);
//         this.rightWingTip.setPos(-56.0F, 0.0F, 0.0F);
//         this.rightWingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4, 0.0F, 112, 136);
//         this.rightWingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 144);
//         this.rightWing.addChild(this.rightWingTip);
//         this.rightRearLeg = new ModelRenderer(this);
//         this.rightRearLeg.setPos(-16.0F, 16.0F, 42.0F);
//         this.rightRearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, 0, 0);
//         this.rightRearLegTip = new ModelRenderer(this);
//         this.rightRearLegTip.setPos(0.0F, 32.0F, -4.0F);
//         this.rightRearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12, 0.0F, 196, 0);
//         this.rightRearLeg.addChild(this.rightRearLegTip);
//         this.rightRearFoot = new ModelRenderer(this);
//         this.rightRearFoot.setPos(0.2F, 30.0F, 9.0F);
//         this.setRotationAngle(rightRearFoot, 0.7517F, 0.0F, 0.0F);
//         this.rightRearFoot.addBox("main", -1.0F, -1.0408F, -12.5433F, 2, 4, 14, 0.0F, 175, 238);
//         this.rightRearFoot.addBox("main", -3.2F, -1.0408F, -8.6433F, 6, 4, 6, 0.0F, 175, 246);
//         this.rightRearFoot.addBox("main", -0.1F, -1.0408F, -17.1433F, 0, 4, 5, 0.0F, 151, 244);
//         this.rightRearLegTip.addChild(this.rightRearFoot);
//         this.talon_r3_r1 = new ModelRenderer(this);
//         this.talon_r3_r1.setPos(-24.0F, 2.9592F, -6.5433F);
//         this.setRotationAngle(talon_r3_r1, 0.0F, 0.2182F, 0.0F);
//         this.talon_r3_r1.addBox("talon", 20.3F, -4.0F, -5.7F, 0, 4, 5, 0.0F, 151, 244);
//         this.rightRearFoot.addChild(talon_r3_r1);
//         this.talon_r2_r1 = new ModelRenderer(this);
//         this.talon_r2_r1.setPos(0.0F, 2.9592F, -6.5433F);
//         this.setRotationAngle(talon_r2_r1, 0.0F, -0.2182F, 0.0F);
//         this.talon_r2_r1.addBox("talon", 3.0F, -4.0F, -11.0F, 0, 4, 5, 0.0F, 151, 244);
//         this.rightRearFoot.addChild(talon_r2_r1);
//         this.main_r4_r1 = new ModelRenderer(this);
//         this.main_r4_r1.setPos(-12.0F, 41.9592F, -7.5433F);
//         this.setRotationAngle(main_r4_r1, 0.0F, -0.2182F, 0.0F);
//         this.main_r4_r1.addBox("toe", 14.0F, -43.0F, -8.0F, 2, 4, 14, 0.0F, 175, 238);
//         this.rightRearFoot.addChild(main_r4_r1);
//         this.main_r3_r1 = new ModelRenderer(this);
//         this.main_r3_r1.setPos(-12.0F, 41.9592F, -7.5433F);
//         this.setRotationAngle(main_r3_r1, 0.0F, 0.2182F, 0.0F);
//         this.main_r3_r1.addBox("toe", 7.4F, -43.0F, -2.8F, 2, 4, 14, 0.0F, 175, 238);
//         this.rightRearFoot.addChild(main_r3_r1);
//      }
//
//      @Override
//      public void prepareMobModel(FellBeastEntity beastEntity, float limbSwing, float limbSwingAmount, float partialTick) {
//         this.entity = beastEntity;
//         this.a = partialTick;
//      }
//
//      @Override
//      public void setupAnim(FellBeastEntity beastEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//      }
//
//      @Override
//      public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder vertexBuilder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//         matrixStack.pushPose();
//         float f = MathHelper.lerp(this.a, this.entity.oFlapTime, this.entity.flapTime);
//         this.jaw.xRot = (float)(Math.sin((double)(f * ((float)Math.PI * 2F))) + 1.0D) * 0.2F;
//         float f1 = (float)(Math.sin((double)(f * ((float)Math.PI * 2F) - 1.0F)) + 1.0D);
//         f1 = (f1 * f1 + f1 * 2.0F) * 0.05F;
//         matrixStack.translate(0.0D, (double)(f1 - 2.0F), -3.0D);
//         matrixStack.mulPose(Vector3f.XP.rotationDegrees(f1 * 2.0F));
//         float f2 = 0.0F;
//         float f3 = 20.0F;
//         float f4 = -12.0F;
//         float f5 = 1.5F;
//         double[] adouble = this.entity.getLatencyPos(6, this.a);
//         float f6 = MathHelper.rotWrap(this.entity.getLatencyPos(5, this.a)[0] - this.entity.getLatencyPos(10, this.a)[0]);
//         float f7 = MathHelper.rotWrap(this.entity.getLatencyPos(5, this.a)[0] + (double)(f6 / 2.0F));
//         float f8 = f * ((float)Math.PI * 2F);
//
//         for(int i = 0; i < 5; ++i) {
//            double[] adouble1 = this.entity.getLatencyPos(5 - i, this.a);
//            float f9 = (float)Math.cos((double)((float)i * 0.45F + f8)) * 0.15F;
//            this.neck.yRot = MathHelper.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F) * 1.5F;
//            this.neck.xRot = f9 + this.entity.getHeadPartYOffset(i, adouble, adouble1) * ((float)Math.PI / 180F) * 1.5F * 5.0F;
//            this.neck.zRot = -MathHelper.rotWrap(adouble1[0] - (double)f7) * ((float)Math.PI / 180F) * 1.5F;
//            this.neck.y = f3;
//            this.neck.z = f4;
//            this.neck.x = f2;
//            f3 = (float)((double)f3 + Math.sin((double)this.neck.xRot) * 10.0D);
//            f4 = (float)((double)f4 - Math.cos((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
//            f2 = (float)((double)f2 - Math.sin((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
//            this.neck.render(matrixStack, vertexBuilder, packedLight, packedOverlay);
//         }
//
//         this.head.y = f3;
//         this.head.z = f4;
//         this.head.x = f2;
//         double[] adouble2 = this.entity.getLatencyPos(0, this.a);
//         this.head.yRot = MathHelper.rotWrap(adouble2[0] - adouble[0]) * ((float)Math.PI / 180F);
//         this.head.xRot = MathHelper.rotWrap((double)this.entity.getHeadPartYOffset(6, adouble, adouble2)) * ((float)Math.PI / 180F) * 1.5F * 5.0F;
//         this.head.zRot = -MathHelper.rotWrap(adouble2[0] - (double)f7) * ((float)Math.PI / 180F);
//         this.head.render(matrixStack, vertexBuilder, packedLight, packedOverlay);
//         matrixStack.pushPose();
//         matrixStack.translate(0.0D, 1.0D, 0.0D);
//         matrixStack.mulPose(Vector3f.ZP.rotationDegrees(-f6 * 1.5F));
//         matrixStack.translate(0.0D, -1.0D, 0.0D);
//         this.body.zRot = 0.0F;
//         this.body.render(matrixStack, vertexBuilder, packedLight, packedOverlay);
//         float f10 = f * ((float)Math.PI * 2F);
//         this.leftWing.xRot = 0.125F - (float)Math.cos((double)f10) * 0.2F;
//         this.leftWing.yRot = -0.25F;
//         this.leftWing.zRot = -((float)(Math.sin((double)f10) + 0.125D)) * 0.8F;
//         this.leftWingTip.zRot = (float)(Math.sin((double)(f10 + 2.0F)) + 0.5D) * 0.75F;
//         this.rightWing.xRot = this.leftWing.xRot;
//         this.rightWing.yRot = -this.leftWing.yRot;
//         this.rightWing.zRot = -this.leftWing.zRot;
//         this.rightWingTip.zRot = -this.leftWingTip.zRot;
//         this.renderSide(matrixStack, vertexBuilder, packedLight, packedOverlay, f1, this.leftWing, this.leftRearLeg, this.leftRearLegTip, this.leftRearFoot);
//         this.renderSide(matrixStack, vertexBuilder, packedLight, packedOverlay, f1, this.rightWing, this.rightRearLeg, this.rightRearLegTip, this.rightRearFoot);
//         matrixStack.popPose();
//         float f11 = -((float)Math.sin((double)(f * ((float)Math.PI * 2F)))) * 0.0F;
//         f8 = f * ((float)Math.PI * 2F);
//         f3 = 10.0F;
//         f4 = 60.0F;
//         f2 = 0.0F;
//         adouble = this.entity.getLatencyPos(11, this.a);
//
//         for(int j = 0; j < 12; ++j) {
//            adouble2 = this.entity.getLatencyPos(12 + j, this.a);
//            f11 = (float)((double)f11 + Math.sin((double)((float)j * 0.45F + f8)) * (double)0.05F);
//            this.neck.yRot = (MathHelper.rotWrap(adouble2[0] - adouble[0]) * 1.5F + 180.0F) * ((float)Math.PI / 180F);
//            this.neck.xRot = f11 + (float)(adouble2[1] - adouble[1]) * ((float)Math.PI / 180F) * 1.5F * 5.0F;
//            this.neck.zRot = MathHelper.rotWrap(adouble2[0] - (double)f7) * ((float)Math.PI / 180F) * 1.5F;
//            this.neck.y = f3;
//            this.neck.z = f4;
//            this.neck.x = f2;
//            f3 = (float)((double)f3 + Math.sin((double)this.neck.xRot) * 10.0D);
//            f4 = (float)((double)f4 - Math.cos((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
//            f2 = (float)((double)f2 - Math.sin((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
//            this.neck.render(matrixStack, vertexBuilder, packedLight, packedOverlay);
//         }
//
//         matrixStack.popPose();
//      }
//
//      private void renderSide(MatrixStack matrixStack, IVertexBuilder vertexBuilder, int packedLight, int packedOverlay, float f1, ModelRenderer wing, ModelRenderer leg, ModelRenderer tip, ModelRenderer foot) {
//         leg.xRot = 1.0F + f1 * 0.1F;
//         tip.xRot = 0.5F + f1 * 0.1F;
//         foot.xRot = 0.75F + f1 * 0.1F;
//         wing.render(matrixStack, vertexBuilder, packedLight, packedOverlay);
//         leg.render(matrixStack, vertexBuilder, packedLight, packedOverlay);
//      }
//
//      public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//         modelRenderer.xRot = x;
//         modelRenderer.yRot = y;
//         modelRenderer.zRot = z;
//      }
//   }
//}