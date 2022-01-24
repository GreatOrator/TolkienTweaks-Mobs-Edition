package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TBasicMachine;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.client.TTMSprites;
import com.greatorator.tolkienmobs.entity.tile.TTMPiggyBankTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import static com.brandon3055.brandonscore.client.gui.GuiToolkit.GuiLayout.DEFAULT;
import static com.brandon3055.brandonscore.client.gui.GuiToolkit.LayoutPos.TOP_LEFT;
import static com.brandon3055.brandonscore.inventory.ContainerSlotLayout.SlotType.TILE_INV;

public class GuiTTMPiggyBank extends ModularGuiContainer<ContainerBCTile<TTMPiggyBankTile>> {

    protected GuiToolkit<GuiTTMPiggyBank> toolkit = new GuiToolkit<>(this, DEFAULT).setTranslationPrefix("gui.tolkienmobs.piggybank");

    private TTMPiggyBankTile tile;

    public GuiTTMPiggyBank(ContainerBCTile<TTMPiggyBankTile> container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.tile = container.tile;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TBasicMachine template = toolkit.loadTemplate(new TBasicMachine(this, tile));

        GuiElement<?> inputSlots = toolkit.createSlots(template.background, 1, 1, 4, (x, y) -> container.getSlotLayout().getSlotData(TILE_INV, 0), TTMSprites.get("item_coin"));
        toolkit.placeInside(inputSlots, template.background, TOP_LEFT, 39, 16);
    }
}