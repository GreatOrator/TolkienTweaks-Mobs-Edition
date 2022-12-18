package com.greatorator.tolkienmobs.entity.projectiles.render;

import com.greatorator.tolkienmobs.entity.projectiles.GaladhrimArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
@OnlyIn(Dist.CLIENT)
public class GaladhrimArrowRender extends ArrowRenderer<GaladhrimArrowEntity> {
    private static final ResourceLocation GALADHRIM_ARROW_TEXTURES = new ResourceLocation(MODID,"textures/entity/ammo_galadhrim_arrow.png");

    public GaladhrimArrowRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(GaladhrimArrowEntity entity) {
        return GALADHRIM_ARROW_TEXTURES;
    }
}
