package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.entity.ambient.EntityTTMRat;
import com.greatorator.tolkienmobs.entity.ambient.render.RenderTTMRat;
import com.greatorator.tolkienmobs.handler.TTMSpawnEgg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class EntityGenerator {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    //#################################################################
    // Entity Registry
    //#################################################################
    public static final RegistryObject<EntityType<EntityTTMRat>> ENTITY_TTM_RAT = ENTITY.register("entityttmrat", () -> EntityType.Builder.create(EntityTTMRat::new, EntityClassification.CREATURE).size(0.5F, 0.5F).trackingRange(8).build("entityttmrat"));
//    public static final RegistryObject<EntityType<EntityGaladhrimArrow>> AMMO_ARROW_GALADHRIM = ENTITY.register("ammo_arrow_galadhrim", () -> EntityType.Builder.<ArrowEntity>create(EntityGaladhrimArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).trackingRange(4).func_233608_b_(20));

    //#################################################################
    // Render Registry
    //#################################################################
    public static void registerEntityRenderer() {
//        System.out.println(ENTITY_TTM_RAT.get());
//        System.exit(0);
        RenderingRegistry.registerEntityRenderingHandler(ENTITY_TTM_RAT.get(), RenderTTMRat::new);
    }

    //#################################################################
    // Attribute Registry
    //#################################################################
    public static void addEntityAttributes(){
        GlobalEntityTypeAttributes.put(ENTITY_TTM_RAT.get(), EntityTTMRat.registerAttributes().create());
    }

    //#################################################################
    // Spawn Egg Registry
    //#################################################################
    public static void registerSpawnEggs() {
        TTMSpawnEgg.setDefaultItemGroup();
        // Ambient
        TTMSpawnEgg.createRegistrySpawnEgg(TTMContent.ITEMS, "spawn_egg_entityttmrat", ENTITY_TTM_RAT.get(), 13354786, 2002668);
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Entities";
    }
}
