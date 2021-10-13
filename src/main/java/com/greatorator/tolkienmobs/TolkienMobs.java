package com.greatorator.tolkienmobs;


import com.greatorator.tolkienmobs.common.MobModify;
import com.greatorator.tolkienmobs.common.network.AirPacket;
import com.greatorator.tolkienmobs.common.network.NetworkHelper;
import com.greatorator.tolkienmobs.proxy.ClientProxy;
import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
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

import java.util.HashMap;
import java.util.Locale;

@Mod(TolkienMobs.MODID)
public class TolkienMobs {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public static final String MODID = "tolkienmobs";
    public static final String NAME = "Tolkien Tweaks (Mobs Edition)";
    public static final String VERSION = "${mod_version}"; //This will now be set automatically by the build.gradle when the jar is built.
    private static TolkienMobs instance;
    public NetworkHelper networkHelper;
    private HashMap<String, Long> modifiedPlayerTimes;

    public static CommonProxy proxy;

    /*TODO List
    1. Trinkets
	(Done)    a. applying said effect (not activating onUpdate)
	(Done)    b. rendering color on trinket
	(Done)    c. tolkienmobs effects not woreking
    2. Effects
        a. The Terror effect - Need on-screen rendering
        b. Sleep Effect - Need on-screen rendering
        c. Drown Effect - does not apply drown damage (or any substituted damage for that matter)
        d. Tornado Effect - That had some extremely fancy packets handling and other GL stuff, not sure it is doable in 1.16
    3. Enchantments Remaining
        a. Hobbit Harvest - Was supposed to be fortune for using the Hoe to harvest crops
        b. Hobbit Plow - Allowed for wide area "plowing" of farmland with right click instead of 1x1 (level made area wider)
    4. Entities
	    a. Frog - not jumping when moving
	    b. Thrush - Model adjustments and placed on ground instead of in the air
	    c. Remaining Entities
	        1.
    5. Remaining blocks -
        a. Fireplace
        b. custom Signs
    6. Custom weapon & tool models
        a. Fire Whip - Proper rendering
        b. Wooden Club - Proper rendering
        c. Mithril armor
	    d. Morguliron armor
    7. Ammo
        a. Fell Beast Fireball - rendering
	    b. Boulder - rendering
	    c. Galadhrim Arrow - rendering
    8. Fluids
	    a. Fluid/color not rendering on buckets in hand/inventory
    9. Generation
        a. Trees
        b. Biomes
        c. Structures
        d. Plants
    10. Network Stuff
    */

    public TolkienMobs() {
        instance = this;
        networkHelper = new NetworkHelper("tolkienmobs", AirPacket.class);
        modifiedPlayerTimes = new HashMap<>();

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

    public void sendAirPacket(ServerPlayerEntity target, int lastAir) {
        if (getIsEntityAllowedTarget(target)) {
            networkHelper.sendPacketToPlayer(new AirPacket(lastAir), target);
        }
    }

    public boolean getIsEntityAllowedTarget(Entity entity) {
        return !(entity instanceof FakePlayer);
    }

    public static MobModify getMobModifiers(LivingEntity ent) {
        return proxy.getRareMobs().get(ent);
    }

    public HashMap<String, Long> getModifiedPlayerTimes() {
        return modifiedPlayerTimes;
    }

}