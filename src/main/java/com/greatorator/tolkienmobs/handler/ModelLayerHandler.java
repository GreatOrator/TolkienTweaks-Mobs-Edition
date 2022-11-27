package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ModelLayerHandler {

    public static ModelLayerLocation createBoatModelName(TolkienBoatEntity.Type type) {
        return createLocation(MODID,"boat/" + type.getName(), "boat");
    }

    public static ModelLayerLocation createLocation(String modId, String name, String part) {
        return new ModelLayerLocation(new ResourceLocation(modId, name), part);
    }
}
