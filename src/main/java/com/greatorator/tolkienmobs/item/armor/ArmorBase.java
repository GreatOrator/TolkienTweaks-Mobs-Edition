package com.greatorator.tolkienmobs.item.armor;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.util.interfaces.IHasModel;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IHasModel {
    public ArmorBase(String name, ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(TolkienMobs.MODID + ":" + name);
        setRegistryName(name);
        setCreativeTab(TolkienMobs.TTMOBS);

            ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        TolkienMobs.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
