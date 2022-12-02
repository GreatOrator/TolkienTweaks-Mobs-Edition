package com.greatorator.tolkienmobs.item.client.model;

import com.greatorator.tolkienmobs.item.basic.CatalystItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 *CustomArmor - GreatOrator
 */
public class CatalystModel extends AnimatedGeoModel<CatalystItem> {
    @Override
    public ResourceLocation getModelLocation(CatalystItem object) {
        return new ResourceLocation(MODID, "geo/item/arda_staff.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CatalystItem object) {
        return new ResourceLocation(MODID, "textures/item/tools/obj/arda_staff.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CatalystItem animatable) {
        return new ResourceLocation(MODID, "animations/arda_staff.animation.json");
    }
}