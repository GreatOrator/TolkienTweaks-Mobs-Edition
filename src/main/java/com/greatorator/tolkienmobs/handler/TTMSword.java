package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.utils.TTMTranslator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TTMSword extends ItemSword {
    public boolean hasEffectOverride = false;
    public TTMSword(ToolMaterial material) {
        super(material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, list, flagIn);
        list.add(TextFormatting.GOLD + TTMTranslator.translateToLocalFormatted("lore." + getUnlocalizedName()));
    }

    public TTMSword setEffectOverride(boolean hasEffectOverride) {
        this.hasEffectOverride = hasEffectOverride;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }
}
