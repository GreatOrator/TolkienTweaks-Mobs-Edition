package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;

import java.util.HashSet;
import java.util.Set;

public class EntityLootGenerator extends EntityLootTables {
    private final Set<EntityType<?>> knownEntities = new HashSet<>();

    @Override
    protected void registerLootTable(EntityType<?> entity, LootTable.Builder builder) {
        super.registerLootTable(entity, builder);
        knownEntities.add(entity);
    }

    @Override
    protected void addTables() {
        // Ambient
        registerLootTable(EntityGenerator.ENTITY_TTM_RAT.get(), noLoot());
        registerLootTable(EntityGenerator.ENTITY_TTM_THRUSH.get(), fromEntityLootTable(EntityType.PARROT));
        registerLootTable(EntityGenerator.ENTITY_TTM_SQUIRREL.get(),
                LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.TREE_ACORN.get())
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder()))
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.GOLDEN_TREE_ACORN.get())
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder())));
        registerLootTable(EntityGenerator.ENTITY_TTM_FROG.get(),
                LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.INSECT.get())
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))
                                    .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder())));
        registerLootTable(EntityGenerator.ENTITY_TTM_SWARM.get(),
                LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.INSECT.get())
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder()))
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.GOLDEN_INSECT.get())
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder())));
        // Merchant
//        registerLootTable(EntityGenerator.ENTITY_TTM_HUMAN.get(), noLoot());
//        registerLootTable(EntityGenerator.ENTITY_TTM_DWARF.get(), noLoot());
//        registerLootTable(EntityGenerator.ENTITY_TTM_ELVES.get(), noLoot());
//        registerLootTable(EntityGenerator.ENTITY_TTM_HOBBIT.get(), noLoot());

        // Passive
        registerLootTable(EntityGenerator.ENTITY_TTM_AUROCH.get(),
                LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.MONSTER_FUR.get())
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder()))
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(Items.LEATHER)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder()))
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(Items.BEEF)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))))
                                .acceptCondition(KilledByPlayer.builder())));
        registerLootTable(EntityGenerator.ENTITY_TTM_GOAT.get(),
                LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(TTMContent.MONSTER_FUR.get())
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder()))
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(Items.LEATHER)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                .acceptCondition(KilledByPlayer.builder()))
                        .addLootPool(LootPool.builder()
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(Items.BEEF)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))
                                        .acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))))
                                .acceptCondition(KilledByPlayer.builder())));
    }

    public LootTable.Builder noLoot() {
        return LootTable.builder();
    }

    public LootTable.Builder fromEntityLootTable(EntityType<?> parent) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(TableLootEntry.builder(parent.getLootTable())));
    }

    @Override
    public Set<EntityType<?>> getKnownEntities() {
        return knownEntities;
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Loot Tables - Entity";
    }
}
