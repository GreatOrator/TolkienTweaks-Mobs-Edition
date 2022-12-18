package com.greatorator.tolkienmobs.entity.projectiles.model;

import com.greatorator.tolkienmobs.entity.projectiles.DragonProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({"removal" })
public class DragonWeaponModel extends AnimatedGeoModel<DragonProjectileEntity> {
    public static final ResourceLocation DRAGON_FIRE = new ResourceLocation(MODID, "item/ammo/ammo_fellbeast_fireball.png");

    @Override
    public ResourceLocation getModelLocation(DragonProjectileEntity object) {
        return new ResourceLocation(MODID, "geo/item/ammo_dragon_breath.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DragonProjectileEntity object) {
        return DRAGON_FIRE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DragonProjectileEntity animatable) {
        return new ResourceLocation(MODID, "animations/ammo_dragon_breath.animation.json");
    }
}
