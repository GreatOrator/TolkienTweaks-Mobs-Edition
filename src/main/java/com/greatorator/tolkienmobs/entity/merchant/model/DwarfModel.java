package com.greatorator.tolkienmobs.entity.merchant.model;

/*
 * Dwarf - GreatOrator
 */

import com.greatorator.tolkienmobs.entity.merchant.DwarfEntity;
import com.greatorator.tolkienmobs.entity.merchant.render.DwarfRender;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "removal" })
public class DwarfModel extends AnimatedGeoModel<DwarfEntity> {
    @Override
    public ResourceLocation getModelLocation(DwarfEntity object) {
        return new ResourceLocation(MODID, "geo/merchant/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity object) {
        return DwarfRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DwarfEntity animatable) {
        return new ResourceLocation(MODID, "animations/merchant/dwarf.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(DwarfEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}