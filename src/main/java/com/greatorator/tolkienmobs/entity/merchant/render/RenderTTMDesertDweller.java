package com.greatorator.tolkienmobs.entity.merchant.render;

import com.greatorator.tolkienmobs.entity.merchant.EntityTTMDesertDweller;
import com.greatorator.tolkienmobs.entity.merchant.model.ModelTTMHuman;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMDesertDweller extends MobRenderer<EntityTTMDesertDweller, ModelTTMHuman<EntityTTMDesertDweller>> {
    public RenderTTMDesertDweller(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMHuman<>(0.0F, false), 1.0F);
        this.addLayer(new BipedArmorLayer(this, new ModelTTMHuman<EntityTTMDesertDweller>(0.5F, false), new ModelTTMHuman<EntityTTMDesertDweller>(1.0F, false)));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMDesertDweller entity) {
        return entity.getDwellerTypeName();
    }

    @Override
    protected void scale(EntityTTMDesertDweller entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.93F;
        if (entitylivingbaseIn.isBaby()) {
            f = (float)((double)f * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        matrixStackIn.scale(f, f, f);
    }
}