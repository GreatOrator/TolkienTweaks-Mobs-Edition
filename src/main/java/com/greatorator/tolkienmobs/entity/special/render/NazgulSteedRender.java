package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.NazgulSteedEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class NazgulSteedRender extends AbstractHorseRenderer<NazgulSteedEntity, HorseModel<NazgulSteedEntity>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/horse/nazgulsteed.png");
    public NazgulSteedRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HorseModel<>(0.0F), 1.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(NazgulSteedEntity p_110775_1_) {
        return mobTexture;
    }

    @Override
    protected void scale(NazgulSteedEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}