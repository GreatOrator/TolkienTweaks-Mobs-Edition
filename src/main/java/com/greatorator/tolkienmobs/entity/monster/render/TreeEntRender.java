package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.TreeEntEntity;
import com.greatorator.tolkienmobs.entity.monster.model.TreeEntModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TreeEntRender extends MobRenderer<TreeEntEntity, TreeEntModel<TreeEntEntity>> {
    public TreeEntRender(EntityRendererManager entityIn, TreeEntModel<TreeEntEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
    }

    @Override
    public ResourceLocation getTextureLocation(TreeEntEntity entity) {
        return entity.getTreeEntTypeName();
    }
}