package com.greatorator.tolkienmobs.entity.tile;

import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SleepingBagTile extends BlockEntity {
    private DyeColor color;

    public SleepingBagTile(BlockPos blockPos, BlockState blockState) {
        super(TolkienTiles.SLEEPING_BAG_TILE.get(), blockPos, blockState);
        this.color = ((SleepingBagBlock)blockState.getBlock()).getColor();
    }

    public SleepingBagTile(BlockPos blockPos, BlockState blockState, DyeColor colorIn) {
        super(TolkienTiles.SLEEPING_BAG_TILE.get(), blockPos, blockState);
        this.color = colorIn;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @OnlyIn(Dist.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((SleepingBagBlock) this.getBlockState().getBlock()).getColor();
        }
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
