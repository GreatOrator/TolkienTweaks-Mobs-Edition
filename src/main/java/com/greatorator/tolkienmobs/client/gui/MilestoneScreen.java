package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiScrollElement;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiSlideControl;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Objects;
import java.util.regex.Pattern;

public class MilestoneScreen extends ModularGuiContainer<ContainerBCTile<MilestoneTile>> {
    protected GuiToolkit<MilestoneScreen> toolkit = new GuiToolkit<>(this, 200, 150).setTranslationPrefix("screen.tolkienmobs.milestone");
    private static final Pattern invalidCharacters = Pattern.compile("[^a-zA-Z-_\\d:]");
    private final PlayerEntity player;
    private final MilestoneTile tile;

    public MilestoneScreen(ContainerBCTile<MilestoneTile> container, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(container, playerInventory, titleIn);
        this.tile = container.tile;
        this.player = playerInventory.player;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase template = new TGuiBase(this);
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(template);
        int bgPad = 5;

        if (player.isCreative()) {
            GuiBorderedRect codeBG = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(188, 12)
                    .setXPos(template.background.xPos() + bgPad + 1)
                    .setMaxYPos(template.background.ySize() + bgPad + 60, false);
                template.background.addChild(codeBG);
            GuiLabel milestoneTitle = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("milestonename"))
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG.maxXPos() - 1, true);
            template.background.addChild(codeBG);
            GuiTextField milestoneName = toolkit.createTextField(template.background)
                    .setText(Objects.equals(tile.milestoneName.get(), "") ? "" : tile.milestoneName.get())
                    .setFieldEnabled(true)
                    .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                    .setValidator(toolkit.catchyValidator(s -> s.equals("") || !invalidCharacters.matcher(s).find()))
                    .setPos(codeBG.xPos() + 2, codeBG.maxYPos() - 11)
                    .setSize(186, 10);
            toolkit.createButton(toolkit.i18n("saved"), template.background).setAlignment(GuiAlign.CENTER)
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() + 2)
                    .setSize(25, 12)
                    .setMaxXPos(codeBG.maxXPos() - 1, true)
                    .onPressed(() -> tile.milestoneName.set(milestoneName.getText()));
        } else {
            GuiBorderedRect locList = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(188, 115)
                    .setXPos(template.background.xPos() + bgPad + 1)
                    .setMaxYPos(template.background.ySize() + bgPad + 167, false);
            template.background.addChild(locList);
            GuiLabel milestoneName = locList.addChild(new GuiLabel(TextFormatting.DARK_BLUE + tile.milestoneName.get().replace("_", " ")).setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setPos(locList.xPos() + 5, locList.yPos() - 11)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(locList.maxXPos() - 1, true);
            GuiButton teleportButton = locList.addChild(toolkit.createButton(tile.milestoneName.get().replace("_", " "), template.background).setAlignment(GuiAlign.CENTER))
                    .setPos(locList.xPos() + 7, locList.yPos() + 1)
                    .setSize(180, 12)
                    .onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
                    }, 0));
            GuiSlideControl scrollBar = toolkit.createVanillaScrollBar()
                    .setPos(template.background.xPos() + bgPad, locList.yPos())
                    .setMaxYPos(template.background.maxYPos() - bgPad - 2, true)
                    .setXSize(8);
            GuiScrollElement scrollElement = new GuiScrollElement().setListMode(GuiScrollElement.ListMode.VERT_LOCK_POS_WIDTH)
                    .setListSpacing(1)
                    .setInsets(1, 1, 1, 1);
            locList.addChild(scrollElement)
                    .setPos(locList)
                    .setSize(locList.getInsetRect()).bindSize(locList, true)
                    .setVerticalScrollBar(scrollBar)
                    .setStandardScrollBehavior();
        }
    }
}
