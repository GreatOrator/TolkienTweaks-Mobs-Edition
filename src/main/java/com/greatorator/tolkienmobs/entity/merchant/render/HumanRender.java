package com.greatorator.tolkienmobs.entity.merchant.render;


import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.merchant.HumanEntity;
import com.greatorator.tolkienmobs.entity.merchant.model.HumanModel;
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

public class HumanRender extends MobRenderer<HumanEntity, HumanModel<HumanEntity>> {
    public static final Map<EntityVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(EntityVariant.class), (enumMap) -> {
                enumMap.put(EntityVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/human/human1.png"));
                enumMap.put(EntityVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/human/human1.png"));
                enumMap.put(EntityVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/human/human2.png"));
                enumMap.put(EntityVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/human/human3.png"));
                enumMap.put(EntityVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/human/human4.png"));
                enumMap.put(EntityVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/human/human5.png"));
                enumMap.put(EntityVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/human/human6.png"));
                enumMap.put(EntityVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/human/human7.png"));
                enumMap.put(EntityVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/human/human8.png"));
                enumMap.put(EntityVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/human/human9.png"));
                enumMap.put(EntityVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/human/human10.png"));
                enumMap.put(EntityVariant.AQUA,
                        new ResourceLocation(MODID, "textures/entity/human/human11.png"));
                enumMap.put(EntityVariant.BEIGE,
                        new ResourceLocation(MODID, "textures/entity/human/human12.png"));
                enumMap.put(EntityVariant.BROWN,
                        new ResourceLocation(MODID, "textures/entity/human/human13.png"));
                enumMap.put(EntityVariant.CORAL,
                        new ResourceLocation(MODID, "textures/entity/human/human14.png"));
                enumMap.put(EntityVariant.CYAN,
                        new ResourceLocation(MODID, "textures/entity/human/human15.png"));
                enumMap.put(EntityVariant.LAVENDER,
                        new ResourceLocation(MODID, "textures/entity/human/human16.png"));
            });

    public HumanRender(EntityRendererProvider.Context context) {
        super(context, new HumanModel<>(context.bakeLayer(ModelLayers.HUSK)), 0.25F);
        model.setAllVisible(true);
    }

    public void render(HumanEntity entity, float p_115977_, float p_115978_, PoseStack stack, MultiBufferSource bufferSource, int p_115981_) {
        super.render(entity, p_115977_, p_115978_, stack, bufferSource, p_115981_);
    }

    @Override
    public ResourceLocation getTextureLocation(HumanEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }}