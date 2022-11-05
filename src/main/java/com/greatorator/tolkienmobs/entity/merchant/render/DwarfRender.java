package com.greatorator.tolkienmobs.entity.merchant.render;

//
//public class DwarfRender extends MobRenderer<DwarfEntity, DwarfModel<DwarfEntity>> {
//    public DwarfRender(EntityRendererManager entityIn, DwarfModel<DwarfEntity> modelSize, float shadowIn) {
//        super(entityIn, modelSize, shadowIn);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(DwarfEntity entity) {
//        return entity.getDwarfTypeName();
//    }
//
//    @Override
//    protected void scale(DwarfEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        float f = 0.95F;
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