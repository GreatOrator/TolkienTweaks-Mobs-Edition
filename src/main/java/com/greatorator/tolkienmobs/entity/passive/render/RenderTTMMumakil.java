package com.greatorator.tolkienmobs.entity.passive.render;

import com.greatorator.tolkienmobs.entity.passive.EntityTTMMumakil;
import com.greatorator.tolkienmobs.entity.passive.model.ModelTTMMumakil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMumakil extends MobRenderer<EntityTTMMumakil, ModelTTMMumakil<EntityTTMMumakil>> {
    public RenderTTMMumakil(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMumakil<>(), 3.0F);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityTTMMumakil entity) {
        return entity.getMumakilTypeName();
    }

    protected void preRenderCallback(EntityTTMMumakil entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}