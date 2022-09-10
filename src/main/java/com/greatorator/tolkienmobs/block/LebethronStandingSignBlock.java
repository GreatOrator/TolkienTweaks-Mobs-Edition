package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.LebethronSignTile;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class LebethronStandingSignBlock extends StandingSignBlock {
    public LebethronStandingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new LebethronSignTile();
    }
}
