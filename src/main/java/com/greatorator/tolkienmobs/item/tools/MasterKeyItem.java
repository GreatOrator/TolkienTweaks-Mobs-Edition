package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.container.gui.MasterKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class MasterKeyItem extends KeyBaseItem {
    public MasterKeyItem(Properties properties, int uses) {
        super(properties, uses);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new MasterKeyAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }
}
