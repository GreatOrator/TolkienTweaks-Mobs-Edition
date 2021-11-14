package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.EntityTTMGoblinKing;
import com.greatorator.tolkienmobs.entity.boss.model.ModelTTMGoblinKing;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMGoblinKing extends MobRenderer<EntityTTMGoblinKing, ModelTTMGoblinKing<EntityTTMGoblinKing>> {
    public RenderTTMGoblinKing(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMGoblinKing<>(0.0F, true), 0.5F);
        //this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMGoblinKing entity) {
        return entity.getGoblinKingTypeName();
    }

    @Override
    protected void scale(EntityTTMGoblinKing entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}