package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMBrigand;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBrigand;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMBrigand extends MobRenderer<EntityTTMBrigand, ModelTTMBrigand<EntityTTMBrigand>> {
    public RenderTTMBrigand(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBrigand<>(0.0F, false), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new ModelTTMBrigand<EntityTTMBrigand>(0.5F, false), new ModelTTMBrigand<EntityTTMBrigand>(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMBrigand entity) {
        return entity.getBrigandTypeName();
    }

    protected void scale(EntityTTMBrigand entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}