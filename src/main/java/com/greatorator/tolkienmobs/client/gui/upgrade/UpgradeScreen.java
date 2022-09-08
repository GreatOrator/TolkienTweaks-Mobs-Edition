package com.greatorator.tolkienmobs.client.gui.upgrade;

import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.greatorator.tolkienmobs.container.UpgradeContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class UpgradeScreen extends ModularGuiContainer<UpgradeContainer> {
    public UpgradeScreen(UpgradeContainer container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
    }

    @Override
    public void addElements(GuiElementManager manager) {

    }
}
