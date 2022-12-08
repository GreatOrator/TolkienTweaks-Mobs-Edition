package com.greatorator.tolkienmobs.entity.ambient.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class SwarmLayer {
    public static final ModelLayerLocation ENTITY_TTM_SWARM = register("entityttmswarm");

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(MODID, p_171301_), p_171302_);
    }
}
