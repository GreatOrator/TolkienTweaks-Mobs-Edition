package com.greatorator.tolkienmobs.objects.tools;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.util.interfaces.IHasModel;
import net.minecraft.item.ItemSpade;

public class ToolShovel extends ItemSpade implements IHasModel {
    public ToolShovel(String name, ToolMaterial material) {
            super(material);
            setUnlocalizedName(name);
            setRegistryName(name);
            setCreativeTab(TolkienMobs.TTMOBS);

            ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels(){
        TolkienMobs.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
