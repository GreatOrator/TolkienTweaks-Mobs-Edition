package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.WatcherEntity;
import com.greatorator.tolkienmobs.entity.boss.model.WatcherModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class WatcherRender extends GeoEntityRenderer<WatcherEntity> {
    public WatcherRender(EntityRendererProvider.Context context) {
        super(context, new WatcherModel());
        this.shadowRadius = 2.1f;
    }

    public void render(WatcherEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(WatcherEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/tmwatcher.png");
    }
}