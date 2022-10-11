package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.GhostLayer;
import com.greatorator.tolkienmobs.entity.monster.OathbreakerEntity;
import com.greatorator.tolkienmobs.entity.monster.model.BarrowWightModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class OathbreakerRender extends MobRenderer<OathbreakerEntity, BarrowWightModel<OathbreakerEntity>> {
    public OathbreakerRender(EntityRendererManager entityIn, BarrowWightModel<OathbreakerEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new GhostLayer(this));
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(OathbreakerEntity entity) {
        return entity.getOathbreakerTypeName();
    }
}