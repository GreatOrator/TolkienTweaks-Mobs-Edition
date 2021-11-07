package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMMinotaur;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMinotaur;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMinotaur extends MobRenderer<EntityTTMMinotaur, ModelTTMMinotaur<EntityTTMMinotaur>> {
    public RenderTTMMinotaur(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMinotaur<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMMinotaur entity) {
        return entity.getMinotaurTypeName();
    }

    protected void scale(EntityTTMMinotaur entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
