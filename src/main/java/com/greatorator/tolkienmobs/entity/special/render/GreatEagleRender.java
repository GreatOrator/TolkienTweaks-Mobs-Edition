package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.special.GreatEagleEntity;
import com.greatorator.tolkienmobs.entity.special.model.GreatEagleModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GreatEagleRender extends GeoEntityRenderer<GreatEagleEntity> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID, "textures/entity/birds/entitygreateagle.png");

    public GreatEagleRender(EntityRendererProvider.Context ctx) {
        super(ctx, new GreatEagleModel());
        this.shadowRadius = 2.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(GreatEagleEntity entity) {
        return mobTexture;
    }

    @Override
    public RenderType getRenderType(GreatEagleEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1.4F, 1.4F, 1.4F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}