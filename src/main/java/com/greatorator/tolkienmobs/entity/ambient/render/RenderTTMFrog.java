package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.EntityTTMFrog;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMFrog;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderTTMFrog extends MobRenderer<EntityTTMFrog, ModelTTMFrog<EntityTTMFrog>> {
    public RenderTTMFrog(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMFrog<>(), 0.5F);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityTTMFrog entity) {
        return entity.getFrogTypeName();
    }

    protected void scale(EntityTTMFrog entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.8F, 0.8F, 0.8F);
    }

}