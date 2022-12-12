package com.greatorator.tolkienmobs.entity.ambient.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.ambient.RatEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.RatModel;
import com.greatorator.tolkienmobs.entity.ambient.variant.AmbientVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RatRender extends GeoEntityRenderer<RatEntity> {
    public static final Map<AmbientVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(AmbientVariant.class), (enumMap) -> {
                enumMap.put(AmbientVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/entityttmrat/entityttmrat1.png"));
                enumMap.put(AmbientVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/entityttmrat/entityttmrat1.png"));
                enumMap.put(AmbientVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/entityttmrat/entityttmrat2.png"));
                enumMap.put(AmbientVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/entityttmrat/entityttmrat3.png"));
                enumMap.put(AmbientVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/entityttmrat/entityttmrat4.png"));
                enumMap.put(AmbientVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/entityttmrat/entityttmrat5.png"));
            });

    public RatRender(EntityRendererProvider.Context ctx) {
        super(ctx, new RatModel());
    }

    @Override
    public ResourceLocation getTextureLocation(RatEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(AmbientVariant.DEFAULT));
    }

    @Override
    public RenderType getRenderType(RatEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(0.3F, 0.3F, 0.3F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
