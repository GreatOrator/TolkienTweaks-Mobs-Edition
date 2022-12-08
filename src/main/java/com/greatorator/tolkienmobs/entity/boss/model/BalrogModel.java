package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.BalrogEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Balrog - GreatOrator */

public class BalrogModel extends AnimatedGeoModel<BalrogEntity> {
    @Override
    public ResourceLocation getModelLocation(BalrogEntity object) {
        return new ResourceLocation(MODID, "geo/boss/balrog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BalrogEntity object) {
        return new ResourceLocation(MODID, "textures/entity/balrog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BalrogEntity animatable) {
        return new ResourceLocation(MODID, "animations/boss/balrog.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(BalrogEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}