package com.greatorator.tolkienmobs.entity.merchant.render;

import com.greatorator.tolkienmobs.entity.merchant.HumanEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.HumanModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HumanRender extends MobRenderer<HumanEntity, HumanModel<HumanEntity>> {
    public HumanRender(EntityRendererManager entityIn, HumanModel<HumanEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
    }

    @Override
    public ResourceLocation getTextureLocation(HumanEntity entity) {
        return entity.getHumanTypeName();
    }

    @Override
    protected void scale(HumanEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
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