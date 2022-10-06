package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.entity.boss.model.GoblinKingModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoblinKingRender extends MobRenderer<GoblinKingEntity, GoblinKingModel<GoblinKingEntity>> {
    public GoblinKingRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoblinKingModel<>(0.0F, true), 0.5F);
//        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinKingEntity entity) {
        return entity.getGoblinKingTypeName();
    }

    @Override
    protected void scale(GoblinKingEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}