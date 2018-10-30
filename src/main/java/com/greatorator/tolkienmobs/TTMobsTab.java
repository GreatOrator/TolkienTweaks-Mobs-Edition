package com.greatorator.tolkienmobs;

import com.greatorator.tolkienmobs.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TTMobsTab extends CreativeTabs
{
    public TTMobsTab(String label) { super("TolkienTweaksMobs"); }
    public ItemStack getTabIconItem() { return new ItemStack(ItemInit.INGOT_MITHRIL);}
}
