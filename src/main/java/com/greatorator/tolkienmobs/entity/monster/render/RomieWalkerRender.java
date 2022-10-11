package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.RomieWalkerEntity;
import com.greatorator.tolkienmobs.entity.monster.model.BrigandModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RomieWalkerRender extends MobRenderer<RomieWalkerEntity, BrigandModel<RomieWalkerEntity>> {
    public RomieWalkerRender(EntityRendererManager entityIn, BrigandModel<RomieWalkerEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RomieWalkerEntity entity) {
        return entity.getRomieWalkerTypeName();
    }
}