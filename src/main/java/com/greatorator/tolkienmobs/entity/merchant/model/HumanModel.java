package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Human - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.HumanEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class HumanModel<T extends LivingEntity> extends HumanoidModel<HumanEntity> {
    public HumanModel(ModelPart part) {
        super(part);
    }
}