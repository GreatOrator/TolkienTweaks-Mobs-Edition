package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.MinotaurEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MinotaurModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class MinotaurRender extends MobRenderer<MinotaurEntity, MinotaurModel<MinotaurEntity>> {
    public MinotaurRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MinotaurModel<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MinotaurEntity entity) {
        return entity.getMinotaurTypeName();
    }

    @Override
    protected void scale(MinotaurEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
