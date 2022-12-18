package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Dwarf - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.HobbitEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.HobbitRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class HobbitModel extends AnimatedGeoModel<HobbitEntity> {
    @Override
    public ResourceLocation getModelLocation(HobbitEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/hobbit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HobbitEntity object) {
        return HobbitRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HobbitEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/hobbit.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(HobbitEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}