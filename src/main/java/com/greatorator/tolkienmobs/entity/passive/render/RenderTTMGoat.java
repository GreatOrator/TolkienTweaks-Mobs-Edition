package com.greatorator.tolkienmobs.entity.passive.render;

import com.greatorator.tolkienmobs.entity.passive.EntityTTMGoat;
import com.greatorator.tolkienmobs.entity.passive.model.ModelTTMGoat;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMGoat extends MobRenderer<EntityTTMGoat, ModelTTMGoat<EntityTTMGoat>> {
    public RenderTTMGoat(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMGoat<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMGoat entity) {
        return entity.getGoatTypeName();
    }

    @Override
    protected void scale(EntityTTMGoat entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}