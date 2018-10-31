package com.greatorator.tolkienmobs.item;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Created by brandon3055 on 31/10/18.
 */
public class TTMSpawnEgg extends ItemMonsterPlacer {

    public TTMSpawnEgg() {}

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EntityList.EntityEggInfo info : EntityList.ENTITY_EGGS.values()) {
                if (info.spawnedID != null && info.spawnedID.getResourceDomain().equals(TolkienMobs.MODID)) {
                    ItemStack itemstack = new ItemStack(this, 1);
                    applyEntityIdToItemStack(itemstack, info.spawnedID);
                    items.add(itemstack);
                }
            }
        }
    }
}
