package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Dwarf - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.DwarfEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.DwarfRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class DwarfModel extends AnimatedGeoModel<DwarfEntity> {
    @Override
    public ResourceLocation getModelLocation(DwarfEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity object) {
        return DwarfRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DwarfEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/dwarf.animation.json");
    }
}