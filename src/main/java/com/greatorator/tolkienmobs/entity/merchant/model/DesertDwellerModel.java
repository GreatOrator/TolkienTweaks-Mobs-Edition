package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Human - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.DesertDwellerEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.DwarfRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class DesertDwellerModel extends AnimatedGeoModel<DesertDwellerEntity> {
    @Override
    public ResourceLocation getModelLocation(DesertDwellerEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/desertdweller.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DesertDwellerEntity object) {
        return DwarfRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DesertDwellerEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/desertdweller.animation.json");
    }
}