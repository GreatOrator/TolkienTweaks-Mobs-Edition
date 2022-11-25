package com.greatorator.tolkienmobs.entity.tile.render;

import com.greatorator.tolkienmobs.block.TrinketTableBlock;
import com.greatorator.tolkienmobs.entity.tile.TrinketTableTile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.client.renderer.block.model.ItemTransforms.TransformType.GROUND;

public class TrinketTableTileRender implements BlockEntityRenderer<TrinketTableTile> {
    public TrinketTableTileRender(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(TrinketTableTile te, float partialTicks, PoseStack mStack, MultiBufferSource getter, int packedLight, int packedOverlay) {
        ItemStack stack1 = te.itemHandler.getStackInSlot(0);
        ItemStack stack3 = te.itemHandler.getStackInSlot(1);
        ItemStack stack2 = te.itemHandler.getStackInSlot(2);
        ItemStack stack4 = te.itemHandler.getStackInSlot(3);
        if (stack1.isEmpty() && stack2.isEmpty() && stack3.isEmpty() && stack4.isEmpty()) return;
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(TrinketTableBlock.FACING);
        Minecraft mc = Minecraft.getInstance();

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
        mStack.scale(0.3F, 0.3F, 0.3F);

        mStack.translate(0.69, -0.91, -0.8);
        if (!stack1.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack1, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.translate(-0.69, 0, 0);
        if (!stack2.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack2, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.translate(-0.68, 0, 0);
        if (!stack3.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack3, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.scale(1.5F, 1.5F, 1.5F);
        mStack.translate(0.45, 0.6, -0.1);
        if (!stack4.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack4, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.popPose();
    }
}
