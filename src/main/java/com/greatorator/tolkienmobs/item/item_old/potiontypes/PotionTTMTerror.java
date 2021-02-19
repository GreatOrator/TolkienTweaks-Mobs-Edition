//package com.greatorator.tolkienmobs.item.item_old.potiontypes;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.handler.TTMPotion;
//import com.greatorator.tolkienmobs.utils.TTMUtilities;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
//import net.minecraft.entity.player.PlayerEntity;
//
//import java.util.Random;
//
//public class PotionTTMTerror extends TTMPotion {
//    public static final String NAME = "crippling_fear";
//    public static PotionTTMTerror instance = null;
//
//    public PotionTTMTerror(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
//        super(name, isBadEffectIn, liquidColorIn, iconIndex);
//        instance = this;
//    }
//
//    @Override
//    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributeMap, int amplifier)
//    {
//        super.applyAttributesModifiersToEntity(entity, attributeMap, amplifier);
//
//        if(entity instanceof EntityLiving)
//        {
//            ((EntityLiving) entity).setNoAI(true);
//        }
//
//        entity.setSilent(true);
//    }
//
//    @Override
//    public void performEffect(LivingEntity entity, int amplifier)
//    {
//        if(this.canFreeze(entity))
//        {
//            Random random = entity.getRNG();
//            boolean frozen = entity.isPotionActive(this);
//
//            if (!frozen) {
//                if (random.nextInt(TTMConfig_Old.chanceTerror) == 0) {
//                    entity.setVelocity(0D, 0D, 0D);
//                    entity.velocityChanged = true;
//                    entity.moveVertical =0;
//                    entity.moveStrafing =0;
//                    entity.motionX =0;
//                    entity.motionZ=0;
//                }
//            }
//            else
//            {
//                if(entity instanceof PlayerEntity)
//                {
//                    entity.setPosition(entity.prevPosX, entity.posY, entity.prevPosZ);
//                }
//                if(random.nextInt(TTMConfig_Old.chanceNotAfraid) == 0)
//                {
//                    entity.removePotionEffect(this);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributeMap, int amplifier)
//    {
//        super.removeAttributesModifiersFromEntity(entity, attributeMap, amplifier);
//
//        if(entity instanceof EntityLiving)
//        {
//            if(((EntityLiving) entity).isAIDisabled())
//            {
//                ((EntityLiving) entity).setNoAI(false);
//            }
//        }
//
//        entity.setSilent(false);
//    }
//
//    @Override
//    public boolean isReady(int duration, int amplifier)
//    {
//        return true;
//    }
//
//    public boolean canFreeze(LivingEntity entity)
//    {
//        if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative())
//        {
//            return TTMConfig_Old.disablePlayerTerror;
//        }
//
//        String entityRegistryName = TTMUtilities.getEntityLocation(entity);
//        return entityRegistryName != null;
//    }
//}