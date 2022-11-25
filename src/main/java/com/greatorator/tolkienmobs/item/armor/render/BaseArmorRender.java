package com.greatorator.tolkienmobs.item.armor.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BaseArmorRender extends EntityRenderers {
    public static class BaseArmorMain<T extends LivingEntity> extends HumanoidModel<T> {
        public static final ModelLayerLocation BODY_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "armor_model_body"), "body");
        public LivingEntity entity;

        public final ModelPart head;
        public final ModelPart body;
        public final ModelPart rightArm;
        public final ModelPart leftArm;
        public final ModelPart rightFoot;
        public final ModelPart leftFoot;
        public final ModelPart rightLeg;
        public final ModelPart leftLeg;

        public BaseArmorMain(ModelPart root) {
            super(new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().font).bakeLayer(ModelLayers.PLAYER_INNER_ARMOR));
            this.head = root.getChild("hat");
            this.body = root.getChild("body");
            this.rightArm = root.getChild("right_arm");
            this.leftArm = root.getChild("left_arm");
            this.rightFoot = root.getChild("right_leg");
            this.leftFoot = root.getChild("left_leg");
            this.rightLeg = root.getChild("right_leg");
            this.leftLeg = root.getChild("left_leg");
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            HumanoidModel livingModel = (HumanoidModel<LivingEntity>) ((LivingEntityRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity)).getModel();

            this.attackTime = livingModel.attackTime;
            this.riding = livingModel.riding;
            this.young = livingModel.young;
            this.leftArmPose = livingModel.leftArmPose;
            this.rightArmPose = livingModel.rightArmPose;
            this.crouching = livingModel.crouching;
            this.head.copyFrom(livingModel.head);
            this.body.copyFrom(livingModel.body);
            this.rightArm.copyFrom(livingModel.rightArm);
            this.leftArm.copyFrom(livingModel.leftArm);
            this.rightLeg.copyFrom(livingModel.rightLeg);
            this.leftLeg.copyFrom(livingModel.leftLeg);

            poseStack.pushPose();
            if (this.young) {
                poseStack.scale(0.5f, 0.5f, 0.5f);
                poseStack.translate(0, 1.5f, 0);
            }

            head.render(poseStack, buffer, packedLight, packedOverlay);
            rightArm.render(poseStack, buffer, packedLight, packedOverlay);
            leftArm.render(poseStack, buffer, packedLight, packedOverlay);
            rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            leftLeg.render(poseStack, buffer, packedLight, packedOverlay);

            poseStack.popPose();
        }
    }

    public static class BaseArmorLegs<T extends LivingEntity> extends HumanoidModel<T> {
        public static final ModelLayerLocation LEG_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "armor_model_legs"), "legs");

        public LivingEntity entity;

        public final ModelPart rightLeg;
        public final ModelPart leftLeg;

        public BaseArmorLegs(ModelPart root) {
            super(new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().font).bakeLayer(ModelLayers.PLAYER_INNER_ARMOR));
            this.rightLeg = root.getChild("right_leg");
            this.leftLeg = root.getChild("left_leg");
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            HumanoidModel livingModel = (HumanoidModel<LivingEntity>) ((LivingEntityRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity)).getModel();

            this.attackTime = livingModel.attackTime;
            this.riding = livingModel.riding;
            this.young = livingModel.young;
            this.leftArmPose = livingModel.leftArmPose;
            this.rightArmPose = livingModel.rightArmPose;
            this.crouching = livingModel.crouching;
            this.hat.copyFrom(livingModel.head);
            this.body.copyFrom(livingModel.body);
            this.rightArm.copyFrom(livingModel.rightArm);
            this.leftArm.copyFrom(livingModel.leftArm);
            this.rightLeg.copyFrom(livingModel.rightLeg);
            this.leftLeg.copyFrom(livingModel.leftLeg);

            rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
        }
    }
}