package com.greatorator.tolkienmobs.entity.passive.model;

import com.greatorator.tolkienmobs.entity.passive.GoatEntity;
import com.greatorator.tolkienmobs.entity.passive.render.GoatRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * Goat - GreatOrator
 */
public class GoatModel extends AnimatedGeoModel<GoatEntity> {
    @Override
    public ResourceLocation getModelLocation(GoatEntity mumakil) {
        return new ResourceLocation(MODID, "geo/passive/goat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoatEntity goat) {
        return GoatRender.LOCATION_BY_VARIANT.get(goat.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoatEntity goat) {
        return new ResourceLocation(MODID, "animations/passive/goat.animation.json");
    }

    @Override
    public void setLivingAnimations(GoatEntity goat, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(goat, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone chested = this.getAnimationProcessor().getBone("chest");
        IBone saddled = this.getAnimationProcessor().getBone("Saddle");

        if (goat.isBaby()) {
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        }

        saddled.setHidden(!goat.isSaddled());
        chested.setHidden(!goat.hasChest());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}