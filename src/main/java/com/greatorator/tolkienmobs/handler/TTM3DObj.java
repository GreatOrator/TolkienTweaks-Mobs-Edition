package com.greatorator.tolkienmobs.handler;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import com.google.common.collect.ImmutableMap;
import com.greatorator.tolkienmobs.client.render.tools.TTMObjItemRender;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IModelTransform;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.TransformationMatrix;

import net.minecraft.item.Item.Properties;

public class TTM3DObj extends TTMSword implements IItemRenderer {
    private String toolRender;
    private String toolTexture;

    public TTM3DObj(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn, String toolRender, String toolTexture) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
        this.toolRender = toolRender;
        this.toolTexture = toolTexture;
    }

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        new TTMObjItemRender(toolRender, toolTexture);
    }

    @Override
    public IModelTransform getModelTransform() {
        return TransformUtils.DEFAULT_TOOL;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }
}
