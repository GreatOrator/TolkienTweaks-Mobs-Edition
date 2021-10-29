//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelBarrowWight;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMOathbreaker;
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
//public class RenderOathbreaker extends RenderLiving<EntityTMOathbreaker> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/oathbreaker/oathbreaker1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/oathbreaker/oathbreaker2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/oathbreaker/oathbreaker3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/oathbreaker/oathbreaker4.png");
//
//    public static final RenderOathbreaker.Factory FACTORY = new RenderOathbreaker.Factory();
//
//    public RenderOathbreaker(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelBarrowWight(true), 0.5F);
//        this.addLayer(new LayerArmed(this, 0.0625f, 0.02F, -0.6F,1F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMOathbreaker entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMOathbreaker> {
//        @Override
//        public Render<? super EntityTMOathbreaker> createRenderFor(RenderManager manager) {
//            return new RenderOathbreaker(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(-2.0F, 0.1875F, 0.0F);
//    }
//}