package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.interfaces.TTMHasModel;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
        } else {
            return new ItemStack(TTMFeatures.ITEM_DOOR_MALLORN); //You dont need the if/else for a single door but if you want to add more then just add more else if blocks.
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return super.getItemDropped(state, rand, fortune);
    }
}
