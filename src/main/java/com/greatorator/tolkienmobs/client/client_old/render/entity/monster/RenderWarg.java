//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelWarg;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMWarg;
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
//public class RenderWarg extends RenderLiving<EntityTMWarg> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/warg/warg1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/warg/warg2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/warg/warg3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/warg/warg4.png");
//
//    public static final RenderWarg.Factory FACTORY = new RenderWarg.Factory();
//
//    public float scale;
//
//    public RenderWarg(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelWarg(), 1.1F);
//        this.scale = scale;
//    }
//
//    protected float handleRotationFloat(EntityTMWarg livingBase, float partialTicks)
//    {
//        return livingBase.getTailRotation();
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMWarg entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMWarg> {
//        @Override
//        public Render<? super EntityTMWarg> createRenderFor(RenderManager manager) {
//            return new RenderWarg(manager, 2.1F);
//        }
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMWarg LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}