package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Human - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.HumanEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.HumanRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class HumanModel extends AnimatedGeoModel<HumanEntity> {
    @Override
    public ResourceLocation getModelLocation(HumanEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/human.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HumanEntity object) {
        return HumanRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HumanEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/human.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(HumanEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}