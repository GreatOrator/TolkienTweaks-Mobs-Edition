package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.GoblinEntity;
import com.greatorator.tolkienmobs.entity.monster.render.HaradrimRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Goblin - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class GoblinModel extends AnimatedGeoModel<GoblinEntity> {
    @Override
    public ResourceLocation getModelLocation(GoblinEntity object) {
        return new ResourceLocation(MODID, "geo/monster/goblin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinEntity object) {
        return HaradrimRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoblinEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/goblin.animation.json");
    }

    @Override
    public void setLivingAnimations(GoblinEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}