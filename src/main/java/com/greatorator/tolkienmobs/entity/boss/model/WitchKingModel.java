package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.WitchKingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * WitchKing - GreatOrator
 */

public class WitchKingModel extends AnimatedGeoModel<WitchKingEntity> {
    @Override
    public ResourceLocation getModelLocation(WitchKingEntity object) {
        return new ResourceLocation(MODID, "geo/boss/witchking.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WitchKingEntity object) {
        return new ResourceLocation(MODID, "textures/entity/witchking.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WitchKingEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/base.animation.json");
    }

    @SuppressWarnings({ "unchecked"})
    @Override
    public void setLivingAnimations(WitchKingEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}