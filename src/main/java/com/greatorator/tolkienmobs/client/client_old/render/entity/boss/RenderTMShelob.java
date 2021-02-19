//package com.greatorator.tolkienmobs.client.render.entity.boss;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelMirkwoodSpider;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMShelob;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nonnull;
//
//public class RenderTMShelob extends RenderLiving<EntityTMShelob> {
//    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmshelob.png");
//
//    public static final Factory FACTORY = new Factory();
//
//    public float scale;
//
//    public RenderTMShelob(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelMirkwoodSpider(), 0.5F * scale);
//        this.scale = scale;
//    }
//
//    @Override
//    @Nonnull
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMShelob entity) {
//        return mobTexture;
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMShelob> {
//        @Override
//        public Render<? super EntityTMShelob> createRenderFor(RenderManager manager) {
//            return new RenderTMShelob(manager, 1.8F);
//        }
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMShelob LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}