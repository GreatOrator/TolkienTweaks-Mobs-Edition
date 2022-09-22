package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyAccessTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

public class BronzeKeyAccessScreen extends KeyBaseAccessScreen {
    protected GuiToolkit<BronzeKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 78).setTranslationPrefix("screen.tolkienmobs.bronze_key");

    public BronzeKeyAccessScreen(PlayerEntity playerIn, ITextComponent title, IKeyAccessTile lockable, String currentCode) {
        super(playerIn, title, lockable, currentCode);
    }
}