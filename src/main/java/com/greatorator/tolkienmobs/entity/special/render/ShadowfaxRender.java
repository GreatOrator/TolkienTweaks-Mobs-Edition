package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.ShadowfaxEntity;
import com.greatorator.tolkienmobs.entity.special.model.ShadowfaxModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ShadowfaxRender extends GeoEntityRenderer<ShadowfaxEntity> {
    public ShadowfaxRender(EntityRendererProvider.Context context) {
        super(context, new ShadowfaxModel());
        this.shadowRadius = 1.3F;
    }

    @Override
    public void renderEarly(ShadowfaxEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
    }

    public void render(ShadowfaxEntity entity, float entityYaw, float partialTicks, @Nonnull PoseStack stack, @Nonnull MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(ShadowfaxEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/horse/shadowfax.png");
    }

    @Override
    public RenderType getRenderType(ShadowfaxEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }
}