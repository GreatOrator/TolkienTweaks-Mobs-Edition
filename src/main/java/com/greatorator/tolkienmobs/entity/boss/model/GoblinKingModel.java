package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Goblin King - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class GoblinKingModel extends AnimatedGeoModel<GoblinKingEntity> {
    @Override
    public ResourceLocation getModelLocation(GoblinKingEntity object) {
        return new ResourceLocation(MODID, "geo/boss/goblinking.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinKingEntity object) {
        return new ResourceLocation(MODID, "textures/entity/goblin/goblinking.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoblinKingEntity animatable) {
        return new ResourceLocation(MODID, "animations/boss/goblinking.animation.json");
    }

    @Override
    public void setLivingAnimations(GoblinKingEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}