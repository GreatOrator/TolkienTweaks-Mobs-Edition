package com.greatorator.tolkienmobs.entity.tile.render;

import com.greatorator.tolkienmobs.entity.tile.LockableChestTile;
import com.greatorator.tolkienmobs.entity.tile.model.LockableChestModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class LockableChestTileRender extends GeoBlockRenderer<LockableChestTile> {
    public LockableChestTileRender(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new LockableChestModel());
    }

    @Override
    public RenderType getRenderType(LockableChestTile animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}