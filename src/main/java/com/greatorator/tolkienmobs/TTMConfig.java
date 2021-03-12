package com.greatorator.tolkienmobs;

import codechicken.lib.config.ConfigTag;
import codechicken.lib.config.StandardConfigFile;
import com.google.common.collect.Lists;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

/**
 * Created by brandon3055 on 31/1/21
 * <p>
 * So this config system is based on 'ConfigTag's A ConfigTag can ether be a container/category that contains child tags or it can be an actual data tag that handles a config value.
 * <p>
 * Each of these can be thought of as a separate "config category" where 'config' is the root / parent category that contains
 * the client and server categories.
 * <p>
 * There is actually no difference between the server and config categories other than how you use them.
 * If you need to you can add as many additional categories and sub categories as you need.
 * <p>
 * The way i generally handle this is anything in the clientTag is only ever used client side.
 * Anything in the serverTag is used server side and can be synced to the client if required.
 * If i need to i will add sup tags to the clientTag or serverTag as needed.
 */
public class TTMConfig {

    private static ConfigTag config;
    private static ConfigTag clientTag;
    private static ConfigTag serverTag;

    public static void load() {
        config = new StandardConfigFile(Paths.get("./config/greatorator/TolkienMobs.cfg")).load();
        loadServer();
        loadClient();
//        EquipCfg.loadConfig(config); This is an example of how in DE i load additional configs in different locations because itr does not always make sense to have all your configs in one massive class.
//        ModuleCfg.loadConfig(config);
        config.runSync(); //This ensures that all configs are written to their static fields on load.
        config.save();
    }

    //Server properties
    public static List<String> potionList;
    public static int spawnChance;
    public static boolean disableVanilla;
    public static boolean disableTolkienMobs;
    public static boolean disableFakePlayer;
    public static String exampleString;
    public static double exampleDouble;

    private static void loadServer() {
        serverTag = config.getTag("Server");
        serverTag.setComment("These are server side config properties.");

        serverTag.getTag("effectList")
                .setComment("Add or remove effect types for trinkets")
                .setDefaultStringList(Lists.newArrayList(potionTypeArray))
                .setSyncToClient()
                .setSyncCallback((tag, type) -> potionList = tag.getStringList());

        serverTag.getTag("spawnChance")
                .setComment("Chance to spawn mob out of 100 (Default 10)")
                .setDefaultInt(10)
                .setSyncCallback((tag, type) -> spawnChance = tag.getInt());

//        serverTag.getTag("disableVanilla")
//                .setComment("Disable Spawning of TolkienMobs (Default False)")
//                .setDefaultBoolean(false)
//                .setSyncToClient()
//                .setSyncCallback((tag, type) -> disableVanilla = tag.getBoolean());
//
//        serverTag.getTag("disableTolkienMobs")
//                .setComment("Disable Spawning of vanilla hostile mobs (Default True)")
//                .setDefaultBoolean(true)
//                .setSyncToClient()
//                .setSyncCallback((tag, type) -> disableTolkienMobs = tag.getBoolean());
//
//        serverTag.getTag("disableFakePlayer")
//                .setComment("Disable fake player in TolkienMobs (Default True)")
//                .setDefaultBoolean(false)
//                .setSyncToClient()
//                .setSyncCallback((tag, type) -> disableFakePlayer = tag.getBoolean());

        serverTag.getTag("exampleString")
                .setComment("This is an example string")
                .setDefaultString("Default Example String")
                .setSyncCallback((tag, type) -> exampleString = tag.getString());

        serverTag.getTag("exampleDouble")
                .setComment("This is an example double")
                .setDefaultDouble(99)
                .setSyncCallback((tag, type) -> exampleDouble = tag.getDouble());
    }

    //Client properties
    public static boolean HeartOverlay;

    private static void loadClient() {
        clientTag = config.getTag("Client");
        clientTag.setComment("These are client side config properties.");

        clientTag.getTag("HeartOverlay")
                .setComment("Enable Heart Overlay Feature - Default True")
                .setDefaultBoolean(true)
                .setSyncCallback((tag, type) -> HeartOverlay = tag.getBoolean());
    }

    public static String[] potionTypeArray = new String[]{"tolkienmobs:blessing_of_eru", "tolkienmobs:elven_nimbleness", "tolkienmobs:ent_draught", "tolkienmobs:personal_blacksmith", "minecraft:absorption", "minecraft:invisibility", "minecraft:night_vision", "minecraft:speed", "minecraft:regeneration", "minecraft:jump_boost", "minecraft:haste", "minecraft:water_breathing", "minecraft:fire_resistance"};

    public static Effect[] potionArray = new Effect[0];

    public static void loadPotionList() {
        List<Effect> potions = new ArrayList<>();
        for (String name : potionTypeArray) {
            Effect potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(name));
            if (potion != null) {
                LOGGER.info(potion);
                potions.add(potion);
            }
        }
        potionArray = potions.toArray(new Effect[0]);
    }
}
