package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.MimicChestEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MimicChestModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
public class MimicChestRender extends GeoEntityRenderer<MimicChestEntity> {
    protected MimicChestEntity tolkienEntity;
    protected RenderType renderType;

    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
        Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
            enumMap.put(MonsterVariant.DEFAULT,
                    new ResourceLocation(MODID, "textures/entity/mimicchest/mimicchest1.png"));
            enumMap.put(MonsterVariant.RED,
                    new ResourceLocation(MODID, "textures/entity/mimicchest/mimicchest1.png"));
            enumMap.put(MonsterVariant.ORANGE,
                    new ResourceLocation(MODID, "textures/entity/mimicchest/mimicchest2.png"));
            enumMap.put(MonsterVariant.YELLOW,
                    new ResourceLocation(MODID, "textures/entity/mimicchest/mimicchest3.png"));
            enumMap.put(MonsterVariant.GREEN,
                    new ResourceLocation(MODID, "textures/entity/mimicchest/mimicchest4.png"));
            enumMap.put(MonsterVariant.BLUE,
                    new ResourceLocation(MODID, "textures/entity/mimicchest/mimicchest5.png"));
        });

    public MimicChestRender(EntityRendererProvider.Context context) {
        super(context, new MimicChestModel());
        this.shadowRadius = 1.0F;
    }

    @Override
    public void renderEarly(MimicChestEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        this.tolkienEntity = animatable;
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public RenderType getRenderType(MimicChestEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        this.renderType = RenderType.entityTranslucent(textureLocation);
        return renderType;
    }

    @Override
    public ResourceLocation getTextureLocation(MimicChestEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}