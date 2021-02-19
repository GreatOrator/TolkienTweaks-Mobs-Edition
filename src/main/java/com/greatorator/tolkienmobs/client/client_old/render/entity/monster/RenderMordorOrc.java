//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelMordorOrc;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMMordorOrc;
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
//public class RenderMordorOrc extends RenderLiving<EntityTMMordorOrc> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/orc/mordororc1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/orc/mordororc2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/orc/mordororc3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/orc/mordororc4.png");
//
//    public static final RenderMordorOrc.Factory FACTORY = new RenderMordorOrc.Factory();
//
//    public float scale;
//
//    public RenderMordorOrc(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelMordorOrc(), 0.5F);
//        this.scale = scale;
//        this.addLayer(new LayerArmed(this, -0.03F, 0.105F, -0.545F, 0.85F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMMordorOrc entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMMordorOrc> {
//        @Override
//        public Render<? super EntityTMMordorOrc> createRenderFor(RenderManager manager) {
//            return new RenderMordorOrc(manager, 0.85F);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMMordorOrc LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}