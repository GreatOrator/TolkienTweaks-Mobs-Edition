package com.greatorator.tolkienmobs.item.armor.model;//package com.greatorator.tolkienmobs.client.render.model.items;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

/**
 *CustomArmor - GreatOrator
 */
public class MorgulIronArmorModel extends HumanoidArmorLayer {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
    private final ModelPart root;

    public MorgulIronArmorModel(ModelPart root) {
        super();
        this.root = root.getChild("root");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition HelmMorgulIron = root.addOrReplaceChild("HelmMorgulIron", CubeListBuilder.create().texOffs(0, 86).addBox(-8.5F, 0.0F, -0.5F, 9.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -8.0F, -4.0F));

        PartDefinition shape24 = HelmMorgulIron.addOrReplaceChild("shape24", CubeListBuilder.create().texOffs(0, 100).addBox(-9.0F, 4.0F, -1.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition shape25 = HelmMorgulIron.addOrReplaceChild("shape25", CubeListBuilder.create().texOffs(39, 112).addBox(-9.0F, 1.7F, 6.0F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition shape26 = HelmMorgulIron.addOrReplaceChild("shape26", CubeListBuilder.create().texOffs(40, 99).addBox(-5.0F, -1.0F, -1.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ChestMorgulIron = root.addOrReplaceChild("ChestMorgulIron", CubeListBuilder.create().texOffs(69, 100).addBox(-7.8F, -0.4F, -0.6F, 9.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(40, 89).addBox(-8.3F, 7.5F, -0.5F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.3F, 0.0F, -2.0F));

        PartDefinition shape23 = ChestMorgulIron.addOrReplaceChild("shape23", CubeListBuilder.create().texOffs(70, 117).addBox(-8.3F, -0.4F, -1.1F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition MorgulIronRA = root.addOrReplaceChild("MorgulIronRA", CubeListBuilder.create().texOffs(102, 117).addBox(17.0F, -0.3F, -1.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 0.0F, -2.0F));

        PartDefinition shape2 = MorgulIronRA.addOrReplaceChild("shape2", CubeListBuilder.create().texOffs(102, 103).addBox(19.0F, 0.0F, 0.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.0F, -0.5F));

        PartDefinition MorgulIronLA = root.addOrReplaceChild("MorgulIronLA", CubeListBuilder.create().texOffs(102, 117).addBox(-13.0F, -0.3F, -1.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, -2.0F));

        PartDefinition shape22 = MorgulIronLA.addOrReplaceChild("shape22", CubeListBuilder.create().texOffs(102, 103).addBox(-13.0F, 0.0F, 0.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, -0.5F));

        PartDefinition MorgulIronRL = root.addOrReplaceChild("MorgulIronRL", CubeListBuilder.create().texOffs(0, 112).mirror().addBox(-4.5F, 1.0F, -0.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 9.0F, -2.0F));

        PartDefinition MorgulIronRLPart = MorgulIronRL.addOrReplaceChild("MorgulIronRLPart", CubeListBuilder.create().texOffs(0, 97).addBox(-5.5F, 1.1F, -0.5F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.0F, -0.5F));

        PartDefinition MorgulIronLL = root.addOrReplaceChild("MorgulIronLL", CubeListBuilder.create().texOffs(0, 112).addBox(-4.5F, 0.0F, -0.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 10.0F, -2.0F));

        PartDefinition MorgulIronLLPart = MorgulIronLL.addOrReplaceChild("MorgulIronLLPart", CubeListBuilder.create().texOffs(0, 97).addBox(-5.5F, 0.1F, -0.5F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.0F, -0.5F));

        PartDefinition MorgulIronRF = root.addOrReplaceChild("MorgulIronRF", CubeListBuilder.create().texOffs(108, 0).mirror().addBox(-4.5F, 8.0F, -0.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 12.0F, -2.0F));

        PartDefinition MorgulIronRFPart = MorgulIronRF.addOrReplaceChild("MorgulIronRFPart", CubeListBuilder.create().texOffs(104, 9).addBox(-5.0F, 9.0F, -1.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition MorgulIronLF = root.addOrReplaceChild("MorgulIronLF", CubeListBuilder.create().texOffs(108, 0).addBox(-4.5F, 8.0F, -0.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 12.0F, -2.0F));

        PartDefinition MorgulIronLFPart = MorgulIronLF.addOrReplaceChild("MorgulIronLFPart", CubeListBuilder.create().texOffs(104, 9).addBox(-5.0F, 9.0F, -1.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}