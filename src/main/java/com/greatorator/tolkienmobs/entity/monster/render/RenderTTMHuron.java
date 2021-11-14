package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMHuron;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMHuron;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMHuron extends MobRenderer<EntityTTMHuron, ModelTTMHuron<EntityTTMHuron>> {
    public RenderTTMHuron(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMHuron<>(0.0F), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMHuron entity) {
        return entity.getHuronTypeName();
    }

    @Override
    protected void scale(EntityTTMHuron entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}