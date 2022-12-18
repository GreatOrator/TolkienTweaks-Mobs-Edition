package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.SwampHagEntity;
import com.greatorator.tolkienmobs.entity.monster.model.SwampHagModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class SwampHagRender extends GeoEntityRenderer<SwampHagEntity> {
    protected RenderType renderType;
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/swamphag/swamp_hag1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/swamphag/swamp_hag1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/swamphag/swamp_hag2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/swamphag/swamp_hag3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/swamphag/swamp_hag4.png"));
            });

    public SwampHagRender(EntityRendererProvider.Context context) {
        super(context, new SwampHagModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(SwampHagEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    public RenderType getRenderType(SwampHagEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        this.renderType = RenderType.entityTranslucent(textureLocation);
        return renderType;
    }
}