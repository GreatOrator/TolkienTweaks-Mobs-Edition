package com.greatorator.tolkienmobs.entity.boss.model;

import com.greatorator.tolkienmobs.entity.boss.GwahirEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class GwahirModel extends AnimatedGeoModel<GwahirEntity> {
    @Override
    public ResourceLocation getModelLocation(GwahirEntity object) {
        return new ResourceLocation(MODID, "geo/special/eagle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GwahirEntity object) {
        return new ResourceLocation(MODID, "textures/entity/birds/entitygreateagle.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GwahirEntity animatable) {
        return new ResourceLocation(MODID, "animations/special/eagle.animation.json");
    }

    @Override
    public void setLivingAnimations(GwahirEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("neck");
        IBone king = this.getAnimationProcessor().getBone("crown");

        king.setHidden(false);

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}