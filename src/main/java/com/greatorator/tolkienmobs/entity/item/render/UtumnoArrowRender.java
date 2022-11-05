package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.entity.item.UtumnoArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class UtumnoArrowRender extends ArrowRenderer<UtumnoArrowEntity> {
    private static final ResourceLocation UTUMNO_ARROW_TEXTURES = new ResourceLocation(MODID,"textures/entity/ammo_utumno_arrow.png");

    public UtumnoArrowRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(UtumnoArrowEntity entity) {
        return UTUMNO_ARROW_TEXTURES;
    }
}
