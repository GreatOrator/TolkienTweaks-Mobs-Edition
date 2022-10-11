package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.ShelobEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MirkwoodSpiderModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ShelobRender extends MobRenderer<ShelobEntity, MirkwoodSpiderModel<ShelobEntity>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/tmshelob.png");
    public ShelobRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MirkwoodSpiderModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(ShelobEntity entity) {
        return mobTexture;
    }

    @Override
    protected void scale(ShelobEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(1.5F, 1.5F, 1.5F);
    }
}