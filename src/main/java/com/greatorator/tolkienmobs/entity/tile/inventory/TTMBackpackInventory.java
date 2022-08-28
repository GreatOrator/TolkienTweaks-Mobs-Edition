package com.greatorator.tolkienmobs.entity.tile.inventory;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout.LayoutFactory;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import com.greatorator.tolkienmobs.handler.TTMCapabilities;
import com.greatorator.tolkienmobs.handler.TTMISUtils;
import com.greatorator.tolkienmobs.handler.TTMInventoryActions;
import com.greatorator.tolkienmobs.handler.TTMSlotManager;
import com.greatorator.tolkienmobs.item.tools.ItemTTMBackpack;
import com.greatorator.tolkienmobs.utils.TTMReference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TTMBackpackInventory extends TileBCore implements ITTMBackpackInventory, INamedContainerProvider {
	public static final LayoutFactory<TTMBackpackInventory> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.inventory);
    private final ItemStackHandler inventory = createHandler(TTMReference.INVENTORY_SIZE);
    private final ItemStackHandler craftingInventory = createHandler(TTMReference.CRAFTING_GRID_SIZE);
    private final FluidTank waterTank = createFluidHandler(TTMReference.BASIC_TANK_CAPACITY);
    private final TTMSlotManager slotManager = new TTMSlotManager(this);
    private final PlayerEntity player;
    private ItemStack stack;

    private boolean ability;
    private int lastTime;
    private final byte screenID;

    private final String INVENTORY = "Inventory";
    private final String CRAFTING_INVENTORY = "CraftingInventory";
    private final String WATER_TANK = "WaterTank";
    private final String COLOR = "Color";
    private final String ABILITY = "Ability";
    private final String LAST_TIME = "LastTime";

    public TTMBackpackInventory(ItemStack stack, PlayerEntity player, byte screenID)
    {
        super(TTMContent.BACKPACK_TILE.get());
        this.player = player;
        this.stack = stack;
        this.screenID = screenID;

        this.loadAllData(stack.getOrCreateTag());
    }

    public void setStack(ItemStack stack)
    {
        this.stack = stack;
    }

    @Override
    public ItemStackHandler getInventory()
    {
        return this.inventory;
    }

    @Override
    public ItemStackHandler getCraftingGridInventory()
    {
        return this.craftingInventory;
    }

    @Override
    public FluidTank getWaterTank()
    {
        return this.waterTank;
    }

    @Override
    public void saveAllData(CompoundNBT compound)
    {
        this.saveTank(compound);
        this.saveItems(compound);
        this.saveAbility(compound);
        this.saveTime(compound);
        this.slotManager.saveUnsortableSlots(compound);
    }

    @Override
    public void loadAllData(CompoundNBT compound)
    {
        this.loadTank(compound);
        this.loadItems(compound);
        this.loadAbility(compound);
        this.loadTime(compound);
        this.slotManager.loadUnsortableSlots(compound);
    }

    @Override
    public void saveItems(CompoundNBT compound)
    {
        compound.put(INVENTORY, this.inventory.serializeNBT());
        compound.put(CRAFTING_INVENTORY, this.craftingInventory.serializeNBT());
    }

    @Override
    public void loadItems(CompoundNBT compound)
    {
        this.inventory.deserializeNBT(compound.getCompound(INVENTORY));
        this.craftingInventory.deserializeNBT(compound.getCompound(CRAFTING_INVENTORY));
    }

    @Override
    public void saveTank(CompoundNBT compound)
    {
        compound.put(WATER_TANK, this.waterTank.writeToNBT(new CompoundNBT()));
    }

    @Override
    public void loadTank(CompoundNBT compound)
    {
        this.waterTank.readFromNBT(compound.getCompound(WATER_TANK));
    }

    @Override
    public void saveColor(CompoundNBT compound) {}
    @Override
    public void loadColor(CompoundNBT compound) {}

    @Override
    public void saveAbility(CompoundNBT compound)
    {
        compound.putBoolean(ABILITY, this.ability);
    }

    @Override
    public void loadAbility(CompoundNBT compound)
    {
        this.ability = compound.getBoolean(ABILITY);
    }

    @Override
    public void saveTime(CompoundNBT compound)
    {
        compound.putInt(LAST_TIME, this.lastTime);
    }

    @Override
    public void loadTime(CompoundNBT compound)
    {
        this.lastTime = compound.getInt(LAST_TIME);
    }

    @Override
    public boolean updateTankSlots()
    {
        return TTMInventoryActions.transferContainerTank(this, getWaterTank(), TTMReference.BUCKET_IN_LEFT, player);
    }

    private void sendPackets()
    {
        if(screenID == TTMReference.WEARABLE_SCREEN_ID)
        {
            TTMCapabilities.synchronise(player);
            TTMCapabilities.synchroniseToOthers(player);
        }
    }

    @Override
    public boolean hasColor()
    {
        return stack.getOrCreateTag().contains(COLOR);
    }

    @Override
    public int getColor()
    {
        if(hasColor())
        {
            return stack.getOrCreateTag().getInt(COLOR);
        }
        return 0;
    }

    @Override
    public void setAbility(boolean value)
    {
        this.ability = value;
    }

    @Override
    public int getLastTime()
    {
        return this.lastTime;
    }

    @Override
    public void setLastTime(int time)
    {
        this.lastTime = time;
    }

    @Override
    public boolean hasTileEntity()
    {
        return false;
    }

    @Override
    public boolean isSleepingBagDeployed()
    {
        return false;
    }

    @Override
    public TTMSlotManager getSlotManager()
    {
        return slotManager;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = TTMISUtils.getAndSplit(getInventory(), index, count);

        if(!itemstack.isEmpty())
        {
            setDataChanged(COMBINED_INVENTORY_DATA);
        }
        return itemstack;
    }

    @Override
    public World getLevel()
    {
        return this.player.level;
    }

    @Override
    public BlockPos getPosition()
    {
        return this.player.blockPosition();
    }

    @Override
    public byte getScreenID()
    {
        return this.screenID;
    }

    @Override
    public ItemStack getItemStack()
    {
        return this.stack;
    }

    @Override
    public void setUsingPlayer(@Nullable PlayerEntity player) {}

    @Override
    public void setDataChanged(byte... dataIds)
    {
        if(getLevel().isClientSide) return;

        for(byte data : dataIds)
        {
            switch(data)
            {
                case INVENTORY_DATA: stack.getOrCreateTag().put(INVENTORY, this.inventory.serializeNBT());
                case CRAFTING_INVENTORY_DATA: stack.getOrCreateTag().put(CRAFTING_INVENTORY, this.craftingInventory.serializeNBT());
                case COMBINED_INVENTORY_DATA: saveItems(stack.getOrCreateTag());
                case TANKS_DATA: saveTank(stack.getOrCreateTag());
                case COLOR_DATA: saveColor(stack.getOrCreateTag());
                case ABILITY_DATA: saveAbility(stack.getOrCreateTag());
                case LAST_TIME_DATA: saveTime(stack.getOrCreateTag());
                case SLOT_DATA: slotManager.saveUnsortableSlots(stack.getOrCreateTag());
                case ALL_DATA: saveAllData(stack.getOrCreateTag());
            }
        }
        sendPackets();
    }

    @Override
    public void setChanged() {}

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent("screen.travelersbackpack.item");
    }

    public static void openGUI(ServerPlayerEntity serverPlayerEntity, ItemStack stack, byte screenID)
    {
        if(!serverPlayerEntity.level.isClientSide)
        {
            if(screenID == TTMReference.ITEM_SCREEN_ID)
            {
                NetworkHooks.openGui(serverPlayerEntity, new TTMBackpackInventory(stack, serverPlayerEntity, screenID), packetBuffer -> packetBuffer.writeByte(screenID));
            }

            if(screenID == TTMReference.WEARABLE_SCREEN_ID)
            {
                NetworkHooks.openGui(serverPlayerEntity, (INamedContainerProvider) TTMCapabilities.getBackpackInv(serverPlayerEntity), packetBuffer -> packetBuffer.writeByte(screenID));
            }
        }
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
    	return new ContainerBCTile<TTMBackpackInventory>(TTMContent.BACKPACK_ITEM_CONTAINER, id, playerInventory, this, SLOT_LAYOUT);
    }

    private ItemStackHandler createHandler(int size)
    {
        return new ItemStackHandler(size)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                setDataChanged(COMBINED_INVENTORY_DATA);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {
                return !(stack.getItem() instanceof ItemTTMBackpack);
            }
        };
    }

    private FluidTank createFluidHandler(int capacity)
    {
        return new FluidTank(capacity)
        {
            @Override
            protected void onContentsChanged()
            {
                setDataChanged(TANKS_DATA);
            }
        };
    }

//    @Nullable
//    @Override
//    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
//        return null;
//    }
}
