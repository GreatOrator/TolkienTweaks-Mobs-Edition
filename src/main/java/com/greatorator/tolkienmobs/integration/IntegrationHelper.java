package com.greatorator.tolkienmobs.integration;

import net.minecraftforge.fml.ModList;

public class IntegrationHelper {
    public static boolean isCuriosInstalled;
    public static boolean isJEIInstalled;
    public static boolean isMantleInstalled;
    public static boolean isTCONInstalled;
    public static boolean isCCInstalled;
    public static boolean isBCInstalled;

    public static void init(){
        isCuriosInstalled = ModList.get().isLoaded("curios");
        isJEIInstalled = ModList.get().isLoaded("jei");
        isTCONInstalled = ModList.get().isLoaded("tconstruct");
        isMantleInstalled = ModList.get().isLoaded("mantle");
        isCCInstalled = ModList.get().isLoaded("codechickenlib");
        isBCInstalled = ModList.get().isLoaded("brandonscore");
    }

}
