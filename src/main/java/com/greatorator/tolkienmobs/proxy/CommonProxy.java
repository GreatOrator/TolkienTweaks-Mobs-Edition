package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.RecipeGenerator;
import com.greatorator.tolkienmobs.event.entity.SleepingEvent;
import com.greatorator.tolkienmobs.event.entity.WorldEvents;
import com.greatorator.tolkienmobs.handler.MilestoneHandler;
import com.greatorator.tolkienmobs.handler.MobHandler;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienProfessions;
import com.greatorator.tolkienmobs.init.TolkienTags;
import com.greatorator.tolkienmobs.integration.IntegrationHelper;
import com.greatorator.tolkienmobs.integration.curios.EquipmentManager;
import com.greatorator.tolkienmobs.network.TolkienPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.ConcurrentHashMap;

import static com.greatorator.tolkienmobs.init.TolkienBlocks.*;

public class CommonProxy {
    private Minecraft mc;
    private Level lastWorld;
    private final ConcurrentHashMap<LivingEntity, MobHandler> rareMobsClient = new ConcurrentHashMap<>();

    public void construct() {
        registerEventListeners();
        TolkienConfig.load();
        TolkienContent.init();
        IntegrationHelper.init();
        EquipmentManager.initialize();
        TolkienTags.init();
        MilestoneHandler.init();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(WorldEvents::onPlayerUpdate);
        MinecraftForge.EVENT_BUS.addListener(WorldEvents::onEntityJoinWorld);
        MinecraftForge.EVENT_BUS.addListener(WorldEvents::addDimensionalSpacing);
//        MinecraftForge.EVENT_BUS.addListener(WorldEvents::biomeModification);

        modBus.addListener(TolkienEntities::registerAttributes);
        TolkienPacketHandler.init();
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        RecipeGenerator.potions();
        TolkienConfig.loadPotionList();
        event.enqueueWork(() -> {
//            TolkienStructures.setupStructures();
//            TTMStructureConfig.registerConfiguredStructures();
            TolkienProfessions.registerPOIS();

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MUSHROOM_DECAY_BLOOM.getId(), POTTED_MUSHROOM_DECAY_BLOOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MUSHROOM_BLOOM_DECAY.getId(), POTTED_MUSHROOM_BLOOM_DECAY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_SIMBELMYNE.getId(), POTTED_FLOWER_SIMBELMYNE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_MIRKWOOD.getId(), POTTED_FLOWER_MIRKWOOD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_ALFIRIN.getId(), POTTED_FLOWER_ALFIRIN);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_ATHELAS.getId(), POTTED_FLOWER_ATHELAS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_NIPHREDIL.getId(), POTTED_FLOWER_NIPHREDIL);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_SWAMPMILKWEED.getId(), POTTED_FLOWER_SWAMPMILKWEED);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(FLOWER_LILLYOFTHEVALLEY.getId(), POTTED_FLOWER_LILLYOFTHEVALLEY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(SAPLING_MALLORN.getId(), POTTED_SAPLING_MALLORN);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(SAPLING_MIRKWOOD.getId(), POTTED_SAPLING_MIRKWOOD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(SAPLING_CULUMALDA.getId(), POTTED_SAPLING_CULUMALDA);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(SAPLING_LEBETHRON.getId(), POTTED_SAPLING_LEBETHRON);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(SAPLING_DEADWOOD.getId(), POTTED_SAPLING_DEADWOOD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(SAPLING_FANGORNOAK.getId(), POTTED_SAPLING_FANGORNOAK);
        });
        TolkienEntities.registerSpawnPlacement();
//        TolkienBiomes.addBiomesToOverworld();
//        TolkienBiomes.addTypes();
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

    public ConcurrentHashMap<LivingEntity, MobHandler> getRareMobs() {
        return rareMobsClient;
    }

    public void registerEventListeners() {
    }

    public Player getPlayer() {
        return null;
    }
}
