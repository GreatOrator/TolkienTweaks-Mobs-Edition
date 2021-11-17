package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.EntityTTMShelob;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMirkwoodSpider;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RenderTTMShelob extends MobRenderer<EntityTTMShelob, ModelTTMMirkwoodSpider<EntityTTMShelob>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/tmshelob.png");
    public RenderTTMShelob(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMirkwoodSpider<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMShelob entity) {
        return mobTexture;
    }

    @Override
    protected void scale(EntityTTMShelob entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(1.5F, 1.5F, 1.5F);
    }
}