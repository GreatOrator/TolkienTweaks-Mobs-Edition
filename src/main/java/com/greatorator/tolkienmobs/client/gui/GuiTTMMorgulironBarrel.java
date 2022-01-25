package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TBasicMachine;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.entity.tile.TTMMorgulironBarrelTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import static com.brandon3055.brandonscore.client.gui.GuiToolkit.GuiLayout.WIDE_EXTRA_TALL;
import static com.brandon3055.brandonscore.client.gui.GuiToolkit.LayoutPos.TOP_LEFT;
import static com.brandon3055.brandonscore.inventory.ContainerSlotLayout.SlotType.TILE_INV;

public class GuiTTMMorgulironBarrel extends ModularGuiContainer<ContainerBCTile<TTMMorgulironBarrelTile>> {

    protected GuiToolkit<GuiTTMMorgulironBarrel> toolkit = new GuiToolkit<>(this, WIDE_EXTRA_TALL).setTranslationPrefix("gui.tolkienmobs.barrel_morguliron");

    private TTMMorgulironBarrelTile tile;

    public GuiTTMMorgulironBarrel(ContainerBCTile<TTMMorgulironBarrelTile> container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.tile = container.tile;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TBasicMachine template = toolkit.loadTemplate(new TBasicMachine(this, tile));

        GuiElement<?> inputSlots = toolkit.createSlots(template.background, 9, 6, 1, (x, y) -> container.getSlotLayout().getSlotData(TILE_INV, (y * 9) + x), null);
        toolkit.placeInside(inputSlots, template.background, TOP_LEFT, 8, 16);
    }
}