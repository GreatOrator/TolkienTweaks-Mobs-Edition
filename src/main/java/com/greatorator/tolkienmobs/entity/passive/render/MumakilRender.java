package com.greatorator.tolkienmobs.entity.passive.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.passive.MumakilEntity;
import com.greatorator.tolkienmobs.entity.passive.model.MumakilModel;
import com.greatorator.tolkienmobs.entity.passive.variant.PassiveVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MumakilRender extends GeoEntityRenderer<MumakilEntity> {
    public static final Map<PassiveVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PassiveVariant.class), (enumMap) -> {
                enumMap.put(PassiveVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/mumakil/mumakil1.png"));
                enumMap.put(PassiveVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/mumakil/mumakil1.png"));
                enumMap.put(PassiveVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/mumakil/mumakil2.png"));
                enumMap.put(PassiveVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/mumakil/mumakil3.png"));
                enumMap.put(PassiveVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/mumakil/mumakil4.png"));
            });

    public MumakilRender(EntityRendererProvider.Context context) {
        super(context, new MumakilModel());
        this.shadowRadius = 3.8F;
    }

    @Override
    public void renderEarly(MumakilEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
    }

    public void render(MumakilEntity entity, float entityYaw, float partialTicks, @Nonnull PoseStack stack, @Nonnull MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(MumakilEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(PassiveVariant.DEFAULT));
    }

    @Override
    public RenderType getRenderType(MumakilEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }
}