package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.GollumEntity;
import com.greatorator.tolkienmobs.entity.special.model.GollumModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GollumRender extends MobRenderer<GollumEntity, GollumModel<GollumEntity>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/gollum.png");

    public GollumRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GollumModel<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(GollumEntity entity) {
        return mobTexture;
    }

    @Override
    protected void scale(GollumEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}