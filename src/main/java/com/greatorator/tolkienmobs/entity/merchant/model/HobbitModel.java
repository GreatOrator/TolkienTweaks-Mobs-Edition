package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Dwarf - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.HobbitEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.DwarfRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class HobbitModel extends AnimatedGeoModel<HobbitEntity> {
    @Override
    public ResourceLocation getModelLocation(HobbitEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/hobbit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HobbitEntity object) {
        return DwarfRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HobbitEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/hobbit.animation.json");
    }
}