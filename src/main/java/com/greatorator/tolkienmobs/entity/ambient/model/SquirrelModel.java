package com.greatorator.tolkienmobs.entity.ambient.model;

import com.greatorator.tolkienmobs.entity.ambient.SquirrelEntity;
import com.greatorator.tolkienmobs.entity.ambient.render.SquirrelRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** SOSquirrel - Boyd151995 & GreatOrator */
@SuppressWarnings({ "unchecked"})
public class SquirrelModel extends AnimatedGeoModel<SquirrelEntity> {
    @Override
    public ResourceLocation getModelLocation(SquirrelEntity object) {
        return new ResourceLocation(MODID, "geo/ambient/squirrel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SquirrelEntity object) {
        return SquirrelRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SquirrelEntity animatable) {
        return new ResourceLocation(MODID, "animations/ambient/squirrel.animation.json");
    }

    @Override
    public void setLivingAnimations(SquirrelEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}
