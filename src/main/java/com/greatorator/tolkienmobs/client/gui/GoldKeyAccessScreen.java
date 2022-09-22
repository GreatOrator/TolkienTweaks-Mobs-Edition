package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyAccessTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

public class GoldKeyAccessScreen extends KeyBaseAccessScreen {
    protected GuiToolkit<GoldKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 78).setTranslationPrefix("screen.tolkienmobs.gold_key");

    public GoldKeyAccessScreen(PlayerEntity playerIn, ITextComponent title, IKeyAccessTile lockable, String currentCode) {
        super(playerIn, title, lockable, currentCode);
    }
}