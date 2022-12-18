package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.entity.item.SimpleTrapEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class SimpleTrapModel<T extends SimpleTrapEntity> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getAnimationFileLocation(T entity) {
        return new ResourceLocation(MODID, "animations/trap.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(T entity) {
        return new ResourceLocation(MODID, "geo/item/trap.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return new ResourceLocation(MODID, "textures/entity/web_trap.png");
    }
}
