package com.greatorator.tolkienmobs;

import com.greatorator.tolkienmobs.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TolkienMobs.MODID, version = TolkienMobs.VERSION)
public class TolkienMobs
{
    public static final String MODID = "tolkienmobs";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(clientSide = "com.greatorator.tolkienmobs.proxy.ClientProxy", serverSide = "com.greatorator.tolkienmobs.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static TolkienMobs instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
    }
}
