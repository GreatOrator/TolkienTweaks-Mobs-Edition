package com.greatorator.tolkienmobs.entity.passive.render;

import com.greatorator.tolkienmobs.entity.passive.MumakilEntity;
import com.greatorator.tolkienmobs.entity.passive.model.MumakilModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MumakilRender extends MobRenderer<MumakilEntity, MumakilModel<MumakilEntity>> {
    public MumakilRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MumakilModel<>(), 3.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(MumakilEntity entity) {
        return entity.getMumakilTypeName();
    }

    @Override
    protected void scale(MumakilEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}