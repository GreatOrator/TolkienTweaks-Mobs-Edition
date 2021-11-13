package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.boss.EntityTTMGoblinKing;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMGoblin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public class TTMEntityType<T extends Entity> extends net.minecraftforge.registries.ForgeRegistryEntry<EntityType<?>> {
//    public static final EntityType<EntityTTMGoblinKing> GOBLIN_KING = register("goblin_king", EntityType.Builder.<EntityTTMGoblinKing>of(EntityTTMGoblinKing::new, EntityClassification.MONSTER).sized(0.7F, 0.5F).clientTrackingRange(8));
//    public static final EntityType<EntityTTMGoblin> GOBLIN = register("goblin", EntityType.Builder.<EntityTTMGoblin>of(EntityTTMGoblin::new, EntityClassification.MONSTER).sized(0.7F, 0.5F).clientTrackingRange(8));
//
//
//    private static <T extends Entity> EntityType<T> register(String p_200712_0_, EntityType.Builder<T> p_200712_1_) {
//        return Registry.register(Registry.ENTITY_TYPE, p_200712_0_, p_200712_1_.build(p_200712_0_));
//    }
}
