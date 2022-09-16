package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiScreen;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

public class BronzeKeyAccessScreen extends ModularGuiScreen {
    protected GuiToolkit<BronzeKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 150).setTranslationPrefix("screen.tolkienmobs.bronze_key");
    private final PlayerEntity player;

    public BronzeKeyAccessScreen(ITextComponent titleIn, PlayerEntity playerIn) {
        super(titleIn);
        this.player = playerIn;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase temp = new TGuiBase(this);
        temp.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        temp.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(temp);
        int bgPad = 5;

    }
}
