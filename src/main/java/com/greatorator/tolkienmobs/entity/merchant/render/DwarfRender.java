package com.greatorator.tolkienmobs.entity.merchant.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.DwarfEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.DwarfModel;
import com.greatorator.tolkienmobs.entity.merchant.variant.EntityVariant;
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

public class DwarfRender extends GeoEntityRenderer<DwarfEntity> {
    public static final Map<EntityVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(EntityVariant.class), (enumMap) -> {
                enumMap.put(EntityVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf1.png"));
                enumMap.put(EntityVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf1.png"));
                enumMap.put(EntityVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf2.png"));
                enumMap.put(EntityVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf3.png"));
                enumMap.put(EntityVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf4.png"));
                enumMap.put(EntityVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf5.png"));
                enumMap.put(EntityVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf6.png"));
                enumMap.put(EntityVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf7.png"));
                enumMap.put(EntityVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf8.png"));
                enumMap.put(EntityVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf9.png"));
                enumMap.put(EntityVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/dwarf/dwarf10.png"));
            });

    public DwarfRender(EntityRendererProvider.Context context) {
        super(context, new DwarfModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderType getRenderType(DwarfEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4F, 0.4F, 0.4F);
        } else {
            stack.scale(0.9F, 0.9F, 0.9F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}