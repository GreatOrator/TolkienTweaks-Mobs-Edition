package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCGuiSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiScreen;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiEvent;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.IGuiEventListener;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyBase;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.regex.Pattern;

public class KeyBaseAccessScreen extends ModularGuiScreen implements IGuiEventListener {
    protected GuiToolkit<KeyBaseAccessScreen> toolkit = new GuiToolkit<>(this, 200, 90).setTranslationPrefix("screen.tolkienmobs.base_key");
    private static Pattern invalidCharacters = Pattern.compile("[^a-zA-Z-_\\d:]");

    private final Player player;
    private final String currentCode;
    private final int uses;

    public KeyBaseAccessScreen(Player playerIn, Component title, IKeyBase lockable, String currentCode, int uses) {
        super(title);
        this.player = playerIn;
        this.currentCode = currentCode;
        this.uses = uses;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase temp = new TGuiBase(this);
        temp.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCGuiSprites.getThemed("background_dynamic"));
        temp.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(temp);
        int bgPad = 5;

        if (player.isCreative()) {
            GuiBorderedRect codeBG = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(150, 12)
                    .setXPos(temp.background.xPos() + bgPad + 1)
                    .setMaxYPos(temp.background.maxYPos() - bgPad - 50, false);
            temp.background.addChild(codeBG);
            GuiLabel keyTitle = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("keycode"))
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG.maxXPos() - 1, true);
            temp.background.addChild(codeBG);
            GuiLabel keySaved = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("saved"))
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() + 2)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG.maxXPos() - 1, true);
            GuiTextField keyCode = toolkit.createTextField(temp.background)
                    .setValue(currentCode)
                    .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                    .setFilter(toolkit.catchyValidator(s -> s.equals("") || !invalidCharacters.matcher(s).find()))
                    .setPos(codeBG.xPos() + 2, codeBG.maxYPos() - 11)
                    .setSize(145,10)
                    .onValueChanged(TolkienNetwork::sendKeyCodeUpdate);
            GuiBorderedRect codeBG2 = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(150, 12)
                    .setXPos(temp.background.xPos() + bgPad + 1)
                    .setMaxYPos(temp.background.maxYPos() - bgPad - 10, false);
            temp.background.addChild(codeBG2);
            GuiLabel keyTitle2 = codeBG2.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("keycode2"))
                    .setPos(codeBG2.xPos() + 5, codeBG2.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG2.maxXPos() - 1, true);
            temp.background.addChild(codeBG2);
            GuiLabel keySaved2 = codeBG2.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("saved2"))
                    .setPos(codeBG2.xPos() + 5, codeBG2.maxYPos() + 2)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG2.maxXPos() - 1, true);
            GuiTextField keyCode2 = toolkit.createTextField(temp.background)
                    .setValue(String.valueOf(uses))
                    .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n("instructions2"))
                    .setFilter(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                    .setPos(codeBG2.xPos() + 2, codeBG2.maxYPos() - 11)
                    .setSize(145,10)
                    .onValueChanged(TolkienNetwork::sendKeyUsesUpdate);
        }
    }

    @Override
    public void onMGuiEvent(GuiEvent event, GuiElement eventSource) {

    }
}
