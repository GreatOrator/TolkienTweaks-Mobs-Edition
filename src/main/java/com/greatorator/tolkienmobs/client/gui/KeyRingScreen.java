package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.SlotMover;
import com.greatorator.tolkienmobs.container.KeyRingContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class KeyRingScreen extends ModularGuiContainer<KeyRingContainer> {
    private final PlayerEntity player;

    protected GuiToolkit<KeyRingScreen> toolkit = new GuiToolkit<>(this, 176, 166).setTranslationPrefix("gui.tolkienmobs.key_ring");

    public KeyRingScreen(KeyRingContainer container, PlayerInventory playerInv, ITextComponent titleIn) {
        super(container, playerInv, titleIn);
        this.player = playerInv.player;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase template = new TGuiBase(this);
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(template);
        template.playerSlots = toolkit.createPlayerSlotsManualMovers(template.background, false, index -> new SlotMover(container.slots.get(index)));
        toolkit.placeInside(template.playerSlots, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -5, -5);

        // ### Main Coin Pouch Inventory ###
        int slotColumns = 9;
        int slotRows = 2;
        int slotsCount = slotColumns * slotRows;
        GuiElement<?> mainSlots = toolkit.createSlots(template.background, slotColumns, slotRows, 0, (x, y) -> new SlotMover(container.mainSlots.get(x + (y * slotColumns))), null);
        toolkit.placeInside(mainSlots, template.background, GuiToolkit.LayoutPos.TOP_RIGHT, -5, 16);

        // ### Update title position ###
        template.title.setAlignment(GuiAlign.LEFT);
        template.title.setXPos(mainSlots.xPos());
        template.title.setMaxYPos(mainSlots.yPos() - 3, false);
    }

    @Override
    public void tick() {
        container.clientTick();
        super.tick();
    }
}
