package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

/** Borrowed from Tmtravlr - PotionCore*/
public class PotionTTMDrowning extends TTMPotion {
    public static final String NAME = "elemental_drowning";
    public static final String TAG_NAME = "elemental-drown";
    public static final String TAG_BOOLEAN = "elemental-being drowned";
    public static PotionTTMDrowning instance = null;

    public PotionTTMDrowning() {
        super(NAME, true, 7791097, 2);
        instance = this;
    }

    public boolean canAmplify() {
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {

        if(!entity.getEntityData().hasKey(TAG_NAME)) {
            entity.getEntityData().setInteger(TAG_NAME, 300);
        }

        if (entity.isInsideOfMaterial(Material.WATER))
        {
            entity.setAir(300);
            entity.getEntityData().setInteger(TAG_NAME, 300);
        }

        int respiration = EnchantmentHelper.getRespirationModifier(entity);
        int air = entity.getEntityData().getInteger(TAG_NAME);
        air = ((respiration > 0) && (entity.getRNG().nextInt(respiration + 1) > 0) ? air : air - 1);

        if (air == -20)
        {
            air = 0;
            if(!entity.world.isRemote) {
                entity.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        }
        entity.getEntityData().setInteger(TAG_NAME, air);
    }
}