package com.greatorator.tolkienmobs.entity.projectiles.render;

import com.greatorator.tolkienmobs.entity.projectiles.DragonProjectileEntity;
import com.greatorator.tolkienmobs.entity.projectiles.model.DragonWeaponModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class DragonWeaponRender extends GeoProjectilesRenderer<DragonProjectileEntity>
{
    public static final ResourceLocation DRAGON_FIRE = new ResourceLocation(MODID, "item/ammo/ammo_fellbeast_fireball.png");

    public DragonWeaponRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DragonWeaponModel());
    }

    @Override
    public void renderEarly(DragonProjectileEntity animatable, PoseStack stackIn, float ticks,
                            MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        float scalingFactor = 25.0F;
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        stackIn.scale(0.125F*(animatable.tickCount*scalingFactor), 0.125F*(animatable.tickCount*scalingFactor), 0.125F*(animatable.tickCount*scalingFactor));

    }

    @Override
    public RenderType getRenderType(DragonProjectileEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        return RenderType.entityTranslucent(DRAGON_FIRE);
    }
}
