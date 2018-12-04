package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import com.greatorator.tolkienmobs.block.BlockLogs.EnumType;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenCulumaldaTree;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenLebethronTree;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMallornTree;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMirkwoodTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Map;
import java.util.Random;

public class BlockSaplings extends BlockBush implements IGrowable, IBCoreBlock
{
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", EnumType.class);

    public BlockSaplings()
    {
        setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MALLORN).withProperty(STAGE, 0));
    }

    //Sapling Shape
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    //Variants
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(EnumType type : EnumType.values())
        {
            items.add(new ItemStack(this, 1, type.getMeta()));
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 1)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();
        i = i | state.getValue(STAGE) << 3;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT, STAGE);
    }

    //Tree Code

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if(state.getValue(STAGE) == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, rand, pos, state);
        }
    }

    public void generateTree(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if(!TerrainGen.saplingGrowTree(world, rand, pos)) return;
        WorldGenerator gen = (rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false));
        boolean flag = false;
        int i = 0, j = 0;

        switch(state.getValue(VARIANT))
        {
            case MALLORN:
                gen = new WorldGenMallornTree(false);
                break;
            case MIRKWOOD:
                gen = new WorldGenMirkwoodTree(false);
                break;
            case CULUMALDA:
                gen = new WorldGenCulumaldaTree(false);
                break;
            case LEBETHRON:
                gen = new WorldGenLebethronTree(false);
                break;
        }

        IBlockState iblockstate = Blocks.AIR.getDefaultState();
        if(flag)
        {
            world.setBlockState(pos.add(i, 0, j), iblockstate, 4);
            world.setBlockState(pos.add(i + 1, 0, j), iblockstate, 4);
            world.setBlockState(pos.add(i, 0, j + 1), iblockstate, 4);
            world.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate, 4);
        }
        else
        {
            world.setBlockState(pos, iblockstate, 4);
        }

        if(!gen.generate(world, rand, pos.add(i, 0, j)))
        {
            if(flag)
            {
                world.setBlockState(pos.add(i, 0, j), iblockstate, 4);
                world.setBlockState(pos.add(i + 1, 0, j), iblockstate, 4);
                world.setBlockState(pos.add(i, 0, j + 1), iblockstate, 4);
                world.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate, 4);
            }
            else
            {
                world.setBlockState(pos, iblockstate, 4);
            }
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return EnumType.SAPLING_NAME_LOOKUP;
    }
}

