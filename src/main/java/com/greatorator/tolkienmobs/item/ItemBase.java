package com.greatorator.tolkienmobs.item;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.ItemInit;
import net.minecraft.item.Item;

@Deprecated
public class ItemBase extends Item {
    public ItemBase(String name) {
        setUnlocalizedName(TolkienMobs.MODID + ":" + name);
        setRegistryName(name);
        setCreativeTab(TolkienMobs.TTMOBS);

        ItemInit.ITEMS.add(this);
    }
}
