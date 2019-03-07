package com.greatorator.tolkienmobs;

import com.brandon3055.brandonscore.BrandonsCore;
import com.brandon3055.brandonscore.registry.ModFeatureParser;
import com.brandon3055.tolkientweaks.TolkienTweaks;
import com.greatorator.tolkienmobs.client.TTMobsTab;
import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.proxy.CommonProxy;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.utils.TTMDataFixes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TolkienMobs.MODID, name = TolkienMobs.NAME, version = TolkienMobs.VERSION, dependencies = TolkienMobs.DEPENDENCIES, guiFactory = TolkienMobs.GUI_FACTORY)
public class TolkienMobs {
    //Static mod fields
    public static final String MODID = "tolkienmobs";
    public static final String NAME = "Tolkien Tweaks (Mobs Edition)";
    public static final String VERSION = "${mod_version}"; //This will now be set automatically by the build.gradle when the jar is built.
    public static final String CLIENT_PROXY = "com.greatorator.tolkienmobs.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.greatorator.tolkienmobs.proxy.CommonProxy";
    public static final String DEPENDENCIES = "required-after:brandonscore@[" + BrandonsCore.VERSION + ",);required-after:tolkientweaks@[" + TolkienTweaks.VERSION + ",);"; //Will depend on the version of BC that the project is built against
    public static final String GUI_FACTORY = "com.greatorator.tolkienmobs.client.TTMGuiFactory"; //Using BC's config system this is all you need to add an in game mod config gui.

    @Instance
    public static TolkienMobs instance;

    @SidedProxy(clientSide = TolkienMobs.CLIENT_PROXY, serverSide = TolkienMobs.COMMON_PROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabToolsArmor = new TTMobsTab("tools", () -> TTMFeatures.AXE_MITHRIL);
    public static CreativeTabs tabWorldMats = new TTMobsTab("mats", () -> TTMFeatures.INGOT_MITHRIL);
    public static CreativeTabs tabMobsSpawn = new TTMobsTab.SpawnTab("spawn", () -> Items.SPAWN_EGG);
    public static CreativeTabs tabFoodItems = new TTMobsTab("food", () -> TTMFeatures.LEMBAS);
    public static CreativeTabs tabQuestItems = new TTMobsTab("quest", () -> TTMFeatures.ITEM_FORTRESSMAP);
    public static CreativeTabs tabSignItems = new TTMobsTab("signs", () -> Items.SIGN);

    private final DataFixer dataFixer;

    public TolkienMobs() {
        new PotionInit();
        Logger ttLog = LogManager.getLogger("tolkientweaks");
        Logger bcLog = LogManager.getLogger("brandonscore");
        LogHelperTTM.info("Meeting of the Fellowship started! Waiting for the rest of the party to arrive...");
        if (Loader.isModLoaded("tolkientweaks")) {
            ttLog.log(Level.INFO, "You shall have my axe!");
            bcLog.log(Level.INFO, "...and you shall have my bow!");
            LogHelperTTM.info("Together we shall be the Fellowship of the Mods!");
        }
        else {
            ttLog.log(Level.INFO, "...");
            LogHelperTTM.info("No party, no play!");
        }
        this.dataFixer = TTMDataFixes.createFixer();
    }

    public DataFixer getDataFixer()
    {
        return this.dataFixer;
    }
    //I like to run the init events through the proxy because for one thing it makes your main class a lot cleaner
    //And it also makes it dead simple to manage client/server side code

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModFeatureParser.registerModFeatures(MODID);//This is a call to let BC know when its time to register our stuff.
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
        proxy.serverRegistries(event);
    }
}