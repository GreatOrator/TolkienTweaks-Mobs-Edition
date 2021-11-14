package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.TTMGhostLayer;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMFellSpirit;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBarrowWight;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMFellSpirit extends MobRenderer<EntityTTMFellSpirit, ModelTTMBarrowWight<EntityTTMFellSpirit>> {
    public RenderTTMFellSpirit(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBarrowWight<>(0.0F, false), 1.0F);
        this.addLayer(new TTMGhostLayer(this));
        this.addLayer(new BipedArmorLayer(this, new ModelTTMBarrowWight(0.5F, true), new ModelTTMBarrowWight(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMFellSpirit entity) {
        return entity.getFellSpiritTypeName();
    }

    @Override
    protected void scale(EntityTTMFellSpirit entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}