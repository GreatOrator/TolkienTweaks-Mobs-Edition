package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.MimicChestEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MimicChestModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MimicChestRender extends MobRenderer<MimicChestEntity, MimicChestModel> {
    public MimicChestRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MimicChestModel(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(MimicChestEntity entity) {
        return entity.getMimicChestTypeName();
    }

    @Override
    protected void scale(MimicChestEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}