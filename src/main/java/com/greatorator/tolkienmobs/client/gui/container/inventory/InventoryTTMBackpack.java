package com.greatorator.tolkienmobs.client.gui.container.inventory;

//public class InventoryTTMBackpack implements ITTMBackpackInventory, INamedContainerProvider {
//    private final ItemStackHandler inventory = createHandler(TTMBackpackTile.INV_SLOTS_COUNT);
//    private final ItemStackHandler craftingInventory = createHandler(TTMBackpackTile.CRAFT_SLOTS_COUNT);
//    private final FluidTank fluidTank = createFluidHandler(3000);
//    private final TTMSlotManager slotManager = new TTMSlotManager(this);
//    private final PlayerEntity player;
//    private ItemStack stack;
//    private boolean ability;
//    private int lastTime;
//    private final byte screenID;
//
//    private final String INVENTORY = "Inventory";
//    private final String CRAFTING_INVENTORY = "CraftingInventory";
//    private final String LEFT_TANK = "LeftTank";
//    private final String RIGHT_TANK = "RightTank";
//    private final String COLOR = "Color";
//    private final String ABILITY = "Ability";
//    private final String LAST_TIME = "LastTime";
//
//    public InventoryTTMBackpack(ItemStack stack, PlayerEntity player, byte screenID)
//    {
//        this.player = player;
//        this.stack = stack;
//        this.screenID = screenID;
//
//        this.loadAllData(stack.getOrCreateTag());
//    }
//
//    public void setStack(ItemStack stack)
//    {
//        this.stack = stack;
//    }
//
//    @Override
//    public ItemStackHandler getInventory()
//    {
//        return this.inventory;
//    }
//
//    @Override
//    public ItemStackHandler getCraftingGridInventory()
//    {
//        return this.craftingInventory;
//    }
//
//    @Override
//    public FluidTank getLeftTank()
//    {
//        return this.fluidTank;
//    }
//
//    @Override
//    public void saveAllData(CompoundNBT compound)
//    {
//        this.saveTanks(compound);
//        this.saveItems(compound);
//        this.saveAbility(compound);
//        this.saveTime(compound);
//        this.slotManager.saveUnsortableSlots(compound);
//    }
//
//    @Override
//    public void loadAllData(CompoundNBT compound)
//    {
//        this.loadTanks(compound);
//        this.loadItems(compound);
//        this.loadAbility(compound);
//        this.loadTime(compound);
//        this.slotManager.loadUnsortableSlots(compound);
//    }
//
//    @Override
//    public void saveItems(CompoundNBT compound)
//    {
//        compound.put(INVENTORY, this.inventory.serializeNBT());
//        compound.put(CRAFTING_INVENTORY, this.craftingInventory.serializeNBT());
//    }
//
//    @Override
//    public void loadItems(CompoundNBT compound)
//    {
//        this.inventory.deserializeNBT(compound.getCompound(INVENTORY));
//        this.craftingInventory.deserializeNBT(compound.getCompound(CRAFTING_INVENTORY));
//    }
//
//    @Override
//    public void saveTanks(CompoundNBT compound)
//    {
//        compound.put(LEFT_TANK, this.fluidTank.writeToNBT(new CompoundNBT()));
//    }
//
//    @Override
//    public void loadTanks(CompoundNBT compound)
//    {
//        this.fluidTank.readFromNBT(compound.getCompound(LEFT_TANK));
//    }
//
//    @Override
//    public void saveColor(CompoundNBT compound) {}
//    @Override
//    public void loadColor(CompoundNBT compound) {}
//
//    @Override
//    public void saveAbility(CompoundNBT compound)
//    {
//        compound.putBoolean(ABILITY, this.ability);
//    }
//
//    @Override
//    public void loadAbility(CompoundNBT compound)
//    {
//        this.ability = compound.getBoolean(ABILITY);
//    }
//
//    @Override
//    public void saveTime(CompoundNBT compound)
//    {
//        compound.putInt(LAST_TIME, this.lastTime);
//    }
//
//    @Override
//    public void loadTime(CompoundNBT compound)
//    {
//        this.lastTime = compound.getInt(LAST_TIME);
//    }
//
//    @Override
//    public boolean updateTankSlots()
//    {
//        return InventoryActions.transferContainerTank(this, getLeftTank(), Reference.BUCKET_IN_LEFT, player) || InventoryActions.transferContainerTank(this, getRightTank(), Reference.BUCKET_IN_RIGHT, player);
//    }
//
//    private void sendPackets()
//    {
//        if(screenID == Reference.WEARABLE_SCREEN_ID)
//        {
//            CapabilityUtils.synchronise(player);
//            CapabilityUtils.synchroniseToOthers(player);
//        }
//    }
//
//    @Override
//    public boolean hasColor()
//    {
//        return stack.getOrCreateTag().contains(COLOR);
//    }
//
//    @Override
//    public int getColor()
//    {
//        if(hasColor())
//        {
//            return stack.getOrCreateTag().getInt(COLOR);
//        }
//        return 0;
//    }
//
//    @Override
//    public boolean getAbilityValue()
//    {
//        return TravelersBackpackConfig.enableBackpackAbilities ? this.ability : false;
//    }
//
//    @Override
//    public void setAbility(boolean value)
//    {
//        this.ability = value;
//    }
//
//    @Override
//    public int getLastTime()
//    {
//        return this.lastTime;
//    }
//
//    @Override
//    public void setLastTime(int time)
//    {
//        this.lastTime = time;
//    }
//
//    @Override
//    public boolean hasTileEntity()
//    {
//        return false;
//    }
//
//    @Override
//    public boolean isSleepingBagDeployed()
//    {
//        return false;
//    }
//
//    @Override
//    public TTMSlotManager getSlotManager()
//    {
//        return slotManager;
//    }
//
//    @Override
//    public ItemStack decrStackSize(int index, int count)
//    {
//        ItemStack itemstack = ItemStackUtils.getAndSplit(getInventory(), index, count);
//
//        if(!itemstack.isEmpty())
//        {
//            setDataChanged(COMBINED_INVENTORY_DATA);
//        }
//        return itemstack;
//    }
//
//    @Override
//    public World getLevel()
//    {
//        return this.player.level;
//    }
//
//    @Override
//    public BlockPos getPosition()
//    {
//        return this.player.blockPosition();
//    }
//
//    @Override
//    public byte getScreenID()
//    {
//        return this.screenID;
//    }
//
//    @Override
//    public ItemStack getItemStack()
//    {
//        return this.stack;
//    }
//
//    @Override
//    public void setUsingPlayer(@Nullable PlayerEntity player) {}
//
//    @Override
//    public void setDataChanged(byte... dataIds)
//    {
//        if(getLevel().isClientSide) return;
//
//        for(byte data : dataIds)
//        {
//            switch(data)
//            {
//                case INVENTORY_DATA: stack.getOrCreateTag().put(INVENTORY, this.inventory.serializeNBT());
//                case CRAFTING_INVENTORY_DATA: stack.getOrCreateTag().put(CRAFTING_INVENTORY, this.craftingInventory.serializeNBT());
//                case COMBINED_INVENTORY_DATA: saveItems(stack.getOrCreateTag());
//                case TANKS_DATA: saveTanks(stack.getOrCreateTag());
//                case COLOR_DATA: saveColor(stack.getOrCreateTag());
//                case ABILITY_DATA: saveAbility(stack.getOrCreateTag());
//                case LAST_TIME_DATA: saveTime(stack.getOrCreateTag());
//                case SLOT_DATA: slotManager.saveUnsortableSlots(stack.getOrCreateTag());
//                case ALL_DATA: saveAllData(stack.getOrCreateTag());
//            }
//        }
//        sendPackets();
//    }
//
//    @Override
//    public void setChanged() {}
//
//    @Override
//    public ITextComponent getDisplayName()
//    {
//        return new TranslationTextComponent("screen.tolkienmobs.item");
//    }
//
//    public static void abilityTick(PlayerEntity player)
//    {
//        if(player.isAlive() && CapabilityUtils.isWearingBackpack(player))
//        {
//            InventoryTTMBackpack inv = CapabilityUtils.getBackpackInv(player);
//
//            if(!player.level.isClientSide)
//            {
//                if(inv.getLastTime() > 0)
//                {
//                    inv.setLastTime(inv.getLastTime() - 1);
//                    inv.setDataChanged(LAST_TIME_DATA);
//                }
//            }
//
//            if(inv.getAbilityValue())
//            {
//                BackpackAbilities.ABILITIES.abilityTick(CapabilityUtils.getWearingBackpack(player), player, null);
//            }
//        }
//    }
//
//    public static void openGUI(ServerPlayerEntity serverPlayerEntity, ItemStack stack, byte screenID)
//    {
//        if(!serverPlayerEntity.level.isClientSide)
//        {
//            if(screenID == Reference.ITEM_SCREEN_ID)
//            {
//                NetworkHooks.openGui(serverPlayerEntity, new InventoryTTMBackpack(stack, serverPlayerEntity, screenID), packetBuffer -> packetBuffer.writeByte(screenID));
//            }
//
//            if(screenID == Reference.WEARABLE_SCREEN_ID)
//            {
//                NetworkHooks.openGui(serverPlayerEntity, CapabilityUtils.getBackpackInv(serverPlayerEntity), packetBuffer -> packetBuffer.writeByte(screenID));
//            }
//        }
//    }
//
//    @Nullable
//    @Override
//    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity)
//    {
//        return new ContainerTTMBackpackItem(windowID, playerInventory, this);
//    }
//
//    private ItemStackHandler createHandler(int size)
//    {
//        return new ItemStackHandler(size)
//        {
//            @Override
//            protected void onContentsChanged(int slot)
//            {
//                setDataChanged(COMBINED_INVENTORY_DATA);
//            }
//
//            @Override
//            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
//            {
//                return !(stack.getItem() instanceof ItemTTMBackpack);
//            }
//        };
//    }
//
//    private FluidTank createFluidHandler(int capacity)
//    {
//        return new FluidTank(capacity)
//        {
//            @Override
//            protected void onContentsChanged()
//            {
//                setDataChanged(TANKS_DATA);
//            }
//        };
//    }
//}
