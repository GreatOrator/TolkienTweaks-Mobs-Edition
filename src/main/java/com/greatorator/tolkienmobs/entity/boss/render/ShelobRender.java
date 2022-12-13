package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.ShelobEntity;
import com.greatorator.tolkienmobs.entity.boss.model.ShelobModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ShelobRender extends GeoEntityRenderer<ShelobEntity> {
    public ShelobRender(EntityRendererProvider.Context context) {
        super(context, new ShelobModel());
        this.shadowRadius = 2.1f;
    }

    public void render(ShelobEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        stack.scale(1.5F, 1.5F, 1.5F);
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(ShelobEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/tmshelob.png");
    }
}