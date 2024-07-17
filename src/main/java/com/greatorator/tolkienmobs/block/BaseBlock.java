package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class BaseBlock extends ItemBlockBCore {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = false;
    private boolean hasLore = false;

    public BaseBlock(Block block, Item.Properties builder) {
        super(block, builder);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(Component.translatable(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        }
    }

    public BaseBlock setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    public BaseBlock setHasLore() {
        this.hasLore = true;
        return this;
    }

    public BaseBlock setItemHasUse() {
        this.itemHasUse = true;
        return this;
    }

    public BaseBlock setSpawnInfo() {
        this.canSpawnEntity = true;
        return this;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }
}