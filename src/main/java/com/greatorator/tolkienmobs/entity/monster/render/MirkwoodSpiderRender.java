package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.MirkwoodSpiderEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MirkwoodSpiderModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MirkwoodSpiderRender extends MobRenderer<MirkwoodSpiderEntity, MirkwoodSpiderModel<MirkwoodSpiderEntity>> {
    public MirkwoodSpiderRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MirkwoodSpiderModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(MirkwoodSpiderEntity entity) {
        return entity.getMirkwoodSpiderTypeName();
    }

    @Override
    protected void scale(MirkwoodSpiderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}