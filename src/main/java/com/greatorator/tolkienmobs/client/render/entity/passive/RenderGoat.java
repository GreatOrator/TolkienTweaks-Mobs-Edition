//package com.greatorator.tolkienmobs.client.render.entity.passive;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.passive.ModelGoat;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMGoat;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nullable;
//
//public class RenderGoat extends RenderLiving<EntityTMGoat> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goat/goat1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goat/goat2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goat/goat3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goat/goat4.png");
//
//    public static final RenderGoat.Factory FACTORY = new RenderGoat.Factory();
//
//    public RenderGoat(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelGoat(), 0.5F);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(EntityTMGoat entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMGoat> {
//        @Override
//        public Render<? super EntityTMGoat> createRenderFor(RenderManager manager) {
//            return new RenderGoat(manager);
//        }
//
//    }
//}