package com.greatorator.tolkienmobs.client.gui;

import com.greatorator.tolkienmobs.tileentity.container.ContainerTTMFireplace;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GuiTTMFireplace extends ContainerScreen<ContainerTTMFireplace> {
    private final ResourceLocation GUI = new ResourceLocation(MODID, "textures/gui/tmfireplace_gui.png");

    public GuiTTMFireplace(ContainerTTMFireplace screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    public void render(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);


        this.blit(matrixStack, relX + 52, relY + 36, 176, 0, 14, 17); // Fire Icon
        this.blit(matrixStack, relX + 80, relY + 34, 176, 17, 32, 19); // Progress Icon
    }
//
//    private static final ResourceLocation TEXTURES = new ResourceLocation(TolkienMobs.MODID + ":textures/gui/tmfireplace_gui.png");
//    private PlayerEntity player;
//    private TileTMFireplace tile;
//
//    public GuiTMFireplace(PlayerEntity player, TileTMFireplace tile) {
//        super(new ContainerTMFireplace(player, tile));
//        this.player = player;
//        this.tile = tile;
//
//        //Standard default container size.
//        this.xSize = 176;
//        this.ySize = 166;
//    }
//
//    @Override
//    public void addElements(GuiElementManager manager) {
//        //Create a vanilla background element that will be used as a base for the rest of the gui
//        GuiTexture background = GuiTexture.newVanillaGuiTexture(xSize, ySize);
//        //Add This adds a reload callback that centers the background on the screen when the gui is opened or resized.
//        background.addAndFireReloadCallback(guiTexture -> guiTexture.setPos(guiLeft(), guiTop()));
//        //Add the background to the element manager
//        manager.add(background);
//
//        //From this point on all new elements will be added to the background element so they inherit the background's position.
//        //This greatly simplifies the positional logic of the elements
//
//        //Take a look at layoutPlayerSlots to figure out how this works but
//        //This gives us a nice little element that represents the player inventory slots.
//        MGuiElementBase playerSlots = layoutPlayerSlots(background);
//
//        //This will be used as a container for the furnace gui elements.
//        //Once they are defined relative to each other we can use this to position them as a group
//        MGuiElementBase furnaceElements = new MGuiElementBase();
//        background.addChild(furnaceElements);
//
//        //Now to lay out the rest of the slots i am going to use a lot of relative positions to make life easier.
//        //e.g. Instead of calculating an exact position of the fuel slot and then calculating the exact position of the
//        //burning flames icon its a lot easier to just place the flames relative to the fuel slot.
//
//        GuiSlotRender input1 = new GuiSlotRender();
//        furnaceElements.addChild(input1);
//
//        GuiSlotRender input2 = new GuiSlotRender();
//        furnaceElements.addChild(input2);
//        //Put input slot 2 4 pixels to the right of input slot 1
//        input2.setRelPos(input1, input1.xSize() + 4, 0);
//
//        //Update the bounds of the element so we can use its bounds to position the fire element
//        furnaceElements.setSize(furnaceElements.getEnclosingRect());
//
//        MGuiElementBase fireIcon = createFireIcon();
//        furnaceElements.addChild(fireIcon);
//        //Put the fire icon in the center 4 pixels bellow the input slots
//        fireIcon.setXPos((furnaceElements.xSize() / 2) - (fireIcon.xSize() / 2));
//        fireIcon.setYPos(furnaceElements.maxYPos() + 4);
//
//        GuiSlotRender fuelSlot = new GuiSlotRender();
//        furnaceElements.addChild(fuelSlot);
//        //Now we place the fuel slot 4 pixels bellow the fire icon and center it.
//        fuelSlot.setYPos(fireIcon.maxYPos() + 4);
//        fuelSlot.setXPos((furnaceElements.xSize() / 2) - (fuelSlot.xSize() / 2));
//
//        //Update the bounds of the element so we can use its bounds to position the progress Icon
//        furnaceElements.setSize(furnaceElements.getEnclosingRect());
//
//        MGuiElementBase progressIcon = createProgressIcon();
//        furnaceElements.addChild(progressIcon);
//        //Center the progress icon vertically and place it 4 pixels to the right of everything else.
//        progressIcon.setYPos((furnaceElements.ySize() / 2) - (progressIcon.ySize() / 2));
//        progressIcon.setXPos(furnaceElements.maxXPos() + 4);
//
//        //This is not rendered as a standard slot so we need to create a custom icon for it.
//        GuiTexture outputSlot = new GuiTexture(0, 30, 26, 26, TEXTURES);
//        furnaceElements.addChild(outputSlot);
//        //Now we center this vertically and place it 8 pixels to the right of the progress icon.
//        outputSlot.setYPos((furnaceElements.ySize() / 2) - (outputSlot.ySize() / 2));
//        outputSlot.setXPos(progressIcon.maxXPos() + 8);
//
//        //Finally we update the bounds one last time to reflect the new elements
//        furnaceElements.setSize(furnaceElements.getEnclosingRect());
//
//
//        //At this point we have all of the furnace elements laid out and positioned correctly relative to each other. But theres a problem...
//        //They are currently all sitting up at the top left of the screen. But thats not a problem because they are all contained in the
//        //furnaceElements Element so we can easily reposition them as a group and put them where they need to be.
//
//        //First we can center them on the X axis
//        furnaceElements.setXPos(background.xPos() + (background.xSize() / 2) - (furnaceElements.xSize() / 2));
//
//        //Now before i forget we should add a title to this GUI (I totally forgot btw)
//        ITextComponent textComponent = tile.getDisplayName();
//        GuiLabel title = new GuiLabel(textComponent == null ? "" : textComponent.getFormattedText());
//        title.setSize(xSize(), 8);
//        title.setPos(guiLeft(), guiTop() + 4);
//        background.addChild(title);
//
//        //Then we can place them half way between the previously played out player slots and the Title.
//        int ySpace = playerSlots.yPos() - title.maxYPos();
//        furnaceElements.setYPos(title.maxYPos() + (ySpace / 2) - (furnaceElements.ySize() / 2));
//        //On second thought lets just push that up a few pixels.
//        furnaceElements.translate(0, -3);
//    }
//
//
//    private MGuiElementBase createFireIcon() {
//        //Define the background "static" image
//        GuiTexture texture = new GuiTexture(0, 16, 14, 14, TEXTURES);
//
//        GuiTexture overlay = new GuiTexture(14, 16, 14, 14, TEXTURES);
//        overlay.setYSizeMod((guiTexture, integer) -> (int) (14 * tile.getBurnProgress()));
//        overlay.setTexYGetter(() -> 16 + (int)(14 * (1D - tile.getBurnProgress())));
//        overlay.setYPosMod(() -> texture.yPos() + (int) (14 * (1D - tile.getBurnProgress())));
//        texture.addChild(overlay);
//        return texture;
//    }
//
//    private MGuiElementBase createProgressIcon() {
//        //Define the background "static" image
//        GuiTexture texture = new GuiTexture(0, 0, 22, 16, TEXTURES);
//
//        GuiTexture overlay = new GuiTexture(22, 0, 22, 16, TEXTURES);
//        overlay.setXSizeMod((guiTexture, integer) -> (int) (23 * tile.getSmeltProgress()));
//        texture.addChild(overlay);
//
//        return texture;
//    }
//
//
//    private MGuiElementBase layoutPlayerSlots(MGuiElementBase bg) {
//        //By adding all of the player slots to a container element we give ourselves a convenient little slot package
//        //that we can easily position in the gui.
//        MGuiElementBase playerSlots = new MGuiElementBase();
//        bg.addChild(playerSlots);
//
//        //Add Hot Bar Slots
//        for (int x = 0; x < 9; x++) {
//            playerSlots.addChild(new GuiSlotRender().setRelPos(18 * x, 54 + 4));
//        }
//
//        //Add Inventory Slots
//        for (int y = 0; y < 3; y++) {
//            for (int x = 0; x < 9; x++) {
//                playerSlots.addChild(new GuiSlotRender().setRelPos(18 * x, y * 18));
//            }
//        }
//
//        //This calculates the size of the bounds of the playerSlots element based on the slots we just added to it.
//        //This means we now have a way to get the total width and height of the slots which makes positioning them a lot easier.
//        playerSlots.setSize(playerSlots.getEnclosingRect());
//
//        //First things first lets center the slots on the background.
//        playerSlots.setXPos(bg.xPos() + (bg.xSize() / 2) - (playerSlots.xSize() / 2));
//
//        //Now we can calculate the gap to the left and right of the slots and use that to set the height.
//        //This way we can make the gap bellow the slots identical to that on each side of the slots
//        int gap = playerSlots.xPos() - bg.xPos();
//        playerSlots.setYPos(bg.maxYPos() - playerSlots.ySize() - gap);
//
//        return playerSlots;
//    }
}