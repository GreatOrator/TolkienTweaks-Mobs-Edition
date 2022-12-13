package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.MorgulironGolemEntity;
import com.greatorator.tolkienmobs.entity.boss.model.MorgulironGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MorgulironGolemRender extends GeoEntityRenderer<MorgulironGolemEntity> {
    public MorgulironGolemRender(EntityRendererProvider.Context context) {
        super(context, new MorgulironGolemModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public ResourceLocation getTextureLocation(MorgulironGolemEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_morgul.png");
    }

    public void render(MorgulironGolemEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }
}
