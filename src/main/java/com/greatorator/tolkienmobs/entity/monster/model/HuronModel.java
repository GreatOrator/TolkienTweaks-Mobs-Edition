package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.HuronEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Huron - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class HuronModel extends AnimatedGeoModel<HuronEntity> {
    @Override
    public ResourceLocation getModelLocation(HuronEntity object) {
        return new ResourceLocation(MODID, "geo/monster/huron.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HuronEntity object) {
        return new ResourceLocation(MODID, "textures/entity/huron.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HuronEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/huron.animation.json");
    }

    @Override
    public void setLivingAnimations(HuronEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}