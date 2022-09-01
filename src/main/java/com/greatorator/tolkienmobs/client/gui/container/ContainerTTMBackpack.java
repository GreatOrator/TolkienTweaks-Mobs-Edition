package com.greatorator.tolkienmobs.client.gui.container;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import com.greatorator.tolkienmobs.entity.tile.tiledata.DataTTMInventoryStateData;
import com.greatorator.tolkienmobs.entity.tile.tilezone.ZoneTTMInventoryContents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerTTMBackpack extends Container {
    public static ContainerTTMBackpack createContainerServerSide(int windowID, PlayerInventory playerInventory, ZoneTTMInventoryContents invZoneContents, ZoneTTMInventoryContents craftZoneContents, ZoneTTMInventoryContents craftOutputZoneContents, ZoneTTMInventoryContents fluidZoneContents, ZoneTTMInventoryContents fluidInputZoneContents, ZoneTTMInventoryContents fluidOutputZoneContents, DataTTMInventoryStateData invStateData) {
        return new ContainerTTMBackpack(windowID, playerInventory,
                invZoneContents, craftZoneContents, craftOutputZoneContents, fluidZoneContents, fluidInputZoneContents, fluidOutputZoneContents, invStateData);
    }

    public static ContainerTTMBackpack createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {
        ZoneTTMInventoryContents invZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(INV_SLOTS_COUNT);
        ZoneTTMInventoryContents craftZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(CRAFT_SLOTS_COUNT);
        ZoneTTMInventoryContents craftOutputZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(CRAFT_OUTPUT_SLOTS_COUNT);
        ZoneTTMInventoryContents fluidZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(WATER_TANK_SLOTS_COUNT);
        ZoneTTMInventoryContents fluidInputZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(WATER_INPUT_SLOTS_COUNT);
        ZoneTTMInventoryContents fluidOutputZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(WATER_OUTPUT_SLOTS_COUNT);
        DataTTMInventoryStateData invStateData = new DataTTMInventoryStateData();

        return new ContainerTTMBackpack(windowID, playerInventory, invZoneContents, craftZoneContents, craftOutputZoneContents, fluidZoneContents, fluidInputZoneContents, fluidOutputZoneContents, invStateData);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_ROW_COUNT = 3;
    private static final int INVENTORY_ROW_COUNT = 6;
    private static final int COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = COLUMN_COUNT * PLAYER_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int INV_SLOTS_COUNT = TTMBackpackTile.INV_SLOTS_COUNT;
    public static final int CRAFT_SLOTS_COUNT = TTMBackpackTile.CRAFT_SLOTS_COUNT;
    public static final int CRAFT_OUTPUT_SLOTS_COUNT = TTMBackpackTile.CRAFT_OUTPUT_SLOTS_COUNT;
    public static final int WATER_INPUT_SLOTS_COUNT = TTMBackpackTile.WATER_TANK_SLOTS_COUNT;
    public static final int WATER_OUTPUT_SLOTS_COUNT = TTMBackpackTile.WATER_INPUT_SLOTS_COUNT;
    public static final int WATER_TANK_SLOTS_COUNT = TTMBackpackTile.WATER_OUTPUT_SLOTS_COUNT;
    public static final int TOTAL_SLOTS_COUNT = INV_SLOTS_COUNT + CRAFT_SLOTS_COUNT + CRAFT_OUTPUT_SLOTS_COUNT + WATER_TANK_SLOTS_COUNT + WATER_INPUT_SLOTS_COUNT + WATER_OUTPUT_SLOTS_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int HOTBAR_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT;
    private static final int FIRST_INV_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int FIRST_CRAFT_SLOT_INDEX = FIRST_INV_SLOT_INDEX + INV_SLOTS_COUNT;
    private static final int FIRST_CRAFT_OUTPUT_SLOT_INDEX = FIRST_CRAFT_SLOT_INDEX + CRAFT_SLOTS_COUNT;
    private static final int FIRST_WATER_SLOT_INDEX = FIRST_CRAFT_OUTPUT_SLOT_INDEX + CRAFT_OUTPUT_SLOTS_COUNT;
    private static final int FIRST_WATER_INPUT_SLOT_INDEX = FIRST_WATER_SLOT_INDEX + WATER_TANK_SLOTS_COUNT;
    private static final int FIRST_WATER_OUTPUT_SLOT_INDEX = FIRST_WATER_INPUT_SLOT_INDEX + WATER_INPUT_SLOTS_COUNT;

    public static final int PLAYER_INVENTORY_XPOS = 90;
    public static final int PLAYER_INVENTORY_YPOS = 127;

    final int SLOT_SPACING = 18;
    final int HOTBAR_XPOS = 90;
    final int HOTBAR_YPOS = 185;

    final int INV_SLOTS_XPOS = 90;
    final int INV_SLOTS_YPOS = 6;

    final int CRAFT_SLOTS_XPOS = 6;
    final int CRAFT_SLOTS_YPOS = 6;
    final int CRAFT_OUTPUT_SLOTS_XPOS = 6;
    final int CRAFT_OUTPUT_SLOTS_YPOS = 62;

    final int TANK_SLOTS_XPOS = 37;
    final int TANK_SLOTS_YPOS = 91;
    final int TANK_INPUT_SLOTS_XPOS = 6;
    final int TANK_INPUT_SLOTS_YPOS = 96;
    final int TANK_OUTPUT_SLOTS_XPOS = 6;
    final int TANK_OUTPUT_SLOTS_YPOS = 135;

    public ContainerTTMBackpack(int windowID, PlayerInventory invPlayer, ZoneTTMInventoryContents invZoneContents, ZoneTTMInventoryContents craftZoneContents, ZoneTTMInventoryContents craftOutputZoneContents, ZoneTTMInventoryContents fluidZoneContents, ZoneTTMInventoryContents fluidInputZoneContents, ZoneTTMInventoryContents fluidOutputZoneContents, DataTTMInventoryStateData invStateData) {
        super(TTMContent.BACKPACK_CONTAINER.get(), windowID);

        if (TTMContent.BACKPACK_CONTAINER.get() == null)
            throw new IllegalStateException("Must initialise containerTypeContainerBackpack before constructing a ContainerTTMBackpack!");

        this.invZoneContents = invZoneContents;
        this.craftZoneContents = craftZoneContents;
        this.craftOutputZoneContents = craftOutputZoneContents;
        this.fluidZoneContents = fluidZoneContents;
        this.fluidInputZoneContents = fluidInputZoneContents;
        this.fluidOutputZoneContents = fluidOutputZoneContents;
        this.invStateData = invStateData;

        addDataSlots(invStateData);

        int row;
        int col;
        int slot = 0;

        this.world = invPlayer.player.level;

        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (col = 0; col < HOTBAR_SLOT_COUNT; col++) {
            addSlot(new Slot(invPlayer, col, HOTBAR_XPOS + SLOT_SPACING * col, HOTBAR_YPOS));
        }

        // Add the rest of the players inventory to the gui
        for (row = 0; row < PLAYER_ROW_COUNT; row++) {
            for (col = 0; col < COLUMN_COUNT; col++) {
                int slotNumber = HOTBAR_SLOT_COUNT + row * COLUMN_COUNT + col;
                int xpos = PLAYER_INVENTORY_XPOS + col * SLOT_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + row * SLOT_SPACING;
                addSlot(new Slot(invPlayer, slotNumber, xpos, ypos));
            }
        }

        // Add the tile Inventory slots
        for (row = 0; row < INVENTORY_ROW_COUNT; row++) {
            for (col = 0; col < COLUMN_COUNT; col++) {
                int slotNumber = slot++;
                addSlot(new Slot(invZoneContents, slotNumber, INV_SLOTS_XPOS + col * SLOT_SPACING, INV_SLOTS_YPOS + row * SLOT_SPACING));
            }
        }

        // Add the tile Crafting slots
        for (row = 0; row < PLAYER_ROW_COUNT; ++row) {
            for (col = 0; col < PLAYER_ROW_COUNT; ++col) {
                int slotNumber = (row * col);
                this.addSlot(new Slot(craftZoneContents, slotNumber, CRAFT_SLOTS_XPOS + col * SLOT_SPACING, CRAFT_SLOTS_YPOS + row * SLOT_SPACING));
            }
        }
        this.addSlot(new SlotOutput(craftOutputZoneContents, CRAFT_OUTPUT_SLOTS_COUNT - 1, CRAFT_OUTPUT_SLOTS_XPOS, CRAFT_OUTPUT_SLOTS_YPOS));

        // Water Tank
        this.addSlot(new Slot(fluidZoneContents, CRAFT_OUTPUT_SLOTS_COUNT - 1, TANK_SLOTS_XPOS, TANK_SLOTS_YPOS));

        // Water Input
        this.addSlot(new SlotInput(fluidInputZoneContents, WATER_INPUT_SLOTS_COUNT - 1, TANK_INPUT_SLOTS_XPOS, TANK_INPUT_SLOTS_YPOS));

        // Water Output
        this.addSlot(new SlotOutput(fluidOutputZoneContents, WATER_OUTPUT_SLOTS_COUNT - 1, TANK_OUTPUT_SLOTS_XPOS, TANK_OUTPUT_SLOTS_YPOS));
    }

    @Override
    public boolean stillValid(PlayerEntity player)
    {
        return invZoneContents.stillValid(player) && craftZoneContents.stillValid(player) && craftOutputZoneContents.stillValid(player) && fluidZoneContents.stillValid(player) && fluidInputZoneContents.stillValid(player) && fluidOutputZoneContents.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack (PlayerEntity player,int sourceSlotIndex) {
        Slot sourceSlot = this.slots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceItemStack = sourceSlot.getItem();
        ItemStack sourceStackBeforeMerge = sourceItemStack.copy();
        boolean successfulTransfer = false;

        SlotZone sourceZone = SlotZone.getZoneFromIndex(sourceSlotIndex);

        switch (sourceZone) {
            case INV_ZONE:
                successfulTransfer = mergeInto(SlotZone.PLAYER_HOTBAR, sourceItemStack, true);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, true);
                }
                if (successfulTransfer) {
                    sourceSlot.onQuickCraft(sourceItemStack, sourceStackBeforeMerge);
                }
                break;

            case CRAFT_OUTPUT_ZONE:

            case FLUID_OUTPUT_ZONE:
                successfulTransfer = mergeInto(SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, false);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(SlotZone.PLAYER_HOTBAR, sourceItemStack, false);
                }
                break;

            case PLAYER_HOTBAR:
            default:
                throw new IllegalArgumentException("unexpected sourceZone:" + sourceZone);
        }
        if (!successfulTransfer) return ItemStack.EMPTY;

        if (sourceItemStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        if (sourceItemStack.getCount() == sourceStackBeforeMerge.getCount()) {
            return ItemStack.EMPTY;
        }
        sourceSlot.onTake(player, sourceItemStack);
        return sourceStackBeforeMerge;
    }

    private boolean mergeInto (SlotZone destinationZone, ItemStack sourceItemStack, boolean fillFromEnd){
        return moveItemStackTo(sourceItemStack, destinationZone.firstIndex, destinationZone.lastIndexPlus1, fillFromEnd);
    }


    private final ZoneTTMInventoryContents invZoneContents;
    private final ZoneTTMInventoryContents craftZoneContents;
    private final ZoneTTMInventoryContents craftOutputZoneContents;
    private final ZoneTTMInventoryContents fluidZoneContents;
    private final ZoneTTMInventoryContents fluidInputZoneContents;
    private final ZoneTTMInventoryContents fluidOutputZoneContents;
    private final DataTTMInventoryStateData invStateData;

    private final World world;

    public class SlotInput extends Slot {
        public SlotInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return TTMBackpackTile.isItemValidForInputSlot(stack);
        }
    }

    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return TTMBackpackTile.isItemValidForOutputSlot(stack);
        }
    }

    private enum SlotZone {
    PLAYER_HOTBAR(HOTBAR_FIRST_SLOT_INDEX, HOTBAR_SLOT_COUNT),
    PLAYER_MAIN_INVENTORY(PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_SLOT_COUNT),
    INV_ZONE(FIRST_INV_SLOT_INDEX, INV_SLOTS_COUNT),
    CRAFT_ZONE(FIRST_CRAFT_SLOT_INDEX, CRAFT_SLOTS_COUNT),
    CRAFT_OUTPUT_ZONE(FIRST_CRAFT_OUTPUT_SLOT_INDEX, CRAFT_OUTPUT_SLOTS_COUNT),
    FLUID_ZONE(FIRST_WATER_SLOT_INDEX, WATER_TANK_SLOTS_COUNT),
    FLUID_INPUT_ZONE(FIRST_WATER_INPUT_SLOT_INDEX, WATER_INPUT_SLOTS_COUNT),
    FLUID_OUTPUT_ZONE(FIRST_WATER_OUTPUT_SLOT_INDEX, WATER_OUTPUT_SLOTS_COUNT);

        SlotZone(int firstIndex, int numberOfSlots) {
            this.firstIndex = firstIndex;
            this.slotCount = numberOfSlots;
            this.lastIndexPlus1 = firstIndex + numberOfSlots;
        }

        public final int firstIndex;
        public final int slotCount;
        public final int lastIndexPlus1;

        public static SlotZone getZoneFromIndex(int slotIndex) {
            for (SlotZone slotZone : SlotZone.values()) {
                if (slotIndex >= slotZone.firstIndex && slotIndex < slotZone.lastIndexPlus1) return slotZone;
            }
            throw new IndexOutOfBoundsException("Unexpected slotIndex");
        }
    }
}