package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.boss.model.ModelTTMWitchKing;
import com.greatorator.tolkienmobs.entity.special.NazgulEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class NazgulRender extends MobRenderer<NazgulEntity, ModelTTMWitchKing<NazgulEntity>> {
    private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/nazgul.png");

    public NazgulRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMWitchKing<>(0.0F, false), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new ModelTTMWitchKing<NazgulEntity>(0.5F, false), new ModelTTMWitchKing<NazgulEntity>(1.0F, true)));
    }

    @Override
    public ResourceLocation getTextureLocation(NazgulEntity entity) {
        return mobTexture;
    }

    @Override
    protected void scale(NazgulEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}