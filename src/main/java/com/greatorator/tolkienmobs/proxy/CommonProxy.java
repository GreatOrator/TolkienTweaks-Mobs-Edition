package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.RecipeGenerator;
import com.greatorator.tolkienmobs.event.entity.SleepingEvent;
import com.greatorator.tolkienmobs.event.entity.WorldEvents;
import com.greatorator.tolkienmobs.handler.MilestoneSaveData;
import com.greatorator.tolkienmobs.handler.MobModify;
import com.greatorator.tolkienmobs.init.*;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.EquipmentManager;
import com.greatorator.tolkienmobs.item.tools.CoinPouchItem;
import com.greatorator.tolkienmobs.item.tools.KeyRingItem;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMStructureConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMTreeFeatureConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.ConcurrentHashMap;

public class CommonProxy {
    private Minecraft mc;
    private Level lastWorld;
    private final ConcurrentHashMap<LivingEntity, MobModify> rareMobsClient = new ConcurrentHashMap<>();

    public void construct() {
        registerEventListeners();
        TTMConfig.load();
        TTMContent.init();
        TTMHelper.init();
        EquipmentManager.initialize();
        TolkienTags.init();
        MilestoneSaveData.init();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(WorldEvents::onPlayerUpdate);
        MinecraftForge.EVENT_BUS.addListener(WorldEvents::addDimensionalSpacing);
        MinecraftForge.EVENT_BUS.addListener(WorldEvents::biomeModification);

        TTMTreeFeatureConfig.FOLIAGE_PLACER_REGISTER.register(modBus);

        modBus.addListener(TolkienEntities::registerAttributes);
        TolkienNetwork.init();
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        RecipeGenerator.potions();
        TTMConfig.loadPotionList();
        TTMConfig.loadDimensionList();
        event.enqueueWork(() -> {
            TolkienStructures.setupStructures();
            TTMStructureConfig.registerConfiguredStructures();
            TolkienProfessions.registerBanker();
            TolkienProfessions.registerGrocer();
            TolkienProfessions.registerJunk();
            TolkienProfessions.registerPet();
            TolkienProfessions.registerSmith();
            TolkienProfessions.registerTailor();
        });
        TolkienEntities.registerSpawnPlacement();
        TolkienBiomes.addBiomesToOverworld();
        TolkienBiomes.addTypes();
    }

    public void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new SleepingEvent());
    }

    public void serverSetup(FMLDedicatedServerSetupEvent event) {

    }

    public void playSound(SoundEvent soundEvent, BlockPos pos, float pitch) {
    }

    public void onAirPacket(int air) {

    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent tick) {
        if (mc.level == null || (mc.screen != null && mc.screen.isPauseScreen()))
            return;

        if (mc.level != lastWorld) {
            boolean newGame = lastWorld == null;
            lastWorld = mc.level;

            if (!newGame) {
                TolkienMobs.proxy.getRareMobs().clear();
            }
        }
    }

    public ConcurrentHashMap<LivingEntity, MobModify> getRareMobs() {
        return rareMobsClient;
    }

    public void registerEventListeners() {
        MinecraftForge.EVENT_BUS.addListener(CoinPouchItem::onItemPickup);
        MinecraftForge.EVENT_BUS.addListener(KeyRingItem::onItemPickup);
    }

    public Player getPlayer() {
        return null;
    }
}
