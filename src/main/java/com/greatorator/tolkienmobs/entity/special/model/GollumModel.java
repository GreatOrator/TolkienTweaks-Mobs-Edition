package com.greatorator.tolkienmobs.entity.special.model;

import com.greatorator.tolkienmobs.entity.special.GollumEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * ModelGollum - GreatOrator
 */
public class GollumModel extends AnimatedGeoModel<GollumEntity> {
    private final ResourceLocation mobTexture = new ResourceLocation(MODID, "textures/entity/birds/entitygreateagle.png");

    @Override
    public ResourceLocation getModelLocation(GollumEntity object) {
        return new ResourceLocation(MODID, "geo/special/gollum.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GollumEntity object) {
        return new ResourceLocation(MODID + ":textures/entity/gollum.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GollumEntity animatable) {
        return new ResourceLocation(MODID, "animations/special/gollum.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(GollumEntity gollum, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(gollum, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("GollumHead");
        IBone eating = this.getAnimationProcessor().getBone("fish");

        eating.setHidden(!gollum.isEating());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}