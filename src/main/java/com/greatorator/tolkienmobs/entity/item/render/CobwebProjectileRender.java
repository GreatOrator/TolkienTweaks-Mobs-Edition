package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.entity.item.CobwebProjectileEntity;
import com.greatorator.tolkienmobs.entity.item.model.CobwebProjectileModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class CobwebProjectileRender extends GeoProjectilesRenderer<CobwebProjectileEntity> {
    public CobwebProjectileRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CobwebProjectileModel());
    }

    @Override
    public void render(CobwebProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        float scaleFactor = 1.0F;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public RenderType getRenderType(CobwebProjectileEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
