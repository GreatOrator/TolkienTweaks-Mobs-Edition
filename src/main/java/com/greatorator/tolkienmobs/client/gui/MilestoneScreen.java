package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.regex.Pattern;

public class MilestoneScreen extends ModularGuiContainer<ContainerBCTile<MilestoneTile>> {
    protected GuiToolkit<MilestoneScreen> toolkit = new GuiToolkit<>(this, 200, 150).setTranslationPrefix("screen.tolkienmobs.milestone");
    private static final Pattern invalidCharacters = Pattern.compile("[^a-zA-Z-_\\d:]");
    private final PlayerEntity player;
    private final MilestoneTile tile;
    private String markerName;

    public MilestoneScreen(ContainerBCTile<MilestoneTile> container, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(container, playerInventory, titleIn);
        this.tile = container.tile;
        this.player = playerInventory.player;
    }


    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase temp = new TGuiBase(this);
        temp.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        temp.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(temp);
        int bgPad = 5;

        GuiBorderedRect codeBG = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(188, 12)
                .setXPos(temp.background.xPos() + bgPad + 1)
                .setMaxYPos(temp.background.maxYPos() - bgPad - 33, false);
//        if (player.isCreative()) {
            temp.background.addChild(codeBG);
        GuiLabel milestoneTitle = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("milestonename"))
                .setPos(codeBG.xPos() + 5, codeBG.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(codeBG.maxXPos() - 1, true);
        temp.background.addChild(codeBG);
            GuiLabel milestoneSaved = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("saved"))
                .setPos(codeBG.xPos() + 5, codeBG.maxYPos() + 2)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(codeBG.maxXPos() - 1, true);
            GuiTextField milestoneName = toolkit.createTextField(temp.background)
                    .setText(markerName)
                    .setFieldEnabled(true)
                    .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                    .setValidator(toolkit.catchyValidator(s -> s.equals("") || !invalidCharacters.matcher(s).find()))
                    .setPos(codeBG.xPos() + 2, codeBG.maxYPos() - 11)
                    .setSize(145, 10)
                    .setChangeListener(TolkienNetwork::sendMilestoneUpdate);
//        } else {
//            GuiLabel milestoneName = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
//                    .setLabelText(markerName)
//                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() - 11)
//                    .setYSize(8)
//                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
//                    .setMaxXPos(codeBG.maxXPos() - 1, true);
//        }
    }
}
