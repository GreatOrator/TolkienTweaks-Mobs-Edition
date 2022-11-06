package com.greatorator.tolkienmobs.item.armor.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BaseArmorRender {
    public static class BaseArmorMain<T extends LivingEntity> extends HumanoidModel<T> {
        private static final ResourceLocation mithril_Armor = new ResourceLocation(MODID, "textures/armor/mithril_armor_model.png");
        private static final ResourceLocation morguliron_Armor = new ResourceLocation(MODID, "textures/armor/morguliron_armor_model.png");

        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "armor_model"), "main");

        public LivingEntity entity;

        public final ModelPart Head;
        public final ModelPart Body;
        public final ModelPart RightArm;
        public final ModelPart LeftArm;
        public final ModelPart RightFoot;
        public final ModelPart LeftFoot;

        public BaseArmorMain(ModelPart root) {
            super(new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().font).bakeLayer(ModelLayers.PLAYER_INNER_ARMOR));
            this.Head = root.getChild("head");
            this.Body = root.getChild("body");
            this.RightArm = root.getChild("right_arm");
            this.LeftArm = root.getChild("left_arm");
            this.RightFoot = root.getChild("right_foot");
            this.LeftFoot = root.getChild("left_foot");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 86).addBox(-4.5F, -5.0F, -4.0F, 9.0F, 5.0F, 9.0F, new CubeDeformation(1.0F))
                    .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition bone6 = Head.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 100).addBox(-5.0F, -23.3F, -4.5F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.9F))
                    .texOffs(40, 112).addBox(-5.0F, -26.2F, 2.5F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.7F))
                    .texOffs(40, 97).addBox(-1.0F, -30.3F, -5.2F, 2.0F, 4.0F, 11.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

            PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(70, 101).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition bone3 = Body.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(70, 117).addBox(-5.0F, -25.4F, -3.1F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.2F))
                    .texOffs(0, 50).addBox(-5.0F, -16.5F, -2.5F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 24.0F, 0.0F));

            PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(81, 101).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

            PartDefinition bone = RightArm.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(102, 117).addBox(-4.0F, -3.3F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.2F))
                    .texOffs(105, 94).mirror().addBox(-3.5F, 6.7F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(81, 101).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

            PartDefinition bone2 = LeftArm.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(102, 117).mirror().addBox(8.0F, -3.3F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false)
                    .texOffs(105, 94).addBox(8.5F, 6.7F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.6F)), PartPose.offset(-10.0F, 0.0F, 0.0F));

            PartDefinition RightFoot = partdefinition.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(104, 9).addBox(-5.0F, 9.0F, -1.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 12.0F, -2.0F));

            PartDefinition bone4 = RightFoot.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(108, 0).mirror().addBox(-4.5F, 8.0F, -0.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition LeftFoot = partdefinition.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(104, 9).addBox(-5.0F, 9.0F, -1.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(4.0F, 12.0F, -2.0F));

            PartDefinition bone5 = LeftFoot.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(108, 0).mirror().addBox(-4.5F, 8.0F, -0.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            EquipmentSlot slot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 3);
            ItemStack stack = entity.getItemBySlot(slot);

            HumanoidModel livingModel = (HumanoidModel<LivingEntity>) ((LivingEntityRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity)).getModel();

            this.attackTime = livingModel.attackTime;
            this.riding = livingModel.riding;
            this.young = livingModel.young;
            this.leftArmPose = livingModel.leftArmPose;
            this.rightArmPose = livingModel.rightArmPose;
            this.crouching = livingModel.crouching;
            this.Head.copyFrom(livingModel.head);
            this.Body.copyFrom(livingModel.body);
            this.RightArm.copyFrom(livingModel.rightArm);
            this.LeftArm.copyFrom(livingModel.leftArm);
            this.RightFoot.copyFrom(livingModel.rightLeg);
            this.LeftFoot.copyFrom(livingModel.leftLeg);

            poseStack.pushPose();
            if (this.young) {
                poseStack.scale(0.5f, 0.5f, 0.5f);
                poseStack.translate(0, 1.5f, 0);
            }

            Head.render(poseStack, buffer, packedLight, packedOverlay);
            RightArm.render(poseStack, buffer, packedLight, packedOverlay);
            LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
            RightFoot.render(poseStack, buffer, packedLight, packedOverlay);
            LeftFoot.render(poseStack, buffer, packedLight, packedOverlay);

            poseStack.popPose();
        }
    }

    public static class BaseArmorLegs<T extends LivingEntity> extends HumanoidModel<T> {
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "armor_model"), "main");

        public LivingEntity entity;

        public final ModelPart RightLeg;
        public final ModelPart LeftLeg;

        public BaseArmorLegs(ModelPart root) {
            super(new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().font).bakeLayer(ModelLayers.PLAYER_INNER_ARMOR));
            this.RightLeg = root.getChild("right_leg");
            this.LeftLeg = root.getChild("left_leg");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 45).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

            PartDefinition shinguard = RightLeg.addOrReplaceChild("shinguard", CubeListBuilder.create().texOffs(0, 70).addBox(-3.4F, 1.1F, -0.5F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offset(0.4F, 0.0F, -2.5F));

            PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 45).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

            PartDefinition shinguard2 = LeftLeg.addOrReplaceChild("shinguard2", CubeListBuilder.create().texOffs(0, 70).addBox(-3.4F, 1.1F, -0.5F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offset(0.4F, 0.0F, -2.5F));

            return LayerDefinition.create(meshdefinition, 64, 32);
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

            /** Setup Anim */
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
            this.RightLeg.copyFrom(livingModel.rightLeg);
            this.LeftLeg.copyFrom(livingModel.leftLeg);

            RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
        }
    }
}