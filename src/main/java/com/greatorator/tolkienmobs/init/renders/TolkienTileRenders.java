package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.entity.tile.render.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTiles.*;

public class TolkienTileRenders {
    public static void init() {
        BlockEntityRenderers.register(TMFIREPLACE_TILE.get(), FireplaceTileRender::new);
        BlockEntityRenderers.register(PIGGYBANK_TILE.get(), PiggyBankTileRender::new);
        BlockEntityRenderers.register(BACKPACK_TILE.get(), BackpackTileRender::new);
        BlockEntityRenderers.register(PLACARD_TILE.get(), PlacardTileRender::new);
        BlockEntityRenderers.register(SIGN_TILE.get(), TolkienSignTileRender::new);
        BlockEntityRenderers.register(MILESTONE_TILE.get(), MilestoneTileRender::new);
        BlockEntityRenderers.register(KEY_STONE_TILE.get(), CamoKeyStoneTileRender::new);
        BlockEntityRenderers.register(CAMO_SPAWNER_TILE.get(), CamoSpawnerTileRender::new);
        BlockEntityRenderers.register(CAMO_CHEST_TILE.get(), CamoChestTileRender::new);
        BlockEntityRenderers.register(LOCKABLE_CHEST_TILE.get(), LockableChestTileRender::new);
        BlockEntityRenderers.register(LOCKABLE_TREASURE_CHEST_TILE.get(), LockableTreasureChestTileRender::new);
        BlockEntityRenderers.register(LOCKABLE_DOUBLE_CHEST_TILE.get(), LockableDoubleChestTileRender::new);
        BlockEntityRenderers.register(LOCKABLE_DOUBLE_TREASURE_CHEST_TILE.get(), LockableDoubleTreasureChestTileRender::new);
    }

    public String getName() {
        return NAME + " - Tile Renders";
    }
}