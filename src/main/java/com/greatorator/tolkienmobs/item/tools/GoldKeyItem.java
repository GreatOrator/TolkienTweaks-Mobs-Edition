package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.container.gui.GoldKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class GoldKeyItem extends KeyBaseItem {
    public GoldKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new GoldKeyAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}