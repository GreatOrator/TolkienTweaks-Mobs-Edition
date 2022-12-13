package com.greatorator.tolkienmobs.entity.ambient.model;

import com.greatorator.tolkienmobs.entity.ambient.FrogEntity;
import com.greatorator.tolkienmobs.entity.ambient.render.FrogRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Frog - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class FrogModel extends AnimatedGeoModel<FrogEntity> {
    @Override
    public ResourceLocation getModelLocation(FrogEntity object) {
        return new ResourceLocation(MODID, "geo/ambient/frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FrogEntity object) {
        return FrogRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FrogEntity animatable) {
        return new ResourceLocation(MODID, "animations/ambient/frog.animation.json");
    }

    @Override
    public void setLivingAnimations(FrogEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone catchBug = this.getAnimationProcessor().getBone("insect");
        IBone tongue = this.getAnimationProcessor().getBone("tongue");

        catchBug.setHidden(!entity.isCatching());
        tongue.setHidden(!entity.isCatching());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}