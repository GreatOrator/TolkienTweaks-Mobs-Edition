package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.GwahirEntity;
import com.greatorator.tolkienmobs.entity.boss.model.GwahirModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GwahirRender extends GeoEntityRenderer<GwahirEntity> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID, "textures/entity/birds/entitygreateagle.png");

    public GwahirRender(EntityRendererProvider.Context ctx) {
        super(ctx, new GwahirModel());
        this.shadowRadius = 2.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(GwahirEntity entity) {
        return mobTexture;
    }

    @Override
    public RenderType getRenderType(GwahirEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1.6F, 1.6F, 1.6F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}