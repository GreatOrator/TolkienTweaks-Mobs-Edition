package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.entity.AmbientEntity;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.VillagerEntity;
import com.greatorator.tolkienmobs.entity.WanderingEntity;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.boss.*;
import com.greatorator.tolkienmobs.entity.item.MorgulCrystalEntity;
import com.greatorator.tolkienmobs.entity.item.SimpleTrapEntity;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.merchant.*;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.AurochEntity;
import com.greatorator.tolkienmobs.entity.passive.GoatEntity;
import com.greatorator.tolkienmobs.entity.passive.MumakilEntity;
import com.greatorator.tolkienmobs.entity.projectiles.*;
import com.greatorator.tolkienmobs.entity.special.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
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
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTabs.spawnGroup;

public class TolkienEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Item> EGG_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

    /* Entity Attributes */
    /** Projectile */
    public static final RegistryObject<Attribute> PROJECTILE_DAMAGE = ranged("generic.projectileDamage", 2d, 0, 2048d);

    /* Entities */
    /** Ambient */
    public static final RegistryObject<EntityType<SwarmEntity>> ENTITY_TTM_SWARM = ENTITY.register(
            "entityttmswarm",
            () -> EntityType.Builder.of(SwarmEntity::new, MobCategory.CREATURE)
                    .sized(0.7F, 1.9F)
                    .build(new ResourceLocation(MODID, "entityttmswarm").toString())
    );
    public static final RegistryObject<EntityType<ThrushEntity>> ENTITY_TTM_THRUSH = ENTITY.register(
            "entityttmthrush",
            () -> EntityType.Builder.of(ThrushEntity::new, MobCategory.CREATURE)
                    .sized(0.24375F, 0.24375F)
                    .build(new ResourceLocation(MODID, "entityttmthrush").toString())
    );
    public static final RegistryObject<EntityType<CrebainEntity>> ENTITY_TTM_CREBAIN = ENTITY.register(
            "entityttmcrebain",
            () -> EntityType.Builder.of(CrebainEntity::new, MobCategory.CREATURE)
                    .sized(0.24375F, 0.24375F)
                    .build(new ResourceLocation(MODID, "entityttmcrebain").toString())
    );
    public static final RegistryObject<EntityType<RatEntity>> ENTITY_TTM_RAT = ENTITY.register(
            "entityttmrat",
            () -> EntityType.Builder.of(RatEntity::new, MobCategory.CREATURE)
                    .sized(0.1875F, 0.1875F)
                    .build(new ResourceLocation(MODID, "entityttmrat").toString())
    );
    public static final RegistryObject<EntityType<SquirrelEntity>> ENTITY_TTM_SQUIRREL = ENTITY.register(
            "entityttmsquirrel",
            () -> EntityType.Builder.of(SquirrelEntity::new, MobCategory.CREATURE)
                    .sized(0.225F, 0.450F)
                    .build(new ResourceLocation(MODID, "entityttmsquirrel").toString())
    );
    public static final RegistryObject<EntityType<FrogEntity>> ENTITY_TTM_FROG = ENTITY.register(
            "entityttmfrog",
            () -> EntityType.Builder.of(FrogEntity::new, MobCategory.CREATURE)
                    .sized(0.15F, 0.15F)
                    .build(new ResourceLocation(MODID, "entityttmfrog").toString())
    );

    /** Merchants */
    public static final RegistryObject<EntityType<HumanEntity>> ENTITY_TTM_HUMAN = ENTITY.register(
            "entityttmhuman",
            () -> EntityType.Builder.of(HumanEntity::new, MobCategory.MISC)
                    .sized(0.5F, 2.0F)
                    .build(new ResourceLocation(MODID, "entityttmhuman").toString())
    );
    public static final RegistryObject<EntityType<DwarfEntity>> ENTITY_TTM_DWARF = ENTITY.register(
            "entityttmdwarf",
            () -> EntityType.Builder.of(DwarfEntity::new, MobCategory.MISC)
                    .sized(0.50625F, 1.51875F)
                    .build(new ResourceLocation(MODID, "entityttmdwarf").toString())
    );
    public static final RegistryObject<EntityType<DesertDwellerEntity>> ENTITY_TTM_DESERTDWELLER = ENTITY.register(
            "entityttmdesertdweller",
            () -> EntityType.Builder.of(DesertDwellerEntity::new, MobCategory.MISC)
                    .sized(0.5F, 2.0F)
                    .build(new ResourceLocation(MODID, "entityttmdesertdweller").toString())
    );
    public static final RegistryObject<EntityType<ElvesEntity>> ENTITY_TTM_ELVES = ENTITY.register(
            "entityttmelves",
            () -> EntityType.Builder.of(ElvesEntity::new, MobCategory.MISC)
                    .sized(0.55F, 2.2F)
                    .build(new ResourceLocation(MODID, "entityttmelves").toString())
    );
    public static final RegistryObject<EntityType<HobbitEntity>> ENTITY_TTM_HOBBIT = ENTITY.register(
            "entityttmhobbit",
            () -> EntityType.Builder.of(HobbitEntity::new, MobCategory.MISC)
                    .sized(0.35F, 1.4F)
                    .build(new ResourceLocation(MODID, "entityttmhobbit").toString())
    );

    /** Monster */
    public static final RegistryObject<EntityType<OathbreakerEntity>> ENTITY_TTM_OATHBREAKER = ENTITY.register(
            "entityttmoathbreaker",
            () -> EntityType.Builder.of(OathbreakerEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0F)
                    .build(new ResourceLocation(MODID, "entityttmoathbreaker").toString())
    );
    public static final RegistryObject<EntityType<BarrowWightEntity>> ENTITY_TTM_BARROW = ENTITY.register(
            "entityttmbarrow",
            () -> EntityType.Builder.of(BarrowWightEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0F)
                    .build(new ResourceLocation(MODID, "entityttmbarrow").toString())
    );
    public static final RegistryObject<EntityType<FellSpiritEntity>> ENTITY_TTM_FELLSPIRIT = ENTITY.register(
            "entityttmfellspirit",
            () -> EntityType.Builder.of(FellSpiritEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0F)
                    .build(new ResourceLocation(MODID, "entityttmfellspirit").toString())
    );
    public static final RegistryObject<EntityType<BrigandEntity>> ENTITY_TTM_BRIGAND = ENTITY.register(
            "entityttmbrigand",
            () -> EntityType.Builder.of(BrigandEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0F)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "entityttmbrigand").toString())
    );
    public static final RegistryObject<EntityType<HaradrimEntity>> ENTITY_TTM_HARADRIM = ENTITY.register(
            "entityttmharadrim",
            () -> EntityType.Builder.of(HaradrimEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0F)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "entityttmharadrim").toString())
    );
    public static final RegistryObject<EntityType<RomieWalkerEntity>> ENTITY_TTM_ROMIEWALKER = ENTITY.register(
            "entityttmromiewalker",
            () -> EntityType.Builder.of(RomieWalkerEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0F)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "entityttmromiewalker").toString())
    );
    public static final RegistryObject<EntityType<DeepClawEntity>> ENTITY_TTM_DEEPCLAW = ENTITY.register(
            "entityttmdeepclaw",
            () -> EntityType.Builder.of(DeepClawEntity::new, MobCategory.MONSTER)
                    .sized(0.625F, 0.5625F)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "entityttmdeepclaw").toString())
    );
    public static final RegistryObject<EntityType<TreeEntEntity>> ENTITY_TTM_TREEENT = ENTITY.register(
            "entityttmtreeent",
            () -> EntityType.Builder.of(TreeEntEntity::new, MobCategory.MONSTER)
                    .sized(1.125F, 5.5F)
                    .build(new ResourceLocation(MODID, "entityttmtreeent").toString())
    );
    public static final RegistryObject<EntityType<MimicChestEntity>> ENTITY_TTM_MIMICCHEST = ENTITY.register(
            "entityttmmimicchest",
            () -> EntityType.Builder.of(MimicChestEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.3125F)
                    .build(new ResourceLocation(MODID, "entityttmmimicchest").toString())
    );
    public static final RegistryObject<EntityType<DuergarEntity>> ENTITY_TTM_DUERGAR = ENTITY.register(
            "entityttmduergar",
            () -> EntityType.Builder.of(DuergarEntity::new, MobCategory.MONSTER)
                    .sized(0.50625F, 1.51875F)
                    .build(new ResourceLocation(MODID, "entityttmduergar").toString())
    );
    public static final RegistryObject<EntityType<ElementalGolemEntity>> ENTITY_TTM_ELEMENTALGOLEM = ENTITY.register(
            "entityttmelementalgolem",
            () -> EntityType.Builder.of(ElementalGolemEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 3.125F)
                    .build(new ResourceLocation(MODID, "entityttmelementalgolem").toString())
    );
    public static final RegistryObject<EntityType<GoblinEntity>> ENTITY_TTM_GOBLIN = ENTITY.register(
            "entityttmgoblin",
            () -> EntityType.Builder.of(GoblinEntity::new, MobCategory.MONSTER)
                    .sized(0.375F, 0.9375F)
                    .build(new ResourceLocation(MODID, "entityttmgoblin").toString())
    );
    public static final RegistryObject<EntityType<HuronEntity>> ENTITY_TTM_HURON = ENTITY.register(
            "entityttmhuron",
            () -> EntityType.Builder.of(HuronEntity::new, MobCategory.MONSTER)
                    .sized(0.75F, 2.25F)
                    .build(new ResourceLocation(MODID, "entityttmhuron").toString())
    );
    public static final RegistryObject<EntityType<MinotaurEntity>> ENTITY_TTM_MINOTAUR = ENTITY.register(
            "entityttmminotaur",
            () -> EntityType.Builder.of(MinotaurEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 3.4375F)
                    .build(new ResourceLocation(MODID, "entityttmminotaur").toString())
    );
    public static final RegistryObject<EntityType<MordorOrcEntity>> ENTITY_TTM_MORDORORC = ENTITY.register(
            "entityttmmordororc",
            () -> EntityType.Builder.of(MordorOrcEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0625F)
                    .build(new ResourceLocation(MODID, "entityttmmordororc").toString())
    );
    public static final RegistryObject<EntityType<TrollEntity>> ENTITY_TTM_TROLL = ENTITY.register(
            "entityttmtroll",
            () -> EntityType.Builder.of(TrollEntity::new, MobCategory.MONSTER)
                    .sized(0.75F, 2.625F)
                    .build(new ResourceLocation(MODID, "entityttmtroll").toString())
    );
    public static final RegistryObject<EntityType<SwampHagEntity>> ENTITY_TTM_SWAMPHAG = ENTITY.register(
            "entityttmswamphag",
            () -> EntityType.Builder.of(SwampHagEntity::new, MobCategory.MONSTER)
                    .sized(1.0625F, 2.125F)
                    .build(new ResourceLocation(MODID, "entityttmswamphag").toString())
    );
    public static final RegistryObject<EntityType<WargEntity>> ENTITY_TTM_WARG = ENTITY.register(
            "entityttmwarg",
            () -> EntityType.Builder.of(WargEntity::new, MobCategory.MONSTER)
                    .sized(1.875F, 2.0625F)
                    .build(new ResourceLocation(MODID, "entityttmwarg").toString())
    );
    public static final RegistryObject<EntityType<UrukHaiEntity>> ENTITY_TTM_URUKHAI = ENTITY.register(
            "entityttmurukhai",
            () -> EntityType.Builder.of(UrukHaiEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 2.0625F)
                    .build(new ResourceLocation(MODID, "entityttmurukhai").toString())
    );
    public static final RegistryObject<EntityType<MirkwoodSpiderEntity>> ENTITY_TTM_MIRKWOODSPIDER = ENTITY.register(
            "entityttmmirkwoodspider",
            () -> EntityType.Builder.of(MirkwoodSpiderEntity::new, MobCategory.MONSTER)
                    .sized(1.25F, 0.8125F)
                    .build(new ResourceLocation(MODID, "entityttmmirkwoodspider").toString())
    );

    /** Boss */
    public static final RegistryObject<EntityType<WatcherEntity>> ENTITY_TTM_WATCHER = ENTITY.register(
            "entityttmwatcher",
            () -> EntityType.Builder.of(WatcherEntity::new, MobCategory.WATER_CREATURE)
                    .sized(7.5F, 7.5F)
                    .build(new ResourceLocation(MODID, "entityttmwatcher").toString())
    );
    public static final RegistryObject<EntityType<BalrogEntity>> ENTITY_TTM_BALROG = ENTITY.register(
            "entityttmbalrog",
            () -> EntityType.Builder.of(BalrogEntity::new, MobCategory.MONSTER)
                    .sized(1.0625F, 3.5F)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "entityttmbalrog").toString())
    );
    public static final RegistryObject<EntityType<WitchKingEntity>> ENTITY_TTM_WITCHKING = ENTITY.register(
            "entityttmwitchking",
            () -> EntityType.Builder.of(WitchKingEntity::new, MobCategory.MONSTER)
                    .sized(0.65F, 2.6F)
                    .build(new ResourceLocation(MODID, "entityttmwitchking").toString())
    );
    public static final RegistryObject<EntityType<GwahirEntity>> ENTITY_TTM_GWAHIR = ENTITY.register(
            "entityttmgwahir",
            () -> EntityType.Builder.of(GwahirEntity::new, MobCategory.CREATURE)
                    .sized(1.4F, 1.9F)
                    .build(new ResourceLocation(MODID, "entityttmgwahir").toString())
    );
    public static final RegistryObject<EntityType<GoblinKingEntity>> ENTITY_TTM_GOBLINKING = ENTITY.register(
            "entityttmgoblinking",
            () -> EntityType.Builder.of(GoblinKingEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 1.25F)
                    .build(new ResourceLocation(MODID, "entityttmgoblinking").toString())
    );
    public static final RegistryObject<EntityType<MithrilGolemEntity>> ENTITY_TTM_MITHRILGOLEM = ENTITY.register(
            "entityttmmithrilgolem",
            () -> EntityType.Builder.of(MithrilGolemEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 3.125F)
                    .build(new ResourceLocation(MODID, "entityttmmithrilgolem").toString())
    );
    public static final RegistryObject<EntityType<MorgulironGolemEntity>> ENTITY_TTM_MORGULIRONGOLEM = ENTITY.register(
            "entityttmmorgulirongolem",
            () -> EntityType.Builder.of(MorgulironGolemEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 3.125F)
                    .build(new ResourceLocation(MODID, "entityttmmorgulirongolem").toString())
    );
    public static final RegistryObject<EntityType<ShelobEntity>> ENTITY_TTM_SHELOB = ENTITY.register(
            "entityttmshelob",
            () -> EntityType.Builder.of(ShelobEntity::new, MobCategory.MONSTER)
                    .sized(1.6875F, 1.78125F)
                    .build(new ResourceLocation(MODID, "entityttmshelob").toString())
    );
    public static final RegistryObject<EntityType<FellBeastEntity>> ENTITY_FELL_BEAST = ENTITY.register(
            "entityttmfellbeast",
            () -> EntityType.Builder.of(FellBeastEntity::new, MobCategory.MONSTER)
                    .sized(2.625F, 4.25F)
                    .build(new ResourceLocation(MODID, "entityttmfellbeast").toString())
    );

    /** Passive */
    public static final RegistryObject<EntityType<MumakilEntity>> ENTITY_TTM_MUMAKIL = ENTITY.register(
            "entityttmmumakil",
            () -> EntityType.Builder.of(MumakilEntity::new, MobCategory.CREATURE)
                    .sized(2.5F, 3.3125F)
                    .build(new ResourceLocation(MODID, "entityttmmumakil").toString())
    );
    public static final RegistryObject<EntityType<AurochEntity>> ENTITY_TTM_AUROCH = ENTITY.register(
            "entityttmauroch",
            () -> EntityType.Builder.of(AurochEntity::new, MobCategory.CREATURE)
                    .sized(1.125F, 1.3125F)
                    .build(new ResourceLocation(MODID, "entityttmauroch").toString())
    );
    public static final RegistryObject<EntityType<GoatEntity>> ENTITY_TTM_GOAT = ENTITY.register(
            "entityttmgoat",
            () -> EntityType.Builder.of(GoatEntity::new, MobCategory.CREATURE)
                    .sized(0.875F, 1.875F)
                    .build(new ResourceLocation(MODID, "entityttmgoat").toString())
    );

    /** Special */
    public static final RegistryObject<EntityType<GollumEntity>> ENTITY_TTM_GOLLUM = ENTITY.register(
            "entityttmgollum",
            () -> EntityType.Builder.of(GollumEntity::new, MobCategory.CREATURE)
                    .sized(0.625F, 1.5625F)
                    .build(new ResourceLocation(MODID, "entityttmgollum").toString())
    );
    public static final RegistryObject<EntityType<ShadowfaxEntity>> ENTITY_TTM_SHADOWFAX = ENTITY.register(
            "entityttmshadowfax",
            () -> EntityType.Builder.of(ShadowfaxEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.5F)
                    .build(new ResourceLocation(MODID, "entityttmshadowfax").toString())
    );
    public static final RegistryObject<EntityType<NazgulEntity>> ENTITY_TTM_NAZGUL = ENTITY.register(
            "entityttmnazgul",
            () -> EntityType.Builder.of(NazgulEntity::new, MobCategory.MONSTER)
                    .sized(0.55F, 2.2F)
                    .build(new ResourceLocation(MODID, "entityttmnazgul").toString())
    );
    public static final RegistryObject<EntityType<NazgulSteedEntity>> ENTITY_TTM_NAZGULSTEED = ENTITY.register(
            "entityttmnazgulsteed",
            () -> EntityType.Builder.of(NazgulSteedEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.5F)
                    .build(new ResourceLocation(MODID, "entityttmnazgulsteed").toString())
    );
    public static final RegistryObject<EntityType<GreatEagleEntity>> ENTITY_TTM_GREAT_EAGLE = ENTITY.register(
            "entityttmgreateagle",
            () -> EntityType.Builder.of(GreatEagleEntity::new, MobCategory.CREATURE)
                    .sized(1.225F, 1.6625F)
                    .build(new ResourceLocation(MODID, "entityttmgreateagle").toString())
    );

    /** Ammo */
    public static final RegistryObject<EntityType<GaladhrimArrowEntity>> AMMO_ARROW_GALADHRIM = ENTITY.register("ammo_arrow_galadhrim", () -> EntityType.Builder.<GaladhrimArrowEntity>of(GaladhrimArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_arrow_galadhrim"));
    public static final RegistryObject<EntityType<UtumnoArrowEntity>> AMMO_ARROW_UTUMNO = ENTITY.register("ammo_utumno_arrow", () -> EntityType.Builder.<UtumnoArrowEntity>of(UtumnoArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_utumno_arrow"));
    public static final RegistryObject<EntityType<FellBeastFireballEntity>> AMMO_FELLBEAST_FIREBALL = ENTITY.register("ammo_fellbeast_fireball", () -> EntityType.Builder.<FellBeastFireballEntity>of(FellBeastFireballEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_fellbeast_fireball"));
    public static final RegistryObject<EntityType<BoulderEntity>> AMMO_BOULDER = ENTITY.register("ammo_boulder", () -> EntityType.Builder.<BoulderEntity>of(BoulderEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build(MODID + ":ammo_boulder"));
    public static final RegistryObject<EntityType<FireBreathEntity>> AMMO_DRAGON_BREATH = ENTITY.register("ammo_dragon_breath", () -> EntityType.Builder.<FireBreathEntity>of(FireBreathEntity::new, MobCategory.MISC).sized(0.75F, 0.75F).build(MODID + ":ammo_dragon_breath"));
    public static final RegistryObject<EntityType<CobwebProjectileEntity>> AMMO_COBWEB = ENTITY.register("ammo_cobweb", () -> EntityType.Builder.<CobwebProjectileEntity>of(CobwebProjectileEntity::new, MobCategory.MISC).sized(0.75F, 0.75F).build(MODID + ":ammo_cobweb"));
    public static final RegistryObject<EntityType<TornadoEntity>> AMMO_TORNADO = ENTITY.register("ammo_tornado", () -> EntityType.Builder.<TornadoEntity>of(TornadoEntity::new, MobCategory.MISC).sized(0.75F, 0.75F).build(MODID + ":ammo_tornado"));

    /** Traps */
    public static final RegistryObject<EntityType<SimpleTrapEntity>> TRAP_SIMPLE = ENTITY.register("trap_simple", () -> EntityType.Builder.<SimpleTrapEntity>of(SimpleTrapEntity::new, MobCategory.MISC).sized(0.75F, 0.75F).build(MODID + ":trap_simple"));

    /** Boats */
    public static final RegistryObject<EntityType<TolkienBoatEntity>> MALLORN_BOAT = ENTITY.register("boat_mallorn", () -> EntityType.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_mallorn").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> MIRKWOOD_BOAT = ENTITY.register("boat_mirkwood", () -> EntityType.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_mirkwood").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> CULUMALDA_BOAT = ENTITY.register("boat_culumalda", () -> EntityType.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_culumalda").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> LEBETHRON_BOAT = ENTITY.register("boat_lebethron", () -> EntityType.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_lebethron").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> DEADWOOD_BOAT = ENTITY.register("boat_deadwood", () -> EntityType.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_deadwood").toString()));
    public static final RegistryObject<EntityType<TolkienBoatEntity>> FANGORNOAK_BOAT = ENTITY.register("boat_fangornoak", () -> EntityType.Builder.<TolkienBoatEntity>of(TolkienBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(MODID, "boat_fangornoak").toString()));

    /** Items */
    public static final RegistryObject<EntityType<MorgulCrystalEntity>> MORGUL_CRYSTAL = ENTITY.register("morgul_crystal", () -> EntityType.Builder.<MorgulCrystalEntity>of(MorgulCrystalEntity::new, MobCategory.MISC).sized(2.0F, 2.0F).clientTrackingRange(16).updateInterval(Integer.MAX_VALUE).build(MODID + ":morgul_crystal"));

    /* Spawn Eggs */
    /** Ambient */
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMSWARM = EGG_ITEMS.register("entityttmswarm", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_SWARM, 9482106, 14088652, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMTHRUSH = EGG_ITEMS.register("entityttmthrush", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_THRUSH, 9482106, 9467561, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMCREBAIN = EGG_ITEMS.register("entityttmcrebain", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_CREBAIN, 9482106, 9226665, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMRAT = EGG_ITEMS.register("entityttmrat", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_RAT, 9482106, 2301661, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMSQUIRREL = EGG_ITEMS.register("entityttmsquirrel", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_SQUIRREL, 9482106, 5600397, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMFROG = EGG_ITEMS.register("entityttmfrog", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_FROG, 9482106, 14289362, new Item.Properties().tab(spawnGroup)));

    /** Merchants */
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMHUMAN = EGG_ITEMS.register("entityttmhuman", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_HUMAN, 15186506, 2301661, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMDESERTDWELLER = EGG_ITEMS.register("entityttmdesertdweller", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_DESERTDWELLER, 15186506, 9467561, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMDWARF = EGG_ITEMS.register("entityttmdwarf", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_DWARF, 15186506, 5600397, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMELVES = EGG_ITEMS.register("entityttmelves", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_ELVES, 15186506, 14289362, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMHOBBIT = EGG_ITEMS.register("entityttmhobbit", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_HOBBIT, 15186506, 14088652, new Item.Properties().tab(spawnGroup)));

    /** Monster */
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMOATHBREAKER = EGG_ITEMS.register("entityttmoathbreaker", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_OATHBREAKER, 585619, 16121867, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMBARROW = EGG_ITEMS.register("entityttmbarrow", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_BARROW, 585619, 5600397, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMFELLSPIRIT = EGG_ITEMS.register("entityttmfellspirit", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_FELLSPIRIT, 585619, 7405383, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMBRIGAND = EGG_ITEMS.register("entityttmbrigand", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_BRIGAND, 585619, 14289362, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMHARADRIM = EGG_ITEMS.register("entityttmharadrim", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_HARADRIM, 585619, 12198412, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMROMIEWALKER = EGG_ITEMS.register("entityttmromiewalker", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_ROMIEWALKER, 585619, 16739362, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMDEEPCLAW = EGG_ITEMS.register("entityttmdeepclaw", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_DEEPCLAW, 585619, 14088652, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMTREEENT = EGG_ITEMS.register("entityttmtreeent", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_TREEENT, 585619, 9467561, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMIMICCHEST = EGG_ITEMS.register("entityttmmimicchest", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MIMICCHEST, 585619, 8807990, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMDUERGAR = EGG_ITEMS.register("entityttmduergar", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_DUERGAR, 585619, 9226665, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMELEMENTALGOLEM = EGG_ITEMS.register("entityttmelementalgolem", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_ELEMENTALGOLEM, 585619, 1380646, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMGOBLIN = EGG_ITEMS.register("entityttmgoblin", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_GOBLIN, 585619, 2301661, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMHURON = EGG_ITEMS.register("entityttmhuron", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_HURON, 585619, 10600204, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMINOTAUR = EGG_ITEMS.register("entityttmminotaur", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MINOTAUR, 585619, 2973229, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMORDORORC = EGG_ITEMS.register("entityttmmordororc", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MORDORORC, 585619, 8755748, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMTROLL = EGG_ITEMS.register("entityttmtroll", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_TROLL, 585619, 13146148, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMSWAMPHAG = EGG_ITEMS.register("entityttmswamphag", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_SWAMPHAG, 585619, 12659887, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMWARG = EGG_ITEMS.register("entityttmwarg", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_WARG, 585619, 2703752, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMURUKHAI = EGG_ITEMS.register("entityttmurukhai", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_URUKHAI, 585619, 698898, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMIRKWOODSPIDER = EGG_ITEMS.register("entityttmmirkwoodspider", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MIRKWOODSPIDER, 585619, 16211457, new Item.Properties().tab(spawnGroup)));

    /** Boss */
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMWATCHER = EGG_ITEMS.register("entityttmwatcher", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_WATCHER, 15673963, 7405383, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMBALROG = EGG_ITEMS.register("entityttmbalrog", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_BALROG, 15673963, 9226665, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMWITCHKING = EGG_ITEMS.register("entityttmwitchking", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_WITCHKING, 15673963, 14088652, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMGWAHIR = EGG_ITEMS.register("entityttmgwahir", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_GWAHIR, 15673963, 12659887, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMGOBLINKING = EGG_ITEMS.register("entityttmgoblinking", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_GOBLINKING, 15673963, 2301661, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMITHRILGOLEM = EGG_ITEMS.register("entityttmmithrilgolem", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MITHRILGOLEM, 15673963, 5600397, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMORGULIRONGOLEM = EGG_ITEMS.register("entityttmmorgulirongolem", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MORGULIRONGOLEM, 15673963, 14289362, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMSHELOB = EGG_ITEMS.register("entityttmshelob", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_SHELOB, 15673963, 9467561, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMFELLBEAST = EGG_ITEMS.register("entityttmfellbeast", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_FELL_BEAST, 15673963, 16211457, new Item.Properties().tab(spawnGroup)));

    /** Passive */
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMMUMAKIL = EGG_ITEMS.register("entityttmmumakil", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_MUMAKIL, 7668978, 5600397, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMAUROCH = EGG_ITEMS.register("entityttmauroch", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_AUROCH, 7668978, 2301661, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMGOAT = EGG_ITEMS.register("entityttmgoat", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_GOAT, 7668978, 14289362, new Item.Properties().tab(spawnGroup)));

    /** Special */
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMGOLLUM = EGG_ITEMS.register("entityttmgollum", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_GOLLUM, 2576351, 5600397, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMSHADOWFAX = EGG_ITEMS.register("entityttmshadowfax", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_SHADOWFAX, 2576351, 2301661, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMNAZGUL = EGG_ITEMS.register("entityttmnazgul", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_NAZGUL, 2576351, 14289362, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMNAZGULSTEED = EGG_ITEMS.register("entityttmnazgulsteed", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_NAZGULSTEED, 2576351, 14088652, new Item.Properties().tab(spawnGroup)));
    public static RegistryObject<ForgeSpawnEggItem> EGG_TTMGREATEAGLE = EGG_ITEMS.register("entityttmgreateagle", () -> new ForgeSpawnEggItem(TolkienEntities.ENTITY_TTM_GREAT_EAGLE, 2576351, 9467561, new Item.Properties().tab(spawnGroup)));

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        /* Attribute Registry */
        // Ambient
        event.put(ENTITY_TTM_RAT.get(), RatEntity.createAttributes().build());
        event.put(ENTITY_TTM_SQUIRREL.get(), SquirrelEntity.createAttributes().build());
        event.put(ENTITY_TTM_FROG.get(), SquirrelEntity.createAttributes().build());
        event.put(ENTITY_TTM_SWARM.get(), SwarmEntity.createAttributes().build());
        event.put(ENTITY_TTM_THRUSH.get(), ThrushEntity.createAttributes().build());
        event.put(ENTITY_TTM_CREBAIN.get(), CrebainEntity.createAttributes().build());

        // Merchants
        event.put(ENTITY_TTM_HUMAN.get(), HumanEntity.createAttributes().build());
        event.put(ENTITY_TTM_DWARF.get(), DwarfEntity.createAttributes().build());
        event.put(ENTITY_TTM_DESERTDWELLER.get(), DesertDwellerEntity.createAttributes().build());
        event.put(ENTITY_TTM_ELVES.get(), ElvesEntity.createAttributes().build());
        event.put(ENTITY_TTM_HOBBIT.get(), HobbitEntity.createAttributes().build());

        // Monster
        event.put(ENTITY_TTM_OATHBREAKER.get(), OathbreakerEntity.createAttributes().build());
        event.put(ENTITY_TTM_BARROW.get(), BarrowWightEntity.createAttributes().build());
        event.put(ENTITY_TTM_FELLSPIRIT.get(), FellSpiritEntity.createAttributes().build());
        event.put(ENTITY_TTM_BRIGAND.get(), BrigandEntity.createAttributes().build());
        event.put(ENTITY_TTM_HARADRIM.get(), HaradrimEntity.createAttributes().build());
        event.put(ENTITY_TTM_ROMIEWALKER.get(), RomieWalkerEntity.createAttributes().build());
        event.put(ENTITY_TTM_DEEPCLAW.get(), DeepClawEntity.createAttributes().build());
        event.put(ENTITY_TTM_TREEENT.get(), TreeEntEntity.createAttributes().build());
        event.put(ENTITY_TTM_MIMICCHEST.get(), MimicChestEntity.createAttributes().build());
        event.put(ENTITY_TTM_DUERGAR.get(), DuergarEntity.createAttributes().build());
        event.put(ENTITY_TTM_ELEMENTALGOLEM.get(), ElementalGolemEntity.createAttributes().build());
        event.put(ENTITY_TTM_GOBLIN.get(), GoblinEntity.createAttributes().build());
        event.put(ENTITY_TTM_HURON.get(), HuronEntity.createAttributes().build());
        event.put(ENTITY_TTM_MINOTAUR.get(), MinotaurEntity.createAttributes().build());
        event.put(ENTITY_TTM_MORDORORC.get(), MordorOrcEntity.createAttributes().build());
        event.put(ENTITY_TTM_TROLL.get(), TrollEntity.createAttributes().build());
        event.put(ENTITY_TTM_WARG.get(), WargEntity.createAttributes().build());
        event.put(ENTITY_TTM_URUKHAI.get(), UrukHaiEntity.createAttributes().build());
        event.put(ENTITY_TTM_SWAMPHAG.get(), SwampHagEntity.createAttributes().build());
        event.put(ENTITY_TTM_MIRKWOODSPIDER.get(), MirkwoodSpiderEntity.createAttributes().build());

        // Boss
        event.put(ENTITY_TTM_WATCHER.get(), WatcherEntity.createAttributes().build());
        event.put(ENTITY_TTM_BALROG.get(), BalrogEntity.createAttributes().build());
        event.put(ENTITY_TTM_WITCHKING.get(), WitchKingEntity.createAttributes().build());
        event.put(ENTITY_TTM_GWAHIR.get(), GwahirEntity.createAttributes().build());
        event.put(ENTITY_TTM_GOBLINKING.get(), GoblinKingEntity.createAttributes().build());
        event.put(ENTITY_TTM_MITHRILGOLEM.get(), MithrilGolemEntity.createAttributes().build());
        event.put(ENTITY_TTM_MORGULIRONGOLEM.get(), MorgulironGolemEntity.createAttributes().build());
        event.put(ENTITY_TTM_SHELOB.get(), ShelobEntity.createAttributes().build());
        event.put(ENTITY_FELL_BEAST.get(), FellBeastEntity.createAttributes().build());

        // Passive
        event.put(ENTITY_TTM_AUROCH.get(), AurochEntity.createAttributes().build());
        event.put(ENTITY_TTM_MUMAKIL.get(), MumakilEntity.createAttributes().build());
        event.put(ENTITY_TTM_GOAT.get(), GoatEntity.createAttributes().build());

//        // Special
        event.put(ENTITY_TTM_SHADOWFAX.get(), ShadowfaxEntity.createAttributes().build());
        event.put(ENTITY_TTM_GOLLUM.get(), GollumEntity.createAttributes().build());
        event.put(ENTITY_TTM_NAZGUL.get(), NazgulEntity.createAttributes().build());
        event.put(ENTITY_TTM_NAZGULSTEED.get(), NazgulSteedEntity.createAttributes().build());
        event.put(ENTITY_TTM_GREAT_EAGLE.get(), GreatEagleEntity.createAttributes().build());
    }

    public static void registerSpawnPlacement() {
        //Look in SpawnPlacements for examples
        // Ambient
        SpawnPlacements.register(ENTITY_TTM_THRUSH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ThrushEntity::checkThrushSpawn);
        SpawnPlacements.register(ENTITY_TTM_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SwarmEntity::checkSwarmSpawn);
        SpawnPlacements.register(ENTITY_TTM_CREBAIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrebainEntity::checkCrebainSpawn);
        SpawnPlacements.register(ENTITY_TTM_SQUIRREL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::checkAmbientSpawn);
        SpawnPlacements.register(ENTITY_TTM_FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FrogEntity::checkFrogSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_RAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientEntity::checkAmbientSpawn);

        // Merchants
        SpawnPlacements.register(ENTITY_TTM_HUMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DESERTDWELLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WanderingEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DWARF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_ELVES.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HOBBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VillagerEntity::checkMobSpawnRules);

        //Monster
        SpawnPlacements.register(ENTITY_TTM_OATHBREAKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_BARROW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_FELLSPIRIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_BRIGAND.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HARADRIM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_ROMIEWALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DEEPCLAW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_TREEENT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TreeEntEntity::checkTreeEntSpawn);
        SpawnPlacements.register(ENTITY_TTM_MIMICCHEST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_DUERGAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_ELEMENTALGOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_GOBLIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_HURON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_MORDORORC.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_TROLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_MINOTAUR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_WARG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_URUKHAI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_SWAMPHAG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_MIRKWOODSPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);

        //Passive
        SpawnPlacements.register(ENTITY_TTM_MUMAKIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MumakilEntity::checkMumakilSpawn);
        SpawnPlacements.register(ENTITY_TTM_AUROCH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_GOAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        // Special
        SpawnPlacements.register(ENTITY_TTM_SHADOWFAX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ENTITY_TTM_GOLLUM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GollumEntity::checkGollumSpawn);
        SpawnPlacements.register(ENTITY_TTM_NAZGULSTEED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    private static RegistryObject<Attribute> ranged(String name, double defaultValue, double min, double max) {
        return register(name.toLowerCase().replace('.', '_'), () -> new RangedAttribute("attribute.name." +name, defaultValue, min, max));
    }

    private static RegistryObject<Attribute> register(String name, Supplier<Attribute> attribute) {
        return ATTRIBUTES.register(name, attribute);
    }

    public String getName() {
        return NAME + " - Entities";
    }
}
