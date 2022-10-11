package com.greatorator.tolkienmobs.integration;

import net.minecraftforge.fml.ModList;

public class TTMHelper {
    public static boolean isCuriosInstalled;
    public static boolean isJEIInstalled;
    public static boolean isMantleInstalled;
    public static boolean isTTInstalled;
    public static boolean isBCInstalled;

    public static void init(){
        isCuriosInstalled = ModList.get().isLoaded("curios");
        isJEIInstalled = ModList.get().isLoaded("jei");
        isMantleInstalled = ModList.get().isLoaded("mantle");
        isTTInstalled = ModList.get().isLoaded("tolkientweaks");
        isBCInstalled = ModList.get().isLoaded("brandonscore");
    }

}
