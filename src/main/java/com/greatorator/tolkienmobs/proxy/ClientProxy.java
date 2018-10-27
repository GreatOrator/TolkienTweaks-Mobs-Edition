package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
    }

    @Override
    public void register(Object o) {
        if(o instanceof Block) {
            Block toRegister = (Block) o;
            String name = toRegister.getRegistryName().toString();
            registerItemRenderer(Item.getItemFromBlock(toRegister), 0, name);
        } else if (o instanceof Item) {
            Item toRegister = (Item) o;
            String name = toRegister.getRegistryName().toString();
            registerItemRenderer(toRegister, 0, name);
        } else {
            String s = o.toString();
            Throwable t = new Throwable(s);
            throw new IllegalArgumentException("Please give a registerable object as an Argument!!", t);
        }
    }
}
