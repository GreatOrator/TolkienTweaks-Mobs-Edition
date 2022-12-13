package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.ShelobEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Shelob - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class ShelobModel extends AnimatedGeoModel<ShelobEntity> {
    @Override
    public ResourceLocation getModelLocation(ShelobEntity object) {
        return new ResourceLocation(MODID, "geo/boss/shelob.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShelobEntity object) {
        return new ResourceLocation(MODID, "textures/entity/tmshelob.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShelobEntity animatable) {
        return new ResourceLocation(MODID, "animations/boss/shelob.animation.json");
    }

    @Override
    public void setLivingAnimations(ShelobEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
