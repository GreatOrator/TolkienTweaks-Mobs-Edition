package com.greatorator.tolkienmobs.entity.passive.model;

import com.greatorator.tolkienmobs.entity.passive.AurochEntity;
import com.greatorator.tolkienmobs.entity.passive.render.AurochRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Auroch - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class AurochModel extends AnimatedGeoModel<AurochEntity> {
    @Override
    public ResourceLocation getModelLocation(AurochEntity mumakil) {
        return new ResourceLocation(MODID, "geo/passive/auroch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AurochEntity auroch) {
        return AurochRender.LOCATION_BY_VARIANT.get(auroch.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AurochEntity auroch) {
        return new ResourceLocation(MODID, "animations/passive/auroch.animation.json");
    }

    @Override
    public void setLivingAnimations(AurochEntity auroch, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(auroch, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");

        if (auroch.isBaby()) {
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        }

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}