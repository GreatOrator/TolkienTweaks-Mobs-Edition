package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMTroll;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMTroll;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMTroll extends MobRenderer<EntityTTMTroll, ModelTTMTroll<EntityTTMTroll>> {
    public RenderTTMTroll(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMTroll<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMTroll entity) {
        return entity.getTrollTypeName();
    }

    @Override
    protected void scale(EntityTTMTroll entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}