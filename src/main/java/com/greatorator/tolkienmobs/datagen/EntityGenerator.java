package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMSwarm;
import com.greatorator.tolkienmobs.entity.ambient.render.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderBoulder;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMDwarf;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMElves;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMHobbit;
import com.greatorator.tolkienmobs.entity.merchant.EntityTTMHuman;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMDwarf;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMElves;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMHobbit;
import com.greatorator.tolkienmobs.entity.merchant.render.RenderTTMHuman;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
    private static final EntityType<EntityTTMRat> entityTTMRat = buildEntity("entityttmrat", EntityTTMRat::new, EntityClassification.CREATURE, 0.5F, 0.5F);
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

    // Ammo
    public static final RegistryObject<EntityType<?>> AMMO_ARROW_GALADHRIM = ENTITY.register("ammo_arrow_galadhrim", () -> EntityType.Builder.create(EntityGaladhrimArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).setCustomClientFactory(EntityGaladhrimArrow::new).build(MODID + ":ammo_arrow_galadhrim"));
    public static final RegistryObject<EntityType<?>> AMMO_FELLBEAST_FIREBALL = ENTITY.register("ammo_fellbeast_fireball", () -> EntityType.Builder.create(EntityFellBeastFireball::new, EntityClassification.MISC).size(0.5F, 0.5F).setCustomClientFactory(EntityFellBeastFireball::new).build(MODID + ":ammo_fellbeast_fireball"));
    public static final RegistryObject<EntityType<?>> AMMO_BOULDER = ENTITY.register("ammo_boulder", () -> EntityType.Builder.create(EntityBoulder::new, EntityClassification.MISC).size(0.5F, 0.5F).setCustomClientFactory(EntityBoulder::new).build(MODID + ":ammo_boulder"));

    //#################################################################
    // Render Registry
    //#################################################################
    public static void registerEntityRenderer() {
        // Ambient
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_RAT.get(), RenderTTMRat::new);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_SQUIRREL.get(), RenderTTMSquirrel::new);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_FROG.get(), RenderTTMFrog::new);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_SWARM.get(), m -> new RenderTTMSwarm<>(m, new ModelTTMSwarm(), 0.5F, TolkienMobs.MODID + ":textures/entity/midgeflies.png"));
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_THRUSH.get(), RenderTTMThrush::new);

        // Merchants
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_HUMAN.get(), RenderTTMHuman::new);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_DWARF.get(), RenderTTMDwarf::new);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_ELVES.get(), RenderTTMElves::new);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_HOBBIT.get(), RenderTTMHobbit::new);

        // Ammo
        RenderingRegistry.registerEntityRenderingHandler(AMMO_ARROW_GALADHRIM.get(), new RenderGaladhrimArrow.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(AMMO_FELLBEAST_FIREBALL.get(), new RenderFellBeastFireball.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(AMMO_BOULDER.get(), new RenderBoulder.RenderFactory());
    }

    //#################################################################
    // Attribute Registry
    //#################################################################
    public static void addEntityAttributes(){
        // Ambient
        GlobalEntityTypeAttributes.put(ENTITY_TTM_RAT.get(), EntityTTMRat.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_SQUIRREL.get(), EntityTTMSquirrel.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_FROG.get(), EntityTTMSquirrel.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_SWARM.get(), EntityTTMSwarm.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_THRUSH.get(), EntityTTMThrush.registerAttributes().create());

        // Merchants
        GlobalEntityTypeAttributes.put(ENTITY_TTM_HUMAN.get(), EntityTTMHuman.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_DWARF.get(), EntityTTMDwarf.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_ELVES.get(), EntityTTMElves.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ENTITY_TTM_HOBBIT.get(), EntityTTMHobbit.registerAttributes().create());
    }

    //#################################################################
    // Spawn Eggs
    //#################################################################
    // Ambient
    public static final RegistryObject<Item> EGG_TTMRAT = createSpawnEgg("entityttmrat", entityTTMRat, 13354786, 2002668);
    public static final RegistryObject<Item> EGG_TTMSQUIRREL = createSpawnEgg("entityttmsquirrel", entityTTMSquirrel, 13354786, 9918758);
    public static final RegistryObject<Item> EGG_TTMFROG = createSpawnEgg("entityttmfrog", entityTTMFrog, 13354786, 9984463);
    public static final RegistryObject<Item> EGG_TTMSWARM = createSpawnEgg("entityttmswarm", entityTTMSwarm, 13354786, 4069779);
    public static final RegistryObject<Item> EGG_TTMTHRUSH = createSpawnEgg("entityttmthrush", entityTTMThrush, 13354786, 15775288);

    // Merchants
    public static final RegistryObject<Item> EGG_TTMHUMAN = createSpawnEgg("entityttmhuman", entityTTMHuman, 16426382, 7497469);
    public static final RegistryObject<Item> EGG_TTMDWARF = createSpawnEgg("entityttmdwarf", entityTTMDwarf, 16426382, 11416321);
    public static final RegistryObject<Item> EGG_TTMELVES = createSpawnEgg("entityttmelves", entityTTMElves, 16426382, 8195056);
    public static final RegistryObject<Item> EGG_TTMHOBBIT = createSpawnEgg("entityttmhobbit", entityTTMHobbit, 16426382, 7063795);



    // Helper Methods
    public static RegistryObject<Item> createSpawnEgg(String name, EntityType< ? > entityType, int primaryColor, int secondaryColor ) {
        return SPAWN_EGGS.register(name + "_spawn_egg", () -> new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Properties().group(spawnGroup)));
    }

    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.IFactory<T> factory, EntityClassification classification, float size1, float size2) {
        return makeBuilder(factory, classification).size(size1, size2).build(name);
    }
    private static<T extends Entity> EntityType<T> buildEntity(String name, EntityType.IFactory<T> factory, EntityClassification classification) {
        return makeBuilder(factory, classification).build(name);
    }

    private static<T extends Entity> EntityType.Builder<T> makeBuilder(EntityType.IFactory<T> factory, EntityClassification classification) {
        return EntityType.Builder.create(factory, classification);
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Entities";
    }
}
