package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.SwampHagEntity;
import com.greatorator.tolkienmobs.entity.monster.render.SwampHagRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Borrowed from Konarinn */
@SuppressWarnings({ "unchecked", "removal" })
public class SwampHagModel extends AnimatedGeoModel<SwampHagEntity> {
    @Override
    public ResourceLocation getModelLocation(SwampHagEntity object) {
        return new ResourceLocation(MODID,"geo/monster/swamphag.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SwampHagEntity object) {
        return SwampHagRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SwampHagEntity animatable) {
        return new ResourceLocation(MODID,"animations/monster/swamphag.animation.json");
    }

    @Override
    public void setLivingAnimations(SwampHagEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
