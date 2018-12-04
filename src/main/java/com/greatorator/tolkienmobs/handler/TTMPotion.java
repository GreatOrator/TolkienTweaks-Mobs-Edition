package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class TTMPotion extends Potion {

    public TTMPotion(String name, Boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName("effect." + name);
        setIconIndex(IconIndexX, IconIndexY);
        setRegistryName(new ResourceLocation(TolkienMobs.MODID + ":" + name));
    }

    @Override
    public boolean hasStatusIcon(){
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(TolkienMobs.MODID + "textures/gui/potion_effects.png"));
        return true;
    }
}
