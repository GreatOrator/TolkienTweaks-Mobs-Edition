package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCGuiSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.SlotMover;
import com.greatorator.tolkienmobs.container.LockableChestContainer;
import com.greatorator.tolkienmobs.entity.tile.LockableChestTile;
import com.greatorator.tolkienmobs.item.keys.KeyBaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.regex.Pattern;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableChestScreen extends ModularGuiContainer<LockableChestContainer> {
    protected GuiToolkit<LockableChestScreen> toolkit = new GuiToolkit<>(this, 171, 166).setTranslationPrefix("gui.tolkienmobs.lockable_chest");
    private static final Pattern invalidCharacters = Pattern.compile("[^a-zA-Z-_\\d:]");
    private LockableChestTile tile;
    private final Player player;

    public LockableChestScreen(LockableChestContainer container, Inventory inv, Component titleIn) {
        super(container, inv, titleIn);
        this.tile = container.tile;
        this.player = inv.player;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase template = new TGuiBase(this);
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCGuiSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(template);
        int bgPad = 5;
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (player.isCreative() && player.isCrouching()) {
            // Key Code
            GuiBorderedRect codeBG = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(158, 12)
                    .setXPos(template.background.xPos() + bgPad + 1)
                    .setMaxYPos(template.background.ySize() + bgPad + 100, false);
            template.background.addChild(codeBG);
            GuiLabel KeyStoneTitle = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("keystonecode"))
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG.maxXPos() - 1, true);
            template.background.addChild(codeBG);
            GuiTextField KeyStoneCode = toolkit.createTextField(template.background)
                    .setValue(tile.keyCode.get())
                    .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                    .setFilter(toolkit.catchyValidator(s -> s.equals("") || !invalidCharacters.matcher(s).find()))
                    .setPos(codeBG.xPos() + 2, codeBG.maxYPos() - 11)
                    .setSize(156, 10);
            GuiButton saveCode = codeBG.addChild(toolkit.createButton_old(toolkit.i18n("saved"), template.background).setAlignment(GuiAlign.CENTER))
                    .setPos(codeBG.xPos() + 40, codeBG.maxYPos() + 2)
                    .setSize(78, 12)
                    .onPressed(() -> tile.keyCode.set(KeyStoneCode.getValue()));

        } else if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getCode(stack).equals(tile.keyCode.get()))){
            template.playerSlots = toolkit.createPlayerSlotsManualMovers(template.background, false, index -> new SlotMover(container.slots.get(index)));
            toolkit.placeInside(template.playerSlots, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -5, -5);

            // ### Main Inventory ###
            int slotColumns = 9;
            int slotRows = 3;
            int slotsCount = slotColumns * slotRows;
            GuiElement<?> mainSlots = toolkit.createSlots(template.background, slotColumns, slotRows, 0, (x, y) -> new SlotMover(container.mainSlots.get(x + (y * slotColumns))), null);
            toolkit.placeInside(mainSlots, template.background, GuiToolkit.LayoutPos.TOP_RIGHT, -5, 16);

            // ### Update title position ###
            template.title.setAlignment(GuiAlign.LEFT);
            template.title.setXPos(mainSlots.xPos());
            template.title.setMaxYPos(mainSlots.yPos() - 3, false);
        } else {
            player.sendMessage(new TranslatableComponent(MODID + ".msg.wrong_key").withStyle(ChatFormatting.RED), Util.NIL_UUID);
        }
    }
}