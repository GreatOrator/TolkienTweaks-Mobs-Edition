package com.greatorator.tolkienmobs.client.gui.container;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.client.gui.container.slots.ButtonSlot;
import com.greatorator.tolkienmobs.client.gui.container.slots.CraftingSlot;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import com.greatorator.tolkienmobs.entity.tile.tiledata.DataTTMInventoryStateData;
import com.greatorator.tolkienmobs.entity.tile.tilezone.ZoneTTMInventoryContents;
import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BackpackContainer extends RecipeBookContainer<WrapperTTMCraftingInventory> {
    private static final EquipmentSlotType[] SLOT_IDS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};
    private final PlayerEntity owner;
    BackpackTile tile = new BackpackTile();
    private ButtonSlot deploySleepingbagButton;
    private WrapperTTMCraftingInventory craftInventory;
    CraftResultInventory craftResult = new CraftResultInventory();
    public static Slot craftResultSlot;
    public List<Slot> craftInputSlots = new ArrayList<>();

    public static BackpackContainer createContainerServerSide(int windowID, PlayerInventory playerInventory, ZoneTTMInventoryContents invZoneContents, ZoneTTMInventoryContents craftZoneContents, ZoneTTMInventoryContents craftOutputZoneContents, ZoneTTMInventoryContents fluidZoneContents, ZoneTTMInventoryContents fluidInputZoneContents, ZoneTTMInventoryContents fluidOutputZoneContents, DataTTMInventoryStateData invStateData) {
        return new BackpackContainer(windowID, playerInventory,
                invZoneContents, craftZoneContents, craftOutputZoneContents, fluidZoneContents, fluidInputZoneContents, fluidOutputZoneContents, invStateData);
    }

    public static BackpackContainer createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {
        ZoneTTMInventoryContents invZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(INV_SLOTS_COUNT);
        ZoneTTMInventoryContents craftZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(CRAFT_SLOTS_COUNT);
        ZoneTTMInventoryContents craftOutputZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(CRAFT_OUTPUT_SLOTS_COUNT);
        ZoneTTMInventoryContents fluidZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(TANK_SLOTS_COUNT);
        ZoneTTMInventoryContents fluidInputZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(TANK_INPUT_SLOTS_COUNT);
        ZoneTTMInventoryContents fluidOutputZoneContents = ZoneTTMInventoryContents.createForClientSideContainer(TANK_OUTPUT_SLOTS_COUNT);
        DataTTMInventoryStateData invStateData = new DataTTMInventoryStateData();

        return new BackpackContainer(windowID, playerInventory, invZoneContents, craftZoneContents, craftOutputZoneContents, fluidZoneContents, fluidInputZoneContents, fluidOutputZoneContents, invStateData);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_ROW_COUNT = 3;
    private static final int INVENTORY_ROW_COUNT = 6;
    private static final int COLUMN_COUNT = 9;
    private static final int SHIELD_INVENTORY_SLOT_COUNT = 1;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = COLUMN_COUNT * PLAYER_ROW_COUNT;

    public static final int INV_SLOTS_COUNT = BackpackTile.INV_SLOTS_COUNT;
    public static final int CRAFT_SLOTS_COUNT = BackpackTile.CRAFT_SLOTS_COUNT;
    public static final int CRAFT_OUTPUT_SLOTS_COUNT = BackpackTile.CRAFT_OUTPUT_SLOTS_COUNT;
    public static final int TANK_SLOTS_COUNT = BackpackTile.TANK_SLOTS_COUNT;
    public static final int TANK_INPUT_SLOTS_COUNT = BackpackTile.TANK_INPUT_SLOTS_COUNT;
    public static final int TANK_OUTPUT_SLOTS_COUNT = BackpackTile.TANK_OUTPUT_SLOTS_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int HOTBAR_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT;
    private static final int ARMOR_FIRST_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT + PLAYER_ROW_COUNT;
    private static final int SHIELD_FIRST_SLOT_INDEX = ARMOR_FIRST_SLOT_INDEX + SHIELD_INVENTORY_SLOT_COUNT;
    private static final int FIRST_INV_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int FIRST_CRAFT_SLOT_INDEX = FIRST_INV_SLOT_INDEX + INV_SLOTS_COUNT;
    private static final int FIRST_CRAFT_OUTPUT_SLOT_INDEX = FIRST_CRAFT_SLOT_INDEX + CRAFT_SLOTS_COUNT;
    private static final int FIRST_TANK_SLOT_INDEX = FIRST_CRAFT_OUTPUT_SLOT_INDEX + CRAFT_OUTPUT_SLOTS_COUNT;
    private static final int FIRST_TANK_INPUT_SLOT_INDEX = FIRST_TANK_SLOT_INDEX + TANK_SLOTS_COUNT;
    private static final int FIRST_TANK_OUTPUT_SLOT_INDEX = FIRST_TANK_INPUT_SLOT_INDEX + TANK_INPUT_SLOTS_COUNT;

    public static final int PLAYER_INVENTORY_XPOS = 90;
    public static final int PLAYER_INVENTORY_YPOS = 127;

    final int SLOT_SPACING = 18;
    final int HOTBAR_XPOS = 90;
    final int HOTBAR_YPOS = 185;

    final int INV_SLOTS_XPOS = 90;
    final int INV_SLOTS_YPOS = 16;

    final int ARMOR_SLOTS_XPOS = 52;
    final int ARMOR_SLOTS_YPOS = 127;
    final int SHIELD_SLOT_XPOS = 61;
    final int SHIELD_SLOTS_YPOS = 163;

    final int CRAFT_SLOTS_XPOS = 6;
    final int CRAFT_SLOTS_YPOS = 6;
    final int CRAFT_OUTPUT_SLOTS_XPOS = 6;
    final int CRAFT_OUTPUT_SLOTS_YPOS = 62;

    final int TANK_SLOTS_XPOS = 38;
    final int TANK_SLOTS_YPOS = 88;
    final int TANK_INPUT_SLOTS_XPOS = 6;
    final int TANK_INPUT_SLOTS_YPOS = 93;
    final int TANK_OUTPUT_SLOTS_XPOS = 6;
    final int TANK_OUTPUT_SLOTS_YPOS = 140;

    public BackpackContainer(int windowID, PlayerInventory invPlayer, ZoneTTMInventoryContents invZoneContents, ZoneTTMInventoryContents craftZoneContents, ZoneTTMInventoryContents craftOutputZoneContents, ZoneTTMInventoryContents fluidZoneContents, ZoneTTMInventoryContents fluidInputZoneContents, ZoneTTMInventoryContents fluidOutputZoneContents, DataTTMInventoryStateData invStateData) {
        super(TTMContent.BACKPACK_CONTAINER.get(), windowID);

        this.invZoneContents = invZoneContents;
        this.craftZoneContents = craftZoneContents;
        this.craftOutputZoneContents = craftOutputZoneContents;
        this.fluidZoneContents = fluidZoneContents;
        this.fluidInputZoneContents = fluidInputZoneContents;
        this.fluidOutputZoneContents = fluidOutputZoneContents;
        this.owner = invPlayer.player;

        addDataSlots(invStateData);

        int row;
        int col;
        int slot = 0;
        int id = 0;

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

        // Add players armor inventory
        for (row = 0; row < 2; row++) {
            for (col = 0; col < 2; col++) {
                int slot_id = ++id;
                final EquipmentSlotType equipmentslottype = SLOT_IDS[slot_id - 1];
                this.addSlot(new Slot(invPlayer, ARMOR_FIRST_SLOT_INDEX - (slot_id - 1), ARMOR_SLOTS_XPOS + row * SLOT_SPACING, ARMOR_SLOTS_YPOS + col * SLOT_SPACING) {
                    @Override
                    public int getMaxStackSize() {
                        return 1;
                    }

                    @SuppressWarnings("NullableProblems")
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return stack.canEquip(equipmentslottype, BackpackContainer.this.owner);
                    }

                    @SuppressWarnings("NullableProblems")
                    @Override
                    public boolean mayPickup(PlayerEntity playerIn) {
                        ItemStack itemstack = this.getItem();
                        return (itemstack.isEmpty() || playerIn.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.mayPickup(playerIn);
                    }

                    @OnlyIn(Dist.CLIENT)
                    @Override
                    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                        return Pair.of(PlayerContainer.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS[equipmentslottype.getIndex()]);
                    }
                });
            }
        }

        this.addSlot(new Slot(invPlayer, SHIELD_FIRST_SLOT_INDEX, SHIELD_SLOT_XPOS, SHIELD_SLOTS_YPOS) {
            @OnlyIn(Dist.CLIENT)
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(PlayerContainer.BLOCK_ATLAS, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);
            }
        });

        // Add the tile Inventory slots
        for (row = 0; row < INVENTORY_ROW_COUNT; row++) {
            for (col = 0; col < COLUMN_COUNT; col++) {
                int slotNumber = slot++;
                addSlot(new Slot(invZoneContents, slotNumber, INV_SLOTS_XPOS + col * SLOT_SPACING, INV_SLOTS_YPOS + row * SLOT_SPACING));
            }
        }

        // Add the tile Crafting slots
        craftInventory = new WrapperTTMCraftingInventory(this, 3, 3, tile.craftingItems);
        this.addSlot(craftResultSlot = new CraftingSlot(invPlayer.player, craftInventory, craftResult, CRAFT_OUTPUT_SLOTS_COUNT - 1, CRAFT_OUTPUT_SLOTS_XPOS, CRAFT_OUTPUT_SLOTS_YPOS));

        for (row = 0; row < PLAYER_ROW_COUNT; ++row) {
            for (col = 0; col < PLAYER_ROW_COUNT; ++col) {
                int slotNumber = (row + col * PLAYER_ROW_COUNT);
                craftInputSlots.add(addSlot(new Slot(craftInventory, slotNumber, CRAFT_SLOTS_XPOS + col * SLOT_SPACING, CRAFT_SLOTS_YPOS + row * SLOT_SPACING)));
            }
        }

