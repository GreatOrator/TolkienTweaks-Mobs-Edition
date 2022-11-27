package com.greatorator.tolkienmobs;


import com.greatorator.tolkienmobs.integration.IntegrationHelper;
import com.greatorator.tolkienmobs.proxy.ClientProxy;
import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Locale;

@Mod(TolkienMobs.MODID)
public class TolkienMobs {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public static final String MODID = "tolkienmobs";
    public static final String NAME = "Tolkien Tweaks (Mobs Edition)";
    public static final String VERSION = "${mod_version}";
    private static TolkienMobs instance;
    public static SimpleChannel NETWORK;
    private HashMap<String, Long> modifiedPlayerTimes;

    public static CommonProxy proxy;

    /*TODO List
	1. Classes & Wish list
        a. Recipe Generator not creating trinket recipes
        b. Spawner not rendering mob in list
	    c. EntityLootGenerator (Attempting to process loot for other mods in workspace)
	    d. Armor texture rendering incorrectly
	    e. Tornado Effect not working
		f. BackPack fluid handler capability crash
	    g. Trinket Names not translating properly
	    h. FirePlace not processing recipes
	    i. Trinket Table not processing recipes
	    j. Hobbit Growth not "bonemealing"
	    k.
    2. Categories
	    a. Entities (Implementation)
	    	1. Fell Beast - Needs AI adjustments
			2. Gwaihir - Needs AI adjustments
			3. Great Eagle - Needs AI adjustments
        b. World Generation (Implementation)
            a. Add Fell Beast to dark tower structure and Morgul Crystal
            b. Possible village generation using house structures by race type
            c. Root systems on trees (mallorn, fangornoak and mirkwood)
    3. Stuff Added
        a. Lightning Bug
        b. Lanterns
        c. Coin Pouch and Key Ring will now auto collect items if activated
        d. new potion base for this mod's potions
        e. Deepslate versions of ore
        f. Barrels for all wood types
        g. Added blocks to mineable with tools
        h. Added Mithril and Morgul Iron walls
        i. Ammolite Panes
        j. Refactoring of tags to fix some block weirdness
        k. Trinket Table (Block only so far)
        l. Random trinkets for loot drops/chests
    */

    public TolkienMobs() {
        instance = this;
        modifiedPlayerTimes = new HashMap<>();

        synchronized (MinecraftForge.EVENT_BUS) {
            Logger ccLog = LogManager.getLogger("codechickenlib");
            Logger bcLog = LogManager.getLogger("brandonscore");
            Logger ttLog = LogManager.getLogger("tolkienmobs");
            LOGGER.info("Meeting of the Fellowship started! Waiting for the rest of the party to arrive...");
            if (IntegrationHelper.isCCInstalled) {
                ccLog.log(Level.INFO, "You shall have my axe!");
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

    public static TolkienMobs instance() {
        return instance;
    }

    public static ResourceLocation createRL(String name) {
        if (name.contains(":")) {
            return new ResourceLocation(name);
        }
        return new ResourceLocation(MODID, name);
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