package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.SlotMover;
import com.greatorator.tolkienmobs.container.CamoFluidContainer;
import com.greatorator.tolkienmobs.entity.tile.CamoFluidTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class CamoFluidScreen extends ModularGuiContainer<CamoFluidContainer> {
    protected GuiToolkit<CamoFluidScreen> toolkit = new GuiToolkit<>(this, 171, 166).setTranslationPrefix("gui.tolkienmobs.camo_fluid");
    private CamoFluidTile tile;

    public CamoFluidScreen(CamoFluidContainer container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
        this.tile = container.tile;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase template = new TGuiBase(this);
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(template);

        template.playerSlots = toolkit.createPlayerSlotsManualMovers(template.background, false, index -> new SlotMover(container.slots.get(index)));
        toolkit.placeInside(template.playerSlots, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -5, -5);

        // ### Main Inventory ###
        GuiElement<?> mainSlots = toolkit.createSlots(template.background, 1, 1, 0, (x, y) -> new SlotMover(container.mainSlots.get(x + y)), null);
        mainSlots.setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("fluid.instructions"));

        toolkit.placeInside(mainSlots, template.background, GuiToolkit.LayoutPos.TOP_CENTER, 0, 32);

        // ### Update title position ###
        template.title.setAlignment(GuiAlign.CENTER);
        template.title.setXPos(template.background.xPos() - 10);
        template.title.setMaxYPos(template.background.yPos() + 15, false);
    }
}
