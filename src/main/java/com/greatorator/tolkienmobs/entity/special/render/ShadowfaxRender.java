package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.ShadowfaxEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class ShadowfaxRender extends AbstractHorseRenderer<ShadowfaxEntity, HorseModel<ShadowfaxEntity>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/horse/shadowfax.png");
    public ShadowfaxRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HorseModel<>(0.0F), 1.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(ShadowfaxEntity p_110775_1_) {
        return mobTexture;
    }

    @Override
    protected void scale(ShadowfaxEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}