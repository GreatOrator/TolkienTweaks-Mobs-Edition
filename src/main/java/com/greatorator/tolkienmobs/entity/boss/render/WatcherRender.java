package com.greatorator.tolkienmobs.entity.boss.render;

//
//public class WatcherRender extends MobRenderer<WatcherEntity, WatcherModel<WatcherEntity>> {
//    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/tmwatcher.png");
//
//    public WatcherRender(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new WatcherModel<>(), 1.0F);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(WatcherEntity entity) {
//        return mobTexture;
//    }
//
//    @Override
//    protected void scale(WatcherEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
//    }
////
////    extends RenderLiving<EntityTMWatcher> {
////    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmwatcher.png");
////
////    public static final Factory FACTORY = new Factory();
////
////    public float scale;
////
////
////    public RenderWatcher(RenderManager rendermanagerIn, float scale) {
////        super(rendermanagerIn, new ModelWatcher(), 1.0F);
////        this.scale = scale;
////    }
////
////    @Nullable
////    @Override
////    protected ResourceLocation getEntityTexture(@Nonnull EntityTMWatcher entity) {
////        switch (entity.getMobType()) {
////            case 0:
////            default:
////                return GREEN;
////        }
////    }
////
////    public static class Factory implements IRenderFactory<EntityTMWatcher> {
////        @Override
////        public Render<? super EntityTMWatcher> createRenderFor(RenderManager manager) {
////            return new RenderWatcher(manager, 1.6F);
////        }
////    }
////
////    public void transformHeldFull3DItemLayer() {
////        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
////    }
////
////    /** If you need to apply a GL Scale to your model this is how you do it. */
////    @Override
////    protected void preRenderCallback(EntityTMWatcher LivingEntityIn, float partialTickTime) {
////        GlStateManager.scale(scale, scale, scale);
////    }
//}
