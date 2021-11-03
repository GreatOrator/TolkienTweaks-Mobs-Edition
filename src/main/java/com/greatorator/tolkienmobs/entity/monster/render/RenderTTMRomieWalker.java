package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMRomieWalker;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBrigand;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMRomieWalker extends MobRenderer<EntityTTMRomieWalker, ModelTTMBrigand<EntityTTMRomieWalker>> {
    public RenderTTMRomieWalker(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBrigand<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMRomieWalker entity) {
        return entity.getRomieWalkerTypeName();
    }

    protected void scale(EntityTTMRomieWalker entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}