package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMBrigand;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBrigand;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMBrigand extends MobRenderer<EntityTTMBrigand, ModelTTMBrigand<EntityTTMBrigand>> {
    public RenderTTMBrigand(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBrigand<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMBrigand entity) {
        return entity.getBrigandTypeName();
    }

    protected void scale(EntityTTMBrigand entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}