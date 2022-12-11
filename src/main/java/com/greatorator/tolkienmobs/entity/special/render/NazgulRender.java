package com.greatorator.tolkienmobs.entity.special.render;

import com.greatorator.tolkienmobs.entity.boss.render.BaseNazgulRender;
import com.greatorator.tolkienmobs.entity.special.NazgulEntity;
import com.greatorator.tolkienmobs.entity.special.model.NazgulModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class NazgulRender extends BaseNazgulRender<NazgulEntity> {
    public NazgulRender(EntityRendererProvider.Context context) {
        super(context, new NazgulModel());
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "rightArm";
    }

    public void render(NazgulEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferSource, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(NazgulEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/nazgul.png");
    }

    @Override
    public RenderType getRenderType(NazgulEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1.1F, 1.1F, 1.1F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}