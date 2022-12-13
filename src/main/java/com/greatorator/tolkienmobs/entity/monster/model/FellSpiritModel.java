package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.FellSpiritEntity;
import com.greatorator.tolkienmobs.entity.monster.render.FellSpiritRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class FellSpiritModel extends AnimatedGeoModel<FellSpiritEntity> {
    @Override
    public ResourceLocation getModelLocation(FellSpiritEntity object) {
        return new ResourceLocation(MODID, "geo/monster/base.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FellSpiritEntity object) {
        return FellSpiritRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FellSpiritEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/base.animation.json");
    }

    @Override
    public void setLivingAnimations(FellSpiritEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
