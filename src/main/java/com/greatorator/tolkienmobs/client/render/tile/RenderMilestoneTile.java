package com.greatorator.tolkienmobs.client.render.tile;

import com.greatorator.tolkienmobs.block.MilestoneBlock;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class RenderMilestoneTile extends TileEntityRenderer<MilestoneTile> {
    public RenderMilestoneTile(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(MilestoneTile te, float partialTicks, MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, int packedOverlay) {
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(MilestoneBlock.FACING);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        mStack.pushPose();
        mStack.translate(0.5, 0.5, 0.5);
        mStack.mulPose(new Quaternion(90, 0, 0, true));

        switch (facing) {
            case NORTH:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                break;
            case SOUTH:
                mStack.mulPose(new Quaternion(0, 0, -90, true));
                break;
            case WEST:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                break;
            case EAST:
                mStack.mulPose(new Quaternion(0, 0, 180, true));
                break;
        }

        FontRenderer fontRenderer = this.renderer.getFont();
        IReorderingProcessor irp = new StringTextComponent((TextFormatting.DARK_BLUE + te.milestoneName.get().replace("_", " "))).getVisualOrderText();
        fontRenderer.drawInBatch(irp, 10, -20, 16777215, false, mStack.last().pose(), getter, false, 0, packedLight);
//        drawNameString(te, (TextFormatting.DARK_BLUE + te.milestoneName.get().replace("_", " ")), 0, partialTicks);
        mStack.popPose();
    }

    private void drawNameString(MilestoneTile te, float partialTicks, MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, int packedOverlay) {
    }
}
