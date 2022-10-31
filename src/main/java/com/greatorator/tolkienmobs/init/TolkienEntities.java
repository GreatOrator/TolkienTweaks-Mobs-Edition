package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.entity.AmbientEntity;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.VillagerEntity;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.boss.*;
import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import com.greatorator.tolkienmobs.entity.item.*;
import com.greatorator.tolkienmobs.entity.merchant.*;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.AurochEntity;
import com.greatorator.tolkienmobs.entity.passive.GoatEntity;
import com.greatorator.tolkienmobs.entity.passive.MumakilEntity;
import com.greatorator.tolkienmobs.entity.special.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.init.TolkienTabs.spawnGroup;

public class TolkienEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Ambient
    private static final EntityType<RatEntity> entityTTMRat = buildEntity("entityttmrat", RatEntity::new, MobCategory.CREATURE, 0.75F, 0.5F);
    public static final RegistryObject<EntityType<RatEntity>> ENTITY_TTM_RAT = ENTITY.register("entityttmrat", () -> entityTTMRat);
    private static final EntityType<SquirrelEntity> entityTTMSquirrel = buildEntity("entityttmsquirrel", SquirrelEntity::new, MobCategory.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<SquirrelEntity>> ENTITY_TTM_SQUIRREL = ENTITY.register("entityttmsquirrel", () -> entityTTMSquirrel);
    private static final EntityType<FrogEntity> entityTTMFrog = buildEntity("entityttmfrog", FrogEntity::new, MobCategory.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<FrogEntity>> ENTITY_TTM_FROG = ENTITY.register("entityttmfrog", () -> entityTTMFrog);
    private static final EntityType<SwarmEntity> entityTTMSwarm = buildEntity("entityttmswarm", SwarmEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final RegistryObject<EntityType<SwarmEntity>> ENTITY_TTM_SWARM = ENTITY.register("entityttmswarm", () -> entityTTMSwarm);
    private static final EntityType<ThrushEntity> entityTTMThrush = buildEntity("entityttmthrush", ThrushEntity::new, MobCategory.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<ThrushEntity>> ENTITY_TTM_THRUSH = ENTITY.register("entityttmthrush", () -> entityTTMThrush);
    private static final EntityType<CrebainEntity> entityTTMCrebain = buildEntity("entityttmcrebain", CrebainEntity::new, MobCategory.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<CrebainEntity>> ENTITY_TTM_CREBAIN = ENTITY.register("entityttmcrebain", () -> entityTTMCrebain);

    // Merchants
    private static final EntityType<HumanEntity> entityTTMHuman = buildEntity("entityttmhuman", HumanEntity::new, MobCategory.MISC, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<HumanEntity>> ENTITY_TTM_HUMAN = ENTITY.register("entityttmhuman", () -> entityTTMHuman);
    private static final EntityType<DwarfEntity> entityTTMDwarf = buildEntity("entityttmdwarf", DwarfEntity::new, MobCategory.MISC, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<DwarfEntity>> ENTITY_TTM_DWARF = ENTITY.register("entityttmdwarf", () -> entityTTMDwarf);
    private static final EntityType<ElvesEntity> entityTTMElves = buildEntity("entityttmelves", ElvesEntity::new, MobCategory.MISC, 0.9F, 2.0F);
    public static final RegistryObject<EntityType<ElvesEntity>> ENTITY_TTM_ELVES = ENTITY.register("entityttmelves", () -> entityTTMElves);
    private static final EntityType<HobbitEntity> entityTTMHobbit = buildEntity("entityttmhobbit", HobbitEntity::new, MobCategory.MISC, 0.6F, 1.4F);
    public static final RegistryObject<EntityType<HobbitEntity>> ENTITY_TTM_HOBBIT = ENTITY.register("entityttmhobbit", () -> entityTTMHobbit);
    private static final EntityType<DesertDwellerEntity> entityTTMDesertDweller = buildEntity("entityttmdesertdweller", DesertDwellerEntity::new, MobCategory.MISC, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<DesertDwellerEntity>> ENTITY_TTM_DESERTDWELLER = ENTITY.register("entityttmdesertdweller", () -> entityTTMDesertDweller);

    // Monster
    private static final EntityType<GoblinEntity> entityTTMGoblin = buildEntity("entityttmgoblin", GoblinEntity::new, MobCategory.MONSTER, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<GoblinEntity>> ENTITY_TTM_GOBLIN = ENTITY.register("entityttmgoblin", () -> entityTTMGoblin);
    private static final EntityType<BarrowWightEntity> entityTTMBarrow = buildEntity("entityttmbarrow", BarrowWightEntity::new, MobCategory.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<BarrowWightEntity>> ENTITY_TTM_BARROW = ENTITY.register("entityttmbarrow", () -> entityTTMBarrow);
    private static final EntityType<BrigandEntity> entityTTMBrigand = buildEntity("entityttmbrigand", BrigandEntity::new, MobCategory.MONSTER, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<BrigandEntity>> ENTITY_TTM_BRIGAND = ENTITY.register("entityttmbrigand", () -> entityTTMBrigand);
    private static final EntityType<DeepClawEntity> entityTTMDeepclaw = buildEntity("entityttmdeepclaw", DeepClawEntity::new, MobCategory.MONSTER, 0.7F, 0.7F);
    public static final RegistryObject<EntityType<DeepClawEntity>> ENTITY_TTM_DEEPCLAW = ENTITY.register("entityttmdeepclaw", () -> entityTTMDeepclaw);
    private static final EntityType<TreeEntEntity> entityTTMTreeEnt = buildEntity("entityttmtreeent", TreeEntEntity::new, MobCategory.MONSTER, 1.35F, 5.5F);
    public static final RegistryObject<EntityType<TreeEntEntity>> ENTITY_TTM_TREEENT = ENTITY.register("entityttmtreeent", () -> entityTTMTreeEnt);
    private static final EntityType<DuergarEntity> entityTTMDuergar = buildEntity("entityttmduergar", DuergarEntity::new, MobCategory.MONSTER, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<DuergarEntity>> ENTITY_TTM_DUERGAR = ENTITY.register("entityttmduergar", () -> entityTTMDuergar);
    private static final EntityType<FellSpiritEntity> entityTTMFellSpirit = buildEntity("entityttmfellspirit", FellSpiritEntity::new, MobCategory.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<FellSpiritEntity>> ENTITY_TTM_FELLSPIRIT = ENTITY.register("entityttmfellspirit", () -> entityTTMFellSpirit);
    private static final EntityType<SwampHagEntity> entityTTMSwampHag = buildEntity("entityttmswamphag", SwampHagEntity::new, MobCategory.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<SwampHagEntity>> ENTITY_TTM_SWAMPHAG = ENTITY.register("entityttmswamphag", () -> entityTTMSwampHag);
    private static final EntityType<MirkwoodSpiderEntity> entityTTMMirkwoodSpider = buildEntity("entityttmmirkwoodspider", MirkwoodSpiderEntity::new, MobCategory.MONSTER, 1.4F, 0.9F);
    public static final RegistryObject<EntityType<MirkwoodSpiderEntity>> ENTITY_TTM_MIRKWOODSPIDER = ENTITY.register("entityttmmirkwoodspider", () -> entityTTMMirkwoodSpider);
    private static final EntityType<HaradrimEntity> entityTTMHaradrim = buildEntity("entityttmharadrim", HaradrimEntity::new, MobCategory.MONSTER, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<HaradrimEntity>> ENTITY_TTM_HARADRIM = ENTITY.register("entityttmharadrim", () -> entityTTMHaradrim);
    private static final EntityType<TrollEntity> entityTTMTroll = buildEntity("entityttmtroll", TrollEntity::new, MobCategory.MONSTER, 2.2F, 2.6F);
    public static final RegistryObject<EntityType<TrollEntity>> ENTITY_TTM_TROLL = ENTITY.register("entityttmtroll", () -> entityTTMTroll);
    private static final EntityType<WargEntity> entityTTMWarg = buildEntity("entityttmwarg", WargEntity::new, MobCategory.MONSTER, 1.5F, 1.4F);
    public static final RegistryObject<EntityType<WargEntity>> ENTITY_TTM_WARG = ENTITY.register("entityttmwarg", () -> entityTTMWarg);
    private static final EntityType<MordorOrcEntity> entityTTMMordorOrc = buildEntity("entityttmmordororc", MordorOrcEntity::new, MobCategory.MONSTER, 1.5F, 1.4F);
    public static final RegistryObject<EntityType<MordorOrcEntity>> ENTITY_TTM_MORDORORC = ENTITY.register("entityttmmordororc", () -> entityTTMMordorOrc);
    private static final EntityType<HuronEntity> entityTTMHuron = buildEntity("entityttmhuron", HuronEntity::new, MobCategory.MONSTER, 1.5F, 1.4F);
    public static final RegistryObject<EntityType<HuronEntity>> ENTITY_TTM_HURON = ENTITY.register("entityttmhuron", () -> entityTTMHuron);
    private static final EntityType<OathbreakerEntity> entityTTMOathbreaker = buildEntity("entityttmoathbreaker", OathbreakerEntity::new, MobCategory.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<OathbreakerEntity>> ENTITY_TTM_OATHBREAKER = ENTITY.register("entityttmoathbreaker", () -> entityTTMOathbreaker);
    private static final EntityType<RomieWalkerEntity> entityTTMRomieWalker = buildEntity("entityttmromiewalker", RomieWalkerEntity::new, MobCategory.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<RomieWalkerEntity>> ENTITY_TTM_ROMIEWALKER = ENTITY.register("entityttmromiewalker", () -> entityTTMRomieWalker);
    private static final EntityType<UrukHaiEntity> entityTTMUrukHai = buildEntity("entityttmurukhai", UrukHaiEntity::new, MobCategory.MONSTER, 1.0F, 2.6F);
    public static final RegistryObject<EntityType<UrukHaiEntity>> ENTITY_TTM_URUKHAI = ENTITY.register("entityttmurukhai", () -> entityTTMUrukHai);
    private static final EntityType<ElementalGolemEntity> entityTTMElementalGolem = buildEntity("entityttmelementalgolem", ElementalGolemEntity::new, MobCategory.MONSTER, 1.8F, 3.1F);
    public static final RegistryObject<EntityType<ElementalGolemEntity>> ENTITY_TTM_ELEMENTALGOLEM = ENTITY.register("entityttmelementalgolem", () -> entityTTMElementalGolem);
    private static final EntityType<MinotaurEntity> entityTTMMinotaur = buildEntity("entityttmminotaur", MinotaurEntity::new, MobCategory.MONSTER, 1.8F, 4.0F);
    public static final RegistryObject<EntityType<MinotaurEntity>> ENTITY_TTM_MINOTAUR = ENTITY.register("entityttmminotaur", () -> entityTTMMinotaur);
    private static final EntityType<MimicChestEntity> entityTTMMimicChest = buildEntity("entityttmmimicchest", MimicChestEntity::new, MobCategory.MONSTER, 1.0F, 1.7F);
    public static final RegistryObject<EntityType<MimicChestEntity>> ENTITY_TTM_MIMICCHEST = ENTITY.register("entityttmmimicchest", () -> entityTTMMimicChest);

    // Boss
    private static final EntityType<GoblinKingEntity> entityTTMGoblinKing = buildEntity("entityttmgoblinking", GoblinKingEntity::new, MobCategory.MONSTER, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<GoblinKingEntity>> ENTITY_TTM_GOBLINKING = ENTITY.register("entityttmgoblinking", () -> entityTTMGoblinKing);
    private static final EntityType<FellBeastEntity> entityFellBeast = buildEntity("entityttmfellbeast", FellBeastEntity::new, MobCategory.MONSTER, 16.0F, 8.0F);
    public static final RegistryObject<EntityType<FellBeastEntity>> ENTITY_FELL_BEAST = ENTITY.register("entityttmfellbeast", () -> entityFellBeast);
    private static final EntityType<MithrilGolemEntity> entityTTMMithrilGolem = buildEntity("entityttmmithrilgolem", MithrilGolemEntity::new, MobCategory.MONSTER, 1.8F, 3.1F);
    public static final RegistryObject<EntityType<MithrilGolemEntity>> ENTITY_TTM_MITHRILGOLEM = ENTITY.register("entityttmmithrilgolem", () -> entityTTMMithrilGolem);
    private static final EntityType<MorgulIronGolemEntity> entityTTMMorgulIronGolem = buildEntity("entityttmmorgulirongolem", MorgulIronGolemEntity::new, MobCategory.MONSTER, 1.8F, 3.1F);
    public static final RegistryObject<EntityType<MorgulIronGolemEntity>> ENTITY_TTM_MORGULIRONGOLEM = ENTITY.register("entityttmmorgulirongolem", () -> entityTTMMorgulIronGolem);
    private static final EntityType<WitchKingEntity> entityTTMWitchKing = buildEntity("entityttmwitchking", WitchKingEntity::new, MobCategory.MONSTER, 1.3F, 2.2F);
    public static final RegistryObject<EntityType<WitchKingEntity>> ENTITY_TTM_WITCHKING = ENTITY.register("entityttmwitchking", () -> entityTTMWitchKing);
    private static final EntityType<ShelobEntity> entityTTMShelob = buildEntity("entityttmshelob", ShelobEntity::new, MobCategory.MONSTER, 2.3F, 1.1F);
    public static final RegistryObject<EntityType<ShelobEntity>> ENTITY_TTM_SHELOB = ENTITY.register("entityttmshelob", () -> entityTTMShelob);
    private static final EntityType<BalrogEntity> entityTTMBalrog = buildFireEntity("entityttmbalrog", BalrogEntity::new, MobCategory.MONSTER, 2.3F, 3.5F);
    public static final RegistryObject<EntityType<BalrogEntity>> ENTITY_TTM_BALROG = ENTITY.register("entityttmbalrog", () -> entityTTMBalrog);
    private static final EntityType<WatcherEntity> entityTTMWatcher = buildEntity("entityttmwatcher", WatcherEntity::new, MobCategory.WATER_CREATURE, 2.3F, 3.5F);
    public static final RegistryObject<EntityType<WatcherEntity>> ENTITY_TTM_WATCHER = ENTITY.register("entityttmwatcher", () -> entityTTMWatcher);
    private static final EntityType<GwahirEntity> entityTTMGwahir = buildEntity("entityttmgwahir", GwahirEntity::new, MobCategory.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<GwahirEntity>> ENTITY_TTM_GWAHIR = ENTITY.register("entityttmgwahir", () -> entityTTMGwahir);

    // Passive
    private static final EntityType<AurochEntity> entityTTMAuroch = buildEntity("entityttmauroch", AurochEntity::new, MobCategory.CREATURE, 1.9F, 1.4F);
    public static final RegistryObject<EntityType<AurochEntity>> ENTITY_TTM_AUROCH = ENTITY.register("entityttmauroch", () -> entityTTMAuroch);
    private static final EntityType<MumakilEntity> entityTTMMumakil = buildEntity("entityttmmumakil", MumakilEntity::new, MobCategory.CREATURE, 3.0F, 3.0F);
    public static final RegistryObject<EntityType<MumakilEntity>> ENTITY_TTM_MUMAKIL = ENTITY.register("entityttmmumakil", () -> entityTTMMumakil);
    private static final EntityType<GoatEntity> entityTTMGoat = buildEntity("entityttmgoat", GoatEntity::new, MobCategory.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<GoatEntity>> ENTITY_TTM_GOAT = ENTITY.register("entityttmgoat", () -> entityTTMGoat);

    // Special
    private static final EntityType<ShadowfaxEntity> entityTTMShadowfax = buildEntity("entityttmshadowfax", ShadowfaxEntity::new, MobCategory.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<ShadowfaxEntity>> ENTITY_TTM_SHADOWFAX = ENTITY.register("entityttmshadowfax", () -> entityTTMShadowfax);
    private static final EntityType<GollumEntity> entityTTMGollum = buildEntity("entityttmgollum", GollumEntity::new, MobCategory.CREATURE, 1.0F, 1.0F);
    public static final RegistryObject<EntityType<GollumEntity>> ENTITY_TTM_GOLLUM = ENTITY.register("entityttmgollum", () -> entityTTMGollum);
    private static final EntityType<NazgulEntity> entityTTMNazgul = buildEntity("entityttmnazgul", NazgulEntity::new, MobCategory.MONSTER, 1.3F, 2.2F);
    public static final RegistryObject<EntityType<NazgulEntity>> ENTITY_TTM_NAZGUL = ENTITY.register("entityttmnazgul", () -> entityTTMNazgul);
    private static final EntityType<NazgulSteedEntity> entityTTMNazgulSteed = buildEntity("entityttmnazgulsteed", NazgulSteedEntity::new, MobCategory.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<NazgulSteedEntity>> ENTITY_TTM_NAZGULSTEED = ENTITY.register("entityttmnazgulsteed", () -> entityTTMNazgulSteed);
    private static final EntityType<GreatEagleEntity> entityTTMGreatEagle = buildEntity("entityttmgreateagle", GreatEagleEntity::new, MobCategory.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<GreatEagleEntity>> ENTITY_TTM_GREAT_EAGLE = ENTITY.register("entityttmgreateagle", () -> entityTTMGreatEagle);

    // Ammo
    public static final RegistryObject<EntityType<GaladhrimArrowEntity>> AMMO_ARROW_GALADHRIM = ENTITY.register("ammo_arrow_galadhrim", () -> EntityTypes.Builder.of((EntityTypes.IFactory<GaladhrimArrowEntity>) GaladhrimArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_arrow_galadhrim"));
    public static final RegistryObject<EntityType<UtumnoArrowEntity>> AMMO_ARROW_UTUMNO = ENTITY.register("ammo_utumno_arrow", () -> EntityTypes.Builder.of((EntityTypes.IFactory<UtumnoArrowEntity>) UtumnoArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_utumno_arrow"));
    public static final RegistryObject<EntityType<FellBeastFireballEntity>> AMMO_FELLBEAST_FIREBALL = ENTITY.register("ammo_fellbeast_fireball", () -> EntityTypes.Builder.of((EntityTypes.IFactory<FellBeastFireballEntity>) FellBeastFireballEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_fellbeast_fireball"));
    public static final RegistryObject<EntityType<BoulderEntity>> AMMO_BOULDER = ENTITY.register("ammo_boulder", () -> EntityTypes.Builder.of((EntityTypes.IFactory<BoulderEntity>) BoulderEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_boulder"));

    // Boats
    public static final RegistryObject<EntityType<TolkienBoatEntity>> MALLORN_BOAT = ENTITY.register("boat_mallorn", () -> EntityTypes.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_mallorn").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> MIRKWOOD_BOAT = ENTITY.register("boat_mirkwood", () -> EntityTypes.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_mirkwood").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> CULUMALDA_BOAT = ENTITY.register("boat_culumalda", () -> EntityTypes.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_culumalda").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> LEBETHRON_BOAT = ENTITY.register("boat_lebethron", () -> EntityTypes.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_lebethron").toString()));

    // Items
    public static final RegistryObject<EntityType<MorgulCrystalEntity>> MORGUL_CRYSTAL = ENTITY.register("morgul_crystal", () -> EntityTypes.Builder.<MorgulCrystalEntity>of(MorgulCrystalEntity::new, MobCategory.MISC).sized(2.0F, 2.0F).clientTrackingRange(16).updateInterval(Integer.MAX_VALUE).build(MODID + ":morgul_crystal"));

    /* Spawn Eggs */
    // Ambient
    public static final RegistryObject<Item> EGG_TTMRAT = createSpawnEgg("entityttmrat", entityTTMRat, 9482106, 2301661);
    public static final RegistryObject<Item> EGG_TTMSQUIRREL = createSpawnEgg("entityttmsquirrel", entityTTMSquirrel, 9482106, 5600397);
    public static final RegistryObject<Item> EGG_TTMFROG = createSpawnEgg("entityttmfrog", entityTTMFrog, 9482106, 14289362);
    public static final RegistryObject<Item> EGG_TTMSWARM = createSpawnEgg("entityttmswarm", entityTTMSwarm, 9482106, 14088652);
    public static final RegistryObject<Item> EGG_TTMTHRUSH = createSpawnEgg("entityttmthrush", entityTTMThrush, 9482106, 9467561);
    public static final RegistryObject<Item> EGG_TTMCREBAIN = createSpawnEgg("entityttmcrebain", entityTTMCrebain, 9482106, 9226665);

    // Merchants
    public static final RegistryObject<Item> EGG_TTMHUMAN = createSpawnEgg("entityttmhuman", entityTTMHuman, 15186506, 2301661);
    public static final RegistryObject<Item> EGG_TTMDWARF = createSpawnEgg("entityttmdwarf", entityTTMDwarf, 15186506, 5600397);
    public static final RegistryObject<Item> EGG_TTMELVES = createSpawnEgg("entityttmelves", entityTTMElves, 15186506, 14289362);
    public static final RegistryObject<Item> EGG_TTMHOBBIT = createSpawnEgg("entityttmhobbit", entityTTMHobbit, 15186506, 14088652);
    public static final RegistryObject<Item> EGG_TTMDESERTDWELLER = createSpawnEgg("entityttmdesertdweller", entityTTMDesertDweller, 15186506, 9467561);

    // Monster
    public static final RegistryObject<Item> EGG_TTMGOBLIN = createSpawnEgg("entityttmgoblin", entityTTMGoblin, 585619, 2301661);
    public static final RegistryObject<Item> EGG_TTMBARROW = createSpawnEgg("entityttmbarrow", entityTTMBarrow, 585619, 5600397);
    public static final RegistryObject<Item> EGG_TTMBRIGAND = createSpawnEgg("entityttmbrigand", entityTTMBrigand, 585619, 14289362);
    public static final RegistryObject<Item> EGG_TTMDEEPCLAW = createSpawnEgg("entityttmdeepclaw", entityTTMDeepclaw, 585619, 14088652);
    public static final RegistryObject<Item> EGG_TTMTREEENT = createSpawnEgg("entityttmtreeent", entityTTMTreeEnt, 585619, 9467561);
    public static final RegistryObject<Item> EGG_TTMDUERGAR = createSpawnEgg("entityttmduergar", entityTTMDuergar, 585619, 9226665);
    public static final RegistryObject<Item> EGG_TTMFELLSPIRIT = createSpawnEgg("entityttmfellspirit", entityTTMFellSpirit, 585619, 7405383);
    public static final RegistryObject<Item> EGG_TTMSWAMPHAG = createSpawnEgg("entityttmswamphag", entityTTMSwampHag, 585619, 12659887);
    public static final RegistryObject<Item> EGG_TTMMIRKWOODSPIDER = createSpawnEgg("entityttmmirkwoodspider", entityTTMMirkwoodSpider, 585619, 16211457);
    public static final RegistryObject<Item> EGG_TTMHARADRIM = createSpawnEgg("entityttmharadrim", entityTTMHaradrim, 585619, 12198412);
    public static final RegistryObject<Item> EGG_TTMTROLL = createSpawnEgg("entityttmtroll", entityTTMTroll, 585619, 13146148);
    public static final RegistryObject<Item> EGG_TTMWARG = createSpawnEgg("entityttmwarg", entityTTMWarg, 585619, 2703752);
    public static final RegistryObject<Item> EGG_TTMMORDORORC = createSpawnEgg("entityttmmordororc", entityTTMMordorOrc, 585619, 8755748);
    public static final RegistryObject<Item> EGG_TTMHURON = createSpawnEgg("entityttmhuron", entityTTMHuron, 585619, 10600204);
    public static final RegistryObject<Item> EGG_TTMOATHBREAKER = createSpawnEgg("entityttmoathbreaker", entityTTMOathbreaker, 585619, 16121867);
    public static final RegistryObject<Item> EGG_TTMROMIEWALKER = createSpawnEgg("entityttmromiewalker", entityTTMRomieWalker, 585619, 16739362);
    public static final RegistryObject<Item> EGG_TTMURUKHAI = createSpawnEgg("entityttmurukhai", entityTTMUrukHai, 585619, 698898);
    public static final RegistryObject<Item> EGG_TTMELEMENTALGOLEM = createSpawnEgg("entityttmelementalgolem", entityTTMElementalGolem, 585619, 1380646);
    public static final RegistryObject<Item> EGG_TTMMINOTAUR = createSpawnEgg("entityttmminotaur", entityTTMMinotaur, 585619, 2973229);
    public static final RegistryObject<Item> EGG_TTMMIMICCHEST = createSpawnEgg("entityttmmimicchest", entityTTMMimicChest, 585619, 8807990);

    // Boss
    public static final RegistryObject<Item> EGG_TTMGOBLINKING = createSpawnEgg("entityttmgoblinking", entityTTMGoblinKing, 15673963, 2301661);
    public static final RegistryObject<Item> EGG_TTMMITHRILGOLEM = createSpawnEgg("entityttmmithrilgolem", entityTTMMithrilGolem, 15673963, 5600397);
    public static final RegistryObject<Item> EGG_TTMMORGULIRONGOLEM = createSpawnEgg("entityttmmorgulirongolem", entityTTMMorgulIronGolem, 15673963, 14289362);
    public static final RegistryObject<Item> EGG_TTMWITCHKING = createSpawnEgg("entityttmwitchking", entityTTMWitchKing, 15673963, 14088652);
    public static final RegistryObject<Item> EGG_TTMSHELOB = createSpawnEgg("entityttmshelob", entityTTMShelob, 15673963, 9467561);
    public static final RegistryObject<Item> EGG_TTMBALROG = createSpawnEgg("entityttmbalrog", entityTTMBalrog, 15673963, 9226665);
    public static final RegistryObject<Item> EGG_TTMWATCHER = createSpawnEgg("entityttmwatcher", entityTTMWatcher, 15673963, 7405383);
    public static final RegistryObject<Item> EGG_TTMGWAHIR = createSpawnEgg("entityttmgwahir", entityTTMGwahir, 15673963, 12659887);
    public static final RegistryObject<Item> EGG_TTMFELLBEAST = createSpawnEgg("entityttmfellbeast", entityFellBeast, 15673963, 16211457);

    // Passive
    public static final RegistryObject<Item> EGG_TTMAUROCH = createSpawnEgg("entityttmauroch", entityTTMAuroch, 7668978, 2301661);
    public static final RegistryObject<Item> EGG_TTMMUMAKIL = createSpawnEgg("entityttmmumakil", entityTTMMumakil, 7668978, 5600397);
    public static final RegistryObject<Item> EGG_TTMGOAT = createSpawnEgg("entityttmgoat", entityTTMGoat, 7668978, 14289362);

    // Special
    public static final RegistryObject<Item> EGG_TTMSHADOWFAX = createSpawnEgg("entityttmshadowfax", entityTTMShadowfax, 2576351, 2301661);
    public static final RegistryObject<Item> EGG_TTMGOLLUM = createSpawnEgg("entityttmgollum", entityTTMGollum, 2576351, 5600397);
    public static final RegistryObject<Item> EGG_TTMNAZGUL = createSpawnEgg("entityttmnazgul", entityTTMNazgul, 2576351, 14289362);
    public static final RegistryObject<Item> EGG_TTMNAZGULSTEED = createSpawnEgg("entityttmnazgulsteed", entityTTMNazgulSteed, 2576351, 14088652);
    public static final RegistryObject<Item> EGG_TTMGREATEAGLE = createSpawnEgg("entityttmgreateagle", entityTTMGreatEagle, 2576351, 9467561);

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        /* Attribute Registry */
        // Ambient
        event.put(ENTITY_TTM_RAT.get(), RatEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SQUIRREL.get(), SquirrelEntity.registerAttributes().build());
        event.put(ENTITY_TTM_FROG.get(), SquirrelEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SWARM.get(), SwarmEntity.registerAttributes().build());
        event.put(ENTITY_TTM_THRUSH.get(), ThrushEntity.registerAttributes().build());
        event.put(ENTITY_TTM_CREBAIN.get(), CrebainEntity.registerAttributes().build());

        // Merchants
        event.put(ENTITY_TTM_HUMAN.get(), HumanEntity.registerAttributes().build());
        event.put(ENTITY_TTM_DWARF.get(), DwarfEntity.registerAttributes().build());
        event.put(ENTITY_TTM_ELVES.get(), ElvesEntity.registerAttributes().build());
        event.put(ENTITY_TTM_HOBBIT.get(), HobbitEntity.registerAttributes().build());
        event.put(ENTITY_TTM_DESERTDWELLER.get(), DesertDwellerEntity.registerAttributes().build());

        // Monster
        event.put(ENTITY_TTM_GOBLIN.get(), GoblinEntity.registerAttributes().build());
        event.put(ENTITY_TTM_BARROW.get(), BarrowWightEntity.registerAttributes().build());
        event.put(ENTITY_TTM_BRIGAND.get(), BrigandEntity.registerAttributes().build());
        event.put(ENTITY_TTM_DEEPCLAW.get(), DeepClawEntity.registerAttributes().build());
        event.put(ENTITY_TTM_TREEENT.get(), TreeEntEntity.registerAttributes().build());
        event.put(ENTITY_TTM_DUERGAR.get(), DuergarEntity.registerAttributes().build());
        event.put(ENTITY_TTM_FELLSPIRIT.get(), FellSpiritEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SWAMPHAG.get(), SwampHagEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MIRKWOODSPIDER.get(), MirkwoodSpiderEntity.registerAttributes().build());
        event.put(ENTITY_TTM_HARADRIM.get(), HaradrimEntity.registerAttributes().build());
        event.put(ENTITY_TTM_TROLL.get(), TrollEntity.registerAttributes().build());
        event.put(ENTITY_TTM_WARG.get(), WargEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MORDORORC.get(), MordorOrcEntity.registerAttributes().build());
        event.put(ENTITY_TTM_HURON.get(), HuronEntity.registerAttributes().build());
        event.put(ENTITY_TTM_OATHBREAKER.get(), OathbreakerEntity.registerAttributes().build());
        event.put(ENTITY_TTM_ROMIEWALKER.get(), RomieWalkerEntity.registerAttributes().build());
        event.put(ENTITY_TTM_URUKHAI.get(), UrukHaiEntity.registerAttributes().build());
        event.put(ENTITY_TTM_ELEMENTALGOLEM.get(), ElementalGolemEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MINOTAUR.get(), MinotaurEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MIMICCHEST.get(), MimicChestEntity.registerAttributes().build());

        // Boss
        event.put(ENTITY_TTM_GOBLINKING.get(), GoblinKingEntity.registerAttributes().build());
        event.put(ENTITY_FELL_BEAST.get(), FellBeastEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MITHRILGOLEM.get(), MithrilGolemEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MORGULIRONGOLEM.get(), MorgulIronGolemEntity.registerAttributes().build());
        event.put(ENTITY_TTM_WITCHKING.get(), WitchKingEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SHELOB.get(), ShelobEntity.registerAttributes().build());
        event.put(ENTITY_TTM_BALROG.get(), BalrogEntity.registerAttributes().build());
        event.put(ENTITY_TTM_WATCHER.get(), WatcherEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GWAHIR.get(), GwahirEntity.registerAttributes().build());

        // Passive
        event.put(ENTITY_TTM_AUROCH.get(), AurochEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MUMAKIL.get(), MumakilEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GOAT.get(), GoatEntity.registerAttributes().build());

        // Special
        event.put(ENTITY_TTM_SHADOWFAX.get(), ShadowfaxEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GOLLUM.get(), GollumEntity.registerAttributes().build());
        event.put(ENTITY_TTM_NAZGUL.get(), NazgulEntity.registerAttributes().build());
        event.put(ENTITY_TTM_NAZGULSTEED.get(), NazgulSteedEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GREAT_EAGLE.get(), GreatEagleEntity.registerAttributes().build());
    }

    public static void registerSpawnPlacement() {
        //Look in SpawnPlacements for examples
        // Ambient
        SpawnPlacements.register(ENTITY_TTM_SQUIRREL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::checkTTMAmbientSpawn);
        SpawnPlacements.register(ENTITY_TTM_FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FrogEntity::checkFrogSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_THRUSH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ThrushEntity::checkThrushSpawn);
        SpawnPlacements.register(ENTITY_TTM_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SwarmEntity::checkSwarmSpawn);
        SpawnPlacements.register(ENTITY_TTM_RAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::checkTTMAmbientSpawn);
        SpawnPlacements.register(ENTITY_TTM_CREBAIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrebainEntity::checkCrebainSpawn);

        // Merchants
        SpawnPlacements.register(ENTITY_TTM_ELVES.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DWARF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HUMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HOBBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DESERTDWELLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);

        //Monster
        SpawnPlacements.register(ENTITY_TTM_GOBLIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_BARROW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_BRIGAND.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DEEPCLAW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_TREEENT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TreeEntEntity::checkTreeEntSpawn);
        SpawnPlacements.register(ENTITY_TTM_DUERGAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_FELLSPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_SWAMPHAG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_MIRKWOODSPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HARADRIM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_TROLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_WARG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_MORDORORC.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HURON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_OATHBREAKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_ROMIEWALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_URUKHAI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_ELEMENTALGOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_MINOTAUR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MinotaurEntity::checkMinotaurSpawn);
        SpawnPlacements.register(ENTITY_TTM_MIMICCHEST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);

        // Boss

        //Passive
        SpawnPlacements.register(ENTITY_TTM_MUMAKIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MumakilEntity::checkMumakilSpawn);
        SpawnPlacements.register(ENTITY_TTM_AUROCH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_GOAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        // Special
        SpawnPlacements.register(ENTITY_TTM_SHADOWFAX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_GOLLUM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GollumEntity::checkGollumSpawn);
        SpawnPlacements.register(ENTITY_TTM_NAZGUL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NazgulEntity::checkNazgulSpawn);
        SpawnPlacements.register(ENTITY_TTM_NAZGULSTEED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    // Helper Methods
    public static RegistryObject<Item> createSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> entityType, int primaryColor, int secondaryColor ) {
        return SPAWN_EGGS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Properties().tab(spawnGroup)));
    }

    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.EntityFactory<T> factory, MobCategory classification, float size1, float size2) {
        return makeBuilder(factory, classification).sized(size1, size2).build(name);
    }
    private static<T extends Entity> EntityType<T> buildFireEntity(String name, EntityType.EntityFactory<T> factory, MobCategory classification, float size1, float size2) {
        return makeBuilder(factory, classification).fireImmune().sized(size1, size2).build(name);
    }
    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.EntityFactory<T> factory, MobCategory classification) {
        return makeBuilder(factory, classification).build(name);
    }

    private static<T extends Entity> EntityType.Builder<T> makeBuilder(EntityType.EntityFactory<T> factory, MobCategory classification) {
        return EntityType.Builder.of(factory, classification);
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Entities";
    }
}
