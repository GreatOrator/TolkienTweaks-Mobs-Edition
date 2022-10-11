package com.greatorator.tolkienmobs.entity.passive.render;

import com.greatorator.tolkienmobs.entity.passive.AurochEntity;
import com.greatorator.tolkienmobs.entity.passive.model.AurochModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AurochRender extends MobRenderer<AurochEntity, AurochModel<AurochEntity>> {
    public AurochRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AurochModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(AurochEntity entity) {
        return entity.getAurochTypeName();
    }

    @Override
    protected void scale(AurochEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}