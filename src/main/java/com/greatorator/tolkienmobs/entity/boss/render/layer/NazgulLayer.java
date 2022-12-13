package com.greatorator.tolkienmobs.entity.boss.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public abstract class NazgulLayer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
    protected T tolkienEntity;
    protected RenderType renderType;
    protected abstract ItemStack getHeldItemStack();
    protected abstract String getArmPartName();

    public NazgulLayer(EntityRendererProvider.Context context, AnimatedGeoModel<T> modelProvider) {
        super(context, modelProvider);
        this.shadowRadius = 0.8f;
    }

    @Override
    public void renderEarly(T animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        this.tolkienEntity = animatable;
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ItemStack heldItemStack = this.getHeldItemStack();
        String armPart = this.getArmPartName();
        String boneName = bone.getName();

        if (heldItemStack != null && !heldItemStack.isEmpty() && boneName.equals(armPart)) {
            poseStack.pushPose();
            this.moveAndRotateMatrixToMatchBone(poseStack, bone);
            // poseStack.translate((bone.getPositionX() * 0.1f) - (0.2f * 0.1f), (bone.getPositionY() * 0.1f) + (0.8f * 0.1f), (bone.getPositionZ() * 0.1f) + (3f * 0.1f) - 0.3f);
            poseStack.scale(1F, 1F, 1F);
            Minecraft.getInstance().gameRenderer.itemInHandRenderer.renderItem(this.tolkienEntity, heldItemStack,
                    ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false, poseStack, this.rtb,
                    packedLight);

            poseStack.popPose();
            vertexConsumer = this.rtb.getBuffer(this.renderType);
        }

        super.renderRecursively(bone, poseStack, vertexConsumer, LightTexture.FULL_BRIGHT, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public RenderType getRenderType(T animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        this.renderType = RenderType.entityTranslucent(textureLocation);
        return renderType;
    }

    protected void moveAndRotateMatrixToMatchBone(PoseStack stack, GeoBone bone) {
        stack.translate(bone.getPivotX() / 16, bone.getPivotY() / 24, bone.getPivotZ() - 1.7);
        float xRot = bone.getRotationX() * (180 / (float) Math.PI);
        float yRot = bone.getRotationY() * (180 / (float) Math.PI);
        float zRot = bone.getRotationZ() * (180 / (float) Math.PI);
        stack.mulPose(Vector3f.XP.rotationDegrees(xRot - 90));
        stack.mulPose(Vector3f.YP.rotationDegrees(yRot - 180));
        stack.mulPose(Vector3f.ZP.rotationDegrees(zRot));
    }
}