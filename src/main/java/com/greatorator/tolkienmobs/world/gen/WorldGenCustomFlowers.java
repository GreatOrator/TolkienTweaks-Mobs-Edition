package com.greatorator.tolkienmobs.world.gen;

        import com.greatorator.tolkienmobs.block.BlockFlowers;
        import com.greatorator.tolkienmobs.init.TTMFeatures;
        import net.minecraft.block.BlockFlower;
        import net.minecraft.block.state.IBlockState;
        import net.minecraft.util.math.BlockPos;
        import net.minecraft.world.World;
        import net.minecraft.world.gen.feature.WorldGenFlowers;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;

public class WorldGenCustomFlowers extends WorldGenFlowers {

    private List<IBlockState> genableFlowers = new ArrayList<>();

    public WorldGenCustomFlowers() {
        super(null, null);

        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.MIRKWOOD));
        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.SIMBELMYNE));
    }

    public void setGeneratedBlock(BlockFlower flowerIn, BlockFlower.EnumFlowerType typeIn) {}

    private static final BlockFlowers.EnumType[] BIOME_FLOWERS = new BlockFlowers.EnumType[]{BlockFlowers.EnumType.MIRKWOOD, BlockFlowers.EnumType.SIMBELMYNE};

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        IBlockState flowerState = genableFlowers.get(rand.nextInt(genableFlowers.size()));

        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255)) {
                worldIn.setBlockState(blockpos, flowerState, 2);
            }
        }

        return true;
    }
}