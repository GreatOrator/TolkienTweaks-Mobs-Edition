package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMWarg;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMWarg;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMWarg extends MobRenderer<EntityTTMWarg, ModelTTMWarg<EntityTTMWarg>> {
    public RenderTTMWarg(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMWarg<>(1, 0.0F), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMWarg entity) {
        return entity.getWargTypeName();
    }

    protected void scale(EntityTTMWarg entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}