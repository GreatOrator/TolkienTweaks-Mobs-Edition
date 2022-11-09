package com.greatorator.tolkienmobs.init;

import net.minecraft.world.level.block.state.properties.WoodType;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienWoodTypes {
    public static WoodType MALLORN = WoodType.register(WoodType.create(MODID + ":mallorn"));
    public static WoodType MIRKWOOD = WoodType.register(WoodType.create(MODID + ":mirkwood"));
    public static WoodType CULUMALDA = WoodType.register(WoodType.create(MODID + ":culumalda"));
    public static WoodType LEBETHRON = WoodType.register(WoodType.create(MODID + ":lebethron"));
    public static WoodType DEADWOOD = WoodType.register(WoodType.create(MODID + ":deadwood"));
    public static WoodType FANGORNOAK = WoodType.register(WoodType.create(MODID + ":fangornoak"));

    public String getName() {
        return NAME + " - Wood Types";
    }
}
