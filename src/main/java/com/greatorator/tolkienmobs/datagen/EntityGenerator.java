package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMFrog;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMRat;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMSquirrel;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMSwarm;
import com.greatorator.tolkienmobs.entity.ambient.model.ModelTTMSwarm;
import com.greatorator.tolkienmobs.entity.ambient.render.RenderTTMFrog;
import com.greatorator.tolkienmobs.entity.ambient.render.RenderTTMRat;
import com.greatorator.tolkienmobs.entity.ambient.render.RenderTTMSquirrel;
import com.greatorator.tolkienmobs.entity.ambient.render.RenderTTMSwarm;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderBoulder;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.render.RenderGaladhrimArrow;
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
    private static final EntityType<EntityTTMSwarm> entityTTMSwarm = buildEntity("entityttmswarm", EntityTTMSwarm::new, EntityClassification.CREATURE, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<EntityTTMSwarm>> ENTITY_TTM_SWARM = ENTITY.register("entityttmswarm", () -> entityTTMSwarm);

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
    }

    //#################################################################
    // Spawn Eggs
    //#################################################################
    // Ambient
    public static final RegistryObject<Item> EGG_TTMRAT = createSpawnEgg("entityttmrat", entityTTMRat, 13354786, 2002668);
    public static final RegistryObject<Item> EGG_TTMSQUIRREL = createSpawnEgg("entityttmsquirrel", entityTTMSquirrel, 13354786, 9918758);
    public static final RegistryObject<Item> EGG_TTMFROG = createSpawnEgg("entityttmfrog", entityTTMFrog, 13354786, 9984463);
    public static final RegistryObject<Item> EGG_TTMSWARM = createSpawnEgg("entityttmswarm", entityTTMSwarm, 13354786, 4069779);



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
