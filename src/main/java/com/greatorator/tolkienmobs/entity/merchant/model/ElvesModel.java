package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Dwarf - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.ElvesEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.ElvesRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class ElvesModel extends AnimatedGeoModel<ElvesEntity> {
    @Override
    public ResourceLocation getModelLocation(ElvesEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/elf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ElvesEntity object) {
        return ElvesRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ElvesEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/elf.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(ElvesEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}