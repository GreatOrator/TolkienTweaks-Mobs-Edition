package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TolkienBoatRender extends BoatRenderer {
    private static final ResourceLocation MALLORN_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/mallorn.png");
    private static final ResourceLocation MIRKWOOD_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/mirkwood.png");
    private static final ResourceLocation CULUMALDA_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/culumalda.png");
    private static final ResourceLocation LEBETHRON_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/boat/lebethron.png");

    public TolkienBoatRender(EntityRendererManager rendererManager) {
        super(rendererManager);
        this.shadowRadius = 0.8F;
    }

    public ResourceLocation getTextureLocation(TolkienBoatEntity boatEntity) {
        switch(boatEntity.getWoodType()) {
            case "mallorn":
            default:
                return MALLORN_LOCATION;
            case "mirkwood":
                return MIRKWOOD_LOCATION;
            case "culumalda":
                return CULUMALDA_LOCATION;
            case "lebethron":
                return LEBETHRON_LOCATION;
        }
    }

}
