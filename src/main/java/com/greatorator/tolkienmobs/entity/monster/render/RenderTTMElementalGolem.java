package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMElementalGolem;
import com.greatorator.tolkienmobs.entity.monster.model.ModelTTMElementalGolem;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RenderTTMElementalGolem extends MobRenderer<EntityTTMElementalGolem, ModelTTMElementalGolem<EntityTTMElementalGolem>> {
    public RenderTTMElementalGolem(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTTMElementalGolem<>(), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMElementalGolem entity) {
        return entity.getElementalGolemTypeName();
    }

    @Override
    protected void setupRotations(EntityTTMElementalGolem p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
        if (!((double)p_225621_1_.animationSpeed < 0.01D)) {
            float lvt_6_1_ = 13.0F;
            float lvt_7_1_ = p_225621_1_.animationPosition - p_225621_1_.animationSpeed * (1.0F - p_225621_5_) + 6.0F;
            float lvt_8_1_ = (Math.abs(lvt_7_1_ % 13.0F - 6.5F) - 3.25F) / 3.25F;
            p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(6.5F * lvt_8_1_));
        }
    }
}
