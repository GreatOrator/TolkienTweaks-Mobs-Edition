package com.greatorator.tolkienmobs;

import com.brandon3055.brandonscore.handlers.FileHandler;
import com.brandon3055.brandonscore.registry.IModConfigHelper;
import com.brandon3055.brandonscore.registry.ModConfigContainer;
import com.brandon3055.brandonscore.registry.ModConfigProperty;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created by brandon3055 on 31/10/18.
 */
@ModConfigContainer(modid = TolkienMobs.MODID)
public class TTMConfig implements IModConfigHelper {
    @Override
    public Configuration createConfiguration(FMLPreInitializationEvent event) {
        return new Configuration(new File(FileHandler.rootConfigFolder, "tolkienmobs.cfg"), true);
    }

    @Override
    public String getCategoryComment(String category) {
        return ""; //Use this to provide a custom description for a config category
    }

    @Override
    public void onConfigChanged(String propertyName, String propertyCategory) {
        //Use this to detect runtime config changes made by the user (if required)
    }

    @ModConfigProperty(category = "Category for this property", name = "exampleBooleanConfig", comment = "Description for this config property")
    public static boolean exampleBooleanConfig = true;

    @ModConfigProperty(category = "Category for this property", name = "exampleIntConfig", comment = "Description for this config property")
    public static int exampleIntConfig = 10000;

    @ModConfigProperty(category = "Category for this property", name = "exampleIntWithMinMax", comment = "Description for this config property")
    @ModConfigProperty.MinMax(min = "500", max = "1000000")
    public static int exampleIntWithMinMax = 10000;

    @ModConfigProperty(category = "Category for this property", name = "exampleIntArray", comment = "Description for this config property")
    public static int[] exampleIntArray = new int[0];

    @ModConfigProperty(category = "Category for this property", name = "exampleDouble", comment = "Description for this config property")
    public static double exampleDouble = 1;

    @ModConfigProperty(category = "Category for this property", name = "exampleString", comment = "Description for this config property")
    public static String exampleString = "thermalfoundation";

    @ModConfigProperty(category = "Category for this property", name = "exampleStringArray", comment = "Description for this config property")
    public static String[] exampleStringArray = new String[]{"String 1", "String 2"};



    //Config sync examples + additional flags

    //This will silently sync when the player connects to a server and will be reset to its original state when they disconnect
    @ModConfigProperty(category = "Category", name = "exampleSyncedProperty", comment = "Description", autoSync = true)
    public static boolean exampleSyncedProperty = true;

    //This can be used where a config needs to be set on game launch. It will prompt the user to sync their config and then restart their game.
    //p.s requiresSync should not be used without requiresMCRestart because if the config does not require an MC restart there is no reason it can not be set to auto.
    @ModConfigProperty(category = "Category", name = "exampleSyncedProperty2", comment = "Description", requiresSync = true, requiresMCRestart = true)
    public static boolean exampleSyncedProperty2 = true;

    //Use this for properties that require an MC restart to apply
    @ModConfigProperty(category = "Category", name = "exampleRestartProperty", comment = "Description", requiresMCRestart = true)
    public static boolean exampleRestartProperty = true;

    //Use this for properties that require a world restart to apply
    @ModConfigProperty(category = "Category", name = "exampleRestartProperty2", comment = "Description", requiresWorldRestart = true)
    public static boolean exampleRestartProperty2 = true;
}
