package com.greatorator.tolkienmobs.tile;

import com.brandon3055.brandonscore.blocks.TileBCBase;
import com.brandon3055.brandonscore.lib.ITilePlaceListener;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedEnum;
import com.greatorator.tolkienmobs.block.BlockSigns.EnumType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by brandon3055 on 6/12/18.
 */
public class TileSign extends TileBCBase implements ITilePlaceListener {

    public final ManagedEnum<EnumType> TYPE = register("type", new ManagedEnum<>(EnumType.EMPTY)).saveToTile().syncViaTile().trigerUpdate().finish();
    public final ManagedEnum<EnumFacing> ROTATION = register("rotation", new ManagedEnum<>(EnumFacing.NORTH)).saveToTile().syncViaTile().trigerUpdate().finish();
    public final ManagedBool WALL = register("wall", new ManagedBool(false)).saveToTile().syncViaTile().trigerUpdate().finish();

    @Override
    public void onTilePlaced(World world, BlockPos pos, EnumFacing placedAgainst, float hitX, float hitY, float hitZ, EntityPlayer placer, ItemStack stack) {
        WALL.value = placedAgainst != EnumFacing.DOWN;
        ROTATION.value = placer.getHorizontalFacing();
        TYPE.value = EnumType.byMetadata(stack.getItemDamage());
        updateBlock();
    }
}
