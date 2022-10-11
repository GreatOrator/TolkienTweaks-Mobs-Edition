package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.layers.DwarfHeldItemLayer;
import com.greatorator.tolkienmobs.entity.monster.DuergarEntity;
import com.greatorator.tolkienmobs.entity.monster.model.DuergarModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DuergarRender extends MobRenderer<DuergarEntity, DuergarModel<DuergarEntity>> {
    public DuergarRender(EntityRendererManager entityIn, DuergarModel<DuergarEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new DwarfHeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(DuergarEntity entity) {
        return entity.getDuergarTypeName();
    }
}