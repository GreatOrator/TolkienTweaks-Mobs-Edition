package com.greatorator.tolkienmobs.entity.monster.render;

import com.greatorator.tolkienmobs.entity.monster.MinotaurEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MinotaurModel;
import com.greatorator.tolkienmobs.entity.monster.render.layer.WeaponLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MinotaurRender extends WeaponLayer<MinotaurEntity> {
    public MinotaurRender(EntityRendererProvider.Context context) {
        super(context, new MinotaurModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(MinotaurEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/minotaur.png");
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "rightArm";
    }

    @Override
    public RenderType getRenderType(MinotaurEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}