package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.GreatEagleEntity;
import com.greatorator.tolkienmobs.entity.special.model.GreatEagleModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GreatEagleRender extends MobRenderer<GreatEagleEntity, GreatEagleModel> {
    private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/birds/entitygreateagle.png");

    public GreatEagleRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GreatEagleModel(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(GreatEagleEntity entity) {
        return mobTexture;
    }

    @Override
    protected void scale(GreatEagleEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}