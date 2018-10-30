package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.util.interfaces.IHasModel;
import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe implements IHasModel {
    public ToolPickaxe(String name, ToolMaterial material){
            super(material);
            setUnlocalizedName(TolkienMobs.MODID + ":" + name);
            setRegistryName(name);
            setCreativeTab(TolkienMobs.TTMOBS);

            ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels(){
        TolkienMobs.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
