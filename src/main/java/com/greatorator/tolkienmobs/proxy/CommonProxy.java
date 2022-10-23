package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.BiomeGenerator;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.RecipeGenerator;
import com.greatorator.tolkienmobs.datagen.StructureGenerator;
import com.greatorator.tolkienmobs.event.entity.EntityEvents;
import com.greatorator.tolkienmobs.event.entity.SleepingEvent;
import com.greatorator.tolkienmobs.handler.MilestoneSaveData;
import com.greatorator.tolkienmobs.handler.MobModify;
import com.greatorator.tolkienmobs.handler.TTMTags;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidHelper;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.TTMEquipMgr;
import com.greatorator.tolkienmobs.item.tools.CoinPouchItem;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import com.greatorator.tolkienmobs.world.gen.TTMFeature;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMStructureConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMTreeFeatureConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    private World lastWorld;
    private final ConcurrentHashMap<LivingEntity, MobModify> rareMobsClient = new ConcurrentHashMap<>();

    public void construct() {
        registerEventListeners();
        TTMConfig.load();
        TTMContent.init();
        TTMHelper.init();
        TTMEquipMgr.initialize();
        TTMTags.init();
        MilestoneSaveData.init();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(EntityEvents::onPlayerUpdate);
        MinecraftForge.EVENT_BUS.addListener(EntityEvents::addDimensionalSpacing);
        MinecraftForge.EVENT_BUS.addListener(EntityEvents::biomeModification);

        TTMTreeFeatureConfig.FOLIAGE_PLACER_REGISTER.register(modBus);

        MinecraftForge.EVENT_BUS.addListener(TTMFeature::biomeLoading);

        modBus.addListener(EntityGenerator::registerAttributes);
        IFluidHelper.bootStrap();
        TolkienNetwork.init();
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        RecipeGenerator.potions();
        TTMConfig.loadPotionList();
        TTMConfig.loadDimensionList();
        event.enqueueWork(() -> {
            StructureGenerator.setupStructures();
            TTMStructureConfig.registerConfiguredStructures();
        });
        EntityGenerator.registerSpawnPlacement();
        BiomeGenerator.addBiomesToOverworld();
        BiomeGenerator.addTypes();
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
    }

    public PlayerEntity getPlayer() {
        return null;
    }
}
