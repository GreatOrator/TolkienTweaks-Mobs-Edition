package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;

import java.util.Map;

public class BlockTMStairs extends BlockStairs implements IBCoreBlock {
    public static final PropertyEnum<BlockLogs.EnumType> VARIANT = PropertyEnum.create("variant", BlockLogs.EnumType.class);

    public BlockTMStairs(IBlockState modelState)
    {
        super(modelState);
        setSoundType(SoundType.WOOD);

        this.setDefaultState(modelState.withProperty(VARIANT, BlockLogs.EnumType.MALLORN));

        this.useNeighborBrightness = true;
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return BlockLogs.EnumType.LOG_NAME_LOOKUP; //TODO add a slab name lookup
    }
}
