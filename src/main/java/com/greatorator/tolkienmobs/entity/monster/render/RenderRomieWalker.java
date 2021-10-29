//package com.greatorator.tolkienmobs.client.render.entity.monster;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerArmed;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelBrigand;
//import com.greatorator.tolkienmobs.entity.hostile.EntityRomieWalker;
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
//public class RenderRomieWalker extends RenderLiving<EntityRomieWalker> {
//    private static final ResourceLocation GREEN = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/romiewalker/romiewalker0.png");
//    private static final ResourceLocation BLUE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/romiewalker/romiewalker1.png");
//    private static final ResourceLocation BLACK = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/romiewalker/romiewalker2.png");
//    private static final ResourceLocation WHITE = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/romiewalker/romiewalker3.png");
//    private static final ResourceLocation YELLOW = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/romiewalker/romiewalker4.png");
//
//    public static final RenderRomieWalker.Factory FACTORY = new RenderRomieWalker.Factory();
//
//    public float scale;
//
//    public RenderRomieWalker(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelBrigand(), 0.5F);
//        this.addLayer(new LayerArmed(this, 0.0825F, 0.0825F, -0.625F, 1F));
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityRomieWalker entity) {
//        switch (entity.getMobType()) {
//            case 0:
//            default:
//                return GREEN;
//            case 1:
//                return YELLOW;
//            case 2:
//                return BLACK;
//            case 3:
//                return BLUE;
//            case 4:
//                return WHITE;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityRomieWalker> {
//        @Override
//        public Render<? super EntityRomieWalker> createRenderFor(RenderManager manager) {
//            return new RenderRomieWalker(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//}