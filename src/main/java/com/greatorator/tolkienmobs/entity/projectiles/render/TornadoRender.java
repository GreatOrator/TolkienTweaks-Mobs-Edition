package com.greatorator.tolkienmobs.entity.projectiles.render;

import com.greatorator.tolkienmobs.entity.projectiles.TornadoEntity;
import com.greatorator.tolkienmobs.entity.projectiles.model.TornadoModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

@SuppressWarnings({ "unchecked", "removal" })
public class TornadoRender extends GeoProjectilesRenderer<TornadoEntity> {
	public TornadoRender(EntityRendererProvider.Context renderManager) {
            super(renderManager, new TornadoModel());
        }

    @Override
    public void renderEarly(TornadoEntity animatable, PoseStack stackIn, float partialTicks,
                            MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float alpha) {
        if (!animatable.isBlast()) {
            float scaleFactor = 1.25F;
            stackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {
            stackIn.mulPose(Vector3f.YP.rotationDegrees(animatable.getYRot() * ((float) Math.PI / 180F)));
        }

        if (animatable.lifeTime <= 1) {
            float scaleFactor = 0.0F;
            stackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {

        }
    }

    @Override
    public RenderType getRenderType(TornadoEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}