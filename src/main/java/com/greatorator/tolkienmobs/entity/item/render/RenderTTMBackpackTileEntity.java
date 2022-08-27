package com.greatorator.tolkienmobs.entity.item.render;

import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RenderTTMBackpackTileEntity extends TileEntityRenderer<TTMBackpackTile> {
    public RenderTTMBackpackTileEntity(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TTMBackpackTile p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {

    }
}
