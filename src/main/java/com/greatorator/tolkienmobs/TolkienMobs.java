package com.greatorator.tolkienmobs;

import com.brandon3055.brandonscore.BrandonsCore;
import com.greatorator.tolkienmobs.client.TTMobsTab;
import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TolkienMobs.MODID, name = TolkienMobs.NAME, version = TolkienMobs.VERSION, dependencies = TolkienMobs.DEPENDENCIES, guiFactory = TolkienMobs.GUI_FACTORY)
public class TolkienMobs {
    //Static mod fields
    public static final String MODID = "tolkienmobs";
    public static final String NAME = "Tolkien Tweaks (Mobs Edition)";
    public static final String VERSION = "${mod_version}"; //This will now be set automatically by the build.gradle when the jar is built.
    public static final String CLIENT_PROXY = "com.greatorator.tolkienmobs.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.greatorator.tolkienmobs.proxy.CommonProxy";
    public static final String DEPENDENCIES = "required-after:brandonscore@[" + BrandonsCore.VERSION + ",);"; //Will depend on the version of BC that the project is built against
    public static final String GUI_FACTORY = "com.greatorator.tolkienmobs.client.TTMGuiFactory"; //Using BC's config system this is all you need to add an in game mod config gui.

    @Instance
    public static TolkienMobs instance;

    @SidedProxy(clientSide = TolkienMobs.CLIENT_PROXY, serverSide = TolkienMobs.COMMON_PROXY)
    public static CommonProxy proxy;

    public static CreativeTabs TTMOBS = new TTMobsTab("TolkienTweaksMobs");

    //I like to run the init events through the proxy because for one thing it makes your main class a lot cleaner
    //And it also makes it dead simple to manage client/server side code

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
