//package com.greatorator.tolkienmobs.block.itemblock;
//
//import com.brandon3055.brandonscore.blocks.BlockBCore;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockStone;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.MapColor;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyEnum;
//import net.minecraft.block.state.BlockFaceShape;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.Entity;
//import net.minecraft.init.Blocks;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IBlockAccess;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.Random;
//
//public class BlockStonePath extends BlockBCore
//{
//    protected static final AxisAlignedBB STONE_PATH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
//    protected static final AxisAlignedBB field_194405_c = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
//    public static final PropertyEnum<BlockStone.EnumType> VARIANT = PropertyEnum.<BlockStone.EnumType>create("variant", BlockStone.EnumType.class);
//
//    public BlockStonePath()
//    {
//        super(Material.ROCK);
//        setSoundType(SoundType.STONE);
//        this.setLightOpacity(255);
//    }
//
//    /**
//     * Get the MapColor for this Block and the given BlockState
//     */
//    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
//    {
//        return MapColor.YELLOW_STAINED_HARDENED_CLAY;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
//    {
//        switch (side)
//        {
//            case UP:
//                return true;
//            case NORTH:
//            case SOUTH:
//            case WEST:
//            case EAST:
//                IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
//                Block block = iblockstate.getBlock();
//                return !iblockstate.isOpaqueCube() && block != TTMFeatures.STONE_PATH;
//            default:
//                return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
//        }
//    }
//
//    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
//    {
//        super.onBlockAdded(worldIn, pos, state);
//        this.updateBlockState(worldIn, pos);
//    }
//
//    private void updateBlockState(World worldIn, BlockPos pos)
//    {
//        if (worldIn.getBlockState(pos.up()).getMaterial().isSolid())
//        {
//            BlockStonePath.turnToCobble(worldIn, pos);
//        }
//    }
//
//    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
//    {
//        return STONE_PATH_AABB;
//    }
//
//    public boolean isOpaqueCube(IBlockState state)
//    {
//        return false;
//    }
//
//    public boolean isFullCube(IBlockState state)
//    {
//        return false;
//    }
//
//    public Item getItemDropped(IBlockState state, Random rand, int fortune)
//    {
//        return Item.getItemFromBlock(Blocks.COBBLESTONE);
//    }
//
//    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
//    {
//        return new ItemStack(this);
//    }
//
//    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
//    {
//        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//        this.updateBlockState(worldIn, pos);
//    }
//
//    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
//    {
//        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
//    }
//
//    protected static void turnToCobble(World p_190970_0_, BlockPos worldIn)
//    {
//        p_190970_0_.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState());
//        AxisAlignedBB axisalignedbb = field_194405_c.offset(worldIn);
//
//        for (Entity entity : p_190970_0_.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb))
//        {
//            double d0 = Math.min(axisalignedbb.maxY - axisalignedbb.minY, axisalignedbb.maxY - entity.getEntityBoundingBox().minY);
//            entity.setPositionAndUpdate(entity.posX, entity.posY + d0 + 0.001D, entity.posZ);
//        }
//    }
//}