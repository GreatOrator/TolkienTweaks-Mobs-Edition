package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.RockGolemEntity;
import com.greatorator.tolkienmobs.entity.monster.render.ElementalGolemRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Golem - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class RockGolemModel extends AnimatedGeoModel<RockGolemEntity> {
    @Override
    public ResourceLocation getModelLocation(RockGolemEntity object) {
        return new ResourceLocation(MODID, "geo/monster/rockgolem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RockGolemEntity object) {
        return ElementalGolemRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RockGolemEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/rockgolem.animation.json");
    }

    @Override
    public void setLivingAnimations(RockGolemEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("h_head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}