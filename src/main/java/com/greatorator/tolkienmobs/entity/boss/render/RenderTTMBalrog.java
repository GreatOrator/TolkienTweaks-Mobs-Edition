package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.client.render.HeldItemLayerMainOnly;
import com.greatorator.tolkienmobs.entity.boss.EntityTTMBalrog;
import com.greatorator.tolkienmobs.entity.boss.model.ModelTTMBalrog;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RenderTTMBalrog extends MobRenderer<EntityTTMBalrog, ModelTTMBalrog<EntityTTMBalrog>> {
    private ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/balrog.png");

    public RenderTTMBalrog(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBalrog<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayerMainOnly<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMBalrog entity) {
        return mobTexture;
    }

    @Override
    protected void scale(EntityTTMBalrog entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }
}