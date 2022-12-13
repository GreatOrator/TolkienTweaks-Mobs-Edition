package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.WatcherEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Watcher - GreatOrator */
@SuppressWarnings({"removal" })
public class WatcherModel extends AnimatedGeoModel<WatcherEntity> {

    @Override
    public ResourceLocation getModelLocation(WatcherEntity object) {
        return new ResourceLocation(MODID, "geo/boss/tmwatcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WatcherEntity object) {
        return new ResourceLocation(MODID, "textures/entity/tmwatcher.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WatcherEntity animatable) {
        return new ResourceLocation(MODID, "animations/boss/tmwatcher.animation.json");
    }
}