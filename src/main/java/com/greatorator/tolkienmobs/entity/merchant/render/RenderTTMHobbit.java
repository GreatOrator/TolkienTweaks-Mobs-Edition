package com.greatorator.tolkienmobs.entity.merchant.render;

import com.greatorator.tolkienmobs.entity.merchant.HobbitEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.HobbitModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMHobbit extends MobRenderer<HobbitEntity, HobbitModel<HobbitEntity>> {
    public RenderTTMHobbit(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HobbitModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(HobbitEntity entity) {
        return entity.getHobbitTypeName();
    }

    @Override
    protected void scale(HobbitEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.7F;
        if (entitylivingbaseIn.isBaby()) {
            f = (float)((double)f * 0.5D);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.5F;
        }

        matrixStackIn.scale(f, f, f);
    }
}