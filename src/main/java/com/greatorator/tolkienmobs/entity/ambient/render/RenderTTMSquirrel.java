package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.EntityTTMSquirrel;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMSquirrel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderTTMSquirrel extends MobRenderer<EntityTTMSquirrel, ModelTTMSquirrel<EntityTTMSquirrel>> {
    public RenderTTMSquirrel(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMSquirrel<>(), 0.3F);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityTTMSquirrel entity) {
        return entity.getSquirrelTypeName();
    }

    protected void scale(EntityTTMSquirrel entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.2F, 0.2F, 0.2F);
    }

}