package com.greatorator.tolkienmobs.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class MirkwoodSignBlock extends WallSignBlock {
    public MirkwoodSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new MirkwoodSignTile();
    }
}
