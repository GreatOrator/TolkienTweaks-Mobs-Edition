package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.fluid.FluidBlock;
import com.greatorator.tolkienmobs.utils.TTMUtilities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class SpecialInit {
    public static final BlockFluidClassic enchanted_water = null;

    @Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            registry.register(TTMUtilities.setBlockName(new FluidBlock(FluidInit.ENCHANTED_WATER, Material.WATER), "enchanted_water"));
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onModelEvent(final ModelRegistryEvent event)
        {
            registerBlockModel(enchanted_water);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockModel(Block parBlock)
    {
        registerBlockModel(parBlock, 0);
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockModel(Block parBlock, int parMetaData)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(parBlock), parMetaData,
                new ModelResourceLocation(TolkienMobs.MODID + ":" + parBlock.getUnlocalizedName().substring(5), "inventory"));
    }
}
