package com.greatorator.tolkienmobs.client.render.tile;

import com.brandon3055.brandonscore.client.render.TESRBase;
import com.greatorator.tolkienmobs.tileentity.TTMFireplaceTile;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RenderTTMFireplaceTile extends TESRBase<TTMFireplaceTile> {
    public RenderTTMFireplaceTile(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

//    @Override
//    public void render(TileTMFireplace te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
//        ItemStack stack1 = te.getStackInSlot(0);
//        ItemStack stack2 = te.getStackInSlot(1);
//        ItemStack stack3 = te.getStackInSlot(3);
//
//        if (stack1.isEmpty() && stack2.isEmpty() && stack3.isEmpty()) return;
//
//        IBlockState state = te.getState(TTMFeatures.BLOCK_TMFIREPLACE);
//        EnumFacing facing = state.getValue(BlockTMFireplace.FACING);
//
//        ItemStack bars = new ItemStack(Blocks.IRON_BARS);
//
//        GlStateManager.pushMatrix();
//        GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
//        GlStateManager.rotate(90, 1, 0, 0);
//
//        switch (facing) {
//            case NORTH:
//                GlStateManager.rotate(90, 0, 0, 1);
//                break;
//            case SOUTH:
//                GlStateManager.rotate(-90, 0, 0, 1);
//                break;
//            case WEST:
//                GlStateManager.rotate(0, 0, 0, 1);
//                break;
//            case EAST:
//                GlStateManager.rotate(180, 0, 0, 1);
//                break;
//        }
//
//        GlStateManager.scale(0.85, 0.85, 0.85);
//        Minecraft.getMinecraft().getRenderItem().renderItem(bars, ItemCameraTransforms.TransformType.NONE);
//
//        GlStateManager.translate(0, 0, -0.04);
//        GlStateManager.translate(0.1, -0.3, 0);
//
//        if (!stack1.isEmpty()) {
//            Minecraft.getMinecraft().getRenderItem().renderItem(stack1, ItemCameraTransforms.TransformType.GROUND);
//        }
//
//        GlStateManager.translate(-0.2, 0.4, 0);
//        if (!stack2.isEmpty()) {
//            Minecraft.getMinecraft().getRenderItem().renderItem(stack2, ItemCameraTransforms.TransformType.GROUND);
//        }
//        GlStateManager.translate(-0.2, -0.4, 0.5);
//        GlStateManager.rotate(90, 0, 0, 1);
//        if (!stack3.isEmpty()) {
//            Minecraft.getMinecraft().getRenderItem().renderItem(stack3, ItemCameraTransforms.TransformType.GROUND);
//        }
//
//        GlStateManager.popMatrix();
//    }
}
