package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMMinotaur;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMinotaur;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMinotaur extends MobRenderer<EntityTTMMinotaur, ModelTTMMinotaur<EntityTTMMinotaur>> {
    public RenderTTMMinotaur(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMinotaur<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMMinotaur entity) {
        return entity.getMinotaurTypeName();
    }

    protected void scale(EntityTTMMinotaur entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
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
}
