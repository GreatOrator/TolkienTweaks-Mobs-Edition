package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.GwahirEntity;
import com.greatorator.tolkienmobs.entity.boss.model.GwahirModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GwahirRender extends MobRenderer<GwahirEntity, GwahirModel> {
    private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/birds/entitygreateagle.png");

    public GwahirRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GwahirModel(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(GwahirEntity entity) {
        return mobTexture;
    }

    @Override
    protected void scale(GwahirEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}