package com.greatorator.tolkienmobs.init;

import com.google.common.collect.Sets;
import com.greatorator.tolkienmobs.entity.tile.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienBlocks.*;

public class TolkienTiles {
    public static final DeferredRegister<BlockEntityType<?>> TILE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);

    public static RegistryObject<BlockEntityType<FireplaceTile>> TMFIREPLACE_TILE = TILE.register("tmfireplace_tile", () -> BlockEntityType.Builder.of(FireplaceTile::new, TTMFIREPLACE.get()).build(null));
    public static RegistryObject<BlockEntityType<PiggyBankTile>> PIGGYBANK_TILE = TILE.register("piggybank_tile", () -> BlockEntityType.Builder.of(PiggyBankTile::new, PIGGYBANK.get()).build(null));
    public static RegistryObject<BlockEntityType<MithrilBarrelTile>> BARREL_MITHRIL_TILE = TILE.register("barrel_mithril_tile", () -> BlockEntityType.Builder.of(MithrilBarrelTile::new, BARREL_MITHRIL.get()).build(null));
    public static RegistryObject<BlockEntityType<MorgulironBarrelTile>> BARREL_MORGULIRON_TILE = TILE.register("barrel_morguliron_tile", () -> BlockEntityType.Builder.of(MorgulironBarrelTile::new, BARREL_MORGULIRON.get()).build(null));
    public static RegistryObject<BlockEntityType<BackpackTile>> BACKPACK_TILE = TILE.register("backpack_tile", () -> BlockEntityType.Builder.of(BackpackTile::new, BACKPACK.get()).build(null));
    public static RegistryObject<BlockEntityType<SleepingBagTile>> SLEEPING_BAG_TILE = TILE.register("sleeping_bag", () -> new BlockEntityType<>(SleepingBagTile::new, Sets.newHashSet(SLEEPING_BAG_RED.get(), SLEEPING_BAG_BLUE.get(), SLEEPING_BAG_BLACK.get(), SLEEPING_BAG_BROWN.get(), SLEEPING_BAG_CYAN.get(), SLEEPING_BAG_GRAY.get(), SLEEPING_BAG_GREEN.get(), SLEEPING_BAG_LIGHT_BLUE.get(), SLEEPING_BAG_LIGHT_GRAY.get(), SLEEPING_BAG_LIME.get(), SLEEPING_BAG_MAGENTA.get(), SLEEPING_BAG_ORANGE.get(), SLEEPING_BAG_PINK.get(), SLEEPING_BAG_PURPLE.get(), SLEEPING_BAG_WHITE.get(), SLEEPING_BAG_YELLOW.get()), null));
    public static RegistryObject<BlockEntityType<PlacardTile>> PLACARD_TILE = TILE.register("placard_tile", () -> BlockEntityType.Builder.of(PlacardTile::new, PLACARD.get()).build(null));
    public static RegistryObject<BlockEntityType<CamoKeyStoneTile>> KEY_STONE_TILE = TILE.register("key_stone_tile", () -> BlockEntityType.Builder.of(CamoKeyStoneTile::new, KEY_STONE_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<CamoFluidTile>> CAMO_FLUID_TILE = TILE.register("camo_fluid_tile", () -> BlockEntityType.Builder.of(CamoFluidTile::new, CAMO_FLUID_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<CamoChestTile>> CAMO_CHEST_TILE = TILE.register("camo_chest_tile", () -> BlockEntityType.Builder.of(CamoChestTile::new, CAMO_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<CamoSpawnerTile>> CAMO_SPAWNER_TILE = TILE.register("camo_spawner_tile", () -> BlockEntityType.Builder.of(CamoSpawnerTile::new, CAMO_SPAWNER_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<MilestoneTile>> MILESTONE_TILE = TILE.register("milestone_tile", () -> BlockEntityType.Builder.of(MilestoneTile::new, MILESTONE_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<LockableChestTile>> LOCKABLE_CHEST_TILE = TILE.register("lockable_chest_tile", () -> BlockEntityType.Builder.of(LockableChestTile::new, LOCKABLE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<LockableTreasureChestTile>> LOCKABLE_TREASURE_CHEST_TILE = TILE.register("lockable_treasure_chest_tile", () -> BlockEntityType.Builder.of(LockableTreasureChestTile::new, LOCKABLE_TREASURE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<LockableDoubleChestTile>> LOCKABLE_DOUBLE_CHEST_TILE = TILE.register("lockable_double_chest_tile", () -> BlockEntityType.Builder.of(LockableDoubleChestTile::new, LOCKABLE_DOUBLE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<LockableDoubleTreasureChestTile>> LOCKABLE_DOUBLE_TREASURE_CHEST_TILE = TILE.register("lockable_double_treasure_chest_tile", () -> BlockEntityType.Builder.of(LockableDoubleTreasureChestTile::new, LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<TolkienSignTile>> MALLORN_SIGN_TILE = TILE.register("mallorn_sign", () -> BlockEntityType.Builder.of(TolkienSignTile::new, MALLORN_SIGN.get(), MALLORN_WALL_SIGN.get()).build(null));
    public static RegistryObject<BlockEntityType<TolkienSignTile>> MIRKWOOD_SIGN_TILE = TILE.register("mirkwood_sign", () -> BlockEntityType.Builder.of(TolkienSignTile::new, MIRKWOOD_SIGN.get(), MIRKWOOD_WALL_SIGN.get()).build(null));
    public static RegistryObject<BlockEntityType<TolkienSignTile>> CULUMALDA_SIGN_TILE = TILE.register("culumalda_sign", () -> BlockEntityType.Builder.of(TolkienSignTile::new, CULUMALDA_SIGN.get(), CULUMALDA_WALL_SIGN.get()).build(null));
    public static RegistryObject<BlockEntityType<TolkienSignTile>> LEBETHRON_SIGN_TILE = TILE.register("lebethron_sign", () -> BlockEntityType.Builder.of(TolkienSignTile::new, LEBETHRON_SIGN.get(), LEBETHRON_WALL_SIGN.get()).build(null));

    public String getName() {
        return NAME + " - Tile Entities";
    }
}
