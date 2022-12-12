package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.BarrowWightEntity;
import com.greatorator.tolkienmobs.entity.monster.model.BarrowWightModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BarrowWightRender extends GeoEntityRenderer<BarrowWightEntity> {
    protected BarrowWightEntity tolkienEntity;
    protected RenderType renderType;

    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/barrowwight/barrowwight1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/barrowwight/barrowwight1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/barrowwight/barrowwight2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/barrowwight/barrowwight3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/barrowwight/barrowwight4.png"));
            });

    public BarrowWightRender(EntityRendererProvider.Context context) {
        super(context, new BarrowWightModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public void renderEarly(BarrowWightEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
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

    protected void moveAndRotateMatrixToMatchBone(PoseStack stack, GeoBone bone) {
        stack.translate(((float) 0.38), 0.95, -0.2);
        float xRot = bone.getRotationX() * (180 / (float) Math.PI);
        float yRot = bone.getRotationY() * (180 / (float) Math.PI);
        float zRot = bone.getRotationZ() * (180 / (float) Math.PI);
        stack.mulPose(Vector3f.XP.rotationDegrees(xRot - 90));
        stack.mulPose(Vector3f.YP.rotationDegrees(yRot - 180));
        stack.mulPose(Vector3f.ZP.rotationDegrees(zRot));
    }

    @Override
    public RenderType getRenderType(BarrowWightEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        this.renderType = RenderType.entityTranslucent(textureLocation);
        return renderType;
    }

    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    protected String getArmPartName() {
        return "rightArm";
    }

    @Override
    public ResourceLocation getTextureLocation(BarrowWightEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}