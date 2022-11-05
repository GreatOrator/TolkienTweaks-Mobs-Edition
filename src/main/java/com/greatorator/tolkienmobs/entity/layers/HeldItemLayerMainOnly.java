package com.greatorator.tolkienmobs.entity.layers;

//
//@OnlyIn(Dist.CLIENT)
//public class HeldItemLayerMainOnly<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
//   public HeldItemLayerMainOnly(IEntityRenderer<T, M> p_i50934_1_) {
//      super(p_i50934_1_);
//   }
//
//   @Override
//   public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
//      boolean flag = entity.getMainArm() == HandSide.RIGHT;
//      ItemStack itemstack = flag ? entity.getOffhandItem() : entity.getMainHandItem();
//      ItemStack itemstack1 = flag ? entity.getMainHandItem() : entity.getOffhandItem();
//      if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
//         p_225628_1_.pushPose();
//         if (this.getParentModel().young) {
//            float f = 0.5F;
//            p_225628_1_.translate(0.0D, 0.75D, 0.0D);
//            p_225628_1_.scale(0.5F, 0.5F, 0.5F);
//         }
//
//         if (entity.getMainArm() == HandSide.RIGHT) {
//            this.renderArmWithItem(entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, p_225628_1_, p_225628_2_, p_225628_3_);
//         } else {
//            this.renderArmWithItem(entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, p_225628_1_, p_225628_2_, p_225628_3_);
//         }
//         p_225628_1_.popPose();
//      }
//   }
//
//   private void renderArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_, IRenderTypeBuffer p_229135_6_, int p_229135_7_) {
//      if (!p_229135_2_.isEmpty()) {
//         p_229135_5_.pushPose();
//         this.getParentModel().translateToHand(p_229135_4_, p_229135_5_);
//         p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
//         p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
//         boolean flag = p_229135_4_ == HandSide.LEFT;
//         p_229135_5_.translate((double)((float)(flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
//         Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
//         p_229135_5_.popPose();
//      }
//   }
//}