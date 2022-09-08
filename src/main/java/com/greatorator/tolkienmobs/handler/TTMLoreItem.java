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

public class TTMLoreItem extends Item {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = false;
    private boolean hasLore = false;

    public TTMLoreItem(Item.Properties props) {
        super(props);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslationTextComponent(getDescriptionId() + ".lore").withStyle(TextFormatting.GOLD));
        }
    }

    public TTMLoreItem setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    public TTMLoreItem setHasLore() {
        this.hasLore = true;
        return this;
    }

    public TTMLoreItem setItemHasUse() {
        this.itemHasUse = true;
        return this;
    }

    public TTMLoreItem setSpawnInfo() {
        this.canSpawnEntity = true;
        return this;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }
}