package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.client.render.HeldItemLayerMainOnly;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMHaradrim;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMHaradrim;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMHaradrim extends MobRenderer<EntityTTMHaradrim, ModelTTMHaradrim<EntityTTMHaradrim>> {

    public RenderTTMHaradrim(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMHaradrim<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayerMainOnly<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMHaradrim entity) {
        return entity.getHaradrimTypeName();
    }

    @Override
    protected void scale(EntityTTMHaradrim entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}