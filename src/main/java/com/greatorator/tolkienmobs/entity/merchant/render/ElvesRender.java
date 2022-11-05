package com.greatorator.tolkienmobs.entity.merchant.render;

//
//public class ElvesRender extends MobRenderer<ElvesEntity, ElvesModel<ElvesEntity>> {
//    public ElvesRender(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new ElvesModel<>(), 1.0F);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(ElvesEntity entity) {
//        return entity.getElvesTypeName();
//    }
//
//    protected void preRenderCallback(VillagerEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        float f = 0.98F;
//        if (entitylivingbaseIn.isBaby()) {
//            f = (float) ((double) f * 0.5D);
//            this.shadowRadius = 0.25F;
//        } else {
//            this.shadowRadius = 0.5F;
//        }
//
//        matrixStackIn.scale(f, f, f);
//    }
//}