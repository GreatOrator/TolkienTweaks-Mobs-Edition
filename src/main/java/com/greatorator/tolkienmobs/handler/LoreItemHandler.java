package com.greatorator.tolkienmobs.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class LoreItemHandler extends Item {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = false;
    private boolean hasLore = false;

    public LoreItemHandler(Item.Properties props) {
        super(props);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        }
    }

    public LoreItemHandler setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    public LoreItemHandler setHasLore() {
        this.hasLore = true;
        return this;
    }

    public LoreItemHandler setItemHasUse() {
        this.itemHasUse = true;
        return this;
    }

    public LoreItemHandler setSpawnInfo() {
        this.canSpawnEntity = true;
        return this;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }
}