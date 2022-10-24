package com.greatorator.tolkienmobs.item.armor;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.ResourceLocation;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MithrilHorseArmor extends HorseArmorItem {
        public MithrilHorseArmor(int armorValue, String tierArmor, Properties properties) {
        super(armorValue, new ResourceLocation(MODID,"textures/models/armor/" + tierArmor + "_horse_armor.png"), properties);
    }
}
