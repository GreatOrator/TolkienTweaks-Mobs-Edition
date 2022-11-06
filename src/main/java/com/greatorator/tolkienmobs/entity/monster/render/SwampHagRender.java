package com.greatorator.tolkienmobs.entity.monster.render;

//
//public class SwampHagRender extends MobRenderer<SwampHagEntity, WitchModel<SwampHagEntity>> {
//
//    public SwampHagRender(EntityRendererManager p_i46131_1_) {
//        super(p_i46131_1_, new WitchModel(0.0F), 0.5F);
//        this.addLayer(new WitchHeldItemLayer(this));
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(SwampHagEntity entity) {
//        return entity.getSwampHagTypeName();
//    }
//
//    @Override
//    public void render(SwampHagEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
//        ((WitchModel)this.model).setHoldingItem(!p_225623_1_.getMainHandItem().isEmpty());
//        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
//    }
//
//    protected void scale(WitchEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
//        float lvt_4_1_ = 0.9375F;
//        p_225620_2_.scale(0.9375F, 0.9375F, 0.9375F);
//    }
//}