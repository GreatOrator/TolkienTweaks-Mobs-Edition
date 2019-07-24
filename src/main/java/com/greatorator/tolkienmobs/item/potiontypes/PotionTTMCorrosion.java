package com.greatorator.tolkienmobs.item.potiontypes;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.List;

public class PotionTTMCorrosion extends TTMPotion {
    public static final String NAME = "inventory_corrosion";
    public static PotionTTMCorrosion instance = null;

    public static int damageTime = 20;
    public PotionTTMCorrosion(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
        super(name, isBadEffectIn, liquidColorIn, iconIndex);
        instance = this;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        List<ItemStack> equipment = Lists.newArrayList(entity.getArmorInventoryList());
        equipment.addAll(Lists.newArrayList(entity.getHeldEquipment()));

        if(entity.ticksExisted % damageTime == 0) {
            for(ItemStack stack : equipment) {
                if(stack.getItem().isDamageable() && !stack.getItem().getIsRepairable(stack, new ItemStack(Items.GOLD_INGOT))) {
                    if(!entity.world.isRemote) {
                        stack.damageItem(amplifier+1, entity);
                    }
                }
            }
        }
    }
}