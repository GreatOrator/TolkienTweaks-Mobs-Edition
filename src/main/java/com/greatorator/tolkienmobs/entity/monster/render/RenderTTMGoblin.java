package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMGoblin;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMGoblin;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMGoblin extends MobRenderer<EntityTTMGoblin, ModelTTMGoblin<EntityTTMGoblin>> {
    public RenderTTMGoblin(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMGoblin<>(0.0F, true), 0.5F);
        //this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMGoblin entity) {
        return entity.getGoblinTypeName();
    }

    protected void scale(EntityTTMGoblin entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}