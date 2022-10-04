package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyAccessTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

public class SilverKeyAccessScreen extends KeyBaseAccessScreen {
    protected GuiToolkit<SilverKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 90).setTranslationPrefix("screen.tolkienmobs.silver_key");

    public SilverKeyAccessScreen(PlayerEntity playerIn, ITextComponent title, IKeyAccessTile lockable, String currentCode, String uses) {
        super(playerIn, title, lockable, currentCode, uses);
    }
}