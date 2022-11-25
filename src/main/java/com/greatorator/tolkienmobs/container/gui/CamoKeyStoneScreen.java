package com.greatorator.tolkienmobs.container.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCGuiSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.entity.tile.CamoKeyStoneTile;
import com.greatorator.tolkienmobs.handler.registers.SpritesRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class CamoKeyStoneScreen extends ModularGuiContainer<ContainerBCTile<CamoKeyStoneTile>> {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    protected GuiToolkit<CamoKeyStoneScreen> toolkit = new GuiToolkit<>(this, 200, 128).setTranslationPrefix("screen.tolkienmobs.keystone");
    private static final Pattern invalidCharacters = Pattern.compile("[^a-zA-Z-_\\d:]");

    private final Player player;
    private final CamoKeyStoneTile tile;
    private static boolean keepKey = false;
    private static boolean rsAlways = false;
    private static boolean rsDelay = false;
    private static boolean rsPulse = false;

    public CamoKeyStoneScreen(ContainerBCTile<CamoKeyStoneTile> container, Inventory playerInventory, Component titleIn) {
        super(container, playerInventory, titleIn);
        this.tile = container.tile;
        this.player = playerInventory.player;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase template = new TGuiBase(this);
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCGuiSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(template);
        int bgPad = 5;

        if (player.isCreative()) {
            // Key Code
            GuiTextField KeyStoneCode = toolkit.createTextField(template.background)
                    .setValue(tile.keyCode.get())
                    .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                    .setFilter(toolkit.catchyValidator(s -> s.equals("") || !invalidCharacters.matcher(s).find()))
                    .setPos(template.background.xPos() + bgPad + 1, template.background.ySize() + bgPad + 100)
                    .setSize(186, 10);
            template.background.addChild(KeyStoneCode);
            GuiLabel KeyStoneTitle = KeyStoneCode.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("keystonecode"))
                    .setPos(KeyStoneCode.xPos() + 5, KeyStoneCode.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(KeyStoneCode.maxXPos() - 1, true);
            template.background.addChild(KeyStoneCode);
            GuiButton saveCode = KeyStoneCode.addChild(toolkit.createButton_old(toolkit.i18n("saved"), template.background).setAlignment(GuiAlign.CENTER))
                    .setPos(KeyStoneCode.xPos() + 55, KeyStoneCode.maxYPos() + 2)
                    .setSize(78, 12)
                    .onPressed(() -> tile.keyCode.set(KeyStoneCode.getValue()));

            // Tick Delay
            GuiTextField delayCode = toolkit.createTextField(template.background)
                    .setValue(String.valueOf(tile.tickDelay.get()))
                    .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n("tickdelay"))
                    .setFilter(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                    .setPos(-9000, -9000)
                    .setSize(76, 10)
                    .setEnabledCallback(() -> !rsPulse);
            GuiLabel delayTitle = delayCode.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("delaytitle"))
                    .setPos(delayCode.xPos() + 5, delayCode.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(delayCode.maxXPos() - 1, true)
                    .setEnabledCallback(() -> !rsPulse);
            GuiButton saveTick = delayCode.addChild(toolkit.createButton_old(toolkit.i18n("savedelay"), template.background).setAlignment(GuiAlign.CENTER))
                    .setPos(-9000, -9000)
                    .setSize(78, 12)
                    .onPressed(() -> tile.tickDelay.set(Integer.parseInt((delayCode.getValue()))))
                    .setEnabledCallback(() -> !rsPulse);

            // Key and Redstone Modes
            GuiButton keyButton = toolkit.createIconButton(template.background, 18, 18, () -> tile.keyConsume.get() ? SpritesRegister.get("keys/key_consume") : SpritesRegister.get("keys/key_keep"));
                toolkit.placeInside(keyButton, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 60, -42);
                keyButton.setHoverText(e -> tile.keyConsume.get() ? toolkit.i18n("key.consume") : toolkit.i18n("key.keep"));
                keyButton.onPressed(() -> {
                    tile.keyConsume.set(keepKey = !keepKey);
                    tile.sendPacketToServer(mcDataOutput -> {
                    }, 0);
                });
            GuiButton redstoneDelay = toolkit.createIconButton(template.background, 18, 18, () -> tile.rsDelay.get() ? SpritesRegister.get("keys/redstone_delay_active") : SpritesRegister.get("keys/redstone_delay"));
            toolkit.placeInside(redstoneDelay, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 100, -42);
            redstoneDelay.setHoverText(e -> tile.rsDelay.get() ? toolkit.i18n("redstone.delay.active") : toolkit.i18n("redstone.delay"));
            redstoneDelay.onPressed(() -> {
                rsAlways = false;
                rsPulse = false;
                tile.rsAlways.set(false);
                tile.rsPulse.set(false);
                tile.rsDelay.set(rsDelay = !rsDelay);

                delayCode.setPos(template.background.maxXPos() + bgPad + 1, delayCode.maxYPos() - 21);
                    toolkit.placeOutside(delayCode, redstoneDelay, GuiToolkit.LayoutPos.TOP_CENTER, -8, 42);

                    saveTick.setPos(delayCode.xPos() + 5, delayCode.maxYPos() + 2);
                    toolkit.placeOutside(saveTick, redstoneDelay, GuiToolkit.LayoutPos.TOP_CENTER, -8, 55);

                tile.sendPacketToServer(mcDataOutput -> {
                }, 2);
            });
            GuiButton redstoneAlways = toolkit.createIconButton(template.background, 18, 18, () -> tile.rsAlways.get() ? SpritesRegister.get("keys/redstone_always_active") : SpritesRegister.get("keys/redstone_always"));
                toolkit.placeInside(redstoneAlways, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 80, -42);
                redstoneAlways.setHoverText(e -> tile.rsAlways.get() ? toolkit.i18n("redstone.always.active") : toolkit.i18n("redstone.always"));
                redstoneAlways.onPressed(() -> {
                        keepKey = false;
                        rsPulse = false;
                        rsDelay = false;
                    tile.keyConsume.set(false);
                    tile.rsPulse.set(false);
                    tile.rsDelay.set(false);
                    tile.rsAlways.set(rsAlways = !rsAlways);

                    delayCode.setPos(-9000, -9000);
                        saveTick.setPos(-9000, -9000);

                    tile.sendPacketToServer(mcDataOutput -> {
                    }, 1);
                });
            GuiButton redstonePulse = toolkit.createIconButton(template.background, 18, 18, () -> tile.rsPulse.get() ? SpritesRegister.get("keys/redstone_pulse_active") : SpritesRegister.get("keys/redstone_pulse"));
                toolkit.placeInside(redstonePulse, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 120, -42);
                redstonePulse.setHoverText(e -> tile.rsPulse.get() ? toolkit.i18n("redstone.pulse.active") : toolkit.i18n("redstone.pulse"));
                redstonePulse.onPressed(() -> {
                        rsAlways = false;
                        rsDelay = false;
                    tile.rsAlways.set(false);
                    tile.rsDelay.set(false);
                    tile.rsPulse.set(rsPulse = !rsPulse);

                    delayCode.setPos(-9000, -9000);
                        saveTick.setPos(-9000, -9000);

                    tile.sendPacketToServer(mcDataOutput -> {
                    }, 3);
                });
        }
    }
}
