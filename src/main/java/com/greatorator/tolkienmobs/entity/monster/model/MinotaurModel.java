package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.MinotaurEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Minotaur - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class MinotaurModel extends AnimatedGeoModel<MinotaurEntity> {
    @Override
    public ResourceLocation getModelLocation(MinotaurEntity object) {
        return new ResourceLocation(MODID, "geo/monster/minotaur.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MinotaurEntity object) {
        return new ResourceLocation(MODID, "textures/entity/minotaur.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MinotaurEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/minotaur.animation.json");
    }

    @Override
    public void setLivingAnimations(MinotaurEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}