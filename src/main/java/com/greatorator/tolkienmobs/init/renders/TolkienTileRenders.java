package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.entity.tile.render.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTiles.*;

public class TolkienTileRenders {
    public static void init() {
        BlockEntityRenderers.register(TMFIREPLACE_TILE.get(), RenderFireplaceTile::new);
        BlockEntityRenderers.register(PIGGYBANK_TILE.get(), RenderPiggyBankTile::new);
        BlockEntityRenderers.register(BACKPACK_TILE.get(), RenderBackpackTile::new);
        BlockEntityRenderers.register(PLACARD_TILE.get(), RenderPlacardTile::new);
        BlockEntityRenderers.register(SIGN_TILE.get(), TolkienSignTileRender::new);
        BlockEntityRenderers.register(MILESTONE_TILE.get(), RenderMilestoneTile::new);
        BlockEntityRenderers.register(KEY_STONE_TILE.get(), RenderCamoKeyStoneTile::new);
        BlockEntityRenderers.register(CAMO_SPAWNER_TILE.get(), RenderCamoSpawnerTile::new);
        BlockEntityRenderers.register(CAMO_CHEST_TILE.get(), RenderCamoChestTile::new);
        BlockEntityRenderers.register(LOCKABLE_CHEST_TILE.get(), RenderLockableChestTile::new);
        BlockEntityRenderers.register(LOCKABLE_TREASURE_CHEST_TILE.get(), RenderLockableTreasureChestTile::new);
        BlockEntityRenderers.register(LOCKABLE_DOUBLE_CHEST_TILE.get(), RenderLockableDoubleChestTile::new);
        BlockEntityRenderers.register(LOCKABLE_DOUBLE_TREASURE_CHEST_TILE.get(), RenderLockableDoubleTreasureChestTile::new);
    }

    public String getName() {
        return NAME + " - Tile Renders";
    }
}