package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.EntityTTMWitchKing;
import com.greatorator.tolkienmobs.entity.boss.model.ModelTTMWitchKing;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RenderTTMWitchKing extends MobRenderer<EntityTTMWitchKing, ModelTTMWitchKing<EntityTTMWitchKing>> {
private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/witchking.png");

public RenderTTMWitchKing(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMWitchKing<>(0.0F, false), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new ModelTTMWitchKing<EntityTTMWitchKing>(0.5F, false), new ModelTTMWitchKing<EntityTTMWitchKing>(1.0F, true)));
        }

@Override
public ResourceLocation getTextureLocation(EntityTTMWitchKing entity) {
        return mobTexture;
        }

@Override
protected void scale(EntityTTMWitchKing entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        }
}