package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TBasicMachine;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.entity.tile.BaseBarrelTile;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import static com.brandon3055.brandonscore.client.gui.GuiToolkit.GuiLayout.WIDE;
import static com.brandon3055.brandonscore.client.gui.GuiToolkit.LayoutPos.TOP_LEFT;
import static com.brandon3055.brandonscore.inventory.ContainerSlotLayout.SlotType.TILE_INV;

public class BaseBarrelScreen extends ModularGuiContainer<ContainerBCTile<BaseBarrelTile>> {

    protected GuiToolkit<BaseBarrelScreen> toolkit = new GuiToolkit<>(this, WIDE).setTranslationPrefix("gui.tolkienmobs.barrel");

    private BaseBarrelTile tile;

    public BaseBarrelScreen(ContainerBCTile<BaseBarrelTile> container, Inventory inv, Component title) {
        super(container, inv, title);
        this.tile = container.tile;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TBasicMachine template = toolkit.loadTemplate(new TBasicMachine(this, tile));

        GuiElement<?> inputSlots = toolkit.createSlots(template.background, 9, 3, 1, (x, y) -> container.getSlotLayout().getSlotData(TILE_INV, (y * 9) + x), null);
        toolkit.placeInside(inputSlots, template.background, TOP_LEFT, 8, 16);
    }
}