package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.client.gui.GoldKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class GoldKeyItem extends KeyBaseItem {
    public GoldKeyItem(Properties properties) {
        super(properties);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new GoldKeyAccessScreen(player, stack.getHoverName(), null, getKey(stack)));
    }
}
