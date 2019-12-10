package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PotionTMAntidote extends TTMPotion {
    public static PotionTMAntidote instance = null;

    public PotionTMAntidote(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
        super(name, isBadEffectIn, liquidColorIn, iconIndex);
        instance = this;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        entity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
    }
}
