package com.greatorator.tolkienmobs.handler;

import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class LoreBlock extends ItemBlockBCore {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = false;
    private boolean hasLore = false;

    public LoreBlock(Block block, Item.Properties builder) {
        super(block, builder);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        }
    }

    public LoreBlock setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    public LoreBlock setHasLore() {
        this.hasLore = true;
        return this;
    }

    public LoreBlock setItemHasUse() {
        this.itemHasUse = true;
        return this;
    }

    public LoreBlock setSpawnInfo() {
        this.canSpawnEntity = true;
        return this;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }
}