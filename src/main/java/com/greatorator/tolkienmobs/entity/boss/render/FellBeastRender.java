package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.FellBeastEntity;
import com.greatorator.tolkienmobs.entity.boss.model.FellBeastModel;
import com.greatorator.tolkienmobs.entity.boss.render.layer.PassengerLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class FellBeastRender extends GeoEntityRenderer<FellBeastEntity> {
    public FellBeastRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FellBeastModel());
        this.addLayer(new PassengerLayer<>(this));
        this.shadowRadius = 2.1f;
    }

    @Override
    public RenderType getRenderType(FellBeastEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityCutoutNoCull(texture);
    }
}
