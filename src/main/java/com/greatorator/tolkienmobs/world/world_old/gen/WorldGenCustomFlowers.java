//package com.greatorator.tolkienmobs.world.world_old.gen;
//
//        import com.greatorator.tolkienmobs.block.itemblock.BlockFlowers;
//        import com.greatorator.tolkienmobs.init.TTMFeatures;
//        import net.minecraft.block.BlockFlower;
//        import net.minecraft.block.state.IBlockState;
//        import net.minecraft.util.math.BlockPos;
//        import net.minecraft.world.World;
//        import net.minecraft.world.gen.feature.WorldGenFlowers;
//
//        import java.util.ArrayList;
//        import java.util.List;
//        import java.util.Random;
//
//public class WorldGenCustomFlowers extends WorldGenFlowers {
//
//    private List<IBlockState> genableFlowers = new ArrayList<>();
//    private boolean genFlowerList;
//    private IBlockState biomeFlower;
//
//    public WorldGenCustomFlowers() {
//        super(null, null);
//
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.MIRKWOOD));
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.SIMBELMYNE));
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ALFIRIN));
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS));
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.NIPHREDIL));
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.SWAMPMILKWEED));
//        genableFlowers.add(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.LILLYOFTHEVALLEY));
//    }
//
//    public void setGeneratedBlock(BlockFlower flowerIn, BlockFlower.EnumFlowerType typeIn) {}
//
//    public boolean generate(World worldIn, Random rand, BlockPos position) {
//        if (!genFlowerList) {
//            IBlockState flowerState = genableFlowers.get(rand.nextInt(genableFlowers.size()));
//
//            for (int i = 0; i < 64; ++i) {
//                BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
//
//                if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && TTMFeatures.FLOWERS.canBlockStay(worldIn, blockpos, flowerState)) {
//                    worldIn.setBlockState(blockpos, flowerState, 2);
//                }
//            }
//        }
//        else {
//            IBlockState flowerState = biomeFlower;
//
//            for (int i = 0; i < 64; ++i) {
//                BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
//
//                if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && TTMFeatures.FLOWERS.canBlockStay(worldIn, blockpos, flowerState)) {
//                    worldIn.setBlockState(blockpos, flowerState, 2);
//                }
//            }
//        }
//
//        return true;
//    }
//
//    public void setGenFlowerList(boolean genFlowerList) {
//        this.genFlowerList = genFlowerList;
//    }
//
//    public void setBiomeFlower(IBlockState biomeFlower) {
//        this.biomeFlower = biomeFlower;
//    }
//}