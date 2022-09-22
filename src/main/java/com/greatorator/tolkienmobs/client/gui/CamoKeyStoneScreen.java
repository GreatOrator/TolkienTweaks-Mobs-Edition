package com.greatorator.tolkienmobs.client.gui;

import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.client.TTMSprites;
import com.greatorator.tolkienmobs.entity.tile.CamoKeyStoneTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class CamoKeyStoneScreen extends ModularGuiContainer<ContainerBCTile<CamoKeyStoneTile>> {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    private boolean keymode;
    protected GuiToolkit<CamoKeyStoneScreen> toolkit = new GuiToolkit<>(this, 200, (keymode ? 70 : 128)).setTranslationPrefix("screen.tolkienmobs.keystone");
    private static final Pattern invalidCharacters = Pattern.compile("[^a-zA-Z-_\\d:]");

    private final PlayerEntity player;
    private final CamoKeyStoneTile tile;
    private static boolean keepKey = false;
    private static boolean rsAlways = true;
    private static boolean rsDelay = false;
    private static boolean rsPulse = false;

    public CamoKeyStoneScreen(ContainerBCTile<CamoKeyStoneTile> container, PlayerInventory playerInventory, ITextComponent titleIn) {
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
            // Key Code
            GuiBorderedRect codeBG = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(188, 12)
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
                    .setFieldEnabled(true)
                    .setText(tile.keyCode.get())
                    .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("instructions"))
                    .setValidator(toolkit.catchyValidator(s -> s.equals("") || !invalidCharacters.matcher(s).find()))
                    .setPos(codeBG.xPos() + 2, codeBG.maxYPos() - 11)
                    .setSize(186, 10);
            GuiButton saveCode = codeBG.addChild(toolkit.createButton(toolkit.i18n("saved"), template.background).setAlignment(GuiAlign.CENTER))
                    .setPos(codeBG.xPos() + 55, codeBG.maxYPos() + 2)
                    .setSize(78, 12)
                    .onPressed(() -> tile.keyCode.set(KeyStoneCode.getText()));

            // Tick Delay
            GuiBorderedRect delayBG = new GuiBorderedRect();
            toolkit.placeInside(delayBG, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 4, -6);
            delayBG.set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setPos(-9000, -9000)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(78, 12)
                    .setEnabledCallback(() -> !rsPulse);
            GuiLabel delayTitle = delayBG.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("delaytitle"))
                    .setPos(delayBG.xPos() + 5, delayBG.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(delayBG.maxXPos() - 1, true)
                    .setEnabledCallback(() -> !rsPulse);
            GuiTextField delayCode = delayBG.addChild(toolkit.createTextField(template.background))
                    .setFieldEnabled(true)
                    .setText(String.valueOf(tile.tickDelay.get()))
                    .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("tickdelay"))
                    .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                    .setPos(delayBG.xPos() + 2, delayBG.maxYPos() - 11)
                    .setSize(76, 10)
                    .setEnabledCallback(() -> !rsPulse);
            GuiButton saveTick = delayBG.addChild(toolkit.createButton(toolkit.i18n("savedelay"), template.background).setAlignment(GuiAlign.CENTER))
                    .setPos(-9000, -9000)
                    .setSize(78, 12)
                    .onPressed(() -> tile.tickDelay.set(Long.parseLong(delayCode.getText())))
                    .setEnabledCallback(() -> !rsPulse);

            // Key and Redstone Modes
            GuiButton keyButton = toolkit.createIconButton(template.background, 18, 18, () -> keepKey ? TTMSprites.get("keys/key_consume") : TTMSprites.get("keys/key_keep"));
                toolkit.placeInside(keyButton, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 60, -42);
                keyButton.setHoverText(e -> keepKey ? toolkit.i18n("key.consume") : toolkit.i18n("key.keep"));
                keyButton.onPressed(() -> {
                    tile.keyConsume.set(keepKey = !keepKey);

                    LOGGER.info("Consume Key: " + tile.keyConsume.get());
                    LOGGER.info("Redstone Always: " + tile.rsAlways.get());
                    LOGGER.info("Redstone Delay: " + tile.rsDelay.get());
                    LOGGER.info("Redstone Pulse: " + tile.rsPulse.get());

                    tile.sendPacketToServer(mcDataOutput -> {
                    }, 0);
                });
            GuiButton redstoneDelay = toolkit.createIconButton(template.background, 18, 18, () -> rsDelay ? TTMSprites.get("keys/redstone_delay_active") : TTMSprites.get("keys/redstone_delay"));
            toolkit.placeInside(redstoneDelay, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 100, -42);
            redstoneDelay.setHoverText(e -> rsDelay ? toolkit.i18n("redstone.delay.active") : toolkit.i18n("redstone.delay"));
            redstoneDelay.onPressed(() -> {
                rsAlways = false;
                rsPulse = false;
                tile.rsPulse.set(false);
                tile.rsAlways.set(false);
                tile.rsDelay.set(rsDelay = !rsDelay);

                    delayBG.setPos(template.background.maxXPos() + bgPad + 1, delayBG.maxYPos() - 21);
                    toolkit.placeOutside(delayBG, redstoneDelay, GuiToolkit.LayoutPos.TOP_CENTER, -8, 42);

                    saveTick.setPos(delayBG.xPos() + 5, delayBG.maxYPos() + 2);
                    toolkit.placeOutside(saveTick, redstoneDelay, GuiToolkit.LayoutPos.TOP_CENTER, -8, 55);

                tile.sendPacketToServer(mcDataOutput -> {
                }, 2);
            });
            GuiButton redstoneAlways = toolkit.createIconButton(template.background, 18, 18, () -> rsAlways ? TTMSprites.get("keys/redstone_always_active") : TTMSprites.get("keys/redstone_always"));
                toolkit.placeInside(redstoneAlways, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 80, -42);
                redstoneAlways.setHoverText(e -> rsAlways ? toolkit.i18n("redstone.always.active") : toolkit.i18n("redstone.always"));
                redstoneAlways.onPressed(() -> {
                        rsPulse = false;
                        rsDelay = false;
                    tile.rsPulse.set(false);
                    tile.rsDelay.set(false);
                    tile.rsAlways.set(rsAlways = !rsAlways);

                        delayBG.setPos(-9000, -9000);
                        saveTick.setPos(-9000, -9000);

                    tile.sendPacketToServer(mcDataOutput -> {
                    }, 1);
                });
            GuiButton redstonePulse = toolkit.createIconButton(template.background, 18, 18, () -> rsPulse ? TTMSprites.get("keys/redstone_pulse_active") : TTMSprites.get("keys/redstone_pulse"));
                toolkit.placeInside(redstonePulse, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 120, -42);
                redstonePulse.setHoverText(e -> rsPulse ? toolkit.i18n("redstone.pulse.active") : toolkit.i18n("redstone.pulse"));
                redstonePulse.onPressed(() -> {
                        rsAlways = false;
                        rsDelay = false;
                    tile.rsPulse.set(false);
                    tile.rsDelay.set(false);
                    tile.rsPulse.set(rsPulse = !rsPulse);

                        delayBG.setPos(-9000, -9000);
                        saveTick.setPos(-9000, -9000);

                    tile.sendPacketToServer(mcDataOutput -> {
                    }, 3);
                });
        }
    }
}
