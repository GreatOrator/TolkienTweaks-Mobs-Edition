package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.ElementalGolemEntity;
import com.greatorator.tolkienmobs.entity.monster.render.ElementalGolemRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Golem - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class ElementalGolemModel extends AnimatedGeoModel<ElementalGolemEntity> {
    @Override
    public ResourceLocation getModelLocation(ElementalGolemEntity object) {
        return new ResourceLocation(MODID, "geo/monster/golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ElementalGolemEntity object) {
        return ElementalGolemRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ElementalGolemEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/golem.animation.json");
    }

    @Override
    public void setLivingAnimations(ElementalGolemEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}