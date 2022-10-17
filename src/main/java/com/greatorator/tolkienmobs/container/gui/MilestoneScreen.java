package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCSprites;
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
import com.greatorator.tolkienmobs.handler.MilestoneSaveData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class MilestoneScreen extends ModularGuiContainer<ContainerBCTile<MilestoneTile>> {
    protected GuiToolkit<MilestoneScreen> toolkit = new GuiToolkit<>(this, 200, 150).setTranslationPrefix("screen.tolkienmobs.milestone");
    private final PlayerEntity player;
    private final MilestoneTile tile;

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

        GuiLabel nameLabel = toolkit.createHeading(TextFormatting.DARK_BLUE + tile.milestoneName.get(), temp.background)
                .setAlignment(GuiAlign.CENTER)
                .setSize(temp.background.xSize() - 10, 8)
                .setShadowStateSupplier(() -> BCConfig.darkMode)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setEnabledCallback(() -> !player.isCreative());
        toolkit.placeOutside(nameLabel, temp.title, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 2);

        GuiTextField nameField = toolkit.createTextField(temp.background)
                .setText(tile.milestoneName.get())
                .setChangeListener(tile.milestoneName::set)
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("instructions"))
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

        for (MilestoneSaveData.MilestoneData data : MilestoneSaveData.getKnownByPlayer(player)) {
            if (data.getUuid().equals(tile.getUUID())) continue;
            GuiElement<?> container = new GuiElement<>()
                    .setYSize(18);
            GuiButton button = toolkit.createButton(data.getName(), container)
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

        GuiButton setPayment = toolkit.createButton("Set held item as payment", temp.background)
                .setHoverText("Set the payment item required to travel to this milestone")
                .setSize(nameField.xSize(), 12)
                .setPos(nameField.xPos(), nameField.maxYPos() + 2)
                .onPressed(() -> tile.sendPacketToServer(output -> {}, 0))
                .setEnabledCallback(player::isCreative);

        GuiTextField distPer = toolkit.createTextField(temp.background)
                .setText(tile.distanceCost.get() == 0 ? "" : tile.distanceCost.get()+"")
                .setChangeListener(s -> tile.sendPacketToServer(output -> output.writeVarInt(Utils.parseInt(s)), 1))
                .setHoverText("Distance per payment item in blocks (cost will be distance divided by this number)")
                .setSize(95, 12)
                .setPos(setPayment.xPos(), setPayment.maxYPos() + 2)
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Utils.validInteger(s)))
                .setEnabledCallback(player::isCreative);
        distPer.setSuggestion("Distance per item");

        GuiTextField dimCost = toolkit.createTextField(temp.background)
                .setText(tile.dimensionCost.get() == 0 ? "" : tile.dimensionCost.get()+"")
                .setChangeListener(s -> tile.sendPacketToServer(output -> output.writeVarInt(Utils.parseInt(s)), 2))
                .setHoverText("Cost to travel to this milestone from another dimension")
                .setSize(95, 12)
                .setPos(distPer.maxXPos(), distPer.yPos())
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Utils.validInteger(s)))
                .setEnabledCallback(player::isCreative);
        dimCost.setSuggestion("X-Dimension Cost");

    }
}
