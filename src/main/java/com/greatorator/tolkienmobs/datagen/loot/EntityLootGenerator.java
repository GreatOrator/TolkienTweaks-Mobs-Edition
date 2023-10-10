package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.LootHandler;
import com.greatorator.tolkienmobs.handler.functions.TrinketRandomlyFunction;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class EntityLootGenerator extends EntityLoot {
    private final Set<EntityType<?>> knownEntities = new HashSet<>();
    private static final LootPool.Builder BRONZE = createCoinPool(TolkienItems.ITEM_COIN_BRONZE.get());
    private static final LootPool.Builder SILVER = createCoinPool(TolkienItems.ITEM_COIN_SILVER.get());
    private static final LootPool.Builder GOLD = createCoinPool(TolkienItems.ITEM_COIN_GOLD.get());
    private static final LootPool.Builder MITHRIL = createCoinPool(TolkienItems.ITEM_COIN_MITHRIL.get());
    private static final LootPool.Builder FACTION_COIN = createCoinPool(TolkienItems.ITEM_FACTIONCOIN.get());
    private static final LootPool.Builder FACTION_TOKEN = createCoinPool(TolkienItems.ITEM_FACTIONTOKEN.get());
    private static final LootPool.Builder DARK_SIGIL = createCoinPool(TolkienItems.ITEM_DARKSIGIL.get());

    private static final LootPool.Builder AMULET = createTrinketPool(TolkienItems.TRINKET_AMULET.get());
    private static final LootPool.Builder BELT = createTrinketPool(TolkienItems.TRINKET_BELT.get());
    private static final LootPool.Builder CHARM = createTrinketPool(TolkienItems.TRINKET_CHARM.get());
    private static final LootPool.Builder RING = createTrinketPool(TolkienItems.TRINKET_RING.get());
    private static final LootPool.Builder GLOVE = createTrinketPool(TolkienItems.TRINKET_GLOVE.get());
    private static final LootPool.Builder HAT = createTrinketPool(TolkienItems.TRINKET_HAT.get());
    private static final LootPool.Builder CLOAK = createTrinketPool(TolkienItems.TRINKET_CLOAK.get());

    @Override
    protected void add(@Nonnull EntityType<?> entity, @Nonnull LootTable.Builder builder) {
        super.add(entity, builder);
        knownEntities.add(entity);
    }

    @Override
    protected void addTables() {
        //Adding stuff to vanilla entity loot tables
        addInject(BRONZE, EntityType.ZOMBIE);
        addInject(BRONZE, EntityType.ZOMBIE_VILLAGER);
        addInject(SILVER, EntityType.SKELETON);
        addInject(SILVER, EntityType.WITHER_SKELETON);
        addInject(SILVER, EntityType.SKELETON_HORSE);
        addInject(BRONZE, EntityType.CREEPER);
        addInject(GOLD, EntityType.GHAST);
        addInject(GOLD, EntityType.BLAZE);
        addInject(SILVER, EntityType.HUSK);
        addInject(BRONZE, EntityType.MAGMA_CUBE);
        addInject(BRONZE, EntityType.ZOMBIFIED_PIGLIN);
        addInject(BRONZE, EntityType.ZOGLIN);
        addInject(BRONZE, EntityType.DROWNED);
        addInject(GOLD, EntityType.GUARDIAN);
        addInject(MITHRIL, EntityType.ELDER_GUARDIAN);
        addInject(BRONZE, EntityType.SLIME);
        addInject(BRONZE, EntityType.STRAY);
        addInject(SILVER, EntityType.ENDERMAN);
        addInject(SILVER, EntityType.SPIDER);
        addInject(GOLD, EntityType.CAVE_SPIDER);

        // Ambient
        add(TolkienEntities.ENTITY_TTM_RAT.get(), noLoot());
        add(TolkienEntities.ENTITY_TTM_THRUSH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.FEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
        add(TolkienEntities.ENTITY_TTM_SQUIRREL.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.TREE_ACORN.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLDEN_TREE_ACORN.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_CREBAIN.get(),
                LootTable.lootTable()
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.CREBAIN_FEATHER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_FROG.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.INSECT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_SWARM.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.INSECT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLDEN_INSECT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        // Passive
        add(TolkienEntities.ENTITY_TTM_AUROCH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BEEF)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_GOAT.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BEEF)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_MUMAKIL.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MUMAKIL_LEATHER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 3.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BEEF)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        // Monsters
        add(TolkienEntities.ENTITY_TTM_OATHBREAKER.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_BARROW.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_FELLSPIRIT.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_GOBLIN.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_BRIGAND.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_DEEPCLAW.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_TREEENT.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.STICK)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 6.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.LOG_DEADWOOD.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.LEAFPILE_CULUMALDA.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.DRINK_ENT_DRAUGHT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_BRIGAND.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.AXE_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_DUERGAR.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.AXE_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_SWAMPHAG.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.SUGAR)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.REDSTONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_MIRKWOODSPIDER.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.COBWEB)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_HARADRIM.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_TROLL.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.CLUB_WOODEN.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_WARG.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_MORDORORC.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_HURON.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.STICK)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 6.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.LOG_DEADWOOD.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.LEAFPILE_CULUMALDA.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_ROMIEWALKER.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_ELEMENTALGOLEM.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_EARTH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_AIR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_FIRE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_WATER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_SUMMON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.BLOCK_MITHRIL.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.BLOCK_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.STONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_URUKHAI.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_MINOTAUR.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.AXE_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_MIMICCHEST.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(CHARM)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.CHEST)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.DRINK_PERSONAL_BLACKSMITH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_ROCKGOLEM.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_EARTH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_AIR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_FIRE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_WATER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_SUMMON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.BLOCK_MITHRIL.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienBlocks.BLOCK_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.STONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));

        // Special
        add(TolkienEntities.ENTITY_TTM_SHADOWFAX.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BEEF)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_GOLLUM.get(),
                LootTable.lootTable()
                        .withPool(BRONZE)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SHOVEL_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 0.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.SALMON)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_NAZGUL.get(),
                LootTable.lootTable()
                        .withPool(GOLD)
                        .withPool(CLOAK)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_NAZGULSTEED.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_GREAT_EAGLE.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.BIRD_FEATHER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_ISTARI.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.ARDA_STAFF.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        // Boss
        add(TolkienEntities.ENTITY_TTM_GOBLINKING.get(),
                LootTable.lootTable()
                        .withPool(AMULET)
                        .withPool(SILVER)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_MITHRILGOLEM.get(),
                LootTable.lootTable()
                        .withPool(CHARM)
                        .withPool(MITHRIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MITHRIL.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.GOLEM_STONE_SUMMON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_MORGULIRONGOLEM.get(),
                LootTable.lootTable()
                        .withPool(CHARM)
                        .withPool(MITHRIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_WITCHKING.get(),
                LootTable.lootTable()
                        .withPool(CLOAK)
                        .withPool(MITHRIL)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(DARK_SIGIL)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.SWORD_MORGULIRON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(TolkienEntities.ENTITY_TTM_SHELOB.get(),
                LootTable.lootTable()
                        .withPool(BELT)
                        .withPool(GOLD)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.COBWEB)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_BALROG.get(),
                LootTable.lootTable()
                        .withPool(RING)
                        .withPool(GOLD)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.WHIP_FIRE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.FIRE_CHARGE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_WATCHER.get(),
                LootTable.lootTable()
                        .withPool(GLOVE)
                        .withPool(GOLD)
                        .withPool(FACTION_COIN)
                        .withPool(FACTION_TOKEN)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.PRISMARINE_SHARD)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.INK_SAC)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
        add(TolkienEntities.ENTITY_TTM_GWAHIR.get(),
                LootTable.lootTable()
                        .withPool(HAT)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(TolkienItems.BIRD_FEATHER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
    }

    public LootTable.Builder noLoot() {
        return LootTable.lootTable();
    }

    @Override
    public Set<EntityType<?>> getKnownEntities() {
        return knownEntities;
    }

    private static LootPool.Builder createCoinPool(Item type) {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(type)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(70))
                .add(LootItem.lootTableItem(type).when(LootItemKilledByPlayerCondition.killedByPlayer()))
                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.03F));
    }

    private static LootPool.Builder createTrinketPool(Item type) {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(type)
                        .apply(TrinketRandomlyFunction.randomApplicableEffect())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
                        .setWeight(10))
                .add(LootItem.lootTableItem(type).when(LootItemKilledByPlayerCondition.killedByPlayer()))
                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.03F));
    }

    private void addInject(LootPool.Builder pool, EntityType<?> entityType) {
        addInject(LootTable.lootTable().withPool(pool).setParamSet(LootContextParamSets.ENTITY), TolkienMobs.createRL(entityType.getDefaultLootTable().getPath()));
    }

    private void addInject(LootTable.Builder builder, ResourceLocation location) {
        if (!LootHandler.INJECT_LIST.contains(location.getPath())) {
            throw new IllegalStateException(MessageFormat.format("{} is not present in LootHandler.INJECT_LIST and will not be injected at runtime!", location));
        }
        add(new ResourceLocation(location.getNamespace(), "inject/" + location.getPath()), builder);
    }

    public String getName() {
        return NAME + " - Entity";
    }
}