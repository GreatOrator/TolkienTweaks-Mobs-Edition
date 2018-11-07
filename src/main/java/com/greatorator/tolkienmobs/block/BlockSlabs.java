package com.greatorator.tolkienmobs.block;

import java.util.Map;
import java.util.Random;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.BlockLogs.EnumType;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockSlabs extends BlockSlab implements IBCoreBlock
{
	public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
	private boolean isDouble;

	public BlockSlabs(boolean isDouble) {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
		this.isDouble = isDouble;
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return "tile." + TolkienMobs.MODID + ":slab_" + EnumType.byMetadata(meta).name + ".name";
	}

	@Override
	public boolean isDouble() {
		return isDouble;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return EnumType.byMetadata(stack.getMetadata() % 2);
	}

	@Override
	public boolean hasSubItemTypes() {
		return true;
	}

	@Override
	public Map<Integer, String> getNameOverrides() {
		return EnumType.LOG_NAME_LOOKUP; //TODO add a slab name lookup
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.WOODEN_SLAB);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Blocks.WOODEN_SLAB, 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata());
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(Blocks.WOODEN_SLAB, 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata());
	}

	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (BlockPlanks.EnumType blockplanks$enumtype : BlockPlanks.EnumType.values())
		{
			items.add(new ItemStack(this, 1, blockplanks$enumtype.getMetadata()));
		}
	}

	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta & 7));

		if (!this.isDouble())
		{
			iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}

		return iblockstate;
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();

		if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
		{
			i |= 8;
		}

		return i;
	}

	protected BlockStateContainer createBlockState()
	{
		return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
	}

	public int damageDropped(IBlockState state)
	{
		return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
	}
}