//package com.greatorator.tolkienmobs.client.render.entity.boss;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.boss.ModelWitchKing;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMWitchKing;
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
//public class RenderWitchKing extends RenderLiving<EntityTMWitchKing> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/witchking.png");
//
//    public static final Factory FACTORY = new Factory();
//
//    public float scale;
//
//
//    public RenderWitchKing(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelWitchKing(), 1.0F);
//        this.scale = scale;
//        this.addLayer(new LayerArmed(this, 0.125F, 0.25F, -0.625F, 1.6F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMWitchKing entity) {
//        switch (entity.getMobType()) {
//            case 0:
//            default:
//                return GREEN;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMWitchKing> {
//        @Override
//        public Render<? super EntityTMWitchKing> createRenderFor(RenderManager manager) {
//            return new RenderWitchKing(manager, 1.6F);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMWitchKing LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}