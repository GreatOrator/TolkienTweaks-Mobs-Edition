package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMMirkwoodSpider;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMirkwoodSpider;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMirkwoodSpider extends MobRenderer<EntityTTMMirkwoodSpider, ModelTTMMirkwoodSpider<EntityTTMMirkwoodSpider>> {
    public RenderTTMMirkwoodSpider(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMirkwoodSpider<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMMirkwoodSpider entity) {
        return entity.getMirkwoodSpiderTypeName();
    }

    @Override
    protected void scale(EntityTTMMirkwoodSpider entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}