package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMMimicChest;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMMimicChest;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTTMMimicChest extends MobRenderer<EntityTTMMimicChest, ModelTTMMimicChest<EntityTTMMimicChest>> {
    public RenderTTMMimicChest(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMMimicChest<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMMimicChest entity) {
        return entity.getMimicChestTypeName();
    }

//    @Override
//    public void doRender(EntityTMMimicChest mimic, double x, double y, double z, float pitch, float yaw) {
//        if(mimic.getRevengeTarget() == null && !mimic.isAngry()) {
//            ModelMimicChest.renderChest = true;
//        }
//        else{
//            ModelMimicChest.renderChest = false;
//        }
//        super.doRender(mimic, x, y, z, pitch, yaw);
//    }

    protected void scale(EntityTTMMimicChest entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}