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
    public static String exampleString;
    public static double exampleDouble;
    public static boolean exampleBoolean;

    private static void loadServer() {
        serverTag = config.getTag("Server");
        serverTag.setComment("These are server side config properties.");

        serverTag.getTag("effectList")
                .setComment("Add or remove effect types for trinkets")
                .setDefaultStringList(Lists.newArrayList(potionTypeArray))
                .setSyncToClient()
                .setSyncCallback((tag, type) -> potionList = tag.getStringList());

        serverTag.getTag("exampleInt")
                .setComment("This is an example integer")
                .setDefaultInt(4261)
                .setSyncCallback((tag, type) -> exampleInt = tag.getInt());

        ConfigTag exampleTag = serverTag.getTag("exampleString")
                .setComment("This is an example string")
                .setDefaultString("Default Example String")
                .setSyncCallback((tag, type) -> exampleString = tag.getString());
        //The sync call back is what we use to automatically apply this to our static field whenever the config is loaded or synchronized.
        //You can manually read the value from the tag but this is just simpler.
        //You can also technically assign each config tag to a field and access it via that field but this is much less efficient
        exampleString = exampleTag.getString(); //<- So you could do this instead of using setSyncCallback but i prefer setSyncCallback

        serverTag.getTag("exampleDouble")
                .setComment("This is an example double")
                .setDefaultDouble(99)
                .setSyncCallback((tag, type) -> exampleDouble = tag.getDouble());

        serverTag.getTag("exampleBoolean")
                .setComment("This is an example boolean")
                .setDefaultBoolean(false)
                .setSyncToClient() // <- Adding this will make this config sync from the server to the client when the client connects. The client client will automatically revert when the client disconnects.
                .setSyncCallback((tag, type) -> exampleBoolean = tag.getBoolean());
    }

    //Client properties
    public static boolean HeartOverlay;
    public static int exampleInt;
    public static List<Integer> exampleIntList;
    public static List<String> potionList;

    private static void loadClient() {
        clientTag = config.getTag("Client");
        clientTag.setComment("These are client side config properties.");

        clientTag.getTag("HeartOverlay")
                .setComment("Enable Heart Overlay Feature - Default True")
                .setDefaultBoolean(true)
                .setSyncCallback((tag, type) -> HeartOverlay = tag.getBoolean());

        clientTag.getTag("exampleInt")
                .setComment("This is an example integer")
                .setDefaultInt(4261)
                .setSyncCallback((tag, type) -> exampleInt = tag.getInt());

        clientTag.getTag("exampleIntList")
                .setComment("This is an example Int List")
                .setDefaultIntList(Lists.newArrayList(4261, 3055, 1624))
                .setSyncCallback((tag, type) -> exampleIntList = tag.getIntList());
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
