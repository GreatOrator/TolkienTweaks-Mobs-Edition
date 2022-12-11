package com.greatorator.tolkienmobs.entity.ambient.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.ambient.SquirrelEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.SquirrelModel;
import com.greatorator.tolkienmobs.entity.ambient.variant.AmbientVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class SquirrelRender extends GeoEntityRenderer<SquirrelEntity> {
    public static final Map<AmbientVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(AmbientVariant.class), (enumMap) -> {
                enumMap.put(AmbientVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/sosquirrel/sosquirrel1.png"));
                enumMap.put(AmbientVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/sosquirrel/sosquirrel1.png"));
                enumMap.put(AmbientVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/sosquirrel/sosquirrel2.png"));
                enumMap.put(AmbientVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/sosquirrel/sosquirrel3.png"));
                enumMap.put(AmbientVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/sosquirrel/sosquirrel4.png"));
                enumMap.put(AmbientVariant.MURDER,
                        new ResourceLocation(MODID, "textures/entity/sosquirrel/killer_squirrel.png"));
            });

    public SquirrelRender(EntityRendererProvider.Context ctx) {
        super(ctx, new SquirrelModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SquirrelEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderType getRenderType(SquirrelEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(0.2F, 0.2F, 0.2F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}