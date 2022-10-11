package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.client.render.HeldItemLayerMainOnly;
import com.greatorator.tolkienmobs.entity.monster.MordorOrcEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MordorOrcModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MordorOrcRender extends MobRenderer<MordorOrcEntity, MordorOrcModel<MordorOrcEntity>> {
    public MordorOrcRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MordorOrcModel<>(0.0F, true), 1.0F);
        this.addLayer(new HeldItemLayerMainOnly<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MordorOrcEntity entity) {
        return entity.getMordorOrcTypeName();
    }

    @Override
    protected void scale(MordorOrcEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}