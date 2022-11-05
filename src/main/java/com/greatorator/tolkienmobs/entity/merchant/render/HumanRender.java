package com.greatorator.tolkienmobs.entity.merchant.render;

//
//public class HumanRender extends MobRenderer<HumanEntity, HumanModel<HumanEntity>> {
//    public HumanRender(EntityRendererManager entityIn, HumanModel<HumanEntity> modelSize, float shadowIn) {
//        super(entityIn, modelSize, shadowIn);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(HumanEntity entity) {
//        return entity.getHumanTypeName();
//    }
//
//    @Override
//    protected void scale(HumanEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        float f = 0.93F;
//        if (entitylivingbaseIn.isBaby()) {
//            f = (float)((double)f * 0.5D);
//            this.shadowRadius = 0.25F;
//        } else {
//            this.shadowRadius = 0.5F;
//        }
//
//        matrixStackIn.scale(f, f, f);
//    }
//}