package com.greatorator.tolkienmobs.item.client.render;

import com.greatorator.tolkienmobs.item.armor.MorgulironArmorItem;
import com.greatorator.tolkienmobs.item.client.model.MorgulironArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class MorgulironArmorRender extends GeoArmorRenderer<MorgulironArmorItem> {
    public MorgulironArmorRender() {
        super(new MorgulironArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}