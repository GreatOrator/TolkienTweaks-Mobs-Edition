package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.WargEntity;
import com.greatorator.tolkienmobs.entity.monster.model.WargModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
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

public class WargRender extends GeoEntityRenderer<WargEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/warg/warg1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/warg/warg1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/warg/warg2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/warg/warg3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/warg/warg4.png"));
            });

    public WargRender(EntityRendererProvider.Context context) {
        super(context, new WargModel());
        this.shadowRadius = 0.6f;
    }

    @Override
    public ResourceLocation getTextureLocation(WargEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    public RenderType getRenderType(WargEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1.5F, 1.5F, 1.5F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}