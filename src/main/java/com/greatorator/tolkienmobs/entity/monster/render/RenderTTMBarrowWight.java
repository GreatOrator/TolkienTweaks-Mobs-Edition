package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMBarrowWight;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBarrowWight;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMBarrowWight extends MobRenderer<EntityTTMBarrowWight, ModelTTMBarrowWight<EntityTTMBarrowWight>> {
    public RenderTTMBarrowWight(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBarrowWight<>(0.0F, true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMBarrowWight entity) {
        return entity.getBarrowTypeName();
    }

    protected void scale(EntityTTMBarrowWight entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}