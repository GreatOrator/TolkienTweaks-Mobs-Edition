package com.greatorator.tolkienmobs.entity.merchant.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.DesertDwellerEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.DesertDwellerModel;
import com.greatorator.tolkienmobs.entity.merchant.variant.EntityVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class DesertDwellerRender extends MobRenderer<DesertDwellerEntity, DesertDwellerModel<DesertDwellerEntity>> {
    public static final Map<EntityVariant, ResourceLocation> LOCATION_BY_VARIANT =
        Util.make(Maps.newEnumMap(EntityVariant.class), (enumMap) -> {
            enumMap.put(EntityVariant.DEFAULT,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant1.png"));
            enumMap.put(EntityVariant.RED,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant1.png"));
            enumMap.put(EntityVariant.ORANGE,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant2.png"));
            enumMap.put(EntityVariant.YELLOW,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant3.png"));
            enumMap.put(EntityVariant.GREEN,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant4.png"));
            enumMap.put(EntityVariant.BLUE,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant5.png"));
            enumMap.put(EntityVariant.INDIGO,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant6.png"));
            enumMap.put(EntityVariant.VIOLET,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant7.png"));
            enumMap.put(EntityVariant.MAGENTA,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant8.png"));
            enumMap.put(EntityVariant.PINK,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant9.png"));
            enumMap.put(EntityVariant.GRAY,
                    new ResourceLocation(MODID, "textures/entity/desertdweller/haradmerchant10.png"));

        });

    public DesertDwellerRender(EntityRendererProvider.Context context) {
        super(context, new DesertDwellerModel<>(context.bakeLayer(ModelLayers.HUSK)), 0.25F);
        model.setAllVisible(true);
    }

    public void render(DesertDwellerEntity entity, float p_115977_, float p_115978_, PoseStack stack, MultiBufferSource bufferSource, int p_115981_) {
        super.render(entity, p_115977_, p_115978_, stack, bufferSource, p_115981_);
    }

    @Override
    public ResourceLocation getTextureLocation(DesertDwellerEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}