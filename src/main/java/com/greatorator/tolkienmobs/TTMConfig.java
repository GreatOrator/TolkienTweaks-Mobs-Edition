package com.greatorator.tolkienmobs;

import com.brandon3055.brandonscore.handlers.FileHandler;
import com.brandon3055.brandonscore.registry.IModConfigHelper;
import com.brandon3055.brandonscore.registry.ModConfigContainer;
import com.brandon3055.brandonscore.registry.ModConfigProperty;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brandon3055 on 31/10/18.
 */
@ModConfigContainer(modid = TolkienMobs.MODID)
public class TTMConfig implements IModConfigHelper {
    public static Map<String, String> comments = new HashMap<String, String>();

    static {
        comments.put("Mob Spawning", "Disable groups of mobs or all mobs using these settings.");
        comments.put("Aggressive (non-boss) Mobs", "Use these settings to disable any individual naturally spawning Hostile mobs.");
        comments.put("Boss Mobs", "Boss Mobs do not currently naturally spawn, \nuse these settings if you do not want them available in-game.");
        comments.put("Special Mobs", "Special Mobs do not currently naturally spawn, \nuse these settings if you do not want them available in-game.");
        comments.put("Passive Mobs", "Use these settings to disable any individual \nnaturally spawning Passive mobs. Note: This will potentially impact \nthe ability to get certain items as \nthey are only available from shop-keepers.");
        comments.put("Biomes", "Disable any or all Biomes added by this mod, some mobs \nwill only be available in these biomes, so if disabled it will \nautomatically make certain mobs unavailable.");
        comments.put("Potion Types", " This creates rings, charms, belts and amulets for \neach potion listed. They need to be added using the internal names like \nthe ones already listed.");
        comments.put("Miscellaneous Configs", " Enable or Disable various options for \nblocks in this mod,  For example, disabling the ability of \nhallowed earth to damage as a player.");
    }
    @Override
    public Configuration createConfiguration(FMLPreInitializationEvent event) {
        return new Configuration(new File(FileHandler.rootConfigFolder, "tolkienmobs.cfg"), true);
    }

    @Override
    public String getCategoryComment(String category) {
        return comments.getOrDefault(category, "");
    }

//    @ModConfigProperty(category = "Category for this property", name = "exampleBooleanConfig", comment = "Description for this config property")
//    public static boolean exampleBooleanConfig = true;

//    @ModConfigProperty(category = "Category for this property", name = "exampleIntConfig", comment = "Description for this config property")
//    public static int exampleIntConfig = 10000;

//    @ModConfigProperty(category = "Category for this property", name = "exampleIntWithMinMax", comment = "Description for this config property")
//    @ModConfigProperty.MinMax(min = "500", max = "1000000")
//    public static int exampleIntWithMinMax = 10000;

//    @ModConfigProperty(category = "Category for this property", name = "exampleIntArray", comment = "Description for this config property")
//    public static int[] exampleIntArray = new int[0];

//    @ModConfigProperty(category = "Category for this property", name = "exampleDouble", comment = "Description for this config property")
//    public static double exampleDouble = 1;

//    @ModConfigProperty(category = "Category for this property", name = "exampleString", comment = "Description for this config property")
//    public static String exampleString = "thermalfoundation";

//    @ModConfigProperty(category = "Category for this property", name = "exampleStringArray", comment = "Description for this config property")
//    public static String[] exampleStringArray = new String[]{"String 1", "String 2"};



    //Config sync examples + additional flags

    //This will silently sync when the player connects to a server and will be reset to its original state when they disconnect
//    @ModConfigProperty(category = "Category", name = "exampleSyncedProperty", comment = "Description", autoSync = true)
//    public static boolean exampleSyncedProperty = true;

    //This can be used where a config needs to be set on game launch. It will prompt the user to sync their config and then restart their game.
    //p.s requiresSync should not be used without requiresMCRestart because if the config does not require an MC restart there is no reason it can not be set to auto.
//    @ModConfigProperty(category = "Category", name = "exampleSyncedProperty2", comment = "Description", requiresSync = true, requiresMCRestart = true)
//    public static boolean exampleSyncedProperty2 = true;

    //Use this for properties that require an MC restart to apply
//    @ModConfigProperty(category = "Category", name = "exampleRestartProperty", comment = "Description", requiresMCRestart = true)
//    public static boolean exampleRestartProperty = true;

