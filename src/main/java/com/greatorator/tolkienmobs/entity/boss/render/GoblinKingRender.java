package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.entity.boss.model.GoblinKingModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GoblinKingRender extends GeoEntityRenderer<GoblinKingEntity> {
    public GoblinKingRender(EntityRendererProvider.Context context) {
        super(context, new GoblinKingModel());
        this.shadowRadius = 1.0f;
        }

    @Override
    public ResourceLocation getTextureLocation(GoblinKingEntity entity) {
    return new ResourceLocation(MODID, "textures/entity/goblin/goblinking.png");
        }

    public void render(GoblinKingEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
        }
}