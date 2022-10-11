package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.DeepClawEntity;
import com.greatorator.tolkienmobs.entity.monster.model.DeepClawModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DeepClawRender extends MobRenderer<DeepClawEntity, DeepClawModel<DeepClawEntity>> {
    public DeepClawRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DeepClawModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(DeepClawEntity entity) {
        return entity.getDeepclawTypeName();
    }
}