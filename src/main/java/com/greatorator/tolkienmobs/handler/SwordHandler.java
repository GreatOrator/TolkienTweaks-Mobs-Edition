package com.greatorator.tolkienmobs.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SwordHandler extends SwordItem {
    public boolean hasEffectOverride = false;
    private final boolean hasLore = false;

    public SwordHandler(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        }
    }

    public SwordHandler setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }
}