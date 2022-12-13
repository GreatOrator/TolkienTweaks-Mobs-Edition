package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.MimicChestEntity;
import com.greatorator.tolkienmobs.entity.monster.render.MimicChestRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** ModelGollum - GreatOrator */
@SuppressWarnings({"removal" })
public class MimicChestModel extends AnimatedGeoModel<MimicChestEntity> {
    @Override
    public ResourceLocation getModelLocation(MimicChestEntity object) {
        return new ResourceLocation(MODID, "geo/monster/mimic.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MimicChestEntity object) {
        return MimicChestRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MimicChestEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/mimic.animation.json");
    }

    @Override
    public void setLivingAnimations(MimicChestEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        IBone active1 = this.getAnimationProcessor().getBone("MimicTongue");
        IBone active2 = this.getAnimationProcessor().getBone("leftLeg");
        IBone active3 = this.getAnimationProcessor().getBone("rightLeg");
        IBone active4 = this.getAnimationProcessor().getBone("leftbackLeg");
        IBone active5 = this.getAnimationProcessor().getBone("rightbackLeg");

        active1.setHidden(!entity.isActive());
        active2.setHidden(!entity.isActive());
        active3.setHidden(!entity.isActive());
        active4.setHidden(!entity.isActive());
        active5.setHidden(!entity.isActive());
    }
}