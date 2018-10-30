package com.greatorator.tolkienmobs.objects.blocks.item;

import com.greatorator.tolkienmobs.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ItemBlockBase extends ItemBlock {
    private String registryDomain = null;
    public Map<Integer, String> nameOverrides = new HashMap<>();

    public ItemBlockBase(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BlockBase base = block instanceof BlockBase ? (BlockBase) block : null;

        if (base != null && base.nameOverrides.containsKey(stack.getItemDamage())) {
            return "tile." + getRegistryDomain() + ":" + base.nameOverrides.get(stack.getItemDamage());
        }
        else if (!nameOverrides.isEmpty() && nameOverrides.containsKey(stack.getItemDamage())) {
            return "tile." + getRegistryDomain() + ":" + nameOverrides.get(stack.getItemDamage());
        }

        return super.getUnlocalizedName(stack);
    }

    public ItemBlockBase addName(int meta, String name) {
        nameOverrides.put(meta, name);
        setHasSubtypes(true);
        return this;
    }

    @Override
    public int getMetadata(int damage) {
        return getHasSubtypes() ? damage : 0;
    }

    public String getRegistryDomain() {
        if (registryDomain == null) {
            ResourceLocation regName = getRegistryName();
            registryDomain = regName == null ? "null" : regName.getResourceDomain();
        }

        return registryDomain;
    }
}