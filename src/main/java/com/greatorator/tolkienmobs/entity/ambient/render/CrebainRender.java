package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.entity.ambient.CrebainEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.CrebainModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class CrebainRender extends GeoEntityRenderer<CrebainEntity> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID, "textures/entity/birds/crebain.png");

    public CrebainRender(EntityRendererProvider.Context ctx) {
        super(ctx, new CrebainModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CrebainEntity entity) {
        return mobTexture;
    }

    @Override
    public RenderType getRenderType(CrebainEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(0.3F, 0.3F, 0.3F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}