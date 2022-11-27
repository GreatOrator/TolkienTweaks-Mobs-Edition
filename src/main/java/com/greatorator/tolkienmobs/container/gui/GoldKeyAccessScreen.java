package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.greatorator.tolkienmobs.handler.interfaces.KeyBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class GoldKeyAccessScreen extends KeyBaseAccessScreen {
    protected GuiToolkit<GoldKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 90).setTranslationPrefix("screen.tolkienmobs.gold_key");

    public GoldKeyAccessScreen(Player playerIn, Component title, KeyBase lockable, String currentCode, int uses) {
        super(playerIn, title, lockable, currentCode, uses);
    }
}