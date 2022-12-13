package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.OathbreakerEntity;
import com.greatorator.tolkienmobs.entity.monster.render.OathbreakerRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class OathbreakerModel extends AnimatedGeoModel<OathbreakerEntity> {
    @Override
    public ResourceLocation getModelLocation(OathbreakerEntity object) {
        return new ResourceLocation(MODID, "geo/monster/base.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OathbreakerEntity object) {
        return OathbreakerRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OathbreakerEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/base.animation.json");
    }

    @Override
    public void setLivingAnimations(OathbreakerEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
