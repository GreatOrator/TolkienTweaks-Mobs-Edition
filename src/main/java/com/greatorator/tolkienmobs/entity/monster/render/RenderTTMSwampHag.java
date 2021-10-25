package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMSwampHag;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WitchHeldItemLayer;
import net.minecraft.client.renderer.entity.model.WitchModel;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTTMSwampHag extends MobRenderer<EntityTTMSwampHag, WitchModel<EntityTTMSwampHag>> {

    public RenderTTMSwampHag(EntityRendererManager p_i46131_1_) {
        super(p_i46131_1_, new WitchModel(0.0F), 0.5F);
        this.addLayer(new WitchHeldItemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTTMSwampHag entity) {
        return entity.getSwampHagTypeName();
    }

    public void render(EntityTTMSwampHag p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        ((WitchModel)this.model).setHoldingItem(!p_225623_1_.getMainHandItem().isEmpty());
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    protected void scale(WitchEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        float lvt_4_1_ = 0.9375F;
        p_225620_2_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}