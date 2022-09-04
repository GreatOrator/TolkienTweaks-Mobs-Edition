package com.greatorator.tolkienmobs.client.gui.container;

//public class ContainerTTMBackpackItem extends ContainerTTMBackpack {
//    public ContainerTTMBackpackItem(int windowID, PlayerInventory playerInventory, PacketBuffer data) {
//        this(windowID, playerInventory, createInventory(playerInventory, data));
//    }
//
//    public ContainerTTMBackpackItem(int windowID, PlayerInventory invPlayer, ZoneTTMInventoryContents invZoneContents, ZoneTTMInventoryContents craftZoneContents, ZoneTTMInventoryContents craftOutputZoneContents, ZoneTTMInventoryContents fluidZoneContents, ZoneTTMInventoryContents fluidInputZoneContents, ZoneTTMInventoryContents fluidOutputZoneContents, DataTTMInventoryStateData invStateData) {
//        super(windowID, invPlayer, invZoneContents, craftZoneContents, craftOutputZoneContents, fluidZoneContents, fluidInputZoneContents, fluidOutputZoneContents, invStateData);
//    }
//
//    private static InventoryTTMBackpack createInventory(final PlayerInventory playerInventory, final PacketBuffer data)
//    {
//        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
//        Objects.requireNonNull(data, "data cannot be null");
//
//        final ItemStack stack; //Get ItemStack from hand or capability to avoid sending a lot of information by packetBuffer
//        final byte screenID = data.readByte();
//
//        if(screenID == Reference.ITEM_SCREEN_ID)
//        {
//            stack = playerInventory.player.getItemBySlot(EquipmentSlotType.MAINHAND);
//        }
//        else
//        {
//            if(data.writerIndex() == 5)
//            {
//                final int entityId = data.readInt();
//                stack = CapabilityUtils.getWearingBackpack((PlayerEntity)playerInventory.player.level.getEntity(entityId));
//
//                if(stack.getItem() instanceof ItemTTMBackpack)
//                {
//                    return CapabilityUtils.getBackpackInv((PlayerEntity)playerInventory.player.level.getEntity(entityId));
//                }
//            }
//            else
//            {
//                stack = CapabilityUtils.getWearingBackpack(playerInventory.player);
//            }
//        }
//        if(stack.getItem() instanceof ItemTTMBackpack)
//        {
//            if(screenID == Reference.WEARABLE_SCREEN_ID)
//            {
//                return CapabilityUtils.getBackpackInv(playerInventory.player);
//            }
//            else if(screenID == Reference.ITEM_SCREEN_ID)
//            {
//                return new InventoryTTMBackpack(stack, playerInventory.player, screenID);
//            }
//        }
//        throw new IllegalStateException("ItemStack is not correct! " + stack);
//    }
//}