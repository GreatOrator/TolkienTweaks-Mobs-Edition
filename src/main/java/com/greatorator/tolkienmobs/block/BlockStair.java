package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import com.greatorator.tolkienmobs.block.BlockLogs.EnumType;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

import java.util.Map;

public class BlockStair extends BlockStairs implements IBCoreBlock {


    public BlockStair(IBlockState state){
        super(state);
        this.useNeighborBrightness = true;
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return EnumType.LOG_NAME_LOOKUP;
    }
}
