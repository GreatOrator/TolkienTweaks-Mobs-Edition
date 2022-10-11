package com.greatorator.tolkienmobs.entity.merchant.render;

import com.greatorator.tolkienmobs.entity.VillagerEntity;
import com.greatorator.tolkienmobs.entity.merchant.ElvesEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.ElvesModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ElvesRender extends MobRenderer<ElvesEntity, ElvesModel<ElvesEntity>> {
    public ElvesRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ElvesModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(ElvesEntity entity) {
        return entity.getElvesTypeName();
    }

    protected void preRenderCallback(VillagerEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.98F;
        if (entitylivingbaseIn.isBaby()) {
            f = (float) ((double) f * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        matrixStackIn.scale(f, f, f);
    }
}