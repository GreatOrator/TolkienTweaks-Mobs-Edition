package com.greatorator.tolkienmobs.entity.special.model;

import com.greatorator.tolkienmobs.entity.special.NazgulSteedEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked"})
public class NazgulSteedModel extends AnimatedGeoModel<NazgulSteedEntity> {
    @Override
    public ResourceLocation getModelLocation(NazgulSteedEntity steed) {
        return new ResourceLocation(MODID, "geo/special/nazgulsteed.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NazgulSteedEntity steed) {
        return new ResourceLocation(MODID, "textures/entity/horse/nazgulsteed.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NazgulSteedEntity steed) {
        return new ResourceLocation(MODID, "animations/special/horse.animation.json");
    }

    @Override
    public void setLivingAnimations(NazgulSteedEntity steed, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(steed, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone chested = this.getAnimationProcessor().getBone("chest");
        IBone saddled = this.getAnimationProcessor().getBone("headSaddle");
        IBone saddled2 = this.getAnimationProcessor().getBone("Saddle");

        if (steed.isBaby()) {
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        }

        saddled.setHidden(!steed.isSaddled());
        saddled2.setHidden(!steed.isSaddled());
        chested.setHidden(!steed.hasChest());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}