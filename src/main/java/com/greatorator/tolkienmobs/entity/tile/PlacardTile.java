package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

/**
 * Created by brandon3055 on 6/12/18.
 */
public class PlacardTile extends TileBCore implements IForgeBlockEntity {

    public PlacardTile(BlockPos blockPos, BlockState blockState) {
        super(TolkienTiles.PLACARD_TILE.get(), blockPos, blockState);
    }
    public PlacardTile(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(TolkienTiles.PLACARD_TILE.get(), pos, state);
    }
}