package com.greatorator.tolkienmobs.entity.boss.render;

import com.greatorator.tolkienmobs.entity.boss.WitchKingEntity;
import com.greatorator.tolkienmobs.entity.boss.model.WitchKingModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class WitchKingRender extends BaseNazgulRender<WitchKingEntity> {
    public WitchKingRender(EntityRendererProvider.Context context) {
        super(context, new WitchKingModel());
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "rightArm";
    }

    public void render(WitchKingEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(WitchKingEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/witchking.png");
    }

    @Override
    public RenderType getRenderType(WitchKingEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1.3F, 1.3F, 1.3F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}