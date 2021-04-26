package com.greatorator.tolkienmobs.entity.merchant.render;

import com.greatorator.tolkienmobs.entity.merchant.EntityTTMDwarf;
import com.greatorator.tolkienmobs.entity.merchant.model.ModelTTMDwarf;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMDwarf extends MobRenderer<EntityTTMDwarf, ModelTTMDwarf<EntityTTMDwarf>> {
    public RenderTTMDwarf(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMDwarf<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMDwarf entity) {
        return entity.getDwarfTypeName();
    }

    protected void scale(EntityTTMDwarf entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.95F;
        if (entitylivingbaseIn.isBaby()) {
            f = (float)((double)f * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        matrixStackIn.scale(f, f, f);
    }
}