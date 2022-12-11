package com.greatorator.tolkienmobs.entity.ambient.model;

import com.greatorator.tolkienmobs.entity.ambient.RatEntity;
import com.greatorator.tolkienmobs.entity.ambient.render.RatRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Rat - GreatOrator */
@SuppressWarnings({ "unchecked"})
public class RatModel extends AnimatedGeoModel<RatEntity> {
    @Override
    public ResourceLocation getModelLocation(RatEntity object) {
        return new ResourceLocation(MODID, "geo/ambient/rat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RatEntity object) {
        return RatRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RatEntity animatable) {
        return new ResourceLocation(MODID, "animations/ambient/rat.animation.json");
    }

    @Override
    public void setLivingAnimations(RatEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}