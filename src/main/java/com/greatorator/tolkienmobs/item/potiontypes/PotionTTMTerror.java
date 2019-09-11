package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.handler.TTMPotion;
import com.greatorator.tolkienmobs.utils.TTMUtilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import java.util.Random;

public class PotionTTMTerror extends TTMPotion {
    public static final String NAME = "crippling_fear";
    public static PotionTTMTerror instance = null;

    public PotionTTMTerror(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
        super(name, isBadEffectIn, liquidColorIn, iconIndex);
        instance = this;
    }

    @Override
    public void applyAttributesModifiersToEntity(EntityLivingBase entity, AbstractAttributeMap attributeMap, int amplifier)
    {
        super.applyAttributesModifiersToEntity(entity, attributeMap, amplifier);

        if(entity instanceof EntityLiving)
        {
            ((EntityLiving) entity).setNoAI(true);
        }

        entity.setSilent(true);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier)
    {
        if(this.canFreeze(entity))
        {
            Random random = entity.getRNG();
            boolean frozen = entity.isPotionActive(this);

            if (!frozen) {
                if (random.nextInt(TTMConfig.chanceTerror) == 0) {
                    if (entity instanceof EntityPlayer) {
                        entity.addPotionEffect(new PotionEffect(this, 100, amplifier));
                    } else {
                        entity.addPotionEffect(new PotionEffect(this, 300, amplifier));
                    }
                }
            }
            else
            {
                if(entity instanceof EntityPlayer)
                {
                    entity.setPosition(entity.prevPosX, entity.posY, entity.prevPosZ);
                }
                if(random.nextInt(TTMConfig.chanceNotAfraid) == 0)
                {
                    entity.removePotionEffect(this);
                }
            }
        }
    }

    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase entity, AbstractAttributeMap attributeMap, int amplifier)
    {
        super.removeAttributesModifiersFromEntity(entity, attributeMap, amplifier);

        if(entity instanceof EntityLiving)
        {
            if(((EntityLiving) entity).isAIDisabled())
            {
                ((EntityLiving) entity).setNoAI(false);
            }
        }

        entity.setSilent(false);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    public boolean canFreeze(EntityLivingBase entity)
    {
        if(entity instanceof EntityPlayer && !((EntityPlayer) entity).isCreative())
        {
            return TTMConfig.disablePlayerTerror;
        }

        String entityRegistryName = TTMUtilities.getEntityLocation(entity);
        return entityRegistryName != null;
    }
}