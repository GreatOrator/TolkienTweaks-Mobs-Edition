package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMTreeEnt;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMTreeEnt;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMTreeEnt extends MobRenderer<EntityTTMTreeEnt, ModelTTMTreeEnt<EntityTTMTreeEnt>> {
    public RenderTTMTreeEnt(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMTreeEnt<>(0.0F, true), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMTreeEnt entity) {
        return entity.getTreeEntTypeName();
    }

    @Override
    protected void scale(EntityTTMTreeEnt entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}