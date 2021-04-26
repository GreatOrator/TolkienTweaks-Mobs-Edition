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
    protected void add(EntityType<?> entity, LootTable.Builder builder) {
        super.add(entity, builder);
        knownEntities.add(entity);
    }

    @Override
    protected void addTables() {
        // Ambient
        add(EntityGenerator.ENTITY_TTM_RAT.get(), noLoot());
        add(EntityGenerator.ENTITY_TTM_THRUSH.get(), fromEntityLootTable(EntityType.PARROT));
        add(EntityGenerator.ENTITY_TTM_SQUIRREL.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.TREE_ACORN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.GOLDEN_TREE_ACORN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer())));
        add(EntityGenerator.ENTITY_TTM_FROG.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.INSECT.get())
                                    .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                    .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer())));
        add(EntityGenerator.ENTITY_TTM_SWARM.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.INSECT.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.GOLDEN_INSECT.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer())));
        // Merchant
//        registerLootTable(EntityGenerator.ENTITY_TTM_HUMAN.get(), noLoot());
//        registerLootTable(EntityGenerator.ENTITY_TTM_DWARF.get(), noLoot());
//        registerLootTable(EntityGenerator.ENTITY_TTM_ELVES.get(), noLoot());
//        registerLootTable(EntityGenerator.ENTITY_TTM_HOBBIT.get(), noLoot());

        // Passive
        add(EntityGenerator.ENTITY_TTM_AUROCH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.MONSTER_FUR.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.LEATHER)
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.BEEF)
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(KilledByPlayer.killedByPlayer())));
        add(EntityGenerator.ENTITY_TTM_GOAT.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.MONSTER_FUR.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.LEATHER)
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.BEEF)
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(KilledByPlayer.killedByPlayer())));

        // Monster
        add(EntityGenerator.ENTITY_TTM_GOBLIN.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.SWORD_MORGULIRON.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_COIN_BRONZE.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(2.0F, 6.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_FACTIONCOIN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_FACTIONTOKEN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.MONSTER_FLESH.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(KilledByPlayer.killedByPlayer())));
        add(EntityGenerator.ENTITY_TTM_BARROW.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.SWORD_MORGULIRON.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_COIN_BRONZE.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(2.0F, 6.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_FACTIONCOIN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_FACTIONTOKEN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.MONSTER_FLESH.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_DARKSIGIL.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(KilledByPlayer.killedByPlayer())));

        // Boss
        add(EntityGenerator.ENTITY_TTM_GOBLINKING.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.SWORD_MORGULIRON.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_COIN_SILVER.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                .when(KilledByPlayer.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_FACTIONCOIN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.ITEM_FACTIONTOKEN.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(TTMContent.MONSTER_FLESH.get())
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .when(KilledByPlayer.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT)
                                        .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                        .apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(KilledByPlayer.killedByPlayer())));
    }

    public LootTable.Builder noLoot() {
        return LootTable.lootTable();
    }

    public LootTable.Builder fromEntityLootTable(EntityType<?> parent) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(TableLootEntry.lootTableReference(parent.getDefaultLootTable())));
    }

    @Override
    public Set<EntityType<?>> getKnownEntities() {
        return knownEntities;
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Loot Tables - Entity";
    }
}
