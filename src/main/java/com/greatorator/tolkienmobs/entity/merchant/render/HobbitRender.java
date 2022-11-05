package com.greatorator.tolkienmobs.entity.merchant.render;

//
//public class HobbitRender extends MobRenderer<HobbitEntity, HobbitModel<HobbitEntity>> {
//    public HobbitRender(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new HobbitModel<>(), 1.0F);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(HobbitEntity entity) {
//        return entity.getHobbitTypeName();
//    }
//
//    @Override
//    protected void scale(HobbitEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        float f = 0.7F;
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