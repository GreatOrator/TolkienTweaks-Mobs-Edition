//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerHeldItemSwampHag;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelSwampHag;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMSwampHag;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//@SideOnly(Side.CLIENT)
//public class RenderSwampHag extends RenderLiving<EntityTMSwampHag>
//{
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/swamp_hag.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/swamp_hag.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/swamp_hag.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/swamp_hag.png");
//
//    public static final RenderSwampHag.Factory FACTORY = new RenderSwampHag.Factory();
//
//    public RenderSwampHag(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelSwampHag(), 0.5F);
//        this.addLayer(new LayerHeldItemSwampHag(this));
//    }
//
//    public ModelSwampHag getMainModel()
//    {
//        return (ModelSwampHag)super.getMainModel();
//    }
//
//    public void doRender(EntityTMSwampHag entity, double x, double y, double z, float entityYaw, float partialTicks)
//    {
//        ((ModelSwampHag)this.mainModel).holdingItem = !entity.getHeldItemMainhand().isEmpty();
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMSwampHag entity) {
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
//    public static class Factory implements IRenderFactory<EntityTMSwampHag> {
//        @Override
//        public Render<? super EntityTMSwampHag> createRenderFor(RenderManager manager) {
//            return new RenderSwampHag(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer()
//    {
//        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
//    }
//
//    protected void preRenderCallback(EntityTMSwampHag LivingEntityIn, float partialTickTime)
//    {
//        float f = 0.9375F;
//        GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
//    }
//}