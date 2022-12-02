package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Dwarf - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.ElvesEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.DwarfRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ElvesModel extends AnimatedGeoModel<ElvesEntity> {
    @Override
    public ResourceLocation getModelLocation(ElvesEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/elf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ElvesEntity object) {
        return DwarfRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ElvesEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/elf.animation.json");
    }
}