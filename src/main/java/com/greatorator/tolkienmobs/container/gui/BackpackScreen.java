package com.greatorator.tolkienmobs.container.gui;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.RenderUtils;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.Vertex5;
import com.brandon3055.brandonscore.client.BCGuiSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.SlotMover;
import com.brandon3055.brandonscore.utils.MathUtils;
import com.brandon3055.brandonscore.utils.Utils;
import com.greatorator.tolkienmobs.container.BackpackContainer;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import com.greatorator.tolkienmobs.handler.registers.SpritesRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.Material;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static net.minecraft.ChatFormatting.DARK_AQUA;
import static net.minecraft.ChatFormatting.GRAY;

/**
 * Overhauled by brandon3055 on 07/09/2022
 */
public class BackpackScreen extends ModularGuiContainer<BackpackContainer> {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public static RenderType FLUID_RENDER_TYPE = RenderUtils.getFluidRenderType();
    protected GuiToolkit<BackpackScreen> toolkit = new GuiToolkit<>(this, 257, 207).setTranslationPrefix("gui.tolkienmobs.backpack");
    private final BackpackTile tile;
    private static boolean displayUpgrades = false;

    public BackpackScreen(BackpackContainer container, Inventory inv, Component titleIn) {
        super(container, inv, titleIn);
        this.tile = container.tile;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public void addElements(GuiElementManager manager) {
        // Get ourselves the basic gui template.
        TGuiBase template = new TGuiBase(this);
        // Do a little hack to switch the templates background to a custom dynamic background
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCGuiSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        //Load the template.
        toolkit.loadTemplate(template);

        // ### Add player inventory to gui
        // SlotMove is responsible for properly updating the position of the container slots.
        // Normally this is handled automagically by a ContainerSlotLayout (See class GuiLayoutFactories in DE) but as this is a little less simple than most this has to be done manually.
        template.playerSlots = toolkit.createPlayerSlotsManualMovers(template.background, false, index -> new SlotMover(container.slots.get(index)));
        //Place the player inventory in the bottom right corner of the gui with a -5,-5 offset
        toolkit.placeInside(template.playerSlots, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -5, -5);

        // ### Main Backpack Inventory ###
        int slotColumns = 9;
        int slotRows = 2;
        int slotsCount = slotColumns * slotRows;
        GuiElement<?> mainSlots = toolkit.createSlots(template.background, slotColumns, slotRows, 0, (x, y) -> new SlotMover(container.mainSlots.get(x + (y * slotColumns))), null);
        toolkit.placeInside(mainSlots, template.background, GuiToolkit.LayoutPos.TOP_RIGHT, -5, 16);
        mainSlots.setEnabledCallback(() -> !displayUpgrades);

        GuiElement<?> extraSlots1 = toolkit.createSlots(template.background, slotColumns, slotRows, 0, (x, y) -> new SlotMover(container.mainSlots.get(slotsCount + (x + (y * slotColumns)))), null);
        toolkit.placeOutside(extraSlots1, mainSlots, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 0);
        extraSlots1.setEnabledCallback(() -> tile.sizeUpgrade.get() > 0 && !displayUpgrades);

        GuiElement<?> extraSlots2 = toolkit.createSlots(template.background, slotColumns, slotRows, 0, (x, y) -> new SlotMover(container.mainSlots.get((slotsCount * 2) + (x + (y * slotColumns)))), null);
        toolkit.placeOutside(extraSlots2, extraSlots1, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 0);
        extraSlots2.setEnabledCallback(() -> tile.sizeUpgrade.get() > 1 && !displayUpgrades);

        // ### Main Upgrade Inventory
        int slotColumns1 = 5;
        int slotRows1 = 1;
        GuiElement<?> upgradeSlots = toolkit.createSlots(template.background, slotColumns1, slotRows1, 0, (x, y) -> new SlotMover(container.upgradeSlots.get(x + (y * slotColumns1))), null);
        toolkit.placeOutside(upgradeSlots, template.playerSlots, GuiToolkit.LayoutPos.TOP_CENTER, 0, -3);
        upgradeSlots.setEnabledCallback(() -> displayUpgrades);

        // ### Update title position ###
        //Set title alignment mode to left
        template.title.setAlignment(GuiAlign.LEFT);
        //Update the title position to be aligned with the left of the inventory.
        template.title.setXPos(mainSlots.xPos());
        //Set the bottom of the title to be 3 pixels above the inventory slots.
        template.title.setMaxYPos(mainSlots.yPos() - 3, false);

        // ### Add Armor and off-hand slots ###
        //The bellow commented code will work in the next release of BCore
//        GuiElement<?> armorSlots = toolkit.createSlots(template.background, 2, 2, 0, (x, y) -> new SlotMover(container.playerEquipment.get(3 - (y + (x * 2)))), (x, y) -> BCSprites.getArmorSlot(3 - (y + (x * 2))));
//        toolkit.placeOutside(armorSlots, template.playerSlots, GuiToolkit.LayoutPos.TOP_LEFT, -2, 18*2);
//        GuiElement<?> offhandSlot = toolkit.createSlots(template.background, 1, 1, 0, (x, y) -> new SlotMover(container.playerEquipment.get(4)), BCSprites.get("slots/armor_shield"));
//        toolkit.placeOutside(offhandSlot, armorSlots.playerSlots, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 0);

        //But for now we have to do each armor slot individually.
        //So we set the position of the head slot, Then position all other slots relative to that.
        GuiElement<?> headSlot = toolkit.createSlots(template.background, 1, 1, 0, (x, y) -> new SlotMover(container.playerEquipment.get(3)), BCGuiSprites.getArmorSlot(3));
        toolkit.placeOutside(headSlot, template.playerSlots, GuiToolkit.LayoutPos.TOP_LEFT, -20, 18);
        //Chest
        toolkit.createSlot(template.background, new SlotMover(container.playerEquipment.get(2)), () -> BCGuiSprites.getArmorSlot(2), false)
                .setRelPos(headSlot, 0, 18);
        //Legs
        toolkit.createSlot(template.background, new SlotMover(container.playerEquipment.get(1)), () -> BCGuiSprites.getArmorSlot(1), false)
                .setRelPos(headSlot, 18, 0);
        //Boots
        toolkit.createSlot(template.background, new SlotMover(container.playerEquipment.get(0)), () -> BCGuiSprites.getArmorSlot(0), false)
                .setRelPos(headSlot, 18, 18);
        //Off-Hand
        toolkit.createSlot(template.background, new SlotMover(container.playerEquipment.get(4)), () -> BCGuiSprites.get("slots/armor_shield"), false)
                .setRelPos(headSlot, 18 / 2, 18 * 2);

        // ### Crafting Slots ###
        GuiTexture craftTexture = template.background.addChild(new GuiTexture(imageWidth, imageHeight, 56, 56, SpritesRegister.get("backpack/crafting_table")));
        //Place the texture relative to the top left of the background.
        toolkit.placeInside(craftTexture, template.background, GuiToolkit.LayoutPos.TOP_LEFT, 4, 4);
        //Create the 3/3 grid of output slots
        GuiElement<?> craftInputSlots = createSlotsNoBG(template.background, 3, 3, 0, (x, y) -> new SlotMover(container.craftInputSlots.get(x + (y * 3))));
        //Align them with the crafting texture
        craftInputSlots.setRelPos(craftTexture, 1, 1);
        //Create the output slot
        GuiElement<?> craftOutputSlot = toolkit.createSlot(template.background, new SlotMover(container.craftResultSlot), null, false);
        //Place it relative to the bottom left of the crafting slots.
        toolkit.placeOutside(craftOutputSlot, craftInputSlots, GuiToolkit.LayoutPos.BOTTOM_LEFT, 18, 2);

        //Add the crafting arrow
        GuiTexture craftArrow = template.background.addChild(new GuiTexture(16, 16, SpritesRegister.get("backpack/craft_arrow")));
        toolkit.placeOutside(craftArrow, craftOutputSlot, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 4, -1);

        // ### Tank ###
        GuiTexture tankSlotsTex = template.background.addChild(new GuiTexture(20, 75, SpritesRegister.get("backpack/tank_slots")));
        toolkit.placeOutside(tankSlotsTex, craftOutputSlot, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, 8);
        //Tank Slots
        GuiElement<?> tankInputSlot = createSlotsNoBG(template.background, 1, 1, 0, (x, y) -> new SlotMover(container.fluidItemSlots.get(0)));
        toolkit.placeInside(tankInputSlot, tankSlotsTex, GuiToolkit.LayoutPos.TOP_CENTER, 0, 5);
        GuiElement<?> tankOutputSlot = createSlotsNoBG(template.background, 1, 1, 0, (x, y) -> new SlotMover(container.fluidItemSlots.get(1)));
        toolkit.placeInside(tankOutputSlot, tankSlotsTex, GuiToolkit.LayoutPos.BOTTOM_CENTER, 0, -5);

        GuiTexture tankArrows = template.background.addChild(new GuiTexture(12, 12, SpritesRegister.get("backpack/tank_arrows")));
        toolkit.placeOutside(tankArrows, tankSlotsTex, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 1, 0);

        GuiTexture tankBG = template.background.addChild(new GuiTexture(12, 75, SpritesRegister.get("backpack/tank")));
        toolkit.placeOutside(tankBG, tankArrows, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 1, 0);
        GuiElement<?> fluidRenderer = createFluidLevelRenderer(tankBG, tile.fluidTank, 10, 73);
        toolkit.placeOutside(fluidRenderer, tankArrows, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 2, -1);
        GuiTexture tankOverlay = fluidRenderer.addChild(new GuiTexture(12, 75, SpritesRegister.get("backpack/tank_overlay")));
        toolkit.placeOutside(tankOverlay, tankArrows, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 1, 0);

        GuiButton bedButton = toolkit.createIconButton(template.background, 16, 16, () -> tile.isSleepingbagDeployed.get() ? SpritesRegister.get("backpack/bed_deployed") : SpritesRegister.get("backpack/bed"));
        toolkit.placeInside(bedButton, template.background, GuiToolkit.LayoutPos.BOTTOM_LEFT, 4, -4);
        bedButton.setHoverText(e -> tile.isSleepingbagDeployed.get() ? toolkit.i18n("bed.remove") : toolkit.i18n("bed.deployed"));
        bedButton.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
        }, 0));

        GuiButton campfireButton = toolkit.createIconButton(template.background, 16, 16, () -> tile.isCampfireDeployed.get() ? SpritesRegister.get("backpack/campfire_deployed") : SpritesRegister.get("backpack/campfire"));
        toolkit.placeOutside(campfireButton, bedButton, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 1, 0);
        campfireButton.setHoverText(e -> tile.isCampfireDeployed.get() ? toolkit.i18n("campfire.remove") : toolkit.i18n("campfire.deployed"));
        campfireButton.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {
        }, 1));

        GuiButton upgradeButton = toolkit.createIconButton(template.background, 16, 16, () -> displayUpgrades ? SpritesRegister.get("backpack/close_upgrade") : SpritesRegister.get("backpack/upgrade"));
        toolkit.placeOutside(upgradeButton, campfireButton, GuiToolkit.LayoutPos.MIDDLE_RIGHT, 35, 0);
        upgradeButton.setHoverText(e -> displayUpgrades ? toolkit.i18n("close.upgrade") : toolkit.i18n("open.upgrade"));
        upgradeButton.onPressed(() -> {
            displayUpgrades = !displayUpgrades;
            tile.sendPacketToServer(mcDataOutput -> {}, 2);
        });

        container.craftInputSlots.forEach(slot -> slot.setIsActive(() -> tile.craftUpgrade.get() > 0));
        container.craftResultSlot.setIsActive(() -> tile.craftUpgrade.get() > 0);
        container.fluidItemSlots.forEach(slot -> slot.setIsActive(() -> tile.tankUpgrade.get() > 0));
        container.mainSlots.forEach(slot -> slot.setIsActive(() -> {
            if (displayUpgrades) {
                return false;
            } else {
                return slot.getSlotIndex() < slotsCount + (slotsCount * tile.sizeUpgrade.get());
            }
        }));
        container.upgradeSlots.forEach(slot -> slot.setIsActive(() -> displayUpgrades));

        craftTexture.setEnabledCallback(() -> tile.craftUpgrade.get() > 0);
        craftArrow.setEnabledCallback(() -> tile.craftUpgrade.get() > 0);
        craftOutputSlot.setEnabledCallback(() -> tile.craftUpgrade.get() > 0);

        tankSlotsTex.setEnabledCallback(() -> tile.tankUpgrade.get() > 0);
        tankArrows.setEnabledCallback(() -> tile.tankUpgrade.get() > 0);
        tankBG.setEnabledCallback(() -> tile.tankUpgrade.get() > 0);
        tankOverlay.setEnabledCallback(() -> tile.tankUpgrade.get() > 0);

        bedButton.setEnabledCallback(() -> tile.bedUpgrade.get() > 0);
        campfireButton.setEnabledCallback(() -> tile.fireUpgrade.get() > 0);

    }

    //###############################################################################################################################################################################
    //###############################################################################################################################################################################
    //
    // You can Ignore everything bellow this point. These are just some additional functions that will eventually be integrated into BCore, so you don't need to deal with them
    //
    //###############################################################################################################################################################################
    //###############################################################################################################################################################################

    //Ignore this I just needed a little hack to do the custom backgrounds for the crafting table amd the bucket slots
    public GuiElement<?> createSlotsNoBG(GuiElement<?> parent, int columns, int rows, int spacing, BiFunction<Integer, Integer, SlotMover> slotMapper) {
        GuiElement<?> element = new GuiElement() {
            @Override
            public GuiElement<?> translate(int xAmount, int yAmount) {
                GuiElement<?> ret = super.translate(xAmount, yAmount);
                if (slotMapper != null) {
                    for (int x = 0; x < columns; x++) {
                        for (int y = 0; y < rows; y++) {
                            SlotMover data = slotMapper.apply(x, y);
                            if (data != null) {
                                data.setPos((xPos() + (x * (18 + spacing))) - BackpackScreen.this.guiLeft() + 1, (yPos() + (y * (18 + spacing))) - BackpackScreen.this.guiTop() + 1);
                            }
                        }
                    }
                }
                return ret;
            }
        };
        element.setSize((columns * 18) + ((columns - 1) * spacing), (rows * 18) + ((rows - 1) * spacing));
        parent.addChild(element);
        return element;
    }

    //I will eventually add a proper fluid tank element into BCore but for now this wil do
    public GuiElement<?> createFluidLevelRenderer(GuiElement<?> parent, FluidTank tank, int xSize, int ySize) {
        GuiElement<?> element = new GuiElement() {
            @Override
            public void renderElement(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
                FluidStack stack = tank.getFluid();
                if (stack.isEmpty()) {
                    super.renderElement(minecraft, mouseX, mouseY, partialTicks);
                    return;
                }

                double zLevel = getRenderZLevel();
                int alpha = 255;
                double x = xPos();
                double y = yPos();
                double width = xSize();
                double height = ySize();

                double capacity = stack.getAmount() / (double) tank.getCapacity();
                FluidAttributes attributes = stack.getFluid().getAttributes();
                if (attributes == null) return;

                if (attributes.isGaseous(stack)) {
                    alpha = (int) (Math.pow(capacity, 0.4) * 255);
                } else {
                    y = yPos() + (1D - capacity) * ySize();
                    height = capacity * ySize();
                }

                Material material = ForgeHooksClient.getBlockMaterial(attributes.getStillTexture(stack));
                if (material == null || material.sprite() == null) return;

                MultiBufferSource.BufferSource getter = Minecraft.getInstance().renderBuffers().bufferSource();

                CCRenderState ccrs = CCRenderState.instance();
                ccrs.reset();
                ccrs.bind(FLUID_RENDER_TYPE, getter);
                ccrs.baseColour = attributes.getColor(stack) << 8 | alpha;
                ccrs.colour = attributes.getColor(stack) << 8 | alpha;

                List<Vertex5> verts = new ArrayList<>();
                RenderUtils.makeFluidQuadVertices(verts, new Vector3(x, y + height, zLevel), new Vector3(x + width, y + height, zLevel), new Vector3(x + width, y, zLevel), new Vector3(x, y, zLevel), material.sprite(), 16);
                for (Vertex5 vert : verts) {
                    ccrs.vert.vec = vert.vec;
                    ccrs.vert.uv = vert.uv;
                    ccrs.writeVert();
                }

                getter.endBatch();
                ccrs.reset();

                super.renderElement(minecraft, mouseX, mouseY, partialTicks);
            }

            @Override
            public boolean renderOverlayLayer(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
                PoseStack poseStack = new PoseStack();
                poseStack.translate(0, 0, getRenderZLevel());

                if (isMouseOver(mouseX, mouseY)) {
                    int maxFluid = tank.getCapacity();
                    int amount = tank.getFluidAmount();

                    String title = toolkit.i18n("fluid_storage");
                    String percent = " (" + MathUtils.round(((double) amount / (double) maxFluid) * 100D, 100) + "%)";

                    List<Component> textList = new ArrayList<>();
                    textList.add(new TranslatableComponent(title).withStyle(DARK_AQUA));
                    textList.add(tank.getFluid().getDisplayName());
                    textList.add(new TranslatableComponent(Utils.addCommas(amount) + " / " + Utils.addCommas(maxFluid) + "mb" + percent).withStyle(GRAY));

                    renderTooltip(poseStack, textList, mouseX, mouseY);
                    return true;
                }
                return super.renderOverlayLayer(minecraft, mouseX, mouseY, partialTicks);
            }
        };
        element.setSize(xSize, ySize);
        parent.addChild(element);
        return element;
    }
}