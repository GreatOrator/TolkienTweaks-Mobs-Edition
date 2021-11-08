package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.TTMGhostLayer;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMBarrowWight;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMBarrowWight;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMBarrowWight extends MobRenderer<EntityTTMBarrowWight, ModelTTMBarrowWight<EntityTTMBarrowWight>> {
    public RenderTTMBarrowWight(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMBarrowWight<>(0.0F, false), 1.0F);
        this.addLayer(new TTMGhostLayer(this));
        this.addLayer(new BipedArmorLayer(this, new ModelTTMBarrowWight(0.5F, true), new ModelTTMBarrowWight(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMBarrowWight entity) {
        return entity.getBarrowTypeName();
    }

    protected void scale(EntityTTMBarrowWight entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}