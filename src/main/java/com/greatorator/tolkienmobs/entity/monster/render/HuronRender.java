package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.HuronEntity;
import com.greatorator.tolkienmobs.entity.monster.model.HuronModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HuronRender extends MobRenderer<HuronEntity, HuronModel<HuronEntity>> {
    public HuronRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HuronModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(HuronEntity entity) {
        return entity.getHuronTypeName();
    }
}