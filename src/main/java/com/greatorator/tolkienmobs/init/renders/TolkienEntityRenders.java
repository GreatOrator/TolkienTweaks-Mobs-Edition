package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.model.SwarmLayer;
import com.greatorator.tolkienmobs.entity.ambient.model.SwarmModel;
import com.greatorator.tolkienmobs.entity.ambient.render.*;
import com.greatorator.tolkienmobs.entity.boss.render.BalrogRender;
import com.greatorator.tolkienmobs.entity.boss.render.GwahirRender;
import com.greatorator.tolkienmobs.entity.boss.render.WatcherRender;
import com.greatorator.tolkienmobs.entity.boss.render.WitchKingRender;
import com.greatorator.tolkienmobs.entity.item.render.GaladhrimArrowRender;
import com.greatorator.tolkienmobs.entity.item.render.MorgulCrystalRenderer;
import com.greatorator.tolkienmobs.entity.item.render.TolkienBoatRender;
import com.greatorator.tolkienmobs.entity.item.render.UtumnoArrowRender;
import com.greatorator.tolkienmobs.entity.merchant.render.*;
import com.greatorator.tolkienmobs.entity.monster.render.*;
import com.greatorator.tolkienmobs.entity.passive.render.AurochRender;
import com.greatorator.tolkienmobs.entity.passive.render.GoatRender;
import com.greatorator.tolkienmobs.entity.passive.render.MumakilRender;
import com.greatorator.tolkienmobs.entity.special.render.*;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TolkienEntityRenders {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        // Ambient
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_RAT.get(), RatRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SQUIRREL.get(), SquirrelRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_FROG.get(), FrogRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SWARM.get(), m -> new SwarmRender<>(m, new SwarmModel(m.bakeLayer(SwarmLayer.ENTITY_TTM_SWARM)), 0.0F, TolkienMobs.MODID + ":textures/entity/midgeflies.png"));
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_THRUSH.get(), ThrushRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_CREBAIN.get(), CrebainRender::new);

        // Merchants
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HUMAN.get(), HumanRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DWARF.get(), DwarfRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DESERTDWELLER.get(), DesertDwellerRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_ELVES.get(), ElvesRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HOBBIT.get(), HobbitRender::new);

        // Monster
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_OATHBREAKER.get(), OathbreakerRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_BARROW.get(), BarrowWightRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_FELLSPIRIT.get(), FellSpiritRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HARADRIM.get(), HaradrimRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_BRIGAND.get(), BrigandRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_ROMIEWALKER.get(), RomieWalkerRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DEEPCLAW.get(), DeepClawRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_TREEENT.get(), TreeEntRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MIMICCHEST.get(), MimicChestRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DUERGAR.get(), DuergarRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_ELEMENTALGOLEM.get(), ElementalGolemRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOBLIN.get(), GoblinRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SWAMPHAG.get(), SwampHagRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MIRKWOODSPIDER.get(), MirkwoodSpiderRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_TROLL.get(), m -> new TrollRender(m, new TrollModel<>(1.0F), 1.0F));
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_WARG.get(), WargRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MORDORORC.get(), MordorOrcRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HURON.get(), HuronRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_URUKHAI.get(), UrukHaiRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MINOTAUR.get(), MinotaurRender::new);

        // Boss
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_WATCHER.get(), WatcherRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_BALROG.get(), BalrogRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_WITCHKING.get(), WitchKingRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GWAHIR.get(), GwahirRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOBLINKING.get(), GoblinKingRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MITHRILGOLEM.get(), MithrilGolemRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MORGULIRONGOLEM.get(), MorgulIronGolemRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SHELOB.get(), ShelobRender::new);
//        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_FELL_BEAST.get(), FellBeastRender::new);
//
        // Passive
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_AUROCH.get(), AurochRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MUMAKIL.get(), MumakilRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOAT.get(), GoatRender::new);

        // Special
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SHADOWFAX.get(), ShadowfaxRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOLLUM.get(), GollumRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_NAZGUL.get(), NazgulRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_NAZGULSTEED.get(), NazgulSteedRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GREAT_EAGLE.get(), GreatEagleRender::new);
//
        // Ammo
        event.registerEntityRenderer(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), GaladhrimArrowRender::new);
        event.registerEntityRenderer(TolkienEntities.AMMO_ARROW_UTUMNO.get(), UtumnoArrowRender::new);
        event.registerEntityRenderer(TolkienEntities.AMMO_FELLBEAST_FIREBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(TolkienEntities.AMMO_BOULDER.get(), ThrownItemRenderer::new);

        // Items
        event.registerEntityRenderer(TolkienEntities.MALLORN_BOAT.get(), TolkienBoatRender::new);
        event.registerEntityRenderer(TolkienEntities.MIRKWOOD_BOAT.get(), TolkienBoatRender::new);
        event.registerEntityRenderer(TolkienEntities.CULUMALDA_BOAT.get(), TolkienBoatRender::new);
        event.registerEntityRenderer(TolkienEntities.LEBETHRON_BOAT.get(), TolkienBoatRender::new);
        event.registerEntityRenderer(TolkienEntities.DEADWOOD_BOAT.get(), TolkienBoatRender::new);
        event.registerEntityRenderer(TolkienEntities.FANGORNOAK_BOAT.get(), TolkienBoatRender::new);
        event.registerEntityRenderer(TolkienEntities.MORGUL_CRYSTAL.get(), MorgulCrystalRenderer::new);
    }

    public String getName() {
        return NAME + " - Entities";
    }
}