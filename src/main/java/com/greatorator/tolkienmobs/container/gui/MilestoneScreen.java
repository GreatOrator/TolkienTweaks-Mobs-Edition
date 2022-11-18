package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCGuiSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiScrollElement;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiSlideControl;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.*;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.utils.Utils;
import com.greatorator.tolkienmobs.entity.tile.MilestoneTile;
import com.greatorator.tolkienmobs.handler.MilestoneHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class MilestoneScreen extends ModularGuiContainer<ContainerBCTile<MilestoneTile>> {
    protected GuiToolkit<MilestoneScreen> toolkit = new GuiToolkit<>(this, 200, 150).setTranslationPrefix("screen.tolkienmobs.milestone");
    private final Player player;
    private final MilestoneTile tile;

    public MilestoneScreen(ContainerBCTile<MilestoneTile> container, Inventory playerInventory, Component titleIn) {
        super(container, playerInventory, titleIn);
        this.tile = container.tile;
        this.player = playerInventory.player;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase temp = new TGuiBase(this);
        temp.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCGuiSprites.getThemed("background_dynamic"));
        temp.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(temp);

        GuiLabel nameLabel = toolkit.createHeading(ChatFormatting.DARK_BLUE + tile.milestoneName.get(), temp.background)
                .setAlignment(GuiAlign.CENTER)
                .setSize(temp.background.xSize() - 10, 8)
                .setShadowStateSupplier(() -> BCConfig.darkMode)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setEnabledCallback(() -> !player.isCreative());
        toolkit.placeOutside(nameLabel, temp.title, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 2);

        GuiTextField nameField = toolkit.createTextField(temp.background)
                .setValue(tile.milestoneName.get())
                .onValueChanged(tile.milestoneName::set)
                .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                .setSize(temp.background.xSize() - 10, 10)
                .setEnabledCallback(player::isCreative);
        toolkit.placeOutside(nameField, temp.title, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 2);

        GuiSlideControl scrollBar = toolkit.createVanillaScrollBar()
                .setPos(nameField.maxXPos()-10, nameField.maxYPos() + (player.isCreative() ? 30 : 2))
                .setMaxYPos(temp.background.maxYPos() - 5, true)
                .setXSize(10);

        GuiElement<?> listBG = temp.background.addChild(new GuiBorderedRect())
                .setPos(nameField.xPos(), scrollBar.yPos())
                .setYSize(scrollBar.ySize())
                .setMaxXPos(scrollBar.xPos() - 1, true)
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D);

        GuiScrollElement listElement = new GuiScrollElement().setListMode(GuiScrollElement.ListMode.VERT_LOCK_POS_WIDTH)
                .setListSpacing(1)
                .setInsets(1, 1, 2, 1);
        listBG.addChild(listElement)
                .setPos(listBG)
                .setSize(listBG.getInsetRect()).bindSize(listBG, true)
                .setVerticalScrollBar(scrollBar)
                .setStandardScrollBehavior();

        for (MilestoneHandler.MilestoneData data : MilestoneHandler.getKnownByPlayer(player)) {
            if (data.getUuid().equals(tile.getUUID())) continue;
            GuiElement<?> container = new GuiElement<>()
                    .setYSize(18);
            GuiButton button = toolkit.createButton_old(data.getName(), container)
                    .setYSize(container.ySize())
                    .setXSize(listElement.getInsetRect().width - (data.getPaymentItem() == Items.AIR ? 0 : 18))
                    .onPressed(() -> tile.sendPacketToServer(output -> output.writeUUID(data.getUuid()), 3));

            if (data.getPaymentItem() == Items.AIR) {
                listElement.addElement(container);
                continue;
            }

            container.addChild(new GuiStackIcon())
                    .setStack(tile.getTravelCost(data))
                    .addSlotBackground()
                    .setYSize(container.ySize())
                    .setXSize(18)
                    .setXPos(button.maxXPos());

            listElement.addElement(container);
        }

        GuiButton setPayment = toolkit.createButton_old("Set held item as payment", temp.background)
                .setHoverText("Set the payment item required to travel to this milestone")
                .setSize(nameField.xSize(), 12)
                .setPos(nameField.xPos(), nameField.maxYPos() + 2)
                .onPressed(() -> tile.sendPacketToServer(output -> {}, 0))
                .setEnabledCallback(player::isCreative);

        GuiTextField distPer = toolkit.createTextField(temp.background)
                .setValue(tile.distanceCost.get() == 0 ? "" : tile.distanceCost.get()+"")
                .onValueChanged(s -> tile.sendPacketToServer(output -> output.writeVarInt(Utils.parseInt(s)), 1))
                .setHoverText("Distance per payment item in blocks (cost will be distance divided by this number)")
                .setSize(95, 12)
                .setPos(setPayment.xPos(), setPayment.maxYPos() + 2)
                .setFilter(toolkit.catchyValidator(s -> s.equals("") || Utils.validInteger(s)))
                .setEnabledCallback(player::isCreative);
        distPer.setSuggestion("Distance per item");

        GuiTextField dimCost = toolkit.createTextField(temp.background)
                .setValue(tile.dimensionCost.get() == 0 ? "" : tile.dimensionCost.get()+"")
                .onValueChanged(s -> tile.sendPacketToServer(output -> output.writeVarInt(Utils.parseInt(s)), 2))
                .setHoverText("Cost to travel to this milestone from another dimension")
                .setSize(95, 12)
                .setPos(distPer.maxXPos(), distPer.yPos())
                .setFilter(toolkit.catchyValidator(s -> s.equals("") || Utils.validInteger(s)))
                .setEnabledCallback(player::isCreative);
        dimCost.setSuggestion("X-Dimension Cost");

    }
}
