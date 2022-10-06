package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.FrogEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.FrogModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class FrogRender extends MobRenderer<FrogEntity, FrogModel<FrogEntity>> {
    public FrogRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FrogModel<>(), 0.5F);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(FrogEntity entity) {
        return entity.getFrogTypeName();
    }

    @Override
    protected void scale(FrogEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.8F, 0.8F, 0.8F);
    }

}