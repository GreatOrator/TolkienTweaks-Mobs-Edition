package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.EntityTTMNazgulSteed;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class RenderTTMNazgulSteed extends AbstractHorseRenderer<EntityTTMNazgulSteed, HorseModel<EntityTTMNazgulSteed>> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/horse/nazgulsteed.png");
    public RenderTTMNazgulSteed(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HorseModel<>(0.0F), 1.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMNazgulSteed p_110775_1_) {
        return mobTexture;
    }

    protected void scale(EntityTTMNazgulSteed entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}