package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.container.gui.MithrilKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class MithrilKeyItem extends KeyBaseItem {
    public MithrilKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new MithrilKeyAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}
