package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.MirkwoodSpiderEntity;
import com.greatorator.tolkienmobs.entity.monster.render.MirkwoodSpiderRender;
import com.greatorator.tolkienmobs.entity.monster.render.MordorOrcRender;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Mirkwood Spider - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class MirkwoodSpiderModel extends AnimatedGeoModel<MirkwoodSpiderEntity> {
    @Override
    public ResourceLocation getModelLocation(MirkwoodSpiderEntity object) {
        return new ResourceLocation(MODID, "geo/monster/mirkwoodspider.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MirkwoodSpiderEntity object) {
        return MirkwoodSpiderRender.LOCATION_BY_VARIANT.getOrDefault(object.getVariant(), MordorOrcRender.LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MirkwoodSpiderEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/mirkwoodspider.animation.json");
    }

    @Override
    public void setLivingAnimations(MirkwoodSpiderEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone webbing = this.getAnimationProcessor().getBone("webbing");

        webbing.setHidden(!entity.isWebShooting());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}