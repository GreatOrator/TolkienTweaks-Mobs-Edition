package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.TTMClientEvents;
import com.greatorator.tolkienmobs.client.TTMSprites;
import com.greatorator.tolkienmobs.client.gui.*;
import com.greatorator.tolkienmobs.client.render.tile.*;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMSwarm;
import com.greatorator.tolkienmobs.entity.ambient.render.*;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderBoulder;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.boss.render.*;
import com.greatorator.tolkienmobs.entity.merchant.render.*;
import com.greatorator.tolkienmobs.entity.merchant.villager.VillagerTTMTrades;
import com.greatorator.tolkienmobs.entity.monster.render.*;
import com.greatorator.tolkienmobs.entity.passive.render.RenderTTMAuroch;
import com.greatorator.tolkienmobs.entity.passive.render.RenderTTMGoat;
import com.greatorator.tolkienmobs.entity.passive.render.RenderTTMMumakil;
import com.greatorator.tolkienmobs.entity.special.render.RenderTTMGollum;
import com.greatorator.tolkienmobs.entity.special.render.RenderTTMNazgul;
import com.greatorator.tolkienmobs.entity.special.render.RenderTTMNazgulSteed;
import com.greatorator.tolkienmobs.entity.special.render.RenderTTMShadowfax;
import com.greatorator.tolkienmobs.handler.TTMHearts;
import com.greatorator.tolkienmobs.init.TTMColor;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.greatorator.tolkienmobs.entity.merchant.villager.VillagerTTMUtility.fixPOITypeBlockStates;

public class ClientProxy extends CommonProxy {

    @Override
    public void construct() {
        super.construct();
        MinecraftForge.EVENT_BUS.addListener(TTMClientEvents::livingUpdate);
        MinecraftForge.EVENT_BUS.addListener(TTMClientEvents::renderOverlayEvent);
        MinecraftForge.EVENT_BUS.addListener(VillagerTTMTrades::onVillagerTradesEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TTMColor::itemColourEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(TTMSprites::initialize);
    }

    @Override
    public void commonSetup(FMLCommonSetupEvent event) {
        super.commonSetup(event);

        if (!TTMHelper.isMantleInstalled) {
            MinecraftForge.EVENT_BUS.register(new TTMHearts());
        }

        fixPOITypeBlockStates(ProfessionGenerator.COIN_TRADER.get());
        fixPOITypeBlockStates(ProfessionGenerator.GROCERY_STORE.get());
        fixPOITypeBlockStates(ProfessionGenerator.PET_MERCHANT.get());
        fixPOITypeBlockStates(ProfessionGenerator.JUNK_TRADER.get());
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event) {
        super.clientSetup(event);
        setupRenderLayers();
        registerEntityRenderer();
        registerTileRenderers();
        registerWoodTypes(event);
    }

    private static void registerWoodTypes(FMLClientSetupEvent event) {
        //Add each of your custom wood types here. This is for textures.
        event.enqueueWork(() -> Atlases.addWoodType(TTMContent.MALLORN_WOOD_TYPE));
        event.enqueueWork(() -> Atlases.addWoodType(TTMContent.MIRKWOOD_WOOD_TYPE));
        event.enqueueWork(() -> Atlases.addWoodType(TTMContent.CULUMALDA_WOOD_TYPE));
        event.enqueueWork(() -> Atlases.addWoodType(TTMContent.LEBETHRON_WOOD_TYPE));
    }

    public static void setupRenderLayers() {
        RenderType cutout = RenderType.cutout();
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

        // Blocks
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_DECAY_BLOOM.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.MUSHROOM_BLOOM_DECAY.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SIMBELMYNE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ALFIRIN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_ATHELAS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_NIPHREDIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_SWAMPMILKWEED.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_DEADWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.SAPLING_FANGORNOAK.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.MITHRIL_BARS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.MORGULIRON_BARS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.DOOR_MORGULIRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MITHRIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TRAPDOOR_MORGULIRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MITHRIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.PRESSURE_PLATE_MORGULIRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TORCH_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.WALL_TORCH_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_MALLORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_MIRKWOOD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_CULUMALDA.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_LEBETHRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.LEAFPILE_FANGORNOAK.get(), cutout);
        RenderTypeLookup.setRenderLayer(TTMContent.TTMFIREPLACE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.PIGGYBANK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TTMContent.PIPEWEED.get(), cutout);

        // GUI Rendering
        ScreenManager.register(TTMContent.TMFIREPLACE_CONTAINER, GuiTTMFireplace::new);
        ScreenManager.register(TTMContent.PIGGYBANK_CONTAINER, GuiTTMPiggyBank::new);
        ScreenManager.register(TTMContent.BARREL_MITHRIL_CONTAINER, GuiTTMMithrilBarrel::new);
        ScreenManager.register(TTMContent.BARREL_MORGULIRON_CONTAINER, GuiTTMMorgulironBarrel::new);
        ScreenManager.register(TTMContent.BACKPACK_CONTAINER, BackpackScreen::new);

    }

