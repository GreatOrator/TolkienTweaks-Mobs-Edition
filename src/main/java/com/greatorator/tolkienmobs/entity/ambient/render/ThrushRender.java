package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.ThrushEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.ThrushModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ThrushRender extends GeoEntityRenderer<ThrushEntity> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID, "textures/entity/birds/thrush.png");

    public ThrushRender(EntityRendererProvider.Context ctx) {
        super(ctx, new ThrushModel());
    }

    @Override
    public ResourceLocation getTextureLocation(ThrushEntity entity) {
        return mobTexture;
    }

    @Override
    public RenderType getRenderType(ThrushEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(0.3F, 0.3F, 0.3F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}