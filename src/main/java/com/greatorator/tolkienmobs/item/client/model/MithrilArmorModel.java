package com.greatorator.tolkienmobs.item.client.model;

import com.greatorator.tolkienmobs.item.armor.MithrilArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 *CustomArmor - GreatOrator
 */
public class MithrilArmorModel extends AnimatedGeoModel<MithrilArmorItem> {
    @Override
    public ResourceLocation getModelLocation(MithrilArmorItem object) {
        return new ResourceLocation(MODID, "geo/item/base_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MithrilArmorItem object) {
        return new ResourceLocation(MODID, "textures/armor/mithril_armor_model.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MithrilArmorItem animatable) {
        return new ResourceLocation(MODID, "animations/armor_animation.json");
    }
}