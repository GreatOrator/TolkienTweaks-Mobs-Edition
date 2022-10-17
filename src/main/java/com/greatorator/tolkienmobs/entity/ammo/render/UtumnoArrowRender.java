package com.greatorator.tolkienmobs.entity.ammo.render;

import com.greatorator.tolkienmobs.entity.ammo.UtumnoArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class UtumnoArrowRender extends ArrowRenderer<UtumnoArrowEntity> {
    private static final ResourceLocation UTUMNO_ARROW_TEXTURES = new ResourceLocation(MODID,"textures/entity/ammo_utumno_arrow.png");

    public UtumnoArrowRender(EntityRendererManager rendererManager) {
        super(rendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(UtumnoArrowEntity entity) {
        return UTUMNO_ARROW_TEXTURES;
    }
}
