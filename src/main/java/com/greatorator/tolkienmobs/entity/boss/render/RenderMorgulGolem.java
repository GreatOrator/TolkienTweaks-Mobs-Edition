//package com.greatorator.tolkienmobs.client.render.entity.boss;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.monster.ModelElementalGolem;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMMorgulGolem;
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
//public class RenderMorgulGolem extends RenderLiving<EntityTMMorgulGolem> {
//    private static final ResourceLocation MORGULGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_morgul.png");
//
//    public static final RenderMorgulGolem.Factory FACTORY = new RenderMorgulGolem.Factory();
//    private ModelElementalGolem modelbase;
//
//    public RenderMorgulGolem(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelElementalGolem(), 0.5F);
//
//        this.modelbase = (ModelElementalGolem) this.mainModel;
//    }
//
//    @Override
//    public void doRender(EntityTMMorgulGolem golem, double x, double y, double z, float pitch, float yaw) {
//        if(golem.getElementType() >= 1 && golem.getElementType() <= 4 ) {
//            ModelElementalGolem.renderGolem = true;
//        }
//        else{
//            ModelElementalGolem.renderGolem = false;
//        }
//        super.doRender(golem, x, y, z, pitch, yaw);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMMorgulGolem entity) {
//        switch (entity.getElementType()) {
//            case 0:
//            default:
//                return MORGULGOLEM;
//            case 1:
//                return MORGULGOLEM;
//            case 2:
//                return MORGULGOLEM;
//            case 3:
//                return MORGULGOLEM;
//            case 4:
//                return MORGULGOLEM;
//            case 5:
//                return MORGULGOLEM;
//            case 6:
//                return MORGULGOLEM;
//            case 7:
//                return MORGULGOLEM;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMMorgulGolem> {
//        @Override
//        public Render<? super EntityTMMorgulGolem> createRenderFor(RenderManager manager) {
//            return new RenderMorgulGolem(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//}
