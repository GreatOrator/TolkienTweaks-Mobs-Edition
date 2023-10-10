package com.greatorator.tolkienmobs.entity.special.model;

import com.greatorator.tolkienmobs.entity.special.IstariEntity;
import com.greatorator.tolkienmobs.entity.special.render.IstariRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** ModelGollum - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class IstariModel extends AnimatedGeoModel<IstariEntity> {
    @Override
    public ResourceLocation getModelLocation(IstariEntity object) {
        return new ResourceLocation(MODID, "geo/special/istari.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(IstariEntity object) {
        return IstariRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IstariEntity animatable) {
        return new ResourceLocation(MODID, "animations/special/istari.animation.json");
    }

    @Override
    public void setLivingAnimations(IstariEntity gollum, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(gollum, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}