package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.TrollEntity;
import com.greatorator.tolkienmobs.entity.monster.render.TrollRender;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Cave Troll - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class TrollModel extends AnimatedGeoModel<TrollEntity> {
    @Override
    public ResourceLocation getModelLocation(TrollEntity object) {
        return new ResourceLocation(MODID, "geo/monster/troll.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TrollEntity object) {
        return TrollRender.LOCATION_BY_VARIANT.getOrDefault(object.getVariant(), TrollRender.LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TrollEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/base.animation.json");
    }

    @Override
    public void setLivingAnimations(TrollEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}