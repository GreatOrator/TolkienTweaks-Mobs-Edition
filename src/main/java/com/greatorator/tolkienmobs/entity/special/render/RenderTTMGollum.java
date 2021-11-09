package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.EntityTTMGollum;
import com.greatorator.tolkienmobs.entity.special.model.ModelTTMGollum;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RenderTTMGollum extends MobRenderer<EntityTTMGollum, ModelTTMGollum<EntityTTMGollum>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/gollum.png");

    public RenderTTMGollum(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMGollum<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMGollum entity) {
        return mobTexture;
    }

    protected void scale(EntityTTMGollum entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}