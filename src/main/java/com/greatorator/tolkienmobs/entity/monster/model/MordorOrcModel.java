package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.MordorOrcEntity;
import com.greatorator.tolkienmobs.entity.monster.render.MordorOrcRender;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Mordor Orc - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class MordorOrcModel extends AnimatedGeoModel<MordorOrcEntity> {
    @Override
    public ResourceLocation getModelLocation(MordorOrcEntity object) {
        return new ResourceLocation(MODID, "geo/monster/mordororc.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MordorOrcEntity object) {
        return MordorOrcRender.LOCATION_BY_VARIANT.getOrDefault(object.getVariant(), MordorOrcRender.LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MordorOrcEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/base.animation.json");
    }

    @Override
    public void setLivingAnimations(MordorOrcEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}