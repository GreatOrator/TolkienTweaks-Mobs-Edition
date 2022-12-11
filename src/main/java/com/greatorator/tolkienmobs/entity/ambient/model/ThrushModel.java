package com.greatorator.tolkienmobs.entity.ambient.model;

import com.greatorator.tolkienmobs.entity.ambient.ThrushEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/* Thrush - GreatOrator */
public class ThrushModel extends AnimatedGeoModel<ThrushEntity> {
    @Override
    public ResourceLocation getModelLocation(ThrushEntity object) {
        return new ResourceLocation(MODID, "geo/ambient/thrush.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ThrushEntity object) {
        return new ResourceLocation(MODID, "textures/entity/birds/thrush.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ThrushEntity animatable) {
        return new ResourceLocation(MODID, "animations/ambient/thrush.animation.json");
    }

    @SuppressWarnings({ "unchecked"})
    @Override
    public void setLivingAnimations(ThrushEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("neck");
        IBone pecking = this.getAnimationProcessor().getBone("snail");
        IBone tamed = this.getAnimationProcessor().getBone("legband");

        if (entity.isBaby()) {
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        }

        pecking.setHidden(!entity.isPecking());
        tamed.setHidden(!entity.isTame());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}