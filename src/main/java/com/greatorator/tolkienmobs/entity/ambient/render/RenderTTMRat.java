package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.EntityTTMRat;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMRat;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderTTMRat extends MobRenderer<EntityTTMRat, ModelTTMRat<EntityTTMRat>> {
    public RenderTTMRat(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMRat<>(), 0.5F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityTTMRat entity) {
        return entity.getRatTypeName();
    }

    protected void preRenderCallback(EntityTTMRat entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
