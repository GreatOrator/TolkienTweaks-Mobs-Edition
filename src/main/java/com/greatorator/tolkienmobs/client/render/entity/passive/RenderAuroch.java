//package com.greatorator.tolkienmobs.client.render.entity.passive;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.passive.ModelAuroch;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMAuroch;
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
//public class RenderAuroch extends RenderLiving<EntityTMAuroch> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/auroch/auroch1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/auroch/auroch2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/auroch/auroch3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/auroch/auroch4.png");
//
//    public static final RenderAuroch.Factory FACTORY = new RenderAuroch.Factory();
//
//    public float scale;
//
//    public RenderAuroch(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelAuroch(), 0.5F);
//        this.scale = scale;
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMAuroch entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMAuroch> {
//        @Override
//        public Render<? super EntityTMAuroch> createRenderFor(RenderManager manager) {
//            return new RenderAuroch(manager, 1.0F);
//        }
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMAuroch LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}