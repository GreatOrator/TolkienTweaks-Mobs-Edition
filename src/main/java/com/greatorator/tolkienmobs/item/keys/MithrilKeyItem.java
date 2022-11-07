package com.greatorator.tolkienmobs.item.keys;

import com.greatorator.tolkienmobs.container.gui.MithrilKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MithrilKeyItem extends KeyBaseItem {
    public MithrilKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    public void openGui(ItemStack stack, Player player) {
        Minecraft.getInstance().setScreen(new MithrilKeyAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}
