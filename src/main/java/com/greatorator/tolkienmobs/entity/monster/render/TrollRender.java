package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.TrollEntity;
import com.greatorator.tolkienmobs.entity.monster.model.TrollModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class TrollRender extends MobRenderer<TrollEntity, TrollModel<TrollEntity>> {
    public TrollRender(EntityRendererManager entityIn, TrollModel<TrollEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TrollEntity entity) {
        return entity.getTrollTypeName();
    }
}