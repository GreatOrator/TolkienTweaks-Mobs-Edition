//package com.greatorator.tolkienmobs.client.render.entity.boss;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.boss.ModelGoblinKing;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMGoblinKing;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nonnull;
//
//public class RenderGoblinKing extends RenderLiving<EntityTMGoblinKing> {
//    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/goblin/goblinking.png");
//
//    public static final RenderGoblinKing.Factory FACTORY = new RenderGoblinKing.Factory();
//
//    public RenderGoblinKing(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelGoblinKing(), 0.8F);
//        this.addLayer(new LayerArmed(this,0.02F, 0.05875F, -0.33F,0.7F));
//    }
//
//    @Override
//    @Nonnull
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMGoblinKing entity) {
//        return mobTexture;
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMGoblinKing> {
//        @Override
//        public Render<? super EntityTMGoblinKing> createRenderFor(RenderManager manager) {
//            return new RenderGoblinKing(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//}