//        // Water Tank
//        this.addSlot(new Slot(fluidZoneContents, TANK_SLOTS_COUNT - 1, TANK_SLOTS_XPOS, TANK_SLOTS_YPOS));
//
//        // Water Input
//        this.addSlot(new SlotTTMFluid(fluidInputZoneContents, WATER_INPUT_SLOTS_COUNT - 1, TANK_INPUT_SLOTS_XPOS, TANK_INPUT_SLOTS_YPOS));
//
//        // Water Output
//        this.addSlot(new SlotTTMFluid(fluidOutputZoneContents, WATER_OUTPUT_SLOTS_COUNT - 1, TANK_OUTPUT_SLOTS_XPOS, TANK_OUTPUT_SLOTS_YPOS));
        slotsChanged(invPlayer);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean stillValid(PlayerEntity player) {
        return invZoneContents.stillValid(player) && craftZoneContents.stillValid(player) && craftOutputZoneContents.stillValid(player) && fluidZoneContents.stillValid(player) && fluidInputZoneContents.stillValid(player) && fluidOutputZoneContents.stillValid(player);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int sourceSlotIndex) {
        Slot sourceSlot = this.slots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceItemStack = sourceSlot.getItem();
        ItemStack sourceStackBeforeMerge = sourceItemStack.copy();
        boolean successfulTransfer;

        SlotZone sourceZone = SlotZone.getZoneFromIndex(sourceSlotIndex);

        switch (sourceZone) {
            case PLAYER_HOTBAR:
                successfulTransfer = mergeInto(SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, true);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(SlotZone.INV_ZONE, sourceItemStack, true);
                }
                if (successfulTransfer) {
                    sourceSlot.onQuickCraft(sourceItemStack, sourceStackBeforeMerge);
                }
                break;

            case PLAYER_MAIN_INVENTORY:
                successfulTransfer = mergeInto(SlotZone.INV_ZONE, sourceItemStack, true);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(SlotZone.PLAYER_HOTBAR, sourceItemStack, true);
                }
                if (successfulTransfer) {
                    sourceSlot.onQuickCraft(sourceItemStack, sourceStackBeforeMerge);
                }
                break;
            case INV_ZONE:

            case CRAFT_ZONE:

            case ARMOR_ZONE:

            case SHIELD_ZONE:
                successfulTransfer = mergeInto(SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, true);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(SlotZone.PLAYER_HOTBAR, sourceItemStack, true);
                }
                if (successfulTransfer) {
                    sourceSlot.onQuickCraft(sourceItemStack, sourceStackBeforeMerge);
                }
                break;

            case FLUID_ZONE:

            case FLUID_INPUT_ZONE:

            case CRAFT_OUTPUT_ZONE:

            case FLUID_OUTPUT_ZONE:
                successfulTransfer = mergeInto(SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, false);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(SlotZone.PLAYER_HOTBAR, sourceItemStack, false);
                }
                break;
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

    private boolean mergeInto(SlotZone destinationZone, ItemStack sourceItemStack, boolean fillFromEnd) {
        return moveItemStackTo(sourceItemStack, destinationZone.firstIndex, destinationZone.lastIndexPlus1, fillFromEnd);
    }


    private final ZoneTTMInventoryContents invZoneContents;
    private final ZoneTTMInventoryContents craftZoneContents;
    private final ZoneTTMInventoryContents craftOutputZoneContents;
    private final ZoneTTMInventoryContents fluidZoneContents;
    private final ZoneTTMInventoryContents fluidInputZoneContents;
    private final ZoneTTMInventoryContents fluidOutputZoneContents;

    public static class SlotInput extends Slot {
        public SlotInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public boolean mayPlace(ItemStack stack) {
            return BackpackTile.isItemValidForInputSlot(stack);
        }
    }

    private enum SlotZone {
        PLAYER_HOTBAR(HOTBAR_FIRST_SLOT_INDEX, HOTBAR_SLOT_COUNT),
        PLAYER_MAIN_INVENTORY(PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_SLOT_COUNT),
        INV_ZONE(FIRST_INV_SLOT_INDEX, INV_SLOTS_COUNT),
        ARMOR_ZONE(ARMOR_FIRST_SLOT_INDEX, 4),
        SHIELD_ZONE(SHIELD_FIRST_SLOT_INDEX, 1),
        CRAFT_ZONE(FIRST_CRAFT_SLOT_INDEX, CRAFT_SLOTS_COUNT),
        CRAFT_OUTPUT_ZONE(FIRST_CRAFT_OUTPUT_SLOT_INDEX, CRAFT_OUTPUT_SLOTS_COUNT),
        FLUID_ZONE(FIRST_TANK_SLOT_INDEX, TANK_SLOTS_COUNT),
        FLUID_INPUT_ZONE(FIRST_TANK_INPUT_SLOT_INDEX, TANK_INPUT_SLOTS_COUNT),
        FLUID_OUTPUT_ZONE(FIRST_TANK_OUTPUT_SLOT_INDEX, TANK_OUTPUT_SLOTS_COUNT);

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

    @SuppressWarnings("NullableProblems")
    @Override
    public void fillCraftSlotsStackedContents(RecipeItemHelper itemHelper) {
        craftInventory.fillStackedContents(itemHelper);
    }

    @Override
    public void clearCraftingContent() {
        this.craftResult.clearContent();
        craftInventory.clearContent();
    }

    @Override
    public boolean recipeMatches(IRecipe<? super WrapperTTMCraftingInventory> recipe) {
        return recipe.matches(craftInventory, this.owner.level);
    }

    protected static void slotChangedCraftingGrid(int containerID, World worldIn, PlayerEntity playerIn, WrapperTTMCraftingInventory craftInv, CraftResultInventory craftResultInv) {
        if (!worldIn.isClientSide) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) playerIn;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<ICraftingRecipe> optional = Objects.requireNonNull(worldIn.getServer()).getRecipeManager().getRecipeFor(IRecipeType.CRAFTING, craftInv, worldIn);
            if (optional.isPresent()) {
                ICraftingRecipe icraftingrecipe = optional.get();
                if (craftResultInv.setRecipeUsed(worldIn, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.assemble(craftInv);
                }
            }

            craftResultInv.setItem(0, itemstack);
            serverplayerentity.connection.send(new SSetSlotPacket(containerID, craftResultSlot.index, itemstack));
        }
    }

    @SuppressWarnings("NullableProblems")
    public void slotsChanged(IInventory inv) {
        slotChangedCraftingGrid(this.containerId, this.owner.level, this.owner, craftInventory, this.craftResult);
    }

    @SuppressWarnings("NullableProblems")
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.craftResult.clearContent();
        if (!playerIn.level.isClientSide) {
            this.clearContainer(playerIn, playerIn.level, craftInventory);
        }
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return craftInventory.getWidth();
    }

    @Override
    public int getGridHeight() {
        return craftInventory.getHeight();
    }

    @Override
    public int getSize() {
        return 10;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public RecipeBookCategory getRecipeBookType() {
        return RecipeBookCategory.CRAFTING;
    }
}