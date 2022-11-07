package com.greatorator.tolkienmobs.item.keys;

import com.greatorator.tolkienmobs.container.gui.BronzeKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BronzeKeyItem extends KeyBaseItem {
    public BronzeKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    @Override
    public void openGui(ItemStack stack, Player playerIn) {
        Minecraft.getInstance().setScreen(new BronzeKeyAccessScreen(playerIn, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}
