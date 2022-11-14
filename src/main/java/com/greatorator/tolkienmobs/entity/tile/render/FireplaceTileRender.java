package com.greatorator.tolkienmobs.entity.tile.render;

import com.greatorator.tolkienmobs.block.FireplaceBlock;
import com.greatorator.tolkienmobs.entity.tile.FireplaceTile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.client.renderer.block.model.ItemTransforms.TransformType.GROUND;
import static net.minecraft.client.renderer.block.model.ItemTransforms.TransformType.NONE;

public class FireplaceTileRender implements BlockEntityRenderer<FireplaceTile> {
    public FireplaceTileRender(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(FireplaceTile te, float partialTicks, PoseStack mStack, MultiBufferSource getter, int packedLight, int packedOverlay) {
        ItemStack stack1 = te.itemHandler.getStackInSlot(0);
        ItemStack stack2 = te.itemHandler.getStackInSlot(1);
        ItemStack stack3 = te.itemHandler.getStackInSlot(3);
        if (stack1.isEmpty() && stack2.isEmpty() && stack3.isEmpty()) return;
        BlockState state = te.getBlockState();
        Direction facing = state.getValue(FireplaceBlock.FACING);
        ItemStack bars = new ItemStack(Blocks.IRON_BARS);
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

        mStack.scale(0.85F, 0.85F, 0.85F);
        mc.getItemRenderer().renderStatic(bars, NONE, packedLight, packedOverlay, mStack, getter, 0);

        mStack.translate(0, 0, -0.04);
        mStack.translate(0.1, -0.3, 0);
        if (!stack1.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack1, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.translate(-0.2, 0.4, 0);
        if (!stack2.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack2, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.translate(-0.2, -0.4, 0.5);
        mStack.mulPose(new Quaternion(0, 0, 90, true));
        if (!stack3.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack3, GROUND, packedLight, packedOverlay, mStack, getter, 0);
        }

        mStack.popPose();
    }
}
