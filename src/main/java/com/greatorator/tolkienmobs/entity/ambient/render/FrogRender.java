package com.greatorator.tolkienmobs.entity.ambient.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.ambient.FrogEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.FrogModel;
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

public class FrogRender extends GeoEntityRenderer<FrogEntity> {
    public static final Map<AmbientVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(AmbientVariant.class), (enumMap) -> {
                enumMap.put(AmbientVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_green.png"));
                enumMap.put(AmbientVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_yellow.png"));
                enumMap.put(AmbientVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_white.png"));
                enumMap.put(AmbientVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_red.png"));
                enumMap.put(AmbientVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_rainbow.png"));
                enumMap.put(AmbientVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_green.png"));
                enumMap.put(AmbientVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_black.png"));
                enumMap.put(AmbientVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/toaddle/toaddle_blue.png"));
                enumMap.put(AmbientVariant.MURDER,
                        new ResourceLocation(MODID, "textures/entity/toaddle/murderfrog.png"));
            });

    public FrogRender(EntityRendererProvider.Context ctx) {
        super(ctx, new FrogModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FrogEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderType getRenderType(FrogEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(0.3F, 0.3F, 0.3F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}