package com.greatorator.tolkienmobs.entity.boss.render;

//
//public class BalrogRender extends MobRenderer<BalrogEntity, BalrogModel<BalrogEntity>> {
//    private ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/balrog.png");
//
//    public BalrogRender(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new BalrogModel<>(0.0F, true), 1.0F);
//        this.addLayer(new HeldItemLayerMainOnly<>(this));
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(BalrogEntity entity) {
//        return mobTexture;
//    }
//
//    @Override
//    protected void scale(BalrogEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
//        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
//    }
//}