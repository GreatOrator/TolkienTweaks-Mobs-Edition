package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.WitchKingEntity;
import com.greatorator.tolkienmobs.entity.boss.model.WitchKingModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class WitchKingRender extends MobRenderer<WitchKingEntity, WitchKingModel<WitchKingEntity>> {
    private static final ResourceLocation mobTexture = new ResourceLocation(MODID + ":textures/entity/witchking.png");

    public WitchKingRender(EntityRendererManager entityIn, WitchKingModel<WitchKingEntity> modelSize, float shadowIn) {
        super(entityIn, modelSize, shadowIn);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(WitchKingEntity p_110775_1_) {
        return mobTexture;
    }
}