package com.greatorator.tolkienmobs;


import com.greatorator.tolkienmobs.proxy.ClientProxy;
import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(TolkienMobs.MODID)
public class TolkienMobs {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public static final String MODID = "tolkienmobs";
    public static final String NAME = "Tolkien Tweaks (Mobs Edition)";
    public static final String VERSION = "${mod_version}"; //This will now be set automatically by the build.gradle when the jar is built.

    public static CommonProxy proxy;

    /*TODO List
    * Registration Stuff
    *  - Signs
    *  - Entities
    *  - Ammunition
    *  - Fireplace
    *  - Potions - 3 left
    *  - Enchants - 4 left
    *  - Trinkets
    *  - Armor
    *  - Generation
    *   - Trees
    *   - Biomes
    *   - Structures
    *   - Plants
    *
    * Data Generators
    *  - Tags
    *
    * Config (Done)
    * Network
    */

    public TolkienMobs() {
        synchronized (MinecraftForge.EVENT_BUS) {
            Logger ttLog = LogManager.getLogger("tolkientweaks");
            Logger bcLog = LogManager.getLogger("brandonscore");
            LOGGER.info("Meeting of the Fellowship started! Waiting for the rest of the party to arrive...");
            if (ModList.get().isLoaded("tolkientweaks")) {
                ttLog.log(Level.INFO, "You shall have my axe!");
                bcLog.log(Level.INFO, "...and you shall have my bow!");
                LOGGER.info("Together we shall be the Fellowship of the Mods!");
            } else {
                ttLog.log(Level.INFO, "...");
                LOGGER.info("No party, no play!");
            }
        }

        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.construct();

        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        proxy.commonSetup(event);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        proxy.clientSetup(event);
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }

}

//@Mod(modid = TolkienMobs.MODID, name = TolkienMobs.NAME, version = TolkienMobs.VERSION, dependencies = TolkienMobs.DEPENDENCIES, guiFactory = TolkienMobs.GUI_FACTORY)
//public class TolkienMobs {

//    public static CreativeTabs tabToolsArmor = new TTMobsTab("tools", () -> TTMFeatures.AXE_MITHRIL);
//    public static CreativeTabs tabWorldMats = new TTMobsTab("mats", () -> TTMFeatures.INGOT_MITHRIL);
//    public static CreativeTabs tabMobsSpawn = new TTMobsTab.SpawnTab("spawn", () -> Items.SPAWN_EGG);
//    public static CreativeTabs tabFoodItems = new TTMobsTab("food", () -> TTMFeatures.LEMBAS);
//    public static CreativeTabs tabQuestItems = new TTMobsTab("quest", () -> TTMFeatures.ITEM_FORTRESSMAP);
//    public static CreativeTabs tabSignItems = new TTMobsTab("signs", () -> Items.SIGN);
//
//    private final DataFixer dataFixer;
//    public static SimpleNetworkWrapper networkWrapper;
//
//    static {
//        FluidRegistry.enableUniversalBucket();
//    }
//
//    public TolkienMobs() {
//        new PotionInit();
//        new SoundInit();


//        this.dataFixer = TTMDataFixes.createFixer();
//    }
//
//    public DataFixer getDataFixer()
//    {
//        return this.dataFixer;
//    }
//    //I like to run the init events through the proxy because for one thing it makes your main class a lot cleaner
//    //And it also makes it dead simple to manage client/server side code
//
//    @EventHandler
//    public void preInit(FMLPreInitializationEvent event) {
//        ModFeatureParser.registerModFeatures(MODID);//This is a call to let BC know when its time to register our stuff.
//        TTMGUIHandler.initialize();
//        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(TolkienMobs.MODID);
//        networkWrapper.registerMessage(TTMPacketClient.class, TTMStoCMessage.class, 1, Side.CLIENT);
//        proxy.preInit(event);
//    }
//
//    @EventHandler
//    public void init(FMLInitializationEvent event) {
//        proxy.init(event);
//    }
//
//    @EventHandler
//    public void postInit(FMLPostInitializationEvent event) {
//        proxy.postInit(event);
//    }
//
//    @EventHandler
//    public static void serverInit(FMLServerStartingEvent event)
//    {
//        proxy.serverRegistries(event);
//    }
//}