package com.greatorator.tolkienmobs;


import com.greatorator.tolkienmobs.common.MobModify;
import com.greatorator.tolkienmobs.common.network.AirPacket;
import com.greatorator.tolkienmobs.common.network.NetworkHelper;
import com.greatorator.tolkienmobs.handler.interfaces.IFireplaceRecipe;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.proxy.ClientProxy;
import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.simple.SimpleChannel;
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
    public static SimpleChannel NETWORK;
    private HashMap<String, Long> modifiedPlayerTimes;

    public static CommonProxy proxy;

    public static IRecipeType<IFireplaceRecipe> FIREPLACE_RECIPE_TYPE;

    /*TODO List
    1. Entities
	    a. Remaining Entities
	        1. Fell Beast - Needs AI and implementation
			2. Gwaihir - Needs AI and implementation
			3. Great Eagle - Needs AI and implementation
			4. Watcher - Does not want to move around
			5. Deep Claw - Texture Issue (near head, extra texture layer that shouldn't be there)
			6. Tree Ent - not "breathing"
			7. Duergar - not "breathing", not holding weapon
			8. Troll - Texture issue (chest area, looks to be extra texture attached to neck), not "breathing", Weapon not moving in sync
			9. Huron - not "breathing"
			10. Mithril & Morgul Golems - not "breathing"
			11. Witch King - Texture Issue (Noticeable on legs, "armor" skin not moving correctly)
			12. Wandering Trader - Texture Issue (Noticeable on legs, "armor" skin not moving correctly)
			13. Balrog - not "breathing", switching attacks based on range, Weapon not moving in sync
			14. Nazgul - Texture issue (Noticeable on legs, "armor" skin not moving correctly)
			15. Frog - not jumping when moving
		b. Ammo
            a. Fell Beast Fireball - rendering
	        b. Boulder - rendering
	        c. Galadhrim Arrow - rendering
    3. Backpack -
       a. Upgrade system
       b. Improved sleeping bag and campfire deployment
	4. Custom Recipe
	   a. Fireplace - JEI GUI needs fixed
    */

    public TolkienMobs() {
        instance = this;
        networkHelper = new NetworkHelper("tolkienmobs", AirPacket.class);
        modifiedPlayerTimes = new HashMap<>();

        synchronized (MinecraftForge.EVENT_BUS) {
            Logger ttLog = LogManager.getLogger("tolkientweaks");
            Logger bcLog = LogManager.getLogger("brandonscore");
            LOGGER.info("Meeting of the Fellowship started! Waiting for the rest of the party to arrive...");
            if (TTMHelper.isTTInstalled) {
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
        if (TTMHelper.isJEIInstalled) {
            TolkienMobs.FIREPLACE_RECIPE_TYPE = IRecipeType.register(MODID + ":tmfireplace");
        }
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