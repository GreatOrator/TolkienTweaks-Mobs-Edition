package com.greatorator.tolkienmobs.handler;

import com.brandon3055.brandonscore.items.ItemBCore;
import mezz.jei.util.Translator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TTMLore extends ItemBCore {
    public boolean hasEffectOverride = false;

    public TTMLore(int stackSize) {
        this.setMaxStackSize(stackSize);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, list, flagIn);
        list.add(TextFormatting.GOLD + Translator.translateToLocalFormatted("lore." + getUnlocalizedName()));
    }

    public TTMLore setEffectOverride(boolean hasEffectOverride) {
        this.hasEffectOverride = hasEffectOverride;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }
}