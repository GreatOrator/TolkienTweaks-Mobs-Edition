package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

public class MasterKeyAccessScreen extends KeyBaseAccessScreen {
    protected GuiToolkit<MasterKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 90).setTranslationPrefix("screen.tolkienmobs.master_key");

    public MasterKeyAccessScreen(PlayerEntity playerIn, ITextComponent title, IKeyBase lockable, String currentCode, int uses) {
        super(playerIn, title, lockable, currentCode, uses);
    }
}