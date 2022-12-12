package com.greatorator.tolkienmobs.entity.merchant.render;


import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.HumanEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.HumanModel;
import com.greatorator.tolkienmobs.entity.merchant.variant.MerchantVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class HumanRender extends GeoEntityRenderer<HumanEntity> {
    public static final Map<MerchantVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MerchantVariant.class), (enumMap) -> {
                enumMap.put(MerchantVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/human/human1.png"));
                enumMap.put(MerchantVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/human/human1.png"));
                enumMap.put(MerchantVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/human/human2.png"));
                enumMap.put(MerchantVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/human/human3.png"));
                enumMap.put(MerchantVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/human/human4.png"));
                enumMap.put(MerchantVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/human/human5.png"));
                enumMap.put(MerchantVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/human/human6.png"));
                enumMap.put(MerchantVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/human/human7.png"));
                enumMap.put(MerchantVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/human/human8.png"));
                enumMap.put(MerchantVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/human/human9.png"));
                enumMap.put(MerchantVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/human/human10.png"));
                enumMap.put(MerchantVariant.AQUA,
                        new ResourceLocation(MODID, "textures/entity/human/human11.png"));
                enumMap.put(MerchantVariant.BEIGE,
                        new ResourceLocation(MODID, "textures/entity/human/human12.png"));
                enumMap.put(MerchantVariant.BROWN,
                        new ResourceLocation(MODID, "textures/entity/human/human13.png"));
                enumMap.put(MerchantVariant.CORAL,
                        new ResourceLocation(MODID, "textures/entity/human/human14.png"));
                enumMap.put(MerchantVariant.CYAN,
                        new ResourceLocation(MODID, "textures/entity/human/human15.png"));
                enumMap.put(MerchantVariant.LAVENDER,
                        new ResourceLocation(MODID, "textures/entity/human/human16.png"));
            });

    public HumanRender(EntityRendererProvider.Context context) {
        super(context, new HumanModel());
        this.shadowRadius = 0.3f;
    }

    public void render(HumanEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(HumanEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MerchantVariant.DEFAULT));
    }
}