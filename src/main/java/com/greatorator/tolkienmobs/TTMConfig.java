package com.greatorator.tolkienmobs;

import codechicken.lib.config.ConfigTag;
import codechicken.lib.config.StandardConfigFile;
import com.google.common.collect.Lists;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon3055 on 31/1/21
 * <p>
 * So this config system is based on ConfigTag's A ConfigTag can ether be a container/category that contains child tags, or it can be an actual data tag that handles a config value.
 * <p>
 * Each of these can be thought of as a separate "config category" where 'config' is the root / parent category that contains
 * the client and server categories.
 * <p>
 * There is actually no difference between the server and config categories other than how you use them.
 * If you need to you can add as many additional categories and sub categories as you need.
 * <p>
 * The way I generally handle this is anything in the clientTag is only ever used client side.
 * Anything in the serverTag is used server side and can be synced to the client if required.
 * If I need to i will add sup tags to the clientTag or serverTag as needed.
 */
public class TTMConfig {

    private static ConfigTag config;
    private static ConfigTag clientTag;
    private static ConfigTag serverTag;

    public static void load() {
        config = new StandardConfigFile(Paths.get("./config/greatorator/TolkienMobs.cfg")).load();
        loadServer();
        loadClient();
//        EquipCfg.loadConfig(config); This is an example of how in DE I load additional configs in different locations because itr does not always make sense to have all your configs in one massive class.
//        ModuleCfg.loadConfig(config);
        config.runSync(); //This ensures that all configs are written to their static fields on load.
        config.save();
    }

    //Server properties
    public static int dimensionalWarpCoinCost;
    public static int blocksPerCoin;
    public static double milestoneCoinCostMultiplier;
    public static double minimumCoinCost;
    public static double maximumCoinCost;
    public static boolean dimensionalWarp;
    public static boolean disableFakePlayer;
    public static boolean replant;
    public static String exampleString;
    public static List<String> potionList;

    private static void loadServer() {
        serverTag = config.getTag("Server");
        serverTag.setComment("These are server side config properties.");

        serverTag.getTag("effectList")
                .setComment("Add or remove effect types for trinkets")
                .setDefaultStringList(Lists.newArrayList(potionTypeArray))
                .setSyncToClient()
                .setSyncCallback((tag, type) -> potionList = tag.getStringList());

        serverTag.getTag("blocksPerCoin")
                .setComment("Number of blocks (Default 1000)")
                .setDefaultInt(1000)
                .setSyncCallback((tag, type) -> blocksPerCoin = tag.getInt());

        serverTag.getTag("dimensionalWarpCoinCost")
                .setComment("Multiplier for dimensional teleport (Default 3)")
                .setDefaultInt(3)
                .setSyncCallback((tag, type) -> dimensionalWarpCoinCost = tag.getInt());

        serverTag.getTag("milestoneCoinCostMultiplier")
                .setComment("Multiplier for regular teleport (Default 1)")
                .setDefaultDouble(1)
                .setSyncCallback((tag, type) -> milestoneCoinCostMultiplier = tag.getDouble());

        serverTag.getTag("minimumCoinCost")
                .setComment("Minimum coin cost to teleport per (Default 1000) blocks (Default 5)")
                .setDefaultDouble(5)
                .setSyncCallback((tag, type) -> minimumCoinCost = tag.getDouble());

        serverTag.getTag("maximumCoinCost")
                .setComment("Maximum coin cost to teleport per (Default 1000) blocks (Default 50)")
                .setDefaultDouble(50)
                .setSyncCallback((tag, type) -> maximumCoinCost = tag.getDouble());

        serverTag.getTag("dimensionalWarp")
                .setComment("Enable Cross-dimensional Teleport between Milestones (Default true)")
                .setDefaultBoolean(true)
                .setSyncCallback((tag, type) -> dimensionalWarp = tag.getBoolean());

        serverTag.getTag("replant")
                .setComment("Crops will be replanted when harvested via right click. This requires a seed to drop, and is removed from the drop list. (Default true)")
                .setDefaultBoolean(true)
                .setSyncCallback((tag, type) -> replant = tag.getBoolean());

        serverTag.getTag("disableFakePlayer")
                .setComment("Disable fake player in TolkienMobs (Default false)")
                .setDefaultBoolean(false)
                .setSyncToClient()
                .setSyncCallback((tag, type) -> disableFakePlayer = tag.getBoolean());

        serverTag.getTag("exampleString")
                .setComment("This is an example string")
                .setDefaultString("Default Example String")
                .setSyncCallback((tag, type) -> exampleString = tag.getString());
    }

    //Client properties
    public static boolean HeartOverlay;
    public static List<String> dimensionList;

    private static void loadClient() {
        clientTag = config.getTag("Client");
        clientTag.setComment("These are client side config properties.");

        clientTag.getTag("HeartOverlay")
                .setComment("Enable Heart Overlay Feature - Default True")
                .setDefaultBoolean(true)
                .setSyncCallback((tag, type) -> HeartOverlay = tag.getBoolean());
        clientTag.getTag("noWind")
                .setComment("Add or remove effect types for trinkets")
                .setDefaultStringList(Lists.newArrayList(dimensionTypeArray))
                .setSyncToClient()
                .setSyncCallback((tag, type) -> dimensionList = tag.getStringList());
    }

    public static String[] potionTypeArray = new String[]{"tolkienmobs:blessing_of_eru", "tolkienmobs:elven_nimbleness", "tolkienmobs:ent_draught", "tolkienmobs:personal_blacksmith", "minecraft:absorption", "minecraft:invisibility", "minecraft:night_vision", "minecraft:speed", "minecraft:regeneration", "minecraft:jump_boost", "minecraft:haste", "minecraft:water_breathing", "minecraft:fire_resistance"};
    public static String[] dimensionTypeArray = new String[]{DimensionType.NETHER_LOCATION.location().toString(), DimensionType.END_LOCATION.location().toString()};


    public static Effect[] potionArray = new Effect[0];
    public static String[] dimensionArray = new String[0];

    public static void loadPotionList() {
        List<Effect> potions = new ArrayList<>();
        for (String name : potionTypeArray) {
            Effect potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(name));
            if (potion != null) {
                potions.add(potion);
            }
        }
        potionArray = potions.toArray(new Effect[0]);
    }

    public static void loadDimensionList() {
        List<String> dimensions = new ArrayList<>();
        for (String name : dimensionTypeArray) {
            if (name != null) {
                dimensions.add(name);
            }
        }
        dimensionArray = dimensions.toArray(new String[0]);
    }
}
