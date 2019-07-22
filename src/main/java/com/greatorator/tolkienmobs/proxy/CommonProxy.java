package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.commands.TTMCommandSpawn;
import com.greatorator.tolkienmobs.handler.TTMEnchantHandler;
import com.greatorator.tolkienmobs.handler.TTMSoundHandler;
import com.greatorator.tolkienmobs.handler.TerrainEventHandler;
import com.greatorator.tolkienmobs.init.*;
import com.greatorator.tolkienmobs.utils.TTMServerEvents;
import com.greatorator.tolkienmobs.utils.TTMSpawnEvent;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomOres;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomStructures;
import com.greatorator.tolkienmobs.world.types.WorldTypeArda;
import com.greatorator.tolkienmobs.world.types.WorldTypeSingleArda;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import static com.greatorator.tolkienmobs.TTMConfig.disableVanilla;

public class CommonProxy {


    public void registerModel(Item item, int metadata) {}

    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
        registerEventListeners(event.getSide());
        BiomeInit.registerBiomes();
        EntityInit.init();
        CraftingInit.init();
        FluidInit.registerFluids();
        PotionInit.registerPotions();
        EnchantmentsInit.registerEnchants();
        new LootInit();

        MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainEventHandler());
    }

    public void init(FMLInitializationEvent event)
    {
        ProfessionInit.associateCareersAndTrades();
        TTMConfig.loadPotionList();
        new SpecialInit();
    }

    public void postInit(FMLPostInitializationEvent event) {
        WorldType ARDA = new WorldTypeArda("Arda");
        WorldType SINGLEARDA = new WorldTypeSingleArda("SingleArdaBiome");
    }

    public void registerEventListeners(Side s) {
        MinecraftForge.EVENT_BUS.register(new TTMServerEvents());
        MinecraftForge.EVENT_BUS.register(new TTMEnchantHandler());
        MinecraftForge.EVENT_BUS.register(TTMSoundHandler.class);
        if (disableVanilla){
            MinecraftForge.EVENT_BUS.register(new TTMSpawnEvent());
        }
    }

    public static void serverRegistries(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new TTMCommandSpawn());
    }

    public EntityPlayer getPlayer() {
        return null;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(SpecialInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(SpecialInit.BLOCKS.toArray(new Block[0]));
    }
}
