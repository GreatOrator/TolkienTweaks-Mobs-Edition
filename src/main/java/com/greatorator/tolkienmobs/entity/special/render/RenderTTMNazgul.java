package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.boss.model.ModelTTMWitchKing;
import com.greatorator.tolkienmobs.entity.special.EntityTTMNazgul;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RenderTTMNazgul extends MobRenderer<EntityTTMNazgul, ModelTTMWitchKing<EntityTTMNazgul>> {
    private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/nazgul.png");

    public RenderTTMNazgul(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMWitchKing<>(0.0F, false), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new ModelTTMWitchKing<EntityTTMNazgul>(0.5F, false), new ModelTTMWitchKing<EntityTTMNazgul>(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMNazgul entity) {
        return mobTexture;
    }

    @Override
    protected void scale(EntityTTMNazgul entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}