package com.greatorator.tolkienmobs.container.gui;

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
import com.greatorator.tolkienmobs.entity.tile.CamoSpawnerTile;
import com.greatorator.tolkienmobs.handler.TTMSprites;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class CamoSpawnerScreen extends ModularGuiContainer<ContainerBCTile<CamoSpawnerTile>> {
    protected GuiToolkit<CamoSpawnerScreen> toolkit = new GuiToolkit<>(this, 185, 185).setTranslationPrefix("screen.tolkienmobs.camo_spawner");
    private final PlayerEntity player;
    private final CamoSpawnerTile tile;

    public CamoSpawnerScreen(ContainerBCTile<CamoSpawnerTile> container, PlayerInventory playerInventory, ITextComponent titleIn) {
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

        // ### Update title position ###
        GuiLabel nameLabel = toolkit.createHeading(TextFormatting.DARK_AQUA + toolkit.i18n("title"), template.background)
                .setAlignment(GuiAlign.CENTER)
                .setSize(template.background.xSize() - 10, 8)
                .setShadowStateSupplier(() -> BCConfig.darkMode)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setEnabledCallback(() -> !player.isCreative());
        toolkit.placeOutside(nameLabel, template.title, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 2);

        // General Settings
        GuiBorderedRect minDelay = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 1, template.background.yPos() + 30);
        GuiLabel minDelayTitle = minDelay.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("minSpawnDelay"))
                .setPos(minDelay.xPos(), minDelay.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(minDelay.maxXPos() - 1, true);
        template.background.addChild(minDelay);
        GuiTextField minDelayValue = minDelay.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.minSpawnDelay.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("minDelayValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(minDelay.xPos() + 2, minDelay.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton minDelaySave = minDelay.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(minDelay.xPos(), minDelay.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.minSpawnDelay.set((short) Integer.parseInt((minDelayValue.getText()))));
        GuiBorderedRect maxDelay = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 89, template.background.yPos() + 30);
        GuiLabel maxDelayTitle = maxDelay.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("maxSpawnDelay"))
                .setPos(maxDelay.xPos(), maxDelay.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(maxDelay.maxXPos() - 1, true);
        template.background.addChild(maxDelay);
        GuiTextField maxDelayValue = maxDelay.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.maxSpawnDelay.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("maxDelayValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(maxDelay.xPos() + 2, maxDelay.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton maxDelaySave = maxDelay.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(maxDelay.xPos(), maxDelay.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.maxSpawnDelay.set((short) Integer.parseInt((maxDelayValue.getText()))));
        GuiBorderedRect actRnge = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 1, template.background.yPos() + 70);
        GuiLabel actRngeTitle = actRnge.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("activationRange"))
                .setPos(actRnge.xPos(), actRnge.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(actRnge.maxXPos() - 1, true);
        template.background.addChild(actRnge);
        GuiTextField actRngeValue = actRnge.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.activationRange.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("actRngeValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(actRnge.xPos() + 2, actRnge.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton actRngeSave = actRnge.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(actRnge.xPos(), actRnge.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.activationRange.set((short) Integer.parseInt((actRngeValue.getText()))));
        GuiBorderedRect spwnRnge = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 89, template.background.yPos() + 70);
        GuiLabel spwnRngeTitle = spwnRnge.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("spawnRange"))
                .setPos(spwnRnge.xPos(), spwnRnge.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(spwnRnge.maxXPos() - 1, true);
        template.background.addChild(spwnRnge);
        GuiTextField spwnRngeValue = spwnRnge.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.spawnRange.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("spwnRngeValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(spwnRnge.xPos() + 2, spwnRnge.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton spwnRngeSave = spwnRnge.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(spwnRnge.xPos(), spwnRnge.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.spawnRange.set((short) Integer.parseInt((spwnRngeValue.getText()))));
        GuiBorderedRect spwnCount = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 1, template.background.yPos() + 110);
        GuiLabel spwnCountTitle = spwnCount.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("spawnCount"))
                .setPos(spwnCount.xPos(), spwnCount.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(spwnCount.maxXPos() - 1, true);
        template.background.addChild(spwnCount);
        GuiTextField spwnCountValue = spwnCount.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.spawnCount.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("spwnCountValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(spwnCount.xPos() + 2, spwnCount.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton spwnCountSave = spwnCount.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(spwnCount.xPos(), spwnCount.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.spawnCount.set((byte) Integer.parseInt((spwnCountValue.getText()))));
        GuiBorderedRect maxCluster = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 89, template.background.yPos() + 110);
        GuiLabel maxClusterTitle = maxCluster.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("maxCluster"))
                .setPos(maxCluster.xPos(), maxCluster.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(maxCluster.maxXPos() - 1, true);
        template.background.addChild(maxCluster);
        GuiTextField maxClusterValue = maxCluster.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.maxCluster.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("maxClusterValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(maxCluster.xPos() + 2, maxCluster.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton maxClusterSave = maxCluster.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(maxCluster.xPos(), maxCluster.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.maxCluster.set((short) Integer.parseInt((maxClusterValue.getText()))));
        GuiBorderedRect clustRnge = new GuiBorderedRect()
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setSize(84, 12)
                .setPos(template.background.xPos() + bgPad + 1, template.background.yPos() + 150);
        GuiLabel clustRngeTitle = clustRnge.addChild(new GuiLabel().setAlignment(GuiAlign.CENTER).setShadowStateSupplier(() -> BCConfig.darkMode))
                .setDisplaySupplier(() -> toolkit.i18n("clusterRange"))
                .setPos(clustRnge.xPos(), clustRnge.maxYPos() - 21)
                .setYSize(8)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setMaxXPos(clustRnge.maxXPos() - 1, true);
        template.background.addChild(clustRnge);
        GuiTextField clustRngeValue = clustRnge.addChild(toolkit.createTextField(template.background))
                .setFieldEnabled(true)
                .setText(String.valueOf(tile.clusterRange.get()))
                .setHoverText(TextFormatting.DARK_AQUA + toolkit.i18n("clusterRangeValue"))
                .setValidator(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .setPos(clustRnge.xPos() + 2, clustRnge.maxYPos() - 11)
                .setSize(82, 10);
        GuiButton clustRngeSave = clustRnge.addChild(toolkit.createButton(toolkit.i18n("saveValue"), template.background).setAlignment(GuiAlign.CENTER))
                .setPos(clustRnge.xPos(), clustRnge.maxYPos() + 2)
                .setSize(84, 12)
                .onPressed(() -> tile.clusterRange.set((short) Integer.parseInt((clustRngeValue.getText()))));

        GuiButton ignorePlayer = toolkit.createIconButton(template.background, 16, 16, () -> tile.requirePlayer.get() ? TTMSprites.get("spawner/player") : TTMSprites.get("spawner/ignore_player"));
        toolkit.placeInside(ignorePlayer, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -71, -26);
        ignorePlayer.setHoverText(e -> toolkit.i18n("requirePlayer"));
        ignorePlayer.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
        }, 0));

        GuiButton spawnRequirement = toolkit.createIconButton(template.background, 16, 16, () -> tile.ignoreSpawnReq.get() ? TTMSprites.get("spawner/spawn_requirements") : TTMSprites.get("spawner/ignore_spawn_requirements"));
        toolkit.placeInside(spawnRequirement, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -41, -26);
        spawnRequirement.setHoverText(e -> toolkit.i18n("ignoreSpawnReq"));
        spawnRequirement.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
        }, 1));

        GuiButton spawnerParticles = toolkit.createIconButton(template.background, 16, 16, () -> tile.spawnerParticles.get() ? TTMSprites.get("spawner/particles") : TTMSprites.get("spawner/ignore_particles"));
        toolkit.placeInside(spawnerParticles, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -11, -26);
        spawnerParticles.setHoverText(e -> toolkit.i18n("spawnerParticles"));
        spawnerParticles.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
        }, 2));

        // Entity List
        GuiButton listButton = toolkit.createButton(toolkit.i18n("entityTags"), template.background).setAlignment(GuiAlign.CENTER);
        toolkit.placeInside(listButton, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, 9, -1);
        listButton.setSize(84, 12);
//        listButton.setListener(() -> openEntitySelector(container)); container.addChild(listButton);
        spawnerParticles.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
        }, 3));
    }
}
