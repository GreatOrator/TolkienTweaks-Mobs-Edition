package com.greatorator.tolkienmobs.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TolkienTabs {
    public static final CreativeModeTab toolsGroup = new CreativeModeTab("tolkienmobs.tools") {
        public ItemStack makeIcon() {
            return new ItemStack(TolkienItems.PICKAXE_MITHRIL.get());
        }
    };
    public static final CreativeModeTab matsGroup = new CreativeModeTab("tolkienmobs.mats") {
        public ItemStack makeIcon() {
            return new ItemStack(TolkienItems.INGOT_MITHRIL.get());
        }
    };
    public static final CreativeModeTab decoGroup = new CreativeModeTab("tolkienmobs.deco") {
        public ItemStack makeIcon() {
            return new ItemStack(TolkienItems.PIGGYBANK_ITEM.get());
        }
    };
    public static final CreativeModeTab spawnGroup = new CreativeModeTab("tolkienmobs.spawn") {
        public ItemStack makeIcon() {
            return new ItemStack(TolkienItems.GOLEM_STONE_SUMMON.get());
        }
    };
    public static final CreativeModeTab foodGroup = new CreativeModeTab("tolkienmobs.food") {
        public ItemStack makeIcon() {
            return new ItemStack(TolkienItems.LEMBAS.get());
        }
    };
    public static final CreativeModeTab questGroup = new CreativeModeTab("tolkienmobs.quest") {
        public ItemStack makeIcon() {
            return new ItemStack(TolkienItems.ITEM_FORTRESSMAP.get());
        }
    };

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Creative Tabs";
    }
}