package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMFellSpirit;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBarrowWight;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMFellSpirit extends MobRenderer<EntityTTMFellSpirit, ModelTTMBarrowWight<EntityTTMFellSpirit>> {
    public RenderTTMFellSpirit(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBarrowWight<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMFellSpirit entity) {
        return entity.getFellSpiritTypeName();
    }

    protected void scale(EntityTTMFellSpirit entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}