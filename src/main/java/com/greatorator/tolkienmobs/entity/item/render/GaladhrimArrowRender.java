package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.entity.item.GaladhrimArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
@OnlyIn(Dist.CLIENT)
public class GaladhrimArrowRender extends ArrowRenderer<GaladhrimArrowEntity> {
    private static final ResourceLocation GALADHRIM_ARROW_TEXTURES = new ResourceLocation(MODID,"textures/entity/ammo_galadhrim_arrow.png");

    public GaladhrimArrowRender(EntityRendererManager rendererManager) {
        super(rendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(GaladhrimArrowEntity entity) {
        return GALADHRIM_ARROW_TEXTURES;
    }
}
