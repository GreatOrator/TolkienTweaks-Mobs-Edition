package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.container.gui.SilverKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class SilverKeyItem extends KeyBaseItem {
    public SilverKeyItem(Properties properties) {
        super(properties);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new SilverKeyAccessScreen(player, stack.getHoverName(), null, getKey(stack), getUses(stack)));
    }
}
