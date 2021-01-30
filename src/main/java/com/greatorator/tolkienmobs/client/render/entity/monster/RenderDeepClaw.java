//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelDeepClaw;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMDeepClaw;
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
//public class RenderDeepClaw extends RenderLiving<EntityTMDeepClaw> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmdeepclaw/tmdeepclaw1.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmdeepclaw/tmdeepclaw2.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmdeepclaw/tmdeepclaw3.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/tmdeepclaw/tmdeepclaw4.png");
//
//    public static final RenderDeepClaw.Factory FACTORY = new RenderDeepClaw.Factory();
//
//    public float scale;
//
//    public RenderDeepClaw(RenderManager rendermanagerIn, float scale) {
//        super(rendermanagerIn, new ModelDeepClaw(), 1.1F);
//        this.scale = scale;
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMDeepClaw entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMDeepClaw> {
//        @Override
//        public Render<? super EntityTMDeepClaw> createRenderFor(RenderManager manager) {
//            return new RenderDeepClaw(manager, 2.1F);
//        }
//    }
//
//    /** If you need to apply a GL Scale to your model this is how you do it. */
//    @Override
//    protected void preRenderCallback(EntityTMDeepClaw LivingEntityIn, float partialTickTime) {
//        GlStateManager.scale(scale, scale, scale);
//    }
//}