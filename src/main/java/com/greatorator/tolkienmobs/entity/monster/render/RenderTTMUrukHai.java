package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMUrukHai;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMUrukHai;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMUrukHai extends MobRenderer<EntityTTMUrukHai, ModelTTMUrukHai<EntityTTMUrukHai>> {
    public RenderTTMUrukHai(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMUrukHai<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMUrukHai entity) {
        return entity.getUrukHaiTypeName();
    }

    @Override
    protected void scale(EntityTTMUrukHai entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}