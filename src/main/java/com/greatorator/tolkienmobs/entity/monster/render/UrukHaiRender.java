package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.HeldItemLayerMainOnly;
import com.greatorator.tolkienmobs.entity.monster.UrukHaiEntity;
import com.greatorator.tolkienmobs.entity.monster.model.UrukHaiModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class UrukHaiRender extends MobRenderer<UrukHaiEntity, UrukHaiModel<UrukHaiEntity>> {
    public UrukHaiRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new UrukHaiModel<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayerMainOnly<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(UrukHaiEntity entity) {
        return entity.getUrukHaiTypeName();
    }

    @Override
    protected void scale(UrukHaiEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}