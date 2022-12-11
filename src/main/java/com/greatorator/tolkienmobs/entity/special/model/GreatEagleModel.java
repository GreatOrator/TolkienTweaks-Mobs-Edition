package com.greatorator.tolkienmobs.entity.special.model;

import com.greatorator.tolkienmobs.entity.special.GreatEagleEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GreatEagleModel extends AnimatedGeoModel<GreatEagleEntity> {
    @Override
    public ResourceLocation getModelLocation(GreatEagleEntity object) {
        return new ResourceLocation(MODID, "geo/special/eagle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GreatEagleEntity object) {
        return new ResourceLocation(MODID, "textures/entity/birds/entitygreateagle.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GreatEagleEntity animatable) {
        return new ResourceLocation(MODID, "animations/special/eagle.animation.json");
    }

    @SuppressWarnings({ "unchecked"})
    @Override
    public void setLivingAnimations(GreatEagleEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("neck");
        IBone king = this.getAnimationProcessor().getBone("crown");

        king.setHidden(true);

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}