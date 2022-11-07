package com.greatorator.tolkienmobs.item.keys;

import com.greatorator.tolkienmobs.container.gui.MasterKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MasterKeyItem extends KeyBaseItem {
    public MasterKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    public void openGui(ItemStack stack, Player player) {
        Minecraft.getInstance().setScreen(new MasterKeyAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}
