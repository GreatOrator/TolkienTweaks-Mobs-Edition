package com.greatorator.tolkienmobs.entity.passive.render;

import com.greatorator.tolkienmobs.entity.passive.EntityTTMAuroch;
import com.greatorator.tolkienmobs.entity.passive.model.ModelTTMAuroch;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMAuroch extends MobRenderer<EntityTTMAuroch, ModelTTMAuroch<EntityTTMAuroch>> {
    public RenderTTMAuroch(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMAuroch<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMAuroch entity) {
        return entity.getAurochTypeName();
    }

    protected void scale(EntityTTMAuroch entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}