package com.greatorator.tolkienmobs.entity.merchant.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.ElvesEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.ElvesModel;
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

public class ElvesRender extends GeoEntityRenderer<ElvesEntity> {
    public static final Map<MerchantVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MerchantVariant.class), (enumMap) -> {
                enumMap.put(MerchantVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/elves/elves1.png"));
                enumMap.put(MerchantVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/elves/elves1.png"));
                enumMap.put(MerchantVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/elves/elves2.png"));
                enumMap.put(MerchantVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/elves/elves3.png"));
                enumMap.put(MerchantVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/elves/elves4.png"));
                enumMap.put(MerchantVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/elves/elves5.png"));
                enumMap.put(MerchantVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/elves/elves6.png"));
                enumMap.put(MerchantVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/elves/elves7.png"));
                enumMap.put(MerchantVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/elves/elves8.png"));
                enumMap.put(MerchantVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/elves/elves9.png"));
                enumMap.put(MerchantVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/elves/elves10.png"));
                enumMap.put(MerchantVariant.AQUA,
                        new ResourceLocation(MODID, "textures/entity/elves/elves11.png"));
                enumMap.put(MerchantVariant.BEIGE,
                        new ResourceLocation(MODID, "textures/entity/elves/elves12.png"));
                enumMap.put(MerchantVariant.BROWN,
                        new ResourceLocation(MODID, "textures/entity/elves/elves13.png"));
                enumMap.put(MerchantVariant.CORAL,
                        new ResourceLocation(MODID, "textures/entity/elves/elves14.png"));
                enumMap.put(MerchantVariant.CYAN,
                        new ResourceLocation(MODID, "textures/entity/elves/elves15.png"));
                enumMap.put(MerchantVariant.LAVENDER,
                        new ResourceLocation(MODID, "textures/entity/elves/elves16.png"));
            });

    public ElvesRender(EntityRendererProvider.Context context) {
        super(context, new ElvesModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ElvesEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderType getRenderType(ElvesEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4F, 0.4F, 0.4F);
        } else {
            stack.scale(1.1F, 1.1F, 1.1F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}