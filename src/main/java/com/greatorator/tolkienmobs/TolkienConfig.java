package com.greatorator.tolkienmobs;

import codechicken.lib.config.ConfigCallback;
import codechicken.lib.config.ConfigCategory;
import codechicken.lib.config.ConfigFile;
import codechicken.lib.config.ConfigValue;
import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

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
public class TolkienConfig {

    private static ConfigCategory config;
    private static ConfigCategory clientTag;
    private static ConfigCategory serverTag;

    public static void load() {
        config = new ConfigFile(MODID)
                .path(Paths.get("./config/greatorator/TolkienMobs.cfg"))
                .load();
        loadServer();
        loadClient();
//        EquipCfg.loadConfig(config); This is an example of how in DE I load additional configs in different locations because itr does not always make sense to have all your configs in one massive class.
//        ModuleCfg.loadConfig(config);
        config.runSync(ConfigCallback.Reason.MANUAL); //This ensures that all configs are written to their static fields on load.
        config.save();
    }

    //Server properties
    public static String serverID;
    public static String traderCostItem;
    public static int coinCost;
    public static int dimensionalWarp;
    public static double traderCostMultiplier;
    public static boolean disableFakePlayer;
    public static boolean replant;
    public static boolean disableBalrogMark;
    public static boolean disableDwarvenEndurance;
    public static boolean disableElvenLongevity;
    public static boolean disableGondorResolve;
    public static boolean disableHobbitHarvest;
    public static boolean disableHobbitPlow;
    public static boolean disableHobbitGrowth;

    public static List<String> potionList;

    private static void loadServer() {
        serverTag = config.getCategory("Server");
        ConfigValue serverIDTag = serverTag.getValue("serverID")
                .syncTagToClient()
                .setComment("This is a randomly generated id that clients will use to map their tool config settings to this server.")
                .setDefaultString(UUID.randomUUID().toString());
        serverIDTag.onSync((tag, type) -> serverID = tag.getString());
        serverTag.setComment("These are server side config properties.");

        serverTag.getValueList("effectList")
                .setComment("Add or remove effect types for trinkets")
                .setDefaultStrings(Lists.newArrayList(potionTypeArray))
                .syncTagToClient()
                .onSync((tag, type) -> potionList = tag.getStrings());

        serverTag.getValue("traderCostItem")
                .setComment("Set item for Desert Nomad for ask for purchases (Default tolkienmobs:item_coin_bronze")
                .setDefaultString("tolkienmobs:item_coin_bronze")
                .syncTagToClient()
                .onSync((tag, type) -> traderCostItem = tag.getString());

        serverTag.getValue("traderCostMultiplier")
                .setComment("Set item for Desert Nomad for ask for purchases (Default tolkienmobs:item_coin_bronze")
                .setDefaultDouble(1.0D)
                .syncTagToClient()
                .onSync((tag, type) -> traderCostMultiplier = tag.getDouble());

        serverTag.getValue("dimensionalWarpCoinCost")
                .setComment("Cost multiplier for dimensional teleport (Default 3)")
                .setDefaultInt(3)
                .onSync((tag, type) -> dimensionalWarp = tag.getInt());

        serverTag.getValue("coinCost")
                .setComment("Cost to teleport per (Default 500) blocks")
                .setDefaultInt(500)
                .onSync((tag, type) -> coinCost = tag.getInt());

        serverTag.getValue("replant")
                .setComment("Crops will be replanted when harvested via right click. This requires a seed to drop, and is removed from the drop list. (Default true)")
                .setDefaultBoolean(true)
                .onSync((tag, type) -> replant = tag.getBoolean());

        serverTag.getValue("disableFakePlayer")
                .setComment("Disable fake player for Hallowed Earth (Default false)")
                .setDefaultBoolean(false)
                .syncTagToClient()
                .onSync((tag, type) -> disableFakePlayer = tag.getBoolean());

        serverTag.setComment("Enchantment Configs");
        serverTag.getValue("disableBalrogMark")
                .setComment("Enable Balrog Mark Enchantment (Default true)")
                .setDefaultBoolean(true)
                .syncTagToClient()
                .onSync((tag, type) -> disableBalrogMark = tag.getBoolean());
        serverTag.getValue("disableDwarvenEndurance")
                .setComment("Enable Dwarven Endurance Enchantment (Default true)")
                .setDefaultBoolean(true)
                .syncTagToClient()
                .onSync((tag, type) -> disableDwarvenEndurance = tag.getBoolean());
        serverTag.getValue("disableElvenLongevity")
                .setComment("Enable Elven Longevity Enchantment (Default true)")
                .setDefaultBoolean(true)
                .syncTagToClient()
                .onSync((tag, type) -> disableElvenLongevity = tag.getBoolean());
        serverTag.getValue("disableGondorResolve")
                .setComment("Enable Gondor Resolve Enchantment (Default true)")
                .setDefaultBoolean(true)
                .syncTagToClient()
                .onSync((tag, type) -> disableGondorResolve = tag.getBoolean());
        serverTag.getValue("disableHobbitHarvest")
                .setComment("Enable Hobbit Harvest Enchantment (Default true)")
                .setDefaultBoolean(true)
                .syncTagToClient()
                .onSync((tag, type) -> disableHobbitHarvest = tag.getBoolean());
        serverTag.getValue("disableHobbitPlow")
                .setComment("Enable Hobbit Plow Enchantment (Default true)")
                .setDefaultBoolean(true)
                .syncTagToClient()
                .onSync((tag, type) -> disableHobbitPlow = tag.getBoolean());
    }

    //Client properties
    public static boolean HeartOverlay;

    private static void loadClient() {
        clientTag = config.getCategory("Client");
        clientTag.setComment("These are client side config properties.");

        clientTag.getValue("HeartOverlay")
                .setComment("Enable Heart Overlay Feature - Default True")
                .setDefaultBoolean(true)
                .onSync((tag, type) -> HeartOverlay = tag.getBoolean());
    }

    public static String[] potionTypeArray = new String[]{"tolkienmobs:blessing_of_eru", "tolkienmobs:elven_nimbleness", "tolkienmobs:ent_draught", "tolkienmobs:personal_blacksmith", "minecraft:absorption", "minecraft:invisibility", "minecraft:night_vision", "minecraft:speed", "minecraft:regeneration", "minecraft:jump_boost", "minecraft:haste", "minecraft:water_breathing", "minecraft:fire_resistance"};


    public static MobEffect[] potionArray = new MobEffect[0];

    public static void loadPotionList() {
        List<MobEffect> potions = new ArrayList<>();
        for (String name : potionTypeArray) {
            MobEffect potion = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(name));
            if (potion != null) {
                potions.add(potion);
            }
        }
        potionArray = potions.toArray(new MobEffect[0]);
    }
}
