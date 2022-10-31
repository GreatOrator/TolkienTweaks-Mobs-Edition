package com.greatorator.tolkienmobs.handler;

import net.minecraft.world.level.block.state.properties.WoodType;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienWoodTypes {
    public static WoodType MALLORN = WoodType.register(WoodType.create(MODID + ":mallorn"));
    public static WoodType MIRKWOOD = WoodType.register(WoodType.create(MODID + ":mirkwood"));
    public static WoodType CULUMALDA = WoodType.register(WoodType.create(MODID + ":culumalda"));
    public static WoodType LEBETHRON = WoodType.register(WoodType.create(MODID + ":lebethron"));
}
