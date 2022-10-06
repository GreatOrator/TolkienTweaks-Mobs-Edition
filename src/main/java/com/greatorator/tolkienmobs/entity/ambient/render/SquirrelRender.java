package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.SquirrelEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.SquirrelModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SquirrelRender extends MobRenderer<SquirrelEntity, SquirrelModel<SquirrelEntity>> {
    public SquirrelRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SquirrelModel<>(), 0.3F);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(SquirrelEntity entity) {
        return entity.getSquirrelTypeName();
    }

    @Override
    protected void scale(SquirrelEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.2F, 0.2F, 0.2F);
    }

}