    /** Natural Spawning */
    @ModConfigProperty(category = "Mob Spawning", name = "enableNaturalSpawn", comment = "Setting this to false will disable ALL Spawning of this mod's mobs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableNaturalSpawn = true;

    /** Mobs by Category*/
    @ModConfigProperty(category = "Mob Spawning", name = "enablePassive", comment = "Setting this to false will disable ALL passive mobs", requiresMCRestart = true, requiresSync = true)
    public static boolean enablePassive = true;
    @ModConfigProperty(category = "Mob Spawning", name = "enableMonster", comment = "Setting this to false will disable ALL non-passive mobs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMonster = true;
    @ModConfigProperty(category = "Mob Spawning", name = "enableBoss", comment = "Setting this to false will disable ALL boss mobs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableBoss = true;
    @ModConfigProperty(category = "Mob Spawning", name = "enableSpecial", comment = "Setting this to false will disable ALL special mobs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableSpecial = true;

    /** Aggressive mobs - Individual*/
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableBarrowWights", comment = "Setting this to false will disable Barrow Wights", requiresMCRestart = true, requiresSync = true)
    public static boolean enableBarrowWights = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableCaveTrolls", comment = "Setting this to false will disable Cave Trolls", requiresMCRestart = true, requiresSync = true)
    public static boolean enableCaveTrolls = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableCrebain", comment = "Setting this to false will disable Crebain", requiresMCRestart = true, requiresSync = true)
    public static boolean enableCrebain = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableGoblins", comment = "Setting this to false will disable Goblins", requiresMCRestart = true, requiresSync = true)
    public static boolean enableGoblins = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableHurons", comment = "Setting this to false will disable Hurons", requiresMCRestart = true, requiresSync = true)
    public static boolean enableHurons = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableMirkwoodSpiders", comment = "Setting this to false will disable Mirkwood Spiders", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMirkwoodSpiders = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableMordorOrcs", comment = "Setting this to false will disable Mordor Orcs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMordorOrcs = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableTreeEnts", comment = "Setting this to false will disable Tree Ents", requiresMCRestart = true, requiresSync = true)
    public static boolean enableTreeEnts = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableUrukhai", comment = "Setting this to false will disable Urukhai", requiresMCRestart = true, requiresSync = true)
    public static boolean enableUrukhai = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableWargs", comment = "Setting this to false will disable Wargs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableWargs = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableOathbreaker", comment = "Setting this to false will disable Oathbreakers", requiresMCRestart = true, requiresSync = true)
    public static boolean enableOathbreaker = true;
    @ModConfigProperty(category = "Aggressive (non-boss) Mobs", name = "enableMidgeFlies", comment = "Setting this to false will disable Midge Flies", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMidgeFlies = true;

    /** Boss mobs - Individual*/
    @ModConfigProperty(category = "Boss Mobs", name = "enableBalrog", comment = "Setting this to false will disable the Balrog", requiresMCRestart = true, requiresSync = true)
    public static boolean enableBalrog = true;
    @ModConfigProperty(category = "Boss Mobs", name = "enableFellBeast", comment = "Setting this to false will disable the Fell Beast", requiresMCRestart = true, requiresSync = true)
    public static boolean enableFellBeast = true;
    @ModConfigProperty(category = "Boss Mobs", name = "enableWitchKing", comment = "Setting this to false will disable the Witch King", requiresMCRestart = true, requiresSync = true)
    public static boolean enableWitchKing = true;

    /** Special mobs - Individual*/
    @ModConfigProperty(category = "Special Mobs", name = "enableGollum", comment = "Setting this to false will disable Gollum", requiresMCRestart = true, requiresSync = true)
    public static boolean enableGollum = true;
    @ModConfigProperty(category = "Special Mobs", name = "enableNazgul", comment = "Setting this to false will disable the Nazgul", requiresMCRestart = true, requiresSync = true)
    public static boolean enableNazgul = true;

    /** Passive mobs - Individual*/
    @ModConfigProperty(category = "Passive Mobs", name = "enableAuroch", comment = "Setting this to false will disable Aurochs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableAuroch = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableDwarves", comment = "Setting this to false will disable Dwarves", requiresMCRestart = true, requiresSync = true)
    public static boolean enableDwarves = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableElves", comment = "Setting this to false will disable Elves", requiresMCRestart = true, requiresSync = true)
    public static boolean enableElves = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableGoats", comment = "Setting this to false will disable Goats", requiresMCRestart = true, requiresSync = true)
    public static boolean enableGoats = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableHobbits", comment = "Setting this to false will disable Hobbits", requiresMCRestart = true, requiresSync = true)
    public static boolean enableHobbits = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableHumans", comment = "Setting this to false will disable Humans", requiresMCRestart = true, requiresSync = true)
    public static boolean enableHumans = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableMumakil", comment = "Setting this to false will disable Mumakil", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMumakil = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableFrogs", comment = "Setting this to false will disable Frogs", requiresMCRestart = true, requiresSync = true)
    public static boolean enableFrogs = true;
    @ModConfigProperty(category = "Passive Mobs", name = "enableSquirrels", comment = "Setting this to false will disable Society Squirrel", requiresMCRestart = true, requiresSync = true)
    public static boolean enableSquirrels = true;

    /** Biomes */
    @ModConfigProperty(category = "Biomes", name = "enableBarrowDowns", comment = "Setting this to false will disable the Barrow Downs Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableBarrowDowns = true;
    @ModConfigProperty(category = "Biomes", name = "enableDagorlad", comment = "Setting this to false will disable the Dagorlad Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableDagorlad = true;
    @ModConfigProperty(category = "Biomes", name = "enableFangornForest", comment = "Setting this to false will disable the Fangorn Forest Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableFangornForest = true;
    @ModConfigProperty(category = "Biomes", name = "enableFirien", comment = "Setting this to false will disable the Firien Wood Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableFirien = true;
    @ModConfigProperty(category = "Biomes", name = "enableGladden", comment = "Setting this to false will disable the Gladden Fields Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableGladden = true;
    @ModConfigProperty(category = "Biomes", name = "enableHaradwaith", comment = "Setting this to false will disable the Haradwaith (Southlands) Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableHaradwaith = true;
    @ModConfigProperty(category = "Biomes", name = "enableHithaeglir", comment = "Setting this to false will disable the Hithaeglir (Misty Mountains) Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableHithaeglir = true;
    @ModConfigProperty(category = "Biomes", name = "enableIronHills", comment = "Setting this to false will disable the Iron Hills Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableIronHills = true;
    @ModConfigProperty(category = "Biomes", name = "enableLorinand", comment = "Setting this to false will disable the Lorinand (Lothlorien) Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableLorinand = true;
    @ModConfigProperty(category = "Biomes", name = "enableMirkwood", comment = "Setting this to false will disable the Mirkwood Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMirkwood = true;
    @ModConfigProperty(category = "Biomes", name = "enableMordor", comment = "Setting this to false will disable the Mordor Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableMordor = true;
    @ModConfigProperty(category = "Biomes", name = "enableShire", comment = "Setting this to false will disable the Shire Biome", requiresMCRestart = true, requiresSync = true)
    public static boolean enableShire = true;

    /** Miscellaneous Configs */
    @ModConfigProperty(category = "Miscellaneous Configs", name = "enablePlayerDMG", comment = "Setting to true will enable the fake player for hallowed earth", requiresMCRestart = true, requiresSync = true)
    public static boolean enablePlayerDMG = false;

    /** Potion Types */
    @ModConfigProperty(category = "Potion Types", name = "potionTypeArray", comment = "Add or remove potion types from this array.")
    public static String[] potionTypeArray = new String[]{"tolkienmobs:ent_draught", "minecraft:absorption", "minecraft:invisibility", "minecraft:night_vision", "minecraft:speed", "minecraft:regeneration", "minecraft:jump_boost", "minecraft:haste", "minecraft:water_breathing", "minecraft:glowing", "minecraft:fire_resistance"};

    public static Potion[] potionArray = new Potion[0];

    public static void loadPotionList() {
        List<Potion> potions = new ArrayList<>();
        for (String name : potionTypeArray) {
            Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(name));
            if (potion != null) {
                potions.add(potion);
            }
        }
        potionArray = potions.toArray(new Potion[0]);
    }

    @Override
    public void onConfigChanged(String propertyName, String propertyCategory) {
        loadPotionList();
    }
}