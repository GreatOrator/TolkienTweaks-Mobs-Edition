package com.greatorator.tolkienmobs.datagen;

import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class EntityGenerator {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    //#################################################################
    // Entities
    //#################################################################
    //public static RegistryObject<EntityType<PigEntity>> EXAMPLE_ENTITY = ENTITY.register("example_entity", () -> EntityType.Builder.create(PigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F).trackingRange(10).build("example_entity"));

}
