package com.greatorator.tolkienmobs.item.armor;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MorgulironHorseArmor extends HorseArmorItem {
    public MorgulironHorseArmor(int armorValue, String tierArmor, Properties properties) {
        super(armorValue, new ResourceLocation(MODID,"textures/models/armor/" + tierArmor + "_horse_armor.png"), properties);
    }
}