package com.greatorator.tolkienmobs.item.armor.model;//package com.greatorator.tolkienmobs.client.render.model.items;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 *CustomArmor - GreatOrator
 */
public class BaseArmorModel<T extends LivingEntity> extends HumanoidModel<T>{
    public static BaseArmorModel INSTANCE;
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart leftArm;
    public final ModelPart rightFoot;
    public final ModelPart leftFoot;
    public final ModelPart rightLeg;
    public final ModelPart leftLeg;

    public BaseArmorModel(ModelPart part) {
        super(part);
        this.head = part.getChild("head");
        this.body = part.getChild("body");
        this.rightArm = part.getChild("right_arm");
        this.leftArm = part.getChild("left_arm");
        this.rightFoot = part.getChild("right_leg");
        this.leftFoot = part.getChild("left_leg");
        this.rightLeg = part.getChild("right_leg");
        this.leftLeg = part.getChild("left_leg");
    }

    public static class armorBody<T extends LivingEntity> extends HumanoidModel<T> {
        public static final ModelLayerLocation BODY_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "armor_model_body"), "body");
        public LivingEntity entity;

        public armorBody(ModelPart part) {
            super(part);
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0436F, 0.0F, 0.0F));
            PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition rightarm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition leftarm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition rightleg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition leftleg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

            hat.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 86).addBox(-4.5F, -5.0F, -4.0F, 9.0F, 5.0F, 9.0F, new CubeDeformation(1.0F))
                    .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            hat.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 100).addBox(-5.0F, -23.3F, -4.5F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.9F))
                    .texOffs(40, 112).addBox(-5.0F, -26.2F, 2.5F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.7F))
                    .texOffs(40, 97).addBox(-1.0F, -30.3F, -5.2F, 2.0F, 4.0F, 11.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));
            body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(70, 101).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            body.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(70, 117).addBox(-5.0F, -25.4F, -3.1F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.2F))
                    .texOffs(0, 50).addBox(-5.0F, -16.5F, -2.5F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 24.0F, 0.0F));
            rightarm.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(81, 101).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
            rightarm.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(102, 117).addBox(-4.0F, -3.3F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.2F))
                    .texOffs(105, 94).mirror().addBox(-3.5F, 6.7F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
            leftarm.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(81, 101).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
            leftarm.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(102, 117).mirror().addBox(8.0F, -3.3F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false)
                    .texOffs(105, 94).addBox(8.5F, 6.7F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.6F)), PartPose.offset(-10.0F, 0.0F, 0.0F));
            rightleg.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(104, 9).addBox(-5.0F, 9.0F, -1.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 12.0F, -2.0F));
            rightleg.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(108, 0).mirror().addBox(-4.5F, 8.0F, -0.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
            leftleg.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(104, 9).addBox(-5.0F, 9.0F, -1.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(4.0F, 12.0F, -2.0F));
            leftleg.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(108, 0).mirror().addBox(-4.5F, 8.0F, -0.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            EquipmentSlot slot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 3);
            ItemStack stack = entity.getItemBySlot(slot);

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
            this.rightLeg.copyFrom(livingModel.rightLeg);
            this.leftLeg.copyFrom(livingModel.leftLeg);

            /** Not Translucent Armor Parts */
            body.render(poseStack, buffer, packedLight, packedOverlay);
            rightArm.render(poseStack, buffer, packedLight, packedOverlay);
            leftArm.render(poseStack, buffer, packedLight, packedOverlay);
            rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
            head.render(poseStack, buffer, packedLight, packedOverlay);
        }

        public VertexConsumer getVertex(RenderType p_115186_, boolean p_115187_, boolean p_115188_) {
            MultiBufferSource p_115185_ = Minecraft.getInstance().renderBuffers().bufferSource();
            return p_115188_ ? VertexMultiConsumer.create(p_115185_.getBuffer(p_115187_ ? RenderType.armorGlint() : RenderType.armorEntityGlint()), p_115185_.getBuffer(p_115186_)) : p_115185_.getBuffer(p_115186_);
        }
    }

    public static class armorLegs<T extends LivingEntity> extends HumanoidModel<T> {

        public static final ModelLayerLocation LEG_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "armor_model_legs"), "legs");

        public LivingEntity entity;

        public armorLegs(ModelPart part) {
            super(part);
        }

        public static LayerDefinition createLegLayer() {
            MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
            PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition rightleg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition leftleg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

            rightleg.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 45).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
            rightleg.addOrReplaceChild("shinguard", CubeListBuilder.create().texOffs(0, 70).addBox(-3.4F, 1.1F, -0.5F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offset(0.4F, 0.0F, -2.5F));
            leftleg.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 45).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
            leftleg.addOrReplaceChild("shinguard2", CubeListBuilder.create().texOffs(0, 70).addBox(-3.4F, 1.1F, -0.5F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offset(0.4F, 0.0F, -2.5F));

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
            this.rightLeg.copyFrom(livingModel.rightLeg);
            this.leftLeg.copyFrom(livingModel.leftLeg);

            rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
        }
    }
}