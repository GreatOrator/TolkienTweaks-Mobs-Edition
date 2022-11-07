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
import com.brandon3055.brandonscore.lib.datamanager.ManagedByte;
import com.brandon3055.brandonscore.lib.datamanager.ManagedShort;
import com.greatorator.tolkienmobs.entity.tile.CamoSpawnerTile;
import com.greatorator.tolkienmobs.handler.registers.SpritesRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class CamoSpawnerScreen extends ModularGuiContainer<ContainerBCTile<CamoSpawnerTile>> {
    protected GuiToolkit<CamoSpawnerScreen> toolkit = new GuiToolkit<>(this, 185 + 50, 200).setTranslationPrefix("screen.tolkienmobs.camo_spawner");
    private final Player player;
    private final CamoSpawnerTile tile;
    private GuiScrollElement listElement;

    public CamoSpawnerScreen(ContainerBCTile<CamoSpawnerTile> container, Inventory playerInventory, Component titleIn) {
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

        // ### Update title position ###
        GuiLabel nameLabel = toolkit.createHeading(ChatFormatting.DARK_AQUA + toolkit.i18n("title"), temp.background)
                .setAlignment(GuiAlign.CENTER)
                .setSize(temp.background.xSize() - 10, 8)
                .setShadowStateSupplier(() -> BCConfig.darkMode)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setEnabledCallback(() -> !player.isCreative());
        toolkit.placeOutside(nameLabel, temp.title, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 2);

        // General Settings
        GuiLabel minDelayLabel = createLabel(temp.background, "minSpawnDelay");
        minDelayLabel.setPos(temp.background.xPos() + 6, temp.title.maxYPos() + 3);
        GuiTextField minDelay = createField(temp.background, "minDelayValue", tile.minSpawnDelay);
        toolkit.placeOutside(minDelay, minDelayLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        GuiLabel maxDelayLabel = createLabel(temp.background, "maxSpawnDelay");
        toolkit.placeOutside(maxDelayLabel, minDelay, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);
        GuiTextField maxDelay = createField(temp.background, "maxDelayValue", tile.maxSpawnDelay);
        toolkit.placeOutside(maxDelay, maxDelayLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        GuiLabel actRngeLabel = createLabel(temp.background, "activationRange");
        toolkit.placeOutside(actRngeLabel, maxDelay, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);
        GuiTextField actRnge = createField(temp.background, "actRngeValue", tile.activationRange);
        toolkit.placeOutside(actRnge, actRngeLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        GuiLabel spwnRngeLabel = createLabel(temp.background, "spawnRange");
        toolkit.placeOutside(spwnRngeLabel, actRnge, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);
        GuiTextField spwnRnge = createField(temp.background, "spwnRngeValue", tile.spawnRange);
        toolkit.placeOutside(spwnRnge, spwnRngeLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        GuiLabel spwnCountLabel = createLabel(temp.background, "spawnCount");
        toolkit.placeOutside(spwnCountLabel, spwnRnge, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);
        GuiTextField spwnCount = createField(temp.background, "spwnCountValue", tile.spawnCount);
        toolkit.placeOutside(spwnCount, spwnCountLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        GuiLabel maxClusterLabel = createLabel(temp.background, "maxCluster");
        toolkit.placeOutside(maxClusterLabel, spwnCount, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);
        GuiTextField maxCluster = createField(temp.background, "maxClusterValue", tile.maxCluster);
        toolkit.placeOutside(maxCluster, maxClusterLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        GuiLabel clustRngeLabel = createLabel(temp.background, "clusterRange");
        toolkit.placeOutside(clustRngeLabel, maxCluster, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);
        GuiTextField clustRnge = createField(temp.background, "clusterRangeValue", tile.clusterRange);
        toolkit.placeOutside(clustRnge, clustRngeLabel, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 3);

        // Entity List

        GuiSlideControl scrollBar = toolkit.createVanillaScrollBar()
                .setPos(temp.background.maxXPos() - 16, temp.title.maxYPos() + 5)
                .setMaxYPos(temp.background.maxYPos() - 26, true)
                .setXSize(10);

        GuiElement<?> listBG = temp.background.addChild(new GuiBorderedRect())
                .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                .setPos(minDelay.maxXPos() + 3, temp.title.maxYPos() + 5)
                .setMaxPos(scrollBar.xPos() - 1, scrollBar.maxYPos(), true);

        listElement = new GuiScrollElement().setListMode(GuiScrollElement.ListMode.VERT_LOCK_POS_WIDTH)
                .setListSpacing(1)
                .setInsets(1, 1, 2, 1);
        listBG.addChild(listElement)
                .setPos(listBG)
                .setSize(listBG.getInsetRect()).bindSize(listBG, true)
                .setVerticalScrollBar(scrollBar)
                .setStandardScrollBehavior();

        updateEntityList();

        // Other Settings

        GuiButton spawnerParticles = toolkit.createIconButton(temp.background, 16, 16, () -> tile.spawnerParticles.get() ? SpritesRegister.get("spawner/particles") : SpritesRegister.get("spawner/ignore_particles"))
                .setHoverText(e -> toolkit.i18n("spawnerParticles") + "\n" + (tile.spawnerParticles.get() ? "Enabled" : "Disabled"))
                .onPressed(tile.spawnerParticles::invert);
        toolkit.placeInside(spawnerParticles, temp.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -6, -6);

        GuiButton spawnRequirement = toolkit.createIconButton(temp.background, 16, 16, () -> tile.ignoreSpawnReq.get() ? SpritesRegister.get("spawner/spawn_requirements") : SpritesRegister.get("spawner/ignore_spawn_requirements"))
                .setHoverText(e -> toolkit.i18n("ignoreSpawnReq") + "\n" + (tile.ignoreSpawnReq.get() ? "Not Ignored" : "Ignored"))
                .onPressed(tile.ignoreSpawnReq::invert);
        toolkit.placeOutside(spawnRequirement, spawnerParticles, GuiToolkit.LayoutPos.MIDDLE_LEFT, -10, 0);

        GuiButton ignorePlayer = toolkit.createIconButton(temp.background, 16, 16, () -> tile.requirePlayer.get() ? SpritesRegister.get("spawner/player") : SpritesRegister.get("spawner/ignore_player"))
                .setHoverText(e -> toolkit.i18n("requirePlayer") + "\n" + (tile.requirePlayer.get() ? "Required" : "Not Required"))
                .onPressed(tile.requirePlayer::invert);
        toolkit.placeOutside(ignorePlayer, spawnRequirement, GuiToolkit.LayoutPos.MIDDLE_LEFT, -10, 0);
    }

    private void updateEntityList() {
        listElement.clearElements();
        listElement.resetScrollPositions();
        for (CompoundTag tag : tile.entityTags) {
            GuiElement<?> container = new GuiElement<>()
                    .setYSize(32);

            String name = "minecraft:pig";
            if (tag.contains("id")) name = tag.getString("id");

            GuiEntityRenderer renderer = new GuiEntityRenderer()
                    .setEntity(new ResourceLocation(name))
                    .setSize(16, 16)
                    .setPos(16, 8)
                    .setHoverText(tag.toString());
            container.addChild(renderer);

            GuiButton delete = toolkit.createButton("Delete", container)
                    .setSize(50, 14)
                    .setPos(76, 8)
                    .onPressed(() -> tile.sendPacketToServer(output -> output.writeCompoundNBT(tag), 1));


            listElement.addElement(container);
        }
    }

    private GuiLabel createLabel(GuiElement<?> parent, String unlocalised) {
        return parent.addChild(new GuiLabel(toolkit.i18n(unlocalised))
                .setAlignment(GuiAlign.CENTER)
                .setShadowStateSupplier(() -> BCConfig.darkMode)
                .setTextColGetter(GuiToolkit.Palette.Slot::text)
                .setSize(80, 8));
    }

    private GuiTextField createField(GuiElement<?> parent, String unlocalised, ManagedByte field) {
        return toolkit.createTextField(parent)
                .setValue(String.valueOf(field.get()))
                .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n(unlocalised))
                .setFilter(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .onValueChanged(s -> field.set((byte) Integer.parseInt(s)))
                .setSize(80, 12);
    }

    private GuiTextField createField(GuiElement<?> parent, String unlocalised, ManagedShort field) {
        return toolkit.createTextField(parent)
                .setValue(String.valueOf(field.get()))
                .setHoverText(ChatFormatting.DARK_AQUA + toolkit.i18n(unlocalised))
                .setFilter(toolkit.catchyValidator(s -> s.equals("") || Long.parseLong(s) >= 0))
                .onValueChanged(s -> field.set((short) Integer.parseInt(s)))
                .setSize(80, 12);
    }

    private int lastListSize = -1;

    @Override
    public void containerTick() {
        super.containerTick();
        if (tile.entityTags.size() != lastListSize) {
            lastListSize = tile.entityTags.size();
            updateEntityList();
        }
    }
}
