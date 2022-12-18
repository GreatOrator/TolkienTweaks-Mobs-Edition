package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.HuronEntity;
import com.greatorator.tolkienmobs.entity.monster.model.HuronModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class HuronRender extends GeoEntityRenderer<HuronEntity> {
    public HuronRender(EntityRendererProvider.Context context) {
        super(context, new HuronModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(HuronEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/huron.png");
    }

    @Override
    public RenderType getRenderType(HuronEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}