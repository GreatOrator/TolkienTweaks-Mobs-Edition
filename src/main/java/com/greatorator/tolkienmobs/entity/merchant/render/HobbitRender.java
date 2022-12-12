package com.greatorator.tolkienmobs.entity.merchant.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.HobbitEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.HobbitModel;
import com.greatorator.tolkienmobs.entity.merchant.variant.MerchantVariant;
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

public class HobbitRender extends GeoEntityRenderer<HobbitEntity> {
    public static final Map<MerchantVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MerchantVariant.class), (enumMap) -> {
                enumMap.put(MerchantVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit1.png"));
                enumMap.put(MerchantVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit1.png"));
                enumMap.put(MerchantVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit2.png"));
                enumMap.put(MerchantVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit3.png"));
                enumMap.put(MerchantVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit4.png"));
                enumMap.put(MerchantVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit5.png"));
                enumMap.put(MerchantVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit6.png"));
                enumMap.put(MerchantVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit7.png"));
                enumMap.put(MerchantVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit8.png"));
                enumMap.put(MerchantVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit9.png"));
                enumMap.put(MerchantVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit10.png"));
                enumMap.put(MerchantVariant.AQUA,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit11.png"));
                enumMap.put(MerchantVariant.BEIGE,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit12.png"));
                enumMap.put(MerchantVariant.BROWN,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit13.png"));
                enumMap.put(MerchantVariant.CORAL,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit14.png"));
                enumMap.put(MerchantVariant.CYAN,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit15.png"));
                enumMap.put(MerchantVariant.LAVENDER,
                        new ResourceLocation(MODID, "textures/entity/hobbit/hobbit16.png"));
            });

    public HobbitRender(EntityRendererProvider.Context context) {
        super(context, new HobbitModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(HobbitEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MerchantVariant.DEFAULT));
    }

    @Override
    public RenderType getRenderType(HobbitEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4F, 0.4F, 0.4F);
        } else {
            stack.scale(0.7F, 0.7F, 0.7F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}