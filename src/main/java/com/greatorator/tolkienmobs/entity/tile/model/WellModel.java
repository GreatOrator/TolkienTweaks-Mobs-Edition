package com.greatorator.tolkienmobs.entity.tile.model;

import com.greatorator.tolkienmobs.entity.tile.WellTile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class WellModel extends AnimatedGeoModel<WellTile> {
    @Override
    public ResourceLocation getModelLocation(WellTile object) {
        return new ResourceLocation(MODID, "geo/block/well.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WellTile object) {
        return new ResourceLocation(MODID, "textures/block/custom/well.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WellTile animatable) {
        return new ResourceLocation(MODID, "animations/well.animation.json");
    }
}