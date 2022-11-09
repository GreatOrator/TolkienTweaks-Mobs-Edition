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
	1. Classes
	    a. TrinketRecipeBuilder (Builder to control building of trinket recipes)
	    b. TrinketRecipe (Recipes for trinkets to add potions)
	    c. BackpackItem (Allow to open GUI instead of only using block form)
	    d. EntityLootGenerator (Attempting to process loot for other mods in workspace)
	2. Categories
	    a. Entities (Implementation)
	    	1. Fell Beast - Needs AI adjustments
			2. Gwaihir - Needs AI adjustments
			3. Great Eagle - Needs AI adjustments
        b. World Generation (Implementation)
    */

    public TolkienMobs() {
        instance = this;
        modifiedPlayerTimes = new HashMap<>();

        synchronized (MinecraftForge.EVENT_BUS) {
            Logger ttLog = LogManager.getLogger("tolkientweaks");
            Logger bcLog = LogManager.getLogger("brandonscore");
            LOGGER.info("Meeting of the Fellowship started! Waiting for the rest of the party to arrive...");
            if (IntegrationHelper.isTTInstalled) {
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

    public static TolkienMobs instance() {
        return instance;
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