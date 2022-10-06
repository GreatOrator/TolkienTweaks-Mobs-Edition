package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.RatEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.RatModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RatRender extends MobRenderer<RatEntity, RatModel<RatEntity>> {
    public RatRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RatModel<>(), 0.5F);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(RatEntity entity) {
        return entity.getRatTypeName();
    }

    @Override
    protected void scale(RatEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
