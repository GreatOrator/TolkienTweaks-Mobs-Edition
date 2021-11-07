package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.TTMHeldItemLayer;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMMordorOrc;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMordorOrc;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMordorOrc extends MobRenderer<EntityTTMMordorOrc, ModelTTMMordorOrc<EntityTTMMordorOrc>> {
    public RenderTTMMordorOrc(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMordorOrc<>(0.0F, true), 1.0F);
        this.addLayer(new TTMHeldItemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMMordorOrc entity) {
        return entity.getMordorOrcTypeName();
    }

    protected void scale(EntityTTMMordorOrc entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}