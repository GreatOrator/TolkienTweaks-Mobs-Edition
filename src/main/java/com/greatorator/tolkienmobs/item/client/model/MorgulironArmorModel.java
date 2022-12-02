package com.greatorator.tolkienmobs.item.client.model;

import com.greatorator.tolkienmobs.item.armor.MorgulironArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 *CustomArmor - GreatOrator
 */
public class MorgulironArmorModel extends AnimatedGeoModel<MorgulironArmorItem> {
    @Override
    public ResourceLocation getModelLocation(MorgulironArmorItem object) {
        return new ResourceLocation(MODID, "geo/item/base_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MorgulironArmorItem object) {
        return new ResourceLocation(MODID, "textures/armor/morguliron_armor_model.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MorgulironArmorItem animatable) {
        return new ResourceLocation(MODID, "animations/armor_animation.json");
    }
}