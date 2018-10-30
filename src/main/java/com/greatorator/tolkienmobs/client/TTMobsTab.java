package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TTMobsTab extends CreativeTabs
{
    public TTMobsTab(String label) { super("TolkienTweaksMobs"); }
    public ItemStack getTabIconItem() { return new ItemStack(TTMFeatures.INGOT_MITHRIL);}
}
