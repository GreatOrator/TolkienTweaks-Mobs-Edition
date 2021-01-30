//package com.greatorator.tolkienmobs.item.potiontypes;
//
//import com.google.common.collect.Lists;
//import com.greatorator.tolkienmobs.handler.TTMPotion;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.ItemStack;
//
//import java.util.List;
//
//public class PotionTTMBlacksmith extends TTMPotion {
//    public static final String NAME = "personal_blacksmith";
//    public static PotionTTMBlacksmith instance = null;
//
//    public static int damageTime = 20;
//    public PotionTTMBlacksmith(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
//        super(name, isBadEffectIn, liquidColorIn, iconIndex);
//        instance = this;
//    }
//
//    @Override
//    public void performEffect(LivingEntity entity, int amplifier) {
//        List<ItemStack> equipment = Lists.newArrayList(entity.getArmorInventoryList());
//        equipment.addAll(Lists.newArrayList(entity.getHeldEquipment()));
//
//        if(entity.ticksExisted % damageTime == 0) {
//            for(ItemStack stack : equipment) {
//                if(stack.getItem().isDamageable() && stack.getItem().isDamaged(stack)) {
//                    if(stack.getItemDamage()-(amplifier+1) < 0) {
//                        stack.setItemDamage(0);
//                    }
//                    else {
//                        stack.setItemDamage(stack.getItemDamage()-(amplifier+1));
//                    }
//                }
//            }
//        }
//    }
//}