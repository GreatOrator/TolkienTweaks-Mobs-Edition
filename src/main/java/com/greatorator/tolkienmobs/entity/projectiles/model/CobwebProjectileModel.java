package com.greatorator.tolkienmobs.entity.projectiles.model;

import com.greatorator.tolkienmobs.entity.projectiles.CobwebProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class CobwebProjectileModel extends AnimatedGeoModel<CobwebProjectileEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(CobwebProjectileEntity entity) {
        return new ResourceLocation(MODID, "animations/ammo_cobweb.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(CobwebProjectileEntity entity) {
        return new ResourceLocation(MODID, "geo/item/ammo_cobweb.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CobwebProjectileEntity entity) {
        return new ResourceLocation(MODID, "textures/entity/ammo_cobweb.png");
    }

    @Override
    public void setCustomAnimations(CobwebProjectileEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone everything = this.getAnimationProcessor().getBone("webbing");

        everything.setRotationY(-1.5708F);
    }
}