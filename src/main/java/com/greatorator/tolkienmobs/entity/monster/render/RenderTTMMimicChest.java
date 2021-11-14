package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMMimicChest;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMimicChest;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMimicChest extends MobRenderer<EntityTTMMimicChest, ModelTTMMimicChest> {
    public RenderTTMMimicChest(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMimicChest(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMMimicChest entity) {
        return entity.getMimicChestTypeName();
    }

    @Override
    protected void scale(EntityTTMMimicChest entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}