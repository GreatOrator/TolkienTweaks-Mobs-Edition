package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.WitchKingEntity;
import com.greatorator.tolkienmobs.entity.boss.model.WitchKingModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class WitchKingRender extends MobRenderer<WitchKingEntity, WitchKingModel<WitchKingEntity>> {
private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/witchking.png");

public WitchKingRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WitchKingModel<>(0.0F, false), 1.0F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new WitchKingModel<WitchKingEntity>(0.5F, false), new WitchKingModel<WitchKingEntity>(1.0F, true)));
        }

@Override
public ResourceLocation getTextureLocation(WitchKingEntity entity) {
        return mobTexture;
        }

@Override
protected void scale(WitchKingEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        }
}