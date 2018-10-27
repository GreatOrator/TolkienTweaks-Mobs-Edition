package com.greatorator.tolkienmobs.objects.items;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.util.Reference;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase(String name) {
        setUnlocalizedName(Reference.MODID + ":" + name);
        setRegistryName(name);
        setCreativeTab(TolkienMobs.TTMOBS);

        ItemInit.ITEMS.add(this);
    }
}
