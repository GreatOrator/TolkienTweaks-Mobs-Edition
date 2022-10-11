package com.greatorator.tolkienmobs.entity.passive.render;

import com.greatorator.tolkienmobs.entity.passive.GoatEntity;
import com.greatorator.tolkienmobs.entity.passive.model.GoatModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoatRender extends MobRenderer<GoatEntity, GoatModel<GoatEntity>> {
    public GoatRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoatModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(GoatEntity entity) {
        return entity.getGoatTypeName();
    }

    @Override
    protected void scale(GoatEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}