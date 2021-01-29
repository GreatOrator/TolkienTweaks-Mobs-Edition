package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.interfaces.TTMHasModel;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDoors extends BlockDoor implements TTMHasModel {
    public BlockDoors(Material materialIn) {
        super(materialIn);
    }

    public void registerModels() {
        TolkienMobs.proxy.registerModel(Item.getItemFromBlock(this), 0);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        if (this == TTMFeatures.DOOR_MALLORN) {
            return new ItemStack(TTMFeatures.ITEM_DOOR_MALLORN);
        }
        else if (this == TTMFeatures.DOOR_MIRKWOOD)
        {
            return new ItemStack(TTMFeatures.ITEM_DOOR_MIRKWOOD);
        }
        else if (this == TTMFeatures.DOOR_CULUMALDA)
        {
            return new ItemStack(TTMFeatures.ITEM_DOOR_CULUMALDA);
        }
        else if (this == TTMFeatures.DOOR_MORGULIRON)
        {
            return new ItemStack(TTMFeatures.ITEM_DOOR_MORGULIRON);
        }
        else if (this == TTMFeatures.DOOR_MITHRIL)
        {
            return new ItemStack(TTMFeatures.ITEM_DOOR_MITHRIL);
        }
        else {
            return new ItemStack(TTMFeatures.ITEM_DOOR_LEBETHRON);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return super.getItemDropped(state, rand, fortune);
    }
}
