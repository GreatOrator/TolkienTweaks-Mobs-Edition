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
    public static RegistryObject<BlockEntityType<TolkienSignTile>> SIGN_TILE = TILE.register("tolkien_sign_tile", () -> new BlockEntityType<>(TolkienSignTile::new, Sets.newHashSet(MALLORN_SIGN.get(), MIRKWOOD_SIGN.get(), CULUMALDA_SIGN.get(), LEBETHRON_SIGN.get(), DEADWOOD_SIGN.get(), FANGORNOAK_SIGN.get(), MALLORN_WALL_SIGN.get(), MIRKWOOD_WALL_SIGN.get(), CULUMALDA_WALL_SIGN.get(), LEBETHRON_WALL_SIGN.get(), DEADWOOD_WALL_SIGN.get(), FANGORNOAK_WALL_SIGN.get()), null));
    public static RegistryObject<BlockEntityType<BaseBarrelTile>> BARREL_TILE = TILE.register("base_barrel_tile", () -> new BlockEntityType<>(BaseBarrelTile::new, Sets.newHashSet(BARREL_MALLORN.get(), BARREL_MIRKWOOD.get(), BARREL_CULUMALDA.get(), BARREL_LEBETHRON.get(), BARREL_DEADWOOD.get(), BARREL_FANGORNOAK.get()), null));
    public static RegistryObject<BlockEntityType<TrinketTableTile>> TRINKETTABLE_TILE = TILE.register("trinket_table_tile", () -> BlockEntityType.Builder.of(TrinketTableTile::new, TRINKET_TABLE.get()).build(null));

    public String getName() {
        return NAME + " - Tile Entities";
    }
}
