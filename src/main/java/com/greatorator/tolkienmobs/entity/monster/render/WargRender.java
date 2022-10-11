package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.WargEntity;
import com.greatorator.tolkienmobs.entity.monster.model.WargModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WargRender extends MobRenderer<WargEntity, WargModel<WargEntity>> {
    public WargRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WargModel<>(1, 0.0F), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(WargEntity entity) {
        return entity.getWargTypeName();
    }

    @Override
    protected void scale(WargEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}