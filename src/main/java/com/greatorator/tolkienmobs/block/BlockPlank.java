//package com.greatorator.tolkienmobs.block;
//
//import com.brandon3055.brandonscore.blocks.BlockBCore;
//import com.greatorator.tolkienmobs.block.BlockLogs.EnumType;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyEnum;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.world.World;
//
//public class BlockPlank extends BlockBCore {
//    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
//
//
//    public BlockPlank() {
//        super(Material.WOOD);
//        setSoundType(SoundType.WOOD);
//        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MALLORN));
//
//        for (EnumType type : EnumType.values()) {
//            addName(type.meta, "planks_" + type.name);
//        }
//    }
//
//    @Override
//    public int damageDropped(IBlockState state) {
//        return state.getValue(VARIANT).getMeta();
//    }
//
//    @Override
//    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
//        for (EnumType type : EnumType.values()) {
//            items.add(new ItemStack(this, 1, type.getMeta()));
//        }
//    }
//
//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state) {
//        return state.getValue(VARIANT).getMeta();
//    }
//
//    @Override
//    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, PlayerEntity player) {
//        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, VARIANT);
//    }
//}