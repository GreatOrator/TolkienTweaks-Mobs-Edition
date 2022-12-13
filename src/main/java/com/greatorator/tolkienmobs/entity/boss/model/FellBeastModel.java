package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.FellBeastEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Fell Beast - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class FellBeastModel extends AnimatedGeoModel<FellBeastEntity> {
    @Override
    public ResourceLocation getModelLocation(FellBeastEntity object) {
        String modelLocation = "geo/boss/fellbeast.geo.json";
        return new ResourceLocation(MODID, modelLocation);
    }

    @Override
    public ResourceLocation getTextureLocation(FellBeastEntity object) {
        String textureLocation = "textures/entity/fellbeast/fellbeast.png";
        return new ResourceLocation(MODID, textureLocation);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FellBeastEntity animatable) {
        return new ResourceLocation(MODID, "animations/boss/fellbeast.animation.json");
    }

    @Override
    public void setLivingAnimations(FellBeastEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(extraData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
