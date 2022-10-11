package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.BrigandEntity;
import com.greatorator.tolkienmobs.entity.monster.model.BrigandModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class BrigandRender extends MobRenderer<BrigandEntity, BrigandModel<BrigandEntity>> {
    public BrigandRender(EntityRendererManager entityIn, BrigandModel<BrigandEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BrigandEntity entity) {
        return entity.getBrigandTypeName();
    }
}