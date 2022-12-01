package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Human - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.DesertDwellerEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class DesertDwellerModel<T extends LivingEntity> extends HumanoidModel<DesertDwellerEntity> {
    public DesertDwellerModel(ModelPart part) {
        super(part);
    }
}