//package com.greatorator.tolkienmobs.client.render.entity.passive;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.passive.ModelMumakil;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMMumakil;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//public class RenderMumakil extends RenderLiving<EntityTMMumakil> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil/mumakil1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil/mumakil2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil/mumakil3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/mumakil/mumakil4.png");
//
//    public static final RenderMumakil.Factory FACTORY = new RenderMumakil.Factory();
//
//    public float scale;
//
//    public RenderMumakil(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelMumakil(), 2.0F);
//        this.scale = scale;
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMMumakil entity) {
//        switch (entity.getMobType()) {
//            case 0:
//            default:
//                return GREEN;
//            case 1:
//                return GREEN;
//            case 2:
//                return BLACK;
//            case 3:
//                return BLUE;
//            case 4:
//                return WHITE;
//            case 5:
//                return WHITE;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMMumakil> {
//        @Override
//        public Render<? super EntityTMMumakil> createRenderFor(RenderManager manager) {
//            return new RenderMumakil(manager, 2.0F);
//        }
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMMumakil LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}