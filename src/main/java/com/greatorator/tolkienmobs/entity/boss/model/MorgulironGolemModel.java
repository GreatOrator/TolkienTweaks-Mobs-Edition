package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.MorgulironGolemEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Golem - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class MorgulironGolemModel extends AnimatedGeoModel<MorgulironGolemEntity> {
    @Override
    public ResourceLocation getModelLocation(MorgulironGolemEntity object) {
        return new ResourceLocation(MODID, "geo/monster/golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MorgulironGolemEntity object) {
        return new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_morgul.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MorgulironGolemEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/golem.animation.json");
    }

    @Override
    public void setLivingAnimations(MorgulironGolemEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}