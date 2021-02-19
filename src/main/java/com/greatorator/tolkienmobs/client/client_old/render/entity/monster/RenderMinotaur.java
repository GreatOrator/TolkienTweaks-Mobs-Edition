//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelMinotaur;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMMinotaur;
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
//public class RenderMinotaur extends RenderLiving<EntityTMMinotaur> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/minotaur.png");
//
//    public static final Factory FACTORY = new Factory();
//
//    public float scale;
//
//
//    public RenderMinotaur(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelMinotaur(), 1.0F);
//        this.scale = scale;
//        this.addLayer(new LayerArmed(this, 0.23F, 0.2F, -1.35F, 1.4F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMMinotaur entity) {
//        switch (entity.getMobType()) {
//            case 0:
//            default:
//                return GREEN;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMMinotaur> {
//        @Override
//        public Render<? super EntityTMMinotaur> createRenderFor(RenderManager manager) {
//            return new RenderMinotaur(manager, 1.4F);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMMinotaur LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}
