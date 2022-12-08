package com.greatorator.tolkienmobs.entity.passive.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.passive.AurochEntity;
import com.greatorator.tolkienmobs.entity.passive.model.AurochModel;
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

public class AurochRender extends GeoEntityRenderer<AurochEntity> {
    public static final Map<PassiveVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PassiveVariant.class), (enumMap) -> {
                enumMap.put(PassiveVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/auroch/auroch1.png"));
                enumMap.put(PassiveVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/auroch/auroch1.png"));
                enumMap.put(PassiveVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/auroch/auroch2.png"));
                enumMap.put(PassiveVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/auroch/auroch3.png"));
                enumMap.put(PassiveVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/auroch/auroch4.png"));
            });

    public AurochRender(EntityRendererProvider.Context context) {
        super(context, new AurochModel());
        this.shadowRadius = 1.0F;
    }

    @Override
    public void renderEarly(AurochEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
    }

    public void render(AurochEntity entity, float entityYaw, float partialTicks, @Nonnull PoseStack stack, @Nonnull MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(AurochEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderType getRenderType(AurochEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }
}