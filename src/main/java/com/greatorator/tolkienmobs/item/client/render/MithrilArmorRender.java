package com.greatorator.tolkienmobs.item.client.render;

import com.greatorator.tolkienmobs.item.armor.MithrilArmorItem;
import com.greatorator.tolkienmobs.item.client.model.MithrilArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class MithrilArmorRender extends GeoArmorRenderer<MithrilArmorItem> {
    public MithrilArmorRender() {
        super(new MithrilArmorModel());

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