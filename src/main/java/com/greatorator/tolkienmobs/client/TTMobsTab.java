package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TTMobsTab extends CreativeTabs
{
    private String label;
    private int tab;

    static ItemStack itemStackIngot = ItemStack.EMPTY;

    public TTMobsTab(int id, String modid, String label, int tab) {
        super(id, modid);
        this.label = label;
        this.tab = tab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (tab == 0) {

        }
    public TTMobsTab(String label) { super("TolkienTweaksMobs"); }
    public ItemStack getTabIconItem() { return new ItemStack(TTMFeatures.INGOT_MITHRIL);}
}