    //#################################################################
    // Render Registry
    //#################################################################
    public static void registerEntityRenderer() {
        // Ambient
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_RAT.get(), RenderTTMRat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), RenderTTMSquirrel::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_FROG.get(), RenderTTMFrog::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SWARM.get(), m -> new RenderTTMSwarm<>(m, new ModelTTMSwarm(), 0.5F, TolkienMobs.MODID + ":textures/entity/midgeflies.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_THRUSH.get(), RenderTTMThrush::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_CREBAIN.get(), RenderTTMCrebain::new);

        // Merchants
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HUMAN.get(), RenderTTMHuman::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DWARF.get(), RenderTTMDwarf::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ELVES.get(), RenderTTMElves::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HOBBIT.get(), RenderTTMHobbit::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DESERTDWELLER.get(), RenderTTMDesertDweller::new);

        // Monster
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOBLIN.get(), RenderTTMGoblin::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BARROW.get(), RenderTTMBarrowWight::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BRIGAND.get(), RenderTTMBrigand::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DEEPCLAW.get(), RenderTTMDeepClaw::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_TREEENT.get(), RenderTTMTreeEnt::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_DUERGAR.get(), RenderTTMDuergar::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_FELLSPIRIT.get(), RenderTTMFellSpirit::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SWAMPHAG.get(), RenderTTMSwampHag::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MIRKWOODSPIDER.get(), RenderTTMMirkwoodSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HARADRIM.get(), RenderTTMHaradrim::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_TROLL.get(), RenderTTMTroll::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_WARG.get(), RenderTTMWarg::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MORDORORC.get(), RenderTTMMordorOrc::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_HURON.get(), RenderTTMHuron::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_OATHBREAKER.get(), RenderTTMOathbreaker::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ROMIEWALKER.get(), RenderTTMRomieWalker::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_URUKHAI.get(), RenderTTMUrukHai::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_ELEMENTALGOLEM.get(), RenderTTMElementalGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MINOTAUR.get(), RenderTTMMinotaur::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MIMICCHEST.get(), RenderTTMMimicChest::new);

        // Boss
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOBLINKING.get(), RenderTTMGoblinKing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MITHRILGOLEM.get(), RenderTTMMithrilGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MORGULIRONGOLEM.get(), RenderTTMMorgulIronGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_WITCHKING.get(), RenderTTMWitchKing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SHELOB.get(), RenderTTMShelob::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_BALROG.get(), RenderTTMBalrog::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_WATCHER.get(), RenderTTMWatcher::new);

        // Passive
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_AUROCH.get(), RenderTTMAuroch::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_MUMAKIL.get(), RenderTTMMumakil::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOAT.get(), RenderTTMGoat::new);

        // Special
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_SHADOWFAX.get(), RenderTTMShadowfax::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_GOLLUM.get(), RenderTTMGollum::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_NAZGUL.get(), RenderTTMNazgul::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.ENTITY_TTM_NAZGULSTEED.get(), RenderTTMNazgulSteed::new);

        // Ammo
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_ARROW_GALADHRIM.get(), new RenderGaladhrimArrow.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), new RenderFellBeastFireball.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.AMMO_BOULDER.get(), new RenderBoulder.RenderFactory());
    }

    private void registerTileRenderers() {
        ClientRegistry.bindTileEntityRenderer(TTMContent.TMFIREPLACE_TILE.get(), RenderTTMFireplaceTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.PIGGYBANK_TILE.get(), RenderTTMPiggyBankTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.BACKPACK_TILE.get(), RenderBackpackTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.PLACARD_TILE.get(), RenderPlacardTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.MALLORN_SIGN_TILE.get(), RenderTTMMallornSignTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.MIRKWOOD_SIGN_TILE.get(), RenderTTMMirkwoodSignTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.CULUMALDA_SIGN_TILE.get(), RenderTTMCulumaldaSignTile::new);
        ClientRegistry.bindTileEntityRenderer(TTMContent.LEBETHRON_SIGN_TILE.get(), RenderTTMLebethronSignTile::new);
    }

    @Override
    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        super.serverSetup(event);
    }

    @Override
    public PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }
}