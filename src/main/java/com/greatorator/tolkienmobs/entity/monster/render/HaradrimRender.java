package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.HaradrimEntity;
import com.greatorator.tolkienmobs.entity.monster.model.HaradrimModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class HaradrimRender extends MobRenderer<HaradrimEntity, HaradrimModel<HaradrimEntity>> {

    public HaradrimRender(EntityRendererManager entityIn, HaradrimModel<HaradrimEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(HaradrimEntity entity) {
        return entity.getHaradrimTypeName();
    }
}