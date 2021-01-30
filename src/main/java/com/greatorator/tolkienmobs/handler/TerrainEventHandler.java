//package com.greatorator.tolkienmobs.handler;
//
//import com.brandon3055.brandonscore.utils.LogHelperBC;
//import com.greatorator.tolkienmobs.init.BiomeInit;
//import com.greatorator.tolkienmobs.world.gen.WorldGenCustomFlowers;
//import net.minecraftforge.event.terraingen.BiomeEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
///**
// * Created by brandon3055 on 11/11/18.
// */
//public class TerrainEventHandler {
//
//    @SubscribeEvent
//    public void createDecoratorEvent(BiomeEvent.CreateDecorator event) {
//        if (event.getBiome() == BiomeInit.MIRKWOOD) {
//            LogHelperBC.dev("Add Flower Generator");
//            event.getNewBiomeDecorator().flowerGen = new WorldGenCustomFlowers();
//        }
//    }
//
//}