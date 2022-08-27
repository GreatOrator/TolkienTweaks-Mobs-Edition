package com.greatorator.tolkienmobs.entity.tile;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.BlockTTMSleepingBag;
import net.minecraft.item.DyeColor;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TTMSleepingBagTile extends TileEntity {
    private DyeColor color;

    public TTMSleepingBagTile() {
        super(TTMContent.SLEEPING_BAG_TILE.get());
    }

    public TTMSleepingBagTile(DyeColor colorIn) {
        this();
        this.setColor(colorIn);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 11, this.getUpdateTag());
    }

    @OnlyIn(Dist.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((BlockTTMSleepingBag) this.getBlockState().getBlock()).getColor();
        }
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
