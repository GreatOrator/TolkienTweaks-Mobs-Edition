package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.TTMHeldItemLayer;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMHaradrim;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTMHaradrim;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMHaradrim extends MobRenderer<EntityTTMHaradrim, ModelTMHaradrim<EntityTTMHaradrim>> {
    public RenderTTMHaradrim(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTMHaradrim<>(0.0F, true), 1.0F);
        this.addLayer(new TTMHeldItemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMHaradrim entity) {
        return entity.getHaradrimTypeName();
    }

    protected void scale(EntityTTMHaradrim entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}