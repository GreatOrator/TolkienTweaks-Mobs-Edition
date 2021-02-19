//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelBarrowWight;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMBarrowWight;
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
//public class RenderBarrowWight extends RenderLiving<EntityTMBarrowWight> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/barrowwight/barrowwight1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/barrowwight/barrowwight2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/barrowwight/barrowwight3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/barrowwight/barrowwight4.png");
//
//    public static final RenderBarrowWight.Factory FACTORY = new RenderBarrowWight.Factory();
//
//    public float scale;
//
//    public RenderBarrowWight(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelBarrowWight(false), 0.5F);
//        this.addLayer(new LayerArmed(this, 0.0825F, 0.135F, -0.525F, 1F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMBarrowWight entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMBarrowWight> {
//        @Override
//        public Render<? super EntityTMBarrowWight> createRenderFor(RenderManager manager) {
//            return new RenderBarrowWight(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//}