package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMDeepClaw;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMDeepClaw;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMDeepClaw extends MobRenderer<EntityTTMDeepClaw, ModelTTMDeepClaw<EntityTTMDeepClaw>> {
    public RenderTTMDeepClaw(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMDeepClaw<>(1, 0.0F), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMDeepClaw entity) {
        return entity.getDeepclawTypeName();
    }

    @Override
    protected void scale(EntityTTMDeepClaw entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}