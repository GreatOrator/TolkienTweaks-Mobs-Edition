package com.greatorator.tolkienmobs;

import codechicken.lib.config.ConfigTag;
import codechicken.lib.config.StandardConfigFile;
import com.google.common.collect.Lists;

import java.nio.file.Paths;
import java.util.List;

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
        config = new StandardConfigFile(Paths.get("./config/brandon3055/DraconicEvolution.cfg")).load();
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
    public static int exampleInt;
    public static List<Integer> exampleIntList;

    private static void loadClient() {
        clientTag = config.getTag("Client");
        clientTag.setComment("These are client side config properties.");

        clientTag.getTag("exampleInt")
                .setComment("This is an example integer")
                .setDefaultInt(4261)
                .setSyncCallback((tag, type) -> exampleInt = tag.getInt());

        clientTag.getTag("exampleIntList")
                .setComment("This is an example Int List")
                .setDefaultIntList(Lists.newArrayList(4261, 3055, 1624))
                .setSyncCallback((tag, type) -> exampleIntList = tag.getIntList());
    }
}
