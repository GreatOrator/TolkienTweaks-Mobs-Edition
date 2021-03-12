package com.greatorator.tolkienmobs.handler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TTMSword extends SwordItem {
    public boolean hasEffectOverride = false;
    private boolean hasLore = false;

    public TTMSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslationTextComponent(getTranslationKey() + ".lore").mergeStyle(TextFormatting.GOLD));
        }
    }

    public TTMSword setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }
}
