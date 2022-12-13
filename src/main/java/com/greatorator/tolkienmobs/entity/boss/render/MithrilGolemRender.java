package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.MithrilGolemEntity;
import com.greatorator.tolkienmobs.entity.boss.model.MithrilGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MithrilGolemRender extends GeoEntityRenderer<MithrilGolemEntity> {
    public MithrilGolemRender(EntityRendererProvider.Context context) {
        super(context, new MithrilGolemModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public ResourceLocation getTextureLocation(MithrilGolemEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_mithril.png");
    }

    public void render(MithrilGolemEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }
}
