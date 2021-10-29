//package com.greatorator.tolkienmobs.client.render.entity.special;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.entity.layers.LayerMithrilGolemFlower;
//import com.greatorator.tolkienmobs.client.render.model.special.ModelMithrilGolem;
//import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
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
//public class RenderMithrilGolem extends RenderLiving<EntityTMMithrilGolem> {
//    private static final ResourceLocation MITHRILGOLEM = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/elementalgolem/elemental_golem_mithril.png");
//
//    public static final RenderMithrilGolem.Factory FACTORY = new RenderMithrilGolem.Factory();
//    private ModelMithrilGolem modelbase;
//
//    public RenderMithrilGolem(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelMithrilGolem(), 0.5F);
//        this.addLayer(new LayerMithrilGolemFlower(this));
//
//        this.modelbase = (ModelMithrilGolem) this.mainModel;
//    }
//
//    @Override
//    public void doRender(EntityTMMithrilGolem golem, double x, double y, double z, float pitch, float yaw) {
//        if(golem.getElementType() >= 1 && golem.getElementType() <= 4 ) {
//            ModelMithrilGolem.renderGolem = true;
//        }
//        else{
//            ModelMithrilGolem.renderGolem = false;
//        }
//        super.doRender(golem, x, y, z, pitch, yaw);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(@Nonnull EntityTMMithrilGolem entity) {
//        switch (entity.getElementType()) {
//            case 0:
//            default:
//                return MITHRILGOLEM;
//            case 1:
//                return MITHRILGOLEM;
//            case 2:
//                return MITHRILGOLEM;
//            case 3:
//                return MITHRILGOLEM;
//            case 4:
//                return MITHRILGOLEM;
//            case 5:
//                return MITHRILGOLEM;
//            case 6:
//                return MITHRILGOLEM;
//            case 7:
//                return MITHRILGOLEM;
//        }
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMMithrilGolem> {
//        @Override
//        public Render<? super EntityTMMithrilGolem> createRenderFor(RenderManager manager) {
//            return new RenderMithrilGolem(manager);
//        }
//    }
//
//    public void transformHeldFull3DItemLayer() {
//        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
//    }
//
//    protected void applyRotations(EntityTMMithrilGolem entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
//    {
//        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
//
//        if ((double)entityLiving.limbSwingAmount >= 0.01D)
//        {
//            float f = 13.0F;
//            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
//            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
//            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
//        }
//    }
//}