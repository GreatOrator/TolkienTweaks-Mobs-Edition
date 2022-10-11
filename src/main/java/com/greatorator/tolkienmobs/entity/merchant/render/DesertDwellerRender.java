package com.greatorator.tolkienmobs.entity.merchant.render;

import com.greatorator.tolkienmobs.entity.merchant.DesertDwellerEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.HumanModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DesertDwellerRender extends MobRenderer<DesertDwellerEntity, HumanModel<DesertDwellerEntity>> {
    public DesertDwellerRender(EntityRendererManager entityIn, HumanModel<DesertDwellerEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
    }

    @Override
    public ResourceLocation getTextureLocation(DesertDwellerEntity entity) {
        return entity.getDwellerTypeName();
    }

    @Override
    protected void scale(DesertDwellerEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
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