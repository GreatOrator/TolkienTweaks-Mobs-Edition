package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.entity.AmbientEntity;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.boss.*;
import com.greatorator.tolkienmobs.entity.merchant.*;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMAuroch;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMGoat;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMMumakil;
import com.greatorator.tolkienmobs.entity.special.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TTMContent.spawnGroup;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class EntityGenerator {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    //#################################################################
    // Entity Registry
    //#################################################################
    // Ambient
    private static final EntityType<RatEntity> entityTTMRat = buildEntity("entityttmrat", RatEntity::new, EntityClassification.CREATURE, 0.75F, 0.5F);
    public static final RegistryObject<EntityType<RatEntity>> ENTITY_TTM_RAT = ENTITY.register("entityttmrat", () -> entityTTMRat);
    private static final EntityType<SquirrelEntity> entityTTMSquirrel = buildEntity("entityttmsquirrel", SquirrelEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<SquirrelEntity>> ENTITY_TTM_SQUIRREL = ENTITY.register("entityttmsquirrel", () -> entityTTMSquirrel);
    private static final EntityType<FrogEntity> entityTTMFrog = buildEntity("entityttmfrog", FrogEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<FrogEntity>> ENTITY_TTM_FROG = ENTITY.register("entityttmfrog", () -> entityTTMFrog);
    private static final EntityType<SwarmEntity> entityTTMSwarm = buildEntity("entityttmswarm", SwarmEntity::new, EntityClassification.CREATURE, 0.7F, 1.9F);
    public static final RegistryObject<EntityType<SwarmEntity>> ENTITY_TTM_SWARM = ENTITY.register("entityttmswarm", () -> entityTTMSwarm);
    private static final EntityType<ThrushEntity> entityTTMThrush = buildEntity("entityttmthrush", ThrushEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<ThrushEntity>> ENTITY_TTM_THRUSH = ENTITY.register("entityttmthrush", () -> entityTTMThrush);
    private static final EntityType<CrebainEntity> entityTTMCrebain = buildEntity("entityttmcrebain", CrebainEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<CrebainEntity>> ENTITY_TTM_CREBAIN = ENTITY.register("entityttmcrebain", () -> entityTTMCrebain);

    // Merchants
    private static final EntityType<EntityTTMHuman> entityTTMHuman = buildEntity("entityttmhuman", EntityTTMHuman::new, EntityClassification.MISC, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMHuman>> ENTITY_TTM_HUMAN = ENTITY.register("entityttmhuman", () -> entityTTMHuman);
    private static final EntityType<EntityTTMDwarf> entityTTMDwarf = buildEntity("entityttmdwarf", EntityTTMDwarf::new, EntityClassification.MISC, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMDwarf>> ENTITY_TTM_DWARF = ENTITY.register("entityttmdwarf", () -> entityTTMDwarf);
    private static final EntityType<EntityTTMElves> entityTTMElves = buildEntity("entityttmelves", EntityTTMElves::new, EntityClassification.MISC, 0.9F, 2.0F);
    public static final RegistryObject<EntityType<EntityTTMElves>> ENTITY_TTM_ELVES = ENTITY.register("entityttmelves", () -> entityTTMElves);
    private static final EntityType<EntityTTMHobbit> entityTTMHobbit = buildEntity("entityttmhobbit", EntityTTMHobbit::new, EntityClassification.MISC, 0.6F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMHobbit>> ENTITY_TTM_HOBBIT = ENTITY.register("entityttmhobbit", () -> entityTTMHobbit);
    private static final EntityType<EntityTTMDesertDweller> entityTTMDesertDweller = buildEntity("entityttmdesertdweller", EntityTTMDesertDweller::new, EntityClassification.MISC, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMDesertDweller>> ENTITY_TTM_DESERTDWELLER = ENTITY.register("entityttmdesertdweller", () -> entityTTMDesertDweller);

    // Monster
    private static final EntityType<EntityTTMGoblin> entityTTMGoblin = buildEntity("entityttmgoblin", EntityTTMGoblin::new, EntityClassification.MONSTER, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<EntityTTMGoblin>> ENTITY_TTM_GOBLIN = ENTITY.register("entityttmgoblin", () -> entityTTMGoblin);
    private static final EntityType<EntityTTMBarrowWight> entityTTMBarrow = buildEntity("entityttmbarrow", EntityTTMBarrowWight::new, EntityClassification.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMBarrowWight>> ENTITY_TTM_BARROW = ENTITY.register("entityttmbarrow", () -> entityTTMBarrow);
    private static final EntityType<EntityTTMBrigand> entityTTMBrigand = buildEntity("entityttmbrigand", EntityTTMBrigand::new, EntityClassification.MONSTER, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMBrigand>> ENTITY_TTM_BRIGAND = ENTITY.register("entityttmbrigand", () -> entityTTMBrigand);
    private static final EntityType<EntityTTMDeepClaw> entityTTMDeepclaw = buildEntity("entityttmdeepclaw", EntityTTMDeepClaw::new, EntityClassification.MONSTER, 0.7F, 1.1F);
    public static final RegistryObject<EntityType<EntityTTMDeepClaw>> ENTITY_TTM_DEEPCLAW = ENTITY.register("entityttmdeepclaw", () -> entityTTMDeepclaw);
    private static final EntityType<EntityTTMTreeEnt> entityTTMTreeEnt = buildEntity("entityttmtreeent", EntityTTMTreeEnt::new, EntityClassification.MONSTER, 1.35F, 5.5F);
    public static final RegistryObject<EntityType<EntityTTMTreeEnt>> ENTITY_TTM_TREEENT = ENTITY.register("entityttmtreeent", () -> entityTTMTreeEnt);
    private static final EntityType<EntityTTMDuergar> entityTTMDuergar = buildEntity("entityttmduergar", EntityTTMDuergar::new, EntityClassification.MONSTER, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMDuergar>> ENTITY_TTM_DUERGAR = ENTITY.register("entityttmduergar", () -> entityTTMDuergar);
    private static final EntityType<EntityTTMFellSpirit> entityTTMFellSpirit = buildEntity("entityttmfellspirit", EntityTTMFellSpirit::new, EntityClassification.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMFellSpirit>> ENTITY_TTM_FELLSPIRIT = ENTITY.register("entityttmfellspirit", () -> entityTTMFellSpirit);
    private static final EntityType<EntityTTMSwampHag> entityTTMSwampHag = buildEntity("entityttmswamphag", EntityTTMSwampHag::new, EntityClassification.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMSwampHag>> ENTITY_TTM_SWAMPHAG = ENTITY.register("entityttmswamphag", () -> entityTTMSwampHag);
    private static final EntityType<EntityTTMMirkwoodSpider> entityTTMMirkwoodSpider = buildEntity("entityttmmirkwoodspider", EntityTTMMirkwoodSpider::new, EntityClassification.MONSTER, 1.4F, 0.9F);
    public static final RegistryObject<EntityType<EntityTTMMirkwoodSpider>> ENTITY_TTM_MIRKWOODSPIDER = ENTITY.register("entityttmmirkwoodspider", () -> entityTTMMirkwoodSpider);
    private static final EntityType<EntityTTMHaradrim> entityTTMHaradrim = buildEntity("entityttmharadrim", EntityTTMHaradrim::new, EntityClassification.MONSTER, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMHaradrim>> ENTITY_TTM_HARADRIM = ENTITY.register("entityttmharadrim", () -> entityTTMHaradrim);
    private static final EntityType<EntityTTMTroll> entityTTMTroll = buildEntity("entityttmtroll", EntityTTMTroll::new, EntityClassification.MONSTER, 3.4F, 4.6F);
    public static final RegistryObject<EntityType<EntityTTMTroll>> ENTITY_TTM_TROLL = ENTITY.register("entityttmtroll", () -> entityTTMTroll);
    private static final EntityType<EntityTTMWarg> entityTTMWarg = buildEntity("entityttmwarg", EntityTTMWarg::new, EntityClassification.MONSTER, 1.5F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMWarg>> ENTITY_TTM_WARG = ENTITY.register("entityttmwarg", () -> entityTTMWarg);
    private static final EntityType<EntityTTMMordorOrc> entityTTMMordorOrc = buildEntity("entityttmmordororc", EntityTTMMordorOrc::new, EntityClassification.MONSTER, 1.5F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMMordorOrc>> ENTITY_TTM_MORDORORC = ENTITY.register("entityttmmordororc", () -> entityTTMMordorOrc);
    private static final EntityType<EntityTTMHuron> entityTTMHuron = buildEntity("entityttmhuron", EntityTTMHuron::new, EntityClassification.MONSTER, 1.5F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMHuron>> ENTITY_TTM_HURON = ENTITY.register("entityttmhuron", () -> entityTTMHuron);
    private static final EntityType<EntityTTMOathbreaker> entityTTMOathbreaker = buildEntity("entityttmoathbreaker", EntityTTMOathbreaker::new, EntityClassification.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMOathbreaker>> ENTITY_TTM_OATHBREAKER = ENTITY.register("entityttmoathbreaker", () -> entityTTMOathbreaker);
    private static final EntityType<EntityTTMRomieWalker> entityTTMRomieWalker = buildEntity("entityttmromiewalker", EntityTTMRomieWalker::new, EntityClassification.MONSTER, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMRomieWalker>> ENTITY_TTM_ROMIEWALKER = ENTITY.register("entityttmromiewalker", () -> entityTTMRomieWalker);
    private static final EntityType<EntityTTMUrukHai> entityTTMUrukHai = buildEntity("entityttmurukhai", EntityTTMUrukHai::new, EntityClassification.MONSTER, 1.0F, 2.6F);
    public static final RegistryObject<EntityType<EntityTTMUrukHai>> ENTITY_TTM_URUKHAI = ENTITY.register("entityttmurukhai", () -> entityTTMUrukHai);
    private static final EntityType<EntityTTMElementalGolem> entityTTMElementalGolem = buildEntity("entityttmelementalgolem", EntityTTMElementalGolem::new, EntityClassification.MONSTER, 1.8F, 3.1F);
    public static final RegistryObject<EntityType<EntityTTMElementalGolem>> ENTITY_TTM_ELEMENTALGOLEM = ENTITY.register("entityttmelementalgolem", () -> entityTTMElementalGolem);
    private static final EntityType<EntityTTMMinotaur> entityTTMMinotaur = buildEntity("entityttmminotaur", EntityTTMMinotaur::new, EntityClassification.MONSTER, 1.8F, 4.0F);
    public static final RegistryObject<EntityType<EntityTTMMinotaur>> ENTITY_TTM_MINOTAUR = ENTITY.register("entityttmminotaur", () -> entityTTMMinotaur);
    private static final EntityType<EntityTTMMimicChest> entityTTMMimicChest = buildEntity("entityttmmimicchest", EntityTTMMimicChest::new, EntityClassification.MONSTER, 1.0F, 1.7F);
    public static final RegistryObject<EntityType<EntityTTMMimicChest>> ENTITY_TTM_MIMICCHEST = ENTITY.register("entityttmmimicchest", () -> entityTTMMimicChest);

    // Boss
    private static final EntityType<GoblinKingEntity> entityTTMGoblinKing = buildEntity("entityttmgoblinking", GoblinKingEntity::new, EntityClassification.MONSTER, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<GoblinKingEntity>> ENTITY_TTM_GOBLINKING = ENTITY.register("entityttmgoblinking", () -> entityTTMGoblinKing);
    private static final EntityType<MithrilGolemEntity> entityTTMMithrilGolem = buildEntity("entityttmmithrilgolem", MithrilGolemEntity::new, EntityClassification.MONSTER, 1.8F, 3.1F);
    public static final RegistryObject<EntityType<MithrilGolemEntity>> ENTITY_TTM_MITHRILGOLEM = ENTITY.register("entityttmmithrilgolem", () -> entityTTMMithrilGolem);
    private static final EntityType<MorgulIronGolemEntity> entityTTMMorgulIronGolem = buildEntity("entityttmmorgulirongolem", MorgulIronGolemEntity::new, EntityClassification.MONSTER, 1.8F, 3.1F);
    public static final RegistryObject<EntityType<MorgulIronGolemEntity>> ENTITY_TTM_MORGULIRONGOLEM = ENTITY.register("entityttmmorgulirongolem", () -> entityTTMMorgulIronGolem);
    private static final EntityType<WitchKingEntity> entityTTMWitchKing = buildEntity("entityttmwitchking", WitchKingEntity::new, EntityClassification.MONSTER, 1.3F, 2.2F);
    public static final RegistryObject<EntityType<WitchKingEntity>> ENTITY_TTM_WITCHKING = ENTITY.register("entityttmwitchking", () -> entityTTMWitchKing);
    private static final EntityType<ShelobEntity> entityTTMShelob = buildEntity("entityttmshelob", ShelobEntity::new, EntityClassification.MONSTER, 2.3F, 1.1F);
    public static final RegistryObject<EntityType<ShelobEntity>> ENTITY_TTM_SHELOB = ENTITY.register("entityttmshelob", () -> entityTTMShelob);
    private static final EntityType<BalrogEntity> entityTTMBalrog = buildFireEntity("entityttmbalrog", BalrogEntity::new, EntityClassification.MONSTER, 2.3F, 3.5F);
    public static final RegistryObject<EntityType<BalrogEntity>> ENTITY_TTM_BALROG = ENTITY.register("entityttmbalrog", () -> entityTTMBalrog);
    private static final EntityType<WatcherEntity> entityTTMWatcher = buildEntity("entityttmwatcher", WatcherEntity::new, EntityClassification.WATER_CREATURE, 2.3F, 3.5F);
    public static final RegistryObject<EntityType<WatcherEntity>> ENTITY_TTM_WATCHER = ENTITY.register("entityttmwatcher", () -> entityTTMWatcher);
    private static final EntityType<GwahirEntity> entityTTMGwahir = buildEntity("entityttmgwahir", GwahirEntity::new, EntityClassification.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<GwahirEntity>> ENTITY_TTM_GWAHIR = ENTITY.register("entityttmgwahir", () -> entityTTMGwahir);

    // Passive
    private static final EntityType<EntityTTMAuroch> entityTTMAuroch = buildEntity("entityttmauroch", EntityTTMAuroch::new, EntityClassification.CREATURE, 1.9F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMAuroch>> ENTITY_TTM_AUROCH = ENTITY.register("entityttmauroch", () -> entityTTMAuroch);
    private static final EntityType<EntityTTMMumakil> entityTTMMumakil = buildEntity("entityttmmumakil", EntityTTMMumakil::new, EntityClassification.CREATURE, 3.0F, 3.0F);
    public static final RegistryObject<EntityType<EntityTTMMumakil>> ENTITY_TTM_MUMAKIL = ENTITY.register("entityttmmumakil", () -> entityTTMMumakil);
    private static final EntityType<EntityTTMGoat> entityTTMGoat = buildEntity("entityttmgoat", EntityTTMGoat::new, EntityClassification.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMGoat>> ENTITY_TTM_GOAT = ENTITY.register("entityttmgoat", () -> entityTTMGoat);

    // Special
    private static final EntityType<ShadowfaxEntity> entityTTMShadowfax = buildEntity("entityttmshadowfax", ShadowfaxEntity::new, EntityClassification.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<ShadowfaxEntity>> ENTITY_TTM_SHADOWFAX = ENTITY.register("entityttmshadowfax", () -> entityTTMShadowfax);
    private static final EntityType<GollumEntity> entityTTMGollum = buildEntity("entityttmgollum", GollumEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
    public static final RegistryObject<EntityType<GollumEntity>> ENTITY_TTM_GOLLUM = ENTITY.register("entityttmgollum", () -> entityTTMGollum);
    private static final EntityType<NazgulEntity> entityTTMNazgul = buildEntity("entityttmnazgul", NazgulEntity::new, EntityClassification.MONSTER, 1.3F, 2.2F);
    public static final RegistryObject<EntityType<NazgulEntity>> ENTITY_TTM_NAZGUL = ENTITY.register("entityttmnazgul", () -> entityTTMNazgul);
    private static final EntityType<NazgulSteedEntity> entityTTMNazgulSteed = buildEntity("entityttmnazgulsteed", NazgulSteedEntity::new, EntityClassification.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<NazgulSteedEntity>> ENTITY_TTM_NAZGULSTEED = ENTITY.register("entityttmnazgulsteed", () -> entityTTMNazgulSteed);
    private static final EntityType<GreatEagleEntity> entityTTMGreatEagle = buildEntity("entityttmgreateagle", GreatEagleEntity::new, EntityClassification.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<GreatEagleEntity>> ENTITY_TTM_GREAT_EAGLE = ENTITY.register("entityttmgreateagle", () -> entityTTMGreatEagle);

    // Ammo
    public static final RegistryObject<EntityType<?>> AMMO_ARROW_GALADHRIM = ENTITY.register("ammo_arrow_galadhrim", () -> EntityType.Builder.of(EntityGaladhrimArrow::new, EntityClassification.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityGaladhrimArrow::new).build(MODID + ":ammo_arrow_galadhrim"));
    public static final RegistryObject<EntityType<?>> AMMO_FELLBEAST_FIREBALL = ENTITY.register("ammo_fellbeast_fireball", () -> EntityType.Builder.of(EntityFellBeastFireball::new, EntityClassification.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityFellBeastFireball::new).build(MODID + ":ammo_fellbeast_fireball"));
    public static final RegistryObject<EntityType<?>> AMMO_BOULDER = ENTITY.register("ammo_boulder", () -> EntityType.Builder.of(EntityBoulder::new, EntityClassification.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityBoulder::new).build(MODID + ":ammo_boulder"));

    public static void registerSpawnPlacement() {
        //Look in EntitySpawnPlacementRegistry for examples
        // Ambient
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::checkTTMAmbientSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FrogEntity::checkFrogSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_THRUSH.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ThrushEntity::checkThrushSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_SWARM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SwarmEntity::checkSwarmSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_RAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::checkTTMAmbientSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_CREBAIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrebainEntity::checkCrebainSpawn);

        // Merchants
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_ELVES.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_DWARF.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_HUMAN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_HOBBIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_DESERTDWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);

        //Monster
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_GOBLIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_BARROW.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_BRIGAND.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_DEEPCLAW.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_TREEENT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMTreeEnt::checkTreeEntSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_DUERGAR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_FELLSPIRIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_SWAMPHAG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_MIRKWOODSPIDER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_HARADRIM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_TROLL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_WARG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_MORDORORC.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_HURON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_OATHBREAKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_ROMIEWALKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_URUKHAI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_ELEMENTALGOLEM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_MINOTAUR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMMinotaur::checkMinotaurSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_MIMICCHEST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);

        // Boss

        //Passive
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_MUMAKIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMMumakil::checkMumakilSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_AUROCH.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_GOAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);

        // Special
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_SHADOWFAX.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_GOLLUM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GollumEntity::checkGollumSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_NAZGUL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NazgulEntity::checkNazgulSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_NAZGULSTEED.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);

        //Look in EntitySpawnPlacementRegistry for examples
    }

    //#################################################################
    // Attribute Registry
    //#################################################################

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // Ambient
        event.put(ENTITY_TTM_RAT.get(), RatEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SQUIRREL.get(), SquirrelEntity.registerAttributes().build());
        event.put(ENTITY_TTM_FROG.get(), SquirrelEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SWARM.get(), SwarmEntity.registerAttributes().build());
        event.put(ENTITY_TTM_THRUSH.get(), ThrushEntity.registerAttributes().build());
        event.put(ENTITY_TTM_CREBAIN.get(), CrebainEntity.registerAttributes().build());

        // Merchants
        event.put(ENTITY_TTM_HUMAN.get(), EntityTTMHuman.registerAttributes().build());
        event.put(ENTITY_TTM_DWARF.get(), EntityTTMDwarf.registerAttributes().build());
        event.put(ENTITY_TTM_ELVES.get(), EntityTTMElves.registerAttributes().build());
        event.put(ENTITY_TTM_HOBBIT.get(), EntityTTMHobbit.registerAttributes().build());
        event.put(ENTITY_TTM_DESERTDWELLER.get(), EntityTTMDesertDweller.registerAttributes().build());

        // Monster
        event.put(ENTITY_TTM_GOBLIN.get(), EntityTTMGoblin.registerAttributes().build());
        event.put(ENTITY_TTM_BARROW.get(), EntityTTMBarrowWight.registerAttributes().build());
        event.put(ENTITY_TTM_BRIGAND.get(), EntityTTMBrigand.registerAttributes().build());
        event.put(ENTITY_TTM_DEEPCLAW.get(), EntityTTMDeepClaw.registerAttributes().build());
        event.put(ENTITY_TTM_TREEENT.get(), EntityTTMTreeEnt.registerAttributes().build());
        event.put(ENTITY_TTM_DUERGAR.get(), EntityTTMDuergar.registerAttributes().build());
        event.put(ENTITY_TTM_FELLSPIRIT.get(), EntityTTMFellSpirit.registerAttributes().build());
        event.put(ENTITY_TTM_SWAMPHAG.get(), EntityTTMSwampHag.registerAttributes().build());
        event.put(ENTITY_TTM_MIRKWOODSPIDER.get(), EntityTTMMirkwoodSpider.registerAttributes().build());
        event.put(ENTITY_TTM_HARADRIM.get(), EntityTTMHaradrim.registerAttributes().build());
        event.put(ENTITY_TTM_TROLL.get(), EntityTTMTroll.registerAttributes().build());
        event.put(ENTITY_TTM_WARG.get(), EntityTTMWarg.registerAttributes().build());
        event.put(ENTITY_TTM_MORDORORC.get(), EntityTTMMordorOrc.registerAttributes().build());
        event.put(ENTITY_TTM_HURON.get(), EntityTTMHuron.registerAttributes().build());
        event.put(ENTITY_TTM_OATHBREAKER.get(), EntityTTMOathbreaker.registerAttributes().build());
        event.put(ENTITY_TTM_ROMIEWALKER.get(), EntityTTMRomieWalker.registerAttributes().build());
        event.put(ENTITY_TTM_URUKHAI.get(), EntityTTMUrukHai.registerAttributes().build());
        event.put(ENTITY_TTM_ELEMENTALGOLEM.get(), EntityTTMElementalGolem.registerAttributes().build());
        event.put(ENTITY_TTM_MINOTAUR.get(), EntityTTMMinotaur.registerAttributes().build());
        event.put(ENTITY_TTM_MIMICCHEST.get(), EntityTTMMimicChest.registerAttributes().build());

        // Boss
        event.put(ENTITY_TTM_GOBLINKING.get(), GoblinKingEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MITHRILGOLEM.get(), MithrilGolemEntity.registerAttributes().build());
        event.put(ENTITY_TTM_MORGULIRONGOLEM.get(), MorgulIronGolemEntity.registerAttributes().build());
        event.put(ENTITY_TTM_WITCHKING.get(), WitchKingEntity.registerAttributes().build());
        event.put(ENTITY_TTM_SHELOB.get(), ShelobEntity.registerAttributes().build());
        event.put(ENTITY_TTM_BALROG.get(), BalrogEntity.registerAttributes().build());
        event.put(ENTITY_TTM_WATCHER.get(), WatcherEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GWAHIR.get(), GwahirEntity.registerAttributes().build());

        // Passive
        event.put(ENTITY_TTM_AUROCH.get(), EntityTTMAuroch.registerAttributes().build());
        event.put(ENTITY_TTM_MUMAKIL.get(), EntityTTMMumakil.registerAttributes().build());
        event.put(ENTITY_TTM_GOAT.get(), EntityTTMGoat.registerAttributes().build());

        // Special
        event.put(ENTITY_TTM_SHADOWFAX.get(), ShadowfaxEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GOLLUM.get(), GollumEntity.registerAttributes().build());
        event.put(ENTITY_TTM_NAZGUL.get(), NazgulEntity.registerAttributes().build());
        event.put(ENTITY_TTM_NAZGULSTEED.get(), NazgulSteedEntity.registerAttributes().build());
        event.put(ENTITY_TTM_GREAT_EAGLE.get(), GreatEagleEntity.registerAttributes().build());
    }

    //#################################################################
    // Spawn Eggs
    //#################################################################
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

    // Helper Methods
    public static RegistryObject<Item> createSpawnEgg(String name, EntityType< ? > entityType, int primaryColor, int secondaryColor ) {
        return SPAWN_EGGS.register(name + "_spawn_egg", () -> new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Properties().tab(spawnGroup)));
    }

    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.IFactory<T> factory, EntityClassification classification, float size1, float size2) {
        return makeBuilder(factory, classification).sized(size1, size2).build(name);
    }
    private static<T extends Entity> EntityType<T> buildFireEntity(String name, EntityType.IFactory<T> factory, EntityClassification classification, float size1, float size2) {
        return makeBuilder(factory, classification).fireImmune().sized(size1, size2).build(name);
    }
    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.IFactory<T> factory, EntityClassification classification) {
        return makeBuilder(factory, classification).build(name);
    }

    private static<T extends Entity> EntityType.Builder<T> makeBuilder(EntityType.IFactory<T> factory, EntityClassification classification) {
        return EntityType.Builder.of(factory, classification);
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Entities";
    }
}
