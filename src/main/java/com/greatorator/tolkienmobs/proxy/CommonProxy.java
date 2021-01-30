package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.utils.TTMServerEvents;
import com.greatorator.tolkienmobs.utils.TTMSpawnEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class CommonProxy {

    public void construct() {
        registerEventListeners();
    }

    public void commonSetup(FMLCommonSetupEvent event) {

    }

    public void clientSetup(FMLClientSetupEvent event) {

    }

    public void serverSetup(FMLDedicatedServerSetupEvent event) {

    }


    public void registerEventListeners() {
        MinecraftForge.EVENT_BUS.addListener(TTMSpawnEvent::onEntitySpawn);
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::onLivingUpdate);



        //This still works but you can now register specific event handler methods which if you just have a single handler in a class it makes more sense
//        MinecraftForge.EVENT_BUS.register(new TTMServerEvents());
//        MinecraftForge.EVENT_BUS.register(new TTMEnchantHandler());
//        MinecraftForge.EVENT_BUS.register(TTMSoundHandler.class);
//        MinecraftForge.EVENT_BUS.register(new TTMEffectEvents());
//        if (disableVanilla) {
//            MinecraftForge.EVENT_BUS.register(new TTMSpawnEvent());
//        }
    }


//    public void preInit(FMLPreInitializationEvent event) {
//        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
//        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
//        registerEventListeners(event.getSide());
//        BiomeInit.registerBiomes();
//        EntityInit.init();
//        CraftingInit.init();
//        PotionInit.registerPotions();
//        EnchantmentsInit.registerEnchants();
//        new LootInit();
//
//        MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainEventHandler());
//
//        if (Loader.isModLoaded("tconstruct")) {
//            LogHelperTTM.info("Tinkers Construct is installed, Sending dwarves to begin mining deep in the earth.");
//            TinkersTTM.preInit();
//        }
//    }
//
//    public void init(FMLInitializationEvent event) {
//        ProfessionInit.associateCareersAndTrades();
//        TTMConfig.loadPotionList();
//
//        if (Loader.isModLoaded("tconstruct")) {
//            LogHelperTTM.info("Dwarves firing up the foundries...");
//            TinkersTTM.init();
//        }
//    }
//
//    public void postInit(FMLPostInitializationEvent event) {
//        WorldType ARDA = new WorldTypeArda("Arda");
//        WorldType SINGLEARDA = new WorldTypeSingleArda("SingleArdaBiome");
//    }
//

//
//    public static void serverRegistries(FMLServerStartingEvent event) {
//        event.registerServerCommand(new TTMCommandSpawn());
//    }

    public PlayerEntity getPlayer() {
        return null;
    }
}
