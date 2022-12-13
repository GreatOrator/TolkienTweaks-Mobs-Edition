package com.greatorator.tolkienmobs.entity.passive.model;

import com.greatorator.tolkienmobs.entity.passive.MumakilEntity;
import com.greatorator.tolkienmobs.entity.passive.render.MumakilRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Mumakil - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class MumakilModel extends AnimatedGeoModel<MumakilEntity> {
    @Override
    public ResourceLocation getModelLocation(MumakilEntity mumakil) {
        return new ResourceLocation(MODID, "geo/passive/mumakil.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MumakilEntity mumakil) {
        return MumakilRender.LOCATION_BY_VARIANT.get(mumakil.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MumakilEntity mumakil) {
        return new ResourceLocation(MODID, "animations/passive/mumakil.animation.json");
    }

    @Override
    public void setLivingAnimations(MumakilEntity mumakil, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(mumakil, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("MumuNeck");

        if (mumakil.isBaby()) {
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        }

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}