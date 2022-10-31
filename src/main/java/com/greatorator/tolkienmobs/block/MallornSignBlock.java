package com.greatorator.tolkienmobs.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class MallornSignBlock extends WallSignBlock {
    public MallornSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new MallornSignTile();
    }
}
