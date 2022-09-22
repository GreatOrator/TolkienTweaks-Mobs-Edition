package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.client.gui.MithrilKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class MithrilKeyItem extends KeyBaseItem {
    public MithrilKeyItem(Properties properties) {
        super(properties);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new MithrilKeyAccessScreen(player, stack.getHoverName(), null, getKey(stack)));
    }
}
