package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;

public class PotionElementalLightning extends TTMPotion {
    public static final String NAME = "elemental_lightning";
    public static PotionElementalLightning instance = null;

    public PotionElementalLightning() {
        super(NAME, true, 16640281, 3);
        instance = this;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {

        entity.world.addWeatherEffect(new EntityLightningBolt(entity.world, entity.posX, entity.posY, entity.posZ, true));
    }
}