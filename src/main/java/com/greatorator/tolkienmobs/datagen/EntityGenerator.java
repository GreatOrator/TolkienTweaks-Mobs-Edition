package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.entity.EntityTTMAmbients;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.boss.EntityTTMGoblinKing;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMDwarf;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMElves;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMHobbit;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMHuman;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMAuroch;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMGoat;
import com.greatorator.tolkienmobs.entity.passive.EntityTTMMumakil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.gen.Heightmap;
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
    private static final EntityType<EntityTTMRat> entityTTMRat = buildEntity("entityttmrat", EntityTTMRat::new, EntityClassification.CREATURE, 0.75F, 0.5F);
    public static final RegistryObject<EntityType<EntityTTMRat>> ENTITY_TTM_RAT = ENTITY.register("entityttmrat", () -> entityTTMRat);
    private static final EntityType<EntityTTMSquirrel> entityTTMSquirrel = buildEntity("entityttmsquirrel", EntityTTMSquirrel::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<EntityTTMSquirrel>> ENTITY_TTM_SQUIRREL = ENTITY.register("entityttmsquirrel", () -> entityTTMSquirrel);
    private static final EntityType<EntityTTMFrog> entityTTMFrog = buildEntity("entityttmfrog", EntityTTMFrog::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<EntityTTMFrog>> ENTITY_TTM_FROG = ENTITY.register("entityttmfrog", () -> entityTTMFrog);
    private static final EntityType<EntityTTMSwarm> entityTTMSwarm = buildEntity("entityttmswarm", EntityTTMSwarm::new, EntityClassification.CREATURE, 0.7F, 1.9F);
    public static final RegistryObject<EntityType<EntityTTMSwarm>> ENTITY_TTM_SWARM = ENTITY.register("entityttmswarm", () -> entityTTMSwarm);
    private static final EntityType<EntityTTMThrush> entityTTMThrush = buildEntity("entityttmthrush", EntityTTMThrush::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<EntityTTMThrush>> ENTITY_TTM_THRUSH = ENTITY.register("entityttmthrush", () -> entityTTMThrush);

    // Merchants
    private static final EntityType<EntityTTMHuman> entityTTMHuman = buildEntity("entityttmhuman", EntityTTMHuman::new, EntityClassification.MISC, 0.7F, 1.85F);
    public static final RegistryObject<EntityType<EntityTTMHuman>> ENTITY_TTM_HUMAN = ENTITY.register("entityttmhuman", () -> entityTTMHuman);
    private static final EntityType<EntityTTMDwarf> entityTTMDwarf = buildEntity("entityttmdwarf", EntityTTMDwarf::new, EntityClassification.MISC, 0.7F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMDwarf>> ENTITY_TTM_DWARF = ENTITY.register("entityttmdwarf", () -> entityTTMDwarf);
    private static final EntityType<EntityTTMElves> entityTTMElves = buildEntity("entityttmelves", EntityTTMElves::new, EntityClassification.MISC, 0.9F, 2.0F);
    public static final RegistryObject<EntityType<EntityTTMElves>> ENTITY_TTM_ELVES = ENTITY.register("entityttmelves", () -> entityTTMElves);
    private static final EntityType<EntityTTMHobbit> entityTTMHobbit = buildEntity("entityttmhobbit", EntityTTMHobbit::new, EntityClassification.MISC, 0.6F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMHobbit>> ENTITY_TTM_HOBBIT = ENTITY.register("entityttmhobbit", () -> entityTTMHobbit);

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
    private static final EntityType<EntityTTMGoblinKing> entityTTMGoblinKing = buildEntity("entityttmgoblinking", EntityTTMGoblinKing::new, EntityClassification.MONSTER, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<EntityTTMGoblinKing>> ENTITY_TTM_GOBLINKING = ENTITY.register("entityttmgoblinking", () -> entityTTMGoblinKing);

    // Passive
    private static final EntityType<EntityTTMAuroch> entityTTMAuroch = buildEntity("entityttmauroch", EntityTTMAuroch::new, EntityClassification.CREATURE, 1.9F, 1.4F);
    public static final RegistryObject<EntityType<EntityTTMAuroch>> ENTITY_TTM_AUROCH = ENTITY.register("entityttmauroch", () -> entityTTMAuroch);
    private static final EntityType<EntityTTMMumakil> entityTTMMumakil = buildEntity("entityttmmumakil", EntityTTMMumakil::new, EntityClassification.CREATURE, 3.0F, 3.0F);
    public static final RegistryObject<EntityType<EntityTTMMumakil>> ENTITY_TTM_MUMAKIL = ENTITY.register("entityttmmumakil", () -> entityTTMMumakil);
    private static final EntityType<EntityTTMGoat> entityTTMGoat = buildEntity("entityttmgoat", EntityTTMGoat::new, EntityClassification.CREATURE, 1.3964844F, 1.6F);
    public static final RegistryObject<EntityType<EntityTTMGoat>> ENTITY_TTM_GOAT = ENTITY.register("entityttmgoat", () -> entityTTMGoat);

    // Ammo
    public static final RegistryObject<EntityType<?>> AMMO_ARROW_GALADHRIM = ENTITY.register("ammo_arrow_galadhrim", () -> EntityType.Builder.of(EntityGaladhrimArrow::new, EntityClassification.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityGaladhrimArrow::new).build(MODID + ":ammo_arrow_galadhrim"));
    public static final RegistryObject<EntityType<?>> AMMO_FELLBEAST_FIREBALL = ENTITY.register("ammo_fellbeast_fireball", () -> EntityType.Builder.of(EntityFellBeastFireball::new, EntityClassification.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityFellBeastFireball::new).build(MODID + ":ammo_fellbeast_fireball"));
    public static final RegistryObject<EntityType<?>> AMMO_BOULDER = ENTITY.register("ammo_boulder", () -> EntityType.Builder.of(EntityBoulder::new, EntityClassification.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityBoulder::new).build(MODID + ":ammo_boulder"));

    public static void registerSpawnPlacement() {
        //Look in EntitySpawnPlacementRegistry for examples
        // Ambient
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMAmbients::checkTTMAmbientSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMFrog::checkFrogSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_THRUSH.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMThrush::checkThrushSpawn);
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_SWARM.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTTMSwarm::checkSwarmSpawn);

        // Merchants
        EntitySpawnPlacementRegistry.register(EntityGenerator.ENTITY_TTM_ELVES.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);

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

        //Look in EntitySpawnPlacementRegistry for examples
    }

    //#################################################################
    // Attribute Registry
    //#################################################################
    public static void addEntityAttributes(){
        // Ambient
        GlobalEntityTypeAttributes.put(ENTITY_TTM_RAT.get(), EntityTTMRat.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_SQUIRREL.get(), EntityTTMSquirrel.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_FROG.get(), EntityTTMSquirrel.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_SWARM.get(), EntityTTMSwarm.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_THRUSH.get(), EntityTTMThrush.registerAttributes().build());

        // Merchants
        GlobalEntityTypeAttributes.put(ENTITY_TTM_HUMAN.get(), EntityTTMHuman.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_DWARF.get(), EntityTTMDwarf.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_ELVES.get(), EntityTTMElves.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_HOBBIT.get(), EntityTTMHobbit.registerAttributes().build());

        // Monster
        GlobalEntityTypeAttributes.put(ENTITY_TTM_GOBLIN.get(), EntityTTMGoblin.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_BARROW.get(), EntityTTMBarrowWight.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_BRIGAND.get(), EntityTTMBrigand.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_DEEPCLAW.get(), EntityTTMDeepClaw.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_TREEENT.get(), EntityTTMTreeEnt.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_DUERGAR.get(), EntityTTMDuergar.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_FELLSPIRIT.get(), EntityTTMFellSpirit.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_SWAMPHAG.get(), EntityTTMSwampHag.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_MIRKWOODSPIDER.get(), EntityTTMMirkwoodSpider.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_HARADRIM.get(), EntityTTMHaradrim.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_TROLL.get(), EntityTTMTroll.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_WARG.get(), EntityTTMWarg.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_MORDORORC.get(), EntityTTMMordorOrc.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_HURON.get(), EntityTTMHuron.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_OATHBREAKER.get(), EntityTTMOathbreaker.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_ROMIEWALKER.get(), EntityTTMRomieWalker.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_URUKHAI.get(), EntityTTMUrukHai.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_ELEMENTALGOLEM.get(), EntityTTMElementalGolem.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_MINOTAUR.get(), EntityTTMMinotaur.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_MIMICCHEST.get(), EntityTTMMimicChest.registerAttributes().build());

        // Boss
        GlobalEntityTypeAttributes.put(ENTITY_TTM_GOBLINKING.get(), EntityTTMGoblinKing.registerAttributes().build());

        // Passive
        GlobalEntityTypeAttributes.put(ENTITY_TTM_AUROCH.get(), EntityTTMAuroch.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_MUMAKIL.get(), EntityTTMMumakil.registerAttributes().build());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_GOAT.get(), EntityTTMGoat.registerAttributes().build());
    }

    //#################################################################
    // Spawn Eggs
    //#################################################################
    // Ambient
    public static final RegistryObject<Item> EGG_TTMRAT = createSpawnEgg("entityttmrat", entityTTMRat, 13354786, 2301661);
    public static final RegistryObject<Item> EGG_TTMSQUIRREL = createSpawnEgg("entityttmsquirrel", entityTTMSquirrel, 13354786, 5600397);
    public static final RegistryObject<Item> EGG_TTMFROG = createSpawnEgg("entityttmfrog", entityTTMFrog, 13354786, 14289362);
    public static final RegistryObject<Item> EGG_TTMSWARM = createSpawnEgg("entityttmswarm", entityTTMSwarm, 13354786, 14088652);
    public static final RegistryObject<Item> EGG_TTMTHRUSH = createSpawnEgg("entityttmthrush", entityTTMThrush, 13354786, 9467561);

    // Merchants
    public static final RegistryObject<Item> EGG_TTMHUMAN = createSpawnEgg("entityttmhuman", entityTTMHuman, 16426382, 2301661);
    public static final RegistryObject<Item> EGG_TTMDWARF = createSpawnEgg("entityttmdwarf", entityTTMDwarf, 16426382, 5600397);
    public static final RegistryObject<Item> EGG_TTMELVES = createSpawnEgg("entityttmelves", entityTTMElves, 16426382, 14289362);
    public static final RegistryObject<Item> EGG_TTMHOBBIT = createSpawnEgg("entityttmhobbit", entityTTMHobbit, 16426382, 7063795);

    // Monster
    public static final RegistryObject<Item> EGG_TTMGOBLIN = createSpawnEgg("entityttmgoblin", entityTTMGoblin, 14705521, 2301661);
    public static final RegistryObject<Item> EGG_TTMBARROW = createSpawnEgg("entityttmbarrow", entityTTMBarrow, 14705521, 5600397);
    public static final RegistryObject<Item> EGG_TTMBRIGAND = createSpawnEgg("entityttmbrigand", entityTTMBrigand, 14705521, 14289362);
    public static final RegistryObject<Item> EGG_TTMDEEPCLAW = createSpawnEgg("entityttmdeepclaw", entityTTMDeepclaw, 14705521, 14088652);
    public static final RegistryObject<Item> EGG_TTMTREEENT = createSpawnEgg("entityttmtreeent", entityTTMTreeEnt, 14705521, 9467561);
    public static final RegistryObject<Item> EGG_TTMDUERGAR = createSpawnEgg("entityttmduergar", entityTTMDuergar, 14705521, 9226665);
    public static final RegistryObject<Item> EGG_TTMFELLSPIRIT = createSpawnEgg("entityttmfellspirit", entityTTMFellSpirit, 14705521, 7405383);
    public static final RegistryObject<Item> EGG_TTMSWAMPHAG = createSpawnEgg("entityttmswamphag", entityTTMSwampHag, 14705521, 12659887);
    public static final RegistryObject<Item> EGG_TTMMIRKWOODSPIDER = createSpawnEgg("entityttmmirkwoodspider", entityTTMMirkwoodSpider, 14705521, 16211457);
    public static final RegistryObject<Item> EGG_TTMHARADRIM = createSpawnEgg("entityttmharadrim", entityTTMHaradrim, 14705521, 12198412);
    public static final RegistryObject<Item> EGG_TTMTROLL = createSpawnEgg("entityttmtroll", entityTTMTroll, 14705521, 13146148);
    public static final RegistryObject<Item> EGG_TTMWARG = createSpawnEgg("entityttmwarg", entityTTMWarg, 14705521, 2703752);
    public static final RegistryObject<Item> EGG_TTMMORDORORC = createSpawnEgg("entityttmmordororc", entityTTMMordorOrc, 14705521, 8755748);
    public static final RegistryObject<Item> EGG_TTMHURON = createSpawnEgg("entityttmhuron", entityTTMHuron, 14705521, 10600204);
    public static final RegistryObject<Item> EGG_TTMOATHBREAKER = createSpawnEgg("entityttmoathbreaker", entityTTMOathbreaker, 14705521, 16121867);
    public static final RegistryObject<Item> EGG_TTMROMIEWALKER = createSpawnEgg("entityttmromiewalker", entityTTMRomieWalker, 14705521, 16739362);
    public static final RegistryObject<Item> EGG_TTMURUKHAI = createSpawnEgg("entityttmurukhai", entityTTMUrukHai, 14705521, 698898);
    public static final RegistryObject<Item> EGG_TTMELEMENTALGOLEM = createSpawnEgg("entityttmelementalgolem", entityTTMElementalGolem, 14705521, 1380646);
    public static final RegistryObject<Item> EGG_TTMMINOTAUR = createSpawnEgg("entityttmminotaur", entityTTMMinotaur, 14705521, 2973229);
    public static final RegistryObject<Item> EGG_TTMMIMICCHEST = createSpawnEgg("entityttmmimicchest", entityTTMMimicChest, 14705521, 8807990);

    // Boss
    public static final RegistryObject<Item> EGG_TTMGOBLINKING = createSpawnEgg("entityttmgoblinking", entityTTMGoblinKing, 11025577, 2301661);

    // Passive
    public static final RegistryObject<Item> EGG_TTMAUROCH = createSpawnEgg("entityttmauroch", entityTTMAuroch, 4751910, 2301661);
    public static final RegistryObject<Item> EGG_TTMMUMAKIL = createSpawnEgg("entityttmmumakil", entityTTMMumakil, 4751910, 5600397);
    public static final RegistryObject<Item> EGG_TTMGOAT = createSpawnEgg("entityttmgoat", entityTTMGoat, 4751910, 14289362);

    // Helper Methods
    public static RegistryObject<Item> createSpawnEgg(String name, EntityType< ? > entityType, int primaryColor, int secondaryColor ) {
        return SPAWN_EGGS.register(name + "_spawn_egg", () -> new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Properties().tab(spawnGroup)));
    }

    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.IFactory<T> factory, EntityClassification classification, float size1, float size2) {
        return makeBuilder(factory, classification).sized(size1, size2).build(name);
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
