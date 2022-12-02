package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Human - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.HumanEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.DwarfRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class HumanModel extends AnimatedGeoModel<HumanEntity> {
    @Override
    public ResourceLocation getModelLocation(HumanEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/human.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HumanEntity object) {
        return DwarfRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HumanEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/human.animation.json");
    }
}