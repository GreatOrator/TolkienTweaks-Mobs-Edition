package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.container.gui.BronzeKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BronzeKeyItem extends KeyBaseItem {
    public BronzeKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    @Override
    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new BronzeKeyAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}
