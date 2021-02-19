//package com.greatorator.tolkienmobs.handler;
//
//import net.minecraft.block.Block;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.common.IFuelHandler;
//import org.apache.commons.lang3.tuple.Pair;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class TTMFuel implements IFuelHandler {
//    private Map<Pair<Item, Integer>, Integer> itemMetaFuelList = new HashMap<Pair<Item, Integer>, Integer>();
//    private Map<Item, Integer> itemFuelList = new HashMap<Item, Integer>();
//
//    @Override
//    public int getBurnTime(ItemStack fuel) {
//        return getFuelValue(fuel);
//    }
//
//    // register an item with a specific meta value as a fuel
//    public void addFuel(Item item, int metadata, int value)
//    {
//        itemMetaFuelList.put(Pair.of(item, metadata), value);
//    }
//
//    // register an item as a fuel (for any meta value)
//    public void addFuel(Item item, int value)
//    {
//        itemFuelList.put(item, value);
//    }
//
//    public void addFuel(Block block, int metadata, int value)
//    {
//        addFuel(Item.getItemFromBlock(block), metadata, value);
//    }
//
//    public void addFuel(Block block, int value)
//    {
//        addFuel(Item.getItemFromBlock(block), value);
//    }
//
//    public int getFuelValue(ItemStack stack)
//    {
//        if (stack == null) {return 0;}
//        Item item = stack.getItem();
//        if (item == null) {return 0;}
//        Pair<Item, Integer> pair = Pair.of(item, stack.getItemDamage());
//
//        // see if the specific item/meta combination is registered
//        if (itemMetaFuelList.containsKey(pair))
//        {
//            return itemMetaFuelList.get(pair);
//        }
//
//        // see if the item in general is registered
//        if (itemFuelList.containsKey(item))
//        {
//            return itemFuelList.get(item);
//        }
//
//        // otherwise no value as fuel
//        return 0;
//    }
//}