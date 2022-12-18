package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.WargEntity;
import com.greatorator.tolkienmobs.entity.monster.render.UrukHaiRender;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Warg - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class WargModel extends AnimatedGeoModel<WargEntity> {
    @Override
    public ResourceLocation getModelLocation(WargEntity object) {
        return new ResourceLocation(MODID, "geo/monster/warg.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WargEntity object) {
        return UrukHaiRender.LOCATION_BY_VARIANT.getOrDefault(object.getVariant(), UrukHaiRender.LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WargEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/warg.animation.json");
    }

    @Override
    public void setLivingAnimations(WargEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}