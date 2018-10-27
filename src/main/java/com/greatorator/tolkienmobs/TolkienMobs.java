package com.greatorator.tolkienmobs;

import com.greatorator.tolkienmobs.proxy.CommonProxy;
import com.greatorator.tolkienmobs.util.Reference;
import com.greatorator.tolkienmobs.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)

public class TolkienMobs {
    @Instance
    public static TolkienMobs instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    public static final CreativeTabs TTMOBS = new TTMobsTab("TolkienTweaksMobs");

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {RegistryHandler.preInitRegistries();}
    @EventHandler
    public static void init(FMLInitializationEvent event) {RegistryHandler.initRegistries();}
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {RegistryHandler.postInitRegistries();}
}
