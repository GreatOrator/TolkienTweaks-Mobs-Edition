package com.greatorator.tolkienmobs.entity.merchant.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.DesertDwellerEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.DesertDwellerModel;
import com.greatorator.tolkienmobs.entity.merchant.variant.MerchantVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class DesertDwellerRender extends GeoEntityRenderer<DesertDwellerEntity> {
    public static final Map<MerchantVariant, ResourceLocation> LOCATION_BY_VARIANT =
        Util.make(Maps.newEnumMap(MerchantVariant.class), (enumMap) -> {
            enumMap.put(MerchantVariant.DEFAULT,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant1.png"));
            enumMap.put(MerchantVariant.RED,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant1.png"));
            enumMap.put(MerchantVariant.ORANGE,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant2.png"));
            enumMap.put(MerchantVariant.YELLOW,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant3.png"));
            enumMap.put(MerchantVariant.GREEN,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant4.png"));
            enumMap.put(MerchantVariant.BLUE,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant5.png"));
            enumMap.put(MerchantVariant.INDIGO,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant6.png"));
            enumMap.put(MerchantVariant.VIOLET,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant7.png"));
            enumMap.put(MerchantVariant.MAGENTA,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant8.png"));
            enumMap.put(MerchantVariant.PINK,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant9.png"));
            enumMap.put(MerchantVariant.GRAY,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant10.png"));
        });

    public DesertDwellerRender(EntityRendererProvider.Context context) {
        super(context, new DesertDwellerModel());
        this.shadowRadius = 0.3f;
    }

    public void render(DesertDwellerEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(DesertDwellerEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}