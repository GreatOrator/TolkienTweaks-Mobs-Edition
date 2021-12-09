package com.greatorator.tolkienmobs.integration.curios;

import com.greatorator.tolkienmobs.handler.interfaces.ITTMEquip;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

/** Borrowed from Draconic Evolution */
public class TTMCurioWrapper implements ICurio {
    private ITTMEquip item;
    private ItemStack stack;

    public TTMCurioWrapper(ItemStack stack) {
        this.item = (ITTMEquip) stack.getItem();
        this.stack = stack;
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
        item.equipmentTick(stack, livingEntity);
    }


    @Override
    public boolean canEquip(String identifier, LivingEntity livingEntity) {
        return item.canEquip(livingEntity);
    }

    @Override
    public List<ITextComponent> getTagsTooltip(List<ITextComponent> tagTooltips) {
        return item.getTagsTooltip(stack, tagTooltips);
    }

    @Override
    public boolean canRightClickEquip() {
        return item.canRightClickEquip(stack);
    }

    @Override
    public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
//        item.renderEquipment(matrixStack, renderTypeBuffer, light, light, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }
}
