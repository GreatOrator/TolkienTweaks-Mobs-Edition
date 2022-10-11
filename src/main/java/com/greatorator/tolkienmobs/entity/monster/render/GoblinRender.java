package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.GoblinEntity;
import com.greatorator.tolkienmobs.entity.monster.model.GoblinModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoblinRender extends MobRenderer<GoblinEntity, GoblinModel<GoblinEntity>> {
    public GoblinRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoblinModel<>(0.0F, true), 0.5F);
        //this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinEntity entity) {
        return entity.getGoblinTypeName();
    }

    @Override
    protected void scale(GoblinEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}