package com.greatorator.tolkienmobs.handler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TTMLore extends Item {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = true;
    private  boolean hasLore = false;

    public TTMLore(Item.Properties props) {
        super(props);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslationTextComponent(getTranslationKey() + ".lore").mergeStyle(TextFormatting.GOLD));
        }
    }

    public TTMLore setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    public TTMLore setHasLore() {
        this.hasLore = true;
        return  this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }
}