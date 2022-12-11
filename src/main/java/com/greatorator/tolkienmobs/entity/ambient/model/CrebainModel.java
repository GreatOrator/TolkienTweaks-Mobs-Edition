package com.greatorator.tolkienmobs.entity.ambient.model;

import com.greatorator.tolkienmobs.entity.ambient.CrebainEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/* Crebain - GreatOrator */
@SuppressWarnings({ "unchecked"})
public class CrebainModel extends AnimatedGeoModel<CrebainEntity> {
    @Override
    public ResourceLocation getModelLocation(CrebainEntity object) {
        return new ResourceLocation(MODID, "geo/ambient/crebain.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CrebainEntity object) {
        return new ResourceLocation(MODID, "textures/entity/birds/crebain.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CrebainEntity animatable) {
        return new ResourceLocation(MODID, "animations/ambient/crebain.animation.json");
    }

    @Override
    public void setLivingAnimations(CrebainEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("neck");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            int pitch = entity.getFlying() ? 30 : 0;
            head.setRotationX((extraData.headPitch - pitch) * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}