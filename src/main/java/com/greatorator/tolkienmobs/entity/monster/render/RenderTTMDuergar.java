package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.merchant.model.ModelTTMDwarf;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMDuergar;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMDuergar extends MobRenderer<EntityTTMDuergar, ModelTTMDwarf<EntityTTMDuergar>> {
    public RenderTTMDuergar(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMDwarf<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMDuergar entity) {
        return entity.getDuergarTypeName();
    }

    protected void scale(EntityTTMDuergar entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}