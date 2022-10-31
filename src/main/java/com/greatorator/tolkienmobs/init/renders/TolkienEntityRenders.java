package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.model.SwarmModel;
import com.greatorator.tolkienmobs.entity.ambient.render.*;
import com.greatorator.tolkienmobs.entity.boss.fellbeast.render.FellBeastRender;
import com.greatorator.tolkienmobs.entity.boss.model.WitchKingModel;
import com.greatorator.tolkienmobs.entity.boss.render.*;
import com.greatorator.tolkienmobs.entity.item.BoulderEntity;
import com.greatorator.tolkienmobs.entity.item.FellBeastFireballEntity;
import com.greatorator.tolkienmobs.entity.item.render.GaladhrimArrowRender;
import com.greatorator.tolkienmobs.entity.item.render.MorgulCrystalRenderer;
import com.greatorator.tolkienmobs.entity.item.render.TolkienBoatRender;
import com.greatorator.tolkienmobs.entity.item.render.UtumnoArrowRender;
import com.greatorator.tolkienmobs.entity.merchant.model.DwarfModel;
import com.greatorator.tolkienmobs.entity.merchant.model.HumanModel;
import com.greatorator.tolkienmobs.entity.merchant.render.*;
import com.greatorator.tolkienmobs.entity.monster.model.*;
import com.greatorator.tolkienmobs.entity.monster.render.*;
import com.greatorator.tolkienmobs.entity.passive.render.AurochRender;
import com.greatorator.tolkienmobs.entity.passive.render.GoatRender;
import com.greatorator.tolkienmobs.entity.passive.render.MumakilRender;
import com.greatorator.tolkienmobs.entity.special.render.*;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class TolkienEntityRenders {
    public static void init(EntityRenderersEvent.RegisterRenderers event) {

        // Ambient
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_RAT.get(), RatRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SQUIRREL.get(), SquirrelRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_FROG.get(), FrogRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SWARM.get(), m -> new SwarmRender<>(m, new SwarmModel(), 0.5F, TolkienMobs.MODID + ":textures/entity/midgeflies.png"));
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_THRUSH.get(), ThrushRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_CREBAIN.get(), CrebainRender::new);

        // Merchants
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HUMAN.get(), m -> new HumanRender(m, new HumanModel<>(1.0F), 0.5F));
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DWARF.get(), m -> new DwarfRender(m, new DwarfModel<>(1.0F), 0.5F));
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_ELVES.get(), ElvesRender::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HOBBIT.get(), RenderTTMHobbit::new);
        event.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DESERTDWELLER.get(), m -> new DesertDwellerRender(m, new HumanModel<>(1.0F), 0.5F));

        // Monster
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOBLIN.get(), GoblinRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_BARROW.get(), m -> new BarrowWightRender(m, new BarrowWightModel<>(1.0F, false), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_BRIGAND.get(), m -> new BrigandRender(m, new BrigandModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DEEPCLAW.get(), DeepClawRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_TREEENT.get(), m -> new TreeEntRender(m, new TreeEntModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_DUERGAR.get(), m -> new DuergarRender(m, new DuergarModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_FELLSPIRIT.get(), m -> new FellSpiritRender(m, new BarrowWightModel<>(1.0F, false), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SWAMPHAG.get(), SwampHagRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MIRKWOODSPIDER.get(), MirkwoodSpiderRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HARADRIM.get(), m -> new HaradrimRender(m, new HaradrimModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_TROLL.get(), m -> new TrollRender(m, new TrollModel<>(1.0F), 1.0F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_WARG.get(), WargRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MORDORORC.get(), MordorOrcRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_HURON.get(), HuronRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_OATHBREAKER.get(), m -> new OathbreakerRender(m, new BarrowWightModel<>(1.0F, false), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_ROMIEWALKER.get(), m -> new RomieWalkerRender(m, new BrigandModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_URUKHAI.get(), UrukHaiRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_ELEMENTALGOLEM.get(), ElementalGolemRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MINOTAUR.get(), MinotaurRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MIMICCHEST.get(), MimicChestRender::new);

        // Boss
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOBLINKING.get(), GoblinKingRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MITHRILGOLEM.get(), MithrilGolemRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MORGULIRONGOLEM.get(), MorgulIronGolemRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_WITCHKING.get(), m -> new WitchKingRender(m, new WitchKingModel<>(1.0F), 0.5F));
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SHELOB.get(), ShelobRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_BALROG.get(), BalrogRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_WATCHER.get(), WatcherRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GWAHIR.get(), GwahirRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_FELL_BEAST.get(), FellBeastRender::new);

        // Passive
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_AUROCH.get(), AurochRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_MUMAKIL.get(), MumakilRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOAT.get(), GoatRender::new);

        // Special
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_SHADOWFAX.get(), ShadowfaxRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GOLLUM.get(), GollumRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_NAZGUL.get(), NazgulRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_NAZGULSTEED.get(), NazgulSteedRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.ENTITY_TTM_GREAT_EAGLE.get(), GreatEagleRender::new);

        // Ammo
        RenderingRegistry.registerEntityRenderer(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), GaladhrimArrowRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.AMMO_ARROW_UTUMNO.get(), UtumnoArrowRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.AMMO_FELLBEAST_FIREBALL.get(), new ClientProxy.fellBeastFireballRenderFactory());
        RenderingRegistry.registerEntityRenderer(TolkienEntities.AMMO_BOULDER.get(), new ClientProxy.boulderRenderFactory());

        // Items
        RenderingRegistry.registerEntityRenderer(TolkienEntities.MALLORN_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.MIRKWOOD_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.CULUMALDA_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.LEBETHRON_BOAT.get(), TolkienBoatRender::new);
        RenderingRegistry.registerEntityRenderer(TolkienEntities.MORGUL_CRYSTAL.get(), MorgulCrystalRenderer::new);
    }

    private static class boulderRenderFactory implements IRenderFactory<BoulderEntity> {
        @Override
        public EntityRenderer<? super BoulderEntity> createRenderFor(EntityRendererManager manager) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            return new SpriteRenderer<>(manager, itemRenderer);
        }
    }

    private static class fellBeastFireballRenderFactory implements IRenderFactory<FellBeastFireballEntity> {
        @Override
        public EntityRenderer<? super FellBeastFireballEntity> createRenderFor(EntityRendererManager manager) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            return new SpriteRenderer<>(manager, itemRenderer);
        }
    }
